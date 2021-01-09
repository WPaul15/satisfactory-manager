package com.wd40.satisfactorymanager.repository;

import com.wd40.satisfactorymanager.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {}
