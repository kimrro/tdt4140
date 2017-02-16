package com.tdt4140.bob.Application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseHandler {

	private Connection con = null;
	
	private final String url = "jdbc:mysql://rds-mysql-bob.c9ztinmq6h0z.us-west-2.rds.amazonaws.com:3306/bobdb?autoConnect=true&useSSL=false";
	private final String username = "robert";
	private final String password = "dbbob123";
	
	public void connect() {
		System.out.println("Connecting to database...");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, username, password);
			if (this.con != null) {
				System.out.println("Connected!");
			}
		} catch (SQLException e) {
			System.out.println("Could not connect to database\nERROR: " + e.getSQLState());
		} catch (ClassNotFoundException e) {
			System.out.println("Could not find driver\nERROR: " + e.getMessage());
		}
		
	}
	
	public void close() {
		try {
			con.close();
		} catch (SQLException e) {
			System.out.println("Could not close connection to database\nERROR: " + e.getSQLState());
		}
	}

	public PreparedStatement prepareQuery(String query) throws SQLException {
		return con.prepareStatement(query);
	}

}
