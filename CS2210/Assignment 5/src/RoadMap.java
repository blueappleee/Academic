// Written by Connor Ciavarella, Student Number: 
// Written for CS2210 Assignment 5
// all given test maps work but map 8 takes a while just as a heads up

// Following class defines the roadmap data type

// import needed packages
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class RoadMap {
	// initialize instance variables
	private ArrayList<String> input;
	private int scale;
	private Node start;
	private Node end;
	private Graph map;
	private int width;
	private int length;
	private int initial;
	private int toll;
	private int gain;
	private BufferedReader file;
	
	// constructor which sets instance variables
	public RoadMap(String inputFile) throws MapException {
		// opens file
		try {
			file = new BufferedReader(new FileReader(inputFile));
		
		}
		
		// if issue then exception thrown
		catch (Exception e) {
			throw new MapException();
			
		}
		
		try {
			// put the file line by line into an array
			input = new ArrayList<String>();
			
			String line = file.readLine();
			
			while (line != null) {
				if (line != "\n") {
					input.add(line);
					
				}
				
				line = file.readLine();
				
			}
			
			// set constant instance variables
			scale = Integer.valueOf(input.get(0));
			start = new Node(Integer.valueOf(input.get(1)));
			end = new Node(Integer.valueOf(input.get(2)));
			width = Integer.valueOf(input.get(3));
			length = Integer.valueOf(input.get(4));
			initial = Integer.valueOf(input.get(5));
			toll = Integer.valueOf(input.get(6));
			gain = Integer.valueOf(input.get(7));
			
			// close file
			file.close();
			
		}
		
		// if issue putting file into list then throw exception since file isnt correct format
		catch (Exception e) {
			throw new MapException();
			
		}
		
		// set up variables to make the map
		int size = width * length;
		map = new Graph(size);
		
		int h = 0;
		int v = 0;
		String mapstr;
		String mapstr2;
		Node nodeu;
		Node nodev;
		char prevchar = 'a';
		char mapchar;
		char mapchar2;
		
		int check = 7;
		
		//  loops through every line in the file that builds the map
		for (int i = 8; i < input.size(); i+=2) {
			// gets a line
			mapstr = input.get(i);
			
			// checks each char in the line to create the horizontal connections
			for (int j = 0; j < mapstr.length(); j++) {
				mapchar = mapstr.charAt(j);
				
				if (h == size-1) {
					break;
					
				}
				
				nodeu = map.getNode(h);
				nodev = map.getNode(h+1);
				
				// checks node type to insert correct node type and then increment h to insert correct node position
				if (mapchar == 'T') {
					map.insertEdge(nodeu, nodev, toll);
					h++;
					
				}
				
				else if (mapchar == 'F') {
					map.insertEdge(nodeu, nodev, 0);
					h++;
					
				}
				
				else if (mapchar == 'C') {
					map.insertEdge(nodeu, nodev, -gain);
					h++;
					
				}	
				
				else if (mapchar == 'X') {
					if (prevchar != 'X') {
						h++;
						
					}
				}
				
				// prev char to not add to h multiple times when block out houses
				prevchar = mapchar;
				
			}
			
			prevchar = 'a';
			
			// ensure h is correctly set for next line
			if (h < (i - check) * (width)) {
				h = (i - check) * (width);
				
			}
			
			// loop through next line to set vertical connections to prev line	
			if (i != input.size() - 1) {
				mapstr2 = input.get(i+1);
				
				for (int j = 0; j < mapstr.length(); j++) {
					mapchar = mapstr.charAt(j);
					
					if (v + width >= size) {
						break;
						
					}

					nodeu = map.getNode(v);
					nodev = map.getNode(v + width);
					
					// get char type to make correct node in correct position
					if (mapchar == '+') {
						mapchar2 = mapstr2.charAt(j);
						
					
						if (mapchar2 == 'T') {
							map.insertEdge(nodeu, nodev, toll);
							v++;
						
						}
					
						else if (mapchar2 == 'F') {
							map.insertEdge(nodeu, nodev, 0);
							v++;
						
						}
					
						else if (mapchar2 == 'C') {
							map.insertEdge(nodeu, nodev, -gain);
							v++;
						
						}
					
						else if (mapchar2 == 'X') {
							v++;
						
						}
					}
				}
				
				// ensure v is set up for next vertical line
				if (v < (i - check) * (width)) {
					v = (i - check) * (width);
					
				}
			}
			
			else {
				break;
				
			}
			
			check += 1;
			
		}
		
		// clear the input array
		input.clear();

	}
	
	// return the map
	public Graph getGraph() {
		return map;
		
	}
	
	// return the starting node
	public int getStartingNode() {
		return start.getName();
		
	}
	
	// return the end node
	public int getDestinationNode() {
		return end.getName();
		
	}
	
	// returns initial money
	public int getInitialMoney() {
		return initial;
		
	}
	
	// Recursively find the path to the end node
	public ArrayList<Node> getpath(int node, int destination, int currmoney, ArrayList<Node> path) {
		// set up var to help path finding
		Iterator<Edge> edges;
		ArrayList<Edge> options = new ArrayList<Edge>();
		ArrayList<Node> done = new ArrayList<Node>();
		Edge check;
		Node checknode;
		int type;
		
		Node curr = new Node(node);
		
		// if node = end then that the correct path
		if (node == destination) {
			return path;
			
		}
		
		// if path is empty then no solution so null
		if (path.isEmpty()) {
			return null;
			
		}
		
		edges = map.incidentEdges(curr);

		// get the valid options for the next movement of the current node
		while (edges.hasNext()) {
			check = edges.next();

			checknode = check.secondEndpoint();	
			
			if (path.contains(checknode) == false) {
				type = check.getType();
				
				if (currmoney - type >= 0) {
					options.add(check);	

				}	
			}
		}
		
		// if there are options try each option until destination found or none left
		if (options.size() != 0) {
			for (int k = 0; k < options.size(); k++) {
				curr = options.get(k).secondEndpoint();
				path.add(curr);
				
				// recusive find path with new money, path, node
				done =  getpath(curr.getName(), destination, currmoney - options.get(k).getType(), path);
				
				// if done is not null then if done is the destination return it
				if (done != null) {
					if (done.get(done.size() - 1).getName() == destination) {
						return done;
					
					}
				}
			}	
		}

		path.remove(path.size() - 1);
		
		// return null if cannot find path
		return null;
		
	}
	
	// return the iterator of the path
	public Iterator findPath(int start, int destination, int initialMoney) {
		// sets up var for path call
		Node curr = map.getNode(start);

		ArrayList<Node> path = new ArrayList<Node>();
		
		path.add(curr);
		
		// calls getpath to get path to end or null if none
		path = getpath(start, destination, initialMoney, path);
		
		// if path to end exists return the iterator to it
		if (path != null) {
			return path.iterator(); 
		
		}
	
		// if path to end doesnt exist then return null
		else {
			return null;
		
		}
	}
}
