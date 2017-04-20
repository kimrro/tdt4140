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
	
	
	/** 
     * Function activated by button clicked
     * <p>
     * Retrieves the password for the user from database and checks if it matches the written password from input
     *
     * @author 			jorgburg
     */
	
	public void onLogin() throws SQLException {
		dbh = app.getDatabaseHandler();
		lh = new LoginHandler();
		
		username = txtUsername.getText();
		pwd = passPassword.getText();
			
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
	
	/** 
     * Function activated by button clicked
     * <p>
     * Sends the user to register page 
     *
     * @author 			jorgburg
     */
	public void goToRegister() {
		try {
            app.makeRegister(); } 
		catch (Exception e) {
            System.out.println(e.getMessage());
		}
	}
	
	/** 
     * Function activated after user is logged in
     * <p>
     * Saves the privilege of the user, which tells if the user is either admin or student
     *
     * @author 			jorgburg
     */
	public void createSession(String username, int privilege) {
		User user = new User(username, privilege);
	}
		
}
	
	

	

