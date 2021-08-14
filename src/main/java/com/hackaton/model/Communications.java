package com.hackaton.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class Communications {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String message;
	private Boolean isDelivered;
	
	@ManyToOne
	@JoinColumn(name = "account_id")
	private Account account;

	public Communications() {

	}

	public Communications(String message, Boolean isDelivered , Account account) {
		super();
		this.message = message;
		this.isDelivered = isDelivered;
		this.account = account;
	}

}
