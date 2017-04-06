package com.tdt4140.bob.JavaFX.Controllers.Login;

public class User {
	private static String username;
	private static int privilege;
	
	/**
	 * A constructor used when a user logs in. Automatically assigns username and privilege to variables.<p>
	 * Used to keep record of the user logged in on the device.
	 * @param username Username logged in with.
	 * @param privilege Privilege the user has.
	 * @author KimRobin
	 */
	public User(String username, int privilege) {
		User.username = username;
		User.privilege = privilege;
	}

	/**
	 * A static function to get username.
	 * @return Returns the username of the user logged in.
	 * @author KimRobin
	 */
	public static String getUsername() {
		return username;
	}
	
	/**
	 * A static function to get privilege.
	 * @return Returns the privilege of the user logged in.
	 * @author KimRobin
	 */
	public static int getPrivilege() {
		return privilege;
	}
	
	/**
	 * A function to reset (set username to an empty string and privilege to 0) user.<p>
	 * Used when a user logs out.
	 * @author KimRobin
	 */
	public static void resetUser() {
		username = "";
		privilege = 0;
	}
}
