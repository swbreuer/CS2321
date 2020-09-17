package cs2321;
import java.util.Iterator;

import net.datastructures.Position;
import net.datastructures.PositionalList;


public class DoublyLinkedList<E> implements PositionalList<E> {
	node<E> head;
	node<E> tail;
	int size;

	static class node<E> implements Position<E>{
		E element;
		node<E> next;
		node<E> last;
		
		@Override
		public E getElement() throws IllegalStateException {
			return element;
		}
		
		public node(){
			next = null;
			last = null;
			element = null;
		}
	}

	public DoublyLinkedList() {
		head = null;
		tail = null;
		size = 0;
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
	public Position<E> first() {
		return head;
	}

	@Override
	public Position<E> last() {
		return tail;
	}

	private node<E> find(E p) throws IllegalArgumentException{
		node<E> node = head;
		while(node.getElement()!=p & node.next!=null) {
			node = node.next;
		}
		if(node.getElement()!=p) {
			throw new IllegalArgumentException();
		}
		return node;
	}
	
	@Override
	public Position<E> before(Position<E> p) throws IllegalArgumentException {
		node<E> node = find(p.getElement());
		return node.last;
	}

	@Override
	public Position<E> after(Position<E> p) throws IllegalArgumentException {
		node<E> node = find(p.getElement());
		return node.next;
	}

	@Override
	public Position<E> addFirst(E e) {
		node<E> newnode = new node<E>();
		newnode.element = e;
		newnode.next = head;
		
		if(head == null) {
			head = newnode;
		}
		
		else {
			head.last = newnode;
			newnode.next = head;
			head = newnode;
		}
		
		if(tail == null) {
			tail = head.next;
		}
		
		size++;
		return newnode;
	}

	@Override
	public Position<E> addLast(E e) {
		node<E> newnode = new node<E>();
		newnode.element = e;
		if(tail == null) {
			tail = newnode;
		}
		
		else {
			tail.next = newnode;
			newnode.last = tail;
			tail = newnode;
		}
		if(head == null) {
			head = tail.last;
		}
		
		size++;
		return newnode;
	}

	@Override
	public Position<E> addBefore(Position<E> p, E e) throws IllegalArgumentException {
		node<E> after = find(p.getElement());
		node<E> newnode = new node<E>();
		newnode.element = e;
		newnode.next = after;
		newnode.last = after.last;
		after.last.next = newnode;
		after.last = newnode;
		size++;
		return newnode;
	}

	@Override
	public Position<E> addAfter(Position<E> p, E e) throws IllegalArgumentException {
		node<E> before = find(p.getElement());
		node<E> newnode = new node<E>();
		newnode.element = e;
		newnode.next = before.next;
		newnode.last = before;
		before.next.last = newnode;
		before.next = newnode;
		size++;
		return newnode;
	}

	@Override
	public E set(Position<E> p, E e) throws IllegalArgumentException {
		node<E> temp = find(p.getElement());
		E output = temp.getElement();
		temp.element = e;
		return output;
	}

	@Override
	public E remove(Position<E> p) throws IllegalArgumentException {
		node<E> node = find(p.getElement());
		E output = node.element;
		
		if(size == 1) {
			head = null;
			tail = null;
		}
		else {
			if(node == tail) {
				tail.last.next = null;
				tail = tail.last;
			}
			else if(node == head) {
				head.next.last = null;
				head = head.next;
			}
		
			else {
				node.last.next = node.next;
				node.next.last = node.last;
			}
		}
		size--;
		return output;
	}

	public E removeFirst() throws IllegalArgumentException {
		if(head == null | tail == null) {
			throw new IllegalArgumentException();
		}
		E output;
		if(head == tail) {
			output = head.getElement();
			head = null;
			tail = null;
		}
		
		else {
			output = head.next.element;
			head.next.next.last = head;
			head.next = head.next.next;
		}
		
		size--;
		return output;
	}
	
	public E removeLast() throws IllegalArgumentException {
		if(tail == null) {
			throw new IllegalArgumentException();
		}
		
		E output;
		if(head == tail) {
			output = head.getElement();
			head = null;
			tail = null;
		}
		
		else {
			output = tail.last.element;
			tail.last.last.next = head;
			tail.last = tail.last.last;
		}
		
		size--;
		return output;
	}

	@Override
	public Iterator<E> iterator() {
		return new ElementIterator();
	}

	@Override
	public Iterable<Position<E>> positions() {
		return  new NodeIterable();
	}
	
	private class NodeIterable implements Iterable<Position<E>>{
		
		@Override
		public Iterator<Position<E>> iterator() {
			return new NodeIterator();
		}
		
	}
	
	private class NodeIterator implements Iterator<Position<E>>{
		node<E> cursor;
		
		public NodeIterator() {
			cursor = new node<E>();
			cursor.next = head;
		}
		
		@Override
		public boolean hasNext() {
			return cursor.next!=null;
		}

		@Override
		public Position<E> next() {
			cursor = cursor.next;
			return cursor;
		}
	}
	
	private class ElementIterator implements Iterator<E>{
		node<E> cursor;
		public ElementIterator() {
			cursor = new node<E>();
			cursor.next = head;
		}
		
		@Override
		public boolean hasNext() {
			return cursor.next!=null;
		}

		@Override
		public E next() {
			cursor = cursor.next;
			return cursor.getElement();
		}
	}
}
