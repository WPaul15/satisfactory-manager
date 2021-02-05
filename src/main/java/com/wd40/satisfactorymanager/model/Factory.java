package com.wd40.satisfactorymanager.model;

import java.util.HashMap;
import java.util.Map;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;

@Entity
public class Factory {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String name;

  @OneToMany(cascade = CascadeType.ALL)
  @MapKey(name = "key")
  private Map<String, MachineGroup> machineGroups;

  public Factory() {}

  public Factory(String name) {
    this.name = name;
    this.machineGroups = new HashMap<>();
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

  public Map<String, MachineGroup> getMachineGroups() {
    return machineGroups;
  }

  public void setMachineGroups(Map<String, MachineGroup> machineGroups) {
    this.machineGroups = machineGroups;
  }
}
