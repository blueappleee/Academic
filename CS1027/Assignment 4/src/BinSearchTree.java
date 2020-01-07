// Written by Connor Ciavarella, Student Number: 251023554
// The following class defines the methods for the object type BinSearchTree

public class BinSearchTree {
	// declares the private instance variable root
	private BinSearchTreeNode root; 
	
	// finds the leaf which contains the searchWord and returns it by calling the search method
	public BinSearchTreeNode getWord(String searchWord) {
		return search(root, searchWord);
		
	}
	
	// recursive function which finds the leaf which contains the searchWord starting at r
	private BinSearchTreeNode search(BinSearchTreeNode r, String searchWord) {
		// if r == null then the node is either empty or has been searched and the word is not in it
		if (r == null) {
			return null;
			
		}
		
		// if r.getWord is the searchWord then r is the leaf that was to be searched for and is returned
		else if (r.getWord().equals(searchWord)) {
			return r;
			
		}
		
		// if r's word is lexiographically larger then the searchWord then continue the search on the left branch by recalling search
		else if (r.getWord().compareTo(searchWord) > 0) {
			return search(r.getLeft(), searchWord);
					
		}
		
		// if nothing else then r's word is lexiographically smaller then the searchWord so continue the search on the right branch by recalling search
		else {
			return search(r.getRight(), searchWord);
			
		}
	}
	
	// inserts a word onto a node in the tree containing data by calling the search and insert methods
	public void insertWord(String theWord, String theFileName, int thePosition) {
		// finds the node that the word should be inserted into 
		BinSearchTreeNode node = search(root, theWord); 
		
		//if node == null then it does not exist and create a new node and insert it
		if (node == null) {
			insert(root, theWord, theFileName, thePosition);
			
			
		}
		
		// inserts the word into the nodes data
		else {
			node.getFiles().insertWord(theFileName, thePosition);
			
		}
	}
	
	// Recursive function searches for a location and then creates and inserts a node and adds it to a BinSearchTree
	private void insert(BinSearchTreeNode r, String theWord, String theFile, int thePosition) {
		// following statements determine correct place to put node
		// if r == null then the tree is empty so make the node the root of the tree
		if (r == null) {
			r = new BinSearchTreeNode(theWord, theFile, thePosition);
			
			root = r;
			
		}
		
		// if r's word is larger then the searchWord then the node goes on the right
		else if (r.getWord().compareTo(theWord) > 0) {
			// if r does not have a left then set the left of r to the newly created node
			if (r.getLeft() == null) {
				BinSearchTreeNode left = new BinSearchTreeNode(theWord, theFile, thePosition);
				
				r.setLeft(left);
				
			}
			
			// if r has a left then continue the search down the left branch by recalling insert
			else {
				insert(r.getLeft(), theWord, theFile, thePosition);
				
			}	
		}
		
		// if the right of r is null and since the word must be larger then r since it passed the previous statement, create a node and place it to the right of r
		else if (r.getRight() == null) {
			BinSearchTreeNode right = new BinSearchTreeNode(theWord, theFile, thePosition);
			
			r.setRight(right);
			
		}
		// if nothing else recall insert and continue the search down the right branch
		else {
			insert(r.getRight(), theWord, theFile, thePosition);
			
		}
	}
}
