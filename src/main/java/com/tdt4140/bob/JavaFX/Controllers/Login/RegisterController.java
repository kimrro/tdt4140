package com.tdt4140.bob.JavaFX.Controllers.Login;

import java.sql.SQLException;

import com.tdt4140.bob.Application.DatabaseHandler;
import com.tdt4140.bob.JavaFX.Controllers.Controller;
import com.tdt4140.bob.Application.Login.LoginHandler;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class RegisterController extends Controller {
	@FXML
	private Button btnRegister;
	@FXML
	private TextField txtUsername;

	private DatabaseHandler dbh;

	private LoginHandler lh;

	public void registerUser() throws SQLException {
		dbh = app.getDatabaseHandler();
		lh = new LoginHandler();

		if (lh.getUserCredentials(dbh, txtUsername.getText()).next()) {
			System.out.println("Username is already taken!");
		} else {

		}
	}

	private boolean isValidUsername() {
		if(txtUsername.getText().length() >= 3 && txtUsername.getText().length() <= 16) {
			return true;
		}
		return false;
	}

	private boolean isValidPassword() {

	}

	private boolean isValidName() {

	}
}
