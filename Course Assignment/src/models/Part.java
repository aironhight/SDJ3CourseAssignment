package models;

import java.io.Serializable;

public class Part implements Serializable
{
	private String type;
	private String carVIN;
	private double weight;
	private int id;
	
	public Part (String type, String carVIN, double weight) {
		this.weight = weight;
		this.carVIN = carVIN;
		this.type = type;
		id = -10;
	}
	
	public Part (String type, String carVIN, double weight, int id) {
		this.weight = weight;
		this.carVIN = carVIN;
		this.type = type;
		this.id = id;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public String getCarVIN() {
		return carVIN;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public int getID() {
		return id;
	}
	
	public void setID(int id) {
		this.id = id;
	}
	
}
