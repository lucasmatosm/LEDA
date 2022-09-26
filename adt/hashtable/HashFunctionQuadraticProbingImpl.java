package adt.hashtable;

public class HashFunctionQuadraticProbingImpl<T> implements
		HashFunctionOpenAddress {

	
	protected Hashtable<T> hashtable;
	protected int c1;
	protected int c2;
	
	protected HashFunctionClosedAddress originalHashFunction;
	
	public HashFunctionQuadraticProbingImpl(Hashtable<T> hashtable, 
			HashFunctionClosedAddressMethod method, //the method of the original hash function
			int c1, int c2) {
		super();
		this.hashtable = hashtable;
		this.c1 = c1;
		this.c2 = c2;
		originalHashFunction = new HashFunctionDivisionMethodImpl<T>(this.hashtable);
	}
	
	/**
	 * The hash function might use the length of the hashtable. This can be captured through the method
	 * capacity of the hashtable.
	 */
	@Override
	public int hash(Object element, int probe) {
		return ( originalHashFunction.hash(element) + c1 * probe + c2 * probe * probe ) % hashtable.capacity();
	}

}
