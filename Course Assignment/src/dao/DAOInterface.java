package dao;

import java.util.ArrayList;

import models.Car;
import models.Order;
import models.Part;
import models.OrderPart;

public interface DAOInterface 
{
	int addCarRecord(Car car);
	int findPalletForPart(Part part);
	void addPartRecord(Part part, int carID, int palletID);
	ArrayList<Part> findAllPartsFromCar(int carID, String carVIN);
	ArrayList<OrderPart> getAllOrderParts();
	ArrayList<Order> getAllOrders();
}
