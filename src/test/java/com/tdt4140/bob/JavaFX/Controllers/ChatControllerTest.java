package com.tdt4140.bob.JavaFX.Controllers;

import static org.junit.Assert.*;

import org.junit.Test;
import com.tdt4140.bob.JavaFX.Controllers.*;

public class ChatControllerTest extends junit.framework.TestCase{
	
	private ChatController CC = new ChatController();
	
	@Test
	public void testValid() {
		assertTrue(CC.isValidCommand("/lecturer"));
		assertTrue(CC.isValidCommand("/clear"));
	}
	
	@Test
	public void testNotValid() {
		assertTrue(CC.isValidCommand("hellpp"));
		assertTrue(CC.isValidCommand("what classes am I in?"));
	}

}

