package models;

import java.io.Serializable;
import java.util.ArrayList;

public class Car implements Serializable
{
	private double weight;
	private String make;
	private String model;
	private int year;
	private String VIN;
	private ArrayList<Part> parts;
	
	public Car(String VIN, String model, String make, int year, double weight, ArrayList<Part> parts) {
		this.weight = weight;
		this.make =  make;
		this.model = model;
		this.year = year;
		this.VIN = VIN;
		this.parts = parts;
	}

	public double getWeight() {
		return weight;
	}
	
	public String getMake() {
		return make;
	}

	public String getModel() {
		return model;
	}
	
	public int getYear() {
		return year;
	}

	public String getVIN() {
		return VIN;
	}

	public ArrayList<Part> disassemble() {
		return parts;
	}	
}
