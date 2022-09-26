package adt.hashtable;

public interface HashFunction<K extends Comparable<? super K>> {
	public int hash(K key);
}
