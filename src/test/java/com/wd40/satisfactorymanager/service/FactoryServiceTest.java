package com.wd40.satisfactorymanager.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;

import com.wd40.satisfactorymanager.data.Quality;
import com.wd40.satisfactorymanager.dto.request.change.ChangeOp;
import com.wd40.satisfactorymanager.dto.request.change.MachineGroupChange;
import com.wd40.satisfactorymanager.model.Factory;
import com.wd40.satisfactorymanager.model.MachineGroup;
import com.wd40.satisfactorymanager.repository.FactoryRepository;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = {FactoryService.class})
@ActiveProfiles("test")
public class FactoryServiceTest {

  private final FactoryService factoryService;
  @MockBean private FactoryRepository factoryRepository;

  @Autowired
  public FactoryServiceTest(FactoryService factoryService) {
    this.factoryService = factoryService;
  }

  @Test
  void shouldReturnFactoryWithValidId() {
    int id = 1;
    String name = "Test Factory";
    Factory factory = new Factory(name);

    Mockito.when(factoryRepository.findById(id)).thenReturn(Optional.of(factory));

    Factory result = factoryService.getFactoryById(id);

    assertThat(result).isNotNull();
    assertThat(result.getName()).isEqualTo(name);
    assertThat(result.getMachineGroups()).isEmpty();
  }

  @Test
  void shouldThrowExceptionWhenFindingWithInvalidId() {
    int id = -1;

    Mockito.when(factoryRepository.findById(id)).thenReturn(Optional.empty());

    assertThatThrownBy(() -> factoryService.getFactoryById(id))
        .isInstanceOf(NoSuchElementException.class);
  }

  @Test
  void shouldCreateNewFactory() {
    String name = "Test Factory";
    Factory factory = new Factory(name);

    Mockito.when(factoryRepository.save(any(Factory.class))).thenReturn(factory);

    Factory result = factoryService.createNewFactory(name);

    assertThat(result).isNotNull();
    assertThat(result.getName()).isEqualTo(name);
    assertThat(result.getMachineGroups()).isEmpty();
  }

  @Test
  void shouldNotFailUpdateWhenNoChangesPresent() {
    int id = 1;
    String name = "Test Factory";
    Factory factory = new Factory(name);

    Mockito.when(factoryRepository.findById(id)).thenReturn(Optional.of(factory));

    Factory result = factoryService.updateFactory(id, null, null);

    assertThat(result).isNotNull();
    assertThat(result.getName()).isEqualTo(name);
    assertThat(result.getMachineGroups()).isEmpty();
  }

  @Test
  void shouldAddMachineGroupToFactory() {
    int id = 1;
    String name = "Test Factory";
    MachineGroup machineGroup = new MachineGroup("m1", "irn", 1, 100, Quality.NORMAL);
    String key = machineGroup.getKey();

    Factory oldFactory = new Factory(name);
    Factory newFactory = new Factory(name);
    newFactory.setMachineGroups(Map.of(key, machineGroup));

    Mockito.when(factoryRepository.getOne(id)).thenReturn(oldFactory);
    Mockito.when(factoryRepository.save(any(Factory.class))).thenReturn(newFactory);

    Factory result =
        factoryService.updateFactory(
            id, name, Map.of(key, new MachineGroupChange("", machineGroup, ChangeOp.ADD)));

    assertThat(result).isNotNull();

    Map<String, MachineGroup> resultMachineGroups = result.getMachineGroups();

    assertThat(resultMachineGroups.size()).isEqualTo(1);
    assertThat(resultMachineGroups.containsKey(key)).isTrue();
  }

  @Test
  void shouldUpdateFactoryName() {
    int id = 1;
    String oldName = "Test Factory";
    String newName = "Test Factory 2";

    Factory oldFactory = new Factory(oldName);
    Factory newFactory = new Factory(newName);

    Mockito.when(factoryRepository.getOne(id)).thenReturn(oldFactory);
    Mockito.when(factoryRepository.save(any(Factory.class))).thenReturn(newFactory);

    Factory result = factoryService.updateFactory(id, newName, new HashMap<>());

    assertThat(result).isNotNull();
    assertThat(result.getName()).isEqualTo(newName);
    assertThat(result.getMachineGroups()).isEmpty();
  }

