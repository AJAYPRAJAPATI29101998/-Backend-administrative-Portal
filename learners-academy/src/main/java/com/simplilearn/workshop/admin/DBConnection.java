package com.simplilearn.workshop.admin;

import java.sql.Connection;


import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	private static Connection connection;
	
	public DBConnection () throws ClassNotFoundException, SQLException {
		
		
	}
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		// load a driver
				Class.forName("com.mysql.cj.jdbc.Driver");
				
				// obtain a connection
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/administrative-portal?useSSL=false","root","Root@123");
				
		
		return connection;
	}
	public void closeConnection() throws SQLException {
		if(this.connection !=null) {
			this.connection.close();
		}
	}

}