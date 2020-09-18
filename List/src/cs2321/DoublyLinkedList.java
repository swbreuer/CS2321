package cs2321;
import java.util.Iterator;

import net.datastructures.Position;
import net.datastructures.PositionalList;


public class DoublyLinkedList<E> implements PositionalList<E> {
	node head;
	node tail;
	int size;

	/**
	 * @author sambreuer
	 * 
	 * linked list nodes, contain next, last, element
	 */
	private class node implements Position<E>{
		E element;
		node next;
		node last;
		
		@Override
		public E getElement() throws IllegalStateException {
			return element;
		}
		
		public node(){
			next = null;
			last = null;
			element = null;
		}
		 /**
		  * sets the next node after this
		  * attaches next node to rest of list
		  * @param node
		  */
		public void setnext(node node) {
			node.next = this.next;
			node.last = this;
			this.next.last = node;
			this.next = node;
			size++;
		}
		 /**
		  * sets the node before this
		  * attaches last node to rest of list
		  * @param node
		  */
		public void setlast(node node) {
			node.last = this.last;
			node.next = this;
			this.last.next = node;
			this.last = node;
			size++;
		}
	}

	public DoublyLinkedList() {
		head = null;
		tail = null;
		size = 0;
	}

	/**
	 * @return number of elements in list
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * @return if the list is empty
	 */
	@Override
	public boolean isEmpty() {
		return size==0;
	}

	/**
	 * @return first node in list
	 */
	@Override
	public Position<E> first() {
		return head;
	}

	/**
	 * @return last node in list
	 */
	@Override
	public Position<E> last() {
		return tail;
	}

	/**
	 * @throws IllegalArgumentException if a position of type other than node is given
	 * @return node before given node
	 */
	@Override
	public Position<E> before(Position<E> p) throws IllegalArgumentException {
		node test = new node();
		if(!(p.getClass().isInstance(test))) {
			throw new IllegalArgumentException();
		}
		node node = (node) p;
		return node.last;
	}

	/**
	 * @throws IllegalArgumentException if a position of type other than node is given
	 * @param node to get the next node of
	 * @return node after given node
	 */
	@Override
	public Position<E> after(Position<E> p) throws IllegalArgumentException {
		node test = new node();
		if(!(p.getClass().isInstance(test))) {
			throw new IllegalArgumentException();
		}
		node node = (node) p;
		return node.next;
	}
	
	/**
	 * adds node with given element to the beginning of the list
	 * @param E element
	 * @return added node
	 */
	@Override
	public Position<E> addFirst(E e) {
		node newnode = new node();
		newnode.element = e;
		newnode.next = head;
		
		//if there is no head, this node is the head
		if(head == null) {
			head = newnode;
		}
		
		else {
			head.last = newnode;
			newnode.next = head;
			head = newnode;
		}
		
		// if there is no tail, add the node after this as tail
		if(tail == null) {
			tail = head.next;
		}
		
		size++;
		return newnode;
	}

	/**
	 * adds node with given element to the end of the list
	 * @param E element
	 * @return added node
	 */
	@Override
	public Position<E> addLast(E e) {
		node newnode = new node();
		newnode.element = e;
		
		//if there is no tail, this node is the tail
		if(tail == null) {
			tail = newnode;
		}
		
		else {
			tail.next = newnode;
			newnode.last = tail;
			tail = newnode;
		}
		
		// if there is no head, add the node before this as head
		if(head == null) {
			head = tail.last;
		}
		
		size++;
		return newnode;
	}

	/**
	 * adds a new node with element E before a given node
	 * @throws IllegalArgumentException if input of type other than node is given
	 * @param node to add before
	 * @param element to add 
	 * @return node added to list
	 */
	@Override
	public Position<E> addBefore(Position<E> p, E e) throws IllegalArgumentException {
		node test = new node();
		if(!(p.getClass().isInstance(test))) {
			throw new IllegalArgumentException();
		}
		node after = (node) p;
		node newnode = new node();
		newnode.element = e;
		after.setlast(newnode);
		return newnode;
	}

