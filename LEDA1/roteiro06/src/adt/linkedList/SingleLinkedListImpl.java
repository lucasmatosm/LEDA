package adt.linkedList;

public class SingleLinkedListImpl<T> implements LinkedList<T> {

	protected SingleLinkedListNode<T> head;

	public SingleLinkedListImpl() {
		head = new SingleLinkedListNode<T>();
	}

	@Override
	public boolean isEmpty() {
		return head.isNIL();
	}

	@Override
	public int size() {
		int size = 0;
		SingleLinkedListNode<T> aux = head;
		while (!aux.isNIL()) {
			aux = aux.next;
			size++;
		}
		return size;
	}

	@Override
	public T search(T element) {
		SingleLinkedListNode<T> aux = head;
		while (!aux.isNIL()) {
			if (aux.getData().equals(element)) {
				break;
			}
			aux = aux.next;
		}
		return aux.getData();
	}

	@Override
	public void insert(T element) {
		if (element == null)
			return;
		if (head.isNIL()) {
			head = new SingleLinkedListNode<T>(element, head);
		} else {
			SingleLinkedListNode<T> aux = head;
			while (!aux.next.isNIL()) {
				aux = aux.next;
			}
			aux.next = new SingleLinkedListNode<T>(element, aux.next);
		}
	}

	@Override
	public void remove(T element) {
		if (!head.isNIL()) {
			if (head.getData().equals(element)) {
				head = head.next;
			} else {
				SingleLinkedListNode<T> aux = head;
				while (!aux.next.isNIL()) {
					if (aux.next.getData().equals(element)) {
						break;
					}
					aux = aux.next;
				}
				if (!aux.next.isNIL()) {
					aux.next = aux.next.next;
				}
			}
		}
	}

	@Override
	public T[] toArray() {
		@SuppressWarnings("unchecked")
		T[] array = (T[]) new Object[size()];
		SingleLinkedListNode<T> aux = head;
		for (int i = 0; !aux.isNIL(); i++) {
			array[i] = aux.getData();
			aux = aux.next;
		}
		return array;
	}

}