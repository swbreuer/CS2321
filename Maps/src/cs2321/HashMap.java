package cs2321;

import net.datastructures.*;

public class HashMap<K, V> extends AbstractMap<K,V> implements Map<K, V> {
	
	/* Use Array of UnorderedMap<K,V> for the Underlying storage for the map of entries.
	 * 
	 */
	private UnorderedMap<K,V>[]  table;
	int 	size;  // number of mappings(entries) 
	int 	capacity; // The size of the hash table. 
	int     DefaultCapacity = 17; //The default hash table size
	
	/* Maintain the load factor <= 0.75.
	 * If the load factor is greater than 0.75, 
	 * then double the table, rehash the entries, and put then into new places. 
	 */
	double  loadfactor= 0.75;  
	
	/**
	 * Constructor that takes a hash size
	 * @param hashtable size: the number of buckets to initialize 
	 */
	public HashMap(int hashtablesize) {
		capacity = hashtablesize;
		table = (UnorderedMap<K, V>[]) new UnorderedMap[hashtablesize];
	}
	
	/**
	 * Constructor that takes no argument
	 * Initialize the hash table with default hash table size: 17
	 */
	public HashMap() {
		capacity = DefaultCapacity;
		table = (UnorderedMap<K, V>[]) new UnorderedMap[capacity];
	}
	
	/* This method should be called by map an integer to the index range of the hash table 
	 */
	private int hashValue(K key) {
		return Math.abs(key.hashCode()) % capacity;
	}
	
	/*
	 * The purpose of this method is for testing if the table was doubled when rehashing is needed. 
	 * Return the the size of the hash table. 
	 * It should be 17 initially, after the load factor is more than 0.75, it should be doubled.
	 */
	public int tableSize() {
		return capacity;
	}
	
	
	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size==0;
	}

	@Override
	public V get(K key) {
		UnorderedMap<K,V> temp = table[hashValue(key)];
		if(temp == null) {
			temp = new UnorderedMap<K,V>();
			table[hashValue(key)]=temp;
		}
		return temp.get(key);
	}

	@Override
	public V put(K key, V value) {
		if(size*loadfactor>capacity) {
			remake();
		}
		UnorderedMap<K,V> temp = table[hashValue(key)];
		if(temp == null) {
			temp = new UnorderedMap<K,V>();
			table[hashValue(key)]=temp;
		}
		int size = temp.size();
		V ret = temp.put(key,value);
		size +=(temp.size()-size);
		return ret;
	}

	@Override
	public V remove(K key) {
		UnorderedMap<K,V> temp = table[hashValue(key)];
		if(temp == null) {
			temp = new UnorderedMap<K,V>();
			table[hashValue(key)]=temp;
		}
		int size = temp.size();
		V value = temp.remove(key);
		size -=(size-temp.size());
		return value;
	}

	@Override
	public Iterable<Entry<K, V>> entrySet() {
		ArrayList<Entry<K,V>>  temp = new ArrayList<Entry<K,V>>();
		for(int h=0; h< capacity; h++) {
			if(table[h]!=null) {
				for(Entry<K,V> entry : table[h].entrySet()) {
					temp.addLast(entry);
				}
			}
		}
		// TODO Auto-generated method stub
		return temp;
	}
	
	private void remake() {
		capacity = capacity*2;
		UnorderedMap<K,V>[]  temp = (UnorderedMap<K, V>[]) new UnorderedMap[capacity];
		int i = 0;
		for(UnorderedMap<K,V> element: table) {
			temp[i]=element;
		}
		table = (UnorderedMap<K, V>[]) new UnorderedMap[capacity];
		for(UnorderedMap<K,V> element: temp) {
			for(K key: element.keySet()) {
				put(key,element.get(key));
			}
		}
	}
}
