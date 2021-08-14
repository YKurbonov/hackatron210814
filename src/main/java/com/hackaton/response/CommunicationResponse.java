package com.hackaton.response;

import com.hackaton.dao.UserDAO;

import lombok.Data;

@Data
public class CommunicationResponse {
	boolean isSuccess;
	String message;
	UserDAO user;
}
