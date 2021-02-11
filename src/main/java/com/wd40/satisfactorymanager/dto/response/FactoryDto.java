package com.wd40.satisfactorymanager.dto.response;

import com.wd40.satisfactorymanager.model.MachineGroup;
import java.util.Map;
import lombok.Data;

@Data
public class FactoryDto {

  private int id;
  private String name;
  private Map<String, MachineGroup> machineGroups;
}
