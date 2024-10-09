package com.thethirdway.fwittewr.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginDTO {
	@NotBlank(message = "insert a name")
	private String name; 
	@NotBlank(message = "insert a password")
	private String password;
}
