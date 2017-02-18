package com.tdt4140.bob.Application.Login;

import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
	
	private final String username;

	public User(ResultSet userData) throws SQLException {
		this.username = userData.getString(1);
	}
	
    public String getName() {
        return this.username;
    }
}
