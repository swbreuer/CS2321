package cs2321;

public class InPlaceSelectionSort<K extends Comparable<K>> implements Sorter<K> {

	/**
	 * sort - Perform an in-place selection sort
	 * @param array - Array to sort
	 */
	@TimeComplexity("O(n^2)")
	/*
	 * performs n comparisons n times
	 */
	public void sort(K[] array) {
        int n = array.length; 

        // for each element
        for (int i = 0; i < n-1; i++){ 
            int min_idx = i; 
            //loop over all unsorted elements, change index of smallest element to end of sorted array
            for (int j = i+1; j < n; j++) { 
                if (array[j].compareTo(array[min_idx])<0) {
                    min_idx = j; 
                }
            }
  
            //swap smallest element with end of sorted rray 
            K temp = array[min_idx]; 
            array[min_idx] = array[i]; 
            array[i] = temp; 
        } 
	}
}
