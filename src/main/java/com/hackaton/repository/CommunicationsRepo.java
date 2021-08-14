package com.hackaton.repository;


import java.util.List;
import org.springframework.data.repository.CrudRepository;
import com.hackaton.model.Communications;

public interface CommunicationsRepo extends CrudRepository<Communications, Long> {

	List<Communications> findAll();
}
