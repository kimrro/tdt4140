package com.tdt4140.bob.Application.Admin;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.tdt4140.bob.Application.DatabaseHandler;

/** 
 * Method runs when called upon by different controllers.
 * <p>
 * Retrieves keywords and search frequency from the database. 
 *
 * @author 			lajohnso
 */

public class AdminHandler {
	public ResultSet getSubjects(DatabaseHandler dbh, String code) throws SQLException {
		String query = "SELECT keyword,frequency FROM search, subject WHERE search.code = subject.code AND search.code = ?";
		PreparedStatement prepStatement = dbh.prepareQuery(query);
		prepStatement.setString(1, code);
		return prepStatement.executeQuery();
	}
	
	/**
	 * A function to get all the admins from the database.
	 * @param dbh Get the database to connect to.
	 * @return A ResultSet with all the admins.
	 * @author KimRobin
	 */
	public static ResultSet getAllAdmins(DatabaseHandler dbh) throws SQLException {
		String query = "SELECT username, fname, lname FROM user WHERE privilege > 1";
		PreparedStatement prepStatement = dbh.prepareQuery(query);
		return prepStatement.executeQuery();
	}
	
	public static ResultSet getAllUsers(DatabaseHandler dbh) throws SQLException {
		String query = "SELECT username FROM user";
		PreparedStatement prepStatement = dbh.prepareQuery(query);
		return prepStatement.executeQuery();
	}
	
	public static void grantAccess(DatabaseHandler dbh, String username) throws SQLException {
		String query = "UPDATE user SET privilege = 2 WHERE username = ?";
		PreparedStatement prepStatement = dbh.prepareQuery(query);
		prepStatement.setString(1, username);
		prepStatement.executeUpdate();
	}
	
	public static void revokeAccess(DatabaseHandler dbh, String username) throws SQLException {
		String query = "UPDATE user SET privilege = 1 WHERE username = ?";
		PreparedStatement prepStatement = dbh.prepareQuery(query);
		prepStatement.setString(1, username);
		prepStatement.executeUpdate();
	}
}