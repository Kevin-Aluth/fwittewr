package com.thethirdway.fwittewr.dto.response;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;

@Data
public class UserDTO {
	private long id;
	private String name;
	
	private List<PostDTO> publishedPosts; 
	private List<PostDTO> likedPosts; 
	
	@Data
	public static class PostDTO {
		private String content;
		private LocalDate publishmentDate;
	}
}
