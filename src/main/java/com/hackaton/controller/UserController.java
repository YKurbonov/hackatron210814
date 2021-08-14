package com.hackaton.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hackaton.dao.UserDAO;
import com.hackaton.response.UserResponse;
import com.hackaton.service.UserService;

@CrossOrigin(origins="*", allowedHeaders = "*")
@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserService userService;

	@GetMapping("/all")
	public ResponseEntity<UserResponse> getAllUsers() {
		UserResponse response = new UserResponse();
		List<UserDAO> users = userService.getAllUsers();
		response.setUsers(users);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
