package adt.bst;

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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public K search(K key) {
		if(root.isEmpty() || key != root.getKey()){
			return key;
		}
		if(key.compareTo(root.key) < 0){
			return search(root.left.key);
		}else{
			return search(root.right.key);
		}
	}

	@Override
	public void insert(K key, V value) {
		insertNode(root, key, value);
	}
	
	private void insertNode(BSTNode<K, V> no, K chave, V valor){
		if(no.isEmpty()){
			no.setKey(chave);
			no.setValue(valor);
			no.setLeft(new BSTNode<K, V>());
			no.setRight(new BSTNode<K, V>());
			no.getRight().setParent(no);
			no.getLeft().setParent(no);
		}else{
			if (chave.compareTo(no.getKey()) < 0){
				insertNode(no.getLeft(), chave, valor);	
			}else if (chave.compareTo(no.getKey()) > 0){
				insertNode(no.getRight(), chave, valor);
			}
		}
	}

	@Override
	public BSTNode<K, V> maximum(BSTNode<K, V> node) {
		while(!node.getRight().isEmpty()){
			node = node.getRight();
		}
		return node;
	}

	@Override
	public BSTNode<K, V> minimum(BSTNode<K, V> node) {
		while (!node.getLeft().isEmpty()) {
			node = node.getLeft();
		}
		return node;
	}

	@Override
	public BSTNode<K, V> sucessor(BSTNode<K, V> node) {
		BSTNode<K,V> aux = null;
        if(!node.getRight().isEmpty()){
        	return minimum(node.getRight());
        }
        aux = node.getParent();
        while((!aux.isEmpty()) && (node == aux.getParent())){
        	node = aux;
        	aux = aux.getParent();
        }
        return aux;
    }
		
	@Override
	public BSTNode<K, V> predecessor(BSTNode<K, V> node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(K key) {
		// TODO Auto-generated method stub

	}

	@Override
	public K[] preOrder() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public K[] order() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public K[] postOrder() {
		// TODO Auto-generated method stub
		return null;
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
