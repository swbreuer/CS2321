package cs2321;

import java.util.Comparator;

import net.datastructures.*;
/**
 * A Adaptable PriorityQueue based on an heap. 
 * 
 * Course: CS2321 Section ALL
 * Assignment: #3
 * @author Sam Breuer
 */

public class HeapPQ<K extends Comparable<K>,V> implements AdaptablePriorityQueue<K,V> {
	
	/**
	 * 
	 * @author sambreuer
	 *
	 * @param <K>
	 * @param <V>
	 */
	private class node<K extends Comparable<K>,V> implements Entry<K,V>{
		K key;
		V value;
		int index;
		
		public node(K key, V value) {
			this.key = key;
			this.value = value;
		}
		
		@Override
		public K getKey() {
			return key;
		}

		@Override
		public V getValue() {
			return value;
		}
	}
	
	ArrayList<node<K,V>> list;
	Comparator<K> comp;
	
	/* use default comparator, see DefaultComparator.java */
	public HeapPQ() {
		comp = new DefaultComparator<K>();
		list = new ArrayList<node<K,V>>();
	}
	
	/* use specified comparator */
	public HeapPQ(Comparator<K> c) {
		comp = c;
		list = new ArrayList<node<K,V>>();
	}
	
	
	/* 
	 * Return the data array that is used to store entries  
	 * This method is purely for testing purpose of auto-grader
	 */
	Object[] data() {
		return (Object[]) list.list;
	}
	
	/**
	 * The entry should be bubbled up to its appropriate position 
	 * @param int move the entry at index j higher if necessary, to restore the heap property
	 */
	public void upheap(int j){
		node<K,V> parent = list.get((j-1)/2);
		while(parent.index>=0 & comp.compare(list.get(j).getKey(), parent.getKey())<0) {
			
			node<K,V> temp = list.get(j);
			//changes indexes
			temp.index = parent.index;
			parent.index = j;
			//switches nodes
			list.set(j, parent);
			list.set(temp.index, temp);
			//prepares for next loop
			j = temp.index;
			parent = list.get((j-1)/2);
		}
	}
	
	/**
	 * The entry should be bubbled down to its appropriate position 
	 * @param int move the entry at index j lower if necessary, to restore the heap property
	 */
	
	public void downheap(int j){
		node<K,V> temp = list.get(j);
		node<K,V> lChild = null;
		node<K,V> rChild = null;
		
		//returns if there is no left child
		try {
			lChild = list.get(j*2+1);
		}
		catch(Throwable e) {
			return;
		}
		
		//attempts to flip left child and top node if there is no right child
		try {
			rChild = list.get(j*2+2);
		}
		catch(Throwable e) {
			if(comp.compare(lChild.getKey(), temp.getKey())<0) {
				temp.index = lChild.index;
				lChild.index = j;
				list.set(lChild.index, lChild);
				list.set(temp.index, temp);
				j = temp.index;
			}
			return;
		}
		
		//creates two comparisons between the left child and parent and right child and parent
		int lcompare = comp.compare(temp.getKey(), lChild.getKey());
		int rcompare = comp.compare(temp.getKey(), rChild.getKey());
		
		while(rChild.index<list.size() & (lcompare>0|rcompare>0)) {
			
			//switches left child and parent
			if(comp.compare(lChild.getKey(), rChild.getKey())<0) {
				temp.index = lChild.index;
				lChild.index = j;
				list.set(lChild.index, lChild);
				list.set(temp.index, temp);
				j = temp.index;
			}
			
			//switches right child and parent
			else {
				list.set(j, rChild);
				temp.index = rChild.index;
				rChild.index = j;
				list.set(temp.index, temp);
				j = temp.index;
			}
			

			//returns if there is no left child
			try {
				lChild = list.get(j*2+1);
			}
			catch(Throwable e) {
				return;
			}
			
			//attempts to flip left child and top node if there is no right child
			try {
				rChild = list.get(j*2+2);
			}
			catch(Throwable e) {
				if(comp.compare(lChild.getKey(), temp.getKey())<0) {
					temp.index = lChild.index;
					lChild.index = j;
					list.set(lChild.index, lChild);
					list.set(temp.index, temp);
					j = temp.index;
				}
				return;
			}
			
			//creates two comparisons between the left child and parent and right child and parent
			lcompare = comp.compare(temp.getKey(), lChild.getKey());
			rcompare = comp.compare(temp.getKey(), rChild.getKey());
		}
	}

