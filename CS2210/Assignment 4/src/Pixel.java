// Written by Connor Ciavarella, Student Number: 251023554
// Written for CS2210 Assignment 4

// Following class defines the Pixel type

public class Pixel {
	// initializes instance variables
	private Location p;
	private int color;
	
	// constructor
	public Pixel(Location p, int color) {
		// sets instance variables
		this.p = p;
		this.color = color;
		
	}
	
	// returns location
	public Location getLocation() {
		return p;
		
	}
	
	// return color
	public int getColor() {
		return color;
		
	}
	
	
}
