
import java.rmi.RemoteException;
import java.util.ArrayList;

import models.*;
import server.global.TrackingServerMain;
import server.local.LocalServerMain;

import org.json.*;

public class LocalServer 
{
	private ArrayList<Car> cars;
	private LocalServerMain mainLocalServer;
	private TrackingServerMain mainTrackingServer;
		
	public LocalServer()
	{
		cars = new ArrayList<>();
		mainLocalServer = null;
		mainTrackingServer = null;
		
		try {
			
			mainLocalServer = new LocalServerMain();
			mainTrackingServer = new TrackingServerMain();
			
			System.out.println("Local Server Launched");
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean registerCar(String vin, String model, String make, int year, double weight, String parts)
	{
		try {
			
			ArrayList<Part> partsArr = new ArrayList<>();
			
			JSONArray arrParts = new JSONArray(parts);
			
			for (int i = 0; i < arrParts.length(); i++) {
				
				JSONObject jsonObj = arrParts.getJSONObject(i);
				
				Part part = new Part(jsonObj.getString("Type"), jsonObj.getString("CarVIN"), jsonObj.getDouble("Weight")); 
				
				partsArr.add(part);
			}
			
			System.out.println(vin + " " + model + " " + make + " " + year + " " + weight + " " + partsArr);
			
			Car newCar = new Car(vin, model, make, year, weight, partsArr);
			
			cars.add(newCar);
			
			try {
				
				mainLocalServer.registerCar(newCar);
				
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
				return false;
			}
			
			return true;
		
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

	public String trackPart(String carVin) 
	{
		try {
			
			return mainTrackingServer.trackPart(carVin);
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "-1";
	}
}