// Written by Connor Ciavarella, Student Number: 251023554
// following class defines the methods and constructor for the type object type Dictionary

// imports linked list
import java.util.LinkedList;


public class Dictionary implements DictionaryADT{
	// initializes instance variables
	private int tableSize;
	LinkedList<Record>[] table;
	
	// dictionary constructor
	public Dictionary(int size) {
		// sets instance variable values
		tableSize = size;
		
		table = new LinkedList[tableSize];
		
		// sets each element in the table to null as default
		for (int i = 0; i < tableSize; i++) {
			table[i] = null;
			
		}
		
	}
	
	// inserts pair into the table or throws exception if if pair is already in the table
	public int insert(Record pair) throws DictionaryException {
		// calculate hash
		int key = Hash(pair.getConfig(), table.length);
		
		// if the table at key is null then no linked list is in that space
		if (table[key] == null) {
			// create linked list and add pair
			LinkedList<Record> records = new LinkedList<Record>();
			records.add(pair);
			
			// add linked list to table
			table[key] = records; 
			
			return 0;
			
		}
		
		// if its not null then linked list already exists and its a collision
		else {
			// check to see if config is already in the table and if so throw an exception
			for (Record currentRecord : table[key]) {
				if (currentRecord.getConfig().equals(pair.getConfig())) {
					throw new DictionaryException();
					
				}
				
			}
			
			// if not in table add it to the linked list
			table[key].add(pair);
			
			return 1;
			
		}
	}
	
	// removes the record with the config or throws exception if its not in the table
	public void remove(String config) throws DictionaryException {
		// calculate hash
		int key = Hash(config, table.length);
		
		// if config == null or table[key] == null then its not in the table so throw exception
		if (config == null || table[key] == null) {
			throw new DictionaryException();
			
		}
		
		// find the Record object in the linked list and remove it
		else {
			for (Record currentRecord : table[key]) {
				if (currentRecord.getConfig().equals(config)) {
					table[key].remove(currentRecord);
					break;
					
				}
			}
		}
	}
	
	// gets a record object of the given config and returns its score or -1 if its not in the table
	public int get(String config) {
		// calculate hash
		int key = Hash(config, table.length);
		
		// if not in table return -1
		if (config == null || table[key] == null) {
			return -1;
			
		}
		
		else {
			// find and return records score
			for (Record currentRecord: table[key]) {
				if (currentRecord.getConfig().equals(config)) {
					return currentRecord.getScore();
					
				}
			}
			 // if not found return -1
			return -1;
		}
	}
	
	// returns the num of elements in the table
	public int numElements() {
		// starts at zero
		int elements = 0;
		
		// adds size of each linked list since thats num of elements in linked list and repeats for all
		for (int i = 0; i < table.length; i++) {
			elements += table[i].size();
			
		}
		
		return elements;
	}
	
	// hashing function
	private int Hash(String Config, int tableSize) {
		int value = 0;
		
		// start at zero and add 37* value + the config character at each i until the end of config
		for (int i = 0; i < Config.length(); i++) {
			value = 37 * value  + Config.charAt(i);
			
		}
		
		// modulo value and tablesize 
		value %= tableSize;
		
		// if less then zero then add tableSize
		if (value < 0) {
			value += tableSize;
			
		}
		
		return value;		
		
	}
}
