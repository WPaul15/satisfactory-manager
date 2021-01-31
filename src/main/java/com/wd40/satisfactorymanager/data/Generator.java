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

  @JsonCreator
  Generator(
      @JsonProperty("key") String keySegment,
      @JsonProperty("name") String name,
      @JsonProperty("category") String category,
      @JsonProperty("power") int power,
      @JsonProperty("fuels") Set<Fuel> fuels) {
    super(keySegment, name, category, power);
    this.fuels = fuels;
  }

  @Value
  static class Fuel {

    String name;
    double burnTime;
  }
}
