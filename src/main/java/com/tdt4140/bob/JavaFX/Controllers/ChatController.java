package com.tdt4140.bob.JavaFX.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ChatController extends Controller{
	
	@FXML
	private TextArea area;
	@FXML
	private TextField input;
	@FXML
	private Button button;
	
	public void chatClicked() {
		String replie = "ERROR";
		String uText = input.getText();
		
		String TEXT = uText.toLowerCase();
		String part[] = TEXT.split(" ");
//		int n = part.length - 1; //For-løkke
		
		String subjects = ("\n" + "TDT4140 Databases \n" 
							+ "TDT4100 Human M I \n" 
							+ "TDT5000 Objektorientert programmering");
		
//		String subjects = (Hente fra database);
		
		String lecturer = ("Hente fra database!!");
		
		if ((part[0].contains("hello")) || (part[0].contains("hi"))){
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
		
		else if (part[0].contains("I") && part[1].contains("am") || (part[0].contains("i") && part[0].contains("m"))) {
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
				replie = "Not too bad";
			}
			else if(reply_decider == 3) {
				replie = "Very well thank you!";
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
				replie = "Possible commands are: \n \n" + "1: How are you? \n"+ "2: What subjects do i have? \n" + "3: 'lecturer' + 'SUBJECTCODE' \n" + "4: Clear the chat field \n";
		}
		
		else if ((part[0].contains("h")) && part[0].contains("e") && part[0].contains("l") && part[0].contains("p") && part[0] != "help") {
			replie = "Did you mean 'help'?";
		}
		
		else if ((part[0].contains("c")) && part[0].contains("o") && part[0].contains("m") && part[0].contains("n") && part[0] != "commands") {
			replie = "Did you mean 'commands'?";
		}
		
		else if ((part[0].contains("c")) && part[0].contains("l") && part[0].contains("e") && part[0].contains("a") && part[0].contains("r") && part[0] != "clear") {
			replie = "Did you mean 'clear'?";
		}
	
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

	public boolean isValidCommand(String uText) {
		if (uText.contains("help") || uText.contains("exit" ) || uText.contains("what subjects do I have?" ) || uText.contains("hello" ) ) {
			return true;
		}
		return false;
	}
	
}
