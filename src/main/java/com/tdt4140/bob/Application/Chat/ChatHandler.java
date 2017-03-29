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
	
	public static ResultSet getSubjectCode(DatabaseHandler dbh, String coursename) throws SQLException {
		String query = "SELECT code FROM subject WHERE coursename = ?";
		PreparedStatement prepStatement = dbh.prepareQuery(query);
		prepStatement.setString(1, coursename);
		return prepStatement.executeQuery();
	}
	
	public static int writeKeywords(DatabaseHandler dbh, String keyword, String code) throws SQLException {
		int count = 0;
		final String queryCheck = "SELECT count(*) FROM search WHERE keyword = ? AND code = ?";
		final PreparedStatement prepStatement = dbh.prepareQuery(queryCheck);
		prepStatement.setString(1, keyword);
		prepStatement.setString(2, code);
		final ResultSet resultSet = prepStatement.executeQuery();
		if(resultSet.next()) {
		    count = resultSet.getInt(1);
		}
		
		if (count == 0) {
			String query = "INSERT INTO search (keyword, frequency, code) VALUES(?, ?, ?)";
			PreparedStatement prepStatement1 = dbh.prepareQuery(query);
			prepStatement1.setString(1, keyword);
			prepStatement1.setInt(2, 1);
			prepStatement1.setString(3, code);
			return prepStatement1.executeUpdate();
		} else {
			String query = "UPDATE search SET frequency = frequency + 1 WHERE keyword = ? AND code = ?";
			
			PreparedStatement prepStatement1 = dbh.prepareQuery(query);
			prepStatement1.setString(1, keyword);
			prepStatement1.setString(2, code);
			return prepStatement1.executeUpdate();
		}
	}
	
	public static int writeQuestion(DatabaseHandler dbh, String question, String username) throws SQLException {
		String query = "INSERT INTO question (question, username) VALUES(?, ?)";
		PreparedStatement prepStatement = dbh.prepareQuery(query);
		prepStatement.setString(1, question);
		prepStatement.setString(2, username);
		return prepStatement.executeUpdate();
	}
	
	public static ResultSet getQuestion(DatabaseHandler dbh, String username) throws SQLException {
		int count = 0;
		final String queryCheck = "SELECT count(*) FROM question WHERE username = ?";
		final PreparedStatement prepStatement = dbh.prepareQuery(queryCheck);
		prepStatement.setString(1, username);
		final ResultSet resultSet = prepStatement.executeQuery();
		if(resultSet.next()) {
		    count = resultSet.getInt(1);
		}
		
		if (count < 5) {
			String query = "SELECT question FROM question WHERE username = ?";
			PreparedStatement prepStatement1 = dbh.prepareQuery(query);
			prepStatement1.setString(1, username);
			return prepStatement1.executeQuery();
		} else {
			int nr = count - 5;
			String query = "SELECT question FROM question WHERE username = ? AND idquestion > " + nr;
			PreparedStatement prepStatement1 = dbh.prepareQuery(query);
			prepStatement1.setString(1, username);
			return prepStatement1.executeQuery();
		}
	}
	
	public static ResultSet getCurriculum(DatabaseHandler dbh, String keyword, String code) throws SQLException {
		String query = "SELECT pages FROM curriculum WHERE curriculum.code = ? AND curriculum.keyword = ?";
		PreparedStatement prepStatement = dbh.prepareQuery(query);
		prepStatement.setString(1, code);
		prepStatement.setString(2, keyword);
		return prepStatement.executeQuery();
	}
	

}
