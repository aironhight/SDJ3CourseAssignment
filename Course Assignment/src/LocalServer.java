
import java.rmi.RemoteException;
import java.util.ArrayList;

import models.*;
import server.local.LocalServerMain;

import org.json.*;

public class LocalServer 
{
	private ArrayList<Car> cars;
	private LocalServerMain main;
		
	public LocalServer()
	{
		cars = new ArrayList<>();
		main = null;
		
		try {
			
			main = new LocalServerMain();
			
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
				
				main.registerCar(newCar);
				
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
}