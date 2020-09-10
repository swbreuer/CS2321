package cs2321;

import java.util.Iterator;

import net.datastructures.List;

public class ArrayList<E> implements List<E> {
	
	E[] list;
	int elements;

	public ArrayList() {
		list = (E[]) new Object[16];
	}

	@Override
	public int size() {
		return elements;
	}

	@Override
	public boolean isEmpty() {
		return elements==0;
	}

	@Override
	public E get(int i) throws IndexOutOfBoundsException {
		// TODO Auto-generated method stub
		if (i > elements-1)
			throw new IndexOutOfBoundsException();
		
		return list[i];
	}

	@Override
	public E set(int i, E e) throws IndexOutOfBoundsException {
		// TODO Auto-generated method stub
		if (i > elements-1 | i<0)
			throw new IndexOutOfBoundsException();
		
		E out = list[i];
		list[i]=e;
		return out;
	}

	@Override
	public void add(int i, E e) throws IndexOutOfBoundsException {
		// TODO Auto-generated method stub
		if (i > elements-1 | i<0)
			throw new IndexOutOfBoundsException();
		
		if (elements == list.length) {
			E[] newlist = (E[]) new Object[elements*2];
			
			for(int j = i; j <= elements; j++) {
				newlist[j+1]=list[j];
			}
			newlist[i]=e;
			list = newlist;
			return;
		}
		
		for(int j = elements; j >= i; j--) {
			list[j]=list[j-1];
		}
		list[i]=e;
		elements++;
	}
	
	@Override
	public E remove(int i) throws IndexOutOfBoundsException {
		// TODO Auto-generated method stub
		if (i > elements-1 | i<0)
			throw new IndexOutOfBoundsException();
		else if(list[i]==null)
			throw new IndexOutOfBoundsException();
		
		E output = list[i];
		for(int j = i; j <= elements; j++) {
			list[j]=list[j+1];
		}
		elements--;
		return output;
	}

	
	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	public void addFirst(E e)  {
		// TODO Auto-generated method stub
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
		
		for(int j = elements; j >= 0; j--) {
			list[j]=list[j-1];
		}
		list[0]=e;
		elements++;
	}
	
	public void addLast(E e)  {
		// TODO Auto-generated method stub
		if (elements == list.length) {
			E[] newlist = (E[]) new Object[elements*2];
			for(int i = 0; i<=elements;i++) {
				newlist[i+1]=list[i];
			}
			list = newlist;
		}
		list[elements+1]=e;
		elements++;
	}
	
	public E removeFirst() throws IndexOutOfBoundsException {
		// TODO Auto-generated method stub
		E output = list[0];
		for(int i = 0; i<=elements;i++) {
			list[i]=list[i+1];
		}
		return output;
	}
	
	public E removeLast() throws IndexOutOfBoundsException {
		// TODO Auto-generated method stub
		E output = list[elements];
		list[elements] = null;
		return output;
	}
	
	// Return the capacity of array, not the number of elements.
	// Notes: The initial capacity is 16. When the array is full, the array should be doubled. 
	public int capacity() {
		return list.length;
	}
	
}
