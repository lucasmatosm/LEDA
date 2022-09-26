

public class QuickSort {

	public static void quickSort(int[] array, int esquerda, int direita){
		if(esquerda >= direita){
			return; // sai do metodo
		}
		int m = partition(array, esquerda, direita);
		quickSort(array, esquerda, m - 1);
		quickSort(array, m + 1, direita);
	}
	
	private static int partition(int[] array, int esquerda, int direita) {
		int posicaoPivot = direita;
		int pivo = array[esquerda];
		int i = esquerda + 1;

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
	
	public static void main(String args[]){
		int[] vetor = {10, 5, 3, 6, 5, 6, 1};
		quickSort(vetor,0, 6);
		for(int i : vetor){
			System.out.println(i);
		}
	}
}
