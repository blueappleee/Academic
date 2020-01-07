// Written by Connor Ciavarella, Student Number: 251023554
// The following class finds a path from an initial cell to the target customer cell using a stack


// imports the appropriate exceptions for filename errors
import java.io.FileNotFoundException;
import java.io.IOException;

public class FindConnection {
	// initializes the variable cityMap
	Map cityMap;
	
	// sets the cityMap using the filename
	public FindConnection (String filename) {
		try {
			// creates city map based on the filename
			cityMap = new Map(filename);
		}
		
		// catches the exception if the file is not found and prints it then exits
		catch (FileNotFoundException e) {
			System.out.println("Error: File not found");
			System.exit(0);
		}
		
		//catches the exception if the file is in the wrong format and prints it then exits
		catch (IOException e) {
			System.out.println("Error: IO operation failed");
			System.exit(0);
		}
		
	}
	
	public static void main (String[] args) {
		// if there is no args then no input file was provided and therefore the program cannot run so it must be input
		if(args.length < 1) {
			System.out.println("You must provide the name of the input file");
			System.exit(0);
		}
		
		// gets the filename to create the city map
		String mapFileName = args[0];		
		
		// creates the map of the city
		FindConnection map = new FindConnection(mapFileName);
		
		// find the WPC cell
		MapCell currentCell = map.cityMap.getStart();
		
		// creates a new stack of MapCells
		ArrayStack<MapCell> mapStack = new ArrayStack<MapCell>();
		
		// WPC cell into the stack
		mapStack.push(currentCell); 
		// Marking WPC cell as in stack
		currentCell.markInStack(); 
		
		// loops through while the current cell is not the customer meaning path has been found and while the stack is not empty meaning there is no path
		while (!currentCell.isCustomer() && !mapStack.isEmpty()) {
			
			// checks for the next best cell to continue the path using current cell as a parameter
			MapCell nextCell = map.bestCell(currentCell);
			
			// if bestCell returns null then there are no valid neighbours so you pop the cell and mark it out of stack and check with the previous cell for different paths
			if (nextCell == null) {
				mapStack.pop().markOutStack();
				
				// if stack isnt empty it sets current cell to the top of the stack
				if (!mapStack.isEmpty()) {
					currentCell = mapStack.peek();	
					
				}
			}
			
			// if bestCell returns a next cell then its pushed into the stack and marked and current cell is changed to that cell
			else {
				mapStack.push(nextCell);
				nextCell.markInStack();
				currentCell = mapStack.peek();
			}
		}
		
		// if the current cell is the customer cell then the path is found
		if (currentCell.isCustomer()) {
			System.out.println("Path to Customer Cell found in " + mapStack.size() + " cells.");
			
		}
		
		// if the current cell is not the customer cell and the loop exits that means the stack is empty so there is no path
		else {
			System.out.println("Path to Customer Cell not found.");
			
		}
	}
	
