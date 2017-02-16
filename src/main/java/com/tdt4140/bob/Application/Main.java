package com.tdt4140.bob.Application;

import com.tdt4140.bob.Application.Login.Login.Login;

public class Main {

	public static void main(String[] args) {
		DatabaseHandler dbh = new DatabaseHandler();
		dbh.connect();
		
		Login login = new Login();
		login.main(args);
	}
}
