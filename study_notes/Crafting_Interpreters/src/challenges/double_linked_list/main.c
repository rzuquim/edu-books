
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct dll_node {
    struct dll_node* next;
    struct dll_node* prev;
    char* data;
} Node;

Node* createNode(char* data) {
    Node* newNode = (Node*)malloc(sizeof(Node));
    if (!newNode) {
        printf("Memory allocation failed\n");
        return NULL;
    }
    newNode->data = data;
    newNode->next = NULL;
    newNode->prev = NULL;
    return newNode;
}

Node* push(Node* head, char* newData) {
    Node* newNode = createNode(newData);
    head->next = newNode;
    newNode->prev = head;
    return newNode;
}

Node* find(Node* haystack, char* needle) {
    Node* currNode = haystack;
    while (currNode != NULL) {
        if (strcmp(currNode->data, needle) == 0) {
            return currNode;
        }
        currNode = currNode->next;
    }
    return NULL;
}

Node* pluck(Node* haystack, char* needle) {
    Node* toDelete = find(haystack, needle);

    if (toDelete == NULL) {
        return NULL;
    }

    if (toDelete->next != NULL) {
        toDelete->next->prev = toDelete->prev;
    }

    if (toDelete->prev != NULL) {
        toDelete->prev->next = toDelete->next;
    }
    return toDelete;
}

void display(Node* dll) {
    Node* currNode = dll;
    while (currNode != NULL) {
        printf("%s -> ", currNode->data);
        currNode = currNode->next;
    }
    printf("NULL \n");
}

int main() {
    Node* list = createNode("orange");
    Node* curr = push(list, "strawberry");
    curr = push(curr, "banana");
    curr = push(curr, "blueberry");
    curr = push(curr, "papaya");

    display(list);

    Node* banana = find(list, "banana");
    printf("Found %s\n", banana->data);

    Node* miss = find(list, "guaba");
    if (miss == NULL) {
        printf("Could not find guaba\n");
    }

    char* toDelete = "blueberry";
    printf("Deleting %s\n", toDelete);
    Node* removed = pluck(list, toDelete);
    if (removed != NULL) {
        free(removed);
    }
    display(list);

    return 0;
}