	/**
	 * adds a new node with element E after a given node
	 * @throws IllegalArgumentException if input of type other than node is given
	 * @param node to add after
	 * @param element to add 
	 * @return node added to list
	 */
	@Override
	public Position<E> addAfter(Position<E> p, E e) throws IllegalArgumentException {
		node test = new node();
		if(!(p.getClass().isInstance(test))) {
			throw new IllegalArgumentException();
		}
		node before = (node) p;
		node newnode = new node();
		newnode.element = e;
		before.setnext(newnode);
		return newnode;
	}

	/**
	 * sets a given node's element to a given element and returns old element
	 * @param node to change
	 * @param element to set node to
	 * @return element that was in that node previously
	 */
	@Override
	public E set(Position<E> p, E e) throws IllegalArgumentException {
		node test = new node();
		if(!(p.getClass().isInstance(test))) {
			throw new IllegalArgumentException();
		}
		node temp = (node) p;
		E output = temp.getElement();
		temp.element = e;
		return output;
	}

	/**
	 * removes a node from the list and returns the element that was in the node
	 * @throws IllegalArgumentException
	 * @param node to remove from list
	 * @return element in removed node
	 */
	@Override
	public E remove(Position<E> p) throws IllegalArgumentException {
		node test = new node();
		if(!(p.getClass().isInstance(test))) {
			throw new IllegalArgumentException();
		}
		node node = (node) p;
		E output = node.element;
		
		if(size == 1) {
			head = null;
			tail = null;
		}

		else {
			//if the node is the last one, drop the tail off the back
			if(node == tail) {
				tail.last.next = null;
				tail = tail.last;
			}
			//if the node is the head, drop the head off the front
			else if(node == head) {
				head.next.last = null;
				head = head.next;
			}
			//normal remove
			else {
				node.last.next = node.next;
				node.next.last = node.last;
			}
		}
		size--;
		return output;
	}

	/**
	 * removes the first node from the list and returns the element that was in the node
	 * @throws IllegalArgumentException
	 * @return element in removed node
	 */
	public E removeFirst() throws IllegalArgumentException {
		if(head == null | tail == null) {
			throw new IllegalArgumentException();
		}
		
		E output;
		output = tail.getElement();
		
		//if head is the only element, make it null
		if(head.next==null) {
			head = null;
			size--;
			return output;
		}
		
		head.next.last = null;
		head = head.next;
		
		size--;
		return output;
	}

	/**
	 * removes the last node from the list and returns the element that was in the node
	 * @throws IllegalArgumentException
	 * @return element in removed node
	 */
	public E removeLast() throws IllegalArgumentException {
		if(tail == null) {
			throw new IllegalArgumentException();
		}
		
		E output;
		output = tail.getElement();
		
		//if tail is the only element, make it null
		if(tail.last==null) {
			tail = null;
			size--;
			return output;
		}
		
		tail.last.next = null;
		tail = tail.last;
		
		size--;
		return output;
	}

	/**
	 * @return new element iterator
	 */
	@Override
	public Iterator<E> iterator() {
		return new ElementIterator();
	}

	/**
	 * @author sambreuer
	 * 
	 * iterator that returns the elements of the node
	 */
	private class ElementIterator implements Iterator<E>{
		node cursor;
		
		public ElementIterator() {
			cursor = new node();
			cursor.next = head;
		}

		/**
		 * @return if there is a next node
		 */
		@Override
		public boolean hasNext() {
			return cursor.next!=null;
		}

		/**
		 * @return next element
		 */
		@Override
		public E next() {
			cursor = cursor.next;
			return cursor.getElement();
		}
	}

	/**
	 * @return new element iterator
	 */
	@Override
	public Iterable<Position<E>> positions() {
		return  new NodeIterable();
	}
	
	/**
	 * @author sambreuer
	 * 
	 * iterable to make the iterator work
	 */
	private class NodeIterable implements Iterable<Position<E>>{
		
		@Override
		public Iterator<Position<E>> iterator() {
			return new NodeIterator();
		}
		
	}
	
	/**
	 * @author sambreuer
	 * 
	 * iterator that returns the nodes themselves
	 */
	private class NodeIterator implements Iterator<Position<E>>{
		node cursor;
		
		public NodeIterator() {
			cursor = new node();
			cursor.next = head;
		}
		
		/**
		 * @return if there is a next node
		 */
		@Override
		public boolean hasNext() {
			return cursor.next!=null;
		}

		/**
		 * @return next node
		 */
		@Override
		public Position<E> next() {
			cursor = cursor.next;
			return cursor;
		}
	}
}