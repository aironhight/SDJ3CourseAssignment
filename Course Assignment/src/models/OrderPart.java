package models;

public class OrderPart {
	
	private String partType;
	private String carMake;
	private String carModel;
	private int carYear;
	private int quantity;
	
	public OrderPart(String partType, String carMake, String carModel, int carYear, int quantity) {
		super();
		this.partType = partType;
		this.carMake = carMake;
		this.carModel = carModel;
		this.carYear = carYear;
		this.quantity = quantity;
	}
	
	public String getPartType() {
		return partType;
	}
	public String getCarMake() {
		return carMake;
	}
	public String getCarModel() {
		return carModel;
	}
	public int getCarYear() {
		return carYear;
	}
	public int getQuantity() {
		return quantity;
	}

}
