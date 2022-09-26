package adt.hashtable;

public class HashtableOpenAddressQuadraticProbingImpl<T> extends
		AbstractHashtable<T, Object> {

	public HashtableOpenAddressQuadraticProbingImpl(int size, HashFunctionClosedAddressMethod method, int c1, int c2) {
		super(size);
		hashFunction = new HashFunctionQuadraticProbingImpl<T>(this, method, c1, c2);
		table = new Object[size];
	}

	@Override
	public void insert(T element) {
		boolean flag = false;
		if (!isFull()) {
			for(int i = 0; i < capacity() && flag == false; i++) {
				@SuppressWarnings("unchecked")
				int indice = ((HashFunctionQuadraticProbingImpl<T>) hashFunction).hash(element, i);
				if (table[indice] == null || table[indice].getClass() == DELETED.class) {
					table[indice] = element;
					elements++;
				}
				else {
					COLLISIONS++;
				}
			}
		}
	}

	@Override
	public void remove(T element) {
		int indice = indexOf(element);
		if (indice != -1) {
			table[indice] = new DELETED();
			elements--;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T search(T element) {
		T result = null;
		int indice = indexOf(element);
		if (indice != -1) {
			result = (T) table[indice];
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public int indexOf(T element) {
		int index = -1;
		int indice = ((HashFunctionQuadraticProbingImpl<T>) hashFunction).hash(element, 0);
		for(int i = 1; i < capacity() && table[indice] != null; i++) {
			indice = ((HashFunctionQuadraticProbingImpl<T>) hashFunction).hash(element, i);
			if(table[indice].equals(element)) {
				index = indice;
			}
		}
		return index;
	}

}
