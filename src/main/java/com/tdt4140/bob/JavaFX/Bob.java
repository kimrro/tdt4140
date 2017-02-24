package com.tdt4140.bob.JavaFX;

import java.io.IOException;

import com.tdt4140.bob.Application.DatabaseHandler;
import com.tdt4140.bob.JavaFX.Controllers.Controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Bob extends Application {
	
	private Stage primaryStage;
	private Controller currentController;
	private DatabaseHandler dbh = new DatabaseHandler("com.mysql.jdbc.Driver",
			"jdbc:mysql://rds-mysql-bob.c9ztinmq6h0z.us-west-2.rds.amazonaws.com:3306/bobdb?autoConnect=true&useSSL=false",
			"robert",
			"dbbob123");

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		//makeLogin();
		makeRegister();
	}

	private void makeLogin() {
		setScene(loadGeneric("/Login.fxml", "Login"));
		
	}
	
	private void makeRegister() {
		setScene(loadGeneric("/Register.fxml", "Register"));
	}
	
	public void makeDash() {
		setScene(loadGeneric("Dashbord.fxml", "Dashbord"));
	}

	private void setScene(Parent parent) {
		primaryStage.setScene(new Scene(parent));
		parent.getStylesheets().getClass().getResource("/style.css");
		primaryStage.show();
		
	}

	private Parent loadGeneric(String path, String title) {
		FXMLLoader fxmlLoader = new FXMLLoader(Bob.class.getClassLoader().getResource("FXML/" + path));
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
	
	public DatabaseHandler getDatabaseHandler() {
		return this.dbh;
	}
}
