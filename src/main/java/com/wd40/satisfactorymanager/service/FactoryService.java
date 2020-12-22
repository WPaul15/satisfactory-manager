package com.wd40.satisfactorymanager.service;

import com.wd40.satisfactorymanager.model.Factory;
import org.springframework.stereotype.Service;

@Service
public class FactoryService {

	public Factory createNewFactory(String name) {
		return new Factory(name);
	}
}
