package com.wd40.satisfactorymanager.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wd40.satisfactorymanager.model.Recipe;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataFileConfig {

	private static final Logger LOG = LoggerFactory.getLogger(DataFileConfig.class);

	@Value("${data.recipes-file}")
	private String recipesFilePath;

	private final ObjectMapper objectMapper = new ObjectMapper();
	private List<Recipe> recipes;

	@PostConstruct
	private void readRecipeFile() throws IOException {
		recipes = objectMapper.readValue(new URL(recipesFilePath), new TypeReference<>() {});

		LOG.info("{}", new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(recipes));
	}

	@Bean("recipes")
	public List<Recipe> getRecipes() {
		return recipes;
	}
}
