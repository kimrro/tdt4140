package com.tdt4140.bob.Application;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.tdt4140.bob.JavaFX.Controllers.Login.User;

import javafx.collections.ObservableList;

public class ViewSubjects {
	
	public ResultSet getSubjects(DatabaseHandler dbh) throws SQLException {
		String query = "SELECT * FROM user_subject WHERE username = " + User.getUsername();
		PreparedStatement prepStatement = dbh.prepareQuery(query);
		return prepStatement.executeQuery();
	}
}
