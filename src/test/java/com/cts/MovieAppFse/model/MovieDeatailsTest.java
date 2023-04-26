package com.cts.MovieAppFse.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MovieDeatailsTest {
	
	Movie movie=new Movie();
	@Test
	public void MovieTest() {
		assertThat(movie).isNotNull();
	}
	
	@Test 
	public void GetSetMovieTest() {
		Movie movie=new Movie();
		movie.setMid(new Movie.MovieId("RRR","Inox"));

		movie.setAllotedSeats(300);
		assertEquals("RRR",movie.getMid().getMovieName());
		assertEquals("Inox", movie.getMid().getTheatreName());
		assertEquals(300, movie.getAllotedSeats());
		
		
	}
	
	@Test
	public void checkConstructor() {
		Movie movie=new Movie(new Movie.MovieId("RRR", "Inox"),300);
		assertEquals("RRR",movie.getMid().getMovieName());
		assertEquals("Inox", movie.getMid().getTheatreName());
		assertEquals(300, movie.getAllotedSeats());
	}
}
