package com.tdt4140.bob.JavaFX.Controllers;

//import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.scene.text.Text;

public class ChatController extends Controller {
	
	private Text chat1;
	private Text chat2;
	private TextField text;
	
	protected void ButtonClicked(ActionEvent event) {
		String uText = text.getText();
		chat1.setText("You: " + uText + "\n");
		
		String subjects = ("TDT4140 Databases,"+"\n" + " TDT4100 Human M I");
		
		if(uText.contains("hello")){
			botSay("Hello there!");
		}
		else if (uText.contains("what subjects do i have?")) {
			int reply_decider = (int) (Math.random()*2+1);
			if (reply_decider== 1) {
				botSay("You have the subjects:" + subjects);
			}
			else if (reply_decider==2) {
				botSay("Currently, you are attending these classes:"+ "\n" + subjects);
			}
	//terminates the application	
		else if (uText.contains("exit")) {
			Runtime.getRuntime().exit(0);
			}
		}
		
		else if(uText.contains("how are you?")){
			int decider = (int) (Math.random()*2+1);
			if(decider == 1){
				botSay("I'm doing well, thanks");
			}
			else if(decider == 2){
				botSay("Not too bad");
			}
		}
		
		else if(uText.contains("help")){
				botSay("Possible commands are: \n"+ "\n" + "1: what subjects do i have? " + "\n"+ "2: how are you?" + "3: what is the weather like today? \n");
			}
	
			
		else{
			int reply_decider = (int) (Math.random()*3+1);
			if(reply_decider == 1){
				botSay("I didn't get that");
			}
			else if(reply_decider == 2){
				botSay("Please rephrase that");
			}
			else if(reply_decider == 3){
				botSay("Type the 'help' button to show commands");
			}
		}
		text.setText("");
	}
	
	public void botSay(String s){
		chat2.setText("BOB: " + s + "\n");
	}

}
