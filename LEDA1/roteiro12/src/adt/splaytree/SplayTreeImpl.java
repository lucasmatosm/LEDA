package adt.splaytree;

import adt.avltree.AVLTreeImpl;
import adt.bst.BSTNode;

public class SplayTreeImpl<T extends Comparable<T>> extends AVLTreeImpl<T>
		implements SplayTree<T> {

	@Override
	public void remove(T element) {

		BSTNode<T> node = search(element);

		if (!node.isEmpty()) {
			if (node.getLeft().isEmpty() && node.getRight().isEmpty()) {
				node.setData(null);
				node.setLeft(null);
				node.setRight(null);
				splay(node);

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
				splay(node);
			}
		} else {
			BSTNode<T> sucessor = sucessor(element);
			node.setData(sucessor.getData());
			sucessor.setData(null);
			sucessor.setLeft(null);
			sucessor.setRight(null);
			splay(sucessor);
		}
	}

	@Override
	public BSTNode<T> search(T element) {
		BSTNode<T> x = (BSTNode<T>) search(element);
		if (x.isEmpty()) {
			splay((BSTNode<T>) x.getParent());
		} else {
			splay(x);
		}
		return x;
	}

	protected void insert(BSTNode<T> node, T element) {

		if (node.isEmpty()) {
			BSTNode<T> left = new BSTNode<T>();
			BSTNode<T> right = new BSTNode<T>();
			node.setData(element);
			node.setLeft(left);
			left.setParent(node);
			node.setRight(right);
			right.setParent(node);
			splay(node);

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

	private void splay(BSTNode<T> node) {
		// height(node) < height() / 2
		BSTNode<T> pai;
		BSTNode<T> avo;

		while (node != null && !node.isEmpty() && node.getParent() != null) {

			pai = (BSTNode<T>) node.getParent();

			if (pai.equals(root)) {
				if (pai.getLeft().equals(node))
					rightRotation(pai);
				else
					leftRotation(pai);

			} else {
				avo = (BSTNode<T>) node.getParent().getParent();

				if (pai.getLeft().equals(node) && avo.getLeft().equals(pai)) {
					rightRotation(avo);
					rightRotation(pai);

				} else if (pai.getRight().equals(node)
						&& avo.getRight().equals(pai)) {
					leftRotation(avo);
					leftRotation(pai);

				} else {
					if (pai.getLeft().equals(node)) {
						rightRotation(pai);
						leftRotation(avo);
					} else {
						leftRotation(pai);
						rightRotation(avo);
					}
				}
			}
		}
	}
}
