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
	
	public AdjListGraph(boolean directed) {
		this.directed = directed;
		edgeList = new HashMap<edge,E>();
		vertexList = new HashMap<vertex,V>();
	}

	public AdjListGraph() {
		this.directed = false;
		edgeList = new HashMap<edge,E>();
		vertexList = new HashMap<vertex,V>();
	}


	/* (non-Javadoc)
	 * @see net.datastructures.Graph#edges()
	 */
	@Override
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
	@Override
	public Vertex[] endVertices(Edge<E> e) throws IllegalArgumentException {
		edge edge = cleanEdge(e);
		return edge.getEndpoints();
	}


	/* (non-Javadoc)
	 * @see net.datastructures.Graph#insertEdge(net.datastructures.Vertex, net.datastructures.Vertex, java.lang.Object)
	 */
	@Override
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
	@Override
	public Vertex<V> insertVertex(V o) {
		vertex v = new vertex(o);
		vertexList.put(v, o);
		return v;
	}

	/* (non-Javadoc)
	 * @see net.datastructures.Graph#numEdges()
	 */
	@Override
	public int numEdges() {
		return edgeList.size();
	}

	/* (non-Javadoc)
	 * @see net.datastructures.Graph#numVertices()
	 */
	@Override
	public int numVertices() {
		return vertexList.size();
	}

	/* (non-Javadoc)
	 * @see net.datastructures.Graph#opposite(net.datastructures.Vertex, net.datastructures.Edge)
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
	@Override
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
	@Override
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

	/* 
     * replace the element in edge object, return the old element
     */
	public E replace(Edge<E> e, E o) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

    /* 
     * replace the element in vertex object, return the old element
     */
	public V replace(Vertex<V> v, V o) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see net.datastructures.Graph#vertices()
	 */
	@Override
	public Iterable<Vertex<V>> vertices() {
		ArrayList<Vertex<V>> list = new ArrayList<Vertex<V>>();
		for(Entry<vertex, V> entry : vertexList.entrySet()) {
			list.addLast(entry.getKey());
		}
		return list;
	}

	@Override
	public int outDegree(Vertex<V> v) throws IllegalArgumentException {
		vertex vert = cleanVertex(v);
		return vert.getOutgoing().size();
	}

	@Override
	public int inDegree(Vertex<V> v) throws IllegalArgumentException {
		vertex vert = cleanVertex(v);
		return vert.getIncoming().size();
	}

	@Override
	public Iterable<Edge<E>> outgoingEdges(Vertex<V> v)
			throws IllegalArgumentException {
		vertex vert = cleanVertex(v);
		ArrayList<Edge<E>> edges = new ArrayList<Edge<E>>();
		for(Entry<vertex,edge> entry : vert.getOutgoing().entrySet()) {
			edges.addLast(entry.getValue());
		}
		return edges;
	}

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

	@Override
	public Edge<E> getEdge(Vertex<V> u, Vertex<V> v)
			throws IllegalArgumentException {
		return null;
	}
	
	private edge cleanEdge(Edge<E> e) throws IllegalArgumentException{
		edge edge = (edge) e;
		return edge;
	}
	
	private vertex cleanVertex(Vertex<V> v) throws IllegalArgumentException{
		vertex vertex = (vertex) v;
		return vertex;
	}
	
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
