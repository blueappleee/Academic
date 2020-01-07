// Written by Connor Ciavarella, Student Number: 251023554
// The following class finds all matches to words in a given file throughout a given lexicon using a linked list and binary search trees

// imports the appropriate libraries
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;


public class Searcher {
	// declares instance variables
	private HashTable table;
	private String inputFile;
	
	// constructor for the searcher class which sets the table and inputfile variables
	public Searcher(HashTable table, String inputFile) {
		this.table = table;
		this.inputFile = inputFile;
		
	}
	
	// reads the input file and for each word in the input file seperated by a space it searches the tree for matching words and generates output
	public void findAllWords() {
		// try incase file not found or io error
		try {
			// reads the line
			BufferedReader in = new BufferedReader(new FileReader(inputFile)); 
			String line = in.readLine(); 
			
			// if line is null then file is empty and break
			while (line != null) {
				// split by white space into a array
				String[] words = line.split("\\s+");
				
				// call findWord on each word in the array 
				for (int i = 0; i < words.length; i++) {
					findWord(words[i]);
					
				}
				
				// reads the next line
				line = in.readLine();
				
			}
			
			// closes the reader
			in.close();
			
		}
		
		// if file not found print the error and exit
		catch (FileNotFoundException e) {
			System.out.println("Error: File not found");
			System.exit(0);
		}
		
		// if io operation error then print and exit
		catch (IOException e) {
			System.out.println("Error: IO operation failed");
			System.exit(0);
		}
	}
	
	// finds the word in the lexicon
	public void findWord(String searchWord) {
		// finds the index of the table which may contain the word
		int tableIndex = table.computeIndex(searchWord);
		
		// sets tree to the table at the given index
		BinSearchTree Tree = table.getTable()[tableIndex];
		
		// sets treenode to the leaf which contains the searchword
		BinSearchTreeNode TreeNode = Tree.getWord(searchWord);
		
		// if treenode is null then the word is not in the tree so word is not found so print and add to file it not being found
		if (TreeNode == null) {
			CustomPrinter.wordNotFound(searchWord, inputFile);
			
		}
		
		// if word is found
		else {
			// print the word was found
			CustomPrinter.wordFound(searchWord, inputFile);
			
			// get the linked list from the treenode
			LinkedList fileNodes = TreeNode.getFiles();
			
			// get the iterator from the linked list
			Iterator<FileNode> fileIt = fileNodes.iterator();
			
			// traverse using the iterator to get the positions for the word in the node of the list
			while (fileIt.hasNext()) {
				FileNode currentFile = fileIt.next();
				ArrayList<Integer> filePositions = currentFile.getPositions();
				
				// print the message for the word is found and add it to file
				CustomPrinter.printPositionsPerFileFound(currentFile.getFilename(), filePositions, inputFile);
				
			}
		}
	}
}
