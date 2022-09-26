package adt.bt.arithmeticExpression;

import org.junit.Test;

public class TestBTExpressionImpl {

	@Test
	public void test() {
		ArithmeticOperation op1 = new ArithmeticOperation("+", new Digit(2),
				new Digit(5), null);
		ArithmeticOperation op2 = new ArithmeticOperation("*", op1,
				new Digit(7), null);
		op1.setParent(op2);
		ArithmeticOperation op3 = new ArithmeticOperation("/", new Digit(4),
				new Digit(2), null);
		ArithmeticOperation op4 = new ArithmeticOperation("-", op3,
				new Digit(1), null);
		op3.setParent(op4);
		ArithmeticOperation op5 = new ArithmeticOperation("%", op2, op4, null);
		op2.setParent(op5);
		op4.setParent(op5);
		System.out.println(op5.evaluate());
	}

}
