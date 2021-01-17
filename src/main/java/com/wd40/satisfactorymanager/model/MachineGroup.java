package com.wd40.satisfactorymanager.model;

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
  private double clockSpeed = 1.0;
  // TODO: Add recipes
  private String recipe;
  private String machineType;

  public MachineGroup() {}

  public MachineGroup(String machineType, int count, double clockSpeed, String recipe) {
    this.count = count;
    this.clockSpeed = clockSpeed;
    this.recipe = recipe;
  }

  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }

  public double getClockSpeed() {
    return clockSpeed;
  }

  public void setClockSpeed(double clockSpeed) {
    this.clockSpeed = clockSpeed;
  }

  public void updateCount(int countChange) {
    this.count += countChange;
  }

  public String getMachineType() {
    return machineType;
  }
}
