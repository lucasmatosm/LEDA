package adt.bst;

import java.util.ArrayList;
import java.util.List;
import adt.bt.BTNode;

public class BSTImpl<T extends Comparable<T>> implements BST<T> {
	protected BSTNode<T> root;

	public BSTImpl() {
		root = new BSTNode<T>();
	}

	public BSTNode<T> getRoot() {
		return this.root;
	}

	@Override
	public boolean isEmpty() {
		return root.isEmpty();
	}

	@Override
	public int height() {
		return thisheight(getRoot());
	}

	public int thisheight(BTNode<T> btNode) {
		int leftHeight = 0, rigthHeight = 0;
		if (btNode.isEmpty()) {
			return -1;
		}
		if (!btNode.getLeft().isEmpty()) {
			leftHeight += 1 + thisheight((BSTNode<T>) btNode.getLeft());
		}
		if (!btNode.getRight().isEmpty()) {
			rigthHeight += 1 + thisheight((BSTNode<T>) btNode.getRight());
		}
		return Math.max(leftHeight, rigthHeight);
	}

	@Override
	public BSTNode<T> search(T element) {
		return searchElement(getRoot(), element);
	}

	private BSTNode<T> searchElement(BSTNode<T> node, T element) {
		if (node.isEmpty()) {
			return node;
		} else {
			if (element.equals(node.getData())) {
				return node;
			} else {
				if (element.compareTo(node.getData()) > 0) {
					node = (BSTNode<T>) node.getRight();
					return this.searchElement(node, element);
				} else {
					node = (BSTNode<T>) node.getLeft();
					return this.searchElement(node, element);
				}
			}
		}
	}

	@Override
	public void insert(T element) {
		BSTNode<T> parent = new BSTNode<T>();
		insertElement(parent, getRoot(), element);
	}

	private void insertElement(BSTNode<T> parent, BSTNode<T> node, T element) {
		if (node.isEmpty()) {
			node.setData(element);
			node.setParent(parent);
			node.setRight(new BSTNode<T>());
			node.setLeft(new BSTNode<T>());
		} else {
			if (element.compareTo(node.getData()) > 0) {
				parent = node;
				node = (BSTNode<T>) parent.getRight();
				this.insertElement(parent, node, element);
			} else {
				parent = node;
				node = (BSTNode<T>) parent.getLeft();
				this.insertElement(parent, node, element);
			}
		}
	}

	@Override
	public BSTNode<T> maximum() {
		return thismaximum(root);
	}

	private BSTNode<T> thismaximum(BSTNode<T> node) {
		if (node.isEmpty()) {
			return null;
		}
		BSTNode<T> Auxroot = node;
		while (!(Auxroot.getRight().isEmpty())) {
			Auxroot = (BSTNode<T>) Auxroot.getRight();
		}
		return Auxroot;
	}

	@Override
	public BSTNode<T> minimum() {
		return thisminimum(root);
	}

	private BSTNode<T> thisminimum(BSTNode<T> node) {
		if (node.isEmpty()) {
			return null;
		}
		BSTNode<T> Auxroot = node;
		while (!(Auxroot.getLeft().isEmpty())) {
			Auxroot = (BSTNode<T>) Auxroot.getLeft();
		}
		return Auxroot;
	}

	@Override
	public BSTNode<T> sucessor(T element) {
		BSTNode<T> toSearchSucessor = search(element);
		return sucessorElement(toSearchSucessor);
	}

	public BSTNode<T> noSucessor(BSTNode<T> no) {
		BSTNode<T> toSearchSucessor = search(no.getData());
		return sucessorElement(toSearchSucessor);
	}

	public BSTNode<T> sucessorElement(BSTNode<T> node) {
		if (node.isEmpty()) {
			return null;
		}
		if (!(node.getRight().isEmpty())) {
			return thisminimum((BSTNode<T>) node.getRight());
		}
		BSTNode<T> thisparent = (BSTNode<T>) node.getParent();
		while (true) {
			if (thisparent == null || thisparent.isEmpty()) {
				break;
			}
			if (!node.equals(thisparent.getRight())) {
				break;
			}
			node = thisparent;
			thisparent = (BSTNode<T>) node.getParent();
		}
		if (thisparent.isEmpty()) {
			return null;
		}
		return thisparent;
	}

	@Override
	public BSTNode<T> predecessor(T element) {
		BSTNode<T> toSearchPredecessor = search(element);
		return predecessorElement(toSearchPredecessor, element);
	}

