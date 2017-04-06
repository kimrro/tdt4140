package com.tdt4140.bob.JavaFX.Controllers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import com.tdt4140.bob.Application.ViewMaker;
import com.tdt4140.bob.Application.Admin.AdminHandler;
import com.tdt4140.bob.Application.Subjects.SettingsHandler;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;

public class AdminController extends Controller {

	@FXML
	private TabPane tabPane = new TabPane();

	@FXML
	private Label txtError;

	@FXML
	private Button btnBack;

	private SettingsHandler sh;
	private AdminHandler ah;

	/**
	 * A function to return to dashboard/chat application.
	 * @author KimRobin
	 */
	public void goBack() {
		app.makeDash();
	}

	/**
	 * A function that runs automatically when the view is opened.
	 * @author KimRobin
	 */
	@Override
	public void onLoad() {
		sh = new SettingsHandler();
		ah = new AdminHandler();
		ResultSet rs = null;
		ResultSet rs2 = null;
		//Gets all subjects a user has
		try {
			rs = sh.getUserSubjects(app.getDatabaseHandler());
		} catch (SQLException e) {
			e.printStackTrace();
		}

		//Makes tabs for all subjects a user has
		try {
			ArrayList<String> code = new ArrayList<String>();
			while (rs.next()) {
				Tab tab = new Tab();
				tab.setText(rs.getString("code"));
				tabPane.getTabs().add(tab);

				code.add(rs.getString("code"));
			}
			
			int tabIndex = 0;
			
			//Adds content (TableView) to each tab generated. A TableView for lecturers to see most asked questions.
			while (!code.isEmpty()) {
				rs2 = ah.getSubjects(app.getDatabaseHandler(), code.get(0));
				AnchorPane pane = new AnchorPane();
				pane.getChildren().add(ViewMaker.makeTable(rs2, Arrays.asList("Keyword", "Frequency")));
				tabPane.getTabs().get(tabIndex).setContent(pane);
				tabIndex++;
				code.remove(0);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}