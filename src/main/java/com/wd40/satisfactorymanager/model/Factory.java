package com.wd40.satisfactorymanager.model;

import java.util.HashMap;
import java.util.Map;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Factory {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String name;

  @ElementCollection private Map<String, MachineGroup> machines;

  public Factory() {}

  public Factory(String name) {
    this.name = name;
    this.machines = new HashMap<>();
  }

  public Integer getId() {
    return id;
  }

  private void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Map<String, MachineGroup> getMachines() {
    return machines;
  }

  public void setMachines(Map<String, MachineGroup> machines) {
    this.machines = machines;
  }
}
