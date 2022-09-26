package adt.heap.flexible;

import java.util.Comparator;

import adt.heap.GenericHeap;

/**
 * Classe que é um comparator de dois objetos (comparaveis) a ser usado numa min heap.
 * Retorna a ordem invertida da comparacao dos objetos. Isso para que o menor seja considerado
 * maior e assim fique no topo da  heap.
 */
class ComparatorMinHeap<T extends Comparable<? super T>> implements Comparator<T>{
	public int compare(T o1, T o2){
		return - o1.compareTo(o2);
	}
}

/**
 * Classe que é um comparator de dois objetos (comparaveis) a ser usado numa max heap.
 * Retorna a ordem normal da comparacao dos objetos.  
 */
class ComparatorMaxHeap<T extends Comparable<? super T>> implements Comparator<T>{
	public int compare(T o1, T o2){
		return o1.compareTo(o2);
	}
}

/**
 * The flexible heap can work as min-heap (default) or max-heap. This behaviour can be changed 
 * dinamically by exchanging the type of the heap. 
 */
public interface FlexibleHeap<T extends Comparable<? super T>> extends GenericHeap<T> {
	
	/**
	 * Removes and returns the top (root) element of the heap. The method returns null 
	 * if the heap is empty.
	 */
	public T extractHead();
	
	/**
	 * Returns the top (root) element without removing it. It returns null if the heap is empty.
	 */
	public T head();
	
	
	/**
	 * Changes the behaviour of the heap. If it is working as min-heap, it becomes 
	 * a max-heap (and vice-versa). When the heap changes this behaviour its elements
	 * must be re-ordered.
	 */
	public void changeHeapType();
	
}




