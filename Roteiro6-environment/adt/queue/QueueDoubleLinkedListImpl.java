package adt.queue;

import adt.linkedList.DoubleLinkedList;

public class QueueDoubleLinkedListImpl<T> implements Queue<T> {

	
	protected DoubleLinkedList<T> list;
	protected int size;
	
	public QueueDoubleLinkedListImpl(int size) {
		//TODO adjust your constructor
	}
	
	@Override
	public void enqueue(T element) throws QueueOverflowException {
		// TODO Auto-generated method stub

	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T head() {
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
