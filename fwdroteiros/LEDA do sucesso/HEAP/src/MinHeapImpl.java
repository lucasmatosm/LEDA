import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MinHeapImpl<T extends Comparable<? super T>> implements MinHeap<T> {

	protected static final int INITIAL_SIZE = 20;
	protected static final int INCREASING_FACTOR = 10;
	
	T[] heap = (T[]) new Comparable[INITIAL_SIZE];
	int size = 0;
	
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public void insert(T element) {
		if (size >= heap.length) {
			heap = Arrays.copyOf(heap, heap.length + INCREASING_FACTOR);
		}
		else if (isEmpty()) {
			heap[0] = element;
			size++;
			return;
		}

		int aux = size;
		while (aux > 0 && heap[parent(aux)].compareTo(element) > 0) {
			heap[aux] = heap[parent(aux)];
			aux = parent(aux);
		}
		heap[aux] = element;
		size++;
	}

	@Override
	public T extractMinimum() {
		if (size < 1){ 
			return null;
		}
		T min = minimum(); 
		Util.swap(heap, 0, size-1); 
		size--;
		minHeapify(0);
		return min;
	}

	@Override
	public T minimum() {
		if (!isEmpty()) {
			return heap[0];
		}
		return null;
	}

	@Override
	public T[] heapsort(T[] array) {
		List<T> result = new ArrayList<T>();
		buildHeap(array);
		while(!isEmpty()){
			result.add(extractMinimum());
		}
		return (T[]) result.toArray(new Comparable[0]);
	}

	@Override
	public void buildHeap(T[] array) {
		size = array.length;
		heap = (T[]) new Comparable[array.length];  
	
		for (int i = 0; i < array.length; i++) {
			heap[i] = array[i];
		}
		
		for (int i = ((array.length-1) / 2); i > 0; i--) {
			minHeapify(i);
		}
		minHeapify(0);
	}
	
	
	protected void minHeapify(int index){
		int esquerda = (2*index)+1; // Pega o indice da Esquerda
		int direita = (2*index)+2; // Pega o indice da direita
		int menor = index; // Armazena o indice cujo o valor for maior
		
		if ( (esquerda <= size-1) ) {
			
			// Compara o o valor da esquerda se e maior que o elemento do indice
			if (heap[esquerda].compareTo(heap[index]) < 0){
					menor = esquerda;															
			}else{
					menor = index;
			}
		}
		
		if ((direita <= size-1)) {
			// Compara o valor do resultado anterior com o da direita
			if (heap[direita].compareTo(heap[menor]) < 0){
				menor = direita;
			}
		}
		
		if (menor != index){
			Util.swap(heap, index, menor); 
			minHeapify(menor);
		}
	}

	@Override
	public T[] toArray() {
		T[] result = (T[]) new Comparable[size];
		for(int i = 0; i < result.length; i++){
			result[i] = heap[i];
		}
		return result;
	}
	
	protected int parent(int i) {
		return (i-1)/2;
	}

	protected int left(int i) {
		return (2*i) +1;
	}

	protected int right(int i) {
		return (2*i) + 2;
	}

}
