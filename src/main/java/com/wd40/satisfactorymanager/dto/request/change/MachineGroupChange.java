package com.wd40.satisfactorymanager.dto.request.change;

import com.wd40.satisfactorymanager.model.MachineGroup;
import lombok.Value;

@Value
public class MachineGroupChange {

  MachineGroup machineGroup;
  ChangeOp changeOp;
}
