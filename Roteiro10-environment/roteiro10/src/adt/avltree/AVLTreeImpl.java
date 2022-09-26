package adt.avltree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;

public class AVLTreeImpl<K extends Comparable<? super K>, V> 
    extends BSTImpl<K, V> implements AVLTree<K, V> {
	
	protected BSTNode<K,V> node;
	
	public AVLTreeImpl(){
		node = new BSTNode<K, V>();
	}
	
	//AUXILIARY
	protected int calculateBalance(BSTNode<K, V> node){
		if(!node.isEmpty()){
			return heightAux(node.getLeft()) - heightAux(node.getRight());
		}
		return 0;
	}
	
	private boolean isBalanced(BSTNode<K, V> no){
		if(!node.isEmpty()){
			if((Math.abs(calculateBalance(node)) < 2) && (isBalanced(node.getRight()) && (isBalanced(node.getLeft())))){
				return true;
			}
		}
		return false;
	}
	
	//AUXILIARY
	protected void rebalance(BSTNode<K, V> node){
		if((calculateBalance(node) > 1)){ 
			if(calculateBalance(node.getLeft()) < -1){
				leftRotation(node.getLeft());
				rightRotation(node);
			} else { 
				rightRotation(node);
			}
		} else if ((calculateBalance(node) < -1)){ 
			if(calculateBalance(node.getRight()) > 1){
				rightRotation(node.getRight());
				leftRotation(node);
			} else{
				leftRotation(node);
			}
		}
	}
				
	//FAZ OS SWAPS DO NO1 e NO2
	private void trocaNos(BSTNode<K, V> node1, BSTNode<K, V> node2){
		node1.setKey(node2.getKey());
		node1.setValue(node2.getValue());
		node1.setRight(node2.getRight());
		node1.setLeft(node2.getLeft());
		node1.setParent(node2.getParent());
	}
	
	//AUXILIARY
	protected void leftRotation(BSTNode<K, V> node){
		BSTNode<K, V> Pivot = new BSTNode<K, V>();
		trocaNos(Pivot, node.getRight());
		trocaNos(node.getRight(), Pivot.getLeft());
		trocaNos(Pivot.getLeft(), node);
		trocaNos(node, Pivot);
	}
	
	//AUXILIARY
	protected void rightRotation(BSTNode<K, V> node){
		BSTNode<K, V> Pivot = new BSTNode<K, V>();
		trocaNos(Pivot, node.getLeft());
		trocaNos(node.getLeft(), Pivot.getRight());
		trocaNos(Pivot.getRight(), node);
		trocaNos(node, Pivot);
	}
	
	@Override
	public void insert(K key, V value) {
		BSTNode<K, V> aux = this.root;
		if (isEmpty()) {
			aux.setKey(key);
			aux.setValue(value);
			aux.setLeft(new BSTNode<K, V>());
			aux.setRight(new BSTNode<K, V>());
			aux.getLeft().setParent(aux);
			aux.getRight().setParent(aux);
		}else{
			while(!aux.isEmpty()) {
				rebalance(aux);
				if(key.compareTo(aux.getKey()) > 0){
					aux = aux.getRight();
				}else{
					aux = aux.getLeft();
				}
			}
			aux.setKey(key);
			aux.setValue(value);
			aux.setLeft(new BSTNode<K, V>());
			aux.setRight(new BSTNode<K, V>());
			aux.getLeft().setParent(aux);
			aux.getRight().setParent(aux);
		}
	}
	
	private void rebalanceUp(BSTNode<K, V> node) {
		BSTNode<K, V> parent = node.getParent();
		while(parent != null){
			rebalance(parent);
			parent.setParent(parent.getParent());
		}
	}
	
	@Override
	public void remove(K key) {
		BSTNode<K, V> node = searchAux(key);
		removeAux(node);
	}
	
	private void removeAux(BSTNode<K,V> node){
		if (!node.isEmpty()){
			if (node.getLeft().isEmpty() && node.getRight().isEmpty()){
				node.setKey(null);
				node.setValue(null);
				rebalanceUp(node);
			} else{				
				if (!node.getLeft().isEmpty() || !node.getRight().isEmpty()){
					if (!node.equals(root)){
						if (node.getParent().getLeft().equals(node)){
							if (!node.getLeft().isEmpty()){
								node.getParent().setLeft(node.getLeft());
							} else{
								node.getParent().setLeft(node.getRight());
							}
						} else {
							if (!node.getLeft().isEmpty()){
								node.getParent().setRight(node.getLeft());
							} else{
								node.getParent().setRight(node.getRight());
							}						
						}
					} else{
						if (!root.getLeft().isEmpty() && root.getRight().isEmpty()){
							root = root.getLeft();
							root.setParent(null);
						} else{
							if (root.getLeft().isEmpty() && !root.getRight().isEmpty()) {
								root = root.getRight();
								root.setParent(null);
								rebalanceUp(node);
							} else{
								BSTNode<K, V> sucessor = sucessor(node);
								node.setKey(sucessor.getKey());
								node.setValue(sucessor.getValue());
								removeAux(sucessor);													
							}
						}
					}	
				} else{
					if (node.getParent() == null){
						node.setKey(null);
						node.setValue(null);
					}
					BSTNode<K, V> sucessor = sucessor(node);
					node.setKey(sucessor.getKey());
					node.setValue(sucessor.getValue());
					removeAux(sucessor);					
				}
			}
		}
	}
}
