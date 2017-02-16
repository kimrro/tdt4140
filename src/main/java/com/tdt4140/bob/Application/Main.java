package com.tdt4140.bob.Application;

import java.sql.SQLException;

import com.tdt4140.bob.Application.Example.ExampleInfo;
import com.tdt4140.bob.Application.Login.Login;

public class Main {

	public static void main(String[] args) throws SQLException {
		DatabaseHandler dbh = new DatabaseHandler();
		dbh.connect();
		
//		Login login = new Login();
//		login.main(args);
//		System.out.println("test");
		
		ExampleInfo ei = new ExampleInfo();
		System.out.println(ei.getUserInfo(dbh));
	}
}
