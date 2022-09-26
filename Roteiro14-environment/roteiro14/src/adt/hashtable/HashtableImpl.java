package adt.hashtable;

/**
 * The structure has an initial size and a max size.  
 * When the current capacity (size) reaches 75% of the total capacity, 
 * the structure must increase its size (until max size) and perform rehash of all elements. 
 * If the current size of the structure is max size it cannot increase anymore.
 */
public class HashtableImpl<K extends Comparable<? super K>, V> implements Hashtable<K, V> {

	public static final int INITIAL_SIZE = 10;
	public static final int MAX_SIZE = 1000;
	protected HashFunction<K> hashFunction;
	protected V[] table;
	// an array keeping all inserted keys. It must exist in order to rehash all values according to their keys
	protected K[] insertedKeys;
	protected int elements;

	public HashtableImpl(HashFunction<K> hashFunction) {
		table = (V[])new Object[INITIAL_SIZE];
		insertedKeys = (K[]) new Object[INITIAL_SIZE];
		elements = 0;
		this.hashFunction = hashFunction;
	}

	@Override
	public boolean isEmpty() {
		return elements == 0;
	}

	@Override
	public boolean isFull() {
		return elements == MAX_SIZE;
	}

	@Override
	public int size() {
		return elements;
	}

	/**
	 * The key must be kept into inserted keys and the value goes to the position calculated 
	 * by the hash function.
	 */
	@Override
	public void insert(K key, V value){
		if(!this.isFull()){
			int indiceHash = hashFunction.hash(key);
			//ENQUANTO NAO ACHAR UM INDICE QUE ESTEJA NULL NA TABELA
			while(!(table[indiceHash] == null)){
				System.out.println("INDICE " + indiceHash);
				indiceHash = hashFunction.hash(key);
			}
			table[indiceHash] = value;
			insertedKeys[indiceHash] = key;
			elements ++;
		}
		//SE TIVER PELO MENOS 75% DOS ESPACOS ALOCADOS, FAZ UM REHASH
		if(verificaCapacidade()){
			this.rehash();
		}
	}

	@Override
	public void remove(K key) {
		if(!this.isEmpty()){
			int indiceHash = hashFunction.hash(key);
			int contador = 0;
			while(contador <= capacity()){
				if(insertedKeys[indiceHash].equals(key)){
					table[indiceHash] = null;
					insertedKeys[indiceHash] = null;
					elements --;
				}
				indiceHash = hashFunction.hash(key);
				contador ++;
			}
		}
	}

	@Override
	public V search(K key) {
		if(!this.isEmpty()){
			int indiceHash = hashFunction.hash(key);
			int contador = 0;
			while(contador <= capacity()){
				if(insertedKeys[indiceHash].equals(key)){
					return table[indiceHash];
				}
				indiceHash = hashFunction.hash(key);
				contador ++;
			}
		}
		return null;
	} 

	/**
	 * This method cannot check if the given key is in insertedKeys.
	 */
	@Override
	public boolean containsKey(K key) {
		int indiceHash = hashFunction.hash(key);
		if(insertedKeys[indiceHash].equals(key)){
			return true;
		}
		return false;
	}
	
	@Override
	public int capacity(){
		return table.length;
	}
	
	private boolean verificaCapacidade(){
		return false;
	}
	
	/**
	 * Increases the table to a prime number greater than the double of the current table.length
	 * and rehashes all inserted elements. the keys to be rehashed are taken from insertedKeys and the values
	 * are get from table (indices are obtained by the hash function).
	 */
	protected void rehash(){
		
	}
}
