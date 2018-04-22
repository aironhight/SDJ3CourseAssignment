package server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import dao.DAO;
import models.Car;

public class MasterServer extends UnicastRemoteObject implements Server{
	
	private Registry registry;
	private ArrayList<Car> cars;
	private DAO dao;
	
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
		return "success!";
	}

	@Override
	public boolean registerCar(Car car) throws RemoteException {
		
		try {
			cars.add(car);
			dao.addCar(car);
		}
		catch(Exception e)
		{
			System.out.println("DATABASE connection error");
			
			return false;
		}
		return true;
	}

}
