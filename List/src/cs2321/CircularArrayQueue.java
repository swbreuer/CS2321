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

	public CircularArrayQueue(int queueSize) {
		// TODO: create a new queue with the specified size
		queue = (E[]) new Object[queueSize];
		elements = 0;
	}
	
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return elements;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return elements==0;
	}

	@Override
	public void enqueue(E e) {
		// TODO Auto-generated method stub
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

	@Override
	public E first() {
		return queue[head];
	}

	@Override
	public E dequeue() {
		// TODO Auto-generated method stub
		E output = queue[head];

		if(head+1 == queue.length) {
			head = 0;
		}
		else {
			head++;
		}
		elements--;
		return output;
	}
    
}
