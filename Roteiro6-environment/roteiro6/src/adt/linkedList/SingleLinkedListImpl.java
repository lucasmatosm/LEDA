package adt.linkedList;

import java.util.Arrays;

public class SingleLinkedListImpl<T> implements LinkedList<T> {
	
	//Head
	protected SingleLinkedListNode<T> head;
	
	//Contrutor de SingleLinkedListNode
	public SingleLinkedListImpl(){
		head = new SingleLinkedListNode<T>();
	}
	
	@Override
	public boolean isEmpty() {
		return head.isNIL();
	}

	@Override
	public int size() {
		int tamanho = 0;
		SingleLinkedListNode<T> aux = head;
		while(!(head.isNIL())){
			tamanho ++;
			aux = aux.next;	
		}
		return tamanho;
	}

	@Override
	public T search(T element) {
		SingleLinkedListNode<T> x = head;
		while(!(x.next.isNIL() && x.data == element )){
			x = x.next;
		}
		if(x == element){
			return x.data;
		}
		return null;
	}

	@Override
	public void insert(T element) {
		SingleLinkedListNode<T> auxiliar = head;
		while(!(auxiliar.next.isNIL())){ //VERIFICA A ULTIMA POSICAO ANTES DO NIL
			auxiliar.data = element;
		}
		auxiliar.data = element;
		auxiliar.next = new SingleLinkedListNode<T>();
	}

	@Override
	public void remove(T element) {
		if(head.data == element){
			head = head.next;
		}
		else{
			SingleLinkedListNode<T> aux = head;
			SingleLinkedListNode<T> previous = null;
			while(aux.isNIL() == false && aux.data != element){
				previous = aux;
				aux = aux.next;
			}
			if(aux.isNIL() == false){
				previous.next = aux.next;
			}
			aux = aux.next;
		}
	}
	@Override
	public T[] toArray(){
		T[] array;
		array = (T[]) new Object[size()];
		SingleLinkedListNode<T> aux = head;
		int contador = 0;
		while(aux.isNIL() == false){
			array[contador] = head.data;
			aux = aux.next;
			contador ++;
		}
		return array;
	}
	
	public static void main(String args[]){
		SingleLinkedListImpl s = new SingleLinkedListImpl();
		System.out.println(Arrays.toString(s.toArray()));
		
	}

}
