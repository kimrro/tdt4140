package com.tdt4140.bob.JavaFX.Controllers;
import static org.junit.Assert.*;

import org.junit.Test;

import com.tdt4140.bob.Application.Admin.AdminHandler;
import com.tdt4140.bob.Application.Subjects.SettingsHandler;
import com.tdt4140.bob.JavaFX.Bob;
import com.tdt4140.bob.JavaFX.Controllers.*;

import javafx.fxml.FXML;
import javafx.scene.control.TabPane;
	
public class AdminControllerTest extends junit.framework.TestCase {

	private SettingsHandler sh;
	private AdminHandler ah;
	
	
	AdminController ac= new AdminController();
	
	Bob test = new Bob();
	public void test() {
		
		ah = new AdminHandler();
			
	}
}