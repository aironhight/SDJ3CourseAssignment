package server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import dao.DAO;
import dao.DAOInterface;
import models.Car;
import models.Order;
import models.OrderPart;
import models.Part;

public class MasterServer extends UnicastRemoteObject implements Server{
	
	private Registry registry;
	private ArrayList<Car> cars;
	private DAOInterface dao;
	
	public MasterServer(int registryPort) throws RemoteException
    {
        super();
        registry = LocateRegistry.createRegistry(registryPort);
        cars = new ArrayList<Car>();
        dao = new DAO();
    }

	public static void main ( String args[] ) throws Exception
    {
		try 
		{
			MasterServer server = new MasterServer(1099);

			Naming.rebind ("MServer", server);
			

			System.out.println ("Master server online..");
		}
		catch (RemoteException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
    }
	
	
	@Override
	public String trackPart(String carVin) throws RemoteException {
		return "kurwa";
	}

	@Override
	public boolean registerCar(Car car) throws RemoteException 
	{
		try {
			
			cars.add(car);
			
			int carID = dao.addCarRecord(car);
			
			addParts(car.disassemble(), carID);
			
			assignPartsToOrders(car, carID);
		}
		catch(Exception e)
		{
			System.out.println("DATABASE connection error");
			
			return false;
		}
		return true;
	}

	private void assignPartsToOrders(Car car, int carID) 
	{
		ArrayList<Part> parts = dao.findAllPartsFromCar(carID, car.getVIN());	
		
		ArrayList<Order> orders = dao.getAllOrders();
		
		ArrayList<OrderPart> orderParts = dao.getAllOrderParts();
		
		boolean[] selected = new boolean[parts.size()];
		
		for (int i = 0; i < orders.size(); i++) {
			
			int orderID = orders.get(i).getOrderId();
			
			boolean canSupplyEverything = true;
			boolean[] selectedCurrent = new boolean[parts.size()];
			
			for (int j = 0; j < orderParts.size(); j++)
				if (orderParts.get(j).getID() == orderID) {
					
					boolean current = false;
					
					for (int k = 0; k < parts.size(); k++)
						if (!selected[k] && parts.get(k).getType().equals(orderParts.get(j).getPartType())
								&& orderParts.get(j).getCarMake().equals(car.getMake()) && orderParts.get(j).getCarModel().equals(car.getModel())
								&& orderParts.get(j).getCarYear() == car.getYear()) {
							
							selectedCurrent[k] = true;
							
							current = true;
							break;
							
						}
					
					if (!current) { canSupplyEverything = false; break; }
					
				}
			
			if (canSupplyEverything) {
				
				dao.updateOrderStatus(orders.get(i).getOrderId());
				
				for (int j = 0; j < parts.size(); j++)
					if (selectedCurrent[j]) selected[j] = true;
				
			}
			
		}
		
	}

	private void addParts(ArrayList<Part> parts, int carID) 
	{
		for (int i = 0; i < parts.size(); i++) {
			
			int palletID = dao.findPalletForPart(parts.get(i));
					
			dao.addPartRecord(parts.get(i), carID, palletID);
		}
	}

}
