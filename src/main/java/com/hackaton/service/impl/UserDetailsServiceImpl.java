package com.hackaton.service.impl;

import static com.hackaton.util.DateUtil.SIMPLE_DATE_FORMAT;
import static com.hackaton.util.DateUtil.SIMPLE_DATE_TIME_FORMAT;
import static com.hackaton.util.DateUtil.getDateAsString;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hackaton.dao.FriendDAO;
import com.hackaton.dao.CommunicationDAO;
import com.hackaton.dao.UserDAO;
import com.hackaton.model.Account;
import com.hackaton.model.Friends;
import com.hackaton.model.Communications;
import com.hackaton.model.User;
import com.hackaton.repository.AccountRepo;
import com.hackaton.repository.CommunicationsRepo;
import com.hackaton.repository.UserRepo;
import com.hackaton.service.UserService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService, UserService  {

	@Autowired
	UserRepo userRepo;
	
	@Autowired
	CommunicationsRepo communicationRepo;
	
	@Autowired
	AccountRepo accountRepo;

	@Override
	public UserDetails loadUserByUsername(String username) 
			throws UsernameNotFoundException {
		User user = userRepo.findByUsername(username)
            	.orElseThrow(() -> 
                    new UsernameNotFoundException(
                    		"User Not Found with  username  " + username));
    return user;
	}


	@Override
	public UserDAO getUserDAO(User user) {
		UserDAO userDAO = new UserDAO();
		userDAO.setUserId(user.getUserId());
		userDAO.setUsername(user.getUsername());
		userDAO.setFirstName(user.getFirstName());
		userDAO.setLastName(user.getLastName());
		userDAO.setEmail(user.getEmail());
		userDAO.setPhone(user.getPhone());
		Boolean isAdmin = user.getUserLevels().stream().filter(level -> level.getLevel().getName().equals("admin"))
				.findAny().isPresent();
		userDAO.setIsAdmin(isAdmin);
		if (isAdmin) {
			List<Communications> communications = communicationRepo.findAll();
			List<CommunicationDAO> communicationDAOs = communications.stream().map(this::getCommunicationDAO)
					.collect(Collectors.toList());
			userDAO.setCommunications(communicationDAOs);
			
			userDAO.setTotalUsers(userRepo.count());			
			
			List<Account> accounts = accountRepo.findAll();
			
		} else {
			if (user.getAccount() != null) {
				userDAO.setAccountNumber(user.getAccount().getAccountNumber());
				List<CommunicationDAO> communications = user.getAccount().getCommunacations().stream()
						.map(this::getCommunicationDAO)
						.collect(Collectors.toList());
				userDAO.setCommunications(communications);
				
				
				List<FriendDAO> friends = user.getFriends().stream().map(this::getRecipientDAO)
						.collect(Collectors.toList());
				userDAO.setFriends(friends);
			}
		}
		return userDAO;
	}
	


	@Override
	public UserDAO getUserDAOByName(String userName) {
		UserDAO userDAO = null;
		Optional<User> user = userRepo.findByUsername(userName);
		if (user.isPresent()) {
			userDAO = getUserDAO(user.get());
		}
		return userDAO;
	}

	private CommunicationDAO getCommunicationDAO(Communications communication) {
		CommunicationDAO communicationDAO = new CommunicationDAO();
		communicationDAO.setId(communication.getId());
		communicationDAO.setMessage(communication.getMessage());
		communicationDAO.setIsDelivered(true);

		return communicationDAO;
	}

	@Override
	public List<UserDAO> getAllUsers() {
		List<User> users = userRepo.findAll();
		 return users.stream().map(this::transformUser)
		.collect(Collectors.toList());
	}

	private UserDAO transformUser(User user) {
		UserDAO userDAO = new UserDAO();
		userDAO.setUserId(user.getUserId());
		userDAO.setFirstName(user.getFirstName());
		userDAO.setLastName(user.getLastName());
		userDAO.setEmail(user.getEmail());
		return userDAO;
	}

	private FriendDAO getRecipientDAO(Friends friend) {
		FriendDAO friendDAO = new FriendDAO();
		friendDAO.setId(friend.getId());
		friendDAO.setName(friend.getName());
		friendDAO.setEmail(friend.getEmail());
		friendDAO.setPhone(friend.getPhone());
		
		return friendDAO;
	}

		

		@Override
		public List<FriendDAO> getFriends(String username) {
			// TODO Auto-generated method stub
			return null;
		}
	
	
	

	}
