package adt.btree;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Arvore B eh uma eh caracterizada por cada no ter mais de um elemento e alem disso, 
 * possui ordem que a partir dessa, eh determinado a quantidade de nos-filhos que um
 * no pode ter e a quantidade de elementos dentro de cada no.
 *
 * @author Julio Andherson, Igor Candeia, Flavia Gangorra, Adilson Junior
 *
 * @param <K> Chave
 * @param <V> Valor
 */
public class BTreeImpl<K extends Comparable<? super K>, V> implements
		BTree<K, V> {

	protected BNode<K,V> root;
	protected int order;
	protected List<BNode<K,V>> arrayNosRemovidos;
	protected int indice;
	
	/**
	 * Construtor passando a ordem da arvore
	 * @param order
	 */
	public BTreeImpl(int order) {
		this.order = order;
		this.root = new BNode<K,V>(order);
		this.arrayNosRemovidos = new ArrayList<BNode<K,V>>();
		this.indice = - 1;
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
	
	/**
	 * Metodo Auxiliar da altura
	 * @param No
	 * @return a altura da arvore
	 */
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
	/**
	 * Metodo Auxiliar do depthLeftOrder
	 * @param node
	 * @param array
	 * @return Lista de Elementos da arvore
	 */
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
	
	/**
	 * Metodo Auxiliar do tamanho
	 * @param node
	 * @return tamanho
	 */
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
	
	/**
	 * Metodo Auxiliar do Search
	 * @param node
	 * @param key
	 * @return o No onde a chave foi encontrada
	 */
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
	
	/**
	 * Procura a folha de um determinado no
	 * @param node
	 * @param key
	 * @return no-Folha
	 */
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

	@Override
	public BNode<K, V> maximum(BNode<K, V> node) {
		return node.getChildrenAt(node.getChildren().size()-1);
	}
	
	@Override
	public BNode<K, V> minimum(BNode<K, V> node) {
		return node.getChildrenAt(0);
	}
	
	/**
	 * Metodo que procura uma chave, e vai do root ate o ultimo no (independendo
	 * se a chave eh menor ou maior)
	 * @param key
	 * @return no onde esta a chave
	 */
	private BNode<K, V> searchAux(K key){
		BNode<K, V> resp = search(key).node;
		if(resp==null){
			BNode<K, V>[] aux = this.depthLeftOrder();
			for(int i = 0;i<aux.length;i++ ){
				if(aux[i].getElements().contains(new Storable<K, V>(key,null))){
					resp = aux[i];
				}
			}
		}
		return resp;
	}
	
	/**
	 * Pega o no sucessor da chave (no que contem os primeiros valores maiores que a chave)
	 * @param node
	 * @param key
	 * @return o no sucessor
	 */
	private BNode<K, V> sucessor(BNode<K,V> node, K key){
		LinkedList<BNode<K, V>> filhos = node.getChildren();
		for(BNode<K, V> filho : filhos){
			if(filho.getElementAt(0).key.compareTo(key) > 0){
				return filho;
			}
		}
		return null;
	}
	
	/**
	 * Pega o no antecessor da chave (no que contem os primeiros valores menores que a chave)
	 * @param node
	 * @param key
	 * @return o no antecessor
	 */
	private BNode<K, V> predecessor(BNode<K, V> node, K key){
		BNode<K, V> resp = null;
		LinkedList<BNode<K, V>> filhos = node.getChildren();
		for(BNode<K, V> filho :filhos){
			if(filho.getElementAt(filho.size()-1).key.compareTo(key)<0){
				resp = filho;
			}
		}
		return resp;
	}
	
	/**
	 * Troca chave de um no pela chave de outro no
	 * @param no1
	 * @param no2
	 * @param key1
	 * @param key2
	 */
	private void swap(BNode<K, V> no1 ,BNode<K, V> no2,Storable<K, V> key1,Storable<K, V> key2){
		Storable<K, V> tmp = key1;
		no1.getElements().set(no1.getElements().indexOf(key1),key2);
		no2.getElements().set(no2.getElements().indexOf(key2), tmp);
	}
	
	/**
	 * Intercalacao entre pai e filhos
	 * @param no
	 * @param key
	 */
	private void intercalar(BNode<K, V> no, Storable<K, V> key){
		BNode<K, V> preBNode = predecessor(no, key.key);
		BNode<K, V> sucBNode = sucessor(no, key.key);
		preBNode.addElement(key);
		preBNode.addElement(sucBNode.getElementAt(0));
		no.removeChild(sucBNode);
		no.removeElement(key);
	}
	
	/**
	 * Procura os irmãos do no
	 * @param no
	 * @return o irmao com elementos sobrando sem quebrar a propriedade
	 */
	private BNode<K, V> procuraIrmao(BNode<K, V> no){
		BNode<K, V> resp = null;
		int indiceNo = no.getParent().indexOfChild(no);
		if(minimum(no.getParent()).equals(no)){
			if(no.getParent().getChildrenAt(indiceNo + 1).size() > this.order/2){
				resp = no.getParent().getChildrenAt(indiceNo + 1);
			}
		}else if(maximum(no.getParent()).equals(no)){
			if(no.getParent().getChildrenAt(indiceNo - 1).size() > this.order/2){
				resp = no.getParent().getChildrenAt(indiceNo - 1);
			}
		}else{
			if(no.getParent().getChildrenAt(indiceNo - 1).size() > this.order/2){
				resp = no.getParent().getChildrenAt(indiceNo - 1);
			}else if(no.getParent().getChildrenAt(indiceNo + 1).size() > this.order/2){
				resp = no.getParent().getChildrenAt(indiceNo + 1);
			}
		}
		return resp;
	}
	
	/**
	 * Reorganiza as chaves do no para que satisfaca as propriedades
	 * @param no
	 */
	private void redistribuir(BNode<K, V> no){
		BNode<K, V> paiNo = no.getParent();
		BNode<K, V> noBuscado = procuraIrmao(no);
		if(paiNo.indexOfChild(noBuscado) > paiNo.indexOfChild(no)){
			Storable<K, V> temp = noBuscado.getElementAt(0);
			paiNo.addElement(temp);
			noBuscado.removeElement(0);
			Storable<K, V> temp2 = paiNo.getElementAt((paiNo.getElements().indexOf(temp) -1));
			no.addElement(temp2);
			paiNo.removeElement(temp2);	
			if(!no.isLeaf()){
				BNode<K, V> filhoBuscado = noBuscado.getChildrenAt(0);
				no.addChild(no.getChildren().size(), filhoBuscado);
				noBuscado.removeChild(filhoBuscado);
			}
		}else if(paiNo.indexOfChild(noBuscado) < paiNo.indexOfChild(no)){
			Storable<K, V> temp = noBuscado.getElementAt(noBuscado.size() - 1);
			paiNo.addElement(temp);
			noBuscado.removeElement(noBuscado.size() - 1);
			Storable<K, V> temp2 = paiNo.getElementAt((paiNo.getElements().indexOf(temp) + 1));
			no.addElement(temp2);
			paiNo.removeElement(temp2);	
			if(!no.isLeaf()){
				BNode<K, V> filhoBuscado = noBuscado.getChildrenAt(noBuscado.getChildren().size()-1);
				no.addChild(0, filhoBuscado);
				noBuscado.removeChild(filhoBuscado);
			}
		}
	}
	
	/**
	 * Calcula Mediana entre dois nos
	 * @param left
	 * @param right
	 * @return chave media
	 */
	private Storable<K, V> calculaMediana(BNode<K, V> left, BNode<K, V> right){
		BNode<K, V> paiNo = left.getParent();
		Storable<K, V> resp = null;
		for(int i = 0; i < paiNo.size(); i ++){
			if(!left.isEmpty() && paiNo.getElementAt(i).key.compareTo(left.getElementAt(left.size()-1).key)>0&& right.isEmpty()){
				resp=paiNo.getElementAt(i);
				break;
			}else if(!right.isEmpty() && left.isEmpty() && paiNo.getElementAt(i).key.compareTo(right.getElementAt(0).key) < 0){
				resp=paiNo.getElementAt(i);
				break;
			}else if(paiNo.getElementAt(i).key.compareTo(left.getElementAt(left.size()-1).key) > 0 &&
					paiNo.getElementAt(i).key.compareTo(right.getElementAt(0).key) < 0){
				resp = paiNo.getElementAt(i);
			}
		}
		return resp;
	}
	
	/**
	 * Concatena os nos-irmaos
	 * @param left
	 * @param right
	 */
	private void concatenaNos(BNode<K, V> left, BNode<K,V> right){
		Storable<K, V> mediana = calculaMediana(left, right);
		left.addElement(mediana);
		for(int i = 0; i < right.getElements().size(); i ++){
			left.addElement(right.getElementAt(i));
		}
		if(!left.isLeaf()){
			for(int j = 0;j<right.getChildren().size();j++){
				left.addChild(left.getChildren().size(), right.getChildrenAt(j));
			}
			
		}
		left.getParent().removeChild(right);
		left.getParent().removeElement(mediana);
	}
	
	@Override
	public void remove(K key) {
		BNode<K, V> noDel= searchAux(key);
		int elementPosition = noDel.getElements().indexOf(new Storable<K, V>(key,null));
		
		if(noDel.isLeaf()){//CASO 1
			noDel.removeElement(elementPosition);
			
			
		}else{//CASO 2
			BNode<K, V> preBNode = predecessor(noDel,key);
			BNode<K, V> sucBNode = sucessor(noDel, key);
			if(sucBNode.size() >= this.order/2){//CASO 2 B
				swap(noDel, sucBNode, noDel.getElementAt(elementPosition), sucBNode.getElementAt(0));
				remove(key);
			}else if(preBNode.size()>= this.order/2){//CASO 2 A
				swap(noDel, preBNode, noDel.getElementAt(elementPosition),preBNode.getElementAt(preBNode.size() - 1));
				remove(key);
			}else{ //CASO 2 C
				intercalar(noDel, noDel.getElementAt(elementPosition));
				remove(key);
			}
		}
		
		//CASO 3
		this.arrayNosRemovidos.add(noDel);
		this.indice ++;
		if(noDel.getElements().size() == (this.order/2) - 1 && !noDel.equals(this.root)){
			if(!noDel.getParent().isEmpty() && procuraIrmao(noDel) != null){ //CASO 3 A
				redistribuir(noDel);
			}else if(!noDel.getParent().isEmpty() && procuraIrmao(noDel)==null){//CASO NAO TENHA IRMAO COM ELEMENTO SOBRANDO
				try {
					concatenaNos(noDel.getParent().getChildrenAt(noDel.getParent().indexOfChild(noDel)-1), noDel);
				} catch (Exception e) {
					concatenaNos( noDel,noDel.getParent().getChildrenAt(noDel.getParent().indexOfChild(noDel)+1));
				}
				
			}
		}
		if(this.root.isEmpty()&&!this.root.getChildren().isEmpty()){
			this.root = this.root.getChildrenAt(0);
		}
		
	}
	
	/**
	 * Metodo que le arquivo, inserte elementos na arvore e depois remove alguns deles
	 * @param diretorio onde vai ser lido o arquivo
	 * @param arvore a ser adicionado os elementos e removidos
	 */
	public void leituraArquivo(String diretorio, BTreeImpl<K, V> arvore){
		ArrayList<Integer> listaElementosInserir = new ArrayList<Integer>();
		ArrayList<Integer> listaElementosRemover = new ArrayList<Integer>();
		boolean inserindo = false;
		boolean removendo = false;
		try {
			BufferedReader in = new BufferedReader(new FileReader(diretorio));
			String str;
		    while (in.ready()) {
		    	str = in.readLine().trim();
		    	if(inserindo && !str.equals("REMOVER")){
			    	listaElementosInserir.add(Integer.parseInt(str));
		    	}
		    	if(removendo && !str.equals("INSERIR")){
		    		listaElementosRemover.add(Integer.parseInt(str));
		    	}
		    	if(str.equals("INSERIR")){
		    		inserindo = true;
		    		removendo = false;
		    	}
		    	if(str.equals("REMOVER")){
		    		removendo = true;
		    		inserindo = false;
		    	}
		    }
		    in.close();
			} catch (IOException e) {
		}
		//ADICIONA ELEMENTO DO ARQUIVO
		for(Integer elemento : listaElementosInserir){
			arvore.insert((K)elemento, (V)elemento);
		}
		//REMOVE ELEMENTO A PARTIR DO ARQUIVO
		for(Integer elemento : listaElementosRemover){
			arvore.remove((K) elemento);
		}
	}
}