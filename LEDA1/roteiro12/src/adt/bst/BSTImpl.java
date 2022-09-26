package adt.bst;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import adt.bt.BTNode;

/* 
 * Edit this file where indicated with "TODO" comments 
 * YOU MUST NOT CHANGE ANY OTHER POINT 
 * Use the cest case TestBST to check your implementation 
 */

/**
 * 
 * @author Wandson Allys Silva Dantas. Matricula: 112210672
 *
 * @param <T>
 */
public class BSTImpl<T extends Comparable<T>> implements BST<T> {

	protected BSTNode<T> root;

	public BSTImpl() {
		root = new BSTNode<T>();
	}

	public BSTNode<T> getRoot() {
		return root;
	}

	@Override
	public boolean isEmpty() {
		return root.isEmpty();
	}

	@Override
	public int height() {
		return height(root);
	}

	public int height(BSTNode<T> node) {
		if (root == null)
			return 0;

		int result = -1;

		if (!node.isEmpty()) {
			result = 1 + greater(height((BSTNode<T>) node.getLeft()),
					height((BSTNode<T>) node.getRight()));
		}
		return result;
	}

	private int greater(int value1, int value2) {
		if (value2 > value1) {
			return value2;
		}
		return value1;
	}

	@Override
	public int size() {
		return size(root);
	}

	private int size(BSTNode<T> node) {
		int result = 0;
		if (!node.isEmpty()) {
			result = 1 + size((BSTNode<T>) node.getLeft())
					+ size((BSTNode<T>) node.getRight());
		}
		return result;
	}

	public String toString() {
		return Arrays.toString(this.preOrder());
	}

	@Override
	public void insert(T element) {
		insert(root, element);
	}

	protected void insert(BSTNode<T> node, T element) {
		if (node.isEmpty()) {
			node.setData(element);
			node.setLeft(new BSTNode<T>());
			node.setRight(new BSTNode<T>());
			node.getLeft().setParent(node);
			node.getRight().setParent(node);
		} else {
			while (!node.isEmpty()) {
				if (element.compareTo(node.getData()) < 0) {
					node = (BSTNode<T>) node.getLeft();
				} else if (element.compareTo(node.getData()) > 0) {
					node = (BSTNode<T>) node.getRight();
				}
			}
			node.setData(element);
			node.setLeft(new BSTNode<T>());
			node.setRight(new BSTNode<T>());
			node.getLeft().setParent(node);
			node.getRight().setParent(node);
		}
	}

	@Override
	public BSTNode<T> maximum() {
		return maximum(this.getRoot());
	}

	protected BSTNode<T> maximum(BSTNode<T> node) {
		BSTNode<T> result = node;
		if (!node.isEmpty()) {
			while (!node.getRight().isEmpty()) {
				node = (BSTNode<T>) node.getRight();
			}
			result = node;
		} else {
			result = null;
		}
		return result;
	}

	@Override
	public BSTNode<T> minimum() {
		return minimum(this.getRoot());
	}

	protected BSTNode<T> minimum(BSTNode<T> node) {
		BSTNode<T> result = node;
		if (!node.isEmpty()) {
			while (!node.getLeft().isEmpty()) {
				node = (BSTNode<T>) node.getLeft();
			}
			result = node;
		} else {
			result = null;
		}
		return result;
	}

	@Override
	public BSTNode<T> sucessor(T element) {
		BSTNode<T> result = null;
		BSTNode<T> x = (BSTNode<T>) search(element);
		if (!x.isEmpty()) {
			if (!x.getRight().isEmpty()) {
				result = minimum((BSTNode<T>) x.getRight());
			} else {
				BSTNode<T> y = (BSTNode<T>) x.getParent();
				if (y != null) {
					while (y != null && x.equals(y.getRight())) {
						x = y;
						y = (BSTNode<T>) y.getParent();
					}
				}
				result = y;
			}
		}
		return result;
	}

