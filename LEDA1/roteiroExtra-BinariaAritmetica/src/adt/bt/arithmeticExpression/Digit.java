package adt.bt.arithmeticExpression;

public class Digit extends ArithmeticExpression {

	private int digit;
	
	public Digit(int digit) {
		super();
		this.digit = digit;
	}
	
	@Override
	public Integer evaluate() {
		return this.digit;
	}

}
