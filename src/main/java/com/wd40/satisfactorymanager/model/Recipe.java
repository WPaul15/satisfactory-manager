package com.wd40.satisfactorymanager.model;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Recipe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;

	@OneToMany(cascade = CascadeType.ALL)
	private Set<Ingredient> inputs;

	@OneToMany(cascade = CascadeType.ALL)
	private Set<Ingredient> outputs;

	public Recipe() {}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Ingredient> getInputs() {
		return inputs;
	}

	public void setInputs(Set<Ingredient> inputs) {
		this.inputs = inputs;
	}

	public Set<Ingredient> getOutputs() {
		return outputs;
	}

	public void setOutputs(Set<Ingredient> outputs) {
		this.outputs = outputs;
	}
}
