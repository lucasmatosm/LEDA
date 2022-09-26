package adt.hashtable;

/**
 * The interface of a generic hash table. It keeps values of type V associated
 * to keys of type K. The table can work in two modes: closed address (with chaining) 
 * and open address (linear probing, quadratic probe, double hashing).
 */
public interface Hashtable<K extends Comparable<? super K>, V> {
	
	public boolean isEmpty();
	
	public boolean isFull();
	
	public int capacity();
	
	/**
	 * Returns how many values have been inserted into the hash table.
	 */
	public int size();

	/**
	 * Inserts a non-null object into the hash table. 
	 */
	public void insert(K key, V value);

	/**
	 * Removes the element associated to a given key from the hash table.
	 */
	public void remove(K key) ;
	
	/**
	 * Searches a given key in the hash table. If it is not in the table, the hash table
	 * returns null.
	 */
	public V search(K key);
	
	/**
	 * Checks if the hash table contains the given key. 
	 */
	public boolean containsKey(K key);
	
}
