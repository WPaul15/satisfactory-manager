package com.wd40.satisfactorymanager.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.wd40.satisfactorymanager.data.Machine;
import com.wd40.satisfactorymanager.data.Recipe;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {StaticDataService.class})
public class StaticDataServiceTest {

  private final StaticDataService staticDataService;

  @Autowired
  public StaticDataServiceTest(StaticDataService staticDataService) {
    this.staticDataService = staticDataService;
  }

  @Test
  void shouldLoadRecipeFile() {
    Set<Recipe> recipes = staticDataService.getRecipes();

    assertThat(recipes).isNotEmpty();
  }

  @Test
  void shouldLoadMachineFile() {
    Set<Machine> machines = staticDataService.getMachines();

    assertThat(machines).isNotEmpty();
  }
}