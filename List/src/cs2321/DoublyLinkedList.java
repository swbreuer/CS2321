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
			// TODO Auto-generated method stub
			return element;
		}
		
		public node(){
			next = null;
			last = null;
			element = null;
		}
	}

	public DoublyLinkedList() {
		// TODO Auto-generated constructor stub
		head = new node<E>();
		tail = new node<E>();
		head.next = tail;
		tail.last = head;
		size = 0;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return size==0;
	}

	@Override
	public Position<E> first() {
		// TODO Auto-generated method stub
		return head.next;
	}

	@Override
	public Position<E> last() {
		// TODO Auto-generated method stub
		return tail.last;
	}

	@Override
	public Position<E> before(Position<E> p) throws IllegalArgumentException {
		node<E> temp = tail;
		while(temp.getElement() != p.getElement() & temp.last != null) {
			temp = temp.last;
		}
		if(temp.getElement()==p.getElement()) {
			return temp.last;
		}
		else {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public Position<E> after(Position<E> p) throws IllegalArgumentException {
		node<E> temp = head;
		while(temp.next.getElement() != p.getElement()& temp.next != null) {
			temp = temp.next;
		}
		if(temp.getElement()==p.getElement()) {
			return temp.next;
		}
		else {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public Position<E> addFirst(E e) {
		// TODO Auto-generated method stub
		node<E> newnode = new node<E>();
		newnode.element = e;
		newnode.next = head.next;
		newnode.last = head;
		head.next.last = newnode;
		head.next = newnode;
		size++;
		return newnode;
	}

	@Override
	public Position<E> addLast(E e) {
		// TODO Auto-generated method stub
		node<E> newnode = new node<E>();
		newnode.element = e;
		newnode.next = tail;
		newnode.last = tail.last;
		tail.last.next = newnode;
		tail.last = newnode;
		size++;
		return newnode;
	}

	@Override
	public Position<E> addBefore(Position<E> p, E e) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		node<E> before = (node<E>) before(p);
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
	public Position<E> addAfter(Position<E> p, E e) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		node<E> after = (node<E>) after(p);
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
	public E set(Position<E> p, E e) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		node<E> node = (node<E>) before(p);
		E output = node.element;
		node.element = e;
		return output;
	}

	@Override
	public E remove(Position<E> p) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		node<E> node = ((node<E>) before(p)).next;
		E output = node.element;
		node.last.next = node.next;
		node.next.last = node.last;
		size--;
		return output;
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Position<E>> positions() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public E removeFirst() throws IllegalArgumentException {
		// TODO Auto-generated method stub
		if(head.next == tail) {
			throw new IllegalArgumentException();
		}
		E output = head.next.element;
		head.next.next.last = head;
		head.next = head.next.next;
		size--;
		return output;
	}
	
	public E removeLast() throws IllegalArgumentException {
		// TODO Auto-generated method stub
		if(tail.last == head) {
			throw new IllegalArgumentException();
		}
		E output = tail.last.element;
		tail.last.last.next = head;
		tail.last = tail.last.last;
		size--;
		return output;
	}

}
