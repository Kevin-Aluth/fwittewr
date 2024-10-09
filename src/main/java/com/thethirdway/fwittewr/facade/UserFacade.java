package com.thethirdway.fwittewr.facade;

import org.springframework.stereotype.Component;

import com.thethirdway.fwittewr.dto.request.CreateUserDTO;
import com.thethirdway.fwittewr.dto.request.LoginDTO;
import com.thethirdway.fwittewr.dto.response.UserDTO;
import com.thethirdway.fwittewr.mapper.UserMapper;
import com.thethirdway.fwittewr.model.User;
import com.thethirdway.fwittewr.service.UserService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserFacade {
	private final UserService userService;
	private final UserMapper userMapper;
	
	public void registerUser(CreateUserDTO uDTO) {
		User u = userMapper.fromCreateUserDTO(uDTO); 
		userService.saveUser(u);
	}
	
	public User login(LoginDTO loginDTO) {
		User u = userService.getUserByNameAndPassword(loginDTO.getName(), loginDTO.getPassword());
		
		return u;	
	}
	
	public UserDTO getById(long id) {
		User u = userService.getUserById(id); 
		return userMapper.toUserDTO(u); 
	}
}
