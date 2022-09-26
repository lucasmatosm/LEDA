package sorting.divideAndConquer.hybridMergesort;

import sorting.SortingImpl;

/**
 * The algorithm is an hybrid of mergesort and insertion sort. Depending on the
 * size of the input, the algorithm decides between the application of mergesort
 * or insertion sort. The implementation of the algorithm must be in-place!
 */
public class HybridMergesort<T extends Comparable<T>> extends SortingImpl<T> {
	/**
	 * The limit to choose between applying merge or insertion. For inputs with
	 * size less or equal to 4, apply insertion sort.
	 */
	public static final int SIZE_LIMIT = 4;

	protected static int MERGESORT_APPLICATIONS = 0;
	protected static int INSERTIONSORT_APPLICATIONS = 0;

	private void merge(int leftIndex, int rightIndex, T[] array) {
		int i, j;
		T chave;
		for (i = (leftIndex + rightIndex) / 2 + 1; i <= rightIndex; i++) {
			chave = array[i];
			j = i - 1;
			while (j >= leftIndex && chave.compareTo(array[j]) < 0) {
				array[j + 1] = array[j];
				j--;
			}
			array[j + 1] = chave;
		}
	}

	private void mergesort(T[] array, int leftIndex, int rightIndex) {
		choosingSort(array, leftIndex, (leftIndex + rightIndex) / 2);
		choosingSort(array, (leftIndex + rightIndex) / 2 + 1, rightIndex);

		merge(leftIndex, rightIndex, array);
	}

	private void insertionsort(T[] array, int leftIndex, int rightIndex) {
		int i, j;
		T chave;
		for (i = leftIndex + 1; i <= rightIndex; i++) {
			chave = array[i];
			j = i - 1;
			while (j >= leftIndex && chave.compareTo(array[j]) < 0) {
				array[j + 1] = array[j];
				j--;
			}
			array[j + 1] = chave;
		}
	}

	protected void choosingSort(T[] array, int leftIndex, int rightIndex) {

		if (rightIndex - leftIndex + 1 <= SIZE_LIMIT) {
			INSERTIONSORT_APPLICATIONS++;
			insertionsort(array, leftIndex, rightIndex);
		} else {
			MERGESORT_APPLICATIONS++;
			mergesort(array, leftIndex, rightIndex);
		}
	}

	protected void sort(T[] array, int leftIndex, int rightIndex) {
		MERGESORT_APPLICATIONS = 0;
		INSERTIONSORT_APPLICATIONS = 0;

		if (leftIndex > rightIndex || leftIndex < 0
				|| rightIndex >= array.length) {
			return;
		}

		choosingSort(array, leftIndex, rightIndex);
	}

}
