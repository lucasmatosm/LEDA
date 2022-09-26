package sorting;

public class SimpleSortingImpl implements SimpleSorting {

	/**
	 * Implement the bubble sort idea. The algorithm must work in-place and have
	 * O(n^2).
	 */
	@Override
	public void bubbleSort(int[] array) {
		boolean houveTroca = true;
		while (houveTroca){
			houveTroca = false;
			for(int i = 0; i < array.length - 1; i++){
				if (array[i] > array[i + 1]){
					Util.swap(array, i, i + 1);
					houveTroca = true;
				}
			}
		}
	}

	/**
	 * Implement the selection sort idea. The algorithm must work in-place and have
	 * O(n^2).
	 */
	@Override
	public void selectionSort(int[] array) {
		int tamanho = array.length;
		int indiceMenor;
		
		for (int i = 0; i < tamanho; i ++){
			indiceMenor = i;
			for(int j = i + 1; j < tamanho; j ++){
				if(array[j] < array[indiceMenor]){
					indiceMenor = j;
				}
			}
			Util.swap(array, i, indiceMenor);
		}
	}

	/**
	 * Implement the insertion sort idea. The algorithm must work in-place and have
	 * O(n^2).
	 */
	@Override
	public void insertionSort(int[] array) {
		int tamanho = array.length;
		
		for(int i = 1; i < tamanho; i++){
			int elemento = array[i]; 
			for(int j = i - 1; j >= 0 && array[j] > elemento; j--){
				array[j + 1] = array[j];
				array[j] = elemento;
			}	
		}
	}
}
