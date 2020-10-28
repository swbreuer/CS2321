package cs2321;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class FractionalKnapsackTest {
	int[][] test;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testMaximumValueOneFull() {
		test = new int[1][2];
		test[0][0]=4;
		test[0][1]=12;
		assertEquals(12,FractionalKnapsack.MaximumValue(test, 10),0);
	}
	
	@Test
	public void testMaximumValueOnepartial() {
		test = new int[1][2];
		test[0][0]=4;
		test[0][1]=12;
		assertEquals(6,FractionalKnapsack.MaximumValue(test, 2),0);
	}

	@Test
	public void testMaximumValueSingleDivide() {
		test = new int[1][2];
		test[0][0]=4;
		test[0][1]=10;
		assertEquals(7.5,FractionalKnapsack.MaximumValue(test, 3),0);
	}

	@Test
	public void testMaximumValuePart() {
		test = new int[5][2];
		test[0][0]=4;
		test[0][1]=12;
		test[1][0]=8;
		test[1][1]=32;
		test[2][0]=2;
		test[2][1]=30;
		test[3][0]=6;
		test[3][1]=40;
		test[4][0]=1;
		test[4][1]=50;
		assertEquals(124,FractionalKnapsack.MaximumValue(test, 10),0);
	}

	@Test
	public void testMaximumValueSmallPartial() {
		test = new int[2][2];
		test[0][0]=4;
		test[0][1]=12;
		test[1][0]=8;
		test[1][1]=32;
		assertEquals(38,FractionalKnapsack.MaximumValue(test, 10),0);
	}
}
