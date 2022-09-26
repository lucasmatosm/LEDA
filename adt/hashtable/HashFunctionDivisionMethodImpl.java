package adt.hashtable;

public class HashFunctionDivisionMethodImpl<T> implements
		HashFunctionClosedAddress {

	protected Hashtable<T> hashtable;

	public HashFunctionDivisionMethodImpl(Hashtable<T> hashtable) {
		this.hashtable = hashtable;
	}
	
	/**
	 * The hash function might use the length of the hashtable. This can be captured through the method
	 * capacity of the hashtable.
	 */
	@Override
	public int hash(Object element) {
		return element.hashCode() % hashtable.capacity();
	}

}
