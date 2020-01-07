// Not written by me
// tests list and memory methods
//gcc -o test test.c memory.c datatype.c bst.c list.c

#include <stdio.h>
#include "bst.h"
#include "memory.h"
#include "list.h"

int main(void)
{

    mem_ini(1000);
    mem_print();
    printf("\n");

    List_node *list = list_ini();
    list_add(list, 1, 2);
    list_add(list, 2, 4);
    list_add(list, 3, 5);
    list_print(list);

    int *test1 = (int *)simu_malloc(4);
    int *test2 = (int *)simu_malloc(4);
    mem_print();
    printf("\n");
    *test1 = 5;
    *test2 = 6;
    simu_free(test2);
    simu_free(test1);
    mem_print();
    printf("\n");

    list_print(list);

    //Data *data = list_search(list, 2);
    //printf("%d\n", *data);

    list_add(list, 4, 6);
    list_add(list, 5, 7);
    list_add(list, 6, 8);

    mem_print();
    printf("\n");

    list_print(list);
    printf("\n");
    list_free(list);

    list_print(list);

    printf("\n");
    mem_print();

    mem_free();

}

