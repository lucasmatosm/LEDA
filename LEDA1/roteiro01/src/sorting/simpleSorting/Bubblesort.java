package sorting.simpleSorting;

import sorting.SortingImpl;
import sorting.Util;

/**
 * The bubble sort algorithm pushes big elements to the right or small elements
 * to the left by exchanging adjacent elements. The algorithm must sort the
 * elements from leftIndex to rightIndex (inclusive).
 */
public class Bubblesort<T extends Comparable<T>> extends SortingImpl<T> {

	@Override
	protected void sort(T[] array, int leftIndex, int rightIndex) {
		if (array != null && array.length > 1 && leftIndex >= 0
				&& rightIndex <= array.length - 1) {
			boolean swapped = true;
			while (swapped) {
				swapped = false;
				for (int i = leftIndex; i < rightIndex; i++) {
					if (array[i].compareTo(array[i + 1]) > 0) {
						Util.swap(array, i, i + 1);
						swapped = true;
					}
				}
			}
		}
	}
}
