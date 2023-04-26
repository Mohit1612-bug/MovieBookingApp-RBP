package com.cts.MovieAppFse.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cts.MovieAppFse.model.Movie;
import com.cts.MovieAppFse.model.Ticket;
import com.cts.MovieAppFse.repository.MovieRepository;

@Service
public class MovieService {
	
	@Autowired
	private MovieRepository movieRepository;
	
	
	public List<Movie> SearchMovie(String movieName) {
		movieName=movieName.substring(0,1).toUpperCase()+movieName.substring(1).toLowerCase();
		List<Movie> movies=movieRepository.findAll();
		
		List<Movie> searchedMovie=new ArrayList<>();
		for (Movie movie : movies) {
			if(movie.getMid().getMovieName().contains(movieName)) {
				searchedMovie.add(movie);
			}
		}
		return searchedMovie;
	}
	
	public ResponseEntity<Map<String,Boolean>> deleteMovie(String movieName,String theatreName){
		movieRepository.deleteById(new Movie.MovieId(movieName,theatreName));
		Map<String,Boolean> response=new HashMap<>();
		
		response.put("deleted",Boolean.TRUE);
		return ResponseEntity.ok(response);
		
	}
	
	public List<Movie> showMovieTheatre(String movieName){
		List<Movie>movies=movieRepository.findAll();
		List<Movie>theatres=movies.stream().filter((movie)->movie.getMid().getMovieName().equals(movieName)).collect(Collectors.toList());
		
		return theatres;
	}
	
	public boolean updateTicketCount(Ticket ticket) {
		Movie movie=movieRepository.findById(new Movie.MovieId(ticket.getMovieName(), ticket.getTheatreName())).get();
		if(ticket.getNumberOfTickets()<movie.getAllotedSeats()) {
			int count=movie.getAllotedSeats()-ticket.getNumberOfTickets();
			movie.setAllotedSeats(count);
			movieRepository.save(movie);
			return true;
		}
		return false;
	}
}
