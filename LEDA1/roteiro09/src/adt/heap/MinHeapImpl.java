package adt.heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MinHeapImpl<T extends Comparable<T>> implements MinHeap<T> {

	private static final int INITIAL_SIZE = 20;
	private static final int INCREASING_FACTOR = 10;

	@SuppressWarnings("unchecked")
	private T[] heap = (T[]) new Comparable[INITIAL_SIZE];
	private int size = 0;

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public void insert(T element) {

		// Se o heap estiver cheio, aumenta o tamanho de acordo com o fator de
		// aumento
		if (size >= heap.length)
			heap = Arrays.copyOf(heap, heap.length + INCREASING_FACTOR);

		// Caso o heap esteja vazio define o elemento como raiz
		else if (isEmpty()) {
			heap[0] = element;
			size++;
		}

		// Caso o heap nao esteja cheio
		else {

			// Assume "i" igual a quantidade de elementos
			int i = size;

			while (i > 0 && heap[parent(i)].compareTo(element) > 0) {
				heap[i] = heap[parent(i)];
				i = parent(i);
			}

			heap[i] = element;
			size++;
		}
	}

	protected int parent(int i) {
		return (i - 1) / 2;
	}

	protected int left(int i) {
		return (2 * i) + 1;
	}

	protected int right(int i) {
		return (2 * i) + 2;
	}

	@Override
	public T extractRootElement() {

		// Caso a arvore seja vazia
		if (isEmpty())
			return null;

		// Pega o primeiro elemento da arvore
		T min = rootElement();

		// Faz a troca com o ultimo
		Util.swap(heap, 0, size - 1);

		// Decrementa o tamanho da arvore
		size--;

		// Reorganiza a arvora a partir da raiz
		heapify(0);

		// Retorna o minimo
		return min;
	}

	@Override
	public T rootElement() {

		// Caso a arvore nao seja vazia
		if (!isEmpty())
			return heap[0];

		else
			return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T[] heapsort(T[] array) {

		// Cria um ArrayList para guardar o resultado
		List<T> result = new ArrayList<T>();

		// Cria um heap
		buildHeap(array);

		// Enquanto o heap nao estiver vazio ele vai extraindo os elementos pela
		// raiz
		while (!isEmpty())
			result.add(extractRootElement());

		// Retorna o resultado em array
		return (T[]) result.toArray(new Comparable[0]);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void buildHeap(T[] array) {

		size = array.length;

		// Cria um heap com base no tamanho do array
		heap = (T[]) new Comparable[array.length];

		// Adiciona todos os elementos do array no heap
		for (int i = 0; i < array.length; i++)
			heap[i] = array[i];

		// Executa o heapify nos nós de baixo para cima ate chegar no nó
		for (int i = ((array.length - 1) / 2); i >= 0; i--)
			heapify(i);

	}

	private void heapify(int position) {

		// Pega o indice do filho a esquerda
		int left = (2 * position) + 1;

		// Pega o indice do filho a direita
		int right = (2 * position) + 2;

		// Armazena o indice cujo o valor for maior
		int largest;

		largest = position;

		if ((left <= size - 1)) {

			// Compara o o valor da esquerda se é maior que o elemento do indice
			if (heap[left].compareTo(heap[position]) < 0)
				largest = left;

			else
				largest = position;

		}

		if ((right <= size - 1)) {

			// Agora compara o valor do resultado anterior com o da direita
			if (heap[right].compareTo(heap[largest]) < 0)
				largest = right;

		}

		// Se o novo indice encontrado for diferente do indice informado eh por
		// que ainda nao terminou a organizacao
		if (largest != position) {

			// Faz a troca de valores
			Util.swap(heap, position, largest);

			// Chamada recursiva até terminar de varrer a arvore
			heapify(largest);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T[] toArray() {

		// Cria um array para retornar como resultado
		T[] result = (T[]) new Comparable[size];

		// Copia todos os elementos do heap para o array
		for (int i = 0; i < result.length; i++)
			result[i] = heap[i];

		// Retorna o array resultado
		return result;
	}

}
