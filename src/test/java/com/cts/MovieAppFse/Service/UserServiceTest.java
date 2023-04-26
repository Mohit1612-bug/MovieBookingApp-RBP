package com.cts.MovieAppFse.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cts.MovieAppFse.model.User;
import com.cts.MovieAppFse.model.UserLogin;
import com.cts.MovieAppFse.repository.UserRepository;
import com.cts.MovieAppFse.service.UserService;

public class UserServiceTest {
	
	private UserService userService;
	
	@MockBean
	private UserRepository userRepository;
	
	
	
	@Test
	public void loginUserTest() {
		User user=new User(123,"Mohit","Chaudhari","mc@gmail.com","mca@1234","63436314");
		when(userRepository.findByEmail("mc@gmail.com")).thenReturn(user);
		
		assertEquals(true, userService.loginUser(new UserLogin("mc@gmail.com", "mca@1234")));
		
	}
}
