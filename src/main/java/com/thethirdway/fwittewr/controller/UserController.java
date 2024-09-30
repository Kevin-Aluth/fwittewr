package com.thethirdway.fwittewr.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thethirdway.fwittewr.dto.request.CreateUserDTO;
import com.thethirdway.fwittewr.dto.request.LoginDTO;
import com.thethirdway.fwittewr.dto.response.UserDTO;
import com.thethirdway.fwittewr.facade.UserFacade;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
	private final UserFacade facade; 
	
	@PostMapping("/register")
	public ResponseEntity<Void> registerUser(@RequestBody CreateUserDTO request) {
		facade.registerUser(request); 
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping("/login")
	public ResponseEntity<UserDTO> login(@RequestBody LoginDTO request) {
		UserDTO uDTO = facade.login(request);
		
		return new ResponseEntity<>(uDTO, HttpStatus.OK);
	}
}
