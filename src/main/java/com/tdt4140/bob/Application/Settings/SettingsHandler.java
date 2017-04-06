package com.tdt4140.bob.Application.Settings;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.tdt4140.bob.Application.DatabaseHandler;
import com.tdt4140.bob.JavaFX.Controllers.Login.User;

public class SettingsHandler {
	
	/**
	 * A function to find all of a user's subjects.
	 * @param dbh Get database to connect to.
	 * @return Returns ResultSet with all of a user's subjects (subject code and coursename).
	 * @author KimRobin
	 */
	public ResultSet getUserSubjects(DatabaseHandler dbh) throws SQLException {
		String query = "SELECT subject.code, coursename FROM user_subject, subject WHERE user_subject.username = ? AND subject.code = user_subject.code";
		PreparedStatement prepStatement = dbh.prepareQuery(query);
		prepStatement.setString(1, User.getUsername());
		return prepStatement.executeQuery();
	}
	
	/**
	 * A function to find all existing subjects not added to a user's list of subjects.<p>
	 * Used to view available subjects when a user is adding/removing subjects.
	 * @param dbh Get database to connect to.
	 * @return Returns ResultSet with all subjects that a user has not added as their subject.
	 * @author KimRobin
	 */
	public ResultSet getAllSubjects(DatabaseHandler dbh) throws SQLException {
		String query = "SELECT code, coursename FROM subject WHERE NOT EXISTS (SELECT code FROM user_subject WHERE subject.code = user_subject.code AND username = ?)";
		PreparedStatement prepStatement = dbh.prepareQuery(query);
		prepStatement.setString(1, User.getUsername());
		return prepStatement.executeQuery();
	}
	
	/**
	 * A function to find a user's current password.<p>
	 * Used as a confirmation when changing password.
	 * @param dbh Get database to connect to
	 * @return Returns a ResultSet with a user's current password.
	 * @author KimRobin
	 */
	public ResultSet getCurrentPassword(DatabaseHandler dbh) throws SQLException {
		String query = "SELECT password FROM user WHERE username = ?";
		PreparedStatement prepStatement = dbh.prepareQuery(query);
		prepStatement.setString(1, User.getUsername());
		return prepStatement.executeQuery();
	}
	
	/**
	 * A function to update a user's password.
	 * @param dbh Get database to connect to.
	 * @param password Update password to this string value.
	 * @author KimRobin
	 */
	public static void updatePassword(DatabaseHandler dbh, String password) throws SQLException {
		String query = "UPDATE user SET password = ? WHERE username = ?";
		PreparedStatement prepStatement = dbh.prepareQuery(query);
		prepStatement.setString(1, password);
		prepStatement.setString(2, User.getUsername());
		prepStatement.executeUpdate();
	}
	
	/**
	 * A function to add subject to a user.
	 * @param dbh Get database to connect to.
	 * @param item String value to specify which subject (item selected in TableView) to add.
	 * @author KimRobin
	 */
	public static void addSubjects(DatabaseHandler dbh, String item) throws SQLException {
		String query = "INSERT INTO user_subject (code, username) VALUES (?, ?)";
		PreparedStatement prepStatement = dbh.prepareQuery(query);
		prepStatement.setString(1, item);
		prepStatement.setString(2, User.getUsername());
		prepStatement.executeUpdate();
	}
	
	/**
	 * A function to remove subject from a user.
	 * @param dbh Get database to connect to.
	 * @param item String value to specify which subject (item selected in TableView) to remove.
	 * @author KimRobin
	 */
	public static void removeSubjects(DatabaseHandler dbh, String item) throws SQLException {
		String query = "DELETE FROM user_subject WHERE code = ? AND username = ?";
		PreparedStatement prepStatement = dbh.prepareQuery(query);
		prepStatement.setString(1, item);
		prepStatement.setString(2, User.getUsername());
		prepStatement.executeUpdate();
	}
}
