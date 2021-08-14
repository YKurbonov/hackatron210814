package com.hackaton.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hackaton.dao.UserDAO;
import com.hackaton.model.Friends;
import com.hackaton.model.User;
import com.hackaton.request.FriendsForm;
//import com.hackaton.request.CommunicationRequest;
import com.hackaton.request.SendMessageRequest;
import com.hackaton.response.CommunicationResponse;
import com.hackaton.service.AccountService;
import com.hackaton.service.UserService;

@CrossOrigin (origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/account")
public class AccountController {

	@Autowired
	AccountService accountService;
	
	@Autowired
	UserService userService;
	
		
	@PostMapping("/addFriend")
	public ResponseEntity<CommunicationResponse> addFriend(
			@Valid @RequestBody FriendsForm request) {
		CommunicationResponse response = new CommunicationResponse();
		User user = (User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		Friends friend = new Friends(request.getName(),
				request.getEmail(), request.getPhone(), request.getMessage());
		friend.setUser(user);
		accountService.saveFriend(friend);
		response.setMessage("Friend is successfully added");
		response.setSuccess(true);
		UserDAO userDAO = userService.getUserDAOByName(user.getUsername());
		response.setUser(userDAO);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PostMapping("/message")
	public ResponseEntity<CommunicationResponse> transfer(
			@Valid @RequestBody SendMessageRequest request) {
		CommunicationResponse response = new CommunicationResponse();
		User user = (User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		accountService.communication(request, user);
		response.setMessage("Message sent Successfully");
		response.setSuccess(true);
		UserDAO userDAO = userService.getUserDAOByName(user.getUsername());
		response.setUser(userDAO);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	
	
	
	
}
