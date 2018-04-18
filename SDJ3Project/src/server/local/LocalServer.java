package server.local;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;
import server.Server;

public class LocalServer extends UnicastRemoteObject implements LocalServerInterface{

	private Server server;
	
	public LocalServer() throws RemoteException {
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
		
		System.out.println("Enter the desired car details");
		
		String s = input.nextLine();
		
		System.out.println("Attempting to start client..");
		
		System.out.println("-----------------------------------");
		
		try 
		{
			LocalServer local = new LocalServer();
			
			local.registerCar();
		} 
		catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void registerCar() throws RemoteException {
		server.registerCar();
	}
}
