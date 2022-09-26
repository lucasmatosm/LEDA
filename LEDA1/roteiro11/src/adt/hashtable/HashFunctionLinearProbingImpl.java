package adt.hashtable;

/**
 * This class is an implementation of the hash function using linear probing.
 */
public class HashFunctionLinearProbingImpl<T> implements
		HashFunctionOpenAddress {

	protected Hashtable<T> hashtable;

	// The auxiliary hash function used by linear probing.
	protected HashFunctionClosedAddress originalHashFunction;

	public HashFunctionLinearProbingImpl(Hashtable<T> hashtable,
			HashFunctionClosedAddressMethod method) {
		super();
		this.hashtable = hashtable;
		originalHashFunction = (HashFunctionClosedAddress) new HashFunctionLinearProbingImpl<T>(hashtable, method);
		// Adjust your constructor to initialize the attribute
		// originalHashFunction with the correct hash function
	}

	/**
	 * The hash function might use the length of the hashtable. This can be
	 * captured through the method capacity of the hashtable.
	 */
	@Override
	public int hash(Object element, int probe) {
		if (element != null) {
			return (element.hashCode() % hashtable.capacity() + probe)
					% hashtable.capacity();
		}
		return probe;
	}

}