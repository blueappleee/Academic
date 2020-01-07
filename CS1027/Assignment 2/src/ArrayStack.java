// Written by Connor Ciavarella, Student Number: 251023554
// Following Class provides the methods for the stack

public class ArrayStack<T> implements ArrayStackADT<T> {
	// initializes the stack
	private T[] stack;
	
	// initializes top to -1 since the stack is empty
	private int top = -1;
	
	// creates a stack with a capacity of 20
	public ArrayStack() {
		stack = (T[]) new Object[20];

	}
	
	// creates a stack with a capacity that is taken as a parameter
	public ArrayStack(int initialCapacity) {
		stack = (T[]) new Object[initialCapacity];
			
	}
	
	// adds the data item to the top of the stack and expands the stack if needed
	public void push(T dataItem) {
		// checks if the new item can fit in the stack by checking if its full using the top variable compared to stack length
		// if it can fit then top adds 1 and the data item is added to the top of the stack
		if ((top + 1) < stack.length) {
			top++;
			stack[top] = dataItem;
		}
		
		// if the data item does not fit
		else {
			// if stack is less then 100 then create a new stack of double the current stacks capacity and copy it
			if (stack.length < 100) {
				T[] newStack = (T[]) new Object[stack.length * 2];
				
				// copys the old stack into the new 
				for (int stackPos = 0; stackPos <= top; stackPos++) {
					newStack[stackPos] = stack[stackPos];
				}
				// sets the stack to the new larger stack
				stack = newStack;
			}
			
			// if greater then 100 creates a new stack with a length 50 greater then the current stack and copy it
			else {
				T[] newStack = (T[]) new Object[stack.length + 50];
				
				//copys the old stack into the new
				for (int stackPos = 0; stackPos <= top; stackPos++) {
					newStack[stackPos] = stack[stackPos];
				}
				// sets the stack to the new larger stack
				stack = newStack;
			}
			
			// adds one to top and adds the data item to the top
			top++;
			stack[top] = dataItem;
		}
	}
	
	// removes the top element of the stack and returns it, throws emptyStackException if stack is empty and also reduces the stack size if less then a third of the stack is full
	public T pop() throws EmptyStackException {
		// checks if empty
		if (isEmpty() == true) {
		      throw new EmptyStackException("Stack is Empty");
		}
		
		//removes the item and subtract 1 from top
		T Item = stack[top];
		stack[top] = null;
		top--;
		
		// checks if top is less than a third of the stack length and if it is it creates a new smaller stack which is then compied and re assigned to stack
		if (top < (stack.length / 3)) {
			T[] newStack = (T[]) new Object[stack.length / 2];
			
			// copy the stack onto the new stack
			for (int stackPos = 0; stackPos <= top; stackPos++) {
				newStack[stackPos] = stack[stackPos];
			}
			
			// set stack to new stack
			stack = newStack;
		}
		
		// return the removed item
		return Item;
		
		
	}
	
	//returns the top value of the stack 
	public T peek() throws EmptyStackException {
		// if the stack is empty it throws a empty stack exception
		if (isEmpty()) {
		      throw new EmptyStackException("Stack is Empty");
		}

		return stack[top];
	}
	
	// returns if the stack is empty as when top == -1 it is empty
	public boolean isEmpty() {
		if (top == -1){
			return true;
		}
		
		else {
			return false;
		}
		
	}
	
	// returns the size of the stack by returning the index of the top + 1
	public int size() {
		return (top + 1);
	}
	
	// returns the stack length
	public int length() {
		return stack.length;
	}
	
	// creates a to string for the stack that lists all the values in the stack
	public String toString() {
		String rep = "Stack: ";
		
		// loops through the stack and adds each value to the rep string while adding a comma if not the last value in the stack
		for (int stackPos = 0; stackPos <= top; stackPos++) {
			if (stackPos != top) {
				rep += stack[stackPos];
				rep += ", ";
			}
			
			else {
				rep += stack[stackPos];
			}
		}
		
		// returns the string rep
		return rep;
	}
}
