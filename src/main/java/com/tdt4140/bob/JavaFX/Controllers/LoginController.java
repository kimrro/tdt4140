package com.tdt4140.bob.JavaFX.Controllers;

import java.awt.TextField;
import java.sql.SQLException;

import com.tdt4140.bob.Application.DatabaseHandler;
import com.tdt4140.bob.Application.Login.LoginHandler;

import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;

public class LoginController extends Controller{
	
	private TextField username;
	private PasswordField password;
	private Text actionTarget;
	private Button button;
	
	private DatabaseHandler dbh;
	private LoginHandler lh;
	String name; 
	String pwd;
	
	public void buttonClicked() throws SQLException{
		dbh = app.getDatabaseHandler();
		
		name = username.getText();
		pwd = password.getText();
		
		try {
			lh.getUserCredentials(dbh, name).next();
		} catch (SQLException e) {
            System.out.println(e.getMessage());
            actionTarget.setFill(Paint.valueOf("REDBRICK"));
            actionTarget.setText("404_FeilOppdaget_Ring_Lars: 45245345");
            
        } if (lh.getUserCredentials(dbh, name).findColumn("password") == pwd) {
			 actionTarget.setFill(Paint.valueOf("GREEN"));
				actionTarget.setText("Velkommen");
				try {
	                app.makeDash();
	            } catch (Exception e) {
	                System.out.println(e.getMessage());
	            }
		} else {
            actionTarget.setFill(Paint.valueOf("#ff3636"));
            actionTarget.setText("Feil brukernavn eller passord");
        }
    }
}
