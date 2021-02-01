package com.wd40.satisfactorymanager.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.wd40.satisfactorymanager.data.Machine;
import com.wd40.satisfactorymanager.data.Recipe;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = {StaticDataService.class})
@ActiveProfiles("test")
public class StaticDataServiceTest {

  private final StaticDataService staticDataService;

  @Autowired
  public StaticDataServiceTest(StaticDataService staticDataService) {
    this.staticDataService = staticDataService;
  }

  @Test
  void shouldLoadRecipeFile() {
    Map<String, Recipe> recipes = staticDataService.getRecipes();

    assertThat(recipes).isNotEmpty();
  }

  @Test
  void shouldLoadMachineFile() {
    Map<String, Machine> machines = staticDataService.getMachines();

    assertThat(machines).isNotEmpty();
  }
}
