package adt.bst;

import java.util.ArrayList;

/**
 * Arvore BST, elementos menores que o seu pai sao filhos a esquerda e elementos 
 * maiores que seu pai sao filhos a direita
 * 
 * @author Julio Andherson, Igor Candeira, Flavia Gangorra, Adilson Junior
 *
 * 
 */
public class BSTImpl<K extends Comparable<? super K>, V> implements BST<K, V> {

	protected  BSTNode<K,V> root;
	
	/**
	 * Construtor default, inicializa a root
	 */
	public BSTImpl() {
		root = new BSTNode<K, V>();
	}

	/**
	 * Metodo de BSTImpl, que retorna a raiz da BST.
	 */
	public BSTNode<K, V> getRoot(){
		return this.root;
	}
	
	/**
	 * Metodo de BSTImpl, que retorna se uma arvore BST esta 
	 * vazia.
	 */
	@Override
	public boolean isEmpty() {
		return root.isEmpty();
	}

	/**
	 * Metodo de BSTImpl, que retorna a altura de ums BST.
	 */
	@Override
	public int height() {
		return height(getRoot());
	}
	
	/**
	 * Calcula a Altura
	 * @param node
	 * @return altura
	 */
	protected int height(BSTNode<K, V> node){
		int height = -1;
		if(!node.isEmpty()){
			if(node.isLeaf()){
				height = 0;
			}
			int heightLeft = height(node.left);
			int heightRight = height(node.right);
			height = 1 + max(heightLeft, heightRight);
		}
		return height;
	}
	
	/**
	 * Metodo Auxiliar da altura, verifica se o maior eh LEFT ou RIGHT
	 * @param heightLeft
	 * @param heightRight
	 * @return Altura do no
	 */
	private int max(int heightLeft, int heightRight) {
		int result = heightLeft;
		if(heightRight > result){
			result = heightRight;
		}
		return result;
	}

	/**
	 * Procura uma chave
	 * @param key
	 * @return o no que contem a chave
	 */
	@Override
	public BSTNode<K,V> search(K key) {
		return search(root, key);
	}

	/**
	 * Procura uma chave
	 * @param node
	 * @param key
	 * @return o no que contem a chave
	 */
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
	
	/**
	 * Metodo  Insert
	 * @param key
	 * @param value
	 */
	@Override
	public void insert(K key, V value) {
		insertRecursivo(getRoot(), key, value);

	}
	
	/**
	 * Metodo Auxiliar do Insert
	 * @param node
	 * @param key
	 * @param value
	 */
	protected void insertRecursivo(BSTNode<K, V> node, K key, V value){
		if (node.isEmpty()) {
			node.key = key;
			node.value = value;
			node.left = new BSTNode<K, V>();
			node.left.parent = node;
			node.right = new BSTNode<K, V>();
			node.right.parent = node;
		} else {
			if (key.compareTo(node.key) < 0) {
				insertRecursivo(node.left, key, value);
			} else if (key.compareTo(node.key) > 0) {
				insertRecursivo(node.right, key, value);
			}
		}
	}
	
	/**
	 * Procura o maior elemento da arvore
	 * @return o maior
	 */
	public BSTNode<K, V> maximun(){
		return maximum(getRoot());
	}
	/**
	 * Procura o maior elemento da arvore
	 * @return o maior
	 */
	
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
	
	/**
	 * Procura o menor elemento da arvore
	 * @return o menor
	 */
	public BSTNode<K, V> minimum(){
		return minimum(getRoot());
	}
	
	/**
	 * Procura o menor elemento da arvore
	 * @return o menor
	 */
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

	/**
	 * Metodo que retorna um no que seja sucessor do no passado como parametro, 
	 * ou seja, o no com chave de valor sucessor a chave do no passado
	 */
	@Override
	public BSTNode<K, V> sucessor(BSTNode<K, V> node) {
		BSTNode<K, V> result = null;
		
		BSTNode<K, V> auxNode = search(node.getKey());

		if (!auxNode.getRight().isEmpty()) {
			result = minimum(auxNode.getRight());
		} else {
			result = auxNode.getParent();//pai
			while (result != null && auxNode.equals(result.getRight())) { 
				auxNode = result;
				result = result.getParent();
			}
		}		
		return result;
	}

