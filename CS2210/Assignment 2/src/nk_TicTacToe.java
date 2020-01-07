// Written by Connor Ciavarella, Student Number: 251023554
// this class defines the methods and constructor of the object nk_TicTacToe

public class nk_TicTacToe {
	// initialize instance variables
	private char[][] gameBoard;
	private int board_size;
	private int inline;
	private int max_levels;
	
	// constructor
	public nk_TicTacToe (int board_size, int inline, int max_levels) {
		// set instance values
		this.board_size = board_size;
		this.inline = inline;
		this.max_levels = max_levels;
		
		// create game board
		gameBoard = new char[board_size][board_size];
		
		// fill game board with empties
		for (int i = 0; i < board_size; i ++) {
			for (int j = 0; j < board_size; j++) {
				gameBoard[i][j] = ' ';
				
			}
		}		
	}	
	
	// create dictionary based on least collisions number
	public Dictionary createDictionary() {
		return new Dictionary(7883); // 7883 gave fewest collisions between 6000 - 8000 for TestDict.java
		
	}
	
	// checks if the config is already in the dictionary 
	public int repeatedConfig(Dictionary configurations) {
		// convert gameboard to string
		StringBuilder currentConfig = new StringBuilder();
		for (int i = 0; i < board_size; i++) {
			for (int j = 0; i < board_size; i ++) {
				currentConfig.append(gameBoard[i][j]);
					
			}	
		}
		
		// return the result of the get method in dictionary for the config
		return configurations.get(currentConfig.toString());

	}
	
	// inster a config into the dictionary
	public void insertConfig(Dictionary configurations, int score) {
		// create string of gameboard
		StringBuilder currentConfig = new StringBuilder();
		for (int i = 0; i < board_size; i++) {
			for (int j = 0; i < board_size; i ++) {
				currentConfig.append(gameBoard[i][j]);
					
			}	
		}
		
		// create record based on gameboard and score
		Record record = new Record(currentConfig.toString(), score);
		
		// insert into dictionary
		configurations.insert(record);	
		
	}
	
	// stores value at given row and column
	public void storePlay(int row, int col, char symbol) {
		gameBoard[row][col] = symbol;
		
	}
		
	// checks if a given square is empty
	public boolean squareIsEmpty(int row, int col) {
		if (gameBoard[row][col] == ' ') {
			return true;
			
		}
		
		else {
			return false;
			
		}
		
	}
	
	// checks if a symbol wins based on number of symbols in a row compared to the inline value
	public boolean wins(char symbol) {
		// sets up check variables
		int runLengthTopBottP1 = 0;
		int runLengthTopBottP2 = 0;
		int runLengthBottTopP1 = 0;
		int runLengthBottTopP2 = 0;
		int runLengthCol = 0;
		int runLengthRow = 0;

		
		// check each row and col 
		for (int i = 0; i < board_size; i ++) {
			// set col and row run to zero if i increments so run doesnt continue over a edge
			runLengthCol = 0;
			runLengthRow = 0;
			for (int j = 0; j < board_size; j ++) {
				// if in run then add to run value
				if (gameBoard[i][j] == symbol) {
					runLengthRow++;
					
				}
				
				// if not in run then set to zero
				else {
					runLengthRow = 0;
					
				}
				
				// if in run then add to run value
				if (gameBoard[j][i] == symbol) {
					runLengthCol++;
					
				}
				
				// if not in run then set to zero
				else {
					runLengthCol = 0;
					
				}
				
				// if run == inline then wins
				if (runLengthCol == inline || runLengthRow == inline) {
					return true;
					
				}
			}	
		}
		
		for (int i = inline; i <= board_size; i++) {
			// set runs to zero if i increments as to not continue run
			runLengthTopBottP1 = 0;
			runLengthTopBottP2 = 0;
			runLengthBottTopP1 = 0;
			runLengthBottTopP2 = 0;
			
			for (int j = 0; j < i; j++ ) {
				// checks top to bottom top half
				if (gameBoard[j][board_size - inline + j - (i - inline)] == symbol) {
					runLengthTopBottP1++;
					
				}
				
				// sets to zero if not in run
				else {
					runLengthTopBottP1 = 0;
					
				}
				
				// checks bottom to top top half
				if (gameBoard[i - 1 - j][j] == symbol) {
					runLengthBottTopP1++;
					
				}
				
				// sets to zero if not in run
				else {
					runLengthBottTopP1 = 0;
					
				}
				
				// prevent double checking the middle diagonal for the bottom half
				// easier to set up loop to check with all 4 corners expanding towards the middle as i increased and more efficient then a loop for each direction
				if (i != board_size) {
					// checks top to bottom on the bottom half
					if (gameBoard[board_size - inline + j - (i - inline)][j] == symbol) {
						runLengthTopBottP2++;
						
					}
					
					// sets to zero if not in run
					else {
						runLengthTopBottP2 = 0;
					
					}
					
					// checks bottom to top on bottom half
					if (gameBoard[board_size - 1 - j][board_size - i + j] == symbol) {
						runLengthBottTopP2++;
						
					}
					
					// sets to zero if not in run
					else {
						runLengthBottTopP2 = 0;
					}
				}
				
				// check if runs equal in line and if so win
				if (runLengthTopBottP1 == inline || runLengthTopBottP2 == inline || runLengthBottTopP1 == inline || runLengthBottTopP2 == inline) {
					return true;
					
				}
			}
		} 
		
		// if no one wins return false
		return false;
		
	}
	
	// checks if result is draw
	public boolean isDraw() {
		// checks no one wins and if board is full then its a draw
		if (wins('O') == false && wins('X') == false) {
			for (int i = 0; i < board_size; i++) {
				for (int j = 0; j < board_size; j++) {
					if (squareIsEmpty(i,j)) {
						return false;
						
					}	
				}
			}
		}
		
		return true;
		
	}
	
	// determines score
	public int evalBoard() {
		// checks computer wins
		if (wins('O')) {
			return 3;
			
		}
		
		// checks human wins
		else if (wins('X')) {
			return 0;
			
		}
		
		// checks if draw
		else if (isDraw()) {
			return 2;
			
		}
		
		// if nothing then still going on
		else {
			return 1;
			
		}
	}
}
