package adt.hashtable;

import java.util.LinkedList;

public class HashtableClosedAddressImpl<T> extends AbstractHashtable<T, LinkedList<T>> {

	@SuppressWarnings("unchecked")
	public HashtableClosedAddressImpl(int size, HashFunctionClosedAddressMethod method) {
		super(size);
		super.table = (LinkedList<T>[]) new LinkedList[getPrimeAbove(size)];
		super.hashFunction = new HashFunctionDivisionMethodImpl<T>(this);
	}

	/**
	 * It returns the prime number that is closest (and greater) to the given number.  
	 */
	int getPrimeAbove(int number){
		int result = number;
		while(!Util.isPrime(result)){
			result = result + 1;
		}
		return result;
	}
	
	@Override
	public void insert(T element) {
		if(search(element) == null) {
			@SuppressWarnings("unchecked")
			int indice = ((HashFunctionDivisionMethodImpl<T>) hashFunction).hash(element);
			if(table[indice] == null) {
				table[indice] = new LinkedList<T>();
			}
			else {
				COLLISIONS++;
			}
			table[indice].add(element);
			elements++;
		}
	}

	@Override
	public void remove(T element) {
		if(search(element) != null) {
			@SuppressWarnings("unchecked")
			int indice = ((HashFunctionDivisionMethodImpl<T>) hashFunction).hash(element);
			table[indice].remove(element);
			elements--;
		}
	}

	@Override
	public T search(T element) {
		T result = null;
		@SuppressWarnings("unchecked")
		int indice = ((HashFunctionDivisionMethodImpl<T>) hashFunction).hash(element);
		if(table[indice] != null && table[indice].indexOf(element) != -1) {
			result = table[indice].get(table[indice].indexOf(element));
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public int indexOf(T element) {
		int result = -1;
		if (search(element) != null) {
			result = ((HashFunctionDivisionMethodImpl<T>) hashFunction).hash(element);
		}
		return result;
	}

}