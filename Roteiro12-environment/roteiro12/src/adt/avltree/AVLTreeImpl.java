package adt.avltree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;

public class AVLTreeImpl<K extends Comparable<? super K>, V> 
    extends BSTImpl<K, V> implements AVLTree<K, V> {

	//AUXILIARY
	protected int calculateBalance(BSTNode<K, V> node) {
		int result = 0;
		if (node != null && !node.isEmpty()) {
			result = height(node.getLeft()) - height(node.getRight());
		}
		return result;
	}

	// AUXILIARY
	protected void rebalance(BSTNode<K, V> node) {
		int balance = calculateBalance(node);

		if (balance < -1) {
			int childBalance = calculateBalance(node.getRight());

			if (childBalance > 0) {
				rightRotation(node.getRight());
				leftRotation(node);
			} else {
				leftRotation(node);
			}
		} else if (balance > 1) {
			int childBalance = calculateBalance(node.getLeft());

			if (childBalance < 0) {
				leftRotation(node.getLeft());
				rightRotation(node);
			} else {
				rightRotation(node);
			}
		}
	}
	
	//AUXILIARY
	protected void leftRotation(BSTNode<K, V> node){
		 BSTNode<K, V> pivot = new BSTNode<K, V>();
         if(!node.isEmpty() && node!=null && node.getRight().getRight()!=null){
        
                 pivot.setKey(node.getRight().getKey());
                 pivot.setValue(node.getRight().getValue());
                 pivot.setLeft(node.getRight().getLeft());
                 pivot.getLeft().setParent(pivot);
                 pivot.setRight(node.getRight().getRight());
                 pivot.getRight().setParent(pivot);

                 node.getRight().setKey(pivot.getLeft().getKey());
                 node.getRight().setValue(pivot.getLeft().getValue());
                 node.getRight().setLeft(pivot.getLeft().getLeft());
                 node.getRight().setRight(pivot.getLeft().getRight());
                 node.getRight().setParent(node);

                 pivot.getLeft().setKey(node.getKey());
                 pivot.getLeft().setValue(node.getValue());
                 pivot.getLeft().setLeft(node.getLeft());
                 pivot.getLeft().getLeft().setParent(pivot.getLeft());
                 pivot.getLeft().setRight(node.getRight());
                 pivot.getLeft().getRight().setParent(pivot.getLeft());
                 pivot.getLeft().setParent(pivot);

                 node.setKey(pivot.getKey());
                 node.setValue(pivot.getValue());
                 node.setLeft(pivot.getLeft());
                 node.getLeft().setParent(node);
                 node.setRight(pivot.getRight());
                 node.getRight().setParent(node);
                 node.setParent(node.getParent());     
         }
	}
	
	//AUXILIARY
	protected void rightRotation(BSTNode<K, V> node){
		  BSTNode<K, V> pivot = new BSTNode<K, V>();
          if(!node.isEmpty() && node!=null && node.getLeft().getLeft()!=null){
        	 
                  pivot.setKey(node.getLeft().getKey());
                  pivot.setValue(node.getLeft().getValue());
                  pivot.setLeft(node.getLeft().getLeft());
                  pivot.getLeft().setParent(pivot);
                  pivot.setRight(node.getLeft().getRight());
                  pivot.getRight().setParent(pivot);
                 
                  node.getLeft().setKey(pivot.getRight().getKey());
                  node.getLeft().setValue(pivot.getRight().getValue());
                  node.getLeft().setLeft(pivot.getRight().getLeft());
                  node.getLeft().setRight(pivot.getRight().getRight());
                  node.getLeft().setParent(node);

                  pivot.getRight().setKey(node.getKey());
                  pivot.getRight().setValue(node.getValue());
                  pivot.getRight().setLeft(node.getLeft());
                  pivot.getRight().getLeft().setParent(pivot.getRight());
                  pivot.getRight().setRight(node.getRight());
                  pivot.getRight().getRight().setParent(pivot.getRight());
                  pivot.getRight().setParent(pivot);

                  node.setKey(pivot.getKey());
                  node.setValue(pivot.getValue());
                  node.setLeft(pivot.getLeft());
                  node.getLeft().setParent(node);
                  node.setRight(pivot.getRight());
                  node.getRight().setParent(node);
                  node.setParent(node.getParent());    
          }
	}
	
	protected void rebalanceUp(BSTNode<K, V> node) {
		BSTNode<K, V> parent = node.getParent();
		
		while(parent != null && !parent.isEmpty()){
			rebalance(parent);
			parent = parent.getParent();
		}
	}
		
	@Override
	public void insert(K key, V value) {
		recursiveInsertion(root, key, value);
	}

	private void recursiveInsertion(BSTNode<K, V> node, K key, V value) {
		if (node.isEmpty()) {
			node.setKey(key);
			node.setValue(value);
			node.setLeft(new BSTNode<K, V>());
			node.getLeft().setParent(node);
			node.setRight(new BSTNode<K, V>());
			node.getRight().setParent(node);
			
			
		} else {
			if (key.compareTo(node.getKey()) < 0) {
				recursiveInsertion(node.getLeft(), key, value);
			} else if (key.compareTo(node.getKey()) > 0) {
				recursiveInsertion(node.getRight(), key, value);
			}
		}
		rebalance(node);
	} 
	
	@Override
	public void remove(K key) {
		BSTNode<K, V> node = new BSTNode<K, V>();
		node.setKey(key);
		node = findNode(root, node);

		remove(node);
	}
	
	private void remove(BSTNode<K, V> node) {
		if (node != null && !node.isEmpty()) {
			if (node.getLeft().isEmpty() && node.getRight().isEmpty()) {
				node.setKey(null);
				node.setValue(null);
				node.setLeft(null);
				node.setRight(null);

				rebalanceUp(node);

			} else if ((!node.getLeft().isEmpty() && node.getRight().isEmpty()) || (node.getLeft().isEmpty() && !node.getRight().isEmpty())) {
				if (!node.equals(root) || (node.equals(root) && node.getParent() != null && node.getParent().equals(root))) {
					if (!node.getParent().getLeft().isEmpty() && node.getParent().getLeft().equals(node)) {
						if (!node.getLeft().isEmpty()) {
							node.getParent().setLeft(node.getLeft());
						} else {
							node.getParent().setLeft(node.getRight());
						}
					} else {
						if (!node.getLeft().isEmpty()) {
							node.getParent().setRight(node.getLeft());
						} else {
							node.getParent().setRight(node.getRight());
						}
					}
				} else {
					if (root.getLeft().isEmpty()) {
						root = root.getRight();
					} else if (root.getRight().isEmpty()) {
						root = root.getLeft();
					}
				}
				rebalanceUp(node);
			} else {
				BSTNode<K, V> sucessor = sucessor(node);
				node.setKey(sucessor.getKey());
				node.setValue(sucessor.getValue());

				remove(sucessor);
			}
		}
	}
}