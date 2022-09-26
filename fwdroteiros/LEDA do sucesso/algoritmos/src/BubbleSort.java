
public class BubbleSort {
//	public static int[] bubbleSort(int[] vetor){
//		boolean houveTroca = true;
//		while(houveTroca){
//			houveTroca = false;
//			for(int i = 0; i < vetor.length -1; i++){
//				if(vetor[i] > vetor[i + 1]){
//					Util.swap(vetor, i, i + 1);
//					houveTroca = true;
//				}
//			}
//		}
//		return vetor;
//	}
	
	public static int[] bubbleSort(int[] vetor){
    for (int i = vetor.length; i >= 1; i--) {
        for (int j = 1; j < i; j++) {
            if (vetor[j - 1] > vetor[j]) {
            	Util.swap(vetor, j, j-1);
            }
        }
    }
    return vetor;
}

	
	public static void main(String args[]){
		int[] vetor = {8, 4, 7, 1};

		bubbleSort(vetor);
		for(int i = 0; i < vetor.length; i++){
			System.out.println(vetor[i]);
		}

	}
	
}