	/**
	 * returns the size of the heap
	 * @return list size
	 */
	@Override
	public int size() {
		return list.size();
	}

	/**
	 * returns true if the heap is empty
	 * @return if list is empty
	 */
	@Override
	public boolean isEmpty() {
		return list.size()==0;
	}

	/**
	 * adds element to the heap with a given key and value
	 * then preserves the order of the heap by upheaping
	 * @param key K
	 * @param value V
	 */
	@Override
	public Entry<K, V> insert(K key, V value) throws IllegalArgumentException {
		/* TCJ
		 * performs upheap which is O log n as it only performs an operation on each layer
		 * the number of layers being log n
		 * amortized input is O n because the list has to double and get copied over which is an
		 * O n operation
		 */
		node<K,V> node = new node<K,V>(key,value);
		node.index = list.size();
		list.addLast(node);
		upheap(list.size()-1);
		return node;
	}

	/**
	 * returns the min elment without removing it
	 * @return node<K,V> min node
	 */
	@Override
	public Entry<K, V> min() {
		/* TCJ
		 * only returns an element and does not perform any heapify operations
		 */
		if(isEmpty()) {
			return null;
		}
		
		node<K,V> node = list.get(0);
		return node;
	}

	/**
	 * switches the min element with the last element, 
	 * removes the min element, then downheaps the new heap root
	 * @return node<K,V> min node
	 */
	@Override
	public Entry<K, V> removeMin() {
		/* TCJ
		 * performs downheap which is O log n as it only performs an operation on each layer
		 * the number of layers being log n
		 */
		if(isEmpty()) {
			return null;
		}
		if(size()==1) {
			return list.removeFirst();
		}
		//switches indexes
		node<K,V> last = list.removeLast();
		last.index = 0;
		node<K,V> first = list.removeFirst();
		//puts end node to the top
		list.addFirst(last);
		//heapify
		downheap(0);
		return first;
	}

	/**
	 * replaces a given input node with the last node in the list 
	 * then upheaps or downheaps as necessary 
	 * @param node<K,V> the node to be removed
	 */
	@Override
	public void remove(Entry<K, V> entry) throws IllegalArgumentException {
		/* TCJ
		 * performs either upheap or downheap, each of which are log n 
		 * and can find node without having to search for it because
		 * each node has its index
		 */
		if(!(entry instanceof node))
			throw new IllegalArgumentException();
		
		node<K,V> node = (node)entry;
		node<K,V> last = list.removeLast();
		//switches indexes
		int index = node.index;
		last.index = index;
		list.set(index, last);
		//chooses to use upheap or downheap
		node<K,V> parent = list.get((index-1)/2);
		if(comp.compare(node.getKey(), parent.getKey())>0 & index > 0){
			upheap(index);
		}
		else {
			downheap(index);
		}
	}

	/**
	 * changes the key of a given node
	 * @param key K to change in the given node
	 * @param entry node<K,V> 
	 */
	@Override
	public void replaceKey(Entry<K, V> entry, K key) throws IllegalArgumentException {
		/* TCJ
		 * performs either upheap or downheap, each of which are log n 
		 * and can find node without having to search for it because
		 * each node has its index
		 */
		if(!(entry instanceof node))
			throw new IllegalArgumentException();
		
		node<K,V> node = (node)entry;
		int index = node.index;
		//switch indexes
		node = list.get(index);
		node.key = key;
		list.set(index,node);
		//chooses to upheap or downheap
		node<K,V> parent = list.get((index-1)/2);
		if(comp.compare(node.getKey(), parent.getKey())<0 & index > 0){
			upheap(index);
		}
		else {
			downheap(index);
		}
	}

	/**
	 * Replaces the value at a given node
	 * @param value V value to put in node
	 * @param entry node<K,V> node to put the new value in
	 */
	@Override
	public void replaceValue(Entry<K, V> entry, V value) throws IllegalArgumentException {
		/* TCJ
		 * replace value is able to find a node by it's index without  
		 * having to search for it and does not need to upheap or downheap
		 */
		if(!(entry instanceof node))
			throw new IllegalArgumentException();
		
		node<K,V> node = (node)entry;
		int index = node.index;
		node = list.get(index);
		node.value = value;
		list.set(index,node);
	}
	
	
	


}
