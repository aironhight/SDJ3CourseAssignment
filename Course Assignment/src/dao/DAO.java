package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DAO 
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
		if (true) {
			
			
			try {
			
				Statement stmt = conn.createStatement();
				
				stmt.executeUpdate("CREATE SCHEMA IF NOT EXISTS facility_schema");
				
				stmt.executeUpdate("CREATE TABLE cars (carID serial PRIMARY KEY, "
						+ "make VARCHAR(10) not null, "
						+ "model VARCHAR(10) not null, "
						+ "year int not null,"
						+ "VIN VARCHAR(10) not null,"
						+ "weight read not null);");
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

	public static void main(String[] args)
	{
		new DAO();
	}

}
