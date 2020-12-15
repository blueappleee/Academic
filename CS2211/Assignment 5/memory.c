// Written by Connor Ciavarella, Student Number:
// Written for CS 2211a Assignment 5

#include <stdio.h>
#include <stdlib.h>
#include "memory.h"

// instance variables
static unsigned char *memory;
static BStree bst;

// initializes the values
void mem_ini(unsigned int size) {
	memory = (unsigned char *) malloc(size);
	bst = bstree_ini(size/5); // size/5 is large enough
	bstree_insert(bst, 0, size);
}

// simulates malloc by finding a block of memory large enough to fit size + 4
void *simu_malloc(unsigned int size) {
	Key key = *bstree_data_search(bst, size + 4);
	Data *data = bstree_search(bst, key);

	bstree_delete(bst, key);

	// sets the first 4 values in the block to size
	for (int i = key; i < key + 4; i++) {
		memory[i] = size;

	}

	// if there is left over memory re insert it into the tree
	if (*data > (size + 4)) {
		Key keynew = key + 4 + size;
		Data datanew = *data - size - 4;

		bstree_insert(bst, keynew, datanew);

	}

	return &memory[key + 4];
}

// simulates free by re inserting the value into the tree
void simu_free(void *ptr) {
	unsigned char *ptrnew = (unsigned char *) ptr;

	// gets key from the pointer and bases size off key
	Key key = ptrnew - memory - 4;
	Data size = memory[key] + 4;

	bstree_insert(bst, key, size);

}

// traverses the tree and prints the free memory
void mem_print(void) {
	bstree_traversal(bst);

}

// frees the memory in the tree
void mem_free(void) {
	bstree_free(bst);
	free(memory);

}
