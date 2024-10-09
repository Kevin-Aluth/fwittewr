package com.thethirdway.fwittewr.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateUserDTO {
	@NotBlank(message = "insert a name")
	private String name; 
	@Email
	@NotBlank(message = "insert an email")
	private String email; 
	@NotBlank(message = "insert a password") 
	private String password; 
	@NotBlank(message = "insert the password a second time")
	private String repeatPassword; 
}
