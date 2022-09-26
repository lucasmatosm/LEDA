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
		return height(getRoot());
	}
	
	private int maior(int heightLeft, int heightRight) {
		int result = heightLeft;
		if(heightRight > result){
			result = heightRight;
		}
		return result;
	}
	
	protected int height(BSTNode<K, V> node){
		int height = -1;
		if(!node.isEmpty()){
			int heightLeft = height(node.left);
			int heightRight = height(node.right);
			height = 1 + maior(heightLeft, heightRight);
		}
		return height;
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
		insertAuxiliar(getRoot(), key, value);

	}
	
	protected void insertAuxiliar(BSTNode<K, V> node, K key, V value){
		if (node.isEmpty()) {
			node.key = key;
			node.value = value;
			node.left = new BSTNode<K, V>();
			node.left.parent = node;
			node.right = new BSTNode<K, V>();
			node.right.parent = node;
		} else {
			if (key.compareTo(node.key) < 0) {
				insertAuxiliar(node.left, key, value);
			} else if (key.compareTo(node.key) > 0) {
				insertAuxiliar(node.right, key, value);
			}
		}
	}

	@Override
	public BSTNode<K, V> maximum(BSTNode<K, V> node) {
		BSTNode<K, V> aux = null;
		if (!node.isEmpty()) {
			aux = node;
			while (!aux.getRight().isEmpty()) {
				aux = aux.getRight();
			}
		}
		return aux;
	}

	@Override
	public BSTNode<K, V> minimum(BSTNode<K, V> node) {
		BSTNode<K, V> aux = null;
		if (!node.isEmpty()) {
			aux = node;
			while (!aux.getLeft().isEmpty()) {
				aux = aux.getLeft();
			}
		}
		return aux;
	}

	@Override
	public BSTNode<K, V> sucessor(BSTNode<K, V> node) {
		BSTNode<K, V> result = null;
		
		BSTNode<K, V> auxNode = search(node.getKey());

		if (!auxNode.getRight().isEmpty()) {
			result = minimum(auxNode.getRight());
		} else {
			result = auxNode.getParent();
			while (result != null && auxNode.equals(result.getRight())) { 
				auxNode = result;
				result = result.getParent();
			}
		}		
		return result;
	}

	@Override
	public BSTNode<K, V> predecessor(BSTNode<K, V> node) {
		BSTNode<K, V> result = null;
		BSTNode<K, V> noAuxiliar = search(node.getKey());

		if (!noAuxiliar.getLeft().isEmpty()) {
			result = maximum(noAuxiliar.getLeft());
		} else {
			result = noAuxiliar.getParent();

			while (result != null && noAuxiliar.equals(result.getLeft())) {
				noAuxiliar = result;
				result = result.getParent();
			}
		}
		return result;
	}

	@Override
	public void remove(K key) {
		BSTNode<K, V> node = new BSTNode<K, V>();
		node.key = key;
		node = search(getRoot(), node.key);
		remove(node);
	}
	protected void remove(BSTNode<K, V> node) {
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
				remove(sucessor);
			}
		}		
	}

	@Override
	public K[] preOrder() {
		ArrayList<K> array = new ArrayList<K>();
		Comparable[] a = new Comparable[size()];
		
		array = preOrderRecursivo(root, array);
		return (K[]) array.toArray(a);
	}
	private ArrayList<K> preOrderRecursivo(BSTNode<K, V> node, ArrayList<K> array){
		if(!node.isEmpty()){
			array.add(node.key);
			preOrderRecursivo(node.left, array);
			preOrderRecursivo(node.right, array);
		}
		return array;
	}

	@Override
	public K[] order() {
		ArrayList<K> array = new ArrayList<K>();
		Comparable[] a = new Comparable[size()];
		
		array = orderAuxiliar(root, array);
		return (K[]) array.toArray(a);
	}
	private ArrayList<K> orderAuxiliar(BSTNode<K, V> node, ArrayList<K> array){
		if(!node.isEmpty()) {
			orderAuxiliar(node.left, array);
			array.add(node.key);
			orderAuxiliar(node.right, array);
		}
		return array;
	}


	@Override
	public K[] postOrder() {
		ArrayList<K> array = new ArrayList<K>();
		Comparable[] a = new Comparable[size()];
		
		array = postOrderAuxiliar(root, array);
		return (K[]) array.toArray(a);
	}
	private ArrayList<K> postOrderAuxiliar(BSTNode<K, V> node, ArrayList<K> array){
		if(!node.isEmpty()){
			postOrderAuxiliar(node.left, array);
			postOrderAuxiliar(node.right, array);
			array.add(node.key);
		}
		return array;
	}

	@Override
	public int size() {
		return sizeAuxiliar(root);
	}
	private int sizeAuxiliar(BSTNode<K,V> node){
		int result = 0;
		if(!node.isEmpty()){
			result = 1 + sizeAuxiliar(node.left) + sizeAuxiliar(node.right);
		}
		return result;
	}
}
