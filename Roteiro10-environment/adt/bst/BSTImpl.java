package adt.bst;

public class BSTImpl<T extends Comparable<T>> implements BST<T> {

	protected  BSTNode<T> root;
	
	
	public BSTImpl() {
		root = new BSTNode<T>();
	}

	public BSTNode<T> getRoot(){
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
	public BSTNode<T> search(T element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(T element) {
		// TODO Auto-generated method stub

	}

	@Override
	public BSTNode<T> maximum() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BSTNode<T> minimum() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BSTNode<T> sucessor(T element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BSTNode<T> predecessor(T element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(T element) {
		// TODO Auto-generated method stub

	}

	@Override
	public T[] preOrder() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T[] order() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T[] postOrder() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		return size(root);
	}
	private int size(BSTNode<T> node){
		int result = 0;
		if(!node.isEmpty()){
			result = 1 + size((BSTNode<T>)node.getLeft()) + size((BSTNode<T>)node.getRight());
		}
		return result;
	}

}
