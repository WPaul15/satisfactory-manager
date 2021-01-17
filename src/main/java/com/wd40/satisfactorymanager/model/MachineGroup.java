package com.wd40.satisfactorymanager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MachineGroup {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private int count;
  private int clockSpeed = 100;
  // TODO: Add recipes
  private String recipe;
  private String machineType;
  @JsonIgnore private String key;

  public MachineGroup() {}

  public MachineGroup(String machineType, int count, int clockSpeed, String recipe) {
    this.count = count;
    this.clockSpeed = clockSpeed;
    this.recipe = recipe;
    this.key = generateKey();
  }

  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }

  public int getClockSpeed() {
    return clockSpeed;
  }

  public void setClockSpeed(int clockSpeed) {
    this.clockSpeed = clockSpeed;
  }

  public void updateCount(int countChange) {
    this.count += countChange;
  }

  public String getMachineType() {
    return machineType;
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  private String generateKey() {
    return "test";
  }
}
