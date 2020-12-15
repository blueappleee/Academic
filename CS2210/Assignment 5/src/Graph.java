// Written by Connor Ciavarella, Student Number:
// Written for CS2210 Assignment 5

// Following class defines the graph data type

// import needed packages
import java.util.*;

public class Graph {
	// initialize instance variables
	private Node nodes[];
	private Edge edges[][];
	
	// constructor which sets instance variables
	public Graph(int n) {
		this.nodes = new Node[n];
		this.edges = new Edge[n][n];
		
		for (int i = 0; i < n; i++) {
			nodes[i] = new Node(i);
			
		}
	}
	
	// returns the node with the given name
	public Node getNode(int name) throws GraphException {
		if (name < nodes.length) {
			if (nodes[name] != null) {
				return nodes[name];	
				
			}
			
			else {
				throw new GraphException();
				
			}
		}
		
		else {
			throw new GraphException();
			
		}
	}
	
	// inserts a edge between two nodes into the list
	public void insertEdge(Node u, Node v, int edgeType) throws GraphException {
		// make sure nodes exist
		getNode(u.getName());
		getNode(v.getName());
		
		// make sure edge doesnt already exist
		if (edges[u.getName()][v.getName()] != null) {
			throw new GraphException();
			
		}
		
		// insert edges
		edges[u.getName()][v.getName()] = new Edge(u, v, edgeType);
		edges[v.getName()][u.getName()] = new Edge(v, u, edgeType);
		
	}
	
	// returns all edges containing the given node
	public Iterator<Edge> incidentEdges(Node u) throws GraphException {
		// make sure node is in the list
		if(u.getName() > nodes.length - 1) {
			throw new GraphException();
			
		}
		
		// set up list for return
		ArrayList<Edge> edgelist = new ArrayList<Edge>();
		
		// loop through edge list to find all edges with the given node
		for (int i = 0; i < edges.length; i++) {
			for (int j = 0; j < edges[i].length; j++) {
				if (edges[i][j] != null) {
					// if the node is first then add the endpoint so the other node is always the second endpoint
					if (edges[i][j].firstEndpoint().getName() == u.getName()) {
						edgelist.add(edges[i][j]);
					
					}
				}
			}
		}
		
		// create iterator to return
		Iterator<Edge> edgeIterator = edgelist.iterator();
		
		// return the iterator
		return edgeIterator;
		
	}
	
	// return a given  edge
	public Edge getEdge(Node u, Node v) throws GraphException {
		// make sure edge exists
		if (u.getName() > (edges.length - 1)|| v.getName() > (edges.length - 1)) {
			throw new GraphException();
			
		}
		
		// if edge isnt null return it
		if (edges[u.getName()][v.getName()] != null) {
			return edges[u.getName()][v.getName()];
			
		}
		
		// if edge is null then throw exception
		else {
			throw new GraphException();
			
		}
	}
	
	// check if nodes are adjacent
	public boolean areAdjacent(Node u, Node v) throws GraphException {
		// make sure nodes exist
		if (u.getName() > (edges.length - 1)|| v.getName() > (edges.length - 1)) {
			throw new GraphException();
			
		}
		
		// if an edge exists between the nodes return true
		if (edges[u.getName()][v.getName()] != null) {
			return true;
			
		}
		
		return false;
		
	}

}