	private BSTNode<T> predecessorElement(BSTNode<T> node, T element) {
		if (node.isEmpty()) {
			return null;
		}
		if (!(node.getLeft().isEmpty())) {
			return thismaximum((BSTNode<T>) node.getLeft());
		}
		BSTNode<T> thisparent = (BSTNode<T>) node.getParent();
		while (true) {
			if (thisparent == null || thisparent.isEmpty()) {
				break;
			}
			if (!node.equals(thisparent.getLeft())) {
				break;
			}
			node = thisparent;
			thisparent = (BSTNode<T>) node.getParent();
		}
		if (thisparent.isEmpty()) {
			return null;
		}
		return thisparent;
	}

	public boolean hasOneChild(BSTNode<T> node) {
		return ((!(node.getLeft().isEmpty())) && node.getRight().isEmpty())
				|| (node.getLeft().isEmpty() && (!node.getRight().isEmpty()));
	}

	public boolean isLeftChild(BSTNode<T> node) {
		if (node.getParent() == null) {
			return false;
		}
		if (node.getParent().isEmpty()) {
			return false;
		}
		return node.getParent().getLeft().equals(node);
	}

	public boolean isLeaf(BSTNode<T> node) {
		return node.getLeft().isEmpty() && node.getRight().isEmpty();
	}

	public void removeRecursive(BSTNode<T> node) {
		if (!node.isEmpty()) {
			if (isLeaf(node)) {
				node.setData(null);
			} else if (hasOneChild(node)) {
				if (!node.equals(getRoot())) {
					if (isLeftChild(node)) {
						if (!node.getLeft().isEmpty()) {
							node.getParent().setLeft(node.getLeft());
							node.getLeft().setParent(node.getParent());
						} else {
							node.getParent().setLeft(node.getRight());
							node.getLeft().setParent(node.getParent());
						}
					} else {
						if (!node.getLeft().isEmpty()) {
							node.getParent().setRight(node.getLeft());
							node.getRight().setParent(node.getParent());
						} else {
							node.getParent().setRight(node.getRight());
							node.getRight().setParent(node.getParent());
						}
					}
				} else {
					root.setData(null);
					if (!node.getRight().isEmpty()) {
						root = (BSTNode<T>) node.getRight();
					} else {
						root = (BSTNode<T>) node.getLeft();
					}
				}
			} else {
				BSTNode<T> sucessor = sucessor(node.getData());
				node.setData(sucessor.getData());
				removeRecursive(sucessor);
			}
		}
	}

	@Override
	public void remove(T element) {
		BSTNode<T> node = search(element);
		this.removeRecursive(node);
	}

	@Override
	public T[] preOrder() {
		T[] array = (T[]) new Comparable[this.size(getRoot())];
		List<T> arrayList = new ArrayList<T>();
		treePreOrder(arrayList, getRoot());
		for (int i = 0; i < arrayList.size(); i++) {
			array[i] = arrayList.get(i);
		}
		return array;
	}

	private void treePreOrder(List<T> Array, BSTNode<T> node) {
		if (!node.isEmpty()) {
			visitNode(node, Array);
			treePreOrder(Array, (BSTNode<T>) node.getLeft());
			treePreOrder(Array, (BSTNode<T>) node.getRight());
		}
	}

	private void visitNode(BSTNode<T> node, List<T> array) {
		array.add(node.getData());
	}

	@Override
	public T[] order() {
		T[] array = (T[]) new Comparable[this.size(getRoot())];
		List<T> arrayList = new ArrayList<T>();
		treeOrder(arrayList, getRoot());
		for (int i = 0; i < arrayList.size(); i++) {
			array[i] = arrayList.get(i);
		}
		return array;
	}

	private void treeOrder(List<T> array, BSTNode<T> node) {
		if (!node.isEmpty()) {
			treeOrder(array, (BSTNode<T>) node.getLeft());
			visitNode(node, array);
			treeOrder(array, (BSTNode<T>) node.getRight());
		}
	}

	@Override
	public T[] postOrder() {
		T[] array = (T[]) new Comparable[this.size(getRoot())];
		List<T> arrayList = new ArrayList<T>();
		treePostOrder(arrayList, getRoot());
		for (int i = 0; i < arrayList.size(); i++) {
			array[i] = arrayList.get(i);
		}
		return array;
	}

	private void treePostOrder(List<T> array, BSTNode<T> node) {
		if (!node.isEmpty()) {
			treePostOrder(array, (BSTNode<T>) node.getLeft());
			treePostOrder(array, (BSTNode<T>) node.getRight());
			visitNode(node, array);
		}
	}

	@Override
	public int size() {
		return size(root);
	}

	protected int size(BSTNode<T> node) {
		int result = 0;
		if (!node.isEmpty()) {
			result = 1 + size((BSTNode<T>) node.getLeft())
					+ size((BSTNode<T>) node.getRight());
		}
		return result;
	}
}