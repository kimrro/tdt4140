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

	/**
	 * A function to start the set the application's GUI, and open login.
	 * @param primaryStage The application is set to this stage.
	 * @author KimRobin
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		makeLogin();
	}

	/**
	 * A function to set the scene to login.
	 * @author KimRobin
	 */
	public void makeLogin() {
		setScene(loadGeneric("/Login.fxml", "Login"));
	}
	
	/**
	 * A function to set the scene to register.
	 * @author KimRobin
	 */
	public void makeRegister() {
		setScene(loadGeneric("/Register.fxml", "Register"));
	}
	
	/**
	 * A function to set the scene to dashboard/chat.
	 * @author KimRobin
	 */
	public void makeDash() {
		setScene(loadGeneric("Dashbord.fxml", "Dashbord"));
	}
	
	public void makeAdmin() {
		setScene(loadGeneric("Admin.fxml", "Admin"));
	}

	/**
	 * A function to set the scene to settings.
	 * @author KimRobin
	 */
	public void makeSettings() {
		setScene(loadGeneric("/Settings.fxml", "Settings"));

	}
	
	/**
	 * A function to set the scene to "grant user access"-view.
	 * @author KimRobin
	 */
	public void makeUserAccess() {
		setScene(loadGeneric("/UserAccess.fxml", "Grant user access"));
		
	}

	/**
	 * A function used when setting a scene.
	 * @param parent Parent of scene.
	 * @author KimRobin
	 */
	private void setScene(Parent parent) {
		primaryStage.setScene(new Scene(parent));
		parent.getStylesheets().getClass().getResource("/style.css");
		primaryStage.show();
		
	}

	/**
	 * A generic function to load .FXML files when setting a scene.
	 * @param path The path of the .FXML file
	 * @param title The title of the scene (seen at the top of the window)
	 * @return
	 */
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
		currentController.onLoad();
		
		primaryStage.setTitle(title);
		return parent;
	}
	
	/**
	 * A function used to get the DatabaseHandler. Used in external classes to connect to database.
	 * @return Returns the DatabaseHandler
	 * @author KimRobin
	 */
	public DatabaseHandler getDatabaseHandler() {
		return this.dbh;
	}
}
