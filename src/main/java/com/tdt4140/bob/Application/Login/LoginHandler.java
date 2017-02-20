package com.tdt4140.bob.Application.Login;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.tdt4140.bob.Application.BCrypt;
import com.tdt4140.bob.Application.DatabaseHandler;
import com.tdt4140.bob.JavaFX.*;

public class LoginHandler {
	
	public static Login login(String userName, String password, DatabaseHandler dbh) throws SQLException {
        ResultSet userData = getUserPassword(userName, dbh);
        //check if the password matches the encrypted one
        if (userData.next()) {
            if (BCrypt.checkpw(password, userData.getString(4))) {
                return null; //return new User(userData);
            }
        }
        return null;
    }
	
	public static ResultSet getUserPassword(String username, DatabaseHandler dbh) throws SQLException {
		String query = "SELECT password FROM user " +
					   "WHERE username = ?";
		PreparedStatement prepStatement = dbh.prepareQuery(query);
		prepStatement.setString(1, username);
		return prepStatement.executeQuery();
	}
}
