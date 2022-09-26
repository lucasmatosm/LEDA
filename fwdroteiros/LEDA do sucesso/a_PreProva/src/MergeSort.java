import java.util.Arrays;

public class MergeSort {
	public static void mergeSort(int[] array){
		if(array.length > 1){
			int[] inicio = Arrays.copyOfRange(array, 0, array.length / 2);
			int[] fim = Arrays.copyOfRange(array, array.length / 2, array.length);
			
			mergeSort(inicio);
			mergeSort(fim);
			
			int[] auxiliar = merge(inicio, fim);
			
			for(int i = 0; i < array.length; i ++){
				array[i] = auxiliar[i];
			}
		}
	}
	private static int[] merge(int[] inicio, int[] fim) {
		int[] resultado = new int[inicio.length + fim.length];
		int indiceInicio = 0;
		int indiceFim = 0;
		int indiceResultado = 0;
		
		while(indiceInicio < inicio.length && indiceFim < fim.length){
			if(inicio[indiceInicio] < fim[indiceFim]){
				resultado[indiceResultado] = inicio[indiceInicio];
				indiceInicio ++;
			}else{
				resultado[indiceResultado] = fim[indiceFim];
				indiceFim ++;
			}
			indiceResultado ++;
		}
		
		if(indiceInicio < inicio.length){
			for(int i = indiceResultado; i < resultado.length; i ++){
				resultado[i] = inicio[indiceInicio];
				indiceInicio ++;
			}
		}
		
		if(indiceFim < fim.length){
			for(int j = indiceResultado; j < resultado.length; j ++){
				resultado[j] = fim[indiceFim];
				indiceFim ++;
			}
		}
		return resultado;
	}
	public static void main(String args[]){
		int[] array = {5, 1, 3, 3, 6, 1, 2};
		mergeSort(array);
		for(int i : array){
			System.out.println(i);
		}
	}
}
