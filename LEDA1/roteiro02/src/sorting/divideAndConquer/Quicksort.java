package sorting.divideAndConquer;

import sorting.SortingImpl;
import sorting.Util;

public class Quicksort<T extends Comparable<T>> extends SortingImpl<T> {

	private int partition(T[] array, int leftIndex, int rightIndex) {
		int i = leftIndex + 1, j = rightIndex;
		T pivot = array[leftIndex];

		while (i <= j) {
			while (i <= j && array[i].compareTo(pivot) <= 0)
				i++;
			while (i <= j && array[j].compareTo(pivot) > 0)
				j--;
			if (i < j)
				Util.swap(array, i, j);
		}
		Util.swap(array, leftIndex, j);
		return j;
	}

	@Override
	protected void sort(T[] array, int leftIndex, int rightIndex) {
		if (leftIndex >= rightIndex || leftIndex < 0
				|| rightIndex >= array.length) {
			return;
		}

		int pivotIndex = partition(array, leftIndex, rightIndex);

		sort(array, leftIndex, pivotIndex - 1);
		sort(array, pivotIndex + 1, rightIndex);
	}
}
