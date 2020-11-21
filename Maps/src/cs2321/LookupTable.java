package cs2321;

import java.util.Iterator;

import net.datastructures.*;

public class LookupTable<K extends Comparable<K>, V> extends AbstractMap<K,V> implements SortedMap<K, V> {
	
	/* 
	 * Use Sorted ArrayList for the Underlying storage for the map of entries.
	 * TODO: Uncomment this line;
	 * private ArrayList<Entry<K,V>> table; 
	 */
	
	ArrayList<Entry<K,V>> list;

	public LookupTable(){
		list = new ArrayList<Entry<K,V>>();
	}

	
	/**
	 * returns value node with given key
	 * @param key of desired value
	 * @return value of node with given key
	 */
	@TimeComplexity("O(lg n)")
	@Override
	public V get(K key) {
		int location = binSearch(key,0,list.size()-1);
		if(location<list.size()) {
			if(list.get(location).getKey()==key) {
				return list.get(location).getValue();
			}
		}
		return null;
	}

	/**
	 * adds a node to the tree and returns any value that was in the same key previously
	 * @param key of new node
	 * @param value of new node
	 * @return value previously at key location
	 */
	@TimeComplexity("O(lg n)")
	@Override
	public V put(K key, V value) {
		int location = binSearch(key,0,list.size()-1);
		mapEntry<K,V> entry = new mapEntry<K,V>(key,value);
		if(location<list.size()) {
			if(list.get(location).getKey()==key) {
				V ret = list.get(location).getValue();
				list.set(location, entry);
				return ret;
			}
		}
		list.add(location, entry);
		return null;
	}

	/**
	 * removes node at given key and returns previous value if node exists
	 * @param key of node to remove
	 * @value value of removed node, null if none exists
	 */
	@TimeComplexity("O(lg n)")
	@Override
	public V remove(K key) {
		int location = binSearch(key,0,list.size()-1);
		if(location<list.size()) {
			if(list.get(location).getKey()==key) {
				return list.remove(location).getValue();
			}
		}
		return null;
	}


	/**
	 * returns list to iterate over
	 */
	@Override
	public Iterable<Entry<K, V>> entrySet() {
		// TODO Auto-generated method stub
		return list;
	}

	/**
	 * returns smallest entry or null if there is none
	 */
	@Override
	public Entry<K, V> firstEntry() {
		// TODO Auto-generated method stub
		try {
			return list.get(0);
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
	}

	/**
	 * returns largest entry or null if there is none
	 */
	@Override
	public Entry<K, V> lastEntry() {
		// TODO Auto-generated method stub
		try {
			return list.get(list.size()-1);
		} catch (IndexOutOfBoundsException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}

	/**
	 * returns entry with key just larger or equal to a given key
	 * @param key to look for
	 * @return entry with key just larger or equal to given key
	 */
	@TimeComplexity("O(lg n)")
	@Override
	public Entry<K, V> ceilingEntry(K key)  {
		int top = binSearch(key, 0 , list.size()-1);
		if(0==list.size()) {
			return null;
		}
		if(list.get(top).getKey().equals(key) | top == 0) {
			return list.get(top);
		}
		
		return list.get(top);
	}

	/**
	 * returns entry with key just smaller or equal to a given key
	 * @param key to look for
	 * @return entry with key just smaller or equal to given key
	 */
	@TimeComplexity("O(lg n)")
	@Override
	public Entry<K, V> floorEntry(K key)  {
		int bottom = binSearch(key, 0 , list.size()-1);
		if(0==list.size()) {
			return null;
		}
		if(bottom==list.size()) {
			return list.get(bottom-1);
		}
		if(list.get(bottom).getKey().equals(key)) {
			return list.get(bottom);
		}
		bottom--;
		return list.get(bottom);
	}

	/**
	 * returns entry with key just smaller than a given key or null
	 * @param key to look for
	 * @return entry with key just smaller than a given key or null
	 */
	@Override
	public Entry<K, V> lowerEntry(K key) {
		int top = binSearch(key, 0 , list.size()-1);
		if(top==0) {
			return null;
		}
		if(list.get(top).getKey().equals(key)) {
			top--;
		}
		return list.get(top);
	}

	/**
	 * returns entry with key just larger than a given key or null
	 * @param key to look for
	 * @return entry with key just larger than a given key or null
	 */
	@Override
	public Entry<K, V> higherEntry(K key) {
		int bottom = binSearch(key, 0 , list.size()-1);
		if(bottom >= list.size()-1) {
			return null;
		}
		if(list.get(bottom).getKey().equals(key)) {
			bottom++;
		}
		return list.get(bottom);
	}

	/**
	 * creates an iterator from a given key go another given key
	 */
	@Override
	public Iterable<Entry<K, V>> subMap(K fromKey, K toKey){
		return new SubMapIterable(fromKey, toKey);
	}

	/**
	 * returns the size of the list
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
		// TODO Auto-generated method stub
		return list.isEmpty();
	}

	/**
	 * searches for a key from a given index to a given index
	 * @param key to search for
	 * @param start of the search
	 * @param end of the search
	 * @return index of entry
	 */
	private int binSearch(K key, int start, int end) {
		int mid = (start + end)/2;
		if(start>end) {
			return start;
		}
		else if(key.compareTo(list.get(mid).getKey())>0) {
			return binSearch(key, mid+1, end);
		}
		else if(key.compareTo(list.get(mid).getKey())<0) {
			return binSearch(key, start, mid-1);
		}
		else {
			return mid;
		}
	}

	/**
	 * iterable over a submap of given keys
	 * @author sambreuer
	 *
	 */
	private class SubMapIterable implements Iterable<Entry<K,V>> {
		K fromkey;
		K tokey;
		public SubMapIterable(K fromkey, K tokey) {
			this.fromkey = fromkey;
			this.tokey = tokey;
		}
		@Override
		public Iterator<Entry<K, V>> iterator() {
			int firstindex = binSearch(fromkey,0,list.size()-1);
			int lastindex = binSearch(tokey,0,list.size()-1);
			return new SubMapIterator(firstindex, lastindex);
		}
	}
	
	/**
	 * iterator over a submap of given keys
	 * @author sambreuer
	 *
	 */
	private class SubMapIterator implements Iterator<Entry<K,V>>{
		int firstindex;
		int lastindex;
		@Override
		public boolean hasNext() {
			return lastindex > firstindex;
		}
		@Override
		public Entry<K, V> next() {
			mapEntry<K,V> ret = (mapEntry<K, V>) list.get(firstindex);
			firstindex = firstindex+1;
			return ret;
		}
		public SubMapIterator(int firstindex, int lastindex){
			this.firstindex = firstindex;
			this.lastindex = lastindex;
		}
	}
	
	/**
	 * @return iterator of the list
	 */
	public Iterator<Entry<K, V>> iterator() {
		// TODO Auto-generated method stub
		return list.iterator();
	}
}
