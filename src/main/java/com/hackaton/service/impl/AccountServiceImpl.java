package com.hackaton.service.impl;


import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hackaton.dao.AccountDAO;
import com.hackaton.model.Account;
import com.hackaton.model.Friends;
import com.hackaton.model.Communications;
import com.hackaton.model.User;
import com.hackaton.repository.AccountRepo;
import com.hackaton.repository.FriendsRepo;

import com.hackaton.request.SendMessageRequest;
import com.hackaton.service.AccountService;
import com.hackaton.service.CommunicationService;


@Service
public class AccountServiceImpl implements AccountService {
	@Autowired
	private AccountDAO accountDAO;
	
	@Autowired
	private AccountRepo accountRepo;
	
	@Autowired
	private FriendsRepo friendRepo;

	@Autowired
	private CommunicationService communicationService;

	@Override
	public Account createAccount() {
		Account account = new Account();
		account.setAccountNumber(getAccountNumber());
		accountRepo.save(account);
		return accountRepo.findByAccountNumber(account.getAccountNumber());
	}

	
	@Override
	public void saveFriend(Friends friend) {
		friendRepo.save(friend);
	}
	
	@Override
	public void communication(SendMessageRequest request, User user) {
		Account account = user.getAccount();
		
		accountDAO.save(account);
		
		Communications communication = new Communications(	"Sent message to " + request.getFriendName(), true, account);
		communicationService.saveCommunication(communication);
		
	}
	
	
	
	
	private Long getAccountNumber() {
		long smallest = 1000_0000_0000_0000L;
		long biggest = 9999_9999_9999_9999L;
		long random = ThreadLocalRandom.current().nextLong(smallest, biggest);
		return random;
	}

}
