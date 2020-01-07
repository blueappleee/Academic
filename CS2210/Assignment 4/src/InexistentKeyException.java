// Written by Connor Ciavarella, Student Number: 251023554
// Written for CS2210 Assignment 4

// Following class defines the InexistentKeyException

public class InexistentKeyException extends RuntimeException {
	public InexistentKeyException () {
	    super ("The key is not stored in the tree");
	    
	  }
}
