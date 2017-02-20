package com.tdt4140.bob.Chat;

import javax.swing.JFrame;


	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;

	import javax.swing.JFrame;
	import javax.swing.JTextArea;
	import javax.swing.JTextField;

import com.mysql.fabric.xmlrpc.base.Array;


	public class bot extends JFrame {

		//Typing Area:
		private JTextField txtEnter = new JTextField();
		
		//Chat Area:
		private JTextArea txtChat = new JTextArea();
		
		public bot() {
			//Frame Attributes:
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setSize(600, 600);
			this.setVisible(true);
			this.setResizable(false);
			this.setLayout(null);
			this.setTitle("BoB the chat bot");
			
			//txtEnter Attributes:
			txtEnter.setLocation(2, 540);
			txtEnter.setSize(590, 30);
			
			//txtEnter Action Event:
			txtEnter.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					String uText = txtEnter.getText();
					txtChat.append("You: " + uText + "\n");
					String subjects = ("TDT4140 Databases, TDT4100 Human M I");
					
					if(uText.contains("hello")){
						botSay("Hello there!");
					}
					else if (uText.contains("what subjects do i have?")) {
						int reply_decider = (int) (Math.random()*2+1);
						if (reply_decider== 1) {
							botSay("You have the subjects:" + subjects);
						}
						else if (reply_decider==2) {
							botSay("Currently, you are attending these classes:" + subjects);
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
					else{
						int reply_decider = (int) (Math.random()*3+1);
						if(reply_decider == 1){
							botSay("I didn't get that");
						}
						else if(reply_decider == 2){
							botSay("Please rephrase that");
						}
						else if(reply_decider == 3){
							botSay("Press the 'help' button to show commands");
						}
					}
					txtEnter.setText("");
				}
			});
			
			//txtChat Attributes:
			txtChat.setLocation(15, 5);
			txtChat.setSize(560, 510);
			txtChat.setEditable(false);
			
			//Add Items To Frame:
			this.add(txtEnter);
			this.add(txtChat);
		}
		
		public void botSay(String s){
			txtChat.append("BOB: " + s + "\n");
		}
		
		public void list_of_commands(String s) {
			txtChat.append("how are you?" + "\n"+ "what subjects do i have?" );
		}
		
		public static void main(String[] args){
			new bot();
		}}

	