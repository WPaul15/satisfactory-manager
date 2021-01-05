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

	public Factory getFactoryById(Integer id) {
		return factoryRepository.findById(id).orElseThrow();
	}

	public Factory createNewFactory(String name) {
		return factoryRepository.save(new Factory(name));
	}

	public void addMachine(String new_machine_type, String new_recipe, int new_clock, int count, String new_quality) {
		String new_key = generate_key(new_machine_type, new_recipe, new_clock, new_quality);
	}

	public String generate_key(String machine_type, String recipe, int clock, String quality) {
		// get machine key
		// get recipe key

		String machine_key = "";
		String recipe_key = "";

		String clock_key = String.format("%03d", clock);

		String quality_key = "";
		switch (quality) {
			case "Impure":
				quality_key = "i";
				break;
			case "Normal":
				quality_key = "n";
				break;
			case "Pure":
				quality_key = "p";
				break;
		}

		return machine_key + quality_key + recipe_key + clock_key;
	}
}
