package com.cts.MovieAppFse.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cts.MovieAppFse.model.Movie;
import com.cts.MovieAppFse.model.Ticket;
import com.cts.MovieAppFse.model.User;
import com.cts.MovieAppFse.model.UserLogin;
import com.cts.MovieAppFse.repository.MovieRepository;
import com.cts.MovieAppFse.repository.TicketRepository;
import com.cts.MovieAppFse.repository.UserRepository;
import com.cts.MovieAppFse.service.MovieService;
import com.cts.MovieAppFse.service.UserService;

@RestController
public class MovieController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private TicketRepository ticketRepository;
	
	@Autowired
	private MovieService movieService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/check")
	public String CheckRoute() {
		return "Check";
	}
	
	@PostMapping("/registerUser")
	public ResponseEntity<User> regiserUser(@RequestBody User user){
		userRepository.save(user);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@PostMapping("/loginUser")
	public boolean loginUser(@RequestBody UserLogin userlogin) {
		return userService.loginUser(userlogin);
	}
	
	@PostMapping("/forgot/password")
	public ResponseEntity<String> forgotPassword(@RequestBody UserLogin userlogin){
		return userService.forgetPassword(userlogin);
	}
	
	
	
	@PostMapping("/movie/add")
	public ResponseEntity<Movie> addMovie(@RequestBody Movie movie){
		movieRepository.save(movie);
		return new ResponseEntity<Movie>(movie, HttpStatus.OK);
	}
	
	@PostMapping("/bookTickets")
	public ResponseEntity<String> bookTicket(@RequestBody Ticket ticket){
		if(movieService.updateTicketCount(ticket)) {
			ticketRepository.save(ticket);
			return new ResponseEntity<String>("Ticket Booked", HttpStatus.OK);
		}
		
		return new ResponseEntity<String>("tickets not available! ", HttpStatus.OK);
	}
	
	
	@GetMapping("/all")
	public ResponseEntity<List<Movie>> allMovies(){
		List<Movie> movies=movieRepository.findAll();
		return new ResponseEntity<List<Movie>>(movies, HttpStatus.OK);
	}
	
	@GetMapping("/movies/search/{movieName}")
	public ResponseEntity<?> SearchMovie(@PathVariable("movieName") String movieName){
		List<Movie> searchedMovies=movieService.SearchMovie(movieName);
		if(searchedMovies.size()!=0) {
			return new ResponseEntity<List<Movie>>(searchedMovies, HttpStatus.OK);
		}
		return new ResponseEntity<String>("No Movies Found", HttpStatus.OK);
	}
	
	@DeleteMapping("/{movieName}/delete/{id}")
	public ResponseEntity<Map<String,Boolean>> deleteMovie(@PathVariable("movieName") String movieName,@PathVariable("id") String theatreName){
		return movieService.deleteMovie(movieName, theatreName);
	}
	
	@GetMapping("/{movieName}/theatres")
	public ResponseEntity<List<Movie>> getTheatres(@PathVariable("movieName") String movieName){
		List<Movie>movies=movieService.showMovieTheatre(movieName);
		return new ResponseEntity<List<Movie>>(movies, HttpStatus.OK);
	}
}
