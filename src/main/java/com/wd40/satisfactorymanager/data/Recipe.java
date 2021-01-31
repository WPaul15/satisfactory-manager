package com.wd40.satisfactorymanager.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Set;
import lombok.Value;

@Value
public class Recipe {

  @JsonProperty("key")
  String keySegment;

  String name;
  String machineType;
  Set<Ingredient> inputs;
  Set<Ingredient> outputs;

  @Value
  public static class Ingredient {

    String item;
    int amount;
  }
}
