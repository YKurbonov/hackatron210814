package com.hackaton.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hackaton.model.Communications;
import com.hackaton.repository.CommunicationsRepo;
import com.hackaton.service.CommunicationService;


@Service
public class CommunicationServiceImpl implements CommunicationService {
	@Autowired
	CommunicationsRepo communicationRepo;
	
	@Override
	public void saveCommunication(Communications communication) {
		communicationRepo.save(communication);
		
	}
}
