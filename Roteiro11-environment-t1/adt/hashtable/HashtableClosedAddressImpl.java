package adt.hashtable;

import java.util.LinkedList;

public class HashtableClosedAddressImpl<T> extends AbstractHashtable<T, LinkedList<T>> {

	// DO NOT DELETE THIS CONSTRUCTOR. ADJUST IT.
	public HashtableClosedAddressImpl(int size, HashFunctionClosedAddressMethod method) {
		super(size);
		//TODO Adjust your constructor here
		// The length of the internal table must be the immediate prime number greater than 
		// the given size. For example, if size=10 then the length must be 11. If size=20, the length 
		// must be 23. You may use the method getPrimeAbove(int size) but you must implement it.
	}
	
	//AUXILIARY
	/**
	 * It returns the prime number that is closest (and greater) to the given number.  
	 */
	int getPrimeAbove(int number){
		int result = number;
		while(!Util.isPrime(result)){
			result = result + 1;
		}
		return result;
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
