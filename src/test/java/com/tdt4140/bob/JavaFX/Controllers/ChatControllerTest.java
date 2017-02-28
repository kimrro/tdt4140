package com.tdt4140.bob.JavaFX.Controllers;

import static org.junit.Assert.*;

import org.junit.Test;
import com.tdt4140.bob.JavaFX.Controllers.*;

public class ChatControllerTest extends junit.framework.TestCase{
	ChatController CC= new ChatController();
	@Test
	public void testIsValidCommand() {
		
		assertTrue(CC.isValidCommand("help"));
		assertFalse(CC.isValidCommand("helpp"));
	}

}

