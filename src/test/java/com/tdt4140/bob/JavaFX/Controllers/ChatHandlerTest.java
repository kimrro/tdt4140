package com.tdt4140.bob.JavaFX.Controllers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;

import com.tdt4140.bob.Application.DatabaseHandler;
import com.tdt4140.bob.Application.Chat.ChatHandler;

public class ChatHandlerTest extends junit.framework.TestCase {
	
	ChatHandler CH = new ChatHandler();
	DatabaseHandler dbh;
	
	public String database() throws SQLException {
		try {
			ChatHandler.writeQuestion(dbh, "what is RAM", "test");
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		ResultSet output = ChatHandler.getQuestion(dbh, "test");
		ArrayList<String> questions = new ArrayList<String>();
		while (output.next()) {
			questions.add(output.getString("question"));
		}
		System.out.println(questions);
		System.out.println(questions.size());
		String a = questions.get(questions.size() - 1);
		System.out.println(a);
		return a;
	}
	
	@Test
	public void question() throws SQLException {
		assertEquals("what is RAM", database());
	}
	
	public String database2() throws SQLException {
		ResultSet output = ChatHandler.getCurriculum(dbh, "Database", "tdt4140");
		String b = output.getString("pages");
		return b;
	}
	
	@Test
	public void curriculum() throws SQLException {
		assertEquals("97", database2());
	}
	
	public String database3() throws SQLException {
		ResultSet output = ChatHandler.getSubjects(dbh, "burg");
		String c = output.getString("pages");
		return c;
	}
	
	@Test
	public void subjects() throws SQLException {
		ArrayList<String> test = new ArrayList<String>();
		test.add("Programvareutvikling");
		test.add("Datamodellering og databasesystemer");
		assertEquals(test, database3());
	}
	
	public ArrayList<String> database4() throws SQLException {
		ResultSet output = ChatHandler.getSubjects(dbh, "Datamodellering og databasesystemer");
		ArrayList<String> lecturer = new ArrayList<String>();
		lecturer.add(output.getString("fornavn") + " " + output.getString("etternavn"));
		return lecturer;
	}
	
	@Test
	public void lecturer() throws SQLException {
		assertEquals("Johan Hansen", database4());
	}
	
	public String database5() throws SQLException {
		ResultSet output = ChatHandler.getSubjectCode(dbh, "Programvareutvikling");
		String d = output.getString("code");
		return d;
	}
	
	@Test
	public void subjectCode() throws SQLException {
		assertEquals("TDT4140", database5());
	}
	

}
