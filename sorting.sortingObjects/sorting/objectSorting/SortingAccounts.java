package sorting.objectSorting;

import java.util.Comparator;


public class SortingAccounts implements SortingObjects<Account> {
	

	/**
	 * Sorts the the array of accounts by number in the simplest way, using one of the above strategies.
	 * You must consider that the accounts will be ordered by number. You must get this number directly from 
	 * each account using getNumber().
	 */
	@Override
	public void sort(Account[] array, Strategy strategy){
		//TODO implement this method
	}
	
	/**
	 * Sorts the the array of accounts using a given comparator and one of the above strategies.
	 * You must use an external Comparator to determine the order between two accounts. 
	 * You have to implement this comparator in this file (externally to the class SortingAccounts). The class 
	 * cannot be public.    
	 */
	@Override
	public void sort(Account[] array, Comparator<Account> comparator,Strategy strategy){
		//TODO implement this method
	}
	

	/**
	 * Sorts the the array of accounts assuming that each stored element is Comparable.
	 * Note that Account already implements Comparable. You must consider this 
	 * to know if an account is less or greater than another account.
	 */
	@Override
	public void sortComparables(Account[] array, Strategy strategy){
		//TODO implement this method
	}
	
}
