package models;

public class Order {
	
	private int orderId;
	private String name;
	private String address;
	private String country;
	
	public Order(int orderId, String address, String country, String name) {
		
		this.orderId = orderId;
		this.address = address;
		this.country = country;
		this.name = name;
	}
	
	public Order(String address, String country, String name)
	{
		this.address = address;
		this.country = country;
		this.name = name;
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
	public String getName() {
		return name;
	}
	
}
