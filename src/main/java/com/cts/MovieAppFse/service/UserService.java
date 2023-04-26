package com.cts.MovieAppFse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cts.MovieAppFse.model.User;
import com.cts.MovieAppFse.model.UserLogin;
import com.cts.MovieAppFse.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	
	public boolean loginUser(UserLogin userLogin) {
		User user=userRepository.findByEmail(userLogin.getEmail());
		if(user!=null) {
			return true;
		}else {
			return false;
		}
	}
	
	public ResponseEntity<String> forgetPassword(UserLogin userlogin) {
		
		
		User user=userRepository.findByEmail(userlogin.getEmail());
		if(user!=null) {
			user.setPassword(userlogin.getPassword());
			userRepository.save(user);
			return new ResponseEntity<String>("Password Updated Succesfully!!!", HttpStatus.OK);
		}
		
		//user does not exists
		return new ResponseEntity<String>("User Does not exits!!", HttpStatus.OK);
		
	}
}
