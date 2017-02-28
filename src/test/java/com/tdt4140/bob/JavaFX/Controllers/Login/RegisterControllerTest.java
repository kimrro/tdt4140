package com.tdt4140.bob.JavaFX.Controllers.Login;

import com.tdt4140.bob.JavaFX.Bob;

public class RegisterControllerTest extends junit.framework.TestCase {
	RegisterController rc = new RegisterController();

	public void testRegister() {
		// isValidUsername()
		assertTrue(rc.isValidUsername("test"));
		assertFalse(rc.isValidUsername("te"));
		assertFalse(rc.isValidUsername("abcdefghijklmnopqrstuvwxyz"));
		
		// isValidPassword()
		assertTrue(rc.isValidPassword("test123"));
		assertFalse(rc.isValidPassword("123"));
		assertFalse(rc.isValidPassword("abcdefghijklmnopqrstuvwxyz"));
		
		// isMatchingPassword()
		assertTrue(rc.isMatchingPassword("123456", "123456"));
		assertFalse(rc.isMatchingPassword("123", "456"));
		
		// isValidName()
		assertTrue(rc.isValidName("Kim", "Robin"));
		assertFalse(rc.isValidName("K", "R"));
		
		// isString()
		assertTrue(rc.isString("String"));
		assertFalse(rc.isString("12"));

	}
}
