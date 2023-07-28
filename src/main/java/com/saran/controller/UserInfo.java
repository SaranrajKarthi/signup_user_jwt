package com.saran.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.saran.entity.User;
import com.saran.service.UserService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@RestController
public class UserInfo {
	
	@Autowired
	UserService userService;

	@GetMapping("/my-info")
	public ResponseEntity<User> getMyInfo(@RequestHeader("Authorization") String token){
		Claims claims = validateToken(token);
		
		if (claims == null) {
			return ResponseEntity.status(401).build();			
		}
		String email =  claims.getSubject();
		User user = userService.getUserByEmail(email);
		return ResponseEntity.ok(user);
	}

	private Claims validateToken(String token) {

		try {
			return Jwts.parser().setSigningKey("8Ns5L8qvBbeIcAvF6zARokHy27SZdnq1").parseClaimsJws(token).getBody();
		} catch (Exception e) {
			return null;
		}
	}

}
