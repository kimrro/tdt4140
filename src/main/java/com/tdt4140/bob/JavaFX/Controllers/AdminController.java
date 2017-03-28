package com.tdt4140.bob.JavaFX.Controllers;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

import com.tdt4140.bob.Application.ViewMaker;
import com.tdt4140.bob.Application.Admin.AdminHandler;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;



public class AdminController extends Controller {
	
		private AdminHandler ah;

		@FXML
		private Pane subjectView;
		
		@FXML
		private Label txtError;
		
		@FXML
		private Button btnBack;

		public void goBack() {
			app.makeDash();
		}
		
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
			subjectView.getChildren().add(ViewMaker.makeTable(rs, Arrays.asList("Keyword","Search Frequency")));
			
			if(subjectView.getChildren().isEmpty()) {
				txtError.setVisible(true);
			}
		}

	}