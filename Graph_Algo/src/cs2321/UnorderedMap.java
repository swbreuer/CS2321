package cs2321;


import java.util.Iterator;

import net.datastructures.Entry;
import net.datastructures.Map;
import net.datastructures.Position;

public class UnorderedMap<K,V> extends AbstractMap<K,V> {
	
	ArrayList<mapEntry<K,V>> list;
	/* Use ArrayList or DoublyLinked list for the Underlying storage for the map of entries.
	 * TODO:  Uncomment one of these two lines;
	 * private ArrayList<Entry<K,V>> table; 
	 * private DoublyLinkedList<Entry<K,V>> table;
	 */
	
	public UnorderedMap() {
		list = new ArrayList<mapEntry<K,V>>();
	}
		
	/**
	 * returns size of the list
	 */
	@Override
	public int size() {
		return list.size();
	}

	/**
	 * returns if the list is empty
	 */
	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	/**
	 * gets value at a given key
	 */
	@Override
	public V get(K key) {
		int index = search(key);
		if(index != -1) {
			return list.get(index).getValue();
		}
		return null;
	}

	/**
	 * @param key to add to the list
	 * @value value to add to the list
	 * @return value previously at key location
	 */
	@Override
	public V put(K key, V value) {
		int index = search(key);
		mapEntry entry = new mapEntry(key,value);
		if(index != -1) {
			V ret = list.get(index).getValue();
			list.set(index, entry);
			return ret;
		}
		else {
			list.addLast(entry);
			return null;
		}
	}

	/**
	 * remove value with given key
	 * @return value of removed node, null if none exist
	 */
	@Override
	public V remove(K key) {
		int index = search(key);
		if(index!=-1) {
			return list.remove(index).getValue();
		}
		return null;
	}


	/**
	 * return iterable of entries
	 */
	@Override
	public Iterable<Entry<K, V>> entrySet() {
		
		return new mapIterable();
	}

	/**
	 * searches for a given key, returns index of that key
	 * @param key to search for
	 * @return int index location of key
	 */
	private int search(K key) {
		Iterable<K> iterator = keySet();
		int index = 0;
		for(K element : iterator) {
			if(element.equals(key)) {
				return index;
			}
			index++;
		}
		return -1;
	}
	
	/**
	 * creates iterable over map
	 * @author sambreuer
	 *
	 */
	class mapIterable implements Iterable<Entry<K,V>>{

		@Override
		public Iterator<Entry<K, V>> iterator() {
			// TODO Auto-generated method stub
			return new mapIterator();
		}
		
	}
	
	/**
	 * creates iterator over map
	 * @author sambreuer
	 *
	 */
	class mapIterator implements Iterator<Entry<K,V>>{
		int index = 0;
		@Override
		public boolean hasNext() {
			return index<list.size();
		}

		@Override
		public Entry<K, V> next() {
			Entry<K,V> ret = list.get(index);
			index++;
			return ret;
		}
		
	}
}
