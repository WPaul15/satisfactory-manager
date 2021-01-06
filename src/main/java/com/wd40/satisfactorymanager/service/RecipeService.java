package com.wd40.satisfactorymanager.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wd40.satisfactorymanager.model.Recipe;
import com.wd40.satisfactorymanager.repository.RecipeRepository;
import java.io.IOException;
import java.net.URL;
import java.util.Set;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RecipeService {

	private static final Logger LOG = LoggerFactory.getLogger(RecipeService.class);

	@Value("${data.recipes-file}")
	private String recipesFilePath;

	private final RecipeRepository recipeRepository;

	private final ObjectMapper objectMapper = new ObjectMapper();

	@Autowired
	public RecipeService(RecipeRepository recipeRepository) {
		this.recipeRepository = recipeRepository;
	}

	@PostConstruct
	private void readRecipeFile() throws IOException {
		LOG.info("Reading recipe file '{}'", recipesFilePath);

		Set<Recipe> recipes = objectMapper.readValue(
			new URL(recipesFilePath),
			new TypeReference<>() {}
		);
		recipeRepository.saveAll(recipes);
	}

	public Recipe getRecipeById(Integer id) {
		return recipeRepository.findById(id).orElseThrow();
	}

	public Recipe getRecipeByName(String name) {
		return recipeRepository.findByName(name).orElseThrow();
	}
}
