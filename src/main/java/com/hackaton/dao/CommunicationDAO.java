package com.hackaton.dao;

import lombok.Data;


@Data
public class CommunicationDAO {
	private Long id;
	private String message;
	private Boolean isDelivered;
}
