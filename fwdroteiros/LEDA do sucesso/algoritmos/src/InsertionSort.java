
public class InsertionSort {
	public static void insertionSort(int[] vetor){
		for(int i = 0; i < vetor.length; i ++){
			int chave = vetor[i];
			int j = i;
			while(j > 0 && vetor[j - 1] > chave){
				vetor[j] = vetor[j - 1];
				j --;
				}
			vetor[j] = chave;
		}
	}
	
	public static void main(String args[]){
		int[] vetor = {9, 12, 2, 3, 8, 0, 2};
		insertionSort(vetor);
		for(int i = 0; i < vetor.length; i++){
			System.out.println(vetor[i]);
		}
	}
}
