package adt.avltree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;

public class AVLTreeImpl<T extends Comparable<T>> 
    extends BSTImpl<T> implements AVLTree<T> {

	//TODO Do not forget: you must override the methods insert and remove conveniently.

	//AUXILIARY
	protected int calculateBalance(BSTNode<T> node){
		// TODO Auto-generated method stub
		return 0;
	}
	
	//AUXILIARY
	protected void rebalance(BSTNode<T> node){
		// TODO Auto-generated method stub
	}
	
	//AUXILIARY
	protected void rebalanceUp(BSTNode<T> node){
			// TODO Auto-generated method stub
	}
	
	//AUXILIARY
	protected void leftRotation(BSTNode<T> node){
		// TODO Auto-generated method stub
	}
	
	//AUXILIARY
	protected void rightRotation(BSTNode<T> node){
		// TODO Auto-generated method stub
	}
	
}
