package com.thethirdway.fwittewr.service;

import com.thethirdway.fwittewr.model.User;

public interface UserService {
	void registerUser();
	void registerAdminUser();
	User loginUser();
	void createPost(); 
	void likePost();
}
