// Written by Connor Ciavarella, Student Number: 251023554
// Written for CS 2211a Assignment 4 Part 1

// ====== this is a sample main program: bs_tree_main.c
// includes the input output library and header file
#include <stdio.h>
#include "bst.h"
int main(void) {

	BStree bst;
	bst = bstree_ini(256);

	bstree_insert(bst, key_construct("Once", 1), 11);
	bstree_insert(bst, key_construct("Upon", 22), 2);
	bstree_insert(bst, key_construct("a", 3), 33);
	bstree_insert(bst, key_construct("Time", 4), 44);
	bstree_insert(bst, key_construct("is", 5), 55);
	bstree_insert(bst, key_construct("filmed", 6), 66);
	bstree_insert(bst, key_construct("in", 7), 77);
	bstree_insert(bst, key_construct("Vancouver", 8), 88);
	bstree_insert(bst, key_construct("!", 99), 9);
	bstree_insert(bst, key_construct("Once", 5), 50);
	bstree_insert(bst, key_construct("Once", 1), 10);

	// initializes the user input variables
	// can input strings up to 100 characters
	char user_name[100];
	int user_id;
	int user_data;

	// takes user input until the user does not follow the format since then scanf will not return 3
	printf("Please enter data in the following format to insert into the list or enter anything any non integer for id or data to end inserting: string name (followed by newline if less then 100 char) integer id integer data.\n");
	while (scanf("%s %d %d", &user_name, &user_id, &user_data) == 3) {
		bstree_insert(bst, key_construct(user_name, user_id), user_data);

	}

	bstree_traversal(bst);
	bstree_free(bst);

}
