// Written by Connor Ciavarella, Student Number: 251023554
// The following class finds the shortest path from an initial cell to the target customer cell using a doubly linked list


// imports the appropriate exceptions for filename errors
import java.io.FileNotFoundException;
import java.io.IOException;

public class ShortestPath {
	// initializes the variable cityMap
	Map cityMap;
	
	public ShortestPath(String filename) {
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
		ShortestPath map = new ShortestPath(mapFileName);
		
		// find the WPC cell
		MapCell currentCell = map.cityMap.getStart();
		
		// creates the empty list to use for finding the path
		DLList<MapCell> mapList = new DLList<MapCell>();
		
		// inserts and marks the start cell into the list
		mapList.insert(currentCell, 0);
		currentCell.markInList();
		
		// loops through while the current cell is not the customer meaning path has been found and while the list is not empty meaning there is no path
		while (!currentCell.isCustomer() && !mapList.isEmpty()) {
			// gets the MapCell in the list with the smallest value and marks it out of list
			currentCell = mapList.getSmallest();
			currentCell.markOutList();
			
			// if currentCell happens to be the customer then break
			if (currentCell.isCustomer()) {
				break;
				
			}
			
			// initializes best cell and makes it the first nextCell
			MapCell bestCell = map.nextCell(currentCell);
			
			// gets a value for distance to start for the currentCell
			int distanceFromInitial = currentCell.getDistanceToStart() + 1;  //d
			
			// if bestCell == null then there are no more neighbouring cells so break
			while (bestCell != null) {
				// checks if bestCell has a distance attribute set and its size
				if (bestCell.getDistanceToStart() > distanceFromInitial) {
					// sets the distance to start to initial and sets its predecessor
					bestCell.setDistanceToStart(distanceFromInitial);
					bestCell.setPredecessor(currentCell);
					
				}
				
				//sets distance form bestCell to the distance from the start to bestCell
				int distanceFromBestCell = bestCell.getDistanceToStart(); 
				
				// if best cell is marked in list
				if (bestCell.isMarkedInList()) {
					// if distance from best cell is less than its value in the list then change its value to the smaller distance
					if (distanceFromBestCell < mapList.getDataValue(bestCell)) {
						mapList.changeValue(bestCell,distanceFromBestCell) ;
						
					}
				}
				
				// if its not in list
				else {
					// add it to list and mark it in list
					mapList.insert(bestCell, distanceFromBestCell);
					bestCell.markInList();
					
				}
				
				// recall bestCell to get the neighbouring cell and do the previous steps on it
				bestCell = map.nextCell(currentCell);

			} 
		}	
		
		// if the current cell is the customer cell then the path is found
		if (currentCell.isCustomer()) {
			System.out.println("Path to Customer Cell found in " + (currentCell.getDistanceToStart() + 1) + " cells.");
			
		}
		
		// if the current cell is not the customer cell and the loop exits that means the list is empty so there is no path
		else {
			System.out.println("Path to Customer Cell not found.");
			
		}
	}
	
