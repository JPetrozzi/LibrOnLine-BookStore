package Persistencia;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConeccionDB 
{
	String DB = "LibrOnLine";
	String userDB = "root";
	String passDB = "";
	String driver = "com.mysql.jdbc.Driver";
	String urlDB = "jdbc:mysql://localhost/"+DB;
	Connection conn = null;
	
	public ConeccionDB()
	{
		try
		{
			Class.forName(driver);
			conn = DriverManager.getConnection(urlDB, userDB, passDB);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public Connection getConnection()
	{
		return conn;
	}
}