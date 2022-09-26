package sorting.divideAndConquer;

public class DivideAndConquerSortingImpl implements DivideAndConquerSorting {

	@Override
	public void mergeSort(int[] array) {


		
	}
	
	//METODO AUXILIAR
	private int partition(int[] array, int esquerda, int direita) {
		int posicaoPivot = direita;
		int i = esquerda + 1;
		int pivo = array[esquerda];

		while(i <= posicaoPivot) {
			if (array[i] <= pivo) {
				i++;
			}else if(array[posicaoPivot] > pivo) {
				posicaoPivot --;
			}else{
				Util.swap(array, i, posicaoPivot);
			}
		}
		Util.swap(array, esquerda, posicaoPivot);
		
		return posicaoPivot;
	}

	@Override
	public void quicksort(int[] array, int left, int right) {
		
		if(left < right) {
			int r = partition(array, left, right);
			quicksort(array, left, (r - 1));
			quicksort(array, (r + 1), right);
		}else{
			return;
		}
	}
	
	//METODO AUXILIAR
	private int[] merge(int[] array1, int[] array2) {

			
		
		
		int[] result = null;
		
		return result;
	}

}
