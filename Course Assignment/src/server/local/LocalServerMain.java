package server.local;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Scanner;

import models.Car;
import models.Part;
import server.Server;

public class LocalServerMain extends UnicastRemoteObject implements LocalServerInterface{

	private Server server;
	
	public LocalServerMain() throws RemoteException {
		super();
		try 
		{
			this.server = (Server) Naming.lookup("rmi://localhost:1099/MServer");
		} 
		catch (MalformedURLException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		System.out.println("Enter car make");
		
		String make = input.nextLine();
		
		System.out.println("Enter car model");
		
		String model = input.nextLine();
		
		System.out.println("Enter car weight");
		
		double weight = Double.parseDouble(input.nextLine());
		
		System.out.println("Enter car year");
		
		int year = Integer.parseInt(input.nextLine());
		
		System.out.println("Enter car VIN");
		
		String VIN = input.nextLine();
		
		// test 
		
		ArrayList<Part> parts = new ArrayList<Part>();
		//parts.add(new Part(6.7, 777, "engine"));
		
		//Car car = new Car(weight, make, model, year, VIN, parts);
		
		System.out.println("Attempting to start client..");
		
		System.out.println("-----------------------------------");
		
		try 
		{
			//LocalServer local = new LocalServer();
			
			//int result = local.registerCar(car);
			
			//System.out.println(result);
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public int registerCar(Car car) throws RemoteException {
		return server.registerCar(car);
	}
}
