package com.tdt4140.bob.JavaFX;

import java.io.IOException;

import com.tdt4140.bob.JavaFX.Controllers.Controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Bob extends Application {
	
	private Stage primaryStage;
	private Controller currentController;

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		//makeLogin();
		makeDash();
	}

	private void makeLogin() {
		setScene(loadGeneric("/Login.fxml", "Login"));
	}
	
	private void makeDash() {
		setScene(loadGeneric("/src/main/resources/Dashbord.fxml", "Chatte med BoB"));
	}

	private void setScene(Parent parent) {
		primaryStage.setScene(new Scene(parent));
		parent.getStylesheets().getClass().getResource("/style.css");
		primaryStage.show();
		
	}

	private Parent loadGeneric(String path, String title) {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(path));
		Parent parent = null;
		try {
			parent = fxmlLoader.load();
		} catch (IOException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
		
		currentController = fxmlLoader.getController();
		currentController.setApp(this);
		
		primaryStage.setTitle(title);
		return parent;
	}

}
