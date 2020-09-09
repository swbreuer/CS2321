
public class ArrayProblems {

	/*
	Given an array of integers nums, sort the array in ascending order.

	Example 1:
	Input: nums = [5,2,3,1]
	Output: [1,2,3,5]
	
	Example 2:
	Input: nums = [5,1,1,2,0,0]
	Output: [0,0,1,1,2,5]
	*/
	
	public static int[] sortArray(int[] nums) {
		int len = nums.length;
		for(int i = 0 ; i< len-1 ; i=i+1) {
    		for(int j = 0 ; j < len-i-1 ; j=j+1) {
    			if(nums[j]>nums[j+1]) {
    				int tmp = nums[j];
    				nums[j]=nums[j+1];
    				nums[j+1]=tmp;
    			}
    		}
		}
        return nums;
	}

	/*
	 * Find the kth largest element in an unsorted array. 
	 * Note that it is the kth largest element in the sorted order, not the kth distinct element.
	   Example1: Input: [3,2,1,5,6,4] and k = 2
			    Output: 5
	   Example2: Input: [3,2,3,1,2,4,5,5,6] and k = 4
			    Output: 4
	*/
    public static int findKthLargest(int[] nums, int k) {
    	int[] sorted = sortArray(nums);
    	int index = sorted.length-k;
    	return sorted[index];
    }
    
}
