package server.global;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Scanner;
import server.*;

public class TrackingServerMain extends UnicastRemoteObject implements GlobalServer {

	private Server server;
	private ArrayList<String> requests;
	
	public TrackingServerMain() throws RemoteException {
		super();
		
		requests = new ArrayList<String>();
		
		try 
		{
			this.server = (Server) Naming.lookup("rmi://localhost:1099/MServer");
		} 
		catch (MalformedURLException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public String trackPart(String carVin) throws RemoteException 
	{
		requests.add(carVin);
		
		return server.trackPart(carVin);
	}

}
