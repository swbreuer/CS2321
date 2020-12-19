package cs2321;

import net.datastructures.Edge;
import net.datastructures.Vertex;

/**
 * @author Ruihong Zhang
 * Reference textbook R14.16 P14.81 
 *
 */
public class Travel {
	
	AdjListGraph<String,Integer> map;
	/**
	 * @param routes: Array of routes between cities. 
	 *                routes[i][0] and routes[i][1] represent the city names on both ends of the route. 
	 *                routes[i][2] represents the cost in string type. 
	 *                Hint: In Java, use Integer.valueOf to convert string to integer. 
	 */
	public Travel(String [][] routes) {
		map = new AdjListGraph<String,Integer>(false);
		for(int i = 0; i < routes.length; i++) {
			if(map.vertMap().get(routes[i][0])==null) {
				map.insertVertex(routes[i][0]);
			}
			if(map.vertMap().get(routes[i][1])==null) {
				map.insertVertex(routes[i][1]);
			}
			map.insertEdge(map.vertMap().get(routes[i][0]),map.vertMap().get(routes[i][1]), Integer.valueOf(routes[i][2]));
		}
	}
	
	/**
	 * @param departure: the departure city name 
	 * @param destination: the destination city name
	 * @return Return the path from departure city to destination using Depth First Search algorithm. 
	 *         The path should be represented as ArrayList or DoublylinkedList of city names. 
	 *         The order of city names in the list should match order of the city names in the path.  
	 *         
	 * @IMPORTANT_NOTE: The outgoing edges should be traversed by the order of the city names stored in
	 *                 the opposite vertices. For example, if V has 3 outgoing edges as in the picture below,
	 *                           V
	 *                        /  |  \
	 *                       /   |    \
	 *                      B    A     F  
	 *              your algorithm below should visit the outgoing edges of V in the order of A,B,F.
	 *              This means you will need to create a helper function to sort the outgoing edges by 
	 *              the opposite city names.
	 *              	              
	 *              See the method sortedOutgoingEdges below. 
	 */
	public Iterable<String> DFSRoute(String departure, String destination ) {
		HashMap<String, Vertex<String>> seen = new HashMap<String,Vertex<String>>();
		DoublyLinkedList<String> path = new DoublyLinkedList<String>();
		
		seen.put(departure, map.vertMap().get(departure));
		path.addLast(departure);
		Vertex<String> head = seen.get(departure);
		
		
		while(!path.last().getElement().equals(destination)) {
			
			String min = null;
			
			for(Edge E : sortedOutgoingEdges(head)) {
				Vertex<String> search = map.opposite(head, E);
				
				if(seen.get(search.getElement())!=null) {
					continue;
				}
				
				min = search.getElement();
				break;
				
			}
			
			seen.put(min, map.vertMap().get(min));
			path.addLast(min);
			head = seen.get(min);
			
		}
		return path;
	}
	
	
	
	/**
	 * @param departure: the departure city name 
	 * @param destination: the destination city name
     * @return Return the path from departure city to destination using Breadth First Search algorithm. 
	 *         The path should be represented as ArrayList or DoublylinkedList of city names. 
	 *         The order of city names in the list should match order of the city names in the path.  
	 *         
	 * @IMPORTANT_NOTE: The outgoing edges should be traversed by the order of the city names stored in
	 *                 the opposite vertices. For example, if V has 3 outgoing edges as in the picture below,
	 *                           V
	 *                        /  |  \
	 *                       /   |    \
	 *                      B    A     F  
	 *              your algorithm below should visit the outgoing edges of V in the order of A,B,F.
	 *              This means you will need to create a helper function to sort the outgoing edges by 
	 *              the opposite city names.
	 *              	             
	 *              See the method sortedOutgoingEdges below. 
	 */
	
