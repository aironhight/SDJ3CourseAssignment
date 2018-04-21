package dao;

public class DatabaseDetails {
	
   private static final String USERID = "postgres";
   private static final String PASSWORD = "goofy123";
   private static final String DATABASE = "postgres";
   private static final String SCHEMA_NAME = "facility_schema";
   private static final String IPADDRESS = "localhost";
   
   private static DatabaseDetails instance;
   
   /**
    * The constructor for DatabaseDetails class
    */
   
   private DatabaseDetails()
   {
      
   }
   
   /**
    * A method to gets the instance of the Database Details class
    * 
    * @return the instance of the Database Details class
    */
   
   public static DatabaseDetails getInstance()
   {
      if (instance == null) instance = new DatabaseDetails();
         
      return instance;
   }
   
   /**
    * A method that gets the IP Address of the database.
    * 
    * @return the IP Address of the database
    */
   
   public static String getIPAddress()
   {
      return IPADDRESS;
   }
   
   /**
    * A method that gets the userID of the database 
    * 
    * @return the userID of the database 
    */
   
   public static String getUserId()
   {
      return USERID;
   }
   
   /**
    * A method that gets the database password
    * 
    * @return the database password
    */
   
   public static String getPassword()
   {
      return PASSWORD;
   }
   
   /**
    * A method that gets the database name
    * 
    * @return database name
    */
   
   public static String getDatabase()
   {
      return DATABASE;
   }
   
   /**
    * A method that gets the name of schema from the database
    * 
    * @return the name of schema from the database
    */
   
   public static String getSchema()
   {
      return SCHEMA_NAME;
   }
}
