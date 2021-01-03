package com.wd40.satisfactorymanager.repository;

import com.wd40.satisfactorymanager.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipesRepository extends JpaRepository<Recipe, Integer> {}
