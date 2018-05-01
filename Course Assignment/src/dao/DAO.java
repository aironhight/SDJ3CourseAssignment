package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import models.Car;
import models.Part;

public class DAO implements DAOInterface
{
	private Connection conn;
	
	public DAO()
	{
        try {
        	
			Class.forName("org.postgresql.Driver");
	        
	        DatabaseDetails d = DatabaseDetails.getInstance();
	        
	        conn = DriverManager.getConnection("jdbc:postgresql://" + d.getIPAddress() + ":5432/" + d.getDatabase(), d.getUserId(), d.getPassword());
	        
	        conn.setSchema(d.getSchema());
	        
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        buildDDL();
	}
	
	private void buildDDL() 
	{
		boolean execute = true;
		
		if (execute) {
			
			try {
			
				Statement stm = conn.createStatement();
				
				stm.executeUpdate("CREATE SCHEMA IF NOT EXISTS facility_schema");
				stm.executeUpdate("DROP TABLE IF EXISTS car CASCADE");
				stm.executeUpdate("DROP TABLE IF EXISTS pallet CASCADE");
				stm.executeUpdate("DROP TABLE IF EXISTS part CASCADE");
				stm.executeUpdate("DROP TABLE IF EXISTS orders CASCADE");
				stm.executeUpdate("DROP TABLE IF EXISTS pick CASCADE");
				stm.executeUpdate("DROP TABLE IF EXISTS orderParts CASCADE");
				
				stm.executeUpdate("CREATE TABLE part (partID serial PRIMARY KEY, " +
						 			"weight decimal NOT NULL CHECK(weight > 0)," + 
									"partType varchar NOT NULL," + 
									"palletID int NOT NULL," + 
									"carID int NOT NULL)");

				stm.executeUpdate("CREATE TABLE orderParts ("
								+ "OrderPartID serial PRIMARY KEY,"
								+ "PartName varchar not null,"
								+ "CarMake varchar not null,"
								+ "CarModel varchar not null,"
								+ "CarYear int not null,"
								+ "OrderID int not null)");
				
				PreparedStatement stmt = conn.prepareStatement("CREATE TABLE car ("
									+ "carID serial PRIMARY KEY, "
									+ "make VARCHAR(10) not null, "
									+ "model VARCHAR(10) not null, "
									+ "year int not null,"
									+ "VIN VARCHAR(15) not null,"
									+ "weight real not null)");
				
				stmt.executeUpdate();
				
				stmt = conn.prepareStatement("CREATE TABLE pallet ("
									+ "palletID serial PRIMARY KEY,"
									+ "partType varchar(30) not null,"
									+ "currentWeight real not null,"
									+ "maximumWeight real not null)");
				
				stmt.executeUpdate();
				
				stmt = conn.prepareStatement("CREATE TABLE pick(" 
									+ "pickID serial PRIMARY KEY," 
									+ "partID int NOT NULL," 
									+ "orderID int NOT NULL)");
				
				stmt.executeUpdate();
				
				stmt = conn.prepareStatement("CREATE TABLE orders("
									+ "orderID serial PRIMARY KEY," 
									+ "receiver_address varchar NOT NULL,"
									+ "receiver_country varchar NOT NULL,"
									+ "receiver_name varchar NOT NULL,"
									+ "dispatched boolean NOT NULL)");
				
				stmt.executeUpdate();
				
				System.out.println("TABLES CREATED SUCCESFULLY");

				stmt.close();

				stm.executeUpdate("alter table part add foreign key (carID) references car (carID) on delete restrict on update restrict");
	
				stm.executeUpdate("alter table part add foreign key (palletID) references pallet (palletID) on delete restrict on update restrict");
				
				stm.executeUpdate("alter table pick add foreign key (orderID) references orders (orderID) on delete restrict on update restrict");
				
				stm.executeUpdate("alter table pick add foreign key (partID) references part (partID) on delete restrict on update restrict");

				stm.executeUpdate("alter table orderParts add foreign key (OrderID) references orders (orderID) on delete restrict on update restrict");
				
				stm.close();

				addPalletsType();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		if (!execute) {
			
		}
	}
	
	private void addPalletsType() throws SQLException 
	{
		addNewPallet("engine", 1025.45);
		addNewPallet("doors", 425.50);
		addNewPallet("windows", 125.145);
		addNewPallet("battery", 200.0);
		addNewPallet("brakes", 98.756);
		addNewPallet("oil system", 105.55);
		addNewPallet("cooling system", 105.55);
		addNewPallet("fuel system", 185.0);
		addNewPallet("suspension", 200.4);
		addNewPallet("transmission", 440.70);
	}

	@Override
	public int addCarRecord(Car car) {
		
		try {
			
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO car (make, model, year, VIN, weight)"
														 + " VALUES (?, ?, ?, ?, ?) ");
			
			stmt.setString(1, car.getMake());
			stmt.setString(2, car.getModel());
			stmt.setInt(3, car.getYear());
			stmt.setString(4, car.getVIN());
			stmt.setDouble(5, car.getWeight());
			
			stmt.executeUpdate();
			
			stmt = conn.prepareStatement("SELECT * FROM car");
			
			ResultSet rS = stmt.executeQuery();
			
			System.out.println("-----------------------------------------------------");
			
			while (rS.next()) {
				
				System.out.println(rS.getInt(1) + " " + rS.getString(2) + " " + rS.getString(3)+ " " + rS.getInt(4) + " " + rS.getString(5) + " " + rS.getDouble(6));
				
			}
			
			stmt.close();
			
			return keyLookupCarByVIN(car.getVIN());
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		printAllCars();
		
		return -1;
	}

	private void printAllCars() 
	{
		try {
			
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM car");
			
			ResultSet rS = stmt.executeQuery();
			
			while (rS.next()) {
				
				System.out.println(rS.getInt(1) + " #  " + rS.getString(2) + " " + rS.getString(3) + " " + rS.getInt(4) + " " + rS.getString(5) + " " + rS.getDouble(6));
				
			}
			
			System.out.println("---------------------------");
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int findPalletForPart(Part part)
	{
		try {

			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM pallet WHERE partType = ?");

			stmt.setString(1, part.getType());
			
			ResultSet rS = stmt.executeQuery();
			
			int palletID = 0;
			double currentWeight = 0;
			double maxDifference = -1;
			
			while (rS.next()) { 
				
				if (rS.getDouble(4) - rS.getDouble(3) > maxDifference) {
					
					palletID = rS.getInt(1);
					currentWeight = rS.getDouble(3);
					maxDifference = rS.getDouble(4) - currentWeight;
					
				}
			}
			
			stmt.close(); rS.close();
			
			if (maxDifference > part.getWeight()) { 
				
				updatePalletWithID(palletID, currentWeight + part.getWeight()); 
				
				return palletID;
				
			} else {
				
				addNewPallet(part.getType(), Math.min(2000, Math.max(part.getWeight() * 4, 100)));
			
				return keyLookupPalletByPartType(part.getType());
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
	
	@Override
	public void addPartRecord(Part part, int carID, int palletID) 
	{
		try {
			
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO part (weight, part_type, car_id, pallet_id) VALUES (?, ?, ?, ?)");
			
			stmt.setDouble(1, part.getWeight());
			stmt.setString(2, part.getType());
			stmt.setInt(3, carID);
			stmt.setInt(4, palletID);
			
			stmt.executeUpdate();
			
			stmt = conn.prepareStatement("SELECT * FROM part");
			
			ResultSet rS = stmt.executeQuery();
			
			while (rS.next()) {
				
				System.out.println(rS.getInt(1) + " " + rS.getDouble(2) + " " + rS.getString(3) + " " + rS.getInt(4) + " " + rS.getInt(5) + " --");
				
			}
			
			stmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void addNewPallet(String type, double maximumWeight) throws SQLException 
	{
		PreparedStatement stmt = conn.prepareStatement("INSERT INTO pallet (partType, currentWeight, maximumWeight) VALUES (?, 0, ?)"); 
		
		stmt.setString(1, type);
		stmt.setDouble(2, maximumWeight);
		
		stmt.executeUpdate();
		
		System.out.println("NEW PALLET ADDED");
		
		stmt.close();
	}

	private void updatePalletWithID(int palletID, double d) throws SQLException 
	{
		PreparedStatement stmt = conn.prepareStatement("UPDATE pallet SET currentWeight = ? WHERE palletID = ?");
		
		stmt.setDouble(1, d);
		stmt.setInt(2, palletID);
		
		stmt.executeUpdate();
		
		stmt.close();
	}
	
	private int keyLookupCarByVIN(String VIN) throws SQLException
	{
		PreparedStatement stmt = conn.prepareStatement("SELECT carID FROM car WHERE VIN = ?");
		
		stmt.setString(1, VIN);
		
		ResultSet rS = stmt.executeQuery();
		
		rS.next();
		
		return rS == null ? -1 : rS.getInt(1);
	}

	private int keyLookupPalletByPartType(String partType) throws SQLException
	{
		PreparedStatement stmt = conn.prepareStatement("SELECT max(palletID) FROM pallet WHERE partType = ?");
		
		stmt.setString(1, partType);
		
		ResultSet rS = stmt.executeQuery();
		
		int index = -1;
		
		while (rS.next()) { index = rS.getInt(1); }
		
		return index;
	}
	
	private void trackPartsByVin(String VIN) throws SQLException{
		ArrayList<Part> parts = new ArrayList<Part>(); // List of the parts that came from the car
		
		PreparedStatement stmt = conn.prepareStatement //Find the parts that came from the car
				("SELECT * FROM part p "
				+ "JOIN car c" 
				+"ON (p.car_id = c.carID"
				+ "WHERE c.VIN = ?");
		stmt.setString(1, VIN);
		
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) { // Add all the parts to the arrayList
			Part temp = new Part(rs.getString("part_type")
								, rs.getString("car_id") 
								, rs.getDouble("weight"));
			
			parts.add(temp);
		}
	}
	
}