	public MapCell nextCell(MapCell cell) {
		// initializes the ideal cell to null so that if it does not get set it can return null since there are no valid neighbour cells to it
		MapCell ideal = null;
		
		// initializes a boolean to check if ideal has been set, used instead of if else statements since one if statement being true does not always mean idael is set
		boolean idealSet = false;
		
		// for when the cell is a omni switch or power station as they have the same properties
		if (cell.isOmniSwitch() || cell.isPowerStation()) {
			
			// ensures the neighbour cell exists and ideal isnt already set
			if (idealSet == false && cell.getNeighbour(0) != null) {
				
				// ensures the neighbour cell is a valid switch type to be accessed at the respective index
				if (cell.getNeighbour(0).isCustomer() || cell.getNeighbour(0).isOmniSwitch() || cell.getNeighbour(0).isVerticalSwitch()) {
					
					// ensures the neighbour cell is not marked and if it is not it pushes the neighbour cell to the neighbourstack
					if (!cell.getNeighbour(0).isMarked()) {	
						ideal = cell.getNeighbour(0);
						idealSet = true;
				
					}
				}
			}
			
			// ensures the neighbour cell exists and ideal isnt already set
			if (idealSet == false && cell.getNeighbour(1) != null) {
				
				// ensures the neighbour cell is a valid switch type to be accessed at the respective index
				if (cell.getNeighbour(1).isCustomer() || cell.getNeighbour(1).isOmniSwitch() || cell.getNeighbour(1).isHorizontalSwitch()) {
					
					// ensures the neighbour cell is not marked and if it is not it pushes the neighbour cell to the neighbourstack
					if (!cell.getNeighbour(1).isMarked()) {	
						ideal = cell.getNeighbour(1);
						idealSet = true;
						
					}
				}
			}
			
			// ensures the neighbour cell exists and ideal isnt already set
			if (idealSet == false && cell.getNeighbour(2) != null) {
					
				// ensures the neighbour cell is a valid switch type to be accessed at the respective index
				if (cell.getNeighbour(2).isCustomer() || cell.getNeighbour(2).isOmniSwitch() || cell.getNeighbour(2).isVerticalSwitch()) {
					
					// ensures the neighbour cell is not marked and if it is not it pushes the neighbour cell to the neighbourstack
					if (!cell.getNeighbour(2).isMarked()) {	
						ideal = cell.getNeighbour(2);
						idealSet = true;
					
					}
				}
			}
			
			// ensures the neighbour cell exists and ideal isnt already set
			if (idealSet == false && cell.getNeighbour(3) != null) {
				
				// ensures the neighbour cell is a valid switch type to be accessed at the respective index
				if (cell.getNeighbour(3).isCustomer() || cell.getNeighbour(3).isOmniSwitch() || cell.getNeighbour(3).isHorizontalSwitch()) {
					
					// ensures the neighbour cell is not marked and if it is not it pushes the neighbour cell to the neighbourstack
					if (!cell.getNeighbour(3).isMarked()) {	
						ideal = cell.getNeighbour(3);
						idealSet = true;
					
					}
				}
			}	
		}
		
		// for when the cell is a vertical switch as they can only access index 0 and 2
		else if (cell.isVerticalSwitch()) {
			// ensures the neighbour cell exists and ideal isnt already set
			if (idealSet == false && cell.getNeighbour(0) != null) {
				
				// ensures the neighbour cell is a valid switch type to be accessed at the respective index
				if (cell.getNeighbour(0).isCustomer() || cell.getNeighbour(0).isOmniSwitch() || cell.getNeighbour(0).isVerticalSwitch()) {
					
					// ensures the neighbour cell is not marked and if it is not it pushes the neighbour cell to the neighbourstack
					if (!cell.getNeighbour(0).isMarked()) {	
						ideal = cell.getNeighbour(0);
						idealSet = true;
				
					}
				}
			}
			
			// ensures the neighbour cell exists and ideal isnt already set
			if (idealSet == false && cell.getNeighbour(2) != null) {
				
				// ensures the neighbour cell is a valid switch type to be accessed at the respective index
				if (cell.getNeighbour(2).isCustomer() || cell.getNeighbour(2).isOmniSwitch() || cell.getNeighbour(2).isVerticalSwitch()) {
						
					// ensures the neighbour cell is not marked and if it is not it pushes the neighbour cell to the neighbourstack
					if (!cell.getNeighbour(2).isMarked()) {	
						ideal = cell.getNeighbour(2);
						idealSet = true;
					
					}
				}
			}
		}
		
		//for when the cell is a horizontal switch since they can only access index 1 and 3
		else if (cell.isHorizontalSwitch()) {
			// ensures the neighbour cell exists and ideal isnt already set
			if (idealSet == false && cell.getNeighbour(1) != null) {
				
				// ensures the neighbour cell is a valid switch type to be accessed at the respective index
				if (cell.getNeighbour(1).isCustomer() || cell.getNeighbour(1).isOmniSwitch() || cell.getNeighbour(1).isHorizontalSwitch()) {
					
					// ensures the neighbour cell is not marked and if it is not it pushes the neighbour cell to the neighbourstack
					if (!cell.getNeighbour(1).isMarked()) {	
						ideal = cell.getNeighbour(1);
						idealSet = true;
						
					}
				}
			}
			
			// ensures the neighbour cell exists and ideal isnt already set
			if (idealSet == false && cell.getNeighbour(3) != null) {
				
				// ensures the neighbour cell is a valid switch type to be accessed at the respective index
				if (cell.getNeighbour(3).isCustomer() || cell.getNeighbour(3).isOmniSwitch() || cell.getNeighbour(3).isHorizontalSwitch()) {
						
					// ensures the neighbour cell is not marked and if it is not it pushes the neighbour cell to the neighbourstack
					if (!cell.getNeighbour(3).isMarked()) {	
						ideal = cell.getNeighbour(3);
						idealSet = true;
					
					}
				}
			}
		}

		//returns the ideal cell
		return ideal;
			
	}		
}
	
	

