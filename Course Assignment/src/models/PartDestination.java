package models;

public class PartDestination {
	private String partType;
	private String receiverName;
	private String receiverAddress;
	private String receiverCity;
	
	public PartDestination(String partType, String receiverName, String receiverAddress, String receiverCity) {
		this.partType = partType;
		this.receiverName = receiverName;
		this.receiverAddress = receiverAddress;
		this.receiverCity = receiverCity;
	}

	public String getPartType() {
		return partType;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public String getReceiverAddress() {
		return receiverAddress;
	}

	public String getReceiverCity() {
		return receiverCity;
	}
}
