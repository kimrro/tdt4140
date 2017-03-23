package com.tdt4140.bob.JavaFX.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.tdt4140.bob.Application.DatabaseHandler;
import com.tdt4140.bob.Application.Login.LoginHandler;
import com.tdt4140.bob.JavaFX.Controllers.Login.User;

public class LoginController extends Controller {
	
	@FXML
	private TextField txtUsername;
	@FXML
	private PasswordField passPassword;
	@FXML
	private Button btnLogin;
	@FXML
	private Text actionTarget;
	@FXML
	private Button btnRegister;
	
	private DatabaseHandler dbh;
	private LoginHandler lh;
	String username; 
	String pwd;
	String password;
	
	public void onLogin() throws SQLException {
		dbh = app.getDatabaseHandler();
		lh = new LoginHandler();
		
		username = txtUsername.getText();
		pwd = passPassword.getText();
		
//		try {
//			lh.getUserCredentials(dbh, name).next();
//		} catch (SQLException e) {
//            System.out.println(e.getMessage());
//            actionTarget.setFill(Paint.valueOf("REDBRICK"));
//            actionTarget.setText("404_FeilOppdaget_Ring_Lars: 45245345");
//            
//        } 
			
		ResultSet rs = lh.getUserCredentials(dbh, username);
		if(rs.next()) {
				password = rs.getString("password");
				if (password.equals(pwd)) {
					actionTarget.setFill(Color.GREEN);
					actionTarget.setText("Welcome!");
					try {
						createSession(username, rs.getInt("privilege"));
		                app.makeDash(); } 
					catch (Exception e) {
		                System.out.println(e.getMessage()); }
				} else {
					 actionTarget.setFill(Paint.valueOf("#ff3636"));
					 actionTarget.setText("Feil passord!"); }
		} else {
				actionTarget.setFill(Paint.valueOf("#ff3636"));
	            actionTarget.setText("Feil brukernavn!"); }
		
	}
	
	public void goToRegister() {
		try {
            app.makeRegister(); } 
		catch (Exception e) {
            System.out.println(e.getMessage());
		}
	}
	
	public void createSession(String username, int privilege) {
		User user = new User(username, privilege);
	}
		
}
	
	

	