	@Override
	public BSTNode<T> predecessor(T element) {
		BSTNode<T> result = null;
		BSTNode<T> x = (BSTNode<T>) search(element);
		if (!x.isEmpty()) {
			if (!x.getLeft().isEmpty()) {
				result = maximum((BSTNode<T>) x.getLeft());
			} else {
				BSTNode<T> y = (BSTNode<T>) x.getParent();
				if (y != null) {
					while (y != null && x.equals(y.getLeft())) {
						x = y;
						y = (BSTNode<T>) y.getParent();
					}
				}
				result = y;
			}
		}
		return result;
	}

	@Override
	public void remove(T element) {
		BSTNode<T> found = (BSTNode<T>) search(element);
		remove(found);
	}

	protected void remove(BSTNode<T> node) {
		BSTNode<T> auxNode = node;
		if (!auxNode.isEmpty()) {
			if (auxNode.getLeft().isEmpty() && auxNode.getRight().isEmpty()) {
				auxNode.setData(null);
				auxNode.setLeft(null);
				auxNode.setRight(null);
			} else if (auxNode.getLeft().isEmpty()
					|| auxNode.getRight().isEmpty()) {
				if (auxNode != this.root) {
					if (auxNode == auxNode.getParent().getLeft()) {
						if (!auxNode.getLeft().isEmpty()) {
							auxNode.getParent().setLeft(auxNode.getLeft());
						} else {
							auxNode.getParent().setLeft(auxNode.getRight());
						}
					} else {
						if (!auxNode.getLeft().isEmpty()) {
							auxNode.getParent().setLeft(auxNode.getLeft());
							auxNode.getLeft().setParent(auxNode.getParent());
						} else {
							auxNode.getParent().setRight(auxNode.getRight());
							auxNode.getRight().setParent(auxNode.getParent());
						}
					}
				} else {
					if (!auxNode.getLeft().isEmpty()) {
						auxNode = (BSTNode<T>) auxNode.getLeft();
					} else {
						auxNode = (BSTNode<T>) auxNode.getRight();
					}
					auxNode.setParent(new BSTNode<T>());
				}
			} else {
				BSTNode<T> sucessor = sucessor(auxNode.getData());
				auxNode.setData(sucessor.getData());
				remove(sucessor);
			}
		}
		node.setLeft(auxNode.getLeft());
		node.setParent(auxNode.getParent());
		node.setRight(auxNode.getRight());
		node.setData(auxNode.getData());

	}

	@Override
	public BTNode<T> search(T elem) {
		BSTNode<T> node = root;
		while (!node.isEmpty() && !elem.equals(node.getData())) {
			if (elem.compareTo(node.getData()) < 0) {
				node = (BSTNode<T>) node.getLeft();
			} else {
				node = (BSTNode<T>) node.getRight();
			}
		}
		return node;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public T[] preOrder() {
		List<T> array = new ArrayList<T>();
		Comparable[] a = new Comparable[size()];
		preOrderRecursive(getRoot(), array);
		return (T[]) array.toArray(a);
	}

	private void preOrderRecursive(BSTNode<T> node, List<T> array) {
		if (!node.isEmpty()) {
			array.add(node.getData());
			preOrderRecursive((BSTNode<T>) node.getLeft(), array);
			preOrderRecursive((BSTNode<T>) node.getRight(), array);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public T[] order() {
		List<T> array = new ArrayList<T>();
		Comparable[] a = new Comparable[size()];
		orderRecursive(getRoot(), array);
		return (T[]) array.toArray(a);
	}

	private void orderRecursive(BSTNode<T> node, List<T> array) {
		if (!node.isEmpty()) {
			orderRecursive((BSTNode<T>) node.getLeft(), array);
			array.add(node.getData());
			orderRecursive((BSTNode<T>) node.getRight(), array);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public T[] postOrder() {
		List<T> array = new ArrayList<T>();
		Comparable[] a = new Comparable[size()];
		postOrderRecursive(getRoot(), array);
		return (T[]) array.toArray(a);
	}

	private void postOrderRecursive(BSTNode<T> node, List<T> array) {
		if (!node.isEmpty()) {
			postOrderRecursive((BSTNode<T>) node.getLeft(), array);
			postOrderRecursive((BSTNode<T>) node.getRight(), array);
			array.add(node.getData());
		}
	}

}