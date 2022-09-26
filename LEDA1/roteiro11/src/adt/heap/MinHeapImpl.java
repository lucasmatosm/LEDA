package adt.heap;

import java.util.Arrays;

/**
 * 
 * @author Wandson Allys Silva Dantas, Matricula: 112210672 
 *
 * @param <T>
 */

public class MinHeapImpl<T extends Comparable<T>> implements MinHeap<T> {

	private static final int INITIAL_SIZE = 20;
	private static final int INCREASING_FACTOR = 10;
	private T[] heap;
	private int size;

	@SuppressWarnings("unchecked")
	public MinHeapImpl() {
		heap = (T[]) new Comparable[INITIAL_SIZE];
		size = 0;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
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
	public void insert(T element) {
		if (element != null) {
			if (size >= heap.length) {
				heap = Arrays.copyOf(heap, heap.length + INCREASING_FACTOR);

			} else if (isEmpty()) {
				heap[0] = element;
				size++;

			} else {
				int i = size;
				while (i > 0 && heap[parent(i)].compareTo(element) > 0) {
					heap[i] = heap[parent(i)];
					i = parent(i);
				}
				heap[i] = element;
				size++;
			}
		}
	}

	@Override
	public T extractRootElement() {
		if (!isEmpty()) {
			T min = rootElement();
			Util.swap(heap, 0, size - 1);
			size--;
			heapify(0);
			return min;
		}
		return null;
	}

	@Override
	public T rootElement() {
		if (isEmpty())
			return null;
		return heap[0];
	}

	@SuppressWarnings("unchecked")
	@Override
	public T[] heapsort(T[] array) {
		T[] result = (T[]) new Comparable[size];
		buildHeap(array);
		int i = 0;
		while (!isEmpty()) {
			result[i] = extractRootElement();
			i++;
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void buildHeap(T[] array) {
		size = array.length;
		heap = (T[]) new Comparable[size];

		for (int i = 0; i < size; i++)
			heap[i] = array[i];

		for (int i = (size - 1) / 2; i >= 0; i--)
			heapify(i);
	}

	private void heapify(int position) {
		if (position >= 0) {
			int left = left(position);
			int right = right(position);
			int largest = position;

			if (left <= size - 1) {
				if (heap[left].compareTo(heap[position]) < 0)
					largest = left;
				else
					largest = position;
			}
			if (right <= size - 1 && heap[right].compareTo(heap[largest]) < 0) {
				largest = right;
			}
			if (largest != position) {
				Util.swap(heap, position, largest);
				heapify(largest);
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T[] toArray() {
		T[] result = (T[]) new Comparable[size];
		for (int i = 0; i < size; i++) {
			result[i] = heap[i];
		}
		return result;
	}

}