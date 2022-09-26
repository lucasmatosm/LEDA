package adt.splaytree;

import adt.avltree.AVLTreeImpl;
import adt.bst.BSTNode;

public class SplayTreeImpl<T extends Comparable<T>> extends AVLTreeImpl<T> implements
		SplayTree<T> {
	

	private void splay(BSTNode<T> node){
		//se o no eh a raiz nao precisa fazer nada	
		while(!node.getData().equals(root.getData())){
			
			//se o pai do no eh raiz, faz apenas uma rotacao
			if(node.getParent().equals(root)){
				
				//faz a rotacao pra direita
				if(root.getLeft().equals(node)){
					rightRotation((BSTNode<T>) node.getParent());
				}else{//rotacao pra esquerda
					leftRotation((BSTNode<T>) node.getParent());
				}
				
				
			}else{
				//se no eh filho esquerdo
				if(node.equals(node.getParent().getLeft())){
					
					//e pai do no tbm eh filho esquerdo
					if(node.getParent().equals(node.getParent().getParent().getLeft())){
						rightRotation((BSTNode<T>) node.getParent().getParent());
						
					}else{//pai eh filho direito
						leftRotation((BSTNode<T>) node.getParent().getParent());
					}
					
					rightRotation((BSTNode<T>) node.getParent());
					
				}else{//no eh filho direito
					//pai tbm eh filho direito
					if(node.getParent().equals(node.getParent().getParent().getRight())){
						leftRotation((BSTNode<T>) node.getParent().getParent());
						
					}else{//pai eh filho esquerdo
						rightRotation((BSTNode<T>) node.getParent().getParent());
					}
					
					leftRotation((BSTNode<T>) node.getParent());
				}
			}
		}
	}
	@Override
	public void insert(T valor) {
		insertRecursive(root, valor);
		splay(searchRecursive(root, valor));
	}

	private void insertRecursive(BSTNode<T> node, T element) {
		if (node.isEmpty()) {
			node.setData(element);
			node.setLeft(new BSTNode<T>());
			node.setRight(new BSTNode<T>());
			node.getLeft().setParent(node);
			node.getRight().setParent(node);
		} 
		
		else {
			if (node.getData().compareTo(element) > 0){
				insertRecursive((BSTNode<T>)node.getLeft(), element);
			}
			else{
				insertRecursive((BSTNode<T>) node.getRight(), element);
			}
		}
	}
	
	@Override
	public BSTNode<T> search(T chave) {
		BSTNode<T> no = searchRecursive(root, chave);
		if (!no.isEmpty()) {
			splay(no);
		} else {
			splay((BSTNode<T>) no.getParent());
		}
		return searchRecursive(root, chave);
	}
	
    protected BSTNode<T> searchRecursive(BSTNode<T> node, T key) {
        BSTNode<T> result = node;
        if (key != null) {
            if (!node.isEmpty()) {
                if (key.compareTo(node.getData()) == 0) {
                    result = node;
                } else if (key.compareTo(node.getData()) < 0) {
                    result = searchRecursive((BSTNode<T>) node.getLeft(), key);
                } else {
                    result = searchRecursive((BSTNode<T>) node.getRight(), key);
                }
            }
        }

        return result;
    }

	@Override
	public void remove(T chave) {
		BSTNode<T> node = searchRecursive(root, chave);
		remove((T) node);
		if (!node.equals(root)) {
			splay((BSTNode<T>) node.getParent());
		}
	}
}
