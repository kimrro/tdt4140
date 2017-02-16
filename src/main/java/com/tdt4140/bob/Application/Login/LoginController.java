package com.tdt4140.bob.Application.Login;

import java.awt.Button;
import java.awt.TextField;
import java.awt.event.ActionEvent;

import javafx.scene.text.Text;

public class LoginController {
	
	protected final TextField username;
	protected final TextField password;
	protected final Button button;
	protected final Text actionTarget;
	
	protected abstract void buttonClicked(ActionEvent event);
	

}
