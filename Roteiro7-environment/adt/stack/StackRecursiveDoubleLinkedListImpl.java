package adt.stack;

import adt.linkedList.DoubleLinkedList;

public class StackRecursiveDoubleLinkedListImpl<T> implements Stack<T> {

	protected DoubleLinkedList<T> list;
	protected int size;
	
	public StackRecursiveDoubleLinkedListImpl(int size) {
		this.size = size;
	}
	
	@Override
	public void push(T element) throws StackOverflowException {
		// TODO Auto-generated method stub

	}

	@Override
	public T pop() throws StackUnderflowException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T top() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isFull() {
		// TODO Auto-generated method stub
		return false;
	}

}
