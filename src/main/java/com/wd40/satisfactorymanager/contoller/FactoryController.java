package com.wd40.satisfactorymanager.contoller;

import com.wd40.satisfactorymanager.model.Factory;
import com.wd40.satisfactorymanager.service.FactoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/factory")
public class FactoryController {

	private final FactoryService factoryService;

	@Autowired
	public FactoryController(FactoryService factoryService) {
		this.factoryService = factoryService;
	}

	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Factory getFactoryById(@PathVariable Integer id) {
		return factoryService.getFactoryById(id);
	}

	@PostMapping(path = "/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public Factory createNewFactory(@PathVariable("name") String name) {
		return factoryService.createNewFactory(name);
	}

	@PostMapping(path = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
	public Factory addMachine() {
		return factoryService.addMachine(
			new Factory("newFactory"),
			"Smelter",
			"Iron Ingot",
			100,
			2,
			""
		);
	}

	@PostMapping(path = "/remove", produces = MediaType.APPLICATION_JSON_VALUE)
	public Factory removeMachine() {
		Factory removeTestFactory = new Factory("removeTestFactory");
		factoryService.addMachine(removeTestFactory, "Smelter", "Iron Ingot", 100, 5, "");

		return factoryService.removeMachine(removeTestFactory, "100", 10);
	}
}
