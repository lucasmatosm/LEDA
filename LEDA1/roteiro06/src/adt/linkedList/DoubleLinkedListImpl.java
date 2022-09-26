package adt.linkedList;

public class DoubleLinkedListImpl<T> extends SingleLinkedListImpl<T> implements
		DoubleLinkedList<T> {

	DoubleLinkedListNode<T> last;

	public DoubleLinkedListNode<T> getLast() {
		return last;
	}

	public DoubleLinkedListImpl() {
		last = new DoubleLinkedListNode<T>();
		head = last;
	}

	@Override
	public void insert(T element) {
		if (element != null) {
			if (head.isNIL()) {
				DoubleLinkedListNode<T> newNode;
				head = new DoubleLinkedListNode<T>();
				newNode = new DoubleLinkedListNode<T>(element, last,
						(DoubleLinkedListNode<T>) head);
				head.next = newNode;
				last.previous = newNode;
				last = newNode;
				head = newNode;
			} else {
				DoubleLinkedListNode<T> newLast = new DoubleLinkedListNode<T>(
						element, (DoubleLinkedListNode<T>) last.next, last);
				((DoubleLinkedListNode<T>) last.next).previous = newLast;
				last.next = newLast;
				last = newLast;
			}
		}
	}

	@Override
	public void insertFirst(T element) {
		if (element != null) {
			DoubleLinkedListNode<T> newNode;
			if (head.isNIL()) {
				head = new DoubleLinkedListNode<T>();
				newNode = new DoubleLinkedListNode<T>(element, last,
						(DoubleLinkedListNode<T>) head);
				head.next = newNode;
				last.previous = newNode;
				last = newNode;
			} else {
				newNode = new DoubleLinkedListNode<T>(element,
						(DoubleLinkedListNode<T>) head,
						((DoubleLinkedListNode<T>) head).previous);
				((DoubleLinkedListNode<T>) head).previous.next = newNode;
				((DoubleLinkedListNode<T>) head).previous = newNode;
			}
			head = newNode;
		}
	}

	@Override
	public void remove(T element) {
		if (!head.isNIL()) {
			DoubleLinkedListNode<T> aux = ((DoubleLinkedListNode<T>) head);
			aux = find(element);
			if (aux != null && !aux.isNIL()) {
				if (last == head) {
					last.next = null;
					last = last.previous;
					head = last;
				} else {
					aux.previous.next = aux.next;
					((DoubleLinkedListNode<T>) aux.next).previous = aux.previous;
					if (aux == head) {
						head = aux.next;
					}
					if (aux == last) {
						last = aux.previous;
					}
				}
			}
		}
	}

	@Override
	public void removeFirst() {
		if (!head.isNIL()) {
			if (last == head) {
				last.next = null;
				last = last.previous;
				head = last;
			} else {
				((DoubleLinkedListNode<T>) head.next).previous = ((DoubleLinkedListNode<T>) head).previous;
				((DoubleLinkedListNode<T>) head).previous.next = head.next;
				head = head.next;
			}
		}
	}

	@Override
	public void removeLast() {
		if (!head.isNIL()) {
			if (last == head) {
				last.next = null;
				last = last.previous;
				head = last;
			} else {
				((DoubleLinkedListNode<T>) last.next).previous = ((DoubleLinkedListNode<T>) last).previous;
				((DoubleLinkedListNode<T>) last).previous.next = last.next;
				last = last.previous;
			}
		}
	}

	public DoubleLinkedListNode<T> find(T element) {
		DoubleLinkedListNode<T> headAux = (DoubleLinkedListNode<T>) head;
		DoubleLinkedListNode<T> lastAux = last;
		DoubleLinkedListNode<T> aux = null;
		for (int i = 0; i <= size() / 2 && !headAux.isNIL(); i++) {
			if (headAux.getData().equals(element)) {
				aux = headAux;
			}
			if (lastAux.getData().equals(element)) {
				aux = lastAux;
			}
			headAux = (DoubleLinkedListNode<T>) headAux.next;
			lastAux = lastAux.previous;
		}
		return aux;
	}

	@Override
	public T search(T element) {
		DoubleLinkedListNode<T> aux = find(element);
		return (aux == null) ? null : aux.getData();
	}

}