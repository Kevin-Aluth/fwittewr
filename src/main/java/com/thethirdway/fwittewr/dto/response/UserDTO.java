package com.thethirdway.fwittewr.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class UserDTO {
	@JsonIgnore
	private long id;
	private String name;
	
	private List<PostDTO> publishedPosts; 
	private List<PostDTO> likedPosts; 
}
