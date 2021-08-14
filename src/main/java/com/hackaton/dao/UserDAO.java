package com.hackaton.dao;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class UserDAO {
	private Long userId;
	private String username;
	private String firstName;
	private String lastName;
	private String dob;
	private String email;
	private String phone;
	private Boolean isAdmin;
	private Long accountNumber;
	private List<CommunicationDAO> communications;
	private List<FriendDAO> friends;
	private Long totalUsers;
	
		
}

