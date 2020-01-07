// Written by Connor Ciavarella, Student Number: 251023554
// Written for CS2210 Assignment 4

// Following class defines the BinaryNode type

public class BinaryNode {
	// initializes instance variables
	private Pixel value;
	private BinaryNode left;
	private BinaryNode right;
	private BinaryNode parent;
	
	// constructor with values
	public BinaryNode (Pixel value, BinaryNode left, BinaryNode right, BinaryNode parent) {
		// sets  instance variables
		this.value = value;
		this.left = left;
		this.right = right;
		this.parent = parent;
		
	}
	
	// constructor without values
	public BinaryNode() {
		// sets all to null
		this.value = null;
		this.left = null;
		this.right = null;
		this.parent = null;
		
	}
	
	// returns parent
	public BinaryNode getParent() {
		return parent;
		
	}
	
	// sets the parent variables
	public void setParent(BinaryNode parent) {
		this.parent = parent;
		
	}
	
	// sets the left variables
	public void setLeft(BinaryNode p) {
		this.left = p;
		
	}
	
	// sets the right variables
	public void setRight(BinaryNode p) {
		this.right = p;
		
	}
	
	// sets the data variables
	public void setData(Pixel value) {
		this.value = value;
		
	}
	
	// leaf is without data so if no data the true else false
	public boolean isLeaf() {
		if (value == null) {
			return true;
			
		}
		
		else {
			return false;
			
		}
	}
	
	// returns data
	public Pixel getData() {
		return value;
		
	}
	
	// returns left
	public BinaryNode getLeft() {
		return left;
		
	}
	
	// return right
	public BinaryNode getRight() {
		return right;
		
	}
}
