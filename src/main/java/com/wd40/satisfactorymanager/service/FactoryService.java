package com.wd40.satisfactorymanager.service;

import com.wd40.satisfactorymanager.dto.change.MachineGroupChange;
import com.wd40.satisfactorymanager.model.Factory;
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
    Factory factory = factoryRepository.getOne(id);

    if (!factory.getName().equals(name)) {
      factory.setName(name);
    }

    factory.updateMachineGroups(changes);

    return factoryRepository.save(factory);
  }
}
