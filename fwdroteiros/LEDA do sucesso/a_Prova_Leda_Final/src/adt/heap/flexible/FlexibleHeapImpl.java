package adt.heap.flexible;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;

import adt.heap.Util;
//adt.heap.flexible.FlexibleHeapFullImpl.Type;

/**
 * O comportamento de qualquer heap é definido pelo heapify. Neste caso o heapify dessa heap
 * deve comparar os elementos e colocar o maior sempre no topo. Essa comparação não é feita 
 * diretamente com os elementos armazenados,
 * mas sim usando um comparator que pode modificar. Dessa forma, dependendo do comparator, 
 * a heap pode funcionar como uma max-heap (se usa um ComparatorMaxHeap) ou min-heap (se usa
 * um ComparatorMinHeap).  
 */
public class FlexibleHeapImpl<T extends Comparable<? super T>> implements
		FlexibleHeap<T> {

	/**
	 * Declara o tipo da heap como tipo enumerado. 
	 */
	enum Type {MIN,MAX};
	
	protected T[] heap;
	protected int index = 0;
	protected Type type;
	/**
	 * O comparador é utilizado para fazer as comparações da heap. O ideal é mudar apenas o comparator
	 * e mandar reordenar a heap usando esse comparator. Assim os metodos da heap não precisam saber 
	 * se vai funcionar como max-heap ou min-heap.
	 */
	protected Comparator<T> comparator;
	
	private static final int INITIAL_SIZE = 20;
	private static final int INCREASING_FACTOR = 10;
	
	/**
	 * Construtor da classe. Note que de inicio a heap funciona como uma min-heap.
	 */
	public FlexibleHeapImpl() {
		this.heap = (T[]) (new Comparable[INITIAL_SIZE]);
		type = Type.MIN;
		this.comparator = new ComparatorMinHeap<T>();
	}

	///////////////////// METODOS IMPLEMENTADOS
	private int parent(int i){
		return (i-1)/2;
	}
	
	/**
	 * Deve retornar o indice que representa o filho a esquerda do elemento indexado pela posicao i no vetor
	 */
	private int left(int i){
		return (i*2 + 1);
	}

	/**
	 * Deve retornar o indice que representa o filho a direita do elemento indexado pela posicao i no vetor
	 */
	private int right(int i){
		return (i*2 + 1) + 1;
	}
	
	@Override
	public boolean isEmpty() {
		return (index == -1);
	}
	@Override
	public T[] heapsort(T[] array) {
		LinkedList<T> result = new LinkedList<T>();

		if(array.length > 0){
			buildHeap(array);
			while(!isEmpty()){
				if(this.type == Type.MIN){
					result.add(extractHead());
				}else{
					result.addFirst(extractHead());
				}
			}
		}
		return result.toArray((T[])new Comparable[0]);
	}
	
	@Override
	public T[] toArray() {
		T[] resp = (T[])new Comparable[index+1];
		for (int i = 0; i <= index; i++) {
			resp[i] = this.heap[i];
		}
		return resp;
	}
	
	/////////////// METODOS A IMPLEMENTAR
	/**
	 * Valida o invariante de uma heap a partir de determinada posicao, que pode ser
	 * a raiz da heap ou de uma sub-heap. O heapify deve colocar os maiores (comparados
	 * usando o comparator) elementos na parte de cima da heap.
	 */
	private void heapify(int position){
		int maior = position;
		int esq = left(position);
		int dir = right(position);
		
		//VERIFICA SE O DA ESQUERDA EH MAIOR
		if((esq < this.index) && (this.comparator.compare(this.heap[esq], this.heap[position])) < 0){
			maior = esq;
		}
		//VERIFICA SE O DA DIREITA EH MAIOR
		if((dir < this.index) && (this.comparator.compare(this.heap[dir], this.heap[maior])) < 0){
			maior = dir;
		}
		if(maior != position){
			Util.swap(this.heap, position, maior);
			heapify(maior);
		}
	}
	
	@Override
	public void insert(T element) {
		// ESSE CODIGO E PARA A HEAP CRESCER SE FOR PRECISO. NAO MODIFIQUE
		if(index == heap.length - 1){
			 heap = Arrays.copyOf(heap, heap.length + INCREASING_FACTOR);
		}
		///////////////////////////////////////////////////////////////////
		int indice = this.index;
		while ((indice > 0) && (comparator.compare(this.heap[parent(indice)], element) > 0)){
			this.heap[indice] = this.heap[parent(indice)];
			indice = parent(indice);
		}
		
		this.heap[indice] = element;
		index++;		
	}
	
	/**
	 * Deixa o array Heap todo preenchido (no tamanho correto de elementos)
	 * @param size tamanho atual do array
	 */
	private void setSize(int size) {
		this.heap = (T[]) new Object[size];
	}

	@Override
	public void buildHeap(T[] array) {
		this.index = array.length;
		setSize(index); //PEGA O TAMANHO CORRETO DO ARRAY

		for(int indice = 0; indice < heap.length; indice++){
			heap[indice] = array[indice];
		}
		
		for (int posicao = ( (index - 1) / 2); posicao >= 0; posicao--) {
			heapify(posicao);
		}
	}

	@Override
	public T extractHead() {
		if(this.isEmpty()){
			return null;
		}
		T head = heap[0];
		heap[0] = heap[index]; //CABECA VAI SER O ULTIMO INSERIDO
		index --;
		heapify(0);
		return head; //CABECA ANTIGA QUE FOI RETIRADA
	}

	@Override
	public T head() {
		return heap[0]; //RETORNA A CABECA SEM REMOVE-LA
	}

	@Override
	public void changeHeapType() {
		if (this.type == Type.MIN) { //MUDA DA MIN PARA MAX
			this.type = Type.MAX;
            this.comparator = new ComparatorMaxHeap<T>();
        }else{ //MUDA DA MAX PARA MIN
        	this.type = Type.MIN;
            this.comparator = new ComparatorMinHeap<T>();
        }
		heapify(0); //RE-ESTRUTURAR A ARVORE PARA PROPRIEDADE SETADA
	}
}
