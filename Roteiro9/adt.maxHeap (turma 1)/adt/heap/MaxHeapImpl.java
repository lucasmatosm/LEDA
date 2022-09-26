package adt.heap;

public class MaxHeapImpl<T extends Comparable<T>> implements MaxHeap<T> {
	
	private static final int INITIAL_SIZE = 20;
	private static final int INCREASING_FACTOR = 10;

	private int parent(int i){
		//TODO Adjust
		return 0;
	}
	
	private int left(int i){
		//TODO Adjust
		return 0;
	}

	private int right(int i){
		//TODO Adjust
		return 0;
	}

	
	@Override
	public void buildHeap(T[] array){
		//TODO Implement your code here
		throw new UnsupportedOperationException("Operation not implemented");
	}
	
	private void heapify(int position){
		//TODO Implement your code here
		throw new UnsupportedOperationException("Operation not implemented");
	}
	
	@Override
	public boolean isEmpty(){
		//TODO Implement your code here
		return false;
	}
	
	@Override
	public void insert(T element) {
		//TODO Implement your code here
		throw new UnsupportedOperationException("Operation not implemented");

	}

	@Override
	public T extractRootElement(){
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T rootElement() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T[] heapsort(T[] array) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public T[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

}
