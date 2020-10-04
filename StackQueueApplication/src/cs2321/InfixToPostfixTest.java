package cs2321;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class InfixToPostfixTest {
	InfixToPostfix test;

	@Before
	public void setUp() throws Exception {
		test = new InfixToPostfix();
	}

	@Test
	public void testConvert1() {
		String expression = "4 + 2";
		String expected = "4 2 +";
		org.junit.Assert.assertEquals(expected, test.convert(expression));
	}
	
	@Test
	public void testConvert2() {
		String expression = "4 - 2";
		String expected = "4 2 -";
		org.junit.Assert.assertEquals(expected, test.convert(expression));
	}
	
	@Test
	public void testConvert3() {
		String expression = "4 * 2";
		String expected = "4 2 *";
		org.junit.Assert.assertEquals(expected, test.convert(expression));
	}
	
	@Test
	public void testConvert4() {
		String expression = "4 / 2";
		String expected = "4 2 /";
		org.junit.Assert.assertEquals(expected, test.convert(expression));
	}
	
	@Test
	public void testConvert5() {
		String expression = "1 * ( 1 + 2 )";
		String expected = "1 1 2 + *";
		org.junit.Assert.assertEquals(expected, test.convert(expression));
	}

	@Test
	public void testConvert6() {
		String expression = "1 * ( 1 + ( 2 - 3 ) )";
		String expected = "1 1 2 3 - + *";
		org.junit.Assert.assertEquals(expected, test.convert(expression));
	}
	
	@Test
	public void testConvert7() {
		String expression = "1 + 4 * 5";
		String expected = "1 4 5 * +";
		org.junit.Assert.assertEquals(expected, test.convert(expression));
	}
	
	@Test
	public void testConvert8() {
		String expression = "1 + 4 * 5 + 4 - 8 / 4";
		String expected = "1 4 5 * + 4 + 8 4 / -";
		org.junit.Assert.assertEquals(expected, test.convert(expression));
	}
}
