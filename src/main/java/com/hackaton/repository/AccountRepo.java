package com.hackaton.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.hackaton.model.Account;

public interface AccountRepo extends CrudRepository <Account, Long>{
	
	Account findByAccountNumber(Long accountNumber);
	
	List<Account> findAll();

}
