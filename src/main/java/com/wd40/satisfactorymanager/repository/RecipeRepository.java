package com.wd40.satisfactorymanager.repository;

import com.wd40.satisfactorymanager.model.Recipe;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipe, Integer> {
	Optional<Recipe> findByName(String name);
}
