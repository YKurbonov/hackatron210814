package com.hackaton.response;

import java.util.List;

import com.hackaton.dao.FriendDAO;

import lombok.Data;

@Data
public class RecipientResponse {
	boolean isSuccess;
	String message;
	List<FriendDAO> recipients;
}
