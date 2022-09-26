package sorting.divideAndConquer.hybridMergesort;


import java.lang.reflect.Array;
import java.util.concurrent.ArrayBlockingQueue;

import sorting.SortingImpl;
import sorting.divideAndConquer.Mergesort;

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
		if((rightIndex - leftIndex) > 4 ){
			mergesort(array, leftIndex, rightIndex);
		}else{
			Insertion(array, leftIndex, rightIndex);
		}		
	}
	
	protected void Insertion(T[] array, int leftIndex, int rightIndex) {
		for (int i = leftIndex + 2; i <= rightIndex; i++) {
			T key = array[i];
			int j = i - 1;
			while ((j >= 0) && (array[j].compareTo(key) > 0)) {
				array[j + 1] = array[j];
				j--;
			}
			array[j + 1] = key;
		}
	}

	
	protected void mergesort(T[] array,int leftIndex, int rightIndex) {
		if((leftIndex <= rightIndex) && (leftIndex >= 0) && (rightIndex <= array.length)){
			int middle = (leftIndex + rightIndex) / 2;
			sort(array, leftIndex, middle);
			sort(array, middle + 1, rightIndex);
			merge(leftIndex, rightIndex, array);
		}
	}
	
	private void merge(int leftIndex, int rightIndex, T[] array){
		int middle = (leftIndex + rightIndex) / 2;
		T[] temporal = (T[])(Array.newInstance(array[0].getClass(), rightIndex - leftIndex + 1));
		int i = leftIndex, j =  middle+ 1, temporalIndex = 0;
		while(i <= middle || j <= rightIndex){
			if(j > rightIndex || (i <= middle && array[i].compareTo(array[j]) < 0)){
				temporal[temporalIndex++] = array[i];
				i++;
			}
			else{
				temporal[temporalIndex++] = array[j];
				j++;
			}
		}
		for(i = 0; i < temporal.length; i++){
			array[leftIndex + i] = temporal[i];
		}
	}

}

