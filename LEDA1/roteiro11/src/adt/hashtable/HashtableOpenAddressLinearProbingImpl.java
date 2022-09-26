package adt.hashtable;

public class HashtableOpenAddressLinearProbingImpl<T> extends
		AbstractHashtable<T, Object> {

	private DELETED deleted = new DELETED();

	// DO NOT DELET THIS CONSTRUCTOR. ADJUST IT.
	@SuppressWarnings("unchecked")
	public HashtableOpenAddressLinearProbingImpl(int size,
			HashFunctionClosedAddressMethod method) {
		super(size);
		table = (T[]) new Object[size];
		hashFunction = new HashFunctionLinearProbingImpl<T>(this, method);
		
		// TODO Adjust your constructor here
		// The length of the internal table must be given size
		// the hash function must be an implementation of linear probing.
	}

	@SuppressWarnings("unchecked")
	@Override
	public void insert(T element) {
		if (element != null) {
			boolean flag = false;
			if (!isFull()) {
				for (int i = 0; i < capacity() && flag == false; i++) {
					int indice = ((HashFunctionLinearProbingImpl<T>) hashFunction)
							.hash(element, i);
					if (table[indice] == null || deleted.equals(table[indice])) {
						table[indice] = element;
						elements++;
					} else {
						COLLISIONS++;
					}
				}
			}
		}
		throw new HashtableOverflowException();
	}

	@Override
	public void remove(T element) {
		if (element != null) {
			int i = indexOf(element);
			if (i != -1) {
				table[i] = new DELETED();
				elements--;
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T search(T element) {
		T result = null;
		if (element != null) {
			int indice = indexOf(element);
			if (indice != -1) {
				result = (T) table[indice];
			}
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public int indexOf(T element) {
		int index = -1;
		int indice = ((HashFunctionLinearProbingImpl<T>) hashFunction).hash(
				element, 0);
		for (int i = 1; i < capacity() && table[indice] != null; i++) {
			indice = ((HashFunctionLinearProbingImpl<T>) hashFunction).hash(
					element, i);
			if (table[indice].equals(element)) {
				index = indice;
			}
		}
		return index;
	}

}