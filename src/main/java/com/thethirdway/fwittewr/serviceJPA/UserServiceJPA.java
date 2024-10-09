package com.thethirdway.fwittewr.serviceJPA;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.thethirdway.fwittewr.dto.request.CreateUserDTO;
import com.thethirdway.fwittewr.model.Post;
import com.thethirdway.fwittewr.model.User;
import com.thethirdway.fwittewr.repo.UserRepository;
import com.thethirdway.fwittewr.service.UserService;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserServiceJPA implements UserService {
	
	private final UserRepository repo;

	@Override
	public void saveUser(User u) {
		if (u.getId() > 0) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "error: id already found");
		}
		repo.save(u);
	}

	@Override
	public User getUserByNameAndPassword(String name, String password) {
		return repo.findByNameAndPassword(name, password)
				.orElseThrow(()->new ResponseStatusException(HttpStatus.I_AM_A_TEAPOT, "username or password incorrect"));
	}

	@Override
	public User getUserById(long id) {
		return repo.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.I_AM_A_TEAPOT, "user id not found"));
	}

	@Override
	public User getUserByName(String name) {
		return repo.findByName(name).orElseThrow(()->new ResponseStatusException(HttpStatus.I_AM_A_TEAPOT, "user id not found"));
	}

	@Override
	public void likePost(long id, Post p) {
		User u = getUserById(id); 
		u.getLikedPosts().add(p); 
		repo.save(u); 
	}
}
