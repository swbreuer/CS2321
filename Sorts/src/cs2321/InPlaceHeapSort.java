package cs2321;

public class InPlaceHeapSort<K extends Comparable<K>> implements Sorter<K> {

	/**
	 * sort - Perform an in-place heap sort
	 * @param array - Array to sort
	 */
	@TimeComplexity("O(n lg n)")
	/*
	 * heapify - log n comparisons n times
	 * sortify - log n comparisons n times
	 */
	public void sort(K[] array) {
		heapify(array);
		sortify(array);
	}

	//forms greatest heap
	private void heapify(K[] a){
		//for all elements of the array
		for (int n = 2; n <= a.length; n++) {
			int chld = n-1; 
			int prnt = (chld-1) / 2;
			//downheap element
			while (prnt >= 0 && a[prnt].compareTo(a[chld])<0) {
				swap(a, prnt, chld);
				chld = prnt; 
				prnt = (chld-1)/2;
			}
		}
	}
	
	private void sortify(K[] array) {
		for (int n = array.length-1; n > 0; --n) {
			//put largest element at the end of the list
			swap(array, 0, n);  // max -> next posn
			int parent = 0;
			//downheap
			while (true) {
				int leftChild = 2 * parent + 1;
				//if there is no left child break
				if (leftChild >= n) {
					break;
				}
				int rightChild = leftChild + 1;
				
				//finds larger of two elements
				int larger = leftChild;
				if (rightChild < n &&array[leftChild].compareTo(array[rightChild]) < 0) {
					larger = rightChild;
				}
				//if parent is smaller than largest of the two elements, swap
				if (array[parent].compareTo(array[larger])<0) {
					  swap(array, parent, larger);
					  parent = larger;
				}
				// if parent is largest, break
				else {
					break;
				}
			}
		}
		
	}
	
	private void swap(K[] array, int i, int j) {
		K temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
}
