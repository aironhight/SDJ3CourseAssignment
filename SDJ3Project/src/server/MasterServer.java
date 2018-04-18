package server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class MasterServer extends UnicastRemoteObject implements Server{
	
	private Registry registry;
	
	public MasterServer(int registryPort) throws RemoteException
    {
        super();
        registry = LocateRegistry.createRegistry(registryPort);
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
		return "kurwo mamichkata ti da iba";
	}

	@Override
	public void registerCar() throws RemoteException {
		// TODO Auto-generated method stub
		
	}

}
