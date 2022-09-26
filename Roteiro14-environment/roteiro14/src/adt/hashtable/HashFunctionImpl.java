package adt.hashtable;

public class HashFunctionImpl<K extends Comparable<? super K>> implements
		HashFunction<K> {

	protected int PROBES = 0;
	/**
	 * Implement this function using one of the required strategy: linear probing, quadratic probing,
	 * or double hashing.
	 */
	@Override
	public int hash(K key) {
		int hashCode = 0;
		//makes use of hash(key,probe)
		hashCode = hash(key, PROBES);
		return hashCode;
	}
	
	private int hash(K key, int probe){
		int hashCode = 0;
		PROBES++; //DO NOT CHANGE THIS LINE
		Integer m = HashtableImpl.INITIAL_SIZE;
		hashCode = ((Integer)key + probe) % m;
		return hashCode;
	}
}
