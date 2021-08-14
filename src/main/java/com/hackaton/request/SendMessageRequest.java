package com.hackaton.request;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class SendMessageRequest {
	@NotBlank
	private String friendName;

	@NotBlank
	private String message;
}
