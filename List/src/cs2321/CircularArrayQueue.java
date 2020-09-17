/**
 * 
 */
package cs2321;

import net.datastructures.Queue;

/**
 * @author ruihong-adm
 * @param <E>
 *
 */

public class CircularArrayQueue<E> implements Queue<E> {
	E[] queue;
	int elements;
	int tail;
	int head;

	/**
	 * creates a circular array list with
	 * 	  array queue to hold data
	 *    int elements to hold number of elements
	 *    int tail, index of queue tail in array
	 *    int head, index of queue head in array
	 *  
	 *  @param queueSize - capacity of queue
	 */
	public CircularArrayQueue(int queueSize) {
		queue = (E[]) new Object[queueSize];
		elements = 0;
		head = 0;
		tail = 0;
	}
	
	/**
	 * returns the number of elements in the queue
	 * 
	 * @return elements : number of elements
	 */
	@Override
	public int size() {
		return elements;
	}

	/**
	 * checks if there are not elements in the queue
	 * 
	 * @return elements == 0
	 */
	@Override
	public boolean isEmpty() {
		return elements==0;
	}

	/**
	 * adds an element to the end of the queue
	 * 
	 * @param e : element to be added 
	 */
	@Override
	public void enqueue(E e) throws IllegalStateException{
		if(elements == queue.length) {
			throw new IllegalStateException();
		}
		
		queue[tail]=e;
		if(tail+1 == queue.length) {
			if(head > 0) {
				tail = 0;
			}
		}
		else {
			tail++;
		}
		elements++;
	}

	/**
	 * returns the first element of the queue
	 * 
	 * @return first element of queue
	 */
	@Override
	public E first() {
		return queue[head];
	}

	/**
	 * removes and returns first element of queue
	 * 
	 * @return first : first element of queue
	 */
	@Override
	public E dequeue() {
		E first = queue[head];

		if(head+1 == queue.length) {
			head = 0;
		}
		else {
			head++;
		}
		elements--;
		return first;
	}
    
}
