package adt.hashtable;

public class HashtableOverflowException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public HashtableOverflowException() {
		super("Hashtable overflow!");
	}

}
