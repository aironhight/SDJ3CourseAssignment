package models;

import java.io.Serializable;

public class Part implements Serializable
{
	private double weight;
	private int carID;
	private String type;
	
	public Part (String type,int carID, double weight) {
		this.weight = weight;
		this.carID = carID;
		this.type = type;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public int getCarVIN() {
		return carID;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
