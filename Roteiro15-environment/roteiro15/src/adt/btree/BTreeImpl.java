package adt.btree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BTreeImpl<K extends Comparable<? super K>, V> implements
		BTree<K, V> {

	protected BNode<K,V> root;
	protected int order;
	
	public BTreeImpl(int order) {
		this.order = order;
		this.root = new BNode<K,V>(order);
	}
	
	@Override
	public BNode<K, V> getRoot() {
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
	private int height(BNode<K,V> node){
		int resp = -1;
		if(!node.isEmpty()){
			if(node.isLeaf()){
				resp = 0;
			}else{
				resp = 1 + height(node.getChildrenAt(0));
				//a idéia é pegar a algura do primeiro filho apenas e somar com 1.
				//Se voce usar array vai ter que ajustar o codigo aqui.
			}
		}
		return resp;
	}
	@Override
	public BNode<K, V>[] depthLeftOrder() {
		List<BNode<K, V>> array = new ArrayList<BNode<K, V>>();
        
        depthLeftOrder(root, array);
        BNode<K, V>[] a = new BNode[array.size()];
        for (int i = 0; i < array.size(); i++) {
			a[i] = array.get(i);
		}
        
        return  a;
	}
	private List<BNode<K, V>> depthLeftOrder(BNode<K,V> node, List<BNode<K, V>> array){
        if(!node.isEmpty()){
        	if (node.getParent() == null) {
				array.add(node);
			}
        	for(int i = 0;i<node.getChildren().size();i++){
        		array.add(node.getChildrenAt(i));
        		depthLeftOrder(node.getChildrenAt(i), array);
        	}
        }
        return array;
}

	@Override
	public int size() {
		return size(root);
    }
    public int size(BNode<K,V> node){
            int resp  = 0;
            if(!node.isEmpty()){
                    resp += node.size();
                    for(int i = 0;i<node.getChildren().size();i++){
                            resp += size(node.getChildrenAt(i)) ;
                    }
            }
            return resp;
    }
	

	@Override
	public BNodePosition<K, V> search(K key) {
		return search(root, key);
	}
	
	private BNodePosition<K, V> search(BNode<K, V> node, K key){
        int i = 0;
        while((i < node.size()) && (node.getElementAt(i).compareTo(new Storable<K, V>(key, null)) < 0)){
        	i++;
        }
        if((i < node.size()) && (node.getElementAt(i).compareTo(new Storable<K, V>(key, null)) == 0)){
        	return (new BNodePosition<K, V>(node, i));
        }
        if(node.isLeaf()){
        	return new BNodePosition<K, V>();
        }
        return search(node.getChildrenAt(i), key);
	}

	@Override
	public void insert(K key, V value) {
		Storable<K, V> element = new Storable<K, V>(key, value);
		BNode<K, V> aux = findLeaf(root, key);
		aux.addElement(element);
		while (aux.getParent() != null) {
			if (aux.overFlow()) {
				aux.split();
				aux.promote();
			}
			aux = aux.getParent();
		}
		
		if (aux.overFlow()) {
			BNode<K, V> node = new BNode<K, V>(aux.getMaxChildren());
			node.addChild(0, aux);
			aux.setParent(node);
			root = node;
			aux.split();
			aux.promote();
		}
	}
	
	private BNode<K, V> findLeaf(BNode<K, V> node, K key){
		int i = 0;
        while((i < node.size()) && (node.getElementAt(i).compareTo(new Storable<K, V>(key, null)) < 0)){
        	i++;
        }
        if(node.isLeaf()){
        	return node;
        }
        return findLeaf(node.getChildrenAt(i), key);
	}

	//NAO PRECISA IMPLEMENTAR OS METODOS ABAIXO
	@Override
	public BNode<K, V> maximum(BNode<K, V> node) {
		//NAO PRECISA IMPLEMENTAR
		return null;
	}
	@Override
	public BNode<K, V> minimum(BNode<K, V> node) {
		//NAO PRECISA IMPLEMENTAR
		return null;
	}
	@Override
	public void remove(K key) {
		//NAO PRECISA IMPLEMENTAR

	}
}
