// Written by Connor Ciavarella, Student Number: 251023554
// class is the functions of DLList and object initialization 

public class DLList<T> implements DLListADT<T> {
	// initialize front, rear, count
	private DLNode<T> front;
	private DLNode<T> rear;
	private int count;
	
	// constructor which sets the front, rear and count initial values
	public DLList() {
		front = null;
		rear = null;
		count = 0;
		
	}
	
	// inserts a dataItem and its value into the DLList
	public void insert(T dataItem, int value) {
		//creates node
		DLNode<T> node = new DLNode(dataItem, value);
		
		// determines where to set the node in the list to get the correct pointers
		if (count == 0) {
			front = node;
			
		}
		
		else {
			rear.setNext(node);
			node.setPrevious(rear);
		
		}
		
		// sets rear to node 
		rear = node;
		// adds 1 to count
		count += 1;
	}
	
	// returns the dataValue of a given dataItem
	public int getDataValue(T dataItem) throws InvalidDataItemException { 
		// temp start node
		DLNode<T> temp = front;
		
		// if temp isnt null loop through the list checking for the dataItem
		if (temp != null) {
			for (int i = 0; i < count; i++) {
				// if temp is the dataitem or temp is found then its either found or non existant so break
				if (temp == null || temp.getData().equals(dataItem)) {
					break;
				
				}	
				
				// allows loop through by progressing to next
				temp = temp.getNext();
			
			}
		}
		
		// in the case temp isnt the dataitem it hasnt been found
		if (temp == null || !temp.getData().equals(dataItem)) {
			throw new InvalidDataItemException("Data Item not found");
			
		}
		
		// return the value
		else {
			return temp.getValue();
			
		}
	}
	
	// changes the value of a given dataItem to the given value
	public void changeValue(T dataItem, int newValue)  throws InvalidDataItemException {
		// temp start node
		DLNode<T> temp = front;
		
		// if temp isnt null loop through the list checking for the dataItem
		if (temp != null) {
			for (int i = 0; i < count; i++) {
				if (temp == null || temp.getData().equals(dataItem)) {
					break;
				
				}	
				
				// allows loop through by progressing to next
				temp = temp.getNext();
			
			}
		}
		
		// in the case temp isnt the dataitem it hasnt been found
		if (temp == null || !temp.getData().equals(dataItem)) {
			throw new InvalidDataItemException("Data Item not found");
			
		}
		
		//sets the new value for the given dataItem
		else {
			temp.setValue(newValue);
		
		}
	}
	
	// returns and removes the dataitem with the smallest value
	public T getSmallest() throws EmptyListException {
		// if empty then throw exception
		if (isEmpty()) {
			throw new EmptyListException("List is empty");
		
		}
		
		// temp node for front to progress
		DLNode<T> temp = front;
		// create a small node and temporarily set it to temp
		DLNode<T> small = temp;
		
		// loop through the list
		for (int i = 0; i < count; i++) {
			if (temp == null) {
				break;
					
			}
				
			// if the new value of temp is smaller then smalls value then set small to temp
			else if (temp.getValue() < small.getValue()) {
				small = temp;
			}
			
			// allows loop through by progressing to next
			temp = temp.getNext();
				
		}
		
		// removes the small node
		DLNode<T> previous = small.getPrevious();
		DLNode<T> next = small.getNext();
		
		// if next is null then its rear so to avoid a null pointer exception check first to aviod it being rear and if so then reassign rear
		if (next != null) {
			next.setPrevious(previous);
		
		}
		
		else {
			rear = small.getPrevious();
			
		}
		
		// if previous is null then its front so to avoid a null pointer exception check first to aviod it being front and if so then reassign front
		if (previous != null) {
			previous.setNext(next);
		
		}
		
		else {
			front = small.getNext();
			
		}
		
		// reduces count by 1
		count--;

		// returns the smallest dataitem
		return small.getData();
		
	}
	
	// returns if the list is empty as when count == 0 it is empty
	public boolean isEmpty() {
		if (count == 0) {
		return true;
		
		}
		
		else {
			return false;
		
		}
	}
	
	// returns the size of the stack by returning the count
	public int size() {
		return count;
		
	}
	
	// creates a to string for the list that lists all the values in the list
	public String toString() {
		String rep = "List: ";
		
		DLNode<T> temp = front;
		
		// loops through the list and adds each value to the rep string 
		for (int i = 0; i < count; i++) {
			if (temp == null) {
				break;
				
			}
				
			rep += temp.getData();
			rep += ", ";
			rep += temp.getValue();
			rep += "; "; 
			
			temp = temp.getNext();
			
		}
		
		// returns the string rep
		return rep;
		
	}
	
}
