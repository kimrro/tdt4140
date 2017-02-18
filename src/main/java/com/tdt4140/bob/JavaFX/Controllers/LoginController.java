package com.tdt4140.bob.JavaFX.Controllers;

import java.awt.Button;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

import com.tdt4140.bob.Application.DatabaseHandler;
import com.tdt4140.bob.Application.Login.LoginHandler;
import com.tdt4140.bob.Application.Login.User;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;

public class LoginController extends Controller{
	
	private TextField username;
	private TextField password;
	private Text actionTarget;
	
	protected void buttonClicked() {
		DatabaseHandler dbh = new DatabaseHandler();
		dbh.connect();
        app.setDatabaseHandler(dbh);

        User user = null;
        try {
            user = LoginHandler.login(username.getText(), password.getText(), dbh);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            actionTarget.setFill(Paint.valueOf("REDBRICK"));
            actionTarget.setText("404_FeilOppdaget_Ring_Lars: 45245345");
        }
        if (user != null) {
            actionTarget.setText("Velkommen");
            try {
                app.setUser(user);
                app.makeDash();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        else {
            actionTarget.setFill(Paint.valueOf("#ff3636"));
            actionTarget.setText("Feil brukernavn eller passord");
        }
    
    }
}
