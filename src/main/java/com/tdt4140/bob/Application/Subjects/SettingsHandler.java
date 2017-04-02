package com.tdt4140.bob.Application.Subjects;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.tdt4140.bob.Application.DatabaseHandler;
import com.tdt4140.bob.JavaFX.Controllers.Login.User;

public class SettingsHandler {
	public ResultSet getUserSubjects(DatabaseHandler dbh) throws SQLException {
		String query = "SELECT subject.code, coursename FROM user_subject, subject WHERE user_subject.username = ? AND subject.code = user_subject.code";
		PreparedStatement prepStatement = dbh.prepareQuery(query);
		prepStatement.setString(1, User.getUsername());
		return prepStatement.executeQuery();
	}
	
	public ResultSet getAllSubjects(DatabaseHandler dbh) throws SQLException {
		String query = "SELECT code, coursename FROM subject";
		PreparedStatement prepStatement = dbh.prepareQuery(query);
		return prepStatement.executeQuery();
	}
	
	public ResultSet getOldPassword(DatabaseHandler dbh) throws SQLException {
		String query = "SELECT password FROM user WHERE username = ?";
		PreparedStatement prepStatement = dbh.prepareQuery(query);
		prepStatement.setString(1, User.getUsername());
		return prepStatement.executeQuery();
	}
	
	public static void updatePassword(DatabaseHandler dbh, String password) throws SQLException {
		String query = "UPDATE user SET password = ? WHERE username = ?";
		PreparedStatement prepStatement = dbh.prepareQuery(query);
		prepStatement.setString(1, password);
		prepStatement.setString(2, User.getUsername());
		prepStatement.executeUpdate();
	}
}
