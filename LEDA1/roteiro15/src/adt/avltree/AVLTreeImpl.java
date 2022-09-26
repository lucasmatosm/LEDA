package adt.avltree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;

public class AVLTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements
		AVLTree<T> {

	@Override
	public void remove(T element) {
		BSTNode<T> node = search(element);

		if (!node.isEmpty()) {

			if (node.getLeft().isEmpty() && node.getRight().isEmpty()) {
				node.setData(null);
				node.setLeft(null);
				node.setRight(null);
				rebalanceUp(node);

			} else if (node.getLeft().isEmpty() || node.getRight().isEmpty()) {
				if (node.equals(root)) {

					if (!node.getLeft().isEmpty()) {
						node.getLeft().setParent(null);
						root = (BSTNode<T>) node.getLeft();

					} else {
						node.getRight().setParent(null);
						root = (BSTNode<T>) node.getRight();
					}
				} else {

					if (!node.getLeft().isEmpty()) {

						if (node.getParent().getLeft().equals(node)) {
							node.getLeft().setParent(node.getParent());
							node.getParent().setLeft(node.getLeft());
						} else {
							node.getLeft().setParent(node.getParent());
							node.getParent().setRight(node.getLeft());
						}

					} else {

						if (node.getParent().getLeft().equals(node)) {
							node.getRight().setParent(node.getParent());
							node.getParent().setLeft(node.getRight());

						} else {
							node.getRight().setParent(node.getParent());
							node.getParent().setRight(node.getRight());
						}
					}
				}
				rebalanceUp(node);

			} else {
				BSTNode<T> sucessor = sucessor(element);
				node.setData(sucessor.getData());
				sucessor.setData(null);
				sucessor.setLeft(null);
				sucessor.setRight(null);
				rebalanceUp(sucessor);
			}
		}
	}

	private void insert(BSTNode<T> node, T element) {

		if (node.isEmpty()) {
			BSTNode<T> left = new BSTNode<T>();
			BSTNode<T> right = new BSTNode<T>();
			node.setData(element);
			node.setLeft(left);
			left.setParent(node);
			node.setRight(right);
			right.setParent(node);
			rebalanceUp(node);
		} else {
			if (element.compareTo(node.getData()) < 0)
				insert((BSTNode<T>) node.getLeft(), element);
			else
				insert((BSTNode<T>) node.getRight(), element);
		}
	}

	@Override
	public void insert(T element) {
		insert(root, element);
	}

	// AUXILIARY
	protected int calculateBalance(BSTNode<T> node) {
		if (node.isEmpty())
			return 0;
		return height((BSTNode<T>) node.getLeft())
				- height((BSTNode<T>) node.getRight());
	}

	// AUXILIARY
	protected void rebalance(BSTNode<T> node) {
		int balance = calculateBalance(node);
		if (Math.abs(balance) > 1) {
			if (balance > 0) {

				int balanceChild = calculateBalance((BSTNode<T>) node.getLeft());

				if (balanceChild < 0) {
					leftRotation((BSTNode<T>) node.getLeft());
					rightRotation(node);
				} else
					rightRotation(node);
			} else {

				int balanceChild = calculateBalance((BSTNode<T>) node
						.getRight());

				if (balanceChild > 0) {
					rightRotation((BSTNode<T>) node.getRight());
					leftRotation(node);
				} else
					leftRotation(node);

			}
		}
	}

	// AUXILIARY
	protected void rebalanceUp(BSTNode<T> node) {
		BSTNode<T> y = (BSTNode<T>) node.getParent();
		while (y != null) {
			rebalance(y);
			y = (BSTNode<T>) y.getParent();
		}
	}

	// AUXILIARY
	protected void leftRotation(BSTNode<T> node) {

		BSTNode<T> pivot = (BSTNode<T>) node.getRight();
		node.setRight(pivot.getLeft());
		pivot.getLeft().setParent(node);
		pivot.setLeft(node);

		if (node.equals(root))
			root = pivot;

		else {
			if (node.getParent().getLeft().equals(node))
				node.getParent().setLeft(pivot);
			else
				node.getParent().setRight(pivot);
		}

		pivot.setParent(node.getParent());
		node.setParent(pivot);
	}

	// AUXILIARY
	protected void rightRotation(BSTNode<T> node) {

		BSTNode<T> pivot = (BSTNode<T>) node.getLeft();
		node.setLeft(pivot.getRight());
		pivot.getRight().setParent(node);
		pivot.setRight(node);

		if (node.equals(root))
			root = pivot;
		
		else {
			if (node.getParent().getLeft().equals(node))
				node.getParent().setLeft(pivot);
			else
				node.getParent().setRight(pivot);
		}

		pivot.setParent(node.getParent());
		node.setParent(pivot);
	}

}
