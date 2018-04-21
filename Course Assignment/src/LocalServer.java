
import java.util.ArrayList;

import models.*;

import org.json.*;

public class LocalServer 
{
	private ArrayList<Car> cars;
		
	public LocalServer()
	{
		cars = new ArrayList<>();
	}
	
	public int registerCar(String vin, String model, String make, int year, double weight, String parts)
	{
		try {
			
			ArrayList<Part> partsArr = new ArrayList<>();
			
			JSONArray arrParts = new JSONArray(parts);
			
			for (int i = 0; i < arrParts.length(); i++) {
				
				JSONObject jsonObj = arrParts.getJSONObject(i);
				
				Part part = new Part(jsonObj.getString("Type"), jsonObj.getString("CarVIN"), jsonObj.getDouble("Weight")); 
				
				partsArr.add(part);
			}
			
			cars.add(new Car(vin, model, make, year, weight, partsArr));
			
			return 1;
		
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}
}