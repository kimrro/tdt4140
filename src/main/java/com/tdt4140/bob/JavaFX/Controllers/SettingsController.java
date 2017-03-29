package com.tdt4140.bob.JavaFX.Controllers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

import com.tdt4140.bob.Application.DatabaseHandler;
import com.tdt4140.bob.Application.ViewMaker;
import com.tdt4140.bob.Application.Subjects.SettingsHandler;
import com.tdt4140.bob.JavaFX.Controllers.Login.User;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;

public class SettingsController extends Controller {

	private SettingsHandler sh;

	@FXML
	private Pane subjectView;

	@FXML
	private Label txtError, txtUser;
	
	@FXML
	private Text feedback;

	@FXML
	private PasswordField passOldPass, passNewPass, passCNewPass;
	
	@FXML
	private Button btnSubmit, btnBack;
	
	private DatabaseHandler dbh;

	public void changePassword() throws SQLException {
		String Pass = passNewPass.getText();
		String Pass2 = passCNewPass.getText(); 
		if (isEqualOldPassword()) {
			if (Pass.equals(Pass2) && Pass.length() > 2 && Pass.length() < 17) {
				SettingsHandler.updatePassword(dbh, Pass);
				feedback.setFill(Color.GREEN);
				feedback.setText("Updated!");
			} else if (Pass.length() < 2 || Pass.length() > 16) {
				feedback.setFill(Paint.valueOf("#ff3636"));
				feedback.setText("The new passwords does not meet the requirements!");
			} else if (!(Pass.equals(Pass2))) {
				feedback.setFill(Paint.valueOf("#ff3636"));
				feedback.setText("The new passwords doesn´t match!");
			}
		} else {
			feedback.setFill(Paint.valueOf("#ff3636"));
			feedback.setText("The old password is wrong!");
		}
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
	
	public void goBack() {
		app.makeDash();
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
		subjectView.getChildren().add(ViewMaker.makeTable(rs, Arrays.asList("Code", "Coursename")));

		if (subjectView.getChildren().isEmpty()) {
			txtError.setVisible(true);
		}
	}

}
