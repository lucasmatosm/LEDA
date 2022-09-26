package adt.avltree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.BTNode;


public class AVLTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements
		AVLTree<T> {

	@Override
	public void insert(T element) {
		insert(getRoot(), element);
		root = resetRoot(root);
	}

	@Override
	protected void insert(BSTNode<T> node, T element) {
		if (node.isEmpty()) {
			node.setData(element);
			node.setLeft(new BSTNode<T>());
			node.setRight(new BSTNode<T>());
			node.getLeft().setParent(node);
			node.getRight().setParent(node);
		} else {
			if (node.getData().compareTo(element) > 0)
				insert((BSTNode<T>) node.getLeft(), element);
			else
				insert((BSTNode<T>) node.getRight(), element);
			rebalance(node);
		}
	}

	@Override
	public void remove(T element) {
		BSTNode<T> node = root;

		while (!root.isEmpty() && !element.equals(node.getData())) {
			if (element.compareTo(node.getData()) < 0)
				node = (BSTNode<T>) node.getLeft();
			else
				node = (BSTNode<T>) node.getRight();
		}

		remove(node);
		root = resetRoot(root);

	}

	@Override
	protected void remove(BSTNode<T> node) {
		BSTNode<T> auxNode = node;

		if (auxNode != null) {
			if (!auxNode.isEmpty()) {

				if (auxNode.getLeft().isEmpty() && auxNode.getRight().isEmpty()) {
					auxNode = new BSTNode<T>();
					rebalanceUp(auxNode);

				} else if (auxNode.getLeft().isEmpty()
						|| auxNode.getRight().isEmpty()) {

					if (auxNode.getParent() != null) {

						if (auxNode.getParent().getLeft().equals(auxNode)) {

							if (!auxNode.getLeft().isEmpty())
								auxNode.getParent().setLeft(auxNode.getLeft());
							else
								auxNode.getParent().setLeft(auxNode.getRight());

						} else {

							if (!auxNode.getLeft().isEmpty())
								auxNode.getParent().setRight(auxNode.getLeft());
							else
								auxNode.getParent()
										.setRight(auxNode.getRight());
						}

					} else {

						if (!auxNode.getLeft().isEmpty()
								&& auxNode.getRight().isEmpty())
							auxNode = (BSTNode<T>) auxNode.getLeft();
						else if (auxNode.getLeft().isEmpty()
								&& !auxNode.getRight().isEmpty())
							auxNode = (BSTNode<T>) auxNode.getRight();

						auxNode.setParent(null);

					}
					rebalanceUp(auxNode);

				} else {
					BSTNode<T> sucessor = sucessor(auxNode.getData());
					auxNode.setData(sucessor.getData());
					remove(sucessor);
				}
			}
		}
		node.setLeft(auxNode.getLeft());
		node.setParent(auxNode.getParent());
		node.setRight(auxNode.getRight());
		node.setData(auxNode.getData());
	}

	private BSTNode<T> resetRoot(BSTNode<T> node) {
		BSTNode<T> auxNode = node;

		while (auxNode.getParent() != null) {
			auxNode = (BSTNode<T>) auxNode.getParent();
		}

		return auxNode;
	}

	// AUXILIARY
	protected int calculateBalance(BSTNode<T> node) {
		if (node != null)
			return height((BSTNode<T>) node.getLeft())
					- height((BSTNode<T>) node.getRight());
		else
			return 0;
	}

	// AUXILIARY
	protected void rebalance(BSTNode<T> node) {
		if (calculateBalance(node) > 1) {
			if (calculateBalance((BSTNode<T>) node.getLeft()) >= 0) {
				rightRotation(node);
			} else {
				leftRotation((BSTNode<T>) node.getLeft());
				rightRotation((BSTNode<T>) node);
			}
		} else if (calculateBalance(node) < -1) {
			if (calculateBalance((BSTNode<T>) node.getRight()) <= 0) {
				leftRotation(node);
			} else {
				rightRotation((BSTNode<T>) node.getRight());
				leftRotation((BSTNode<T>) node);
			}
		}
	}

	// AUXILIARY
	protected void rebalanceUp(BSTNode<T> node) {
		BTNode<T> parent = node.getParent();
		while (parent != null) {
			rebalance((BSTNode<T>) parent);
			parent = parent.getParent();
		}
	}

	// AUXILIARY
	protected void leftRotation(BSTNode<T> node) {
		BSTNode<T> pivot = (BSTNode<T>) node.getRight();

		pivot.setParent(node.getParent());

		if (pivot.getParent() != null) {
			if (node.getParent().getLeft().equals(node))
				pivot.getParent().setLeft(pivot);
			else
				pivot.getParent().setRight(pivot);
		}
		node.setRight(pivot.getLeft());
		node.getRight().setParent(node);
		node.setParent(pivot);

		pivot.setLeft(node);
		pivot.getLeft().setParent(pivot);

		node = pivot;
	}

	// AUXILIARY
	protected void rightRotation(BSTNode<T> node) {

		BSTNode<T> pivot = (BSTNode<T>) node.getLeft();

		pivot.setParent(node.getParent());

		if (pivot.getParent() != null) {
			if (node.getParent().getLeft().equals(node)) {
				pivot.getParent().setLeft(pivot);
			} else {
				pivot.getParent().setRight(pivot);
			}
		}
		node.setLeft(pivot.getRight());
		node.getLeft().setParent(node);
		node.setParent(pivot);

		pivot.setRight(node);
		pivot.getRight().setParent(pivot);

		node = pivot;
	}
}
