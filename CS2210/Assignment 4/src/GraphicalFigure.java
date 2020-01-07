// Written by Connor Ciavarella, Student Number: 251023554
// Written for CS2210 Assignment 4

// Following class defines the graphicalfigure type

public class GraphicalFigure implements GraphicalFigureADT {
	// initializes the instance variables
	private int id;
	private int width;
	private int height;
	private String type;
	private Location pos;
	private BinarySearchTree tree;
	
	// constructor
	public GraphicalFigure(int id, int width, int height, String type, Location pos) {
		// set instance variables
		this.id = id;
		this.width = width;
		this.height = height;
		this.type = type;
		this.pos = pos;
		
		tree = new BinarySearchTree();
		
	}
	
	// set the type variables
	public void setType(String type) {
		this.type = type;
		
	}
	
	// return the width
	public int getWidth() {
		return width;
		
	}
	
	// return the height
	public int getHeight() {
		return height;
		
	}
	
	// return the type
	public String getType () {
		return type;
		
	}
	
	// return the id
	public int getId() {
		return id;
		
	}
	
	// return the offset
	public Location getOffset() {
		return pos;
		
	}
	
	// set the offset
	public void setOffset(Location value) {
		pos = value;
		
	}
	
	// add a pixel to the tree
	public void addPixel(Pixel pix) throws DuplicatedKeyException {
		tree.put(tree.getRoot(), pix);
		
	}
	
	// check if two graphicalfigures intersect
	public boolean intersects(GraphicalFigure gobj) {
		// boolean for if a xcollision
		boolean xCol = false;
		
		// check all x values for both figures to check for overlap 
		for (int i = pos.xCoord(); i <= pos.xCoord() + getWidth(); i ++) {
			for (int j = gobj.getOffset().xCoord(); j <= gobj.getOffset().xCoord() + gobj.getWidth(); j++) {
				// if overlap check for y overlap aswell
				if (i == j) {
					xCol = true;
					
				}
			}
		}
		
		// if x overlap check y for overlap
		if (xCol == true) {
			for (int i = pos.yCoord(); i <= pos.yCoord() + getHeight(); i ++) {
				for (int j = gobj.getOffset().yCoord(); j <= gobj.getOffset().yCoord() + gobj.getHeight(); j++) {
					// if i overlaps as well return true
					if (i == j) {
						return true;
					
					}
				}
			}
		}
		
		// if no overlap return false
		return false;
			
	}
}
