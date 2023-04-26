package com.cts.MovieAppFse.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class TicketTest {

	Ticket ticket=new Ticket();
	
	
	List<String> ll=new ArrayList<>();
	
	
	
	@Test
	public void testTicket() {
		assertThat(ticket).isNotNull();
	}
	
	@Test
	public void testGetSet() {
		Ticket ticket=new Ticket();
		
		ticket.setMovieName("RRR");
		ticket.setNumberOfTickets(5);
		ticket.setSeats(ll);
		ticket.setTheatreName("Inox");
		ticket.setTicketId(123);
		
		
		assertEquals("RRR", ticket.getMovieName());
		assertEquals(5, ticket.getNumberOfTickets());
		assertEquals(ll, ticket.getSeats());
		assertEquals("Inox",ticket.getTheatreName());
		assertEquals(123, ticket.getTicketId());
		
		
	}
}
