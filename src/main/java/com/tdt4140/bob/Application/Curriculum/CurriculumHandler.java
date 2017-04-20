package com.tdt4140.bob.Application.Curriculum;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.tdt4140.bob.Application.DatabaseHandler;
import com.tdt4140.bob.JavaFX.Controllers.Login.User;


public class CurriculumHandler {
	
	/** 
	 * Method is used by "AdminController"
	 * <p>
	 * Retrieves page, keyword & code data from database in order to add keyword to curriculum in AdminController.
	 *
	 * @author 			lajohnso
	 */
	
	public  int addKeyword(DatabaseHandler dbh, String page, String keyword, String code) throws SQLException {
		String query = "INSERT INTO curriculum VALUES(?,?,?)";
		PreparedStatement prepStatement = dbh.prepareQuery(query);
		prepStatement.setString(1, code);
		prepStatement.setString(2, keyword);
		prepStatement.setString(3, page);
		return prepStatement.executeUpdate();
	}
	/** 
	 * Method is used by "AdminController"
	 * <p>
	 * Retrieves code data from database in order to generate tabs in tableView1 & tableView2 in AdminController.
	 *
	 * @author 			lajohnso
	 */
	
	
	public ResultSet getPageKey(DatabaseHandler dbh, String code) throws SQLException {
		String query = "SELECT keyword,pages FROM curriculum WHERE code = ?";
		PreparedStatement prepStatement = dbh.prepareQuery(query);
		prepStatement.setString(1, code);
		return prepStatement.executeQuery();
	}
}