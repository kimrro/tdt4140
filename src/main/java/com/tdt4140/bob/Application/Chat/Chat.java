package com.tdt4140.bob.Application.Chat;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Chat extends Application {
	@FXML private TextField text;
	@FXML private TextArea chat;
	@FXML private Button button;

	@Override
	public void start(Stage arg) throws Exception {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../src/main/resources/Dashbord.fxml"));
		Parent root = (Parent)fxmlLoader.load();
		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.show();
		
	}
}
