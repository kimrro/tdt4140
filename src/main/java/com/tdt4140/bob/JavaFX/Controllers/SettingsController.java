package com.tdt4140.bob.JavaFX.Controllers;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import com.tdt4140.bob.Application.DatabaseHandler;
import com.tdt4140.bob.Application.ViewSubjects;
import com.tdt4140.bob.JavaFX.Bob;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class SettingsController extends Controller implements Initializable {

	private ViewSubjects vs;
	private DatabaseHandler dbh;
	private Bob bob;
	
	@FXML
	private TableView subjectView;
	
	@FXML
	private TableColumn<ObservableList, String> code, coursename;

	public void showSubjects() {
		this.dbh = bob.getDatabaseHandler();

		TableView<ObservableList> tableView = null;
		tableView.setEditable(false);
		ObservableList<ObservableList> data = FXCollections.observableArrayList();
		
		code = new TableColumn<ObservableList, String>();
		try {
			ResultSet rs = vs.getSubjects(this.dbh);
			while (rs.next()) {
				code.setCellValueFactory(new PropertyValueFactory("hardkoda"));
			}
			
			tableView.getColumns().add(code);
		} catch (Exception e) {

		}
	}

	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		showSubjects();
	}
}
