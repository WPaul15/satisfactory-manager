package com.wd40.satisfactorymanager.service;

import com.wd40.satisfactorymanager.dto.request.change.MachineGroupChange;
import com.wd40.satisfactorymanager.model.Factory;
import com.wd40.satisfactorymanager.model.MachineGroup;
import com.wd40.satisfactorymanager.repository.FactoryRepository;
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
    return factoryRepository.save(new Factory(name));
  }

  public Factory updateFactory(Integer id, String name, Map<String, MachineGroupChange> changes) {
    if (name == null && (changes == null || changes.isEmpty())) {
      return getFactoryById(id);
    }

    Factory factory = factoryRepository.getOne(id);

    if (name != null && !factory.getName().equals(name)) {
      factory.setName(name);
    }

    if (changes != null && !changes.isEmpty()) {
      updateMachineGroups(factory, changes);
    }

    return factoryRepository.save(factory);
  }

  private void updateMachineGroups(Factory factory, Map<String, MachineGroupChange> changes) {
    Map<String, MachineGroup> machineGroups = factory.getMachineGroups();

    changes.forEach(
        (key, change) -> {
          switch (change.getChangeOp()) {
            case ADD:
            case UPDATE:
              machineGroups.put(key, change.getMachineGroup());
              break;
            case DELETE:
              machineGroups.remove(key);
              break;
          }
        });
  }
}
