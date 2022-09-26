package adt.hashtable;

public class HashtableOpenAddressLinearProbingImpl<T> extends
		AbstractHashtable<T, Object> {

	// DO NOT DELET THIS CONSTRUCTOR. ADJUST IT.
	public HashtableOpenAddressLinearProbingImpl(int size,HashFunctionClosedAddressMethod method) {
		super(size);
		//TODO Adjust your constructor here
		// The length of the internal table must be given size
		// the hash function must be an implementation of linear probing. 
	}
	
	@Override
	public void insert(T element) {
		// TODO Auto-generated method stub

	}

	@Override
	public void remove(T element) {
		// TODO Auto-generated method stub

	}

	@Override
	public T search(T element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int indexOf(T element) {
		// TODO Auto-generated method stub
		return 0;
	}

}
