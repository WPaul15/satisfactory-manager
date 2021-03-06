package com.wd40.satisfactorymanager.data;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Quality {
  IMPURE("i"),
  NORMAL("n"),
  PURE("p"),
  NONE("");

  private final String key;
}
