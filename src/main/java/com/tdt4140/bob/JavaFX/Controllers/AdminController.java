package com.tdt4140.bob.JavaFX.Controllers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import com.tdt4140.bob.Application.DatabaseHandler;
import com.tdt4140.bob.Application.ViewMaker;
import com.tdt4140.bob.Application.Admin.AdminHandler;
import com.tdt4140.bob.Application.Curriculum.CurriculumHandler;
import com.tdt4140.bob.Application.Settings.SettingsHandler;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.text.Text;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

public class AdminController extends Controller {
	@FXML
	private Text feedback;
	
	@FXML
	private TabPane tabPane1,tabPane2 = new TabPane();

	@FXML
	private Label txtError;
	
	@FXML
	private TextField txtCurriculum,txtPage;

	@FXML
	private Button btnAdd, btnGrant;
	
	@FXML
	private ImageView btnBack;
	
	/** 
     * importing all the handlers
     * @author 			lajohnso
     */

	private SettingsHandler sh = new SettingsHandler();
	private AdminHandler ah = new AdminHandler();
	private DatabaseHandler dbh;
	private CurriculumHandler ch = new CurriculumHandler();


	/** 
     * Method activated when "logo"button is clicked.
     * <p>
     * Redirects the user to chat page .
     *
     * @author 			lajohnso
     */
	public void goBack() {
		app.makeDash();
	}
	
	/**
	 * Function to redirect user to "grant user access"-view
	 * @author KimRobin
	 */
	public void goUserAccess() {
		app.makeUserAccess();
	}
	
	/** 
     * Method is activated when "add" button is clicked.
     * <p>
     * Commits a new keyword to the database (curriculum) and confirms/declines input with user feedback
     * using the "inputControlIsValid()" method.
     *
     * @author 			lajohnso
     */
	
	public void addCurriculum() throws SQLException{
		String page = txtPage.getText();
		String keyword = txtCurriculum.getText();
		String code = tabPane2.getSelectionModel().getSelectedItem().getText();
		if (inputControlIsValid()) {
			ch.addKeyword(app.getDatabaseHandler(), page, keyword, code);
			System.out.println("hei");
			System.out.println(code);
			feedback.setFill(Color.GREEN);
			feedback.setText("You just added a keyword.");
			txtPage.clear();
			txtCurriculum.clear();
			}
		}
	
	/** 
     * Method is called by "addCurriculum()".
     * <p>
     * Tests if the new input is valid.
     *
     * @author 			lajohnso
     */
	
	public boolean inputControlIsValid (){
		if (txtCurriculum.getLength()==0) {
			feedback.setFill(Color.RED);
			feedback.setText("You need to write a keyword.");
			return false;
			}
		else {
			if (txtPage.getLength()==0) {
				feedback.setFill(Color.RED);
				feedback.setText("You need to enter page(s).");;
				return false;
				
			}
			}
		return true;
}	


	/** 
     * Method activated on page/stage load.
     * <p>
     * Generates the two tableViews and imports data from database. 
     *
     * @author 			lajohnso & kimrro
     */
	@Override
	public void onLoad() {
		
		ResultSet rs = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;
		
		//tableView1 (search frequency) generator
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
				tabPane1.getTabs().add(tab);

				code.add(rs.getString("code"));
			}
			
			int tabIndex = 0;
			
			//Adds content (TableView) to each tab generated. A TableView for lecturers to see most asked questions.
			while (!code.isEmpty()) {
				rs2 = ah.getSubjects(app.getDatabaseHandler(), code.get(0));
				AnchorPane pane = new AnchorPane();
				pane.getChildren().add(ViewMaker.makeTable(rs2, Arrays.asList("Keyword", "Frequency")));
				tabPane1.getTabs().get(tabIndex).setContent(pane);
				tabIndex++;
				code.remove(0);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//tableView2(curriculum) generator
		
		try {
			rs = sh.getUserSubjects(app.getDatabaseHandler());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			ArrayList<String> code = new ArrayList<String>();
			while (rs.next()) {
				Tab tab = new Tab();
				tab.setText(rs.getString("code"));
				tabPane2.getTabs().add(tab);

				code.add(rs.getString("code"));
			}
			
			int tabIndex = 0;
			
			while (!code.isEmpty()) {
				rs3 = ch.getPageKey(app.getDatabaseHandler(), code.get(0));
				AnchorPane pane = new AnchorPane();
				pane.getChildren().add(ViewMaker.makeTable(rs3, Arrays.asList("Topics", "Page(s)")));
				tabPane2.getTabs().get(tabIndex).setContent(pane);
				tabIndex++;
				code.remove(0);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}