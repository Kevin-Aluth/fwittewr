package com.thethirdway.fwittewr.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreatePostDTO {
	@NotBlank(message = "provide post with content")
	private String content; 
}
