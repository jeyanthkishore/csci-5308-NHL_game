package com.dhl.g05.database;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
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
		InputStream inputStream = null;
		Path currentRelativePath = Paths.get("");
		String dbDirPath = currentRelativePath.toAbsolutePath().toString();
		Properties property = new Properties();
		Properties properties = new Properties();
		String fileName = "application.properties";
		boolean isNotLocalEnv = false;
		//inputStream = getClass().getClassLoader().getResourceAsStream(fileName);
		try {
			inputStream = new FileInputStream( dbDirPath.substring(0,  dbDirPath.lastIndexOf("target"))+fileName);
		}
		catch (Exception f) {
			System.setProperty("Jdbc.drivers", "com.mysql.jdbc.Driver");
			properties.put("user", username);
			properties.put("password", password);
			properties.put("url", dbhost);
			isNotLocalEnv = true;
		}
		if (inputStream != null && isNotLocalEnv) {

			try {
				property.load(inputStream);
			} catch (IOException e) {
				// TODO Auto-generated catch block

			}

			System.setProperty("Jdbc.drivers", 		property.getProperty("datasource.driver-class-name"));
			properties.put("user", property.getProperty("datasource.username"));
			properties.put("password", property.getProperty("datasource.password"));
			properties.put("url", property.getProperty("datasource.url"));
		} 

		try {
			conn = DriverManager.getConnection(properties.getProperty("url"), properties);
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


