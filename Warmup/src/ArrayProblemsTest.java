import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ArrayProblemsTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	/*
	sortArray CASE 1:
	Input: nums = [5,2,3,1]
	Output: [1,2,3,5]
	 */
	@Test
	void testSortArray1() {
		int[] nums= {5,2,3,1};
		int[] expected = {1,2,3,5};
		System.out.println(ArrayProblems.sortArray(nums));
		assertArrayEquals(expected, ArrayProblems.sortArray(nums));
	}

	/*
	sortArray CASE 2:
	Input: nums = [5,1,1,2,0,0]
	Output: [0,0,1,1,2,5]
	 */
	@Test
	void testSortArray2() {
		int[] nums= {5,1,1,2,0,0};
		int[] expected = {0,0,1,1,2,5};
		System.out.println(ArrayProblems.sortArray(nums));
		assertArrayEquals(expected, ArrayProblems.sortArray(nums));
	}

	/*
	findKthLargest test case1: 
	Input: [3,2,1,5,6,4] and k = 2
	Output: 5
	 */
	@Test
	void testFindKthLargest1() {
		int[] input = {3,2,1,5,6,4};
		int k = 2;
		int expected = 5;
		System.out.println(ArrayProblems.findKthLargest(input, k));
		assert(ArrayProblems.findKthLargest(input, 2) == expected); // TODO
	}


	/*
	findKthLargest test case1: 
	Input: [3,2,3,1,2,4,5,5,6] and k = 4
	Output: 4
	 */
	@Test
	void testFindKthLargest2() {
		int[] input = {3,2,3,1,2,4,5,5,6};
		int k = 4;
		int expected = 4;
		assert(ArrayProblems.findKthLargest(input, k) == expected);
		
	}

}
