
public class SelectionSort {
	public static int[] selectionSort(int[] vetor){
		for(int i = 0; i < vetor.length - 1; i++){
			int menor = i;
			for(int j = i + 1; j < vetor.length; j++){
				if(vetor[j] < vetor[menor]){
					menor = j;
				}
			}
			Util.swap(vetor, i, menor);
		}
		return vetor;
	}
	
	public static void main(String args[]){
		int[] vetor = {8, 4, 7, 1};
		selectionSort(vetor);
		for(int i = 0; i < vetor.length; i++){
			System.out.println(vetor[i]);
		}
	}
}
