package com.tdt4140.bob.JavaFX.Controllers;

import static org.junit.Assert.*;

import org.junit.Test;
import com.tdt4140.bob.JavaFX.Controllers.*;

public class ChatControllerTest extends junit.framework.TestCase{
	ChatController CC= new ChatController();
	@Test
	public void test() {
		
		assertTrue(CC.isValidCommand("what subjects do I have?"));
		assertTrue(CC.isValidCommand("help"));
		assertFalse(CC.isValidCommand("hellpp"));
		assertFalse(CC.isValidCommand("what classes am I in?"));
	}

}

