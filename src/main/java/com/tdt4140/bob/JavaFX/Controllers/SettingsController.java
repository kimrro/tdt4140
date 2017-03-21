package com.tdt4140.bob.JavaFX.Controllers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

import com.tdt4140.bob.Application.ViewMaker;
import com.tdt4140.bob.Application.Subjects.SubjectsHandler;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class SettingsController extends Controller {

	private SubjectsHandler sh;

	@FXML
	private Pane subjectView;
	
	@FXML
	private Label txtError;

	@Override
	public void onLoad() {
		System.out.println("Test");
		sh = new SubjectsHandler();
		ResultSet rs = null;
		try {
			rs = sh.getSubjects(app.getDatabaseHandler());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		subjectView.getChildren().add(ViewMaker.makeTable(rs, Arrays.asList("Code", "Coursename")));
		
		if(subjectView.getChildren().isEmpty()) {
			txtError.setVisible(true);
		}
	}

}
