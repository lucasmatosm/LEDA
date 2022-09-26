
public class GnomeSort {
	public static void gnomeSort(int[] array){
		int posicao = 1;
		while(posicao < array.length){
			if(array[posicao] >= array[posicao - 1]){
				posicao ++;
			}else{
				Util.swap(array, posicao, posicao - 1);
				if(posicao > 1){
					posicao --;
				}else{
					posicao ++;
				}
			}
		}
	}
	
	public static void main(String args[]){
		int[] array = {4, 1, 5, 1, 3, 8, 9, 8};
		gnomeSort(array);
		for(int i : array){
			System.out.println(i);
		}
	}
}
