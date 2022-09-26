package adt.stack;

import java.util.Scanner;

/**
 * The implementation of the NPRSolver interface.
 *
 */
public class NPRSolverImpl implements NPRSolver {

	private Stack<String> stack;
	
	public NPRSolverImpl(int size){
		stack = new StackImpl<String>(size);
	}
	
	@Override
	public String evaluate(String expression) throws InvalidExpressionException {
		final String expressaoValida = "0123456789 +-*/";
		Scanner scanner = new Scanner(expression);
		int n1 = 0;
		int n2 = 0;
		Integer resultado = 0;
		
		for(int i = 0; i < expression.length(); i ++){
			//LANCA EXCECAO SE EXPRESSAO FOR INVALIDA
			if(!(expressaoValida.contains(expression.substring(i, i + 1)) )){
				throw new InvalidExpressionException("Expressao Invalida.");
			}
		}
		
		while(scanner.hasNext()){
			String elemento = scanner.next().trim();
			if(elemento.equals("+")){
				try{
					n1 = Integer.parseInt(stack.pop());
					n2 = Integer.parseInt(stack.pop());				
				}catch(StackUnderflowException e){
					System.err.println(e);
				}
				resultado = n1 + n2;
				try {
					stack.push(resultado + "");
				} catch (StackOverflowException e1) {
					e1.printStackTrace();
				}	
			}
			else if(elemento.equals("-")){
				try{
					n1 = Integer.parseInt(stack.pop());
					n2 = Integer.parseInt(stack.pop());				
				}catch(StackUnderflowException e){
					e.printStackTrace();
				}
				resultado = n2 - n1;
				try {
					stack.push(resultado + "");
				} catch (StackOverflowException e1) {
					e1.printStackTrace();
				}
			}
			else if(elemento.equals("*")){
				try{
					n1 = Integer.parseInt(stack.pop());
					n2 = Integer.parseInt(stack.pop());				
				}catch(StackUnderflowException e){
					e.printStackTrace();
				}
				resultado = n1 * n2;
				try {
					stack.push(resultado + "");
				} catch (StackOverflowException e1) {
					e1.printStackTrace();
				}
			}
			else if(elemento.equals("/")){
				try{
					n1 = Integer.parseInt(stack.pop());
					n2 = Integer.parseInt(stack.pop());				
				}catch(StackUnderflowException e){
					e.printStackTrace();
				}
				resultado = n2 / n1;
				try {
					stack.push(resultado + "");
				} catch (StackOverflowException e1) {
					e1.printStackTrace();
				}
			//SE NAO TIVER NENHUM OPERADOR, VAI EMPILHAR OS NUMEROS
			}else{
				try {
					stack.push(elemento);
				} catch (StackOverflowException e) {
					e.printStackTrace();
				}
			}
		}
		String resultadoExpressao = null; 
		
		try {
			resultadoExpressao = stack.pop();
		} catch (StackUnderflowException e) {
			e.printStackTrace();
		}
		return resultadoExpressao;
	}
}
