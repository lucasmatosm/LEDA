public interface MinHeap<T extends Comparable<? super T>> {

	/**
	 * Verifica se a Heap � vazia.
	 * @return Um boolean.
	 */
	public boolean isEmpty();
	
	/**
	 * Inserts a new element into the heap and maintains the invariant.
	 */
	public void insert(T element);
	
	/**
	 * Removes and returns the minimum element of the heap. The method returns null 
	 * if the heap is empty.
	 */
	public T extractMinimum();
	
	/**
	 * Returns the minimum element without removing it. It returns null if the heap is empty.
	 */
	public T minimum();
	
	/**
	 * Sorts the elements of an array by using the heap concept. Note that the 
	 * algorithm is not designed to be in-place. Thus, the sorting can return in 
	 * an auxiliary array to be returned.   
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
