package com.tdt4140.bob.JavaFX.Controllers;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;
import com.tdt4140.bob.JavaFX.Controllers.*;

public class WikiTest extends junit.framework.TestCase{
	ChatController CC = new ChatController();
	@Test
	public void test1() throws IOException {
		assertTrue(CC.isValidWiki("oslo"));
		assertTrue(CC.isValidWiki("Oslo"));
	}
	
	@Test
	public void test2() throws IOException {
		assertTrue(CC.isValidWiki("joke"));
		assertTrue(CC.isValidWiki("Joke"));
	}
	
	@Test
	public void test3() throws IOException {
		assertTrue(CC.isValidWiki("bergen"));
		assertTrue(CC.isValidWiki("Bergen"));
	}
	
	@Test
	public void test4() throws IOException {
		assertTrue(CC.isValidWiki("random-access memory"));
		assertTrue(CC.isValidWiki("Random-access memory"));
	}

}

