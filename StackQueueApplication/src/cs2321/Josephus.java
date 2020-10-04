package cs2321;

public class Josephus {
	/**
	 * All persons sit in a circle. When we go around the circle, initially starting
	 * from the first person, then the second person, then the third... 
	 * we count 1,2,3,.., k-1. The next person, that is the k-th person is out. 
	 * Then we restart the counting from the next person, go around, the k-th person 
	 * is out. Keep going the same way, when there is only one person left, she/he 
	 * is the winner. 
	 *  
	 *  loops through the input using a queue and counter to dequeue, check if the counter is equal 
	 *  to the K input then either put the element into the output or back onto the queue
	 *  
	 * @parameter persons  an array of string which contains all player names.
	 * @parameter k  an integer specifying the k-th person will be kicked out of the game
	 * @return return a doubly linked list in the order when the players were out of the game. 
	 *         the last one in the list is the winner.  
	 */
	public DoublyLinkedList<String> order(String[] persons, int k ) {
		CircularArrayQueue<String> temp = new CircularArrayQueue(persons.length);
		DoublyLinkedList<String> order = new DoublyLinkedList();
		
		//loads input into the queue
		for(String person: persons) {
			temp.enqueue(person);
		}
		
		//josephus code
		while(!temp.isEmpty()){
			/*loops through k, taking off element, checking if index is k, adding element back if it isn't
			 * else adds element to the DLL order to be output
			 */
			for(int i = 1; i<= k; i++) {
				String test = temp.dequeue();
				
				if(i == k) {
					order.addLast(test);
					break;
				}
				
				temp.enqueue(test);
			}
		}
		return order;
	}	
}
