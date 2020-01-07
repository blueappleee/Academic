// Written by Connor Ciavarella, Student Number: 251023554
// The following class provides the methods and constructor for the object type Record

public class Record {
	// initialize instance variables
	private String config;
	private int score;
	
	// constructor which sets instance variables
	public Record(String config, int score) {
		this.config = config;
		this.score = score;
		
	}
	
	//returns config
	public String getConfig() {
		return config;
		
	}
	
	// returns score
	public int getScore() {
		return score;
	}

}
