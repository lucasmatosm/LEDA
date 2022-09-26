package sorting.cocktailSort;

public class CocktailSortImpl implements CocktailSort {

	@Override
	public void cocktailSort(int[] array) {
		int inicio = 0;
		int fim = array.length - 1;
		int troca = 0;
		
		while(troca == 0 && inicio < fim){
			troca ++;
			for(int i = inicio; i < fim; i ++){ // FOR VARRENDO DA DIREITA PRA ESQUERDA
				if(array[i] > array[i + 1]){
					Util.swap(array, i, i+1);
					troca = 0;
				}
			}
			fim --;
			
			for(int j = fim; j > inicio; j --){ //FOR VARRENDO DA ESQUERDA PRA DIREITA
				if(array[j] < array[j - 1]){
					Util.swap(array, j, j-1);
					troca = 0;
				}
			}
			inicio ++;
		}

	}
}
