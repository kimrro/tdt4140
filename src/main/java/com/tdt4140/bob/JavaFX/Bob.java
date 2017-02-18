package com.tdt4140.bob.JavaFX;

import java.io.IOException;

import com.tdt4140.bob.Application.DatabaseHandler;
import com.tdt4140.bob.Application.Login.User;
import com.tdt4140.bob.JavaFX.Controllers.Controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Bob extends Application {
	
	private Stage primaryStage;
	private Controller currentController;
	private DatabaseHandler dbh;
	private User user;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		makeLogin();
	}

	private void makeLogin() {
		setScene(loadGeneric("/src/main/resources/Login.fxml", "Login"));
	}
	
	public void makeDash() {
        setScene(loadGeneric("/src/main/resources/LoggedIn.fxml", "LoggedIn"));
    }
	
	public User getUser() { 
    	return this.user; 
    }
	
	public void setUser(User user) {
        this.user = user;
    }
	
	public DatabaseHandler getDatabaseHandler() {
        return this.dbh;
    }

    public void setDatabaseHandler(DatabaseHandler dbh) {
		this.dbh = dbh;
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
