package com.tdt4140.bob.JavaFX.Controllers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.controlsfx.control.textfield.TextFields;

import com.tdt4140.bob.Application.ViewMaker;
import com.tdt4140.bob.Application.Admin.AdminHandler;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

public class UserAccessController extends Controller {
	@FXML
	private TextField txtUsers;

	@FXML
	private Label txtFeedback;

	@FXML
	private Button btnGrant, btnRevoke;

	@FXML
	private AnchorPane viewAdmins;

	private List<String> users = new ArrayList<String>();
	private List<String> admins = new ArrayList<String>();

	public void grantAdmin() throws SQLException {
		if (users.contains(txtUsers.getText()) && !admins.contains(txtUsers.getText())) {
			AdminHandler.grantAccess(app.getDatabaseHandler(), txtUsers.getText());
			txtFeedback.setTextFill(Color.GREEN);
			txtFeedback.setText("User is now admin!");
			updateTableView();
		} else if (admins.contains(txtUsers.getText())) {
			txtFeedback.setTextFill(Color.RED);
			txtFeedback.setText("User is already an administrator.");
		} else {
			txtFeedback.setTextFill(Color.RED);
			txtFeedback.setText("User does not exist.");
		}
	}

	public void revokeAdmin() throws SQLException {
		if (users.contains(txtUsers.getText()) && admins.contains(txtUsers.getText())) {
			AdminHandler.revokeAccess(app.getDatabaseHandler(), txtUsers.getText());
			txtFeedback.setTextFill(Color.GREEN);
			txtFeedback.setText("User is no longer admin!");
			updateTableView();
		} else if (!users.contains(txtUsers.getText())) {
			txtFeedback.setTextFill(Color.RED);
			txtFeedback.setText("User does not exists.");
		} else {
			txtFeedback.setTextFill(Color.RED);
			txtFeedback.setText("User is not admin.");
		}
	}

	public void onLoad() {
		ResultSet rs = null;
		try {
			updateTableView();
			rs = AdminHandler.getAllUsers(app.getDatabaseHandler());
			while (rs.next()) {
				this.users.add(rs.getString("username"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		TextFields.bindAutoCompletion(txtUsers, this.users);
	}

	private void updateTableView() throws SQLException {
		this.admins = new ArrayList<String>();
		ResultSet rs = AdminHandler.getAllAdmins(app.getDatabaseHandler());
		viewAdmins.getChildren().add(ViewMaker.makeTable(rs, Arrays.asList("Username", "First name", "Last name")));
		rs = AdminHandler.getAllAdmins(app.getDatabaseHandler());
		while (rs.next()) {
			admins.add(rs.getString("username"));
		}

	}

	public void goDash() {
		app.makeDash();
	}
}
