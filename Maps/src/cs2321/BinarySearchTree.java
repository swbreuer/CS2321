package cs2321;

import java.util.Iterator;

import net.datastructures.Entry;
import net.datastructures.Position;
import net.datastructures.SortedMap;


public class BinarySearchTree<K extends Comparable<K>,V> extends AbstractMap<K,V> implements SortedMap<K,V> {
	
	/* all the data will be stored in the tree*/
	LinkedBinaryTree<Entry<K,V>> tree; 
	int size;  //the number of entries (mappings)
	Position<Entry<K,V>> pointer;
	
	/* 
	 * default constructor
	 */
	public BinarySearchTree() {
		tree = new LinkedBinaryTree<Entry<K,V>>();
		pointer = tree.root;
	}
	
	/* 
	 * Return the tree. The purpose of this method is purely for testing. 
	 * You don't need to make any change. Just make sure to use variable tree to store your entries. 
	 */
	public LinkedBinaryTree<Entry<K,V>> getTree() {
		return tree;
	}
	//returns size of binary search tree
	@Override
	public int size(){
		return tree.size();
	}
	/**
	 * gets an element with a given key, or if that key does not exist, returns null
	 * @param key
	 * @return value of node with key
	 */
	@TimeComplexity("O(lg n)")
	@Override
	public V get(K key) {
		int found = search(key);
		if(found!=0) {
			return null;
		}
		V ret = pointer.getElement().getValue();
		pointer = tree.root();
		return ret;
	}

	/**
	 * gets an element with a given key, or if that key does not exist, returns null
	 * @param key
	 * @return value of node with key
	 */
	@TimeComplexity("O(lg n)")
	@Override
	public V put(K key, V value) {
		V ret = null;
		mapEntry<K,V> element = new mapEntry<K,V>(key,value);
		if(tree.root.getElement() == null) {
			tree.addRoot(element);
			return value;
		}
		int find = search(key);
		switch(find) {
			case 0: ret = pointer.getElement().getValue();
					tree.setElement(pointer, element);
					pointer = tree.root();
					return ret;
			
			case 1: tree.addLeft(pointer, element).getElement().getValue();
					pointer = tree.root();
					return null;
			
			case 2: tree.addRight(pointer, element).getElement().getValue();
					pointer = tree.root();
					return null;
		}
		pointer = tree.root();
		return ret;
	}

	/**
	 * removes a node with a given key and returns the value of that key or null if none exists
	 * @param key
	 * @param value of corresponding node
	 */
	@TimeComplexity("O(lg n)")
	@Override
	public V remove(K key) {
		int found = search(key);
		if(found!=0) {
			return null;
		}
		if(tree.numChildren(pointer)==0) {
			return tree.remove(pointer).getValue();
		}
		V ret = pointer.getElement().getValue();
		
		if(tree.right(pointer)==null | tree.left(pointer)==null) {
			removeExtParent();
		}
		else {
			Position<Entry<K,V>> head = pointer;
			higherEntry(key);
			tree.setElement(head, pointer.getElement());
			removeExtParent();
		}
		pointer = tree.root();
		return ret;
	}

	/**
	 * creates iterable of all entries
	 */
	@Override
	public Iterable<Entry<K, V>> entrySet() {
		return new inorderIterable(firstEntry().getKey(),firstEntry().getKey());
	}

	/**
	 * returns lowest keyed entry
	 */
	@Override
	public Entry<K, V> firstEntry() {
		while(tree.left(pointer)!=null) {
			pointer = tree.left(pointer);
		}
		return pointer.getElement();
	}

	/**
	 * returns highest keyed entry
	 */
	@Override
	public Entry<K, V> lastEntry() {
		while(tree.right(pointer)!=null) {
			pointer = tree.right(pointer);
		}
		return pointer.getElement();
	}

	/**
	 * returns entry just higher than given key
	 * @param key
	 * @return entry with key just higher than given
	 */
	@Override
	public Entry<K, V> ceilingEntry(K key) {
		Entry<K,V> greater = greaterSearch(key, pointer);
		return greater;
	}

	/**
	 * returns entry just lower than given key
	 * @param key
	 * @return entry with key just lower than given
	 */
	@Override
	public Entry<K, V> floorEntry(K key)  {
		Entry<K,V> greater = lesserSearch(key, pointer);
		return greater;
	}

	/**
	 * returns entry strictly lower than given key
	 * @param key
	 * @return entry with key strictly lower than given
	 */
	@Override
	public Entry<K, V> lowerEntry(K key) {
		Entry<K,V> greater = lesserSearch(key, pointer);
		if(greater.getKey()==key) {
			return null;
		}
		return greater;
	}
	
	/**
	 * returns entry strictly higher than given key
	 * @param key
	 * @return entry with key strictly higher than given
	 */
	@Override
	public Entry<K, V> higherEntry(K key){
		Entry<K,V> greater = greaterSearch(key, pointer);
		if(greater.getKey()==key) {
			return null;
		}
		return greater;
	}

