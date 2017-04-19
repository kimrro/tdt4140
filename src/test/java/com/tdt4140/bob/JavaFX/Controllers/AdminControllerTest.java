package com.tdt4140.bob.JavaFX.Controllers;
import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import com.tdt4140.bob.Application.DatabaseHandler;
import com.tdt4140.bob.Application.Admin.AdminHandler;
import com.tdt4140.bob.Application.Curriculum.CurriculumHandler;
import com.tdt4140.bob.Application.Subjects.SettingsHandler;
import com.tdt4140.bob.JavaFX.Bob;
import com.tdt4140.bob.JavaFX.Controllers.*;
import static org.junit.Assert.*;
import java.io.IOException;
import org.junit.Test;
import com.tdt4140.bob.JavaFX.Controllers.*;
import javafx.fxml.FXML;
import javafx.scene.control.TabPane;
	
public class AdminControllerTest extends junit.framework.TestCase {

	AdminController ac= new AdminController();
	private SettingsHandler sh;
	private AdminHandler ah;
	private DatabaseHandler dbh;
	
	@Test
		public void test1() throws IOException {
			assertTrue(ac.inputControlIsValid());
			assertTrue(ac.inputControlIsValid());
	}
			
	@Test
		public void test2() throws IOException {
			assertTrue(ac.inputControlIsValid());
			assertTrue(ac.inputControlIsValid());
		}
			
	@Test
		public void test3() throws IOException {
			assertTrue(ac.inputControlIsValid());
			assertTrue(ac.inputControlIsValid());
	}
	
	@Test
		public void test4() throws IOException {
		assertTrue(ac.inputControlIsValid());
		assertTrue(ac.inputControlIsValid());
}
			
}