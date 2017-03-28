package com.tdt4140.bob.JavaFX.Controllers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

import com.tdt4140.bob.Application.ViewMaker;
import com.tdt4140.bob.Application.Subjects.SettingsHandler;
import com.tdt4140.bob.JavaFX.Controllers.Login.User;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.Pane;

public class SettingsController extends Controller {

	private SettingsHandler sh;

	@FXML
	private Pane yourSubjectsView, allSubjectsView;

	@FXML
	private Label txtError, txtUser;

	@FXML
	private PasswordField passOldPass, passNewPass, passCNewPass;
	
	@FXML
	private Button btnSubmit;

	public void changePassword() throws SQLException {
		passOldPass.setText("");
		passNewPass.setText("");
		passCNewPass.setText("");
	}

	public boolean isEqualOldPassword() throws SQLException {
		sh = new SettingsHandler();
		String oldPassword = null;
		ResultSet rs = sh.getOldPassword(app.getDatabaseHandler());
		if (rs.next()) {
			oldPassword = rs.getString(1);
		}

		return (passOldPass.getText().equals(oldPassword));
	}

	public boolean isEqualNewPassword() {
		return (passNewPass.getText().equals(passCNewPass.getText()));
	}
	
	public void goDashboard() {
		app.makeDash();
	}
	
	public void goSettings() {
		app.makeSettings();
	}
	
	public void addSubjects(){
		
	}
	
	public void deleteSubjects() {
		
	}
	
	@Override
	public void onLoad() {
		txtUser.setText(txtUser.getText() + User.getUsername());
		sh = new SettingsHandler();
		ResultSet rs = null;
		try {
			rs = sh.getSubjects(app.getDatabaseHandler());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		yourSubjectsView.getChildren().add(ViewMaker.makeTable(rs, Arrays.asList("Code", "Coursename")));

		if (yourSubjectsView.getChildren().isEmpty()) {
			txtError.setVisible(true);
		}
	}

}
