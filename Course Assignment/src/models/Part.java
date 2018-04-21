package models;

import java.io.Serializable;

public class Part implements Serializable
{
	private double weight;
	private String carVIN;
	private String type;
	
	public Part (String type,String carVIN, double weight) {
		this.weight = weight;
		this.carVIN = carVIN;
		this.type = type;
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

	public void setCarVIN(String carVIN) {
		this.carVIN = carVIN;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
