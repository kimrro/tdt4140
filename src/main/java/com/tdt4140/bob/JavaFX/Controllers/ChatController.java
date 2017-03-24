package com.tdt4140.bob.JavaFX.Controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import org.apache.commons.lang3.text.WordUtils;
import org.json.JSONException;
import org.json.JSONObject;

import com.tdt4140.bob.JavaFX.Controllers.Login.User;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
	private Button button;
	@FXML
	private TextArea chat1;
	@FXML
	private TextArea chat2;
	@FXML
	private ImageView btnSettings;

public void chatClicked() throws IOException {
		String replie = "ERROR";
		String uText = input.getText();
		
		String TEXT = uText.toLowerCase();
		String part[] = TEXT.split(" ");
		int n = part.length;
		
		String subjects = ("\n" + "TDT4140 Databases \n" 
							+ "TDT4100 Human M I \n" 
							+ "TDT5000 Objektorientert programmering");
		
		String lecturer = ("Hente fra database!");
		
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
			
			if(wikipedia(input1).equals("0")) {
				if (wikipedia(input2).equals("0")){
					replie = "Please rephrase! I couldnt find what you where looking for :(";
				} else {
					replie = "I found this on wikipedia: " + input2 + "\n" + wikipedia(input2);
				}
			} else {
				replie = "I found this on wikipedia: " + input2 + "\n" + wikipedia(input1);
			}
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
		
		else if (TEXT.equals("what subjects do i have") || TEXT.equals("what subjects do i have?") || TEXT.contains("subj")) {
			int reply_decider = (int) (Math.random()* 2 + 1);
			if (reply_decider== 1) {
				replie = "You have the subjects: \n" + subjects;
			}
			else if (reply_decider==2) {
				replie = "Currently, you are attending these classes: \n" + subjects;
			}
		}
	
		else if (part[0].equals("lecturer")) {
			if (part[1].equals("tdt4140")) {
				replie = "In " + part[1] + " you have the lecturer: \n" + lecturer;
			}
			else if (part[1].equals("tdt4100")) {
				replie = "In " + part[1] + " you have: \n" + lecturer;
			} else if (part[1].equals("tdt5000")) {
				replie = "In " + part[1] + " you have: \n \n" + lecturer;
			} else {
				replie = "You have either written the subject code wrong \n "
						+ "	  Your wrote: " + part[1];
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
		
		else if ((part[0].contains("clear"))) {
			int reply_decider = (int) (Math.random() * 2 + 1);
			if(reply_decider == 1){
				replie = "Cleared!";
			}
			else if(reply_decider == 2){
				replie = "Bye bye text =)";
			}
		}
		
		else if ((part[0].contains("help")) || part[0].contains("commands")) {
				replie = "Possible commands are: \n \n" 
						+ "1: How are you? \n"
						+ "2: What subjects do i have (subjects)? \n" 
						+ "3: 'lecturer' + 'SUBJECTCODE' \n" 
						+ "4: Clear the chat field (clear) \n" 
						+ "5: Ask me something";
		}
		
		else if (part[0].contains("h") && part[0].contains("e") && part[0].contains("l") && part[0].contains("p") && part[0] != "help") {
			replie = "Did you mean 'help'?";
		}
		
		else if (part[0].contains("c") && part[0].contains("o") && part[0].contains("m") && part[0].contains("n") && part[0] != "commands") {
			replie = "Did you mean 'commands'?";
		}
		
		else if (part[0].contains("c") && part[0].contains("l") && part[0].contains("e") && part[0].contains("a") && part[0].contains("r") && part[0] != "clear") {
			replie = "Did you mean 'clear'?";
		}
		
		else if (part[0].contains("w") && part[0].contains("h") && part[0].contains("a") && part[0] != "what") {
			replie = "Did you mean to ask me a question?";
		}
		
//		else if (part[0].contains("what") && !(part[1].equals("is")) && (part[2] == "")) {
//			replie = "Did you mean to ask me a question?";
//		}
	
		else {
			int reply_decider = (int) (Math.random() * 4 + 1);
			if(reply_decider == 1) {
				replie = "I didn't get that, try 'help' to show commands";
			}
			else if(reply_decider == 2) {
				replie = "Please rephrase that or type 'commands' to show commands";
			}
			else if(reply_decider == 3) {
				replie = "Type 'help' to show commands";
			}
			else if (reply_decider == 4) {
				replie = "'help' will show you some usefull comands =D";
			}
		}
	
		if (TEXT.contains("clear")) {
				input.setText("");
				area.setText("");
				chatArea(uText, replie);
		} else {
				input.setText("");
				chatArea(uText, replie);
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
		if (uText.contains("help") || uText.contains("exit") || uText.contains("what subjects do I have?")
				|| uText.contains("hello")) {
			return true;
		}
		return false;
	}
	

	public void showSettings() {
		app.makeSettings();
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
