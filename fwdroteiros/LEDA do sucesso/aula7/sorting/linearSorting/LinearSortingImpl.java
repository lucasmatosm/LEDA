package sorting.linearSorting;


public class LinearSortingImpl implements LinearSorting {

	/**
	 * Admita que os numeros a serem ordenados podem variar de 1 até 1.000.000.
	 */
	@Override
	public void countingSort(int[] arrayA) {

		if(arrayA.length == 0){ //SE O ARRAY FOR VAZIO
			return;
		}
		
		int numeroMaior = arrayA[0];
		int numeroMenor = arrayA[0];
		for(int i = 1; i < arrayA.length; i++){
			if(arrayA[i] > numeroMaior){
				numeroMaior = arrayA[i];
			}else if(arrayA[i] < numeroMenor){
				numeroMenor = arrayA[i];
			}
		}
		
		int intervalo = numeroMaior - numeroMenor + 1;
		int[] contador =  new int[intervalo];
		
		for(int i = 0; i < arrayA.length; i++){
			contador[arrayA[i] - numeroMenor]++;
		}
		
		int indice = 0;
		for (int i = 0; i < intervalo; i++){
			for(int j = 0; j < contador[i]; j++){
				arrayA[indice]= i + numeroMenor;
				indice++;
			}
		}
	}

}
