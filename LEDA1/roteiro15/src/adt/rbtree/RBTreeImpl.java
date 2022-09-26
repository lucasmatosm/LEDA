package adt.rbtree;

import java.util.ArrayList;
import java.util.List;

import adt.avltree.AVLTreeImpl;
import adt.bst.BSTNode;
import adt.rbtree.RBNode.Colour;

public class RBTreeImpl<T extends Comparable<T>> extends AVLTreeImpl<T>
		implements RBTree<T> {

	public RBTreeImpl() {
		this.root = new RBNode<T>();
	}

	protected int blackHeight() {
		return blackHeight(this.root);
	}

	public int blackHeight(BSTNode<T> node) {
		if (!node.isEmpty())
			if (((RBNode<T>) node.getLeft()).getColour() == Colour.BLACK
					|| ((RBNode<T>) node.getRight()).getColour() == Colour.BLACK)
				return blackHeight((RBNode<T>) node.getLeft()) + 1;
			else
				return 1;
		return 0;
	}

	protected boolean verifyProperties() {
		boolean resp = verifyNodesColour() && verifyNILNodeColour()
				&& verifyRootColour() && verifyChildrenOfRedNodes()
				&& verifyBlackHeight();

		return resp;
	}

	/**
	 * The colour of each node of a RB tree is black or red. This is guaranteed
	 * by the type Colour.
	 */
	protected boolean verifyNodesColour() {
		// return verifyNodeColor((RBNode<T>) root);
		return true; // already implemented
	}

	public boolean verifyNodeColor(RBNode<T> node) {
		if (node.isEmpty())
			return true;
		else if (node.getColour() == Colour.BLACK
				|| node.getColour() == Colour.RED)
			return verifyNodeColor((RBNode<T>) node.getLeft())
					&& verifyNodeColor((RBNode<T>) node.getRight());
		return false;
	}

	/**
	 * The colour of the root must be black.
	 */
	protected boolean verifyRootColour() {
		return ((RBNode<T>) root).getColour() == Colour.BLACK; // already
																// implemented
	}

	/**
	 * This is guaranteed by the constructor.
	 */
	protected boolean verifyNILNodeColour() {
		return verifyNodeColor((RBNode<T>) root);
		// return true; //already implemented
	}

	/**
	 * Verifies the property for all RED nodes: the children of a red node must
	 * be BLACK.
	 */
	protected boolean verifyChildrenOfRedNodes() {
		return verifyChildrenOfRedNodes((RBNode<T>) getRoot());
	}

	private boolean verifyChildrenOfRedNodes(RBNode<T> node) {
		boolean resp = true;
		if (!node.isEmpty()) {
			if (node.getColour() == Colour.RED)
				resp = ((RBNode<T>) node.getLeft()).getColour() == Colour.BLACK
						&& ((RBNode<T>) node.getRight()).getColour() == Colour.BLACK;
			if (resp)
				return resp
						&& verifyChildrenOfRedNodes((RBNode<T>) node.getRight())
						&& verifyChildrenOfRedNodes((RBNode<T>) node.getLeft());
		}
		return resp;
	}

	/**
	 * Verifies the black-height property from the root. The method blackHeight
	 * returns an exception if the black heights are different.
	 */
	protected boolean verifyBlackHeight() {
		return blackHeight((RBNode<T>) root.getLeft()) == blackHeight((RBNode<T>) root
				.getRight());
	}

	@Override
	public void insert(T value) {
		if (value != null)
			insert(value, getRoot());
	}

	public void insert(T value, BSTNode<T> node) {
		if (node.isEmpty()) {
			node.setData(value);
			node.setLeft(new RBNode<T>());
			node.getLeft().setParent(node);
			node.setRight(new RBNode<T>());
			node.getRight().setParent(node);
			((RBNode<T>) node).setColour(Colour.RED);
			fixUp_Case1((RBNode<T>) node);

		} else {
			if (value.compareTo(node.getData()) > 0)
				insert(value, (BSTNode<T>) node.getRight());

			if (value.compareTo(node.getData()) < 0)
				insert(value, (BSTNode<T>) node.getLeft());
		}
	}

	private void fixUp_Case1(RBNode<T> node) {
		if (node.equals(getRoot()))
			node.setColour(Colour.BLACK);
		else
			fixUp_Case2(node);
	}

	private void fixUp_Case2(RBNode<T> node) {
		if (((RBNode<T>) node.getParent()).getColour() != Colour.BLACK)
			fixUp_Case3(node);
	}

	private void fixUp_Case3(RBNode<T> node) {

		RBNode<T> fatherNode = (RBNode<T>) node.getParent();
		RBNode<T> grandfatherNode = (RBNode<T>) fatherNode.getParent();
		RBNode<T> uncleNode;

		if (fatherNode.equals(grandfatherNode.getLeft()))
			uncleNode = (RBNode<T>) grandfatherNode.getRight();
		else
			uncleNode = (RBNode<T>) grandfatherNode.getLeft();

		if (uncleNode.getColour() == Colour.RED) {
			fatherNode.setColour(Colour.BLACK);
			uncleNode.setColour(Colour.BLACK);
			grandfatherNode.setColour(Colour.RED);
			fixUp_Case1(grandfatherNode);
		} else
			fixUp_Case4(node);

	}

	private void fixUp_Case4(RBNode<T> node) {

		RBNode<T> next = node;
		RBNode<T> parentNode = (RBNode<T>) node.getParent();

		if (node.equals(parentNode.getRight())
				&& parentNode.equals(parentNode.getParent().getLeft())) {
			leftRotation(parentNode);
			next = (RBNode<T>) node.getLeft();

		} else if (node.equals(parentNode.getLeft())
				&& parentNode.equals(parentNode.getParent().getRight())) {
			rightRotation(parentNode);
			next = (RBNode<T>) node.getRight();
		}

		fixUp_Case5(next);
	}

	private void fixUp_Case5(RBNode<T> node) {

		RBNode<T> fatherNode = (RBNode<T>) node.getParent();
		RBNode<T> grandfatherNode = (RBNode<T>) fatherNode.getParent();
		fatherNode.setColour(Colour.BLACK);
		grandfatherNode.setColour(Colour.RED);

		if (node.equals(fatherNode.getLeft()))
			rightRotation(grandfatherNode);
		else
			leftRotation(grandfatherNode);
	}

	@SuppressWarnings("unchecked")
	@Override
	public RBNode<T>[] extendedPreOrder() {
		
		List<RBNode<T>> lista = new ArrayList<RBNode<T>>();
		extendedPreOrder(root, lista);
		RBNode<T>[] array = new RBNode[size()];

		for (int i = 0; i < array.length; i++)
			array[i] = lista.get(i);

		return array;
	}

	private void extendedPreOrder(BSTNode<T> root, List<RBNode<T>> list) {
		if (!root.isEmpty()) {
			list.add((RBNode<T>) root);
			extendedPreOrder((BSTNode<T>) root.getLeft(), list);
			extendedPreOrder((BSTNode<T>) root.getRight(), list);
		}
	}

	public double contnodesred() {
		return contnodesred((RBNode<T>) this.root);
	}

	private double contnodesred(RBNode<T> node) {

		double contadordereds = 0;
		double total = 0;

		if (!node.isEmpty()) {

			if (node.getColour() == Colour.BLACK)
				return contnodesred((RBNode<T>) node.getLeft())
						+ contnodesred((RBNode<T>) node.getRight());

			contadordereds = 1 + contnodesred((RBNode<T>) node.getLeft())
					+ contnodesred((RBNode<T>) node.getRight());

			total = (contadordereds / (2 * contadordereds)) * 100;
		}
		return total;
	}
}