  @Test
  void shouldUpdateExistingMachineGroupRecipeInFactory() {
    int id = 1;
    String name = "Test Factory";
    String newRecipe = "cpr";

    MachineGroup oldMachineGroup = new MachineGroup("m1", "irn", 1, 100, Quality.NORMAL);
    MachineGroup newMachineGroup = new MachineGroup("m1", newRecipe, 1, 100, Quality.NORMAL);

    String oldKey = oldMachineGroup.getKey();
    String newKey = newMachineGroup.getKey();

    Map<String, MachineGroup> machineGroups = new HashMap<>();
    machineGroups.put(oldKey, oldMachineGroup);

    Factory oldFactory = new Factory(name);
    oldFactory.setMachineGroups(machineGroups);

    Factory newFactory = new Factory(name);
    newFactory.setMachineGroups(Map.of(newKey, newMachineGroup));

    Mockito.when(factoryRepository.getOne(id)).thenReturn(oldFactory);
    Mockito.when(factoryRepository.save(any(Factory.class))).thenReturn(newFactory);

    Factory result =
        factoryService.updateFactory(
            id,
            name,
            Map.of(newKey, new MachineGroupChange(oldKey, newMachineGroup, ChangeOp.UPDATE)));

    assertThat(result).isNotNull();

    Map<String, MachineGroup> resultMachineGroups = result.getMachineGroups();

    assertThat(resultMachineGroups.size()).isEqualTo(1);
    assertThat(resultMachineGroups.containsKey(oldKey)).isFalse();
    assertThat(resultMachineGroups.containsKey(newKey)).isTrue();
    assertThat(resultMachineGroups.get(newKey).getRecipeKey()).isEqualTo(newRecipe);
  }

  @Test
  void shouldUpdateExistingMachineGroupCountInFactory() {
    int id = 1;
    String name = "Test Factory";
    int newCount = 4;

    MachineGroup oldMachineGroup = new MachineGroup("m1", "irn", 1, 100, Quality.NORMAL);
    MachineGroup newMachineGroup = new MachineGroup("m1", "irn", newCount, 100, Quality.NORMAL);

    String oldKey = oldMachineGroup.getKey();
    String newKey = newMachineGroup.getKey();

    Map<String, MachineGroup> machineGroups = new HashMap<>();
    machineGroups.put(oldKey, oldMachineGroup);

    Factory oldFactory = new Factory(name);
    oldFactory.setMachineGroups(machineGroups);

    Factory newFactory = new Factory(name);
    newFactory.setMachineGroups(Map.of(newKey, newMachineGroup));

    Mockito.when(factoryRepository.getOne(id)).thenReturn(oldFactory);
    Mockito.when(factoryRepository.save(any(Factory.class))).thenReturn(newFactory);

    Factory result =
        factoryService.updateFactory(
            id,
            name,
            Map.of(newKey, new MachineGroupChange(oldKey, newMachineGroup, ChangeOp.UPDATE)));

    assertThat(result).isNotNull();
    assertThat(oldKey).isEqualTo(newKey);

    Map<String, MachineGroup> resultMachineGroups = result.getMachineGroups();

    assertThat(resultMachineGroups.size()).isEqualTo(1);
    assertThat(resultMachineGroups.containsKey(newKey)).isTrue();
    assertThat(resultMachineGroups.get(newKey).getCount()).isEqualTo(newCount);
  }

  @Test
  void shouldDeleteExistingMachineGroupFromFactory() {
    int id = 1;
    String name = "Test Factory";

    MachineGroup machineGroup = new MachineGroup("con", "irp", 1, 100);
    String key = machineGroup.getKey();

    Map<String, MachineGroup> machineGroups = new HashMap<>();
    machineGroups.put(key, machineGroup);

    Factory oldFactory = new Factory(name);
    oldFactory.setMachineGroups(machineGroups);
    Factory newFactory = new Factory(name);

    Mockito.when(factoryRepository.getOne(id)).thenReturn(oldFactory);
    Mockito.when(factoryRepository.save(any(Factory.class))).thenReturn(newFactory);

    Factory result =
        factoryService.updateFactory(
            id, name, Map.of(key, new MachineGroupChange(key, machineGroup, ChangeOp.DELETE)));

    assertThat(result).isNotNull();
    assertThat(result.getMachineGroups()).isEmpty();
  }

  @Test
  void shouldNotThrowExceptionWhenDeletingNonExistentMachineGroup() {
    int id = 1;
    String name = "Test Factory";

    MachineGroup machineGroup = new MachineGroup("con", "irp", 1, 100);
    String key = machineGroup.getKey();

    Factory factory = new Factory(name);

    Mockito.when(factoryRepository.getOne(id)).thenReturn(factory);
    Mockito.when(factoryRepository.save(any(Factory.class))).thenReturn(factory);

    assertThatNoException()
        .isThrownBy(
            () ->
                factoryService.updateFactory(
                    id,
                    name,
                    Map.of(key, new MachineGroupChange("", machineGroup, ChangeOp.DELETE))));
  }
}
