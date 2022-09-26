package adt.stack;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.DoubleLinkedListImpl;

public class StackDoubleLinkedListImpl<T> implements Stack<T> {

	protected DoubleLinkedList<T> list;
	int size, numOfElements;

	public StackDoubleLinkedListImpl(int size) {
		list = new DoubleLinkedListImpl<T>();
		this.size = size;
		numOfElements = 0;
	}

	@Override
	public void push(T element) throws StackOverflowException {
		if (isFull())
			throw new StackOverflowException();

		if (element != null) {
			numOfElements++;
			list.insert(element);
		}
	}

	@Override
	public T pop() throws StackUnderflowException {
		if (isEmpty())
			throw new StackUnderflowException();

		T elem = top();
		numOfElements--;
		list.removeLast();
		return elem;
	}

	@Override
	public T top() {
		return ((DoubleLinkedListImpl<T>) list).getLast().getData();
	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public boolean isFull() {
		return numOfElements >= size;
	}

}