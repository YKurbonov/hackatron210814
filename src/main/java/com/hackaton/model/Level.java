package com.hackaton.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Level {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long levelId;
	private String name;
	@OneToMany(mappedBy = "level", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<UserLevel> userLevels = new HashSet<>();
}
