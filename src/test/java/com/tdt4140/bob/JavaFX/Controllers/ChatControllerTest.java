package com.tdt4140.bob.JavaFX.Controllers;

import static org.junit.Assert.*;

import org.junit.Test;
import com.tdt4140.bob.JavaFX.Controllers.*;

public class ChatControllerTest extends junit.framework.TestCase{
	ChatController CC = new ChatController();
	@Test
	public void test() {
		
		assertTrue(CC.isValidCommand("/lecturer"));
		assertTrue(CC.isValidCommand("/clear"));
		assertFalse(CC.isValidCommand("hellpp"));
		assertFalse(CC.isValidCommand("what classes am I in?"));
	}

}

