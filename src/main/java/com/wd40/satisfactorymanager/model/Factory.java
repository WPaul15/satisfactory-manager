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
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Factory {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Setter(AccessLevel.NONE)
  private Integer id;

  private String name;

  @OneToMany(cascade = CascadeType.ALL)
  @MapKey(name = "key")
  private Map<String, MachineGroup> machineGroups;

  public Factory(String name) {
    this.name = name;
    this.machineGroups = new HashMap<>();
  }
}
