package com.tdt4140.bob.JavaFX.Controllers.Login;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.tdt4140.bob.Application.DatabaseHandler;

public class LoginHandler {
	public boolean isTest(DatabaseHandler dbh, String username) throws SQLException {
		ResultSet exist = isUserExisting(dbh, "test");
		if (exist.next()) {
			return true;
		}
		return false;
	}
	
	public ResultSet isUserExisting(DatabaseHandler dbh, String username) throws SQLException {
		String query = "SELECT * FROM user";
		PreparedStatement prepStatement = dbh.prepareQuery(query);
		prepStatement.setString(1, username);
		return prepStatement.executeQuery();
	}
}
