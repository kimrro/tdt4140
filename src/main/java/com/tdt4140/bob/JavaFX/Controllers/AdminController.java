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


public class AdminController extends Controller {
		private Bob bob;
		private DatabaseHandler dbh;
		@FXML 
		private TableView<SearchResults> SR = new TableView();
		
		@FXML private TableView SearchResults;
		
		@FXML private Button toggleSettings;
		
		@FXML private Checkbox c1,c2,c3;
		
	

	    private ObservableList<ObservableList> data;
	    private TableView tableview;

	 
	    public void initialize(URL,Location,ResourceBundle resources){
	   
	      	ResultSet rs = null;

	            for(int i=0 ; i<rs.getMetaData().getColumnCount(); i++){
	                final int j = i;                
	                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i+1));
	                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){                    
	                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {                                                                                              
	                        return new SimpleStringProperty(param.getValue().get(j).toString());                        
	                    }                    
	                });

	                tableview.getColumns().addAll(col); 
	                System.out.println("Column ["+i+"] ");
	            }

	         
	            while(rs.next()){
	                ObservableList<String> row = FXCollections.observableArrayList();
	                for(int i=1 ; i<=rs.getMetaData().getColumnCount(); i++){
	                    row.add(rs.getString(i));
	                }
	                System.out.println("Row [1] added "+row );
	                data.add(row);

	            }

	            tableview.setItems(data);
	        try {
	        	
	        }
	          catch(SQLException e){
	              e.printStackTrace();
	              System.out.println("Error on Building Data");             
	          }}
	
    
	          private void toggleSettings() {
	        	  app.makeSettings();
	          }
	}	


