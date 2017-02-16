package com.tdt4140.bob.Application.Login;

import com.tdt4140.bob.Application.DatabaseHandler;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Register {
	@FXML private TextField user;
	@FXML private TextField pass;
	@FXML private TextField cpass;
	@FXML private Button btnReg;
	
	public void registerUser(DatabaseHandler dbh) {
		String username = user.getText();
		System.out.println(username);
	}
}