	public Iterable<String> BFSRoute(String departure, String destination ) {
		HashMap<String, Vertex<String>> seen = new HashMap<String,Vertex<String>>();
		HashMap<String,Edge<Integer>> tree = new HashMap<String,Edge<Integer>>();
		CircularArrayQueue<String> queue = new CircularArrayQueue<String>(map.numVertices());
		
		seen.put(departure, map.vertMap().get(departure));
		queue.enqueue(departure);
		
		Vertex<String> head;
		
		while(!queue.isEmpty()) {
			
			head = seen.get(queue.dequeue());
			
			for(Edge E : sortedOutgoingEdges(head)) {
				
				Vertex<String> search = map.opposite(head, E);
				
				if(seen.get(search.getElement())!=null) {
					continue;
				}
				
				queue.enqueue(search.getElement());
				tree.put(search.getElement(),E);
				seen.put(search.getElement(), search);
			}
		}
		
		DoublyLinkedList<String> path = new DoublyLinkedList<String>();
		Vertex<String> searchNode = seen.get(destination);
		String last = searchNode.getElement();
		while(searchNode.getElement()!=departure) {
			searchNode = map.opposite(searchNode,tree.get(searchNode.getElement()));
			path.addFirst(searchNode.getElement());
		}
		path.addLast(last);
		
		return path;
	}
	
	/**
	 * @param departure: the departure city name 
	 * @param destination: the destination city name
	 * @param itinerary: an empty DoublylinkedList object will be passed in to the method. 
	 * 	       When a shorted path is found, the city names in the path should be added to the list in the order. 
	 * @return return the cost of the shortest path from departure to destination. 
	 *         
	 * @IMPORTANT_NOTE: The outgoing edges should be traversed by the order of the city names stored in
	 *                 the opposite vertices. For example, if V has 3 outgoing edges as in the picture below,
	 *                           V
	 *                        /  |  \
	 *                       /   |    \
	 *                      B    A     F  
	 *              your algorithm below should visit the outgoing edges of V in the order of A,B,F.
	 *              This means you will need to create a helper function to sort the outgoing edges by 
	 *              the opposite city names.
	 *              
	 *              See the method sortedOutgoingEdges below. 
	 */

	public int DijkstraRoute(String departure, String destination, DoublyLinkedList<String> itinerary ) {

		HashMap<Vertex<String>,Integer> seen = new HashMap<Vertex<String>,Integer>();
		HashMap<String,Edge<Integer>> tree = new HashMap<String,Edge<Integer>>();
		HashMap<String,Edge<Integer>> curPath = new HashMap<String,Edge<Integer>>();
		HeapPQ<Integer,Vertex<String>> queue = new HeapPQ<Integer,Vertex<String>>();
		
		seen.put(map.vertMap().get(departure),0);
		queue.insert(0,map.vertMap().get(departure));
		
		Vertex<String> head;
		
		while(!queue.isEmpty()) {
			
			head = queue.removeMin().getValue();
			if(tree.get(head.getElement())!=null) {
				continue;
			}
			tree.put(head.getElement(), curPath.get(head.getElement()));
			
			for(Edge E : sortedOutgoingEdges(head)) {
				
				Vertex<String> search = map.opposite(head, E);
				if(seen.get(search)== null) {
					seen.put(search,seen.get(head)+(int)E.getElement());
					curPath.put(search.getElement(), E);
				}
				else if(seen.get(search)>(seen.get(head)+(int)E.getElement())) {
					seen.put(search,seen.get(head)+(int)E.getElement());
					curPath.put(search.getElement(), E);
				}

				queue.insert(seen.get(search),search);
			}
		}
		
		Vertex<String> searchNode = map.vertMap().get(destination);
		String last = searchNode.getElement();
		int len = seen.get(searchNode);
		
		while(searchNode.getElement()!=departure) {
			searchNode = map.opposite(searchNode,tree.get(searchNode.getElement()));
			itinerary.addFirst(searchNode.getElement());
		}
		itinerary.addLast(last);
		
		return len;
		
	}
	
	

	/**
	 * I strongly recommend you to implement this method to return sorted outgoing edges for vertex V
	 * You may use any sorting algorithms, such as insert sort, selection sort, etc.
	 * 
	 * @param v: vertex v
	 * @return a list of edges ordered by edge's name
	 */
	public Iterable<Edge<Integer>> sortedOutgoingEdges(Vertex<String> v)  {
		String[] vertlist = new String[map.outDegree(v)];
		QuickSort<String> sorter = new QuickSort<String>();
		DoublyLinkedList<Edge<Integer>> edgelist = new DoublyLinkedList<Edge<Integer>>();
		int i = 0;
		for(Edge E : map.outgoingEdges(v)) {
			vertlist[i] = map.opposite(v, E).getElement().toString();
			i++;
		}
		sorter.sort(vertlist);
		for(String s : vertlist) {
			edgelist.addLast(map.getEdge(map.vertMap().get(s), v));
		}
		return edgelist;
	}
}
