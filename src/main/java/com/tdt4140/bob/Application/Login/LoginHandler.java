package com.tdt4140.bob.Application.Login;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.tdt4140.bob.Application.DatabaseHandler;

public class LoginHandler {
	
	public ResultSet getUserCredentials(DatabaseHandler dbh, String username) throws SQLException {
		String query = "SELECT * FROM user WHERE username = ?";
		PreparedStatement prepStatement = dbh.prepareQuery(query);
		prepStatement.setString(1, username);
		return prepStatement.executeQuery();
	}

}
