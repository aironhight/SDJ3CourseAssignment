import java.rmi.RemoteException;
import java.util.ArrayList;

import server.global.TrackingServerMain;

public class TrackPart 
{
	private TrackingServerMain main;
	
	public TrackPart()
	{
		try {
			
			main = new TrackingServerMain();
			
			System.out.println("Tracking server launched");
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String trackPart(String carVin) 
	{
		try {
			
			return main.trackPart(carVin);
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "-1";
	}
}
