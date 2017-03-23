package com.tdt4140.bob.JavaFX.Controllers;

import java.sql.ResultSet;
import java.sql.SQLException;
import junit.framework.TestCase;

import com.tdt4140.bob.Application.Login.LoginHandler;
import com.tdt4140.bob.Application.DatabaseHandler;
import com.tdt4140.bob.JavaFX.Bob;

public class LoginTest extends TestCase {
	
	private DatabaseHandler dbh;
	private LoginHandler lh1, lh2;
	private String username1;
	private String username2;
	private String username3;
	private String notExist;
	
	Bob test = new Bob();
	
	public void testUsername() throws SQLException {
		lh1 = new LoginHandler();
		dbh = test.getDatabaseHandler();
		
		ResultSet us1;
		ResultSet us2;
		ResultSet us3;
		
		username1 = "test";
		username2 = "fuckLars";
		notExist = "finnesIkke";
		
		us1 = lh1.getUserCredentials(dbh, username1);
		us2 = lh1.getUserCredentials(dbh, username2);
		us3 = lh1.getUserCredentials(dbh, notExist);
		
		String name1 = null;
		String name2 = null;
		String name3 = null;
		
		if (us1.next()) {
			 name1 = us1.getString("username");
		} if (us2.next()) {
			name2 = us2.getString("username");
		} if (us3.next()) {
			name3 = us3.getString("username");
		}
		
		assertTrue(name1 != null);
		assertTrue(name2 != null);
		assertFalse(name3 != null);
	}
	
	public void testPassword() throws SQLException {
		lh2 = new LoginHandler();
		dbh = test.getDatabaseHandler();
		
		username1 = "test";
		username2 = "fuckLars";
		username3 = "kimrro";
		ResultSet password1 = lh2.getUserCredentials(dbh, username1);
		ResultSet password2 = lh2.getUserCredentials(dbh, username2);
		ResultSet password3 = lh2.getUserCredentials(dbh, username3);
		
		String pwd1 = null;
		String pwd2 = null;
		String pwd3 = null;
		
		if (password1.next()) {
			pwd1 = password1.getString("password");
		} if (password2.next()) {
			pwd2 = password2.getString("password");
		} if (password3.next()) {
			pwd3 = password3.getString("password");
		}
		
		assertNotSame(pwd1, "feilPassord");
		assertEquals(pwd1, "test123");
		
		assertEquals(pwd2, "faenda123");
		
		assertEquals(pwd3, "123456");
	}

}
