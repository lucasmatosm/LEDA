package adt.bst;

import java.util.ArrayList;
import java.util.List;

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

	private int max(int i1, int i2) {
		return i1 > i2 ? i1 : i2;
	}

	public int height(BSTNode<T> node) {
		if (node.isEmpty())
			return -1;
		return 1 + max(height((BSTNode<T>) node.getLeft()),
				height((BSTNode<T>) node.getRight()));
	}

	@Override
	public int height() {
		return height(root);
	}

	private BSTNode<T> search(BSTNode<T> node, T element) {
		if (node.isEmpty() || node.getData().equals(element))
			return node;

		else if (element.compareTo(node.getData()) > 0)
			return search((BSTNode<T>) node.getRight(), element);

		else
			return search((BSTNode<T>) node.getLeft(), element);
	}

	@Override
	public BSTNode<T> search(T element) {
		return search(root, element);
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

	private BSTNode<T> maximum(BSTNode<T> node) {
		BSTNode<T> max = node;
		if (node.isEmpty())
			return null;
		while (!max.getRight().isEmpty()) {
			max = (BSTNode<T>) max.getRight();
		}
		return max;
	}

	@Override
	public BSTNode<T> maximum() {
		return maximum(root);
	}

	private BSTNode<T> minimum(BSTNode<T> node) {
		BSTNode<T> min = node;
		if (node.isEmpty())
			return null;
		while (!min.getLeft().isEmpty()) {
			min = (BSTNode<T>) min.getLeft();
		}
		return min;
	}

	@Override
	public BSTNode<T> minimum() {
		return minimum(root);
	}

	@Override
	public BSTNode<T> sucessor(T element) {
		BSTNode<T> node = search(element);
		if (node.isEmpty())
			return null;
		
		if (!node.getRight().isEmpty()) {
			return minimum((BSTNode<T>) node.getRight());
		}
		BSTNode<T> y = (BSTNode<T>) node.getParent();
		
		while (y != null && y.getRight().equals(node)) {
			node = y;
			y = (BSTNode<T>) y.getParent();
		}
		return y;
	}

	@Override
	public BSTNode<T> predecessor(T element) {
		BSTNode<T> node = search(element);
		if (node.isEmpty())
			return null;
		// tem filho a esquerda
		if (!node.getLeft().isEmpty()) {
			return maximum((BSTNode<T>) node.getLeft());
		}
		BSTNode<T> y = (BSTNode<T>) node.getParent();
		// ele sobe até encontrar o primeiro ancestral que seja filho à direita
		while (y != null && y.getLeft().equals(node)) {
			node = y;
			y = (BSTNode<T>) y.getParent();
		}
		return y;
	}

	@Override
	public void remove(T element) {
		BSTNode<T> node = search(element);
		if (node.isEmpty())
			return;
		
		if (node.getLeft().isEmpty() && node.getRight().isEmpty()) {
			node.setData(null);
			node.setLeft(null);
			node.setRight(null);
			
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
			
		} else {
			BSTNode<T> sucessor = sucessor(element);
			node.setData(sucessor.getData());
			sucessor.setData(null);
			sucessor.setLeft(null);
			sucessor.setRight(null);
		}
	}

	private void visit(BSTNode<T> node, List<T> list) {
		list.add(node.getData());
	}

	private void preOrder(BSTNode<T> node, List<T> list) {
		if (!node.isEmpty()) {
			visit(node, list);
			preOrder((BSTNode<T>) node.getLeft(), list);
			preOrder((BSTNode<T>) node.getRight(), list);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T[] preOrder() {
		List<T> list = new ArrayList<>();
		preOrder(root, list);
		T[] array = (T[]) new Comparable[size()];
		list.toArray(array);
		return array;
	}

	private void order(BSTNode<T> node, List<T> list) {
		if (!node.isEmpty()) {
			order((BSTNode<T>) node.getLeft(), list);
			visit(node, list);
			order((BSTNode<T>) node.getRight(), list);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T[] order() {
		List<T> list = new ArrayList<>();
		order(root, list);
		T[] array = (T[]) new Comparable[size()];
		list.toArray(array);
		return array;
	}

	private void postOrder(BSTNode<T> node, List<T> list) {
		if (!node.isEmpty()) {
			postOrder((BSTNode<T>) node.getLeft(), list);
			postOrder((BSTNode<T>) node.getRight(), list);
			visit(node, list);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T[] postOrder() {
		List<T> list = new ArrayList<>();
		postOrder(root, list);
		T[] array = (T[]) new Comparable[size()];
		list.toArray(array);
		return array;
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

}
