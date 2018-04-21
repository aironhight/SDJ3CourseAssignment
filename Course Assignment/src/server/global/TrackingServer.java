package server.global;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;
import server.*;

public class TrackingServer extends UnicastRemoteObject implements GlobalServer {

	private Server server;
	
	public TrackingServer() throws RemoteException {
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
		
		System.out.println("search for a part");
		
		String s = input.nextLine();
		
		System.out.println("Attempting to start client..");
		
		System.out.println("-----------------------------------");
		
		try 
		{
			TrackingServer trsrv = new TrackingServer();
			
			String answer = trsrv.trackPart(s);
			
			System.out.println(answer);
		} 
		catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public String trackPart(String carVin) throws RemoteException {
		String s = server.trackPart(carVin);
		return s;
	}

}
