package cs2321;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PostfixExpressionTest {
	PostfixExpression test;
	
	@Before
	public void setUp() throws Exception {
		test = new PostfixExpression();
	}

	@Test
	public void testEvaluate1() {
		String expression = "1 ";
		int expected = 1;
		org.junit.Assert.assertEquals(expected, test.evaluate(expression));
	}
	
	@Test
	public void testEvaluate2() {
		String expression = "1 1 +";
		int expected = 2;
		org.junit.Assert.assertEquals(expected, test.evaluate(expression));
	}
	
	@Test
	public void testEvaluate3() {
		String expression = "1 2 -";
		int expected = -1;
		org.junit.Assert.assertEquals(expected, test.evaluate(expression));
	}
	
	@Test
	public void testEvaluate4() {
		String expression = "4 2 /";
		int expected = 2;
		org.junit.Assert.assertEquals(expected, test.evaluate(expression));
	}
	
	@Test
	public void testEvaluate5() {
		String expression = "2 5 - 4 + 7 *";
		int expected = 7;
		org.junit.Assert.assertEquals(expected, test.evaluate(expression));
	}
	
	@Test
	public void testEvaluate6() {
		String expression = "4 20 5 + * 6 -";
		int expected = 94;
		org.junit.Assert.assertEquals(expected, test.evaluate(expression));
	}
	
	@Test
	public void testEvaluate7() {
		String expression = "4 3 /";
		int expected = 1;
		org.junit.Assert.assertEquals(expected, test.evaluate(expression));
	}
	
	@Test
	public void testEvaluate8() {
		String expression = "2 5 + 4 * 7 -";
		int expected = 21;
		org.junit.Assert.assertEquals(expected, test.evaluate(expression));
	}

}
