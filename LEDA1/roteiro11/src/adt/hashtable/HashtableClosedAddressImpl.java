package adt.hashtable;

import java.util.LinkedList;

public class HashtableClosedAddressImpl<T> extends
		AbstractHashtable<T, LinkedList<T>> {

	// DO NOT DELETE THIS CONSTRUCTOR. ADJUST IT.
	@SuppressWarnings("unchecked")
	public HashtableClosedAddressImpl(int size,
			HashFunctionClosedAddressMethod method) {
		super(size);
		super.table = (LinkedList<T>[]) new LinkedList[getPrimeAbove(size)];

		if (method == HashFunctionClosedAddressMethod.DIVISION) {
			super.hashFunction = new HashFunctionDivisionMethodImpl<T>(this);
		}
		
		for (int i = 0; i < size; i++) {
			table[i] = new LinkedList<T>();
		}

		// TODO Adjust your constructor here
		// The length of the internal table must be the immediate prime number
		// greater than
		// the given size. For example, if size=10 then the length must be 11.
		// If size=20, the length
		// must be 23. You may use the method getPrimeAbove(int size) but you
		// must implement it.
	}

	// AUXILIARY
	/**
	 * It returns the prime number that is closest (and greater) to the given
	 * number.
	 */
	int getPrimeAbove(int number) {
		int result = number;
		while (!Util.isPrime(result)) {
			result = result + 1;
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void insert(T element) {
		if (element != null) {
			if (search(element) == null) {
				int i = ((HashFunctionDivisionMethodImpl<T>) hashFunction)
						.hash(element);
				if (!table[i].isEmpty())
					COLLISIONS++;
				table[i].add(element);
				elements++;
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void remove(T element) {
		if (element != null) {
			if (search(element) != null) {
				int i = ((HashFunctionDivisionMethodImpl<T>) hashFunction)
						.hash(element);
				table[i].remove(element);
				elements--;
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T search(T element) {
		T result = null;
		if (element != null) {
			int i = ((HashFunctionDivisionMethodImpl<T>) hashFunction)
					.hash(element);
			if (table[i] != null && table[i].indexOf(element) != -1) {
				result = table[i].get(table[i].indexOf(element));
			}
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public int indexOf(T element) {
		int result = -1;
		if (element != null) {
			if (search(element) != null) {
				result = ((HashFunctionDivisionMethodImpl<T>) hashFunction)
						.hash(element);
			}
		}
		return result;
	}

}