package com.tdt4140.bob.JavaFX.Controllers;

import java.awt.TextField;
import java.sql.SQLException;

import com.tdt4140.bob.Application.DatabaseHandler;
import com.tdt4140.bob.Application.Login.LoginHandler;

import javafx.scene.control.PasswordField;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;

public class LoginController extends Controller{
	
	private TextField username;
	private PasswordField password;
	private Text actionTarget;
	
	protected void buttonClicked() {
		DatabaseHandler dbh = new DatabaseHandler("com.mysql.jdbc.Driver", 
				"jdbc:mysql://rds-mysql-bob.c9ztinmq6h0z.us-west-2.rds.amazonaws.com:3306/bobdb?autoConnect=true&useSSL=false",
				"robert",
				"dbbob123");
		app.getDatabaseHandler();

        try {
            LoginHandler.login(username.getText(), password.getText(), dbh);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            actionTarget.setFill(Paint.valueOf("REDBRICK"));
            actionTarget.setText("404_FeilOppdaget_Ring_Lars: 45245345");
        }
        if (username.getText() != null) {
            actionTarget.setText("Velkommen");
            try {
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
