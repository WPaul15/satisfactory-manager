package com.wd40.satisfactorymanager.data;

import java.util.Set;
import lombok.Value;

@Value
public class Recipe {

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
