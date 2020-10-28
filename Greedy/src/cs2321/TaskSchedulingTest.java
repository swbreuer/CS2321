package cs2321;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TaskSchedulingTest {
	int[][] test;
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testOneTask() {
		test = new int[1][2];
		test[0][0]= 1;
		test[0][1]= 2;
		
		assertEquals(TaskScheduling.NumOfMachines(test),1);
	}

	@Test
	public void testTwoTasksFit() {
		test = new int[2][2];
		test[0][0]= 1;
		test[0][1]= 2;
		
		test[1][0]= 3;
		test[1][1]= 4;
		assertEquals(TaskScheduling.NumOfMachines(test),1);
	}

	@Test
	public void testFourTasksFit() {
		test = new int[4][2];
		test[0][0]= 1;
		test[0][1]= 4;

		test[1][0]= 4;
		test[1][1]= 6;
		
		test[2][0]= 6;
		test[2][1]= 15;
		
		test[3][0]= 15;
		test[3][1]= 20;
		assertEquals(TaskScheduling.NumOfMachines(test),1);
	}

	@Test
	public void testFourTasksFitScramble() {
		test = new int[4][2];
		test[3][0]= 1;
		test[3][1]= 4;
		
		test[2][0]= 15;
		test[2][1]= 20;

		test[1][0]= 4;
		test[1][1]= 6;
		
		test[0][0]= 6;
		test[0][1]= 15;
		assertEquals(TaskScheduling.NumOfMachines(test),1);
	}
	
	@Test
	public void test3Machines7Tasks() {
		test = new int[7][2];
		test[0][0]= 1;
		test[0][1]= 5;
		
		test[1][0]= 1;
		test[1][1]= 3;
		
		test[2][0]= 2;
		test[2][1]= 5;
		
		test[3][0]= 3;
		test[3][1]= 7;

		test[4][0]= 5;
		test[4][1]= 7;
		
		test[5][0]= 5;
		test[5][1]= 9;

		test[6][0]= 7;
		test[6][1]= 9;
		assertEquals(TaskScheduling.NumOfMachines(test),3);
	}

	@Test
	public void test3Machines7TasksScramble() {
		test = new int[7][2];
		test[3][0]= 1;
		test[3][1]= 5;
		
		test[4][0]= 1;
		test[4][1]= 3;
		
		test[5][0]= 2;
		test[5][1]= 5;
		
		test[0][0]= 3;
		test[0][1]= 7;

		test[1][0]= 5;
		test[1][1]= 7;
		
		test[2][0]= 5;
		test[2][1]= 9;

		test[6][0]= 7;
		test[6][1]= 9;
		
		assertEquals(TaskScheduling.NumOfMachines(test),3);
	}
}
