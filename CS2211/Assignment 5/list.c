// Written by Connor Ciavarella, Student Number:
// Written for CS 2211a Assignment 5

// includes the needed libraries and data structures
#include <stdio.h>
#include <stdlib.h>
#include "list.h"
#include "memory.h"

// initializes the list by creating the head
List list_ini(void) {
	List_node *head= (List_node *) simu_malloc( sizeof(List_node) ); 
	head->next = NULL;
	return head;
}

// finds the parent to a node with the given key
List_node *find_node(List_node *node, Key key) {
	// if node is null then its not in the list
	if (node == NULL) {
		return NULL;

	}

	// if node.next is null then its not in the list since looking for parent
	else if (node->next == NULL) {
		return NULL;

	}

	// if key comp of child returns zero its the parent node node so return the node
	else if (key_comp(key, node->next->key) == 0) {
		return node;

	}

	// if next.next is not null then it could be next so recall on next
	else if (node->next->next != NULL) {
		return find_node(node->next, key);

	}

	else {
		return NULL;

	}
}

// creates a new node
List_node *newNode(Key key, Data data) {
	// allocates memory
	List_node *newnode = (List_node *) simu_malloc(sizeof(List_node));

	// sets the values
	newnode->data = data;
	newnode->key = key;
	newnode->next = NULL;

	return newnode;

}

// searches list for a node with given key and returns its data
Data *list_search(List list, Key key) {
	// if list null then not in list
	if (list == NULL) {
		return NULL;

	}

	// if list key is key then return the data of the node
	else if (list->key == key) {
		return &list->data;

	}

	else {
		// if theres more in the list call again with the next node in the list
		if (list->next != NULL) {
			return list_search(list->next, key);

		}

		else {
			return NULL;

		}
	}
}

// adds a node to the list
void list_add(List list, Key key, Data data) {
	// checsk if the node is already in the list
	List_node *node = find_node(list, key);

	// if the node is in the list dont add it
	if (node != NULL) {
		return;

	}

	// find the end node to insert the new node at
	while (list->next != NULL) {
		list = list->next;

	}

	// create the new node to be insert
	List_node *newnode = newNode(key, data);

	// set the end nodes next to the new node to make the new node the new end node
	list->next = newnode;

}

// deletes a node from the list
void list_delete(List list, Key key) {
	// gets the parent node
	List_node *node = find_node(list, key);

	// sets a temp node the the child of the given node
	List_node *tempNode = node->next;

	// skips over the node wanting to be deleted by setting its parents next to its child
	node->next = tempNode->next;

	// free the node
	simu_free(tempNode);

}

// prints the value after the node then prints the node until the end of the list
void printlist(List_node *node) {
	if (node->next != NULL) {
		printlist(node->next);

	}

	print_list_node(node);

}

// prints the list excluding head using printlist
void list_print(List list) {
	if (list->next != NULL) {
		printlist(list->next);

	}
}

// frees the node but first frees any node after it
void free_node(List_node *node) {
	// frees node after the node
	if (node->next != NULL) {
		free_node(node->next);

	}

	// frees the node
	simu_free(node);

}

// frees list by calling freenode
void list_free(List list) {
	free_node(list);

}
