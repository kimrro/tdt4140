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
		String replie = null;
		String uText = input.getText();
		
		String TEXT = uText.toLowerCase();
		String part[] = TEXT.split(" ");
		int n = part.length;
		
		String subjects = ("\n" + "TDT4140 Databases \n" 
							+ "TDT4100 Human M I \n" 
							+ "TDT4100 Objektorientert programmering");
		
		for (int i = 0; i == n - 1; i++) {
			
			if ((part[i].contains("hello"))){
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
			
			else if (TEXT.contains("what subjects do i have") || part[i].contains("subj")) {
				int reply_decider = (int) (Math.random()* 2 + 1);
				if (reply_decider== 1) {
					replie = "You have the subjects: \n" + subjects;
				}
				else if (reply_decider==2) {
					replie = "Currently, you are attending these classes: \n" + subjects;
				}
				break;
			}
			
			else if ((part[i].contains("how")) && (part[i + 1].contains("are")) && (part[i + 2].contains("you"))){
				int reply_decider = (int) (Math.random() * 3 + 1);
				if (reply_decider == 1) {
					replie = "I'm doing well, thanks";
				}
				else if (reply_decider == 2){
					replie = "Not too bad";
				}
				else if(reply_decider == 3) {
					replie = "Very well thank you!";
				}
				break;
			}
			
			else if ((part[i].contains("help")) || part[i].contains("commands")) {
					replie = "Possible commands are: \n \n" + "1: What subjects do i have? \n"+ "2: How are you? \n" + "3: What is the weather like today? \n" + "4: Clear the chat field \n";
			}
			
			else if ((part[i].contains("h")) && part[i].contains("e") && part[i].contains("l") && part[i].contains("p")) {
				replie = "Did you mean 'help'?";
			}
			
			else if ((part[i].contains("c")) && part[i].contains("o") && part[i].contains("m") && part[i].contains("n") && part[i].contains("d")) {
				replie = "Did you mean 'commands'?";
			}
				
			else if ((part[i].contains("clear"))) {
				//Runtime.getRuntime().exit(0);
				//area.clear();
				replie = "Cleared!";
				area.setText("");
				break;
			}
		
			else {
				int reply_decider = (int) (Math.random() * 4 + 1);
				if(reply_decider == 1){
					replie = "I didn't get that, try 'help' to show commands";
				}
				else if(reply_decider == 2){
					replie = "Please rephrase that or type 'commands' to show commands";
				}
				else if(reply_decider == 3){
					replie = "Type 'help' to show commands";
				}
				else if (reply_decider == 4) {
					replie = "'help' will show you some usefull comands =D";
				}
			}
		}
		
		if (part[0] == "clear") {
			input.setText("");
			area.setText("");
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
