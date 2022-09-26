package sorting.simpleSorting;

import sorting.SortingImpl;
import sorting.Util;

/**
 * The insertion sort algorithm consumes the array (element by element) and
 * inserts the elements into an ordered list. The algorithm must sort the
 * elements from leftIndex to rightIndex (inclusive).
 */
public class Insertionsort<T extends Comparable<T>> extends SortingImpl<T> {

	@Override
	protected void sort(T[] array, int leftIndex, int rightIndex) {
		if (array != null && array.length > 1 && leftIndex >= 0
				&& rightIndex <= array.length - 1) {
			for (int j = leftIndex; j <= rightIndex; j++) {
				T chave = array[j];
				int i = j - 1;

				while (i >= 0 && array[i].compareTo(chave) > 0) {
					Util.swap(array, i, i + 1);
					i--;
				}
			}
		}
	}
}
