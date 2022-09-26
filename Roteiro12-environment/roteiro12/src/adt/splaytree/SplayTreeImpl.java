package adt.splaytree;

import adt.avltree.AVLTreeImpl;
import adt.bst.BSTNode;

public class SplayTreeImpl<K extends Comparable<? super K>, V> extends AVLTreeImpl<K, V> implements
		SplayTree<K, V> {

	private void splay(BSTNode<K,V> node){
        if((node.getParent() != null)){
        	if(node.equals(getRoot())){
            }
        else if(node.getParent().equals(getRoot())){
        	if(node.equals(node.getParent().getLeft())){
        		rightRotation(root);
            }else{
                leftRotation(root);
            }
        }else{
        	BSTNode<K, V> pai = node.getParent();
            BSTNode<K, V> avo = pai.getParent();
            if(node.equals(pai.getLeft()) && pai.equals(avo.getLeft())){
            	rightRotation(avo);
                rightRotation(pai);
                }else if (node.equals(pai.getRight()) && pai.equals(avo.getRight())){
                	leftRotation(avo);
                	leftRotation(pai);
                }else if(node.equals(pai.getRight()) && pai.equals(avo.getLeft())){
                    leftRotation(pai);
                    rightRotation(avo);
                }else if(node.equals(pai.getLeft()) && pai.equals(avo.getRight())){
                	rightRotation(pai);
                    leftRotation(avo);
                }
            splay(node);
        	}
        }
	 }
	 
	@Override
	public void insert(K chave, V valor){
		recursiveInsertion2(root, chave, valor);
		splay(search(root, chave));
	}
	
	@Override
	public BSTNode<K,V> search(K chave){
		BSTNode<K,V> node = search(root, chave);
		if(!node.isEmpty()){
			splay(node);
		}else{
			splay(node.getParent());	
		}
		return search(root,chave);
	}

	@Override
	public void remove(K chave) {
		BSTNode<K, V> node = search(root,chave);
		removeAux(node);
		if(!node.equals(root)){
			splay(node.getParent());
		}
	}
}