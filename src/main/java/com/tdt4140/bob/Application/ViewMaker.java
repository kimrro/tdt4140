package com.tdt4140.bob.Application;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

import java.sql.ResultSet;
import java.util.List;


public class ViewMaker {

    public static TableView makeTable(ResultSet rs, List<String> labels) {
        TableView<ObservableList> table = new TableView<>();
        table.setEditable(false);
        ObservableList<ObservableList> data = FXCollections.observableArrayList();
        try {
            //Add table columns dynamically
            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                final int j = i; // used in string property down below
                TableColumn<ObservableList, String> col = new TableColumn<>(labels.get(i)); //sets column label
                col.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().get(j).toString()));
                table.getColumns().add(col);
            }
            while (rs.next()) {
                //Iterate Row
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    //Iterate Column
                    row.add(rs.getString(i));
                }
                data.add(row);
            }
            table.setItems(data);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error building data.");
        }
        
        AnchorPane.setTopAnchor(table, 0.0);
        AnchorPane.setBottomAnchor(table, 0.0);
        AnchorPane.setLeftAnchor(table, 0.0);
        AnchorPane.setRightAnchor(table, 0.0);
        return table;
    }

}