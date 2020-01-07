// Written by Connor Ciavarella, Student Number: 251023554
// Written for CS 2211a Assignment 5

// includes the libraries and data structures needed
#include <stdio.h>
#include "bst.h"
#include "memory.h"
#include "list.h"

// main program which takes user input to calculate occurrences of integers
int main() {
	// initializes variables
	int allocate;
	int value;

	// input for memory allocation size
	printf("Please insert the amount of memory to be allocated: \n");
	scanf(" %d", &allocate);

	// initializes the memory
	mem_ini(allocate);

	// initializes the list
	List_node *list = list_ini();

	// takes integers until a non integer is entered
	printf("Please enter integers separated by a new line or enter a letter to end inputing\n");
	while (scanf("%d", &value) == 1) {
		// search for value in the list
		Data *data = list_search(list, value);

		// if value isnt in list add it with data one
		if (data == NULL) {
			list_add(list, value, 1);

		}

		// if value is in list delete it and re add it with data + 1 as occurrence increases
		else {
			list_delete(list, value);
			list_add(list, value, *data + 1);

		}
	}

	// prints list
	printf("integer	occurrence\n");
	list_print(list);

	// frees list and memory
	list_free(list);
	mem_free();

}
