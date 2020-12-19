package cs2321;

import net.datastructures.Edge;
import net.datastructures.Vertex;

/**
 * @author Ruihong Zhang
 * Reference: Textbook R-14.27 on page 679
 *
 */
public class Islands  {

	AdjListGraph<Integer,Integer> graph = new AdjListGraph<Integer,Integer>();
	
	/**
	 * @param numOfIslands: total number of islands. It will be numbered as 0,1,2,...
	 * @param distance: distance[i][j] represents the distance between island[i] and island[j]. 
	 * 					-1 means there is no edge between island[i] and island[j]. 
	 */
	public Islands(int numOfIslands, int distance[][]) {
		for(int i = 0; i<numOfIslands; i++) {
			graph.insertVertex(i);
		}
		for(int i = 0; i<distance.length; i++) {
			for(int j = i; j<distance.length; j++) {
				if(distance[i][j]>=0) {
					Vertex<Integer> verti = graph.vertMap().get(i);
					Vertex<Integer> vertj = graph.vertMap().get(j);
					graph.insertEdge(verti, vertj, distance[i][j]);
				}
			}
		}
	}


	/**
	 * @return the cost of minimum spanning tree using Kruskal's algorithm. 
	 */
	public int Kruskal() {
		AdjListGraph<Integer,Integer> tree = new AdjListGraph<Integer,Integer>();
		HashMap<Vertex<Integer>, Boolean> verts = new HashMap<Vertex<Integer>, Boolean>();
		HeapPQ<Integer,Edge<Integer>> edges = new HeapPQ<Integer,Edge<Integer>>();
		int edgeCount = 0;
		int weight = 0;
		
		for(Edge<Integer> e : graph.edges()) {
			edges.insert(e.getElement(), e);
		}
		
		while(edgeCount < graph.numVertices()-1){
			Vertex<Integer> vert1;
			Vertex<Integer> vert2;
			Edge<Integer> search = edges.removeMin().getValue();
			if(tree.vertMap().get((Integer)tree.endVertices(search)[0].getElement())==null) {
				vert1 = tree.insertVertex((Integer)graph.endVertices(search)[0].getElement());
			}
			else {
				vert1 = tree.vertMap().get((Integer)graph.endVertices(search)[0].getElement());
			}
			if(tree.vertMap().get((Integer)tree.endVertices(search)[1].getElement())==null) {
				vert2 = tree.insertVertex((Integer)graph.endVertices(search)[1].getElement());
			}
			else {
				vert2 = tree.vertMap().get((Integer)graph.endVertices(search)[1].getElement());
			}
			Vertex<Integer> searchVert = null;
			
			if(verts.get(vert1) != null) {
				searchVert = vert1;
			}
			else if(verts.get(vert1) != null) {
				searchVert = vert2;
			}
			else {
				tree.insertEdge(vert1, vert2, search.getElement());
				edgeCount++;
				weight = weight + search.getElement();
				verts.put(vert1, true);
				verts.put(vert2, true);
				continue;
			}
			Edge<Integer> testedge = tree.insertEdge(vert1, vert2, search.getElement());
			if(cycleDetect(searchVert, tree)) {
				tree.removeEdge(testedge);
				continue;
			}
			else {
				edgeCount++;
				weight = weight + search.getElement();
				verts.put(vert1, true);
				verts.put(vert2, true);
				continue;
			}
		}
		
		return weight;
		
	}
	
	private Boolean cycleDetect(Vertex<Integer> search, AdjListGraph<Integer,Integer> tree) {
		HashMap<Vertex<Integer>,Boolean> beingVisited = new HashMap<Vertex<Integer>,Boolean>();
		HashMap<Vertex<Integer>,Boolean> beenVisited = new HashMap<Vertex<Integer>,Boolean>();
		return cycleDetect(search,beenVisited,beingVisited,tree,null);
	}
	
	private Boolean cycleDetect(Vertex<Integer> sourceVertex, HashMap<Vertex<Integer>,Boolean> beenVisited, HashMap<Vertex<Integer>,Boolean> beingVisited, AdjListGraph<Integer,Integer> tree, Vertex<Integer> source) {
		beingVisited.put(sourceVertex, true);
	    for (Edge e : tree.outgoingEdges(sourceVertex)) {
	    	Vertex<Integer> neighbor = tree.opposite(sourceVertex, e);
	    	if(neighbor == source) {
	    		continue;
	    	}
	        if (beingVisited.get(neighbor)!=null) {
	            // backward edge exists
	            return true;
	        } else if (beenVisited.get(neighbor)==null && cycleDetect(neighbor, beenVisited,beingVisited, tree, sourceVertex)) {
	            return true;
	        }
	    }
	    beingVisited.remove(sourceVertex);
	    beenVisited.put(sourceVertex, true);
	    return false;
	}
}
