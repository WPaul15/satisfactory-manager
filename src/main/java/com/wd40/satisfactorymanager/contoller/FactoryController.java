package com.wd40.satisfactorymanager.contoller;

import com.wd40.satisfactorymanager.model.Factory;
import com.wd40.satisfactorymanager.model.Recipe;
import com.wd40.satisfactorymanager.service.FactoryService;
import com.wd40.satisfactorymanager.service.RecipeService;
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
	private final RecipeService recipeService;

	@Autowired
	public FactoryController(FactoryService factoryService, RecipeService recipeService) {
		this.factoryService = factoryService;
		this.recipeService = recipeService;
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

	@GetMapping(path = "/recipe/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Recipe getRecipeById(@PathVariable("name") String name) {
		return recipeService.getRecipeByName(name);
	}
}
