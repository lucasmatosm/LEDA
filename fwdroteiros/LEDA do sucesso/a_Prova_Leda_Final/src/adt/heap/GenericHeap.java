package adt.heap;

public interface GenericHeap<T extends Comparable<? super T>> {

	public abstract boolean isEmpty();

	/**
	 * Inserts a new element into the heap and maintains the invariant.
	 */
	public abstract void insert(T element);

	/**
	 * Sorts the elements of an array by using the heap concept. Note that the 
	 * algorithm is not designed to be in-place. Thus, the sorting must occurs 
	 * in the internal array of the heap. At the end an array containing only the not null
	 * element of the internal array must be returned (the heap can have extra space and
	 * cannot return array with null elements).  
	 */
	public abstract T[] heapsort(T[] array);

	/**
	 * Builds a heap from an arbitrary array. This occurs is such a way that 
	 * the elements of the array are incorporated into the heap. 
	 */
	public abstract void buildHeap(T[] array);

	/**
	 * Returns an array representing the underlying structure (internal array) of the heap.
	 * @return
	 */
	public abstract T[] toArray();

}