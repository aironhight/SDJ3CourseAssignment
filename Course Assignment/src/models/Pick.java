package models;

public class Pick {
	private int id;
	private int partId;
	private int orderId;
	
	public Pick(int id, int partId, int orderId) {
		this.id = id;
		this.partId = partId;
		this.orderId = orderId;		
	}

	public int getId() {
		return id;
	}

	public int getPartId() {
		return partId;
	}
	
	public int getOrderId() {
		return orderId;
	}
	
}
