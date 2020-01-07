// Written by Connor Ciavarella, Student Number: 251023554
// Written for CS2210 Assignment 5

// Following class defines the Node type

public class Node {
	// initialize instance variables
	private int name;
	private boolean mark;
	
	// constructor to set instance variable
	public Node(int name) {
		this.name = name;
		
	}
	
	// set mark
	public void setMark (boolean mark) {
		this.mark = mark;
		
	}

	// return mark
	public boolean getMark () {
		return mark;
		
	}
	
	// return name
	public int getName() {
		return name;
		
	}
}
