package com.thethirdway.fwittewr.serviceJPA;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.thethirdway.fwittewr.dto.request.CreateUserDTO;
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
				.orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "username or password incorrect"));
	}
}
