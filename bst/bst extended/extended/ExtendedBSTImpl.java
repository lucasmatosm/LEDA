package adt.bst.extended;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;

public class ExtendedBSTImpl<T extends Comparable<T>> extends BSTImpl<T>
		implements ExtendedBST<T> {

	@Override
	public boolean equals(ExtendedBST<T> tree) {
		BSTNode<T> node1 = this.root;
		BSTNode<T> node2 = (BSTNode<T>) tree.getRoot();

		while (!(node1.isEmpty() || node2.isEmpty())) {

			if (!node1.getData().equals(node2.getData())
					|| compareLeft(node1, node2) || compareRight(node1, node2))
				return false;

			node1 = (BSTNode<T>) node1.getLeft();
			node2 = (BSTNode<T>) node2.getLeft();
		}

		if ((node1.isEmpty() && !node2.isEmpty())
				|| (node2.isEmpty() && !node1.isEmpty()))
			return false;

		node1 = this.root;
		node2 = (BSTNode<T>) tree.getRoot();

		while (!(node1.isEmpty() || node2.isEmpty())) {
			if (!node1.getData().equals(node2.getData())
					|| compareLeft(node1, node2) || compareRight(node1, node2))
				return false;

			node1 = (BSTNode<T>) node1.getRight();
			node2 = (BSTNode<T>) node2.getRight();
		}

		if ((node1.isEmpty() && !node2.isEmpty())
				|| (node2.isEmpty() && !node1.isEmpty()))
			return false;

		return true;

	}

	private boolean compareLeft(BSTNode<T> node1, BSTNode<T> node2) {
		return !node2.getLeft().isEmpty() && !node1.getLeft().isEmpty()
				&& !node1.getLeft().getData().equals(node2.getLeft().getData());
	}

	private boolean compareRight(BSTNode<T> node1, BSTNode<T> node2) {
		return !node1.getRight().isEmpty()
				&& !node2.getRight().isEmpty()
				&& !(node1.getRight().getData().equals(node2.getRight()
						.getData()));
	}

	@Override
	public boolean isSimilar(ExtendedBST<T> tree) {
		return isSimilar((BSTNode<T>) this.root, (BSTNode<T>) tree.getRoot());

	}

	// isSimilar up-bottom
	public boolean isSimilar(BSTNode<T> node1, BSTNode<T> node2) {

		boolean result = false;

		if (!(node1 == null || node2 == null)) {

			if (node1.isEmpty())
				return node2.isEmpty();

			else
				result = (node1.isEmpty() == node2.isEmpty())
						&& isSimilar((BSTNode<T>) node1.getLeft(),
								(BSTNode<T>) node2.getLeft())
						&& isSimilar((BSTNode<T>) node1.getRight(),
								(BSTNode<T>) node2.getRight());
		}
		return result;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean contains(ExtendedBST<T> subtree) {
		Comparable[] array = subtree.order();
		Comparable[] array2 = this.order();
		int contador = 0;

		if (array2.length < array.length)
			return false;

		for (int i = 0; i < array2.length; i++) {
			for (int j = 0; j < array.length; j++) {
				if (array2[i].equals(array[j]))
					contador++;
			}
		}

		return contador == array.length;
	}

	@Override
	public void mirror() {
		BSTNode<T> right = this.minimum();
		BSTNode<T> left = this.maximum();

		mirror(right);
		mirror(left);

		BSTNode<T> aux = (BSTNode<T>) root.getLeft();
		root.setLeft(root.getRight());
		root.setRight(aux);

	}

	protected void mirror(BSTNode<T> node) {
		if (!node.equals(root)) {
			BSTNode<T> aux = (BSTNode<T>) node.getLeft();
			node.setLeft(node.getRight());
			node.setRight(aux);
			mirror((BSTNode<T>) node.getParent());
		}
	}
}