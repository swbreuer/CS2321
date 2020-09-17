package cs2321;

import java.util.Iterator;

import net.datastructures.List;

public class ArrayList<E> implements List<E> {
	
	E[] list;
	int elements;

	 /**
	 * generates the starter array of type E size 16
	 */
	public ArrayList() {
		list = (E[]) new Object[16];
	}

	/**
	 * gives the number of elements in the array
	 * 
	 * @return elements
	 */
	@Override
	public int size() {
		return elements;
	}

	/**
	 * tells if there are no elements in the array
	 * 
	 * @return elements==0
	 */
	@Override
	public boolean isEmpty() {
		return elements==0;
	}

	/**
	 * returns the element at a given index
	 * 
	 * @param i index
	 * @throws IndexOutOfBoundException
	 * @return list[i]
	 */
	@Override
	public E get(int i) throws IndexOutOfBoundsException {
		if (i > elements-1)
			throw new IndexOutOfBoundsException();
		
		return list[i];
	}

	/**
	 * sets a given element in the array and returns previous stored element
	 * @param i index
	 * @param e element
	 * @return prev
	 */
	@Override
	public E set(int i, E e) throws IndexOutOfBoundsException {
		if (i > elements-1 | i<0)
			throw new IndexOutOfBoundsException();
		
		E prev = list[i];
		list[i]=e;
		
		return prev;
	}

	/**
	 * adds an element at the specified index, pushing all other elements down the list
	 * 
	 * @throws IndexOutOfBoundsException
	 * @param i index
	 * @param e element
	 */
	@Override
	public void add(int i, E e) throws IndexOutOfBoundsException {
		//throws exception if the index is out of the length of the elements
		if (i > elements | i<0) 
			throw new IndexOutOfBoundsException();
		
		//if the index is the last of the list, add it to the end
		if(i==list.length-1) {
			list[i]=e;
			elements++;
			return;
		}
		
		E[] newlist;
		
		/* if the index is outside the 
		 * current list no shift is necessary     */
		if (i  >= list.length) {
			newlist = (E[]) new Object[elements*2];
		}
		/* if the index is within the current list
		 * shift all elements after the index    */
		else {
			newlist = (E[]) new Object[list.length];
			for(int j = i; j <= elements; j++) {
				newlist[j+1]=list[j];
			}
		}
		// add all elements of the old list to the new list
		for(int j=0; j<i; j++) {
			newlist[j]=list[j];
		}
		
		newlist[i]=e;
		list = newlist;
		elements++;
	}
	
	/**
	 * removes and returns element from the list at a given index
	 * 
	 * @throws IndexOutOfBoundsException
	 * @param i index
	 * @return prev 
	 */
	@Override
	public E remove(int i) throws IndexOutOfBoundsException {
		/* throws exception if the index is out of the range
		   or if there is not an element at that index      */ 
		if (i > elements-1 | i<0)
			throw new IndexOutOfBoundsException();
		else if(list[i]==null)
			throw new IndexOutOfBoundsException();
		
		
		E prev = list[i];
		// collapses list back down around gap
		for(int j = i; j <= elements; j++) {
			list[j]=list[j+1];
		}
		
		elements--;
		return prev;
	}

	/**
	 * adds element to the front of the list
	 * 
	 * @param e
	 */
	public void addFirst(E e)  {
		
		/*
		 * if the list is full, creates a new list of double the
		 * previous length and shifts all elements into it, adding 
		 * new element to the beginning of the new list
		 */
		if (elements == list.length) {
			E[] newlist = (E[]) new Object[elements*2];
			
			for(int j = 0; j <= elements; j++) {
				newlist[j+1]=list[j];
			}
			
			newlist[0]=e;
			list = newlist;
			elements++;
			return;
		}
		
		// shifts all elements up one starting from the back to save information
		for(int j = elements; j > 0; j--) {
			list[j]=list[j-1];
		}
		
		list[0]=e;
		elements++;
	}
	
	/**
	 * adds element to the end of the list
	 * 
	 * @param e
	 */
	public void addLast(E e)  {
		
		if (elements == list.length) {
			E[] newlist = (E[]) new Object[elements*2];
			
			for(int i = 0; i<elements;i++) {
				newlist[i+1]=list[i];
			}
			
			list = newlist;
		}
		list[elements]=e;
		elements++;
	}
	
	/**
	 * removes and returns first element of list
	 * 
	 * @return output
	 * @throws IndexOutOfBoundsException
	 */
	public E removeFirst() throws IndexOutOfBoundsException {
		
		E prev = list[0];
		for(int i = 0; i<=elements;i++) {
			list[i]=list[i+1];
		}
		
		return prev;
	}
	
	/**
	 * removes and returns last element of lsit
	 * 
	 * @return prev
	 * @throws IndexOutOfBoundsException
	 */
	public E removeLast() throws IndexOutOfBoundsException {
		E prev = list[elements-1];
		list[elements] = null;
		
		return prev;
	}
	
	/**
	 * returns the size of the array storing the list
	 *  
	 * @return list.length
	 */
	public int capacity() {
		return list.length;
	}

	/**
	 * creates an iterator for the list
	 * 
	 * @return ArrayListIterator
	 */
	@Override
	public Iterator<E> iterator() {
		return new ArrayListIterator();
	}
	
	/**
	 * 
	 * @author sambreuer
	 * 
	 * Iterator class for the arraylist, creates an index to move through the list
	 */
	private class ArrayListIterator implements Iterator<E>{
		int index = 0;
		
		/**
		 * checks if the list has a next element by checking if there are more elements
		 * than the size of the index, if they match the index has gone too far
		 * 
		 * @return index<elements
		 */
		@Override
		public boolean hasNext() {
			return index<elements;
		}

		/**
		 * returns the current element then moves one forward
		 * 
		 * @return element
		 */
		@Override
		public E next() {
			E element = list[index];
			index++;
			return element;
		}
		
	}

}
