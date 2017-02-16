package com.tdt4140.bob.Application.Login;

import com.tdt4140.bob.Application.DatabaseHandler;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Register extends Application {
	@FXML private TextField user;
	@FXML private TextField pass;
	@FXML private TextField cpass;
	@FXML private Button btnReg;

	@Override
	public void start(Stage arg0) throws Exception {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../src/main/resources/Register.fxml"));
		Parent root = (Parent)fxmlLoader.load();
		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.show();
		
	}
}
