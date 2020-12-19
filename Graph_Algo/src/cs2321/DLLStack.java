package cs2321;

import net.datastructures.Stack;

public class DLLStack<E> implements Stack<E> {
	
	DoublyLinkedList<E> stack;

	public DLLStack(){
		stack = new DoublyLinkedList<E>();
	}
	
	/**
	 * @return number of elements in the stack
	 */
	@Override
	public int size() {
		return stack.size;
	}

	/**
	 * @return if the stack is empty
	 */
	@Override
	public boolean isEmpty() {
		return stack.isEmpty();
	}

	/**
	 * add element to the stack
	 * @param element to add
	 */
	@Override
	public void push(E e) {
		stack.addFirst(e);
	}

	/**
	 * returns the first element of the stack without removing it
	 * @return returns the first element of the stack
	 */
	@Override
	public E top() {
		if(stack.first()==null) {
			return null;
		}
		return stack.first().getElement();
	}

	/**
	 * removes then returns first element of the stack
	 * @return returns element previously at the top of the stack
	 */
	@Override
	public E pop() {
		return stack.removeFirst();
	}

}
