package com.wd40.satisfactorymanager.dto.request;

import com.wd40.satisfactorymanager.dto.request.change.MachineGroupChange;
import java.util.Map;
import lombok.Value;

@Value
public class FactoryUpdateDto {

  String name;
  Map<String, MachineGroupChange> changes;
}
