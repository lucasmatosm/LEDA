package adt.avltree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;

public class AVLTreeImpl<K extends Comparable<? super K>, V> extends
		BSTImpl<K, V> implements AVLTree<K, V> {

	// AUXILIARY
	protected void rebalance(BSTNode<K, V> node) {
		int balance = calculateBalance(node);

		if (balance < -1) {
			int childBalance = calculateBalance(node.getRight());

			if (childBalance > 0) {
				rightRotation(node.getRight());
				leftRotation(node);
			} else {
				leftRotation(node);
			}
		} else if (balance > 1) {
			int childBalance = calculateBalance(node.getLeft());

			if (childBalance < 0) {
				leftRotation(node.getLeft());
				rightRotation(node);
			} else {
				rightRotation(node);
			}
		}
	}
	
	// AUXILIARY
	protected int calculateBalance(BSTNode<K, V> node) {
		int result = 0;
		if (node != null && !node.isEmpty()) {
			result = height(node.getLeft()) - height(node.getRight());
		}
		return result;
	}
	
	// AUXILIARY
	protected void rightRotation(BSTNode<K, V> node) {
		BSTNode<K, V> pivot = new BSTNode<K, V>();
		if (!node.isEmpty() && node != null && node.getLeft().getLeft() != null) {

			pivot.setKey(node.getLeft().getKey());
			pivot.setValue(node.getLeft().getValue());
			pivot.setLeft(node.getLeft().getLeft());
			pivot.getLeft().setParent(pivot);
			pivot.setRight(node.getLeft().getRight());
			pivot.getRight().setParent(pivot);

			node.getLeft().setKey(pivot.getRight().getKey());
			node.getLeft().setValue(pivot.getRight().getValue());
			node.getLeft().setLeft(pivot.getRight().getLeft());
			node.getLeft().setRight(pivot.getRight().getRight());
			node.getLeft().setParent(node);

			pivot.getRight().setKey(node.getKey());
			pivot.getRight().setValue(node.getValue());
			pivot.getRight().setLeft(node.getLeft());
			pivot.getRight().getLeft().setParent(pivot.getRight());
			pivot.getRight().setRight(node.getRight());
			pivot.getRight().getRight().setParent(pivot.getRight());
			pivot.getRight().setParent(pivot);

			node.setKey(pivot.getKey());
			node.setValue(pivot.getValue());
			node.setLeft(pivot.getLeft());
			node.getLeft().setParent(node);
			node.setRight(pivot.getRight());
			node.getRight().setParent(node);
			node.setParent(node.getParent());

		}
	}

	// AUXILIARY
	protected void leftRotation(BSTNode<K, V> node) {
		BSTNode<K, V> pivot = new BSTNode<K, V>();
		if (!node.isEmpty() && node != null
				&& node.getRight().getRight() != null) {

			pivot.setKey(node.getRight().getKey());
			pivot.setValue(node.getRight().getValue());
			pivot.setLeft(node.getRight().getLeft());
			pivot.getLeft().setParent(pivot);
			pivot.setRight(node.getRight().getRight());
			pivot.getRight().setParent(pivot);
			
			

			node.getRight().setKey(pivot.getLeft().getKey());
			node.getRight().setValue(pivot.getLeft().getValue());
			node.getRight().setLeft(pivot.getLeft().getLeft());
			node.getRight().setRight(pivot.getLeft().getRight());
			node.getRight().setParent(node);

			pivot.getLeft().setKey(node.getKey());
			pivot.getLeft().setValue(node.getValue());
			pivot.getLeft().setLeft(node.getLeft());
			pivot.getLeft().getLeft().setParent(pivot.getLeft());
			pivot.getLeft().setRight(node.getRight());
			pivot.getLeft().getRight().setParent(pivot.getLeft());
			pivot.getLeft().setParent(pivot);

			node.setKey(pivot.getKey());
			node.setValue(pivot.getValue());
			node.setLeft(pivot.getLeft());
			node.getLeft().setParent(node);
			node.setRight(pivot.getRight());
			node.getRight().setParent(node);
			node.setParent(node.getParent());
		}
	}
}
