package com.tdt4140.bob.JavaFX.Controllers.Login;

public class User {
	private static String username;
	private static int privilege;
	
	public User(String username, int privilege) {
		User.username = username;
		User.privilege = privilege;
	}

	public static String getUsername() {
		return username;
	}
	
	public static int getPrivilege() {
		return privilege;
	}
}
