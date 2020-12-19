package cs2321;

public class QuickSort<E extends Comparable<E>> implements Sorter<E> {

	/**
	 * sort - Perform a quick sort
	 * @param array - Array to sort
	 */
	/*
	 * under expected case performs n comparisons log n times, 
	 * under unexpected case performs n comparisons n times
	 */
	public void sort(E[] array) {
		divide(array, 0, array.length-1);
	}
	
	private void divide(E[] array, int first, int last) {
		if (first < last) {
			int pivot = partition(array,first, last);
			divide(array, first, pivot-1);
			divide(array, pivot+1, last);
		}
	}
	
	private int partition (E[] array, int first, int last) {
		
		E pivot = array[first];
		int i = first;
		int j = last;
		
		do {
			//move bottom index up until element is greater than the pivot
			while((pivot.compareTo(array[i])>=0)&(i<last)){
				i++;
			}
			//move top down until element is less than the pivot
			while(pivot.compareTo(array[j])<0) {
				j--;
			}
			//swap the two
			if(i<j) {
				E temp = array[i];
				array[i]=array[j];
				array[j]=temp;
			}
			
		}while(i < j);
		
		//puts pivot in the middle of the list, in its proper place
		E temp = array[first];
		array[first]=array[j];
		array[j]=temp;
		return j;
	}
}
