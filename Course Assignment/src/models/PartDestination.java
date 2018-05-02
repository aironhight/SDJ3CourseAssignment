package models;

public class PartDestination {
	private String partType;
	private String receiverName;
	private String receiverAddress;
	private String receiverCountry;
	
	public PartDestination(String partType, String receiverName, String receiverAddress, String receiverCity) {
		this.partType = partType;
		this.receiverName = receiverName;
		this.receiverAddress = receiverAddress;
		this.receiverCountry = receiverCity;
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

	public String getReceiverCountry() {
		return receiverCountry;
	}
}
