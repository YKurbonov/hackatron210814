package com.hackaton.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.hackaton.model.Level;

public interface LevelRepo extends CrudRepository<Level, Integer> {

	Optional<Level> findByName(String name);
}
