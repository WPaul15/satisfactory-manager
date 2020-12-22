package com.wd40.satisfactorymanager.model;

public class Machine {

	private int count;
	private double clockSpeed = 1.0;
	// TODO: Add recipes
	private String recipe;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public double getClockSpeed() {
		return clockSpeed;
	}

	public void setClockSpeed(double clockSpeed) {
		this.clockSpeed = clockSpeed;
	}
}
