package sorting.combinationsOfSortingAlgorithms;

import sorting.SortingImpl;
import sorting.Util;

/**
 * The combination works as follows: While the array is not sorted, do: - Do a
 * single pass of the bubble sort, left to right. - During the pass, take note
 * of the least element in the unsorted part of the array. - Swap the least
 * element to the front of the unsorted part of the array.
 */
public class CombinationBubbleSelectionSort<T extends Comparable<T>> extends
		SortingImpl<T> {

	@Override
	protected void sort(T[] array, int leftIndex, int rightIndex) {
		// ALTERNATIVE BUBBLE SORT USING WHILE LOOP
		if (array != null && array.length > 1 && leftIndex > 0
				&& rightIndex < array.length) {
			boolean swapped;
			int min, k = 0;
			do {
				swapped = false;
				for (int i = min = leftIndex + k; i < rightIndex - k; i++) {
					// BUBBLE SORT
					if (array[i].compareTo(array[i + 1]) > 0) {
						Util.swap(array, i, i + 1);
						swapped = true;
					}
					// SELECTION SORT
					if (array[i].compareTo(array[min]) < 0) {
						min = i;
					}
				}
				if (array.length > 0)
					Util.swap(array, min, leftIndex + k);
				k++;
			} while (swapped);
		}
	}

}
