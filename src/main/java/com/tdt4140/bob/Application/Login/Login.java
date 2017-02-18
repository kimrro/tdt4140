package com.tdt4140.bob.Application.Login;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Login extends Application {
	@FXML private TextField username;
	@FXML private TextField password;
	@FXML private Text actionTarget;
	@FXML private Button button;

	@Override
	public void start(Stage arg) throws Exception {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../src/main/resources/Login.fxml"));
		Parent root = (Parent)fxmlLoader.load();
		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.show();
		
	}
}
