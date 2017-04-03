package com.tdt4140.bob.JavaFX.Controllers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import com.tdt4140.bob.Application.DatabaseHandler;
import com.tdt4140.bob.Application.ViewMaker;
import com.tdt4140.bob.Application.Subjects.SettingsHandler;
import com.tdt4140.bob.JavaFX.Controllers.Login.User;

import javafx.beans.binding.Bindings;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.util.Callback;

public class SettingsController extends Controller {

	private SettingsHandler sh;

	@FXML
	private AnchorPane yourSubjectsView, allSubjectsView;

	@FXML
	private Label txtError, txtUser;

	@FXML
	private Text feedback;

	@FXML
	private PasswordField passOldPass, passNewPass, passCNewPass;

	@FXML
	private Button btnSubmit, btnAdd, btnDelete;
	
	@SuppressWarnings("rawtypes")
	private TableView tv, tv2;

	public void changePassword() throws SQLException {
		String Pass = passNewPass.getText();
		String Pass2 = passCNewPass.getText();
		DatabaseHandler dbh = app.getDatabaseHandler();
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

	public void goDashboard() {
		app.makeDash();
	}

	public void goSettings() {
		app.makeSettings();
	}

	public void addSubjects() throws SQLException {
		ArrayList<String> selectedItems = getAllItems();
		System.out.println(selectedItems);
		for (int i = 0; i < selectedItems.size(); i++) {
			SettingsHandler.addSubjects(app.getDatabaseHandler(), selectedItems.get(i));
		}
		updateTableViews();
	}

	public void deleteSubjects() throws SQLException {
		ArrayList<String> selectedItems = getYourItems();
		System.out.println(selectedItems);
		for (int i = 0; i < selectedItems.size(); i++) {
			SettingsHandler.deleteSubjects(app.getDatabaseHandler(), selectedItems.get(i));
		}
		updateTableViews();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void clearSelections(TableView tv, TableView tv2) {
		tv.setRowFactory(new Callback<TableView, TableRow>() {
			public TableRow call(TableView tv) {
				final TableRow row = new TableRow<>();
				row.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent e) {
						final int index = row.getIndex();
						if(index >= 0 && index < tv.getItems().size() && tv.getSelectionModel().isSelected(index)) {
							tv.getSelectionModel().clearSelection();
							e.consume();
						}
						tv2.getSelectionModel().clearSelection();
					}
				});
				return row;
			}
		});
	}

	public ArrayList<String> getYourItems() {
		ArrayList<String> items = new ArrayList<String>();
		for (int i = 0; i < this.tv.getSelectionModel().getSelectedItems().size(); i++) {
			items.add(this.tv.getSelectionModel().getSelectedItems().get(i).toString().split(",")[0].substring(1));
		}

		return items;
	}
	
	public ArrayList<String> getAllItems() {
		ArrayList<String> items = new ArrayList<String>();
		for (int i = 0; i < this.tv2.getSelectionModel().getSelectedItems().size(); i++) {
			items.add(this.tv2.getSelectionModel().getSelectedItems().get(i).toString().split(",")[0].substring(1));
		}

		return items;
	}
	
	public void updateTableViews() {
		sh = new SettingsHandler();
		ResultSet rs = null;
		ResultSet rs2 = null;
		try {
			rs = sh.getUserSubjects(app.getDatabaseHandler());
			rs2 = sh.getAllSubjects(app.getDatabaseHandler());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		tv = ViewMaker.makeTable(rs, Arrays.asList("Code", "Coursename"));
		tv2 = ViewMaker.makeTable(rs2, Arrays.asList("Code", "Coursename"));
		yourSubjectsView.getChildren().add(tv);
		allSubjectsView.getChildren().add(tv2);
		
		if (yourSubjectsView.getChildren().isEmpty()) {
			txtError.setVisible(true);
		}
		
		btnDelete.disableProperty().bind(Bindings.isEmpty(tv.getSelectionModel().getSelectedItems()));
		btnAdd.disableProperty().bind(Bindings.isEmpty(tv2.getSelectionModel().getSelectedItems()));
	}

	@SuppressWarnings("unchecked")
	@Override
	public void onLoad() {
		txtUser.setText(txtUser.getText() + User.getUsername());
		updateTableViews();

		clearSelections(tv, tv2);
		clearSelections(tv2, tv);
	}
}
