package com.wd40.satisfactorymanager.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Set;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = true)
public class Generator extends Machine {

  Set<Fuel> fuels;
  int waterUsage;

  @JsonCreator
  public Generator(
      @JsonProperty("name") String name,
      @JsonProperty("category") String category,
      @JsonProperty("power") int power,
      @JsonProperty("fuels") Set<Fuel> fuels,
      @JsonProperty("waterUsage") int waterUsage) {
    super(name, category, power);
    this.fuels = fuels;
    this.waterUsage = waterUsage;
  }

  @Value
  public static class Fuel {

    String name;
    double burnTime;
  }
}
