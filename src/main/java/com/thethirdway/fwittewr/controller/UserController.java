package com.thethirdway.fwittewr.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thethirdway.fwittewr.dto.request.CreateUserDTO;
import com.thethirdway.fwittewr.dto.request.LoginDTO;
import com.thethirdway.fwittewr.dto.response.UserDTO;
import com.thethirdway.fwittewr.facade.UserFacade;
import com.thethirdway.fwittewr.model.User;
import com.thethirdway.fwittewr.security.TokenManagerService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {
	private final UserFacade facade; 
	private final TokenManagerService tokenService; 
	
	@PostMapping("/register")
	public ResponseEntity<Void> registerUser(@Valid @RequestBody CreateUserDTO request) {
		facade.registerUser(request); 
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping("/login")
	public ResponseEntity<Void> login(@Valid @RequestBody LoginDTO request) {
		User u = facade.login(request);
		return ResponseEntity.status(HttpStatus.OK).header("Authorization", tokenService.createToken(u)).build();
	}
	
	@GetMapping("/user/info")
	public ResponseEntity<UserDTO> getPersonalInfo(UsernamePasswordAuthenticationToken upat) {
		User u = (User)upat.getPrincipal();
		return new ResponseEntity<>(facade.getById(u.getId()), HttpStatus.OK);
	}
}
