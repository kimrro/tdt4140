package com.tdt4140.bob.JavaFX.Controllers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

import com.tdt4140.bob.Application.ViewMaker;
import com.tdt4140.bob.Application.Subjects.SettingsHandler;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.Pane;

public class SettingsController extends Controller {

	private SettingsHandler sh;

	@FXML
	private Pane subjectView;

	@FXML
	private Label txtError;

	@FXML
	private PasswordField passOldPass, passNewPass, passCNewPass;

	public void changePassword() throws SQLException {
		if (isEqualOldPassword()) {
			if (isEqualNewPassword()) {
				
			}
		} else {
			
		}
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

	@Override
	public void onLoad() {
		System.out.println("Test");
		sh = new SettingsHandler();
		ResultSet rs = null;
		try {
			rs = sh.getSubjects(app.getDatabaseHandler());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		subjectView.getChildren().add(ViewMaker.makeTable(rs, Arrays.asList("Code", "Coursename")));

		if (subjectView.getChildren().isEmpty()) {
			txtError.setVisible(true);
		}
	}

}
