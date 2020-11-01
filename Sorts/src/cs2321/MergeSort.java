package cs2321;

public class MergeSort<E extends Comparable<E>> implements Sorter<E> {

	/**
	 * sort - Perform a merge sort
	 * @param array - Array to sort
	 */
	@TimeComplexity("O(n lg n)")
	/*
	 * performs n comparisons log n times
	 */
	public void sort(E[] array) {
		divide(array,0,array.length-1);
	}
	
	public void divide(E[] array, int start, int end) {
		
		if(start<end) {
			int mid = (start+end)/2;
			divide(array, start,mid);
			divide(array, mid+1,end);
			conquer(array,start,mid,end);
		}
	}
	
	public void conquer(E[] array, int start, int mid, int end) {
		int i = start;
		int j = mid+1;
		int k = 0;
		E[] temp =(E[]) new Comparable[end-start+1];
		while(i<=mid & j<=end) {
			//if i>j then add i
			if(array[i].compareTo(array[j])<0) {
				temp[k]=array[i];
				i++;
				k++;
			}
			//if j>i then add j
			else {
				temp[k]=array[j];
				j++;
				k++;
			}
		}
		//add the rest of each array
		while(i<=mid) {
			temp[k]=array[i];
			i++;
			k++;
		}
		while(j<=end) {
			temp[k]=array[j];
			j++;
			k++;
		}
		//put the temp array into the main array
		for(i = start; i<=end; i++) {
			array[i] = temp[i-start];
		}
	}
	
}

