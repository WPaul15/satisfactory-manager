package com.wd40.satisfactorymanager.service;

import com.wd40.satisfactorymanager.model.Factory;
import com.wd40.satisfactorymanager.repository.FactoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FactoryService {

	private final FactoryRepository factoryRepository;

	@Autowired
	public FactoryService(FactoryRepository factoryRepository) {
		this.factoryRepository = factoryRepository;
	}

	public Factory createNewFactory(String name) {
		return factoryRepository.save(new Factory(name));
	}
}
