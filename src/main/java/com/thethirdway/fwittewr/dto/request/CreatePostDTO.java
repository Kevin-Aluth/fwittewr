package com.thethirdway.fwittewr.dto.request;

import lombok.Data;

@Data
public class CreatePostDTO {
	private long userId;
	private String content; 
}
