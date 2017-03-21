package com.tdt4140.bob.Application.Admin;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.tdt4140.bob.Application.DatabaseHandler;

public class AdminHandler {
	public ResultSet getSubjects(DatabaseHandler dbh) throws SQLException {
		String query = "SELECT subject.code, coursename FROM user_subject, subject WHERE user_subject.username = 'test' AND subject.code = user_subject.code";
		PreparedStatement prepStatement = dbh.prepareQuery(query);
		//prepStatement.setString(1, "test");
		return prepStatement.executeQuery();
	}
}
