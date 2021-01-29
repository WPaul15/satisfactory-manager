package com.wd40.satisfactorymanager.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wd40.satisfactorymanager.data.Recipe;
import java.io.IOException;
import java.net.URL;
import java.util.Set;
import javax.annotation.PostConstruct;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class StaticDataService {

  @Value("${data.recipes-file}")
  private String recipesFilePath;

  @Value("${data.alt-recipes-file}")
  private String altRecipesFilePath;

  @Getter private Set<Recipe> recipes;

  private final ObjectMapper objectMapper = new ObjectMapper();

  @Autowired
  public StaticDataService() {
    this.objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  }

  @PostConstruct
  private void loadStaticData() throws IOException {
    LOG.debug("Reading recipe file '{}'", recipesFilePath);

    recipes = objectMapper.readValue(new URL(recipesFilePath), new TypeReference<>() {});

    LOG.debug("Reading alternative recipes file '{}'", altRecipesFilePath);

    recipes.addAll(objectMapper.readValue(new URL(altRecipesFilePath), new TypeReference<>() {}));
  }
}
