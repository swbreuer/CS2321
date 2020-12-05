package cs2321;

import net.datastructures.*;

/*
 * Implement Graph interface. A graph can be declared as either directed or undirected.
 * In the case of an undirected graph, methods outgoingEdges and incomingEdges return the same collection,
 * and outDegree and inDegree return the same value.
 * 
 * @author CS2321 Instructor
 */
public class AdjListGraph<V, E> implements Graph<V, E> {

	private boolean directed;
	
	private HashMap<edge,E> edgeList;
	private HashMap<vertex,V> vertexList;
	
	
	/**
	 * constructor for a graph that chooses if it is directed
	 */
	public AdjListGraph(boolean directed) {
		this.directed = directed;
		edgeList = new HashMap<edge,E>();
		vertexList = new HashMap<vertex,V>();
	}
	
	/**
	 * constructor for a non directed graph
	 */
	public AdjListGraph() {
		this.directed = false;
		edgeList = new HashMap<edge,E>();
		vertexList = new HashMap<vertex,V>();
	}
	
	/**
	 * returns iterable over all edges
	 */
	@Override
	@TimeComplexity("O ( M )")
	/*
	 * Time complexity justification: loops over all edges with O(1) operations each
	 */
	public Iterable<Edge<E>> edges() {
		ArrayList<Edge<E>> list = new ArrayList<Edge<E>>();
		for(Entry<edge, E> entry : edgeList.entrySet()) {
			list.addLast(entry.getKey());
		}
		return list;
	}

	/* (non-Javadoc)
	 * @see net.datastructures.Graph#endVertices(net.datastructures.Edge)
	 */
	
	/**
	 * returns verticies on ends of an edge
	 * @param edge
	 * @return array of vertexes
	 */
	@Override
	@TimeComplexity("O ( 1 )")
	/*
	 * Time complexity justification: performs one get that is O(1) and nothing else
	 */
	public Vertex[] endVertices(Edge<E> e) throws IllegalArgumentException {
		edge edge = cleanEdge(e);
		return edge.getEndpoints();
	}

	/* (non-Javadoc)
	 * @see net.datastructures.Graph#insertEdge(net.datastructures.Vertex, net.datastructures.Vertex, java.lang.Object)
	 */
	
	/**
	 * creates a new edge with element o and vertexes u and v
	 * @param vertex u one end
	 * @param vertex v other end
	 * @param E o element on edge
	 * @return edge e edge created
	 */
	@Override
	@TimeComplexity("O ( 1 )")
	/*
	 * time complexity justification: only uses hashmap inserts, which are O(1)
	 * uses no loops
	 */
	public Edge<E> insertEdge(Vertex<V> u, Vertex<V> v, E o)
			throws IllegalArgumentException {
		if(getEdge(u,v)==null) {
			edge e = new edge(u,v,o);
			vertex w = cleanVertex(u);
			vertex x = cleanVertex(v);
			w.getOutgoing().put(x, e);
			x.getIncoming().put(w, e);
			edgeList.put(e, o);
			return e;
		}
		else {
			throw new IllegalArgumentException("edge already exists between " + u + " and " + v);
		}
	}

	/* (non-Javadoc)
	 * @see net.datastructures.Graph#insertVertex(java.lang.Object)
	 */
	
	/**
	 * adds vertex to graph
	 * @param V o vertex element
	 * @return Vertex v new vertex
	 */
	@Override
	@TimeComplexity("O ( 1 )")
	/*
	 * time complexity justification: only uses hashmap inserts, which are O(1)
	 * uses no loops
	 */
	public Vertex<V> insertVertex(V o) {
		vertex v = new vertex(o);
		vertexList.put(v, o);
		return v;
	}

	/* (non-Javadoc)
	 * @see net.datastructures.Graph#numEdges()
	 */
	
	/**
	 * returns number of edges 
	 * @param int number of edges
	 */
	@Override
	public int numEdges() {
		return edgeList.size();
	}

