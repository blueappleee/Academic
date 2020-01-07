// Written by Connor Ciavarella, Student Number: 251023554
// Written for CS2210 Assignment 4

// Following class defines the DuplicatedKeyException

public class DuplicatedKeyException extends RuntimeException {
	public DuplicatedKeyException () {
	    super ("Key is already in the tree");
	    
	  }
}
