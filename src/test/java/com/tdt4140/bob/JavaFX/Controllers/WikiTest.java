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
	
	@Test
	public void testOutput1() throws IOException {
		assertEquals(CC.wikipedia("world war"), "A world war "
				+ "is a war involving many of the countries of "
				+ "the world or many of the most powerful and populous ones.");
	}
	
	@Test
	public void testOutput2() throws IOException {
		assertEquals(CC.wikipedia("football"), "Football is a family of team sports"
				+ " that involve, to varying degrees, kicking a ball with the foot to"
				+ " score a goal.");
	}

}

