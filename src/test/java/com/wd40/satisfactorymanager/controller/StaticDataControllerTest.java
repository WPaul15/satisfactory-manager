package com.wd40.satisfactorymanager.controller;

import static org.assertj.core.api.Assertions.assertThat;

import com.wd40.satisfactorymanager.contoller.StaticDataController;
import com.wd40.satisfactorymanager.data.Generator;
import com.wd40.satisfactorymanager.data.Generator.Fuel;
import com.wd40.satisfactorymanager.data.Machine;
import com.wd40.satisfactorymanager.data.Recipe;
import com.wd40.satisfactorymanager.data.Recipe.Ingredient;
import com.wd40.satisfactorymanager.service.StaticDataService;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = {StaticDataController.class})
@ActiveProfiles("test")
public class StaticDataControllerTest {

  private final StaticDataController staticDataController;

  @MockBean private StaticDataService staticDataService;

  @Autowired
  public StaticDataControllerTest(StaticDataController staticDataController) {
    this.staticDataController = staticDataController;
  }

  @Test
  void shouldReturnSetOfRecipes() {
    Set<Recipe> recipes = new HashSet<>();
    Set<Ingredient> inputs = new HashSet<>();
    Set<Ingredient> outputs = new HashSet<>();

    inputs.add(new Ingredient("Test Input", 30));
    outputs.add(new Ingredient("Test Output", 45));

    recipes.add(new Recipe("tst", "Test Recipe", "constructor", inputs, outputs));

    Mockito.when(staticDataService.getRecipes()).thenReturn(recipes);

    Set<Recipe> result = staticDataController.getRecipes();

    assertThat(result).isNotEmpty();
    assertThat(result.size()).isEqualTo(1);
    assertThat(result.containsAll(recipes)).isTrue();
  }

  @Test
  void shouldReturnSetOfMachines() {
    Map<String, Machine> machines = new HashMap<>();
    Set<Fuel> fuels = new HashSet<>();

    fuels.add(new Fuel("Test Fuel 1", 10));
    fuels.add(new Fuel("Test Fuel 2", 15.5));

    machines.put("tm1", new Machine("Test Machine 1", "production", -15));
    machines.put("tm2", new Generator("Test Machine 2", "generation", 200, fuels, 0));

    Mockito.when(staticDataController.getMachines()).thenReturn(machines);

    Map<String, Machine> result = staticDataController.getMachines();

    assertThat(result).isNotEmpty();
    assertThat(result.size()).isEqualTo(2);
    assertThat(result.keySet().containsAll(machines.keySet())).isTrue();
  }
}
