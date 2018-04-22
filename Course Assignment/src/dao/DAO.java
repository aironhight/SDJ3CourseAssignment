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
		boolean execute = false;
		
		if (execute) {
			
			
			try {
			
				Statement stm = conn.createStatement();
				
				stm.executeUpdate("CREATE SCHEMA IF NOT EXISTS facility_schema");
				
				stm.close();
				
				PreparedStatement stmt = conn.prepareStatement("CREATE TABLE cars (carID serial PRIMARY KEY, "
						+ "make VARCHAR(10) not null, "
						+ "model VARCHAR(10) not null, "
						+ "year int not null,"
						+ "VIN VARCHAR(10) not null,"
						+ "weight real not null)");
				
				stmt.executeUpdate();
				
				stmt.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
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
