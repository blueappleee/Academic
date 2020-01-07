// Connor Ciavarella, Student Number: 251023554
// CS 2211a Assignment 4 Part 1

// ====== this is in bst.h
#include "data.h"
typedef struct {Node *tree_nodes; unsigned int *free_nodes;
int size; int top; int root;} BStree_struct;
typedef BStree_struct* BStree;
BStree bstree_ini(int size);
void bstree_insert(BStree bst, Key *key, Data data);
void bstree_traversal(BStree bst);
void bstree_free(BStree bst);
