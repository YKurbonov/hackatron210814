package com.hackaton.response;

import java.util.List;

import com.hackaton.dao.UserDAO;

import lombok.Data;

@Data
public class UserResponse {
	List<UserDAO> users;
}
