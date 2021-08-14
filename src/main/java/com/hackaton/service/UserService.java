package com.hackaton.service;

import java.util.List;

import com.hackaton.dao.FriendDAO;
import com.hackaton.dao.UserDAO;
import com.hackaton.model.User;

public interface UserService {
	
	UserDAO getUserDAO(User user);
	
	UserDAO getUserDAOByName(String userName);
	
	List<UserDAO> getAllUsers();
	
	List<FriendDAO> getFriends(String username);
}
