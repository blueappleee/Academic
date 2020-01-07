// Written by Connor Ciavarella, Student Number: 251023554
// defines the emptystackexception which outputs a given parameter

public class EmptyStackException extends RuntimeException {
	
	public EmptyStackException(String output) {
		super(output);
	}
}
