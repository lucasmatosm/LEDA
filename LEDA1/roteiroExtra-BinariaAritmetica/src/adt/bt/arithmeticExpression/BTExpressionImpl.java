package adt.bt.arithmeticExpression;

import java.util.ArrayList;
import java.util.List;

import adt.bt.BTNode;

/**
 * An implementation of a BTExpression. Its root contains an Arithmetic Expression.
 * Some methods do not make sense in this kind of tree.  
 */
public class BTExpressionImpl implements BTExpression {

	private static enum VisitOrder { PREORDER, ORDER, POSTORDER };
	private BTNode<String> root;
	
	public BTExpressionImpl(ArithmeticExpression expression) { 
		this.root = expression;
	}
	
	@Override
	public BTNode<String> getRoot() {
		return this.root;
	}

	@Override
	public boolean isEmpty() {
		return this.root.isEmpty();
	}

	public int max(int i1, int i2) {
		return (i1 > i2 ? i1 : i2);
	}
	
	public int height(BTNode<String> node) {
		if (node == null || node.isEmpty()) {
			return 0;
		}
		return 1 + max(height(node.getLeft()), height(node.getRight()));
	}
	
	@Override
	public int height() {
		return height(this.root);
	}

	@Override
	public BTNode<String> search(String elem) {
		throw new RuntimeException("Method does not make sense");
	}

	@Override
	public void insert(String value) {
		throw new RuntimeException("Method does not make sense");
	}

	@Override
	public void remove(String key) {
		throw new RuntimeException("Method does not make sense");	
	}

	public void visit(BTNode<String> node, List<String> list, VisitOrder order) {
		if (node == null || node.isEmpty()) {
			return;
		}
		switch (order) {
		case PREORDER:
			list.add(node.getData());
			visit(node.getLeft(), list, order);
			visit(node.getRight(), list, order);
			break;
		case ORDER:
			visit(node.getLeft(), list, order);
			list.add(node.getData());
			visit(node.getRight(), list, order);
			break;
		case POSTORDER:
			visit(node.getLeft(), list, order);
			visit(node.getRight(), list, order);
			list.add(node.getData());
		}
		
	}
	
	public String[] travel(VisitOrder order) {
		List<String> list = new ArrayList<>();
		visit(this.root, list, order);
		String[] array = new String[this.size()];
		list.toArray(array);
		return array;
	}
	
	@Override
	public String[] preOrder() {
		return travel(VisitOrder.PREORDER);
	}

	@Override
	public String[] order() {
		return travel(VisitOrder.ORDER);
	}

	@Override
	public String[] postOrder() {
		return travel(VisitOrder.POSTORDER);
	}

	public int size(BTNode<String> node) {
		if (node == null || node.isEmpty()) {
			return 0;
		}
		return 1 + size(node.getLeft()) + size(node.getRight());
	}
	
	@Override
	public int size() {
		return size(this.root);
	}

	@Override
	public Integer evaluate() {
		// TODO Auto-generated method stub
		return null;
	}

}
