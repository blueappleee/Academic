// Written by Connor Ciavarella, Student Number: 
// Written for CS2210 Assignment 5

// Following class defines the Edge data type

public class Edge {
	// initialize instance variables
	private Node u;
	private Node v;
	private int type;
	
	// constructor which sets instance variables
	public Edge(Node u, Node v, int type) {
		this.u = u;
		this.v = v;
		this.type = type;
		
	}
	
	// return first node
	public Node firstEndpoint() {
		return u;
		
	}
	
	// return second node
	public Node secondEndpoint() {
		return v;
		
	}
	
	// return type
	public int getType() {
		return type;
		
	}
}
