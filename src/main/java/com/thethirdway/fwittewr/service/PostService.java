package com.thethirdway.fwittewr.service;

import java.util.List;

import com.thethirdway.fwittewr.model.Post;
import com.thethirdway.fwittewr.model.User;

public interface PostService {
	void savePost(Post p);
	void likePost(long id, User u);
	Post getPostById(long id);
	List<Post> getPostsInDateOrder();
	List<User> getLikedUsers(long id);
}
