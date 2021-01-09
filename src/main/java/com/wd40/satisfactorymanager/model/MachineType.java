package com.wd40.satisfactorymanager.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum MachineType {
	NONE("none"),
	MINER("miner"),
	WATER_EXTRACTOR("waterExtractor"),
	OIL_EXTRACTOR("oilExtractor"),
	SMELTER("smelter"),
	FOUNDRY("foundry"),
	CONSTRUCTOR("constructor"),
	ASSEMBLER("assembler"),
	MANUFACTURER("manufacturer"),
	REFINERY("refinery");

	private final String name;

	MachineType(String name) {
		this.name = name;
	}

	@JsonValue
	@Override
	public String toString() {
		return name;
	}
}
