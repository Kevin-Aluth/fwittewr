package com.thethirdway.fwittewr.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thethirdway.fwittewr.model.User;

import lombok.Data;

@Data
public class PostDTO {
	@JsonIgnore
	private long id;
	private InternalUserDTO posterName;
	private String content;
	private LocalDateTime publishmentDate;
	private List<InternalUserDTO> likedUsers; 
	
	@Data
	public static class InternalUserDTO {
		@JsonIgnore
		private long id; 
		private String name;
	}
}
