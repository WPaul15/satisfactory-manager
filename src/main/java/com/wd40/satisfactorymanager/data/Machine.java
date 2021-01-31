package com.wd40.satisfactorymanager.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@JsonTypeInfo(use = Id.NAME, property = "type")
@JsonSubTypes({
  @Type(value = Miner.class, name = "Miner"),
  @Type(value = Generator.class, name = "Generator")
})
public class Machine {

  @JsonProperty("key")
  String keySegment;

  String name;
  String category;
  int power;
}
