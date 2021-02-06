package com.wd40.satisfactorymanager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wd40.satisfactorymanager.data.Quality;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class MachineGroup {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Setter(AccessLevel.NONE)
  private Integer id;

  private String machineKey;
  private String recipeKey;
  private int count;
  private int clockSpeed = 100;
  private Quality quality;

  @JsonIgnore
  private String key;

  public MachineGroup(
      String machineKey, String recipeKey, int count, int clockSpeed, Quality quality) {
    this.machineKey = machineKey;
    this.recipeKey = recipeKey;
    this.count = count;
    this.clockSpeed = clockSpeed;
    this.quality = quality;
    this.key = getKey();
  }

  public void updateCount(int countChange) {
    this.count += countChange;
  }

  public String getKey() {
    return machineKey + quality.getKey() + recipeKey + String.format("%03d", clockSpeed);
  }
}
