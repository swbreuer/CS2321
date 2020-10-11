package cs2321;

import java.util.Comparator;

import net.datastructures.*;
/**
 * A Adaptable PriorityQueue based on an heap. 
 * 
 * Course: CS2321 Section ALL
 * Assignment: #3
 * @author
 */

public class HeapPQ<K extends Comparable<K>,V> implements AdaptablePriorityQueue<K,V> {
	
	private class node<K extends Comparable<K>,V> implements Entry<K,V>{
		K key;
		V value;
		
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
		int parent = (j-1)/2;
		while(parent>=0 & comp.compare(list.get(j).getKey(), list.get(parent).getKey())<0) {
			node<K,V> temp = list.get(j);
			list.set(j, list.get(parent));
			list.set(parent, temp);
			j = parent;
			parent = (j-1)/2;
		}
	}
	
	/**
	 * The entry should be bubbled down to its appropriate position 
	 * @param int move the entry at index j lower if necessary, to restore the heap property
	 */
	
	public void downheap(int j){
		node<K,V> temp = list.get(j);
		int lChild = j*2+1;
		int rChild = j*2+2;
		while(rChild<list.size()) {
			int lcompare = comp.compare(temp.getKey(), list.get(lChild).getKey());
			int rcompare = comp.compare(temp.getKey(), list.get(rChild).getKey());
			if( lcompare>0 | rcompare>0) {
				if(comp.compare(list.get(lChild).getKey(), list.get(rChild).getKey())<0) {
					list.set(j, list.get(lChild));
					list.set(lChild, temp);
					j = lChild;
				}
				else {
					list.set(j, list.get(rChild));
					list.set(rChild, temp);
					j = rChild;
				}
			}
			lChild = j*2+1;
			rChild = j*2+2;
		}
	}

	@Override
	public int size() {
		return list.size();
	}

	@Override
	public boolean isEmpty() {
		return list.size()==0;
	}

	@Override
	public Entry<K, V> insert(K key, V value) throws IllegalArgumentException {
		node<K,V> node = new node<K,V>(key,value);
		list.addLast(node);
		upheap(list.size()-1);
		return node;
	}

	@Override
	public Entry<K, V> min() {
		node<K,V> node = list.get(0);
		return node;
	}

	@Override
	public Entry<K, V> removeMin() {
		node<K,V> node = list.removeFirst();
		list.addFirst(list.removeLast());
		downheap(0);
		return node;
	}

	@Override
	public void remove(Entry<K, V> entry) throws IllegalArgumentException {
		// TODO Auto-generated method stub
	}

	@Override
	public void replaceKey(Entry<K, V> entry, K key) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void replaceValue(Entry<K, V> entry, V value) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}
	


}
