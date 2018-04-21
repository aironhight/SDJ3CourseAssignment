package server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import models.Car;

public class MasterServer extends UnicastRemoteObject implements Server{
	
	private Registry registry;
	private ArrayList<Car> cars;
	
	public MasterServer(int registryPort) throws RemoteException
    {
        super();
        registry = LocateRegistry.createRegistry(registryPort);
        cars = new ArrayList<Car>();
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
<<<<<<< HEAD
		return "success!";
=======
		return "kurwa e maika ti";
>>>>>>> 7314523562388eaa532fb48b0749eb46024068b1
	}

	@Override
	public int registerCar(Car car) throws RemoteException {
		cars.add(car);
		System.out.println(car.toString());
		if(!cars.contains(car))
			return 0;
		else
			return 1;
	}

}
