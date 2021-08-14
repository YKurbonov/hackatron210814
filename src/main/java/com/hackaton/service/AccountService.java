package com.hackaton.service;


import com.hackaton.model.Account;
import com.hackaton.model.Friends;
import com.hackaton.model.User;
import com.hackaton.request.SendMessageRequest;


public interface AccountService {
	Account createAccount();

	void saveFriend(Friends recipient);
	
	void communication(SendMessageRequest request, User user);
}
