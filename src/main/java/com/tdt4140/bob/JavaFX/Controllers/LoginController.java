package com.tdt4140.bob.JavaFX.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController extends Controller{
	
	@FXML
	private TextField txtUsername;
	@FXML
	private PasswordField passPassword;
	@FXML
	private Button btnLogin;
	
	public void onLogin() {
		System.out.println(txtUsername.getText());
	}
	
}
