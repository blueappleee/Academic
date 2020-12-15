// Written by Connor Ciavarella, Student Number:
// Written for CS 2211a Assignment 4 Part 1

// ====== this is in bst.c
#include <stdio.h>
#include <stdlib.h>
#include "bst.h"

// Input: ’size’: size of an array
// Output: a pointer of type BStree,
// i.e. a pointer to an allocated memory of BStree_struct type
// Effect: dynamically allocate memory of type BStree_struct
// allocate memory for a Node array of size+1 for member tree_nodes
// allocate memory for an unsigned int array of size+1 for member free_nodes
// set member size to ’size’;
// set entry free_nodes[i] to i
// set member top to 1;
// set member root to 0;
BStree bstree_ini(int size) {
	// sets a tree pointer
	BStree_struct *tree = (BStree_struct *) malloc(sizeof(BStree_struct));

	// sets the tree instance values
	tree->tree_nodes = (Node *) malloc((size+1) * sizeof(Node));
	tree->size = size;
	tree->free_nodes = (int *) malloc((size+1) * sizeof(Node));
	tree->top = 1;
	tree->root = 0;

	// sets the free nodes to index integer
	for (int i = 0; i < size+1; i++) {
			tree->free_nodes[i] = i;

		}

	return tree;

}

// returns a pointer to the new node being created
Node *new_node(Key *key, Data data) {
	// sets pointer to new node
	Node *newNode = (Node *) malloc(sizeof(Node));

	// sets instance
	newNode->data = data;
	newNode->key = key;
	newNode->left = 0;
	newNode->right = 0;

	return newNode;

}

// returns the index of the parent to the new node thats going to be insert
int find_parent(BStree bst, Key *key, Node currNode, int index) {
	// compares the key
	int keyCompare = key_comp(currNode.key, key);

	// if key is same then return zero
	if (keyCompare == 0) {
		return index;

	}

	else if (keyCompare < 0) {
		// if zero then return the index as it is the parent
		if (currNode.right == 0) {
			return index;

		}

		// if not zero continue to find the parent
		else {
			return find_parent(bst, key, (*bst).tree_nodes[currNode.right], currNode.right);

		}
	}

	else {
		// if zero then return the index as it is the parent
		if (currNode.left == 0) {
			return index;

		}

		// if not zero continue to find the parent
		else {
			return find_parent(bst, key, (*bst).tree_nodes[currNode.left], currNode.left);

		}
	}
}

// Input: ’bst’: a binary search tree
// ’key’: a pointer to Key
// ’data’: an integer
// Effect: ’data’ with ’key’ is inserted into ’bst’
// if ’key’ is already in ’bst’, do nothing
void bstree_insert(BStree bst, Key *key, Data data) {
	// if size = top full so cant insert
	if ((*bst).top == (*bst).size) {
		printf("Binary Search Tree Full. Insertion failed, Node not added.\n");

	}

	// if tree empty insert at root
	else if ((*bst).root == 0) {

		 Node *insertNode = new_node(key, data);

		(*bst).tree_nodes[(*bst).top] = *insertNode;

		(*bst).top++;
		(*bst).root++;

		// frees node
		free(insertNode);

	}

	// else insert at top but set parent
	else {
		// gets parent index
		int bstIndex = find_parent(bst, key, (*bst).tree_nodes[(*bst).root], (*bst).root);

		// gets parent
		Node parent = (*bst).tree_nodes[bstIndex];

		// compares to set left or right for parent
		int compare = key_comp(parent.key, key);

		if (compare > 0) {
			(*bst).tree_nodes[bstIndex].left = (*bst).top;

		}

		else if (compare < 0) {
			(*bst).tree_nodes[bstIndex].right = (*bst).top;

		}

		// inserts node if not the same
		if (compare != 0) {
			Node *insertNode = new_node(key, data);

			(*bst).tree_nodes[(*bst).top] = *insertNode;
			(*bst).top++;

			free(insertNode);

		}
	}
}

// recursive inorder traversal printing nodes
void inorder(BStree bst, Node root) {
	// if node left exists call on the left
	if (root.left != 0) {
		inorder(bst, bst->tree_nodes[root.left]);

	}

	// print node
	print_node(root);

	// if node right exists call on right
	if (root.right != 0) {
		inorder(bst, bst->tree_nodes[root.right]);

	}

}

// Input: ’bst’: a binary search tree
// Effect: print all the nodes in bst using in order traversal
void bstree_traversal(BStree bst) {
	// if tree not empty
	if (bst->root != 0) {
		inorder(bst, bst->tree_nodes[bst->root]);

	}
}

// uses postorder traversal to free pointers
void postorder_Free(BStree bst, Node root) {
	// if node left exists call on the left
	if (root.left != 0) {
		postorder_Free(bst, bst->tree_nodes[root.left]);

	}

	// if node right exists call on right
	if (root.right != 0) {
		postorder_Free(bst, bst->tree_nodes[root.right]);

	}

	// frees keyname and key
	free(root.key->name);
	free(root.key);

}

// Input: ’bst’: a binary search tree
// Effect: all dynamic memory used by bst are freed
void bstree_free(BStree bst) {
	// frees each node
	if (bst->root != 0) {
		postorder_Free(bst, bst->tree_nodes[bst->root]);

	}

	// free lists and bst
	free(bst->tree_nodes);
	free(bst->free_nodes);
	free(bst);

}

