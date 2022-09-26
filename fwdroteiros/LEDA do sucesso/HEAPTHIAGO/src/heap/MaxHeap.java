package heap;

public interface MaxHeap<T extends Comparable<? super T>> {

	public boolean isEmpty();
	
	/**
	 * Inserts a new element into the heap and maintains the invariant.
	 */
	public void insert(T element);
	
	/**
	 * Removes and returns the maximum element of the heap. The method returns null 
	 * if the heap is empty.
	 */
	public T extractMaximum();
	
	/**
	 * Returns the maximum element without removing it. It returns null if the heap is empty.
	 */
	public T maximum();
	
	/**
	 * Sorts the elements of an array by using the heap concept. Note that the 
	 * algorithm is not designed to be in-place. Thus, the sorting must occurs 
	 * in the internal array of the heap. At the end an array containing only the not null
	 * element of the internal array must be returned (the heap can have extra space and
	 * cannot return array with null elements).  
	 */
	public T[] heapsort(T[] array);
	
	/**
	 * Builds a heap from an arbitrary array. This occurs is such a way that 
	 * the elements of the array are incorporated into the heap. 
	 */
	public void buildHeap(T[] array);
	
	/**
	 * Returns an array representing the underlying structure (internal array) of the heap.
	 * @return
	 */
	public T[] toArray();
	
}