	/* (non-Javadoc)
	 * @see net.datastructures.Graph#numVertices()
	 */
	
	/**
	 * returns number of verticies
	 * @return int number of verticies
	 */
	@Override
	public int numVertices() {
		return vertexList.size();
	}

	/* (non-Javadoc)
	 * @see net.datastructures.Graph#opposite(net.datastructures.Vertex, net.datastructures.Edge)
	 */
	
	/**
	 * returns vertex opposite the given one on the edge
	 * @param vertex v vertex to find opposite of
	 * @param edge e edge to find opposite vertex on
	 * @return vertex opposite the given vertex on the given edge
	 */
	@Override
	public Vertex<V> opposite(Vertex<V> v, Edge<E> e)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see net.datastructures.Graph#removeEdge(net.datastructures.Edge)
	 */
	
	/**
	 * removes an edge from the graph
	 * @param edge e edge to remove
	 */
	@Override
	@TimeComplexity("O ( 1 )")
	/*
	 * Time Complexity Justification: uses only hashmap remove methods which are
	 * when properly implemented, is non amortized O(1)
	 */
	public void removeEdge(Edge<E> e) throws IllegalArgumentException {
		edge edge = cleanEdge(e);
		edgeList.remove(edge);
		Vertex[] vertlist = edge.getEndpoints();
		vertex outgo = cleanVertex(vertlist[0]);
		vertex incom = cleanVertex(vertlist[1]);
		outgo.getOutgoing().remove(incom);
		incom.getIncoming().remove(outgo);
	}

	/* (non-Javadoc)
	 * @see net.datastructures.Graph#removeVertex(net.datastructures.Vertex)
	 */
	
	/**
	 * removes vertex from graph
	 * @param vertex v vertex to be removed
	 */
	@Override
	@TimeComplexity("O ( deg(v) )")
	/*
	 * Time Complexity Justification: calls entryset from hashmap which is O(n), the rest is O(1)
	 */
	public void removeVertex(Vertex<V> v) throws IllegalArgumentException {
		vertex vert = cleanVertex(v);
		vertexList.remove(vert);
		for(Entry<vertex,edge> entry : vert.getOutgoing().entrySet()) {
			removeEdge(entry.getValue());
		}
		for(Entry<vertex,edge> entry : vert.getIncoming().entrySet()) {
			removeEdge(entry.getValue());
		}
	}
	
	/**
	 * replace the element in edge object, return the old element
	 * @param edge e edge to replace element of
	 * @param E o element to put in edge
	 * @return E old element of edge
	 */
	public E replace(Edge<E> e, E o) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

    /* 
     * replace the element in vertex object, return the old element
     */
	
	/**
	 * replace the element in edge object, return the old element
	 * @param vertex v vertex to replace element of
	 * @param V o element to put in vertex
	 * @return V old element of vertex
	 */
	public V replace(Vertex<V> v, V o) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see net.datastructures.Graph#vertices()
	 */
	
	/**
	 * returns iterable over all of the vertexes
	 * @return iterable over vertexes
	 */
	@Override
	@TimeComplexity("O ( n )")
	/*
	 * time complexity justification: loops over all vertexes with O(1) operations
	 */
	public Iterable<Vertex<V>> vertices() {
		ArrayList<Vertex<V>> list = new ArrayList<Vertex<V>>();
		for(Entry<vertex, V> entry : vertexList.entrySet()) {
			list.addLast(entry.getKey());
		}
		return list;
	}
	
	/**
	 * returns number of edges going out from vertex
	 * @param vertex v vertex to get outgoing degree
	 * @return int number of outgoing edges
	 */
	@Override
	public int outDegree(Vertex<V> v) throws IllegalArgumentException {
		vertex vert = cleanVertex(v);
		return vert.getOutgoing().size();
	}
	
	/**
	 * returns number of edges going in to vertex
	 * @param vertex v vertex to get in going degree
	 * @return int number of in going edges
	 */
	@Override
	public int inDegree(Vertex<V> v) throws IllegalArgumentException {
		vertex vert = cleanVertex(v);
		return vert.getIncoming().size();
	}
	
	/**
	 * returns iterable over outgoing edges of a given vertex
	 * @param vertex v vertex to return iterable for
	 * @return iterable over all outgoing edges
	 */
	@Override
	@TimeComplexity("O ( deg(v) )")
	/*
	 * Time Complexity Justification: loops over edges of a vertex with O(1) operations
	 */
	public Iterable<Edge<E>> outgoingEdges(Vertex<V> v)
			throws IllegalArgumentException {
		vertex vert = cleanVertex(v);
		ArrayList<Edge<E>> edges = new ArrayList<Edge<E>>();
		for(Entry<vertex,edge> entry : vert.getOutgoing().entrySet()) {
			edges.addLast(entry.getValue());
		}
		return edges;
	}

	/**
	 * returns iterable over in coming edges of a given vertex
	 * @param vertex v vertex to return iterable for
	 * @return iterable over all in coming edges
	 */
	@Override
	public Iterable<Edge<E>> incomingEdges(Vertex<V> v)
			throws IllegalArgumentException {
		vertex vert = cleanVertex(v);
		ArrayList<Edge<E>> edges = new ArrayList<Edge<E>>();
		for(Entry<vertex,edge> entry : vert.getIncoming().entrySet()) {
			edges.addLast(entry.getValue());
		}
		return edges;
	}
	
	/**
	 * returns edge between two vertexes
	 * @param vertex v first edge vertex
	 * @param vertex u second edge vertex
	 */
	@Override
	@TimeComplexity("O ( 1 )")
	/*
	 * Time complexity justifiation: performs single hashmap get which, when properly implemented, is O(1) 
	 */
	public Edge<E> getEdge(Vertex<V> u, Vertex<V> v)
			throws IllegalArgumentException {
		vertex vert = cleanVertex(u);
		vertex verty = cleanVertex(v);
		return vert.getOutgoing().get(verty);
	}

	
	/**
	 * converts abstract Edge to edge implementation
	 * @param Edge e edge to be cleaned
	 * @return edge e returned edge
	 */
	private edge cleanEdge(Edge<E> e) throws IllegalArgumentException{
		edge edge = (edge) e;
		return edge;
	}

	
	/**
	 * converts abstract Vertex to vertex implementation
	 * @param Vertex e vertex to be cleaned
	 * @return vertex e returned vertex
	 */
	private vertex cleanVertex(Vertex<V> v) throws IllegalArgumentException{
		vertex vertex = (vertex) v;
		return vertex;
	}

	
	/**
	 * vertex implementation
	 * holds element V, list of outgoing edges, list of incoming edges
	 * returns outgoing edges, incoming edges, element
	 */
	private class vertex implements Vertex<V>{
		private V element;
		private HashMap<vertex,edge> incoming;
		private HashMap<vertex,edge> outgoing;
		public vertex(V element) {
			this.element = element;
			outgoing = new HashMap<vertex,edge>();
			if(directed) {
				incoming = new HashMap<vertex,edge>();
			}
			else {
				incoming = outgoing;
			}
		}
		@Override
		public V getElement() {
			return element;
		}
		
		public HashMap<vertex,edge> getOutgoing(){
			return outgoing;
		}
		public HashMap<vertex,edge> getIncoming(){
			return incoming;
		}
	}

	
	/**
	 * edge implementation
	 * holds element and array of vertex endpoints
	 * returns element, endpoints
	 */
	private class edge implements Edge<E>{
		private E element;
		private Vertex<V>[] endpoints;
		
		public edge(Vertex<V> w, Vertex<V> x, E element) {
			this.element = element;
			endpoints = new Vertex[]{w,x};
		}
		
		@Override
		public E getElement() {
			return element;
		}
		
		public Vertex<V>[] getEndpoints(){
			return endpoints;
		}
	}
}
