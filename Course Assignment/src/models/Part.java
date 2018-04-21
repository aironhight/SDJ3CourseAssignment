package models;

public class Part {
	private double weight;
	private int carVIN;
	private String type;
	
	public Part(double weight, int carVIN, String type) {
		this.weight = weight;
		this.carVIN = carVIN;
		this.type = type;
	}

	public double getWeight() {
		return weight;
	}

	public int getCarVIN() {
		return carVIN;
	}

	public String getType() {
		return type;
	}
	
}