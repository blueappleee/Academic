// Written by Connor Ciavarella, Student Number: 
// Written for CS2210 Assignment 4

// Following class defines the BinarySearchTree type

public class BinarySearchTree implements BinarySearchTreeADT {
	// initializes instance variable
	private BinaryNode root;
	
	// constructor
	public BinarySearchTree() {
		// sets instance variable
		root = new BinaryNode();
		
	}
	
	// returns the pixel with the given key or null if not in the tree
	public Pixel get(BinaryNode r, Location key) {
		// if the node is a leaf key isnt in the tree
		if (r.isLeaf()) {
			return null;
			
		}
		
		// set up the variables to compare
		Pixel nodeValue = r.getData();
		Location nodeKey = nodeValue.getLocation();
		int compare = nodeKey.compareTo(key);
		
		
		if (compare == 0) {
			return nodeValue;
			
		}
		
		// if node is greater then the key go to the left to check smaller
		else if (compare == 1) {
			BinaryNode left = r.getLeft();
			
			// if left is null key isnt in the tree
			if (left == null) {
				return null;
				
			}
			
			else {
				return get(left, key);
				
			}
		}
		
		// if node is smaller then the key go to the right to check larger
		else {
			BinaryNode right = r.getRight();
			
			// if right is null key isnt in the tree
			if (right == null) {
				return null;
				
			}
			
			else {
				return get(right, key);
				
			}
		}
	}
	
	// puts a node with the given data in the tree
	public void put(BinaryNode r, Pixel data) throws DuplicatedKeyException {
		// if the node is a leaf then input the data at the node
		if (r.isLeaf()) {
			r.setData(data);
			
		}
		
		else {
			// set up the variables for comparision
			Pixel nodeValue = r.getData();
			Location nodeKey = nodeValue.getLocation();
			int compare = nodeKey.compareTo(data.getLocation());
		
			// if compare is zero its already in the tree
			if (compare == 0) {
				throw new DuplicatedKeyException();
			
			}
		
			// if node is greater then the key go to the left to check smaller
			else if (compare == 1) {
				BinaryNode left = r.getLeft();
				
				// if left is null input the data at left
				if (left == null) {
					left = new BinaryNode();
					
					r.setLeft(left);
					
					left.setData(data);
					left.setParent(r);
					
				}
				
				else {
					put(left, data);
					
				}		
			}
		
			// if node is smaller then the key go to the right to check larger
			else if (compare == -1) {
				BinaryNode right = r.getRight();
				
				// if right is null input the data at right
				if (right == null) {
					right = new BinaryNode();
					
					r.setRight(right);
					
					right.setData(data);
					right.setParent(r);
					
				}
				
				else {
					put(right, data);
					
				}
			}
		}
	}
	
	// remove the node with the given key
	public void remove (BinaryNode r, Location key) throws InexistentKeyException {
		// if leaf then node isnt in the tree
		if (r.isLeaf()) {
			throw new InexistentKeyException();
			
		}
		
		// set up variables for comparison
		Pixel nodeValue = r.getData();
		Location nodeKey = nodeValue.getLocation();
		int compare = nodeKey.compareTo(key);
	
		// if compare is 0 then remove the current node
		if (compare == 0) {
			BinaryNode parent = r.getParent();
			BinaryNode right = r.getRight();
			BinaryNode left = r.getLeft();
			
			// if node is root and empty set all to null
			if (parent == null && right == null && left == null) {
				r.setData(null);
				r.setLeft(null);
				r.setRight(null);
				
			}
			
			// if no children then set the parent pointer that points to the current node to null
			else if (right == null && left == null) {
				int parentComp = nodeKey.compareTo(parent.getData().getLocation());
				
				if (parentComp == 1) {
					parent.setRight(null);
					
				}
				
				else {
					parent.setLeft(null);
					
				}

				r.setData(null);
				
			}
			
			// if right is null and left isnt then copy the left tree to the parents left tree to skip over the current node
			else if (right == null) {
				r.setData(left.getData());
				r.setLeft(left.getLeft());
				r.setRight(left.getRight());
				
				if (left.getLeft() != null) {
					left.getLeft().setParent(r);
					
				}
				
				if (left.getRight() != null) {
					left.getRight().setParent(r);
					
				}
			}
			
			// if left is null and right isnt then copy the right tree to the parents right tree to skip over the current node
			else if (left == null) {
				r.setData(right.getData());
				r.setLeft(right.getLeft());
				r.setRight(right.getRight());
				
				if (right.getLeft() != null) {
					right.getLeft().setParent(r);
					
				}
				
				if (right.getRight() != null) {
					right.getRight().setParent(r);
					
				}
			}
			
			// if nothing is null copy the successor to replace the current node and remove the successor
			else {
				Pixel successor = successor(root, r.getData().getLocation());
				
				if (get(root, successor.getLocation()) != null) {
					remove(root, successor.getLocation());
					
				}
				
				r.setData(successor);
				
			}
			
		}
	
		// if node is greater then the key go to the left to check smaller
		else if (compare == 1) {
			BinaryNode left = r.getLeft();
			
			// if left is null its not in the tree
			if (left == null) {
				throw new InexistentKeyException();
				
			}
			
			else {
				remove(left, key);
				
			}		
		}
	
		// if node is smaller then the key go to the right to check larger
		else if (compare == -1) {
			BinaryNode right = r.getRight();
			
			// if right is null its not in the tree
			if (right == null) {
				throw new InexistentKeyException();
				
			}
			
			else {
				remove(right, key);
				
			}
		}
	}
	
	// return the node that matches the given key
	private BinaryNode findNode(BinaryNode r, Location key) {
		// if the node is leaf its not in the tree
		if (r.isLeaf()) {
			return null;
			
		}
		
		// set up the values for comparison
		Pixel nodeValue = r.getData();
		Location nodeKey = nodeValue.getLocation();
		int compare = nodeKey.compareTo(key);

		// if compare is zero return the node 
		if (compare == 0) {
			return r;
			
		}

		// if node is greater then the key go to the left to check smaller
		else if (compare == 1) {
			BinaryNode left = r.getLeft();
		
			// if left is null then its not in the tree
			if (left == null) {
				return null;
			
			}
		
			else {
				return findNode(left, key);
			
			}		
		}

		// if node is smaller then the key go to the right to check larger
		else {	
			BinaryNode right = r.getRight();
		
			// if right is null then its not in the tree
			if (right == null) {
				return null;
			
			}
		
			else {
				return findNode(right, key);
			
			}
		}
	}
	
	// return the inorder successor of the given key
	public Pixel successor(BinaryNode r, Location key) {
		// if the current node is leaf then no successor
		if (r.isLeaf()) {
			return null;
			
		}
		
		// if the node is in the tree
		else if (r.getData().getLocation().compareTo(key) == 0 || get(r,key) != null) {
			if (r.getData().getLocation().compareTo(key) != 0) {
				r = findNode(r,key);
				
			}
			
			if (r == null) {
				return null;
				
			}
			
			BinaryNode right = r.getRight();
			BinaryNode parent = r.getParent();
			
			// if parent and right are null then theres no successor
			if (parent == null && right == null) {
				return null;
				
			}
			
			// if right is null the successor is an ancestor so iterate to get the smallest ancestor thats larger
			else if (right == null) {
				while (parent.getParent() != null && parent.getParent().getRight() != parent) {
					parent = parent.getParent();
					
				}
				
				if (parent.getParent() == null && key.compareTo(parent.getData().getLocation()) != -1) {
					return null;
					
				}
				
				else {
					return parent.getData();
					
				}
				
			}
				
			// if the right tree isnt null get the largest right tree value
			else {
				BinaryNode left = right.getLeft();

				if (left == null) {
					return right.getData();
						
				}
				
				else {
					while (left.getLeft() != null) {
						left = left.getLeft();
					
					}
					
					return left.getData();
					
				}
			}
		}
	
		// if the key isnt in the tree search the tree for the successor
		else {
			BinaryNode current = new BinaryNode();
			
			while (!r.isLeaf()) {
				// set up the comparison to help search the tree
				Pixel nodeValue = r.getData();
				Location nodeKey = nodeValue.getLocation();
				int compare = nodeKey.compareTo(key);
				
				// if node is greater then the key go to the left to check smaller
				if (compare == 1) {
					current = r;
					
					// if left is null then current is successor
					if (r.getLeft() == null) {
						break;
						
					}
					
					r = r.getLeft();
					
				}
				
				// if node is smaller then the key go to the right to check larger
				else {
					// if right is null then current is successor
					if (r.getRight() == null) {
						break;
						
					}
					
					r = r.getRight();
					
				}
			}
			
			return current.getData();
			
		}	
	}
	
	// find the inorder predecessor
	public Pixel predecessor(BinaryNode r, Location key) {
		// if the current node is leaf then no successor
		if (r.isLeaf()) {
			return null;
			
		}
		
		// if the node is in the tree
		else if (get(r,key) != null) {
			r = findNode(r,key);
			
			if (r == null) {
				return null;
				
			}
			
			BinaryNode left = r.getLeft();
			BinaryNode parent = r.getParent();
			
			// if parent and left are null then theres no predecessor
			if (parent == null && left == null) {
				return null;
				
			}
			
			// if the left tree is null the predecessor is a parent so iterate through the parents to find the predecessor
			else if (left == null) {
				if (parent.getLeft() == r) {
					while (parent.getParent() != null && parent.getParent().getLeft() != parent) {
						parent = parent.getParent();
					
					}
				}
				
				if (parent.getParent() == null && key.compareTo(parent.getData().getLocation()) != 1) {
					return null;
					
				}
				
				else {
					return parent.getData();
					
				}
				
			}
				
			// if the left tree isnt null get the smallest left tree value
			else {
				BinaryNode right = left.getRight();
				
				if (right == null) {
					
					return left.getData();
					
				}
				
				else {
					while (right.getRight() != null) {
						right = right.getRight();
					
					}
				
					return right.getData();
					
				}		
			}
		}
		
		// if the key isnt in the tree search the tree for the predecessor
		else {
			BinaryNode current = new BinaryNode();
			
			// set up the comparison to help search the tree
			while (!r.isLeaf()) {
				Pixel nodeValue = r.getData();
				Location nodeKey = nodeValue.getLocation();
				int compare = nodeKey.compareTo(key);
				
				// if node is greater then the key go to the left to check smaller
				if (compare == 1) {
					// if left is null then current is predecessor
					if (r.getLeft() == null) {
						break;
						
					}
					
					r = r.getLeft();
					
				}
				
				// if node is smaller then the key go to the right to check larger
				else {
					current = r;
					
					// if right is null then current is predecessor
					if (r.getRight() == null) {
						break;
						
					}
					
					r = r.getRight();
					
				}
			}
				
			return current.getData();
			
		}
	}
	
	// return the smallest value (leftmost value) in the tree
	public Pixel smallest(BinaryNode r) throws EmptyTreeException {
		// if the root is a leaf the tree is empty
		if (root.isLeaf()) {
			throw new EmptyTreeException();
			
		}
		
		// if the left subtree is null you are at the leftmost node
		if (r.getLeft().isLeaf()) {
			return r.getData();
			
		}
		
		// if the left subtree isnt null then get the next left node
		else {
			return smallest(r.getLeft());
			
		}
		
	}
	
	// return the largest value (rightmost value) in the tree
	public Pixel largest(BinaryNode r) throws EmptyTreeException {
		// if the root is a leaf the tree is empty
		if (root.isLeaf()) {
			throw new EmptyTreeException();
			
		}
		
		// if the right subtree is null you are at the rightmost node
		if (r.getRight().isLeaf()) {
			return r.getData();
			
		}
		
		// if the right subtree isnt null then get the next right node
		else {
			return largest(r.getRight());
			
		}
		
	}
	
	// return the root
	public BinaryNode getRoot() {
		return root;
		
	}
}
