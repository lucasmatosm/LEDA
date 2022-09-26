package adt.bt.arithmeticExpression;

public class ArithmeticOperation extends ArithmeticExpression {

	public ArithmeticOperation(String data, ArithmeticExpression left, 
			ArithmeticExpression right, ArithmeticExpression parent) {
		super(data,left,right,parent);
	}
	
	@Override 
	public Integer evaluate() {
		switch (this.data) {
		case "+":
			return ((ArithmeticExpression)this.left).evaluate() + 
					((ArithmeticExpression)this.right).evaluate();
		case "-":
			return ((ArithmeticExpression)this.left).evaluate() - 
					((ArithmeticExpression)this.right).evaluate();
		case "*":
			return ((ArithmeticExpression)this.left).evaluate() * 
					((ArithmeticExpression)this.right).evaluate();
		case "/":
			return ((ArithmeticExpression)this.left).evaluate() / 
					((ArithmeticExpression)this.right).evaluate();
		case "%":
			return ((ArithmeticExpression)this.left).evaluate() %
					((ArithmeticExpression)this.right).evaluate();
		}
		return 0;
	}

}
