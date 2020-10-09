package com.dhl.g05.database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbConnection {

	private static String dbhost = "jdbc:mysql://db-5308.cs.dal.ca:3306/CSCI5308_5_DEVINT?"
			+ "useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=AST";
	private static String username = "CSCI5308_5_DEVINT_USER";
	private static String password = "2qUcrXvypd";
	private static Connection conn;


	public Connection createNewDBconnection() throws SQLException {
		System.setProperty("Jdbc.drivers", "com.mysql.jdbc.Driver");
		Properties properties = new Properties();
		properties.put("user", username);
		properties.put("password", password);
		try {
			conn = DriverManager.getConnection(dbhost, properties);
			System.out.println("Connection established: "+ conn);

		} 
		catch (SQLException e) {
			System.out.println("Cannot connect to database");
			e.printStackTrace();
		}
		return conn; 
	}

	public void closeDbConnection(Connection conn2) 
	{
		try 
		{
			conn.close();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally 
		{
			if(conn !=null)
				try {
					conn.close();
					System.out.println("Connection closed");
				} 
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
	}
}


