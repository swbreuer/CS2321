package cs2321;

public class InPlaceInsertionSort<K extends Comparable<K>> implements Sorter<K> {

	/**
	 * sort - Perform an in-place insertion sort
	 * @param array - Array to sort
	 */
	@TimeComplexity("O(n^2)")
	/*
	 * performs n comparisons n times
	 */
	public void sort(K[] array) {
		int n = array.length;

		//for all elements
		for (int fill = 0; fill < n-1; fill++) {
			int min = fill;
			//for each element, compare against the sorted array and change index of element smaller than it
			for (int index = fill+1; index < n; index++) {
				if (array[index].compareTo(array[min])<0) {
					min = index;
				}
			}
			
			//swap max and working element
			K tmp = array[fill];
			array[fill] = array[min];
			array[min] = tmp;
		}
	}

}
