package com.hackaton.dao;

import org.springframework.data.repository.CrudRepository;

import com.hackaton.model.Account;

public interface AccountDAO extends CrudRepository<Account, Long> {
	Account findByAccountNumber(Long accountNumber);
}
