
public class InsertionSort {
	public static void insertionSort(int[] array){
		for(int i = 0; i < array.length; i ++){
			int chave = array[i];
			int j = i;
			while(j > 0 && array[j - 1] > chave){
				array[j] = array[j - 1];
				j --;
			}
			array[j] = chave;
		}
	}
	public static void main(String args[]){
		int[] vetor = {1, 5, 6, 2, 1, 3, 5};
		insertionSort(vetor);
		for(int i : vetor){
			System.out.println(i);
		}
	}
}
