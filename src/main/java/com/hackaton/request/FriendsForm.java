package com.hackaton.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class FriendsForm {
	@NotBlank
	@Size(min = 3, max = 100)
	private String name;
	@NotBlank
	@Email(message="Please provide valid Email Address")
	@Size(min = 3, max = 50)
	private String email;
	@NotBlank
	@Size(min = 3, max = 15)
	private Integer phone;
	@NotBlank
	@Size(min = 3, max = 250)
	private String message;
	

}