	/**
	 * 
	 * Metodo que retorna um no que seja predecessor do no passado como parametro, 
	 * ou seja, o no com chave de valor predecessor a chave do no passado
	 */
	@Override
	public BSTNode<K, V> predecessor(BSTNode<K, V> node) {
		BSTNode<K, V> result = null;
		
		BSTNode<K, V> auxNode = search(node.getKey());

		if (!auxNode.getLeft().isEmpty()) {
			result = maximum(auxNode.getLeft());
		} else {
			result = auxNode.getParent();

			while (result != null && auxNode.equals(result.getLeft())) {
				auxNode = result;
				result = result.getParent();
			}
		}
		
		return result;

	}

	/**
	 * * Metodo Remove, remove um  no de uma BST
	 */
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

	/**
	 * Retorna um array com os elementos de uma BST em preOrder, 
	 * isto eh, raiz, elementos a esquerda depois elementos a direita.
	 */
	@Override
	public K[] preOrder() {
		
		ArrayList<K> array = new ArrayList<K>();
		Comparable[] a = new Comparable[size()];
		
		array = preOrderRecursivo(root, array);
		return (K[]) array.toArray(a);
	}
	
	/**
	 * toArray de todos elementos da arvore
	 * @param node
	 * @param array
	 * @return ArrayList de elementos
	 */
	private ArrayList<K> preOrderRecursivo(BSTNode<K, V> node, ArrayList<K> array){
		if(!node.isEmpty()){
			array.add(node.key);
			preOrderRecursivo(node.left, array);
			preOrderRecursivo(node.right, array);
		}
		
		return array;
	}

	/**
	 * Retorna um array com os elementos de uma BST em Order, 
	 * isto eh, elementos a esquerda, raiz e depois elementos a direita.
	 */
	@Override
	public K[] order() {
		ArrayList<K> array = new ArrayList<K>();
		Comparable[] a = new Comparable[size()];
		
		array = orderRecursivo(root, array);
		return (K[]) array.toArray(a);
	}
	
	/**
	 * toArray de todos elementos da arvore
	 * @param node
	 * @param array
	 * @return ArrayList de todos elementos
	 */
	private ArrayList<K> orderRecursivo(BSTNode<K, V> node, ArrayList<K> array){
		if(!node.isEmpty()) {
			orderRecursivo(node.left, array);
			array.add(node.key);
			orderRecursivo(node.right, array);
		}
		return array;
	}

	/**
	 * Retorna um array com os elementos de uma BST em postOrder, 
	 * isto eh, elementos a esquerda, elementos a direita e depois a raiz.
	 */
	@Override
	public K[] postOrder() {
		ArrayList<K> array = new ArrayList<K>();
		Comparable[] a = new Comparable[size()];
		
		array = posOrderRecursivo(root, array);
		return (K[]) array.toArray(a);
	}
	
	/**
	 * toArray de todos elementos
	 * @param node
	 * @param array
	 * @return ArrayList de todos elementos
	 */
	private ArrayList<K> posOrderRecursivo(BSTNode<K, V> node, ArrayList<K> array){
		if(!node.isEmpty()){
			posOrderRecursivo(node.left, array);
			posOrderRecursivo(node.right, array);
			array.add(node.key);
		}
		return array;
	}

	/**
	 * Retorna a quantidade de nos armazenados pela BST, ou seja, seu tamanho.
	 */
	@Override
	public int size() {
		return size(root);
	}
	
	/**
	 * Calcula o tamanho da arvore
	 * @param node
	 * @return o tamanho
	 */
	private int size(BSTNode<K,V> node){
		int result = 0;
		if(!node.isEmpty()){
			result = 1 + size(node.left) + size(node.right);
		}
		return result;
	}
	
	/**
	 * Metodo que encontra um no em uma BST.
	 * @param treeNode
	 * @param node
	 * @return
	 * 		o no encontrado.
	 */
	protected BSTNode<K, V> findNode(BSTNode<K, V> treeNode, BSTNode<K, V> node) {
		BSTNode<K, V> result = null;
		
		if(!treeNode.isEmpty() && !node.isEmpty()){
			if(treeNode.equals(node)){
				result = treeNode;
			}else if(node.key.compareTo(treeNode.key) < 0) {
				result = findNode(treeNode.left, node);
			}else if(node.key.compareTo(treeNode.key) > 0) {
				result = findNode(treeNode.right, node);
			}
		}
		return result;
	}
}