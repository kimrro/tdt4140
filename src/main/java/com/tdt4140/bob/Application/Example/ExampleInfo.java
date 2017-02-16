package com.tdt4140.bob.Application.Example;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.tdt4140.bob.Application.DatabaseHandler;

public class ExampleInfo {
	public String getUserInfo(DatabaseHandler dbh) throws SQLException {
		ResultSet rs = ExampleHandler.getAllUserInfo(dbh);
		if(rs.next()) {
			String user = rs.getString("username");
			String password = rs.getString("password");
			String fname = rs.getString("fname");
			String lname = rs.getString("lname");
			return ("Username: " + user + "\nPassword: " + password + "\nFornavn: " + fname + "\nEtternavn: " + lname);
		} else {
			return ("No user information found.");
		}
	}
}
