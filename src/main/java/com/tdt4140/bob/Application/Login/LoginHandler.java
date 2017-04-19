package com.tdt4140.bob.Application.Login;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.tdt4140.bob.Application.DatabaseHandler;

public class LoginHandler {
	/**
	 * A function to return username, password and privilege.
	 * @param dbh Get database to connect to.
	 * @param username Find this user's credentials.
	 * @return Returns a ResultSet with user credentials.
	 * @author KimRobin 
	 */
	public ResultSet getUserCredentials(DatabaseHandler dbh, String username) throws SQLException {
		String query = "SELECT * FROM user WHERE username = ?";
		PreparedStatement prepStatement = dbh.prepareQuery(query);
		prepStatement.setString(1, username);
		return prepStatement.executeQuery();
	}

	/**
	 * A function to insert user credentials into database.<p>
	 * Used when registering a user.
	 * @param dbh Get database to connect to.
	 * @param username Register user with this username.
	 * @param password Register user with this password.
	 * @param fname Register user with this first name.
	 * @param lname Register user with this last name.
	 * @author KimRobin
	 */
	public void registerUser(DatabaseHandler dbh, String username, String password, String fname, String lname) throws SQLException {
		String query = "INSERT INTO user (username, password, fname, lname) " +
					   "VALUES (?, ?, ?, ?)";
		PreparedStatement prepStatement = dbh.prepareQuery(query);
		
		prepStatement.setString(1, username);
		prepStatement.setString(2, password);
		prepStatement.setString(3, fname);
		prepStatement.setString(4, lname);
		
		prepStatement.executeUpdate();
	}

}
