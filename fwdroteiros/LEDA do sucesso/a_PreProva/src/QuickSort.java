
public class QuickSort {
	public static void quickSort(int[] array, int esquerda, int direita){
		if(esquerda >= direita){
			return;
		}
		int meio = partition(array, esquerda, direita);
		quickSort(array, esquerda, meio - 1);
		quickSort(array, meio + 1, direita);
	}

	private static int partition(int[] array, int esquerda, int direita) {
		int posicaoPivo = direita;
		int pivo = array[esquerda];
		int i = esquerda + 1;
		
		
		return 0;
	}
}
