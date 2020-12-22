package com.wd40.satisfactorymanager.model;

import java.util.ArrayList;
import java.util.List;

public class Factory {

	private String name;
	private List<Machine> machines;

	public Factory(String name) {
		this.name = name;
		this.machines = new ArrayList<>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Machine> getMachines() {
		return machines;
	}

	public void setMachines(List<Machine> machines) {
		this.machines = machines;
	}
}
