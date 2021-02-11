package com.wd40.satisfactorymanager.controller;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.when;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.anyString;

import com.wd40.satisfactorymanager.contoller.FactoryController;
import com.wd40.satisfactorymanager.data.Quality;
import com.wd40.satisfactorymanager.dto.request.FactoryUpdateDto;
import com.wd40.satisfactorymanager.dto.request.change.ChangeOp;
import com.wd40.satisfactorymanager.dto.request.change.MachineGroupChange;
import com.wd40.satisfactorymanager.model.Factory;
import com.wd40.satisfactorymanager.model.MachineGroup;
import com.wd40.satisfactorymanager.service.FactoryService;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = {FactoryController.class})
@ActiveProfiles("test")
public class FactoryControllerTest {

  private final FactoryController factoryController;

  @MockBean private FactoryService factoryService;

  @Autowired
  public FactoryControllerTest(FactoryController factoryController) {
    this.factoryController = factoryController;
  }

  @BeforeEach
  void setup() {
    RestAssuredMockMvc.standaloneSetup(factoryController);
  }

  @Test
  void shouldReturnCreatedFactory() {
    String name = "Test Factory";
    Factory factory = new Factory(name);

    Mockito.when(factoryService.createNewFactory(name)).thenReturn(factory);

    when()
        .post("/factory/" + name)
        .then()
        .assertThat()
        .status(HttpStatus.CREATED)
        .body("name", is(name), "machineGroups", notNullValue());
  }

  @Test
  void shouldReturnFactoryById() {
    int id = 1;
    String name = "Test Factory";
    Factory factory = new Factory(name);

    Mockito.when(factoryService.getFactoryById(id)).thenReturn(factory);

    when()
        .get("/factory/" + id)
        .then()
        .assertThat()
        .status(HttpStatus.OK)
        .body("name", is(name), "machineGroups", notNullValue());
  }

  @Test
  void shouldUpdateFactory() {
    int id = 1;
    String name = "Test Factory";
    Map<String, MachineGroupChange> changes = new HashMap<>();

    MachineGroup machineGroup = new MachineGroup("m1", "irn", 2, 100, Quality.NORMAL);
    String key = machineGroup.getKey();

    changes.put(key, new MachineGroupChange("", machineGroup, ChangeOp.ADD));

    Factory updatedFactory = new Factory(name);
    updatedFactory.setMachineGroups(Map.of(key, machineGroup));

    Mockito.when(factoryService.updateFactory(anyInt(), anyString(), anyMap()))
        .thenReturn(updatedFactory);

    given()
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .body(new FactoryUpdateDto(name, changes))
        .when()
        .put("/factory/" + id)
        .then()
        .assertThat()
        .status(HttpStatus.OK)
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .body("name", is(name))
        .body("machineGroups", notNullValue())
        .body("machineGroups." + key, notNullValue());
  }
}
