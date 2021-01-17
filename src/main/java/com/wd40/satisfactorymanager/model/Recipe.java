package com.wd40.satisfactorymanager.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Recipe {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @JsonProperty("key")
  private String keySegment;

  private String name;
  private final MachineType machineType = MachineType.NONE;

  @OneToMany(cascade = CascadeType.ALL)
  private Set<Ingredient> inputs;

  @OneToMany(cascade = CascadeType.ALL)
  private Set<Ingredient> outputs;

  public Recipe() {}

  public Integer getId() {
    return id;
  }

  public String getKeySegment() {
    return keySegment;
  }

  public String getName() {
    return name;
  }

  public MachineType getMachineType() {
    return machineType;
  }

  public Set<Ingredient> getInputs() {
    return inputs;
  }

  public Set<Ingredient> getOutputs() {
    return outputs;
  }
}
