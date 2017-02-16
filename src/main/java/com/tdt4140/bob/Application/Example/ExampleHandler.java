package com.tdt4140.bob.Application.Example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.tdt4140.bob.Application.DatabaseHandler;

public class ExampleHandler {
	public static ResultSet getAllUserInfo(DatabaseHandler dbh) throws SQLException {
		String query = "SELECT * FROM user";
		PreparedStatement prepStatement = dbh.prepareQuery(query);
		return prepStatement.executeQuery();
	}
	
	public static ResultSet getSpecificUserInfo(String username, DatabaseHandler dbh) throws SQLException {
		String query = "SELECT * FROM user " +
					   "WHERE username = ?";
		PreparedStatement prepStatement = dbh.prepareQuery(query);
		prepStatement.setString(1, username);
		return prepStatement.executeQuery();
	}
}
