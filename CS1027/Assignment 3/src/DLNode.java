// Written by Connor Ciavarella, Student Number: 251023554
// class is the constructor and functions of the type DLNode<T>


public class DLNode<T> {
	// Initializes the instance variables
	private T dataItem;
	private DLNode<T> next;
	private DLNode<T> previous;
	private int value;
	
	// constructor that sets the data and value instance variables
	public DLNode(T data, int value) {
		dataItem = data;
		this.value = value;
	}
	
	// returns the value
	public int getValue() {
		return value;
	}
	
	//returns the dataitem
	public T getData() {
		return dataItem;
		
	}
	
	// returns the next node
	public DLNode<T> getNext() {
		return next;
		
	}
	
	// returns the previous node
	public DLNode<T> getPrevious() {
		return previous;
		
	}
	
	// sets the data item
	public void setData(T data) {
		dataItem = data;
		
	}
	
	//sets the next node
	public void setNext(DLNode<T> node) {
		next = node;
		
	}
	
	// sets the previous node
	public void setPrevious(DLNode<T> node) {
		previous = node;
		
	}
	
	// sets the value
	public void setValue(int newValue) {
		value = newValue;
		
	}
	
}
