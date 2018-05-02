package models;

public class OrderPart {

	private int orderID;
	private String partType;
	private String carMake;
	private String carModel;
	private int carYear;
	private int quantity;
	private String receiverAddress;
	private String receiverCountry;
	private String receiverName;
	
	public OrderPart(String partType, String carMake, String carModel, int carYear, 
			int quantity, String receiverAddress, String receiverCountry, String receiverName) {
		this.partType = partType;
		this.carMake = carMake;
		this.carModel = carModel;
		this.carYear = carYear;
		this.quantity = quantity;
		this.receiverAddress = receiverAddress;
		this.receiverCountry = receiverCountry;
		this.receiverName = receiverName;
		orderID = -10;
	}
	
	public OrderPart(String partType, String carMake, String carModel, int carYear, 
			int quantity, String receiverAddress, String receiverCountry, String receiverName, int orderID) {
		this.partType = partType;
		this.carMake = carMake;
		this.carModel = carModel;
		this.carYear = carYear;
		this.quantity = quantity;
		this.receiverAddress = receiverAddress;
		this.receiverCountry = receiverCountry;
		this.receiverName = receiverName;
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
	public int getQuantity() {
		return quantity;
	}
	
	public int getID() {
		return orderID;
	}

}
