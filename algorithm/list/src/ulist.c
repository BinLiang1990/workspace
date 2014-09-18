//unidirectional list
#include <stdio.h>
#include <malloc.h>

typedef struct Element {
	void *data;
	struct Element *next;
}Element;

typedef struct Ulist {
	int size;
	Element *head;
	Element *tail;
}Ulist;

void list_init(Ulist *list) {
	list->size = 0;
	list->head = NULL;
	list->tail = NULL;
}

void list_destroy(Ulist *list) {
	void *data;
	while(list->size > 0) {
		list_remove_next(list, NULL, &data);
	}
}

/*insert a new element after given target,if target is null,insert a new element
to tail*/
int list_insert_next(Ulist *list, Element *target, const void *data) {
	Element *element = (Element *)malloc(sizeof(Element));
	if(element == NULL) {
		return -1;
	}
	element->data = data;
	if(target == NULL) {
		element->next = NULL;
		if(list->size == 0) {
			list->head = element;
			list->tail = element;
		} else {
			list->tail->next = element;
			list->tail = element;
		}
	} else {
		list->tail = list->tail == target ? element : list->tail;
		element->next = target->next;
		target->next = element;
	}
	++list->size;
	return 0;
}
/*remove a element after given target,if target is null,remove head*/
int list_remove_next(Ulist *list, Element *target, const void **data) {
	if(list->size == 0) {
		return -1;
	}
	Element *element;
	if(target == NULL) {
		*data = list->head->data;
		element = list->head;
		list->head = list->head->next;
		if(list->size == 1) {
			list->tail = NULL;
		}
	} else {
		if(target->next == NULL) {
			return -1;
		}
		element = target->next;
		target->next = target->next->next;
		*data = element->data;
		if(element->next == NULL) {
			list->tail = target;
		}
	}
	--list->size;
	free(element);
	return 0;
}

int main() {
	Ulist *list = (Ulist *)malloc(sizeof(Ulist));
	int a = 2, b = 3;
	void *p;
	list_insert_next(list, NULL, &a);
	list_insert_next(list, NULL, &b);
	printf("%d\n", *(int *)list->head->data + 100);
	list_remove_next(list, NULL, &p);
	printf("remove data is %d\n", *(int *)p);
	printf("%d\n", *(int *)list->head->data + 10);
	printf("size is %d\n", list->size);
	return 0;
}
