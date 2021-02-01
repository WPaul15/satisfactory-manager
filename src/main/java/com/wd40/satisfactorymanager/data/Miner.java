package com.wd40.satisfactorymanager.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = true)
public class Miner extends Machine {

  @JsonProperty("rate")
  int baseExtractionRate;

  @JsonCreator
  public Miner(
      @JsonProperty("name") String name,
      @JsonProperty("category") String category,
      @JsonProperty("power") int power,
      @JsonProperty("rate") int baseExtractionRate) {
    super(name, category, power);
    this.baseExtractionRate = baseExtractionRate;
  }
}
