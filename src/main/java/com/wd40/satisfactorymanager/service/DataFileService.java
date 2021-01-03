package com.wd40.satisfactorymanager.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wd40.satisfactorymanager.model.Recipe;
import com.wd40.satisfactorymanager.repository.RecipesRepository;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class DataFileService {

	private static final Logger LOG = LoggerFactory.getLogger(DataFileService.class);

	@Value("${data.recipes-file}")
	private String recipesFilePath;

	private final RecipesRepository recipesRepository;

	private final ObjectMapper objectMapper = new ObjectMapper();

	@Autowired
	public DataFileService(RecipesRepository recipesRepository) {
		this.recipesRepository = recipesRepository;
	}

	@PostConstruct
	private void readRecipeFile() throws IOException {
		LOG.info("Reading recipe file '{}'", recipesFilePath);

		List<Recipe> recipes = objectMapper.readValue(
			new URL(recipesFilePath),
			new TypeReference<>() {}
		);
		recipesRepository.saveAll(recipes);

		LOG.info("{}", new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(recipes));
	}

	@Bean("recipes")
	public List<Recipe> getRecipes() {
		return recipes;
	}
}
