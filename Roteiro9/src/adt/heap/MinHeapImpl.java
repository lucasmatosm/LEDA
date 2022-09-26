package adt.heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MinHeapImpl<T extends Comparable<T>> implements MinHeap<T> {

	private static final int INITIAL_SIZE = 20;
	private static final int INCREASING_FACTOR = 10;
	@SuppressWarnings("unchecked")
	private T[] heap = (T[]) new Comparable[INITIAL_SIZE];
	private int size = 0;
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public void insert(T element) {
		if (isEmpty()) {
			heap[0] = element;
			size++;
		}
		else if (size >= heap.length) 
			heap = Arrays.copyOf(heap, heap.length + INCREASING_FACTOR);
		else {
			int i = size;
			while (i > 0 && heap[parent(i)].compareTo(element) > 0) {
				heap[i] = heap[parent(i)];
				i = parent(i);
			}
			heap[i] = element;
			size++;
		}
	}

	@Override
	public T extractRootElement() {
		if (isEmpty()) return null;
		T min = rootElement(); 
		Util.swap(heap, 0, size-1); 
		size --; 
		heapify(0); 
		return min; 
	}

	@Override
	public T rootElement() {
		if (!isEmpty()) 
			return heap[0];
		else 
			return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T[] heapsort(T[] array) {
		List<T> result = new ArrayList<T>();
		buildHeap(array);
		while(!isEmpty()) 
			result.add(extractRootElement());
		return (T[]) result.toArray(new Comparable[0]);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void buildHeap(T[] array) {
        size = array.length;
		heap = (T[]) new Comparable[array.length]; 
		for (int i = 0; i < array.length; i++) 
			heap[i] = array[i];
		
		for (int i = ((array.length - 1) / 2); i >= 0; i--) 
			heapify(i);
	}

	private void heapify(int position){
		int left = left(position);
		int right = right(position);
		int largest = position;
		if ((left <= size-1)) {
			if (heap[left].compareTo(heap[position]) < 0) 
				largest = left;
			else 
				largest = position;
		}
		if ((right <= size-1)) {
			if (heap[right].compareTo(heap[largest]) < 0) 
				largest = right;	
		}
		if (largest != position){ 
			Util.swap(heap, position, largest);
			heapify(largest);
		}
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public T[] toArray() {
		T[] result = (T[]) new Comparable[size];
		for(int i = 0; i < result.length; i++) 
			result[i] = heap[i];
		return result;
	}
	protected int parent(int i) {
		return (i-1)/2;
	}

	protected int left(int i) {
		return (2*i) +1;
	}

	protected int right(int i) {
		return (2*i) + 2;
	}

}
