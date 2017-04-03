package com.tdt4140.bob.Application.Chat;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.tdt4140.bob.Application.DatabaseHandler;

public class ChatHandler {
	
	/** 
     * Retrieves data from database
     * <p>
     * Function used to gather the subjects that a given user is assigned to.
     *
     * @param dbh		Get connection to the database with Databasehandler 
     * @param username	Given username used in query
     * @return			Returns the resultset from database
     * @author 			jorgburg
     */
	public static ResultSet getSubjects(DatabaseHandler dbh, String username) throws SQLException {
		String query = "SELECT coursename FROM user, user_subject, subject WHERE user_subject.username = user.username AND user_subject.code = subject.code AND user.username = ?";
		PreparedStatement prepStatement = dbh.prepareQuery(query);
		prepStatement.setString(1, username);
		return prepStatement.executeQuery();
	}
	
	/** 
     * Retrieves data from database
     * <p>
     * Function used to gather the names of lecturers that lectures in a given course
     *
     * @param dbh		Get connection to the database with Databasehandler 
     * @param coursename	Given coursename used in query
     * @return			Returns the resultset from database
     * @author 			jorgburg
     */
	public static ResultSet getLecturer(DatabaseHandler dbh, String coursename) throws SQLException {
		String query = "SELECT fornavn, etternavn FROM subject, subject_lecturer, lecturer WHERE subject_lecturer.idlecturer = lecturer.idlecturer AND subject_lecturer.code = subject.code AND subject.coursename = ?";
		PreparedStatement prepStatement = dbh.prepareQuery(query);
		prepStatement.setString(1, coursename);
		return prepStatement.executeQuery();
	}
	
	/** 
     * Retrieves data from database
     * <p>
     * Function used to gather the subjectcode of a given course.
     *
     * @param dbh		Get connection to the database with Databasehandler 
     * @param username	Given coursename used in query
     * @return			Returns the resultset from database
     * @author 			jorgburg
     */
	public static ResultSet getSubjectCode(DatabaseHandler dbh, String coursename) throws SQLException {
		String query = "SELECT code FROM subject WHERE coursename = ?";
		PreparedStatement prepStatement = dbh.prepareQuery(query);
		prepStatement.setString(1, coursename);
		return prepStatement.executeQuery();
	}
	
	/** 
     * Writes data to database
     * <p>
     * Function used to write keywords from userinput assigned to a specific course.
     *
     * @param dbh		Get connection to the database with Databasehandler 
     * @param keyword	Given keyword from userinput
     * @param code		Code for the specific course
     * @return			Returns the executed qyery
     * @author 			jorgburg
     */
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
	
	/** 
     * Writes data to database
     * <p>
     * Function used to write questions that a specific user has asked the bot.
     *
     * @param dbh		Get connection to the database with Databasehandler 
     * @question		Given question user has asked
     * @param username	Given username used in query
     * @return			Returns the executed query
     * @author 			jorgburg
     */
	public static int writeQuestion(DatabaseHandler dbh, String question, String username) throws SQLException {
		String query = "INSERT INTO question (question, username) VALUES(?, ?)";
		PreparedStatement prepStatement = dbh.prepareQuery(query);
		prepStatement.setString(1, question);
		prepStatement.setString(2, username);
		return prepStatement.executeUpdate();
	}
	
	/** 
     * Retrieves data from database
     * <p>
     * Function used to gather the last questions to the logged in user.
     *
     * @param dbh		Get connection to the database with Databasehandler 
     * @param username	Given username used in query
     * @return			Returns the resultset from database
     * @author 			jorgburg
     */
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
	
	/** 
     * Retrieves data from database
     * <p>
     * Function used to return pages from the curriculum on a given topic.
     *
     * @param dbh		Get connection to the database with Databasehandler 
     * @param keyword	Given keyword that defines the topic			
     * @param username	Given code to define the course
     * @return			Returns the resultset from database
     * @author 			jorgburg
     */
	public static ResultSet getCurriculum(DatabaseHandler dbh, String keyword, String code) throws SQLException {
		String query = "SELECT pages FROM curriculum WHERE curriculum.code = ? AND curriculum.keyword = ?";
		PreparedStatement prepStatement = dbh.prepareQuery(query);
		prepStatement.setString(1, code);
		prepStatement.setString(2, keyword);
		return prepStatement.executeQuery();
	}
	

}
