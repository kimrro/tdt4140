package com.tdt4140.bob.Application.Example;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.tdt4140.bob.Application.DatabaseHandler;

public class ExampleInfo {
	//Get all user info
	public String getUserInfo(DatabaseHandler dbh) throws SQLException {
		ResultSet rs = ExampleHandler.getAllUserInfo(dbh);
		String allUsers = "";
		while (rs.next()) {
			String user = rs.getString("username");
			String password = rs.getString("password");
			String fname = rs.getString("fname");
			String lname = rs.getString("lname");
			allUsers += ("\n\nUsername: " + user + "\nPassword: " + password + "\nFornavn: " + fname + "\nEtternavn: " + lname);
		}
		
		return allUsers;
	}
	
	//Get specific user's info
	public String getUsersInfo(String username, DatabaseHandler dbh) throws SQLException {
		ResultSet rs = ExampleHandler.getSpecificUserInfo(username, dbh);
		if(rs.next()) {
			String user = rs.getString("username");
			String pass = rs.getString("password");
			String fname = rs.getString("fname");
			String lname = rs.getString("lname");
			return ("\n\nUsername: " + user + "\nPassword: " + pass + "\nFirst name: " + fname + "\nLast name: " + lname);
		} else {
			return ("Username '" + username + "' does not exist.");
		}
	}

}
