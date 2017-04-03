package com.tdt4140.bob.JavaFX.Controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.commons.lang3.text.WordUtils;
import org.json.JSONException;
import org.json.JSONObject;

import com.sun.prism.paint.Color;
import com.tdt4140.bob.Application.DatabaseHandler;
import com.tdt4140.bob.Application.Chat.ChatHandler;
import com.tdt4140.bob.JavaFX.Controllers.Login.User;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class ChatController extends Controller {

	@FXML
	private TextArea area, chat;
	@FXML
	private TextField input, text;
	@FXML
	private Button button, btnAdmin, btnLogout, commands, questions;
	@FXML
	private TextArea chat1;
	@FXML
	private TextArea chat2;
	@FXML
	private ImageView btnSettings;
	@FXML
	private ComboBox<String> choiceSubject = new ComboBox<>();
	@FXML
	private Text labelText;
	
	private DatabaseHandler dbh;
	private ArrayList<String> subjects = new ArrayList<String>();
	private String pick;
	private ResultSet rs = null;
	private ResultSet rs1 = null;
	private String username;
	
	public void onLoad() {
		username = User.getUsername();
		pick = "";
		dbh = app.getDatabaseHandler();
		
		ResultSet data = null;
		try {
			data = ChatHandler.getSubjects(dbh, username);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			while (data.next()) {
				subjects.add(data.getString("coursename"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		for (int i = 0; i < subjects.size(); i++) {
			choiceSubject.getItems().add(subjects.get(i));
		}
		
	}
	
	public void subjectPick() {
		pick = choiceSubject.getValue();
	}

	public void showCommands() {
		area.appendText("BOB: Possible commands are: \n" 
								+ "1: /subjects \n" 
								+ "2: /lecturer \n" 
								+ "3: /clear \n" 
								+ "4: Ask me something \n\n");
	}
	
	public void showLastQuestions() throws SQLException{
		int i = 1;
		ResultSet output = ChatHandler.getQuestion(dbh, username);
		ArrayList<String> questions = new ArrayList<String>();
		while (output.next()) {
			questions.add(i + ": " + output.getString("question"));
			i++;
		}
		area.appendText("BOB: Your last questions: \n" + questions + "\n\n"); 
	}
	
	public void chatClicked() throws IOException, SQLException {
		String replie = "ERROR";
		String uText = input.getText();
		
		String TEXT = uText.toLowerCase();
		String part[] = TEXT.split(" ");
		int n = part.length;
		
		if (!(pick.equals(""))) {
			if ((TEXT.contains("hello")) || (TEXT.contains("hi"))){
				int reply_decider = (int) (Math.random() * 3 + 1);
				if (reply_decider == 1) {
						replie = "Hello there!";
				}
				else if (reply_decider == 2) {
					replie = "How you doin? ^^";
				}
				else if (reply_decider == 3) {
					replie = "Hello mate!";
				}
			}
			
			else if ((part[0].equals("what") || part[0].equals("who") || part[0].equals("where")) && part[1].contains("is")) {
				ArrayList<String> list = new ArrayList<String>();
				String subject = "";
				for (int i = 2; i < n; i++) {
					list.add(part[i]);
				}
				for (String s : list) {
				    subject += s + " ";
				}
				String input1 = subject;
				String input2 = WordUtils.capitalizeFully(subject);
				String code = null;
				String page = null;
				
				rs = ChatHandler.getSubjectCode(dbh, pick);
				if (rs.next()) {
					code = rs.getString("code");
				}
				
				rs1 = ChatHandler.getCurriculum(dbh, subject, code);
//				if (rs1.next()) {
//					page = rs1.getString("pages");
//				}
//				if(wikipedia(input1).equals("0") && (!(rs1.next()))) {
//					if (wikipedia(input2).equals("0")){
//						replie = "Please rephrase! I couldnt find what you where looking for :(";
//					} else {
//						ChatHandler.writeKeywords(dbh, subject, code);
//						ChatHandler.writeQuestion(dbh, TEXT, username);
//						
//						replie = "I found this on wikipedia: " + input2 + "\n" + wikipedia(input2);	
//					}
//				} else {
//					ChatHandler.writeKeywords(dbh, subject, code);
//					ChatHandler.writeQuestion(dbh, TEXT, username);	
//					if (rs1.next()) {
//						replie = "You can read about " + input2 + " in the curriculum on the pages: " + page 
//								+ "\nI also found this on wikipedia: " + "\n" + wikipedia(input2);
//					} else {
//						replie = "I found this on wikipedia: " + input2 + "\n" + wikipedia(input2);
//					}
//				}
				
				if (rs1.next()) {
					page = rs1.getString("pages");
					ChatHandler.writeKeywords(dbh, subject, code);
					ChatHandler.writeQuestion(dbh, TEXT, username);	
					if(wikipedia(input1).equals("0")) {
						replie = "You can read about " + input2 + " in the curriculum on the pages: " + page;
					} else {
						replie = "You can read about " + input2 + " in the curriculum on the pages: " + page 
								+ "\nI also found this on wikipedia: " + "\n" + wikipedia(input1);
					}
				} else {
					if(wikipedia(input1).equals("0")) {
						replie = "Please rephrase! I couldnt find what you where looking for :(";
					} else {
						ChatHandler.writeKeywords(dbh, subject, code);
						ChatHandler.writeQuestion(dbh, TEXT, username);	
						replie = "I found this on wikipedia: " + input2 + "\n" + wikipedia(input1);
					}
				}
				
			}
			
			else if (TEXT.equals("/subjects")){
				int reply_decider = (int) (Math.random()* 2 + 1);
				if (reply_decider== 1) {
					replie = "You have the subjects: \n" + subjects;
				}
				else if (reply_decider == 2) {
					replie = "Currently, you are attending these classes: \n" + subjects;
				}
			}
		
			else if (part[0].equals("/lecturer")) {
				ResultSet output = ChatHandler.getLecturer(dbh, pick);
				ArrayList<String> lecturer = new ArrayList<String>();
				while (output.next()) {
					lecturer.add(output.getString("fornavn") + " " + output.getString("etternavn"));
				}
				if (lecturer.isEmpty()) {
					replie = "I dont think there are registered any lecturers in that course..";
				} else {
					replie = "In " + pick + " teaches the lecturer: \n" + lecturer;
				}
			}
			
			else if ((part[0].equals("/clear"))) {
				int reply_decider = (int) (Math.random() * 2 + 1);
				if(reply_decider == 1){
					replie = "Cleared!";
				}
				else if(reply_decider == 2){
					replie = "Bye bye text =)";
				}
			}
			
			else if ((part[0].equals("/help")) || part[0].equals("/commands")) {
					replie = "Possible commands are: \n \n" 
							+ "1: /subjects \n" 
							+ "2: /lecturer \n" 
							+ "3: /clear \n" 
							+ "4: Ask me something";
			}
			
			else if (part[0].contains("h") && part[0].contains("e") && part[0].contains("l") && part[0].contains("p") && part[0] != "help") {
				replie = "Did you mean '/help'?";
			}
			
			else if (part[0].contains("c") && part[0].contains("o") && part[0].contains("m") && part[0].contains("n") && part[0] != "commands") {
				replie = "Did you mean '/commands'?";
			}
			
			else if (part[0].contains("c") && part[0].contains("l") && part[0].contains("e") && part[0].contains("a") && part[0].contains("r") && part[0] != "clear") {
				replie = "Did you mean '/clear'?";
			}
			
			else if (part[0].contains("w") && part[0].contains("h") && part[0].contains("a") && part[0] != "what") {
				replie = "Did you mean to ask me a question?";
			}

			else if (TEXT.contains("i am") || (part[0].contains("i") && part[0].contains("m"))) {
				int reply_decider = (int) (Math.random() * 2 + 1);
				if (reply_decider == 1) {
						replie = "Good for you!!";
				}
				else if (reply_decider == 2) {
					replie = "Nice to hear that!";
				}
			}
			
			else if (TEXT.contains("how are you") || ((part[0].contains("how")) && (part[1].contains("are")) && (part[2].contains("you")))) {
				int reply_decider = (int) (Math.random() * 3 + 1);
				if (reply_decider == 1) {
					replie = "I'm doing well thanks! And you?";
				}
				else if (reply_decider == 2){
					replie = "Not too bad, and for you?";
				}
				else if(reply_decider == 3) {
					replie = "Very well thank you! How is it going for you?";
				}
			}
		
			else {
				int reply_decider = (int) (Math.random() * 4 + 1);
				if(reply_decider == 1) {
					replie = "I didn't get that, try '/help' to show commands";
				}
				else if(reply_decider == 2) {
					replie = "Please rephrase that or type '/commands' to show commands";
				}
				else if(reply_decider == 3) {
					replie = "Type '/help' to show commands";
				}
				else if (reply_decider == 4) {
					replie = "'/help' will show you some usefull comands =D";
				}
			}
		
			if (TEXT.contains("/clear")) {
					input.setText("");
					area.setText("");
					chatArea(uText, replie);
			} else {
					input.setText("");
					chatArea(uText, replie);
			}
		} else {
			area.appendText("BOB: Please choose a subject! \n\n" );
		}

			
	}
	
	public void chatArea(String user ,String bot) {
		area.appendText("You: " + user + "\n" + "BOB: " + bot + "\n" + "\n");
	}

	public String wikipedia(String object) throws IOException{
//		String subject = "Ed Sheeran";
//		object = "world war";
//		ojbect = "RAM";
		System.out.println("Ble skerevet inn: " + object);
		URL url = new URL("https://en.wikipedia.org/w/api.php?action=query&prop=extracts&format=json&exsentences=1&exintro=&explaintext=&exsectionformat=plain&titles=" + object.replace(" ", "%20"));
		String text = "";
		try (BufferedReader br = new BufferedReader(new InputStreamReader(url.openConnection().getInputStream()))) {
		    String line = null;
		    while (null != (line = br.readLine())) {
		        line = line.trim();
		        if (true) {
		            text += line;
		        }
		    }		
		} 
		
		String extract = null;
		String check = null;

		try {
			System.out.println("text = " + text);
			JSONObject json = new JSONObject(text);
			JSONObject query = json.getJSONObject("query");
			JSONObject pages = query.getJSONObject("pages");
			for(String key: pages.keySet()) {
//			    System.out.println("key = " + key);
			    check = key;
			    JSONObject page = pages.getJSONObject(key);
			    extract = page.getString("extract");
//			    System.out.println("extract = " + extract);
			}
		} catch (JSONException e) {
			e.printStackTrace();
			return("0");
		}
		
//		System.out.println("Check: " + check);
		
		if (text.contains("missing")) {
			return("0");
		} else if (extract.contains("redirect") || check.equals("-1") ) {
			return("0");
		} else { 
			return (extract);
		}
		
	}
	
	public boolean isValidCommand(String uText) {
		if (uText.equals("/lecturer") || uText.equals("/clear") || uText.equals("/help") || uText.equals("/subjects")) {
			return true;
		}
		return false;
	}
	
	public void showSettings() {
		app.makeSettings();
	}
	
	public void showAdmin() {
		app.makeAdmin();
	}
	
	public void logOut() {
		User.resetUser();
		app.makeLogin();
	}

	public boolean isValidWiki(String object) throws IOException {
		String input1 = object;
		String input2 = WordUtils.capitalizeFully(object);
		
		if (!(wikipedia(input1).equals("0")) || !(wikipedia(input2).equals("0"))) {
			return true;
		} return false;
	}
	
	
}
