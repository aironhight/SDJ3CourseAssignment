package models;
import java.util.ArrayList;
import java.util.List;

public class Pallet {
	
	private String partType;
	private double maxWeight;
	private String id;
	private ArrayList<Part> parts;
	private double currentWeight;
	
	public Pallet(String partType, double maxWeight, String id, ArrayList<Part> parts) {
		this.partType = partType;
		this.maxWeight = maxWeight;
		this.id = id;
		this.parts = parts;
		currentWeight = getPartsWeight(parts);
	}
	
	public String getPartType() {
		return partType;
	}
	
	public double getMaxWeight() {
		return maxWeight;
	}
	
	public String getId() {
		return id;
	}
	
	public int numberOfParts() {
		return parts.size();
	}
	
	public double getCurrentWeight() {
		return currentWeight;
	}
	
	private double getPartsWeight(ArrayList<Part> partsList) {
		double weight = 0;
		
		for(int i=0; i<partsList.size(); i++) {
			weight += partsList.get(i).getWeight();
		}
		
		return weight;
	}
		
	public ArrayList<Part> getParts(int quantity) throws IllegalArgumentException {
		if(quantity  > parts.size()) {
			throw new IllegalArgumentException("The requested quantity is more than the available on the pallet");
		}
		ArrayList<Part> sublist = new ArrayList<Part>(quantity);
		
		for(int i=0; i<quantity; i++) {
			sublist.add(parts.remove(i));
		}
		
		return sublist;	
	}
	
	public ArrayList<Part> addParts(ArrayList<Part> partsList)  throws IllegalArgumentException{
		if(partsList == null || partsList.size() == 0) {
			throw new IllegalArgumentException("Empty lists are not allowed");
		}
		
		double partsWeight = getPartsWeight(partsList);
		
		if((partsWeight + getPartsWeight(parts)) > maxWeight) {
			throw new IllegalArgumentException("The weight of the pallet will surpass the maximum allowed weight");
		}
		
		if(parts.addAll(partsList)) {
			currentWeight += partsWeight;
			return partsList;
		} else {
			return null;
		}
	}
}
