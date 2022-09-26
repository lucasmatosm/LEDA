package adt.stack;

public class main {
	public static void main(String args[]) throws InvalidExpressionException{
		NPRSolverImpl stack = new NPRSolverImpl(5);
		String expression = ("       ");
				
		System.out.println(stack.evaluate(expression));
	}
}
