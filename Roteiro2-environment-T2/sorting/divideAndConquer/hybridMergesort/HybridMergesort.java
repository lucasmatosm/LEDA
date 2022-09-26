package sorting.divideAndConquer.hybridMergesort;


import sorting.SortingImpl;

/**
 * The algorithm is an hybrid of mergesort and insertion sort. Depending on the size of the input, 
 * the algorithm decides between the application of mergesort or insertion 
 * sort. The implementation of the algorithm must be in-place!
 */
public class HybridMergesort<T extends Comparable<T>> extends SortingImpl<T>{
	/**
	 * The limit to choose between applying merge or insertion. For inputs with size 
	 * less or equal to 4, apply insertion sort. 
	 */
	public static final int SIZE_LIMIT = 4;
	
	protected static int MERGESORT_APPLICATIONS = 0;
	protected static int INSERTIONSORT_APPLICATIONS = 0;
	
	protected void sort(T[] array,int leftIndex, int rightIndex){
		// TODO Auto-generated method stub
		
		//Do not forget of changing the counters for mergesorts and insertionsorts applied.
	}
	
}
