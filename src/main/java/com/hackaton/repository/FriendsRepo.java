package com.hackaton.repository;

import org.springframework.data.repository.CrudRepository;

import com.hackaton.model.Friends;

public interface FriendsRepo extends CrudRepository<Friends, Long>{

}
