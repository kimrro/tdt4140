package com.tdt4140.bob.JavaFX.Controllers;

 import java.awt.Checkbox;
import java.sql.ResultSet;

import com.tdt4140.bob.Application.DatabaseHandler;
import com.tdt4140.bob.JavaFX.Bob;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.sql.SQLException;
import java.util.Arrays;

import com.tdt4140.bob.Application.ViewMaker;
import com.tdt4140.bob.Application.Admin.AdminHandler;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;



public class AdminController extends Controller {
	
		private AdminHandler ah;

		@FXML
		private Pane subjectView;
		
		@FXML
		private Label txtError;

		@Override
		public void onLoad() {
			System.out.println("Test");
			ah = new AdminHandler();
			ResultSet rs = null;
			try {
				rs = ah.getSubjects(app.getDatabaseHandler());
			} catch (SQLException e) {
				e.printStackTrace();
			}
			subjectView.getChildren().add(ViewMaker.makeTable(rs, Arrays.asList("Code", "Coursename")));
			
			if(subjectView.getChildren().isEmpty()) {
				txtError.setVisible(true);
			}
		}

	}
