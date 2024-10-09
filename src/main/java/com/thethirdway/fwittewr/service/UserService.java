package com.thethirdway.fwittewr.service;

import com.thethirdway.fwittewr.model.Post;
import com.thethirdway.fwittewr.model.User;

public interface UserService {
	void saveUser(User u);
	User getUserByNameAndPassword(String name, String password);
	User getUserById(long id);
	User getUserByName(String name);
	void likePost(long id, Post p);
}
