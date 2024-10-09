package com.thethirdway.fwittewr.mapper;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.thethirdway.fwittewr.dto.request.CreateUserDTO;
import com.thethirdway.fwittewr.dto.response.UserDTO;
import com.thethirdway.fwittewr.model.Role;
import com.thethirdway.fwittewr.model.User;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserMapper {
	private final PostMapper postMapper;
	
	public User fromCreateUserDTO(CreateUserDTO uDTO) {
		if (!uDTO.getPassword().equals(uDTO.getRepeatPassword())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "password and repeated password are not equals");
		}
		User u = new User(); 
		
		u.setName(uDTO.getName());
		u.setPassword(uDTO.getPassword());
		u.setEmail(uDTO.getEmail());
		u.setRole(Role.USER);
		u.setPublishedPosts(new ArrayList<>());
		u.setLikedPosts(new ArrayList<>());
		
		return u;
	}
	
	public UserDTO toUserDTO(User u) {
		UserDTO uDTO = new UserDTO(); 
		 
		uDTO.setId(u.getId());
		uDTO.setName(u.getName());
		uDTO.setLikedPosts(postMapper.toPostDTOList(u.getLikedPosts()));
		uDTO.setPublishedPosts(postMapper.toPostDTOList(u.getPublishedPosts()));
		
		return uDTO;
	}
}
