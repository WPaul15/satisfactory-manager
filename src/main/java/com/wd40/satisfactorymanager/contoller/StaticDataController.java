package com.wd40.satisfactorymanager.contoller;

import com.wd40.satisfactorymanager.data.Machine;
import com.wd40.satisfactorymanager.data.Recipe;
import com.wd40.satisfactorymanager.service.StaticDataService;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/data")
public class StaticDataController {

  private final StaticDataService staticDataService;

  @Autowired
  public StaticDataController(StaticDataService staticDataService) {
    this.staticDataService = staticDataService;
  }

  @GetMapping(path = "/recipes", produces = MediaType.APPLICATION_JSON_VALUE)
  public Set<Recipe> getRecipes() {
    return staticDataService.getRecipes();
  }

  @GetMapping(path = "/machines", produces = MediaType.APPLICATION_JSON_VALUE)
  public Set<Machine> getMachines() {
    return staticDataService.getMachines();
  }
}
