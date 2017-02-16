package com.tdt4140.bob.Application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Login extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
    	
        GridPane grid = new GridPane();
        GridPane pane = new GridPane();
        
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10); //horisontal gap i pixler
        grid.setVgap(10); //vertikal gap 
        grid.setPadding(new Insets(10, 20, 10, 20)); //padding top/right/bottom/left
        
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(40);
        
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(60);
        
        grid.getColumnConstraints().addAll(column1, column2);
        
        //Text scenetitle = new Text("BoB");
        //scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        //grid.add(scenetitle, 0, 0); 
        
        ImageView bob = new ImageView(getClass().getResource("/com/tdt4140/bob/Application/bob.png").toExternalForm());
        bob.setFitWidth(70);
        bob.setPreserveRatio(true);
        grid.add(bob, 0, 0); //kolonne, rad

        Label userName = new Label("Brukernavn:");
        grid.add(userName, 0, 1);
        
        final TextField brukernavnBox = new TextField();
        String brukernavn = brukernavnBox.getText();
        brukernavnBox.setText(brukernavn);
        grid.add(brukernavnBox, 1, 1);

        Label pw = new Label("Passord:");
        grid.add(pw, 0, 2);

        final PasswordField passordBox = new PasswordField();
        String passord = passordBox.getText();
        passordBox.setText(passord);
        grid.add(passordBox, 1, 2);
       
        Button btn = new Button("Logg inn");
        btn.setId("button");
        //HBox hbBtn = new HBox(10);
        //hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        //hbBtn.getChildren().add(btn);
        grid.add(btn, 1, 4);

        final Text actiontarget = new Text();
        grid.add(actiontarget, 0, 5);

        btn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
            	if ((brukernavnBox.getText() != null) && (passordBox.getText() != null)) {
            		//actiontarget.setText("Riktig brukernavn og passord!");
            		thestage.setScene(scene2);
            		
            	} else {
                	actiontarget.setFill(Color.FIREBRICK);
            		actiontarget.setText("Feil brukernavn eller passord "); }
            }
        });

        Scene scene1 = new Scene(grid, 320, 300);
        Scene scene2 = new Scene(pane, 320, 300);
        
        scene1.getStylesheets().add("/com/tdt4140/bob/Application/style.css");
        primaryStage.setTitle("Login to BoB");
        primaryStage.setScene(scene1);
        primaryStage.show();
       
    }
}