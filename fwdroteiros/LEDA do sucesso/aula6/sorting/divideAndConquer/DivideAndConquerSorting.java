package sorting.divideAndConquer;

public interface DivideAndConquerSorting {
	/**
	 * Applies the merge sort algorithm and returns an ordered array.
	 */
	public void mergeSort(int[] array);
	
	/**
	 * Applies the quick sort algorithm (in-place).
	 */
	public void quicksort(int[] array, int left, int right);
}
