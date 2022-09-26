package adt.bst;

import java.util.ArrayList;

public class BSTImpl<K extends Comparable<? super K>, V> implements BST<K, V> {

	protected  BSTNode<K,V> root;
	
	
	public BSTImpl() {
		root = new BSTNode<K, V>();
	}

	public BSTNode<K, V> getRoot(){
		return this.root;
	}
	
	@Override
	public boolean isEmpty() {
		return root.isEmpty();
	}

	@Override
	public int height() {
		return height(root);
	}
	
	protected int height(BSTNode<K, V> node){
		int height = -1;
		if(!node.isEmpty()){
			int heightLeft = height(node.left);
			int heightRight = height(node.right);
			height = 1 + max(heightLeft, heightRight);
		}
		return height;
	}

	private int max(int heightLeft, int heightRight) {
		int result = heightLeft;
		if(heightRight > result){
			result = heightRight;
		}
		return result;
	}
	
	@Override
	public BSTNode<K,V> search(K key) {
		return search(root, key);
	}

	public BSTNode<K,V> search(BSTNode<K, V> node, K key) {
		BSTNode<K,V> result = node;
		if (key != null) {
			if (!node.isEmpty()) {
				if (key.compareTo(node.key) == 0) {
					result = node;
				} else if (key.compareTo(node.key) < 0) {
					result = search(node.left, key);
				} else {
					result = search(node.right, key);
				}
			}
		}
		return result;
	}

	@Override
	public void insert(K key, V value) {
		recursiveInsertion2(root, key, value);
	}
	
	public void recursiveInsertion2(BSTNode<K, V> node, K key, V value){
		if (node.isEmpty()) {
			node.key = key;
			node.value = value;
			node.left = new BSTNode<K, V>();
			node.left.parent = node;
			node.right = new BSTNode<K, V>();
			node.right.parent = node;
		} else {
			if (key.compareTo(node.key) < 0) {
				recursiveInsertion2(node.left, key, value);
			} else if (key.compareTo(node.key) > 0) {
				recursiveInsertion2(node.right, key, value);
			}
		}
	}
	
	@Override
	public BSTNode<K, V> maximum(BSTNode<K, V> node) {
		
		node = findNode(root, node);
		
		while (node != null && node.right != null && !node.right.isEmpty()) {
			node = node.right;
		}
		return node;
	}
	
	protected BSTNode<K, V> findNode(BSTNode<K, V> treeNode, BSTNode<K, V> node) {
		BSTNode<K, V> result = null;
		
		if(!treeNode.isEmpty() && !node.isEmpty()){
			if(treeNode.equals(node)){
				result = treeNode;
			}else if(node.key.compareTo(treeNode.key) < 0) {
				result = findNode(treeNode.left, node);
			}else if(node.key.compareTo(treeNode.key) > 0) {
				result = findNode(treeNode.right, node);
			}
		}
		return result;
	}

	@Override
	public BSTNode<K, V> minimum(BSTNode<K, V> node) {
		node = findNode(root, node);
		
		while (node != null && node.left != null && !node.left.isEmpty()) {
			node = node.left;
		}
		return node;
	}


	@Override
	public BSTNode<K, V> sucessor(BSTNode<K, V> node) {
		node = findNode(root, node);

		if(node.right != null && !node.right.isEmpty()){
			return minimum(node.right);
		}

		BSTNode<K, V> parent = node.parent;
		while(parent != null && !parent.isEmpty() && node.equals(parent.right)){
			node = parent;
			parent = parent.parent;
		}
		return parent;
	}

	@Override
	public BSTNode<K, V> predecessor(BSTNode<K, V> node) {
		node = findNode(root, node);
		
		if(node.left != null && !node.left.isEmpty()){
			return maximum(node.left);
		}
		
		BSTNode<K, V> parent = node.parent;
		while(parent != null && !parent.isEmpty() && node.equals(parent.left)){
			node = parent;
			parent = parent.parent;
		}
		return parent;
	}

	@Override
	public void remove(K key) {
		BSTNode<K, V> node = new BSTNode<K, V>();
		node.key = key;
		node = findNode(root, node);
		removeAux(node);
	}
	
	public void removeAux(BSTNode<K, V> node) {
		if(node != null && !node.isEmpty()){
			if(node.left.isEmpty() && node.right.isEmpty()){
				node.key = null;
				node.value = null;
				node.left = null;
				node.right = null;
			} else if ((!node.left.isEmpty() && node.right.isEmpty()) || (node.left.isEmpty() && !node.right.isEmpty()) ){
				if((!node.equals(root) || (node.equals(root)) && node.parent != null && node.parent.equals(root))){
					if(!node.parent.left.isEmpty() && node.parent.left.equals(node)){
						if(!node.left.isEmpty()){
							node.parent.left = node.left;
							node.left.parent = node.parent;
						}else{
							node.parent.left = node.right;
							node.right.parent = node.parent;	
						}
					}else{
						if(!node.left.isEmpty()){
							node.parent.right = node.left;
						}else{
							node.parent.right = node.right;
						}
					}
				} else {
					if(root.left.isEmpty()){
						root = root.right;
					}else if(root.right.isEmpty()){
						root = root.left;
					}
				}
			} else {
				BSTNode<K, V> sucessor = sucessor(node);
				node.key = sucessor.key;
				node.value = sucessor.value;
				removeAux(sucessor);
			}
		}
	}

	@Override
	public K[] preOrder() {
		ArrayList<K> array = new ArrayList<K>();
		Comparable[] a = new Comparable[size()];
		
		array = recursivePreOrder(root, array);
		return (K[]) array.toArray(a);
	}
	
	private ArrayList<K> recursivePreOrder(BSTNode<K, V> node, ArrayList<K> array){
		if(!node.isEmpty()){
			array.add(node.key);
			recursivePreOrder(node.left, array);
			recursivePreOrder(node.right, array);
		}
		return array;
	}

	@Override
	public K[] order() {
		ArrayList<K> array = new ArrayList<K>();
		Comparable[] a = new Comparable[size()];
		
		array = recursiveOrder(root, array);
		return (K[]) array.toArray(a);
	}
	
	private ArrayList<K> recursiveOrder(BSTNode<K, V> node, ArrayList<K> array){
		if(!node.isEmpty()) {
			recursiveOrder(node.left, array);
			array.add(node.key);
			recursiveOrder(node.right, array);
		}
		return array;
	}

	@Override
	public K[] postOrder() {
		ArrayList<K> array = new ArrayList<K>();
		Comparable[] a = new Comparable[size()];
		
		array = recursivePosOrder(root, array);
		return (K[]) array.toArray(a);
	}
	
	private ArrayList<K> recursivePosOrder(BSTNode<K, V> node, ArrayList<K> array){
		if(!node.isEmpty()){
			recursivePosOrder(node.left, array);
			recursivePosOrder(node.right, array);
			array.add(node.key);
		}
		return array;
	}

	@Override
	public int size() {
		return size(root);
	}
	
	private int size(BSTNode<K,V> node){
		int result = 0;
		if(!node.isEmpty()){
			result = 1 + size(node.left) + size(node.right);
		}
		return result;
	}
}
