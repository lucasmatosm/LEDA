package adt.btree;

public class BTreeImpl<T extends Comparable<T>> implements
		BTree<T> {

	protected BNode<T> root;
	protected int order;
	
	public BTreeImpl(int order) {
		this.order = order;
		this.root = new BNode<T>(order);
	}
	
	@Override
	public BNode<T> getRoot() {
		return this.root;
	}

	@Override
	public boolean isEmpty() {
		return this.root.isEmpty();
	}

	@Override
	public int height() {
		return height(this.root);
	}
	private int height(BNode<T> node){
		int resp = -1;
		if(!node.isEmpty()){
			if(node.isLeaf()){
				resp = 0;
			}else{
				//resp = 1 + height(node.children.get(0));
				//a idéia é pegar a algura do primeiro filho apenas e somar com 1.
				//Se voce usar array vai ter que ajustar o codigo aqui.
			}
		}
		return resp;
	}
	@Override
	public BNode<T>[] depthLeftOrder() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public BNodePosition<T> search(T element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(T element) {
		// TODO Auto-generated method stub

	}

	//NAO PRECISA IMPLEMENTAR OS METODOS ABAIXO
	@Override
	public BNode<T> maximum(BNode<T> node) {
		//NAO PRECISA IMPLEMENTAR
		return null;
	}
	@Override
	public BNode<T> minimum(BNode<T> node) {
		//NAO PRECISA IMPLEMENTAR
		return null;
	}
	@Override
	public void remove(T element) {
		//NAO PRECISA IMPLEMENTAR

	}

	

}
