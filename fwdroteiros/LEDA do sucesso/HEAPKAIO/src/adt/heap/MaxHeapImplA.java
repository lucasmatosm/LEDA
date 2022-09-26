package adt.heap;

import java.util.Comparator;

public class MaxHeapImpl<T extends Comparable<? super T>> implements MaxHeap<T> {

	private static final int INITIAL_SIZE = 20;
	private static final int INCREASING_FACTOR = 10;
	int size;
	private T[] heap;

	public MaxHeapImpl() {
		heap = (T[]) new Comparable[INITIAL_SIZE];
		size = -1;
	}

	private void setSize(int i) {
		size = i;
	}

	private void setHeap(T[] array) {
		for (int i = 0; i < array.length; i++) {
			heap[i] = array[i];
		}
	}

	private void setHeapSize(int newSize) {
		heap = (T[]) new Comparable[newSize];
	}

	private void setHeap() {
		T[] arrayAux = (T[]) new Comparable[heap.length + INCREASING_FACTOR];
		for (int i = 0; i < heap.length; i++) {
			arrayAux[i] = heap[i];
		}
		setHeapSize(arrayAux.length);
		setHeap(arrayAux);

	}

	private int parent(int i) {
		return (i - 1) / 2;
	}

	private int left(int i) {
		return 2*i + 1;
	}

	private int right(int i) {
		return 2*i + 2;
	}

	@Override
	public void buildHeap(T[] array) {

		size = array.length;
		heap = (T[]) new Comparable[array.length];
		for(int i =0;i<array.length;i++){
			heap[i] = array[i];
		}
		for (int i = 0; i < array.length / 2; i++) {
			heapify(i);
		}

	}

	private void heapify(int position) {
		int maior;
		int l = left(position);
		int r = right(position);
		if ((l < size) && (heap[l].compareTo(heap[position]) > 0)) {
			maior = l;
		} else {
			maior = position;
		}
		if ((r < size) && (heap[r].compareTo(heap[maior]) > 0)) {
			maior = r;
		}
		if (maior != position) {
			Util.swap(heap, position, maior);
			heapify(maior);
		}
	}

	@Override
	public boolean isEmpty() {

		return size == -1;

	}

	@Override
	public void insert(T element) {
		if (size + 1 > heap.length) {
			aumentaHeap();
		}
		size++;
		int i = size;
		while (i > 0 && heap[parent(i)].compareTo(element) < 0) {
			heap[i] = heap[parent(i)];
			i = parent(i);
		}
		heap[i] = element;
	}

	@Override
	public T extractMaximum() {
		if (size > -1) {
			T max = heap[0];
			heap[0] = heap[size];
			setSize(size - 1);
			heapify(1);
			return max;
		}
		return null;
	}

	@Override
	public T maximum() {
		if (this.isEmpty()) {
			return null;
		} else {
			return heap[0];
		}
	}

	private void aumentaHeap() {
		T[] heapTemp = (T[]) new Comparable[heap.length + INCREASING_FACTOR];
		for (int i = 0; i < heap.length; i++) {
			heapTemp[i] = heap[i];
		}
		heap = heapTemp;
	}

	@Override
	public T[] heapsort(T[] array) {

		buildHeap(array);
		for (int i = 0; i > array.length / 2; i++) {
			Util.swap(array, 1, i);
			size -= 1;
			heapify(1);
		}
		return null;
	}

	@Override
	public T[] toArray() {

		return heap;
	}

}
