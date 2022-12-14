package sorting.variationsOfSelectionsort;

import sorting.SortingImpl;

/**
 * This selectionsort variation puts the greatest element into the right position (walking from 
 * left to right) and, after that, it puts the smallest element into the left position (walking from 
 * right to left). This happens in a same iteration. The following iterations do the same until 
 * the array is completely ordered.The algorithm must sort the elements from leftIndex to 
 * rightIndex (inclusive). 
 */
public class BidirectionalSelectionsort<T extends Comparable<T>> extends SortingImpl<T>{

	@Override
	protected void sort(T[] array,int leftIndex, int rightIndex) {
		// TODO Auto-generated method stub

	}

}
