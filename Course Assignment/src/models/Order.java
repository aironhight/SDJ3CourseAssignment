package models;

public class Order {
	
	private int orderId;
	private String address;
	private String country;
	
	public Order(int orderId, String address, String country) {
		super();
		this.orderId = orderId;
		this.address = address;
		this.country = country;
	}
	
	public int getOrderId() {
		return orderId;
	}
	public String getAddress() {
		return address;
	}
	public String getCountry() {
		return country;
	}
	
}
