package com.tdt4140.bob.JavaFX.Controllers.Login;

import java.sql.SQLException;

import com.tdt4140.bob.Application.DatabaseHandler;
import com.tdt4140.bob.Application.Login.LoginHandler;
import com.tdt4140.bob.JavaFX.Controllers.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class RegisterController extends Controller {
	@FXML
	private Button btnRegister;
	@FXML
	private TextField txtUsername, txtFname, txtLname;
	@FXML
	private PasswordField passPassword, passCPassword;
	@FXML
	private Label labelError;

	private DatabaseHandler dbh;

	private LoginHandler lh;
	
	String username, fname, lname, password, cpassword;

	public void registerUser() throws SQLException {
		dbh = app.getDatabaseHandler();
		lh = new LoginHandler();
		
		username = txtUsername.getText();
		fname = txtFname.getText();
		lname = txtLname.getText();
		password = passPassword.getText();
		cpassword = passCPassword.getText();
		
		labelError.setTextFill(Color.RED);
		
		if (lh.getUserCredentials(dbh, username).next()) {
			labelError.setText("Username is already taken!");
		} else if (!isValidUsername(username)) {
			labelError.setText("Username must be between 3 to 16 characters long.");
		} else if (!isValidPassword(password)) {
			labelError.setText("Password must be between 6 to 16 characters long.");
		} else if (!isMatchingPassword(password, cpassword)) {
			labelError.setText("Password do not match.");
		} else if (!isValidName(fname, lname)) {
			labelError.setText("First and last name must be at least two letters long.");
		} else if (!isString(fname) || !isString(lname)) {
			labelError.setText("Do you really have a non-letter character in your name?");
		} else {
			try {
				lh.registerUser(dbh, username, cpassword, fname, lname);
				
				labelError.setTextFill(Color.GREEN);
				labelError.setText("Successfully registered!");
				app.makeLogin();
				
//				txtUsername.setText("");
//				passPassword.setText("");
//				passCPassword.setText("");
//				txtFname.setText("");
//				txtLname.setText("");
			} catch (SQLException e) {
				labelError.setText("Registration failed: SQLState(" + e.getSQLState() + ")");
			}
		}
	}

	public boolean isValidUsername(String username) {
		if (username.length() >= 3 && username.length() <= 16) {
			return true;
		}
		return false;
	}
	
	public boolean isValidPassword(String password) {
		if(password.length() >= 6 && password.length() <= 16) {
			return true;
		}
		return false;
	}

	public boolean isMatchingPassword(String password, String cpassword) {
		if(password.equals(cpassword)) {
			return true;
		}
		return false;
	}

	public boolean isValidName(String fname, String lname) {
		if(fname.length() >= 2 && lname.length() >= 2) {
			return true;
		}
		return false;
	}
	
	public boolean isString(String text) {
		if(text.matches(".*\\d+.*")) {
			return false;
		}
		return true;
	}
}
