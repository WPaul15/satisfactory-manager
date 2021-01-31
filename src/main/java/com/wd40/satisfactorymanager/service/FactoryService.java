package com.wd40.satisfactorymanager.service;

import com.wd40.satisfactorymanager.model.Factory;
import com.wd40.satisfactorymanager.model.MachineGroup;
import com.wd40.satisfactorymanager.repository.FactoryRepository;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FactoryService {

  private final FactoryRepository factoryRepository;

  @Autowired
  public FactoryService(FactoryRepository factoryRepository) {
    this.factoryRepository = factoryRepository;
  }

  public Factory getFactoryById(Integer id) {
    return factoryRepository.findById(id).orElseThrow();
  }

  public Factory createNewFactory(String name) {
    Factory factory = new Factory(name);
    Map<String, MachineGroup> machines = new HashMap<>();
    machines.put("amh", new MachineGroup("Miner", 2, 100, "Iron Ore"));
    factory.setMachineGroups(machines);

    return factoryRepository.save(factory);
  }

  public Factory addMachine(
      Factory factory,
      String newMachineType,
      String newRecipe,
      int newClock,
      int count,
      String newQuality) {
    Map<String, MachineGroup> machines = factory.getMachineGroups();
    String newKey = getKey(newMachineType, newRecipe, newClock, newQuality);
    if (machines.containsKey(newKey)) {
      machines.get(newKey).updateCount(count);
    } else {
      machines.put(newKey, new MachineGroup(newMachineType, count, newClock, newRecipe));
    }
    return factory;
  }

  public Factory removeMachine(Factory factory, String machineKey, int count) {
    Map<String, MachineGroup> machines = factory.getMachineGroups();
    if (machines.get(machineKey).getCount() > count) {
      machines.get(machineKey).updateCount(-count);
    } else if (machines.get(machineKey).getCount() <= count) {
      machines.remove(machineKey);
    }
    return factory;
  }

  public Factory editMachine(
      Factory factory, String machineKey, String newRecipe, int newClock, String newQuality) {
    Map<String, MachineGroup> machines = factory.getMachineGroups();
    String machineType = machines.get(machineKey).getMachineType();
    int count = machines.get(machineKey).getCount();
    removeMachine(factory, machineKey, count);
    addMachine(factory, machineType, newRecipe, newClock, count, newQuality);
    return factory;
  }

  public String getKey(String machineType, String recipe, int clock, String quality) {
    // get machine key
    // get recipe key

    String machineKey = "";
    String recipeKey = "";

    String clockKey = String.format("%03d", clock);

    String qualityKey = "";
    switch (quality) {
      case "Impure":
        qualityKey = "i";
        break;
      case "Normal":
        qualityKey = "n";
        break;
      case "Pure":
        qualityKey = "p";
        break;
    }

    return machineKey + qualityKey + recipeKey + clockKey;
  }
}
