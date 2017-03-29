package com.tdt4140.bob.Application.Admin;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.tdt4140.bob.Application.DatabaseHandler;

public class AdminHandler {
	public ResultSet getSubjects(DatabaseHandler dbh, String code) throws SQLException {
		String query = "SELECT keyword,frequency FROM search, subject WHERE search.code = subject.code AND search.code = ?";
		PreparedStatement prepStatement = dbh.prepareQuery(query);
		prepStatement.setString(1, code);
		return prepStatement.executeQuery();
	}
}