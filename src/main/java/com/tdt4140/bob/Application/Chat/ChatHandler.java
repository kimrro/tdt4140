package com.tdt4140.bob.Application.Chat;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.tdt4140.bob.Application.DatabaseHandler;

public class ChatHandler {
	

	
	public static ResultSet getSubjects(DatabaseHandler dbh, String username) throws SQLException {
		String query = "SELECT coursename FROM user, user_subject, subject WHERE user_subject.username = user.username AND user_subject.code = subject.code AND user.username = ?";
		PreparedStatement prepStatement = dbh.prepareQuery(query);
		prepStatement.setString(1, username);
		return prepStatement.executeQuery();
	}
	
	public static ResultSet getLecturer(DatabaseHandler dbh, String coursename) throws SQLException {
		String query = "SELECT fornavn, etternavn FROM subject, subject_lecturer, lecturer WHERE subject_lecturer.idlecturer = lecturer.idlecturer AND subject_lecturer.code = subject.code AND subject.coursename = ?";
		PreparedStatement prepStatement = dbh.prepareQuery(query);
		prepStatement.setString(1, coursename);
		return prepStatement.executeQuery();
	}

}
