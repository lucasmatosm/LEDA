package sorting.divideAndConquer;

import java.lang.reflect.Array;

import sorting.SortingImpl;

public class Mergesort<T extends Comparable<T>> extends SortingImpl<T> {

	private void merge(int leftIndex, int rightIndex, T[] array) {
		@SuppressWarnings("unchecked")
		T[] aux = (T[]) (Array.newInstance(array[0].getClass(), rightIndex - leftIndex + 1));
		
		int i = leftIndex, j = (leftIndex + rightIndex) / 2 + 1, auxIndex = 0;
		
		while (i <= (leftIndex + rightIndex) / 2 || j <= rightIndex) {
			if (j > rightIndex || (i <= (leftIndex + rightIndex) / 2 && array[i].compareTo(array[j]) < 0)) {
				aux[auxIndex++] = array[i];
				i++;
			} else {
				aux[auxIndex++] = array[j];
				j++;
			}
		}
		for (i = 0; i < aux.length; i++) {
			array[leftIndex + i] = aux[i];
		}
	}

	@Override
	protected void sort(T[] array, int leftIndex, int rightIndex) {
		if (leftIndex >= rightIndex || leftIndex < 0
				|| rightIndex >= array.length) {
			return;
		}

		sort(array, leftIndex, (leftIndex + rightIndex) / 2);
		sort(array, (leftIndex + rightIndex) / 2 + 1, rightIndex);

		merge(leftIndex, rightIndex, array);
	}

}
