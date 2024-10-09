package com.thethirdway.fwittewr.serviceJPA;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.thethirdway.fwittewr.model.Post;
import com.thethirdway.fwittewr.model.User;
import com.thethirdway.fwittewr.repo.PostRepository;
import com.thethirdway.fwittewr.service.PostService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PostServiceJPA implements PostService {
	
	private final PostRepository repo;
	
	@Override
	public void savePost(Post p) {
		if (p.getId() > 0) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "error: id already exists");
		}
		
		repo.save(p);
	}

	@Override
	public Post getPostById(long id) {
		return repo.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.I_AM_A_TEAPOT, "post id not found"));
	}

	@Override
	public List<Post> getPostsInDateOrder() {
		return repo.getPostsInDateOrder().orElseThrow(()->new ResponseStatusException(HttpStatus.NO_CONTENT, "there are no posts yet"));
	}
	
	public List<User> getLikedUsers(long id) {
		return repo.getPostsLikes(id).orElseThrow(()->new ResponseStatusException(HttpStatus.I_AM_A_TEAPOT, "error while fetching post or likes"));
	}

	@Override
	public void likePost(long id, User u) {
		Post p = getPostById(id);
		p.getLikedUsers().add(u);
		repo.save(p);
	}
}
