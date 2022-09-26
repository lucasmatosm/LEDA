package adt.heap;

import java.util.Arrays;

public class MaxHeapImpl<T extends Comparable<T>> implements MaxHeap<T> {

	private static final int INITIAL_SIZE = 20;
	private static final int INCREASING_FACTOR = 10;
	private T[] heap;
	private int size;

	@SuppressWarnings("unchecked")
	public MaxHeapImpl() {
		heap = (T[]) new Comparable[INITIAL_SIZE];
		size = 0;
	}

	private int parent(int i) {
		return (i - 1) / 2;
	}

	private int left(int i) {
		return 2 * i + 1;
	}

	private int right(int i) {
		return 2 * i + 2;
	}

	@Override
	public void buildHeap(T[] array) {
		heap = array.clone();
		size = array.length;
		for (int i = size / 2; i >= 0; i--) {
			heapify(i);
		}
	}

	private void heapify(int position) {
		int l = left(position);
		int r = right(position);
		int maximo;
		if (l < size && heap[l].compareTo(heap[position]) > 0) {
			maximo = l;
		} else {
			maximo = position;
		}
		if (r < size && heap[r].compareTo(heap[maximo]) > 0) {
			maximo = r;
		}
		if (maximo != position) {
			Util.swap(heap, position, maximo);
			heapify(maximo);
		}
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public void insert(T element) {
		if (size >= heap.length) {
			heap = Arrays.copyOf(heap, size + INCREASING_FACTOR);
		}
		int i = size;
		while (i > 0 && heap[parent(i)].compareTo(element) < 0) {
			heap[i] = heap[parent(i)];
			i = parent(i);
		}
		heap[i] = element;
		size++;
	}

	@Override
	public T extractRootElement() {
		if (size == 0)
			return null;
		T max = heap[0];
		heap[0] = heap[size - 1];
		heap[size - 1] = null;
		size--;
		heapify(0);
		return max;
	}

	@Override
	public T rootElement() {
		return heap[0];
	}

	@Override
	public T[] heapsort(T[] array) {
		T[] copy = array.clone();
		buildHeap(copy);
		int index = 0;
		while (size > 0) {
			copy[index++] = extractRootElement();
		}
		return copy;
	}

	@Override
	public T[] toArray() {
		return heap.clone();
	}

}
