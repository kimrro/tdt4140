package com.tdt4140.bob.Application.Curriculum;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.tdt4140.bob.Application.DatabaseHandler;
import com.tdt4140.bob.JavaFX.Controllers.Login.User;

public class CurriculumHandler {
	
	public  int addKeyword(DatabaseHandler dbh, String page, String keyword, String code) throws SQLException {
		String query = "INSERT INTO curriculum VALUES(?,?,?)";
		PreparedStatement prepStatement = dbh.prepareQuery(query);
		prepStatement.setString(1, code);
		prepStatement.setString(2, keyword);
		prepStatement.setString(3, page);
		return prepStatement.executeUpdate();
	}
	
	public ResultSet getPageKey(DatabaseHandler dbh, String code) throws SQLException {
		String query = "SELECT keyword,pages FROM curriculum WHERE code = ?";
		PreparedStatement prepStatement = dbh.prepareQuery(query);
		prepStatement.setString(1, code);
		return prepStatement.executeQuery();
	}
}