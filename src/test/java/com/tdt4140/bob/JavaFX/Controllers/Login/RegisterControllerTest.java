package com.tdt4140.bob.JavaFX.Controllers.Login;

public class RegisterControllerTest extends junit.framework.TestCase {
	RegisterController rc = new RegisterController();
	
	public void testValidUsername() {
		assertTrue(rc.isValidUsername("test!#%"));
		assertTrue(rc.isValidUsername("test"));
		assertFalse(rc.isValidUsername("te"));
		assertFalse(rc.isValidUsername("abcdefghijklmnopqrstuvwxyz"));
	}
	
	public void testValidPassword() {
		assertTrue(rc.isValidPassword("test!!?#"));
		assertTrue(rc.isValidPassword("test123"));
		assertFalse(rc.isValidPassword("123"));
		assertFalse(rc.isValidPassword("abcdefghijklmnopqrstuvwxyz"));
	}
	
	public void testMatchingPassword() {
		assertTrue(rc.isMatchingPassword("!#=(", "!#=("));
		assertTrue(rc.isMatchingPassword("123456", "123456"));
		assertFalse(rc.isMatchingPassword("123", "456"));
	}
	
	public void testValidName() {
		assertTrue(rc.isValidName("kimz", "robinoooo"));
		assertTrue(rc.isValidName("Kim", "Robin"));
		assertFalse(rc.isValidName("K", "R"));
	}
	
	public void testString() {
		assertTrue(rc.isString("String"));
		assertFalse(rc.isString("12,-"));
		assertFalse(rc.isString("%!¤#"));
	}
}
