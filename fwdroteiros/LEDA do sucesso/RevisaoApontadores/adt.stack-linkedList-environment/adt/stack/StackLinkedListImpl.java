package adt.stack;


import adt.linkedList.RecursiveSingleLinkedListImpl;

public class StackLinkedListImpl<T> implements Stack<T> {

	/**
	 * The top must be a single linked list implemented RECURSIVELY.
	 */
	protected RecursiveSingleLinkedListImpl<T> top;
	
	public StackLinkedListImpl() {
		top = new RecursiveSingleLinkedListImpl<T>();
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
		//admitir que pilha nunca enche porque cresce dinamicamente
		return false;
	}

}
