
public class SelectionSort {
	
	public static void swap(int[] array,int i, int j){
		  int temp = array[i];
		  array[i] = array[j];
		  array[j] = temp;
	}
	
	
	public static void selectionSort(int[] array){
		for(int i = 0; i < array.length - 1; i++){
			int menor = i;
			for(int j = i + 1; j < array.length; j ++){
				if(array[j] < array[menor]){
					menor = j;
				}
			}
			swap(array, i, menor);
		}
	}
	public static void main(String args[]){
		int[] array = {4,2,1,5,7,2};
		selectionSort(array);
		for(int i : array){
			System.out.println(i);
		}
	}
}
