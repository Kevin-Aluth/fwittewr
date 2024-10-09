package com.thethirdway.fwittewr.facade;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Component;

import com.thethirdway.fwittewr.dto.request.CreatePostDTO;
import com.thethirdway.fwittewr.dto.request.LikePostDTO;
import com.thethirdway.fwittewr.dto.response.PostDTO;
import com.thethirdway.fwittewr.mapper.PostMapper;
import com.thethirdway.fwittewr.model.Post;
import com.thethirdway.fwittewr.model.User;
import com.thethirdway.fwittewr.service.PostService;
import com.thethirdway.fwittewr.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class PostFacade {
	private final PostService postService; 
	private final UserService userService; 
	private final PostMapper postMapper; 
	
	public void createPost(CreatePostDTO request, User u) {
		Post p = postMapper.fromCreatePostDTO(request); 
		
		p.setUser(u);
		p.setPublishmentDate(LocalDateTime.now());
		
		postService.savePost(p);
	}
	
	public void likePost(LikePostDTO request, long userId) {
		Post p = postService.getPostById(request.getPostId()); 
		userService.likePost(userId, p);
	}
	
	public List<PostDTO> getPostsInDateOrder() {
		List<PostDTO> posts = postMapper.toPostDTOList(postService.getPostsInDateOrder()); 
		return posts;
	}
}
