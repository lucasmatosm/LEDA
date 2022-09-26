package sorting.variationsOfBubblesort;

import sorting.SortingImpl;

/**
 * This algorithm simulates a logical partitioning of the input array by considering 
 * different indexing, that is, the first sub-array is indexed by even elements and
 * the second sub-array is indexed by odd elements. Then, it applies a complete bubblesort
 * in the first sub-array (exchanges consider elements i and i+1), where i is even. After that, 
 * it applies a complete bubblesort in the second sub-array (exchanges consider elements 
 * i and i+1), where i is odd.  The algorithm repeats this idea until the array is 
 * completely ordered.
 */
public class OddEvenBubblesort<T extends Comparable<T>> extends SortingImpl<T>{
	protected void sort(T[] array,int leftIndex, int rightIndex){
		// TODO Auto-generated method stub
	}
}
