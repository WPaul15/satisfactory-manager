package com.wd40.satisfactorymanager.contoller;

import com.wd40.satisfactorymanager.model.Factory;
import com.wd40.satisfactorymanager.service.FactoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FactoryController {

	private final FactoryService factoryService;

	@Autowired
	public FactoryController(FactoryService factoryService) {
		this.factoryService = factoryService;
	}

	@PostMapping(
		path = "/factory/{name}",
		produces = MediaType.APPLICATION_JSON_VALUE
	)
	public Factory createNewFactory(@PathVariable("name") String name) {
		return factoryService.createNewFactory(name);
	}
}
