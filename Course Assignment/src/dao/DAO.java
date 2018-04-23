package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import models.Car;

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
				stm.executeUpdate("DROP TABLE cars");
				stm.executeUpdate("DROP TABLE pallet");
				
				stm.close();
				
				PreparedStatement stmt = conn.prepareStatement("CREATE TABLE cars ("
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
						+ "maximumWeight real not null)");
				
				stmt.executeUpdate();
				
				System.out.println("TABLES CREATED SUCCESFULLY");

				stmt.close();

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
		PreparedStatement stmt = null;
		
		stmt = conn.prepareStatement("INSERT INTO pallet (partType, maximumWeight) VALUES (?, ?)"); 
		stmt.setString(1, "Engine"); stmt.setDouble(2, 1025.45); stmt.executeUpdate();
		
		stmt = conn.prepareStatement("INSERT INTO pallet (partType, maximumWeight) VALUES (?, ?)"); 
		stmt.setString(1, "Doors"); stmt.setDouble(2, 425.50); stmt.executeUpdate();
		
		stmt = conn.prepareStatement("INSERT INTO pallet (partType, maximumWeight) VALUES (?, ?)"); 
		stmt.setString(1, "Windows"); stmt.setDouble(2, 125.145); stmt.executeUpdate();
		
		stmt = conn.prepareStatement("INSERT INTO pallet (partType, maximumWeight) VALUES (?, ?)"); 
		stmt.setString(1, "Battery"); stmt.setDouble(2, 200.0); stmt.executeUpdate();
		
		stmt = conn.prepareStatement("INSERT INTO pallet (partType, maximumWeight) VALUES (?, ?)"); 
		stmt.setString(1, "Breakes"); stmt.setDouble(2, 98.756); stmt.executeUpdate();
		
		stmt = conn.prepareStatement("INSERT INTO pallet (partType, maximumWeight) VALUES (?, ?)"); 
		stmt.setString(1, "Oil System"); stmt.setDouble(2, 105.55); stmt.executeUpdate();

		stmt = conn.prepareStatement("INSERT INTO pallet (partType, maximumWeight) VALUES (?, ?)"); 
		stmt.setString(1, "Cooling System"); stmt.setDouble(2, 105.55); stmt.executeUpdate();
		
		stmt = conn.prepareStatement("INSERT INTO pallet (partType, maximumWeight) VALUES (?, ?)"); 
		stmt.setString(1, "Fuel suply System"); stmt.setDouble(2, 185.0); stmt.executeUpdate();

		stmt = conn.prepareStatement("INSERT INTO pallet (partType, maximumWeight) VALUES (?, ?)"); 
		stmt.setString(1, "Suspension"); stmt.setDouble(2, 200.4); stmt.executeUpdate();

		stmt = conn.prepareStatement("INSERT INTO pallet (partType, maximumWeight) VALUES (?, ?)"); 
		stmt.setString(1, "Transmission System"); stmt.setDouble(2, 440.70); stmt.executeUpdate();
		
		System.out.println("Pallets added");
		System.out.println("------------------------------------------------");
		
		stmt = conn.prepareStatement("SELECT * FROM pallet");
		
		ResultSet rS = stmt.executeQuery();
		
		while (rS.next()) {
			
			System.out.println(rS.getInt(1) + " " + rS.getString(2) + " " + rS.getDouble(3));
			
		}
		
		stmt.close();
	}

	/* This method deletes all the rows from all the tables */
	private void deleteEverything()
	{
		try {
			
			PreparedStatement stmt = conn.prepareStatement("DELETE FROM cars");
			
			stmt.executeUpdate();
			
			stmt.close();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void addCar(Car car) {
		
		try {
			
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO cars (make, model, year, VIN, weight)"
														 + " VALUES (?, ?, ?, ?, ?) ");
			
			stmt.setString(1, car.getMake());
			stmt.setString(2, car.getModel());
			stmt.setInt(3, car.getYear());
			stmt.setString(4, car.getVIN());
			stmt.setDouble(5, car.getWeight());
			
			stmt.executeUpdate();
			
			stmt.close();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		printAllCars();
	}

	private void printAllCars() 
	{
		try {
			
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM cars");
			
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

}
