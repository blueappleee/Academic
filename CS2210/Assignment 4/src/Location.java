// Written by Connor Ciavarella, Student Number: 
// Written for CS2210 Assignment 4

// Following class defines the Location type

public class Location {
	// initialize instance variables
	private int x;
	private int y;
	
	// constructor
	public Location(int x, int y) {
		// set instance variables
		this.x = x;
		this.y = y;
		
	}

	// returns x coordinate from instance variables
	public int xCoord() {
		return x;
		
	}
	
	// returns y coordinate from instance variables
	public int yCoord() {
		return y;
		
	}
	
	// compares two location for larger or smaller
	public int compareTo (Location p) {
		// defines p's coordinates
		int compX = p.xCoord();
		int compY = p.yCoord();
		
		// if x < p x return -1
		if (x < compX) {
			return -1;
			
		}
		
		// if x > p x return 1
		else if (x > compX) {
			return 1;
			
		}
		
		// if they are the same compare y
		else {
			// if y < py return -1
			if (y < compY) {
				return -1;
				
			}
			
			// if y > py return 1
			else if (y > compY) {
				return 1;
				
			}
			
			// if y same then locations are the same return 0
			else {
				return 0;
				
			}
		}
	}
}