	/**
	 * creates iterator between two keys
	 * @param fromkey start of submap
	 * @param tokey end of submap
	 * @return iterable that iterates over elements with keys between two given keys
	 */
	@Override
	public Iterable<Entry<K, V>> subMap(K fromKey, K toKey)
			throws IllegalArgumentException {
		return new inorderIterable(fromKey,toKey);
	}

	/**
	 * returns if tree is empty
	 */
	@Override
	public boolean isEmpty() {
		return tree.isEmpty();
	}

	/**
	 * searches for node with given node, returns int determining which child the given key would fit in else
	 * @param key
	 * @return 0 = found, 1 = left child, 2 = right child, -1 = does not exist
	 */
	private int search(K key) {
		pointer = tree.root();
		do {
			if(pointer.getElement().getKey().compareTo(key)>0) {
				if(tree.left(pointer)==null) {
					return 1;
				}
				pointer = tree.left(pointer);
			}
			else if(pointer.getElement().getKey().compareTo(key)<0) {
				if(tree.right(pointer)==null) {
					return 2;
				}
				pointer = tree.right(pointer);
			}
			else if(pointer.getElement().getKey().compareTo(key)==0) {
				return 0;
			}
			else {
				return -1;
			}
		} while(true);
	}
	
	 /**
	  * finds node greater than or equal to given key
	  * @param key to search for
	  * @param head of the search algorithm
	  * @return entry that has the node greater than or equal to given key
	  */
	private Entry<K, V> greaterSearch(K key, Position<Entry<K, V>> head) {
	 if(search(key)==-1) {
		 return null;
	 }
	 if (tree.isInternal(pointer)) {
		 return pointer.getElement(); // exact match
	 }
	 while (!tree.isRoot(pointer)) { 
		 if (pointer == tree.right(tree.parent(pointer))) {
			 return tree.parent(pointer).getElement(); // parent has next lesser key
		 }
		 else {
			 pointer = tree.parent(pointer);
		 } 
	 }
	 return null;
	}
	
	/**
	  * finds node less than or equal to given key
	  * @param key to search for
	  * @param head of the search algorithm
	  * @return entry that has the node less than or equal to given key
	  */
	private Entry<K, V> lesserSearch(K key, Position<Entry<K, V>> head) {
	if(search(key)==-1) {
		return null;
	}
	if (tree.isInternal(pointer) && tree.left(pointer)!=null) {
		pointer = tree.left(pointer);
		return treeMax().getElement( ); // this is the predecessor to p
	}
	// otherwise, we had failed search, or match with no left child
	while (!tree.isRoot(pointer)) { 
		if (pointer == tree.right(tree.parent(pointer))) {
			return tree.parent(pointer).getElement( ); // parent has next lesser key
		}
		else {
			pointer = tree.parent(pointer);
		}
	}
	return null;
	}
	
	/**
	 * 
	 * @return
	 */
	private Position<Entry<K, V>> treeMax(){
		Position<Entry<K,V>> walk = pointer;
		while (tree.isInternal(walk)) {
			walk = tree.right(walk);
		}
		return tree.parent(walk);
	}
	
	/**
	 * removes an external node and its parent
	 */
	private void removeExtParent() {
		if(tree.isExternal(tree.right(pointer))) {
			tree.setElement(pointer, tree.right(pointer).getElement());
			tree.remove(tree.right(pointer));
		}
		else {
			tree.setElement(pointer, tree.left(pointer).getElement());
			tree.remove(tree.left(pointer));
		}
		
		pointer = tree.root;
	}
	
	/**
	 * performs an inorder traversal
	 * @param list to add nodes to
	 * @param keystart key to start traversal list at
	 * @param keyend key to end traversal list at
	 * @param node node to start traversal from
	 */
	private void inorder(ArrayList<Entry<K,V>> list, K keystart, K keyend, Position<Entry<K, V>> node) {
		if(node.getElement().getKey().equals(keyend) | node.getElement().getKey().equals(keystart)) {
			list.addLast(node.getElement());
			return ;
		}
		inorder(list,keystart,keyend,tree.left(node));
		list.addLast(node.getElement());
		inorder(list,keystart,keyend,tree.right(node));
	}
	
	/**
	 * iterable for the tree in order from a given key to another
	 * @author sambreuer
	 *
	 */
	private class inorderIterable implements Iterable<Entry<K,V>>{
		K firstkey;
		K lastkey;
		@Override
		public Iterator<Entry<K,V>> iterator(){
			return new inorderIterator(firstkey, lastkey);
		}
		
		inorderIterable(K firstkey, K lastkey){
			this.firstkey = firstkey;
			this.lastkey = lastkey;
		}
	}
	
	/**
	 * iterator for the tree in order from a given key to another
	 * @author sambreuer
	 *
	 */
	private class inorderIterator implements Iterator<Entry<K,V>>{
		ArrayList<Entry<K,V>> list;
		int index = 0;
		@Override
		public boolean hasNext() {
			return index>=list.size();
		}

		@Override
		public Entry<K, V> next() {
			// TODO Auto-generated method stub
			Entry<K,V> ret = list.get(index);
			index = index++;
			return ret;
		}
		
		inorderIterator(K firstkey, K lastkey){
			list = new ArrayList<Entry<K,V>>();
			inorder(list, firstkey, lastkey, tree.root());
		}
	}
}
