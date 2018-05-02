package models;

public class OrderPart {

	private int orderID;
	private String partType;
	private String carMake;
	private String carModel;
	private int carYear;
	
	public OrderPart(String partType, String carMake, String carModel, int carYear) {
		this.partType = partType;
		this.carMake = carMake;
		this.carModel = carModel;
		this.carYear = carYear;
		orderID = -10;
	}
	
	public OrderPart(String partType, String carMake, String carModel, int carYear, int orderID) {
		this.partType = partType;
		this.carMake = carMake;
		this.carModel = carModel;
		this.carYear = carYear;
		this.orderID = orderID;
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
	
	public int getID() {
		return orderID;
	}

}