	// determines the best cell to move to next
	private MapCell bestCell(MapCell cell) {
		// creates a temporary stack of neighbours
		ArrayStack<MapCell> neighbour = new ArrayStack<MapCell>();
		
		// for when the cell is a omni switch or power station as they have the same properties
		if (cell.isOmniSwitch() || cell.isPowerStation()) {
			// ensures the neighbour cell exists
			if (cell.getNeighbour(0) != null) {
				
				// ensures the neighbour cell is a valid switch type to be accessed at the respective index
				if (cell.getNeighbour(0).isCustomer() || cell.getNeighbour(0).isOmniSwitch() || cell.getNeighbour(0).isVerticalSwitch()) {
					
					// ensures the neighbour cell is not marked and if it is not it pushes the neighbour cell to the neighbourstack
					if (!cell.getNeighbour(0).isMarked()) {	
						neighbour.push(cell.getNeighbour(0));
				
					}
				}
			}
			
			// ensures the neighbour cell exists
			if (cell.getNeighbour(1) != null) {
				
				// ensures the neighbour cell is a valid switch type to be accessed at the respective index
				if (cell.getNeighbour(1).isCustomer() || cell.getNeighbour(1).isOmniSwitch() || cell.getNeighbour(1).isHorizontalSwitch()) {
					
					// ensures the neighbour cell is not marked and if it is not it pushes the neighbour cell to the neighbourstack
					if (!cell.getNeighbour(1).isMarked()) {	
						neighbour.push(cell.getNeighbour(1));
						
					}
				}
			}
			
			// ensures the neighbour cell exists
			if (cell.getNeighbour(2) != null) {
					
				// ensures the neighbour cell is a valid switch type to be accessed at the respective index
				if (cell.getNeighbour(2).isCustomer() || cell.getNeighbour(2).isOmniSwitch() || cell.getNeighbour(2).isVerticalSwitch()) {
					
					// ensures the neighbour cell is not marked and if it is not it pushes the neighbour cell to the neighbourstack
					if (!cell.getNeighbour(2).isMarked()) {	
						neighbour.push(cell.getNeighbour(2));
					
					}
				}
			}
			
			// ensures the neighbour cell exists
			if (cell.getNeighbour(3) != null) {
				
				// ensures the neighbour cell is a valid switch type to be accessed at the respective index
				if (cell.getNeighbour(3).isCustomer() || cell.getNeighbour(3).isOmniSwitch() || cell.getNeighbour(3).isHorizontalSwitch()) {
					
					// ensures the neighbour cell is not marked and if it is not it pushes the neighbour cell to the neighbourstack
					if (!cell.getNeighbour(3).isMarked()) {	
						neighbour.push(cell.getNeighbour(3));
					
					}
				}
			}	
		}
		
		// for when the cell is a vertical switch as they can only access index 0 and 2
		else if (cell.isVerticalSwitch()) {
			// ensures the neighbour cell exists
			if (cell.getNeighbour(0) != null) {
				
				// ensures the neighbour cell is a valid switch type to be accessed at the respective index
				if (cell.getNeighbour(0).isCustomer() || cell.getNeighbour(0).isOmniSwitch() || cell.getNeighbour(0).isVerticalSwitch()) {
					
					// ensures the neighbour cell is not marked and if it is not it pushes the neighbour cell to the neighbourstack
					if (!cell.getNeighbour(0).isMarked()) {	
						neighbour.push(cell.getNeighbour(0));
				
					}
				}
			}
			
			// ensures the neighbour cell exists
			if (cell.getNeighbour(2) != null) {
				
				// ensures the neighbour cell is a valid switch type to be accessed at the respective index
				if (cell.getNeighbour(2).isCustomer() || cell.getNeighbour(2).isOmniSwitch() || cell.getNeighbour(2).isVerticalSwitch()) {
						
					// ensures the neighbour cell is not marked and if it is not it pushes the neighbour cell to the neighbourstack
					if (!cell.getNeighbour(2).isMarked()) {	
						neighbour.push(cell.getNeighbour(2));
					
					}
				}
			}
		}
		
		//for when the cell is a horizontal switch since they can only access index 1 and 3
		else if (cell.isHorizontalSwitch()) {
			// ensures the neighbour cell exists
			if (cell.getNeighbour(1) != null) {
				
				// ensures the neighbour cell is a valid switch type to be accessed at the respective index
				if (cell.getNeighbour(1).isCustomer() || cell.getNeighbour(1).isOmniSwitch() || cell.getNeighbour(1).isHorizontalSwitch()) {
					
					// ensures the neighbour cell is not marked and if it is not it pushes the neighbour cell to the neighbourstack
					if (!cell.getNeighbour(1).isMarked()) {	
						neighbour.push(cell.getNeighbour(1));
						
					}
				}
			}
			
			// ensures the neighbour cell exists
			if (cell.getNeighbour(3) != null) {
				
				// ensures the neighbour cell is a valid switch type to be accessed at the respective index
				if (cell.getNeighbour(3).isCustomer() || cell.getNeighbour(3).isOmniSwitch() || cell.getNeighbour(3).isHorizontalSwitch()) {
						
					// ensures the neighbour cell is not marked and if it is not it pushes the neighbour cell to the neighbourstack
					if (!cell.getNeighbour(3).isMarked()) {	
						neighbour.push(cell.getNeighbour(3));
					
					}
				}
			}
		}
		
		// initializes the ideal cell to null so that if it does not get set it can return null since there are no valid neighbour cells to it
		MapCell ideal = null;
		// boolean to check if ideal cell has already been made omni switch or customer so it isnt over written by a vertical or horizontal cell
		boolean idealSet = false;
		
		// loops through the neighbour stack backwards in order to ensure the lowest index and highest priority cell is returned
		for (int test = neighbour.size() - 1; test >= 0; test--) {
			// gets the cell to test for it being ideal and pops it to prepare the stack for the next loop iteration
			MapCell testCell = neighbour.pop();
			
			// checks if neighbour cell is customer and breaks and returns that cell as the next best cell
			if (testCell.isCustomer()) {
				ideal = testCell;
				idealSet = true;
				break;
				
			}
			
			// checks if neighbour cell is a omni switch as it has second priority but does not check if ideal has been set so it can return the lowest index cell since it works backwards
			else if (testCell.isOmniSwitch()){
				ideal = testCell;
				idealSet = true;
				
			}
			
			// checks if test == 0 and idealSet is false so that it can return the lowest index vertical or horizontal cell without over writing a omni switch
			else if (test == 0 && idealSet == false) {
				ideal = testCell;
				idealSet = true;
				
			}		
		}
		
		//returns the ideal cell
		return ideal;
			
	}
}
