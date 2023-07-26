package com.saran.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.saran.dto.ResponseDTO;
import com.saran.entity.User;
import com.saran.service.UserService;

@RestController
public class LoginController {
	
	@Autowired
	UserService userService;

	@PostMapping("/login")
	public ResponseDTO loginUser(@RequestBody User user) {
		ResponseDTO response = new ResponseDTO();
		
		User existingUser = userService.getUserByEmail(user.getEmail());
		
		if(existingUser == null) {
			response.setStatusCode("Error - 404");
			response.setMessage("User with E-mail does not exist");
			return response;
		}
		
		if(!existingUser.getPassword().equals(user.getPassword())) {
			response.setStatusCode("Error - 401");
			response.setMessage("Invalid Password");
			return response;
		}
		
		response.setStatusCode("Succeess - 200");
		response.setMessage("User Login Successfully");
		return response;
	}
}
