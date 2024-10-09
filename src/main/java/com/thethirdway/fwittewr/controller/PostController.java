package com.thethirdway.fwittewr.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thethirdway.fwittewr.dto.request.CreatePostDTO;
import com.thethirdway.fwittewr.dto.request.LikePostDTO;
import com.thethirdway.fwittewr.dto.response.PostDTO;
import com.thethirdway.fwittewr.facade.PostFacade;
import com.thethirdway.fwittewr.model.User;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PostController {
	private final PostFacade facade; 
	
	@PostMapping("/user/createPost")
	ResponseEntity<Void> createPost(@Valid @RequestBody CreatePostDTO request, UsernamePasswordAuthenticationToken upat) {
		User u = (User)upat.getPrincipal();
		facade.createPost(request, u); 
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PutMapping("/user/likePost")
	ResponseEntity<Void> likePost(@Valid @RequestBody LikePostDTO request, UsernamePasswordAuthenticationToken upat) {
		User u = (User)upat.getPrincipal(); 
		facade.likePost(request, u.getId()); 
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/recent")
	ResponseEntity<List<PostDTO>> getPostsInDateOrder() {
		List<PostDTO> posts = facade.getPostsInDateOrder(); 
		
		return new ResponseEntity<>(posts, HttpStatus.OK);
	}
}
