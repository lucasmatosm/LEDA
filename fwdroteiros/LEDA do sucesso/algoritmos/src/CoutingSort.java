
public class CoutingSort {

	public static int[] coutingSort(int[] arrayA, int k){
		int[] arrayC = new int[k];
		int[] arrayB = new int[arrayA.length];
		
		for(int i = 0; i < arrayC.length; i++){ //PREENCHE ARRAY COM 0
			arrayC[i] = 0;
		}
		
		for(int i = 0; i < arrayA.length; i++){ //INCREMENTO
			arrayC[(arrayA[i] - 1)] ++;
		}
		
		for(int i = 1; i < arrayC.length; i ++){ //SOMA DOS PREFIXOS
			arrayC[i] += arrayC[i -1];
		}
		
		for(int i = arrayA.length; i >= 1; i--){ //ORDENACAO
			int indiceA = arrayA[i - 1]; //PEGA O VALOR DA VEZ DO ARRAY A
			int indiceC = arrayC[indiceA - 1] -= 1; //DECREMENTA -1 DO ARRAY C E GUARDA O VALOR DECREMENTADO
			arrayB[indiceC] = indiceA; 
		}
		return arrayB;
	}
	
	public static void main(String args[]){
		int[] arrayA = {3, 1, 4, 1};
		int[] result = coutingSort(arrayA, 10);
		
		for(int i = 0; i < result.length; i++){ 
			System.out.println(result[i]);
		}
	}
}
