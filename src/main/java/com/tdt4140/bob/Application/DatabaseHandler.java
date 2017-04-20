package com.tdt4140.bob.Application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseHandler {

	private Connection con = null;
	
	/**
	 * A constructor used when establishing a connection to the database.
	 * @param dbclass Database driver
	 * @param url Database's URL + port
	 * @param username Database's username
	 * @param password Database's password
	 * @author KimRobin
	 */
	public DatabaseHandler(String dbclass, String url, String username, String password) {
		System.out.println("Connecting to database...");
		try {
			Class.forName(dbclass);
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
	
	/**
	 * A function to close connection.
	 * @author KimRobin
	 */
	public void close() {
		try {
			con.close();
		} catch (SQLException e) {
			System.out.println("Could not close connection to database\nERROR: " + e.getSQLState());
		}
	}

	/**
	 * A function to prepare a statement.<p>
	 * Used when executing queries to database.
	 * @param query The query a user wants to prepare.
	 * @return Returns a prepared statement.
	 * @author KimRobin
	 */
	public PreparedStatement prepareQuery(String query) throws SQLException {
		return con.prepareStatement(query);
	}

}
