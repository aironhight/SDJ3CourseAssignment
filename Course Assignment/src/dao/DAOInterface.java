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
	void insertInOrderPart(OrderPart orderPart);
	int addOrderFromReceiver(Order order); 
	ArrayList<Order> getAllOrders();
	ArrayList<OrderPart> getAllCarParts();
	ArrayList<OrderPart> getAllOrderParts();
	void orderDispatched(int orderId);
	void partDispatched(int id);
	void addPick(int id, int orderID);
	String trackPartsByVin(String VIN);
}
