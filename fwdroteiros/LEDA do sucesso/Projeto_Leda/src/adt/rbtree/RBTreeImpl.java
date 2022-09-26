package adt.rbtree;

import java.util.ArrayList;
import java.util.Arrays;

import adt.avltree.AVLTreeImpl;
import adt.rbtree.RBNode.Colour;
import adt.util.TreeUnderflowException;

/**
 * Classe RBTreeImpl, esta estrutura representa um arvore Preto-Vermelho. 
 *  A estrutura guarda apenas a raiz, que por sua vez pode possuir filhos 
 *  a esquerda e a direita como uma arvore binaria de busca (BST), porem 
 *  cada no armazenado em uma arvore PV possui uma informacao extra, 
 *  que eh a coloracao de seu no, podendo este ser preto ou vermelho.
 * 
 * @author Flavia Gangorra, Igor Candeia, Adilson Junior, Julio Andherson
 *
 * 
 */
public class RBTreeImpl<K extends Comparable<? super K>, V> extends
		AVLTreeImpl<K, V>  implements RBTree<K, V> {

	/**
	 *  Metodo da classe RBTreeImpl, que instancia uma 
	 *  estrura de arvore Preto-Vermelho
	 */
	public RBTreeImpl() {
		this.root = new RBNode<K, V>();
	}

	
	/**
	 * Metodo da classe RBTreeImpl, que retorna o 
	 * black height (ou altura preta) de uma arvore PV. Eh a quantidade 
	 * de nos pretos da raiz ate qualquer no folha da arvore. O black
	 *  height eh um dos invariantes de uma PV, que diz que todo caminho simples 
	 *  de um no a uma folha descendente contem o mesmo numero de nos diferentes 
	 *  de NIL pretos.
	 * @return
	 * 		a altura preta de uma arvore PV
	 */
	protected int blackHeight() {
		if(root.isEmpty()){
			return 0;
		}
		return blackHeight((RBNode<K, V>) root);
	}

	private int blackHeight(RBNode<K, V> node) {
		int result = 0;
		if (!node.isEmpty()) {
			if (Colour.BLACK.equals(node.getColour())) {
				result = 1 + blackHeight((RBNode<K, V>) node.getLeft());
			} else {
				result = max(blackHeight((RBNode<K, V>) node.getLeft()),
						blackHeight((RBNode<K, V>) node.getLeft()));
			}
		}
		return result;
	}

	/**
	 * Metodo da classe RBTreeImpl, que verifica se os invariantes 
	 * de um arvore PV estao sedo mantidos. Que sao: Cada no ou eh preto ou eh vermelho,
	 *toda folha (NIL) eh preta, a raiz eh preta, se um no eh vermelho entao seus 
	 *filhos sao pretos e todo caminho simples de um no a uma folha descendente contem 
	 *o mesmo numero de nos (diferentes de NIL) pretos.
	 * @return
	 * 		verdadeiro caso todos os invariantes foram mantidos, caso contrario false.
	 */
	protected boolean verifyProperties() {
		boolean resp = false;
		if(this.isEmpty()){
			resp = true;
		}
		resp = verifyNodesColour() && verifyNILNodeColour()
				&& verifyRootColour() && verifyChildrenOfRedNodes()
				&& verifyBlackHeight();

		return resp;
	}

	/**
	 * Metodo da classe RBTreeImpl, que verifica se todos os nos de 
	 * uma arvore PV sao ou pretos ou vermelhos.
	 *@return
	 *		verdadeiro ou falso
	 */
	private boolean verifyNodesColour() {
		return true;
	}

	/**
	 * Metodo da classe RBTreeImpl, que verifica se a raiz 
	 * de uma arvore PV eh preta. Pelo invariante a raiz de uma 
	 * PV sempre deve ser preta.
	 * 
	 * @return
	 * 		Verdadeiro caso o invariante seja mantido, falso caso nao.
	 */
	private boolean verifyRootColour() {
		return ((RBNode<K, V>) root).getColour() == Colour.BLACK;
	}

	/**
	 * Metodo da classe RBTreeImpl, que verifica se todos os nos NIL 
	 * de uma arvore PV sao pretos. Nos NIL sempre devem ser pretos.
	 * 
	 * @return 
	 * 		verdadeiro caso todos os nos NIL sejam pretos, ou falso caso o invariante
	 * seja quebrado.
	 */
	private boolean verifyNILNodeColour() {
		return true;
	}



	/**
	 * Metodo da classe RBTreeImpl, que verifica se todo no vermelho 
	 * possui apenas filhos pretos, como diz o invariante de arvore PV.
	 * 
	 * @return 
	 * 		verdadeiro caso o invariante seja mantido, falso caso nao.
	 */
	private boolean verifyChildrenOfRedNodes() {
		boolean resp = true;
		if(!this.isEmpty()){
			resp = verifyChildrenOfRedNodes((RBNode<K, V>) this.root);
		}
		return resp;
	}

	private boolean verifyChildrenOfRedNodes(RBNode<K, V> node) {
		boolean resp = true;
		if (!node.isEmpty()) {
			if (node.getColour().equals(Colour.RED)) {
				if (((RBNode<K, V>) node.getRight()).getColour().equals(
						Colour.BLACK)) {
					resp = ((RBNode<K, V>) node.getLeft()).getColour().equals(
							Colour.BLACK)
							&& verifyChildrenOfRedNodes((RBNode<K, V>) node
									.getLeft())
							&& verifyChildrenOfRedNodes((RBNode<K, V>) node
									.getRight());
				}
			} else {// o no eh preto entao n pode mudar
				resp = verifyChildrenOfRedNodes((RBNode<K, V>) node.getLeft())
						&& verifyChildrenOfRedNodes((RBNode<K, V>) node
								.getRight());
			}
		}		
		return resp;
	}

	
	
	/**
	 * Metodo da classe RBTreeImpl, que verifica se a altura preta (black height) 
	 * da raiz a qualquer folha descendente tanto da esquerda quanto da direita 
	 * devem ser iguais, como diz o invariante.
	 * 
	 * @return
	 * 		verdadeiro caso o invariante seja mantido, falso caso nao.
	 */
	private boolean verifyBlackHeight(){
		if(getRoot().isEmpty() || blackHeight((RBNode<K, V>) root.getLeft()) == blackHeight((RBNode<K, V>) root.getRight())){
			return true;
		}
		return false;
	}
	
	/**
	 * Metodo da classe RBTreeImpl, que retorna qual eh o maior das alturas 
	 * entre dois valores passados como parametro.
	 * @param heightLeft
	 * 		altura1
	 * @param heightRight
	 * 		altura2
	 * @return
	 * 		a maior das duas alturas.
	 */
	public int max(int heightLeft, int heightRight) {
		return heightLeft >= heightRight ? heightLeft : heightRight;
	}

	
	/**
	 * Metodo da classe RBTreeImpl, que ajusta a cores dos nos depois 
	 * de uma insercao para que os invariantes de uma arvore PV nao 
	 * sejam quebrados.
	 * @param node
	 * 		no inserido.
	 */
	private void fixUpInsert(RBNode<K, V> node) {

		if (!node.getParent().equals(root) && !node.getParent().equals(null)// pai eh null
																			
				&& !node.getParent().getParent().equals(null))// avo eh null
															
		{
			RBNode<K, V> pai = (RBNode<K, V>) node.getParent();
			RBNode<K, V> tio = new RBNode<K, V>();
			RBNode<K, V> avo = (RBNode<K, V>) pai.getParent();

			if ((node.getColour().compareTo(Colour.RED) == 0)// verifica se o pai eh vermelho e o tio tambem
					&& (pai.getColour().compareTo(Colour.RED) == 0)) {
				
				if (avo.getLeft().equals(pai)) {// tio
					tio = (RBNode<K, V>) avo.getRight();
				} else if (avo.getRight().equals(pai)) {
					tio = (RBNode<K, V>) avo.getLeft();
				}

				if ((tio.getColour().compareTo(Colour.BLACK) == 0)) { // caso2
					boolean caso2 = false; // 2 e 3
					if ((pai.getRight().equals(node))
							&& avo.getLeft().equals(pai)) {
						leftRotation(pai);
						pai = (RBNode<K, V>) avo.getLeft();
						node = (RBNode<K, V>) pai.getLeft();
						caso2 = true;
						
					} else if (pai.getLeft().equals(node)
							&& avo.getRight().equals(pai)) {
						rightRotation(pai);
						pai = (RBNode<K, V>) avo.getRight();
						node = (RBNode<K, V>) pai.getRight();
						caso2 = true;
					}
					
					if (caso2)
						fixUpInsert(node);
					else if (pai.getLeft().equals(node)// caso 3
							&& avo.getLeft().equals(pai)) {
						avo.setColour(Colour.RED);
						pai.setColour(Colour.BLACK);
						rightRotation(avo);
					} else if (pai.getRight().equals(node)
							&& avo.getRight().equals(pai)) {
						avo.setColour(Colour.RED);
						pai.setColour(Colour.BLACK);
						leftRotation(avo);
					}

				}else if (tio.getColour().compareTo(Colour.RED) == 0) {//caso 1
					if (!avo.equals(root)) {
						pai.setColour(Colour.BLACK);
						tio.setColour(Colour.BLACK);
						avo.setColour(Colour.RED);
						fixUpInsert(avo);
					} else {
						tio.setColour(Colour.BLACK);
						pai.setColour(Colour.BLACK);
					}
				}


			}
		}
	}

	
	/**
	 * Metodo da classe RBTreeImpl, que insere um novo no em uma arvore PV. 
	 * Que possui uma chave, um valor a ser armazenado, e uma cor (preto ou vermelho), 
	 * a principio a cor de um no inserido eh vermelho, caso o no inserido quebre algum 
	 * invariante da arvore PV, as coloracoes serao ajustadas para que todas as propriedades
	 *  sejam mantidas.
	 * @param key
	 * 		chave do no a ser inserido
	 * @param value 
	 * 		valor do no a ser inserido
	 */
	@Override
	public void insert(K key, V value) {
		RBNode<K, V> node = new RBNode<K, V>();
		if (this.isEmpty()) {
			node.setColour(Colour.BLACK);
			node.setKey(key);
			node.setValue(value);
			node.setLeft(new RBNode<K, V>());
			node.setParent(null);
			node.setRight(new RBNode<K, V>());
			node.getLeft().setParent(node);
			node.getRight().setParent(node);
			this.root = node;
		} else {
			RBNode<K, V> no = (RBNode<K, V>) search(key);
			if(no.isEmpty()){
				insertRecursivo(key, value, (RBNode<K, V>) this.root);
			}
		}
	}

	private void insertRecursivo(K key, V value, RBNode<K, V> node) {
		if (node.isEmpty()) {
			node.setKey(key);
			node.setValue(value);
			node.setLeft(new RBNode<K, V>());
			node.setRight(new RBNode<K, V>());
			node.getLeft().setParent(node);
			node.getRight().setParent(node);
			((RBNode<K, V>) node).setColour(Colour.RED);
			fixUpInsert(node);

		} else {
			if (key.compareTo(node.getKey()) > 0) {
				insertRecursivo(key, value, (RBNode<K, V>) node.getRight());
			} else
				insertRecursivo(key, value, (RBNode<K, V>) node.getLeft());
		}

	}

	/**
	 * Metodo da classe RBTreeImpl, que remove um no de uma arvore PV 
	 * atraves de sua chave. Se o no a ser removido for vermelho, apenas o 
	 * removemos, porem se o no for preto, ao retira-lo estaremos alterando o 
	 * black height da PV, logo as coloracoes serao ajustadas para que todas as propriedades
	 *  sejam mantidas.
	 * @throws TreeUnderflowException 
	 */
	@Override
	public void remove(K key) {
		if(!getRoot().isEmpty()){
			RBNode<K, V> removido = (RBNode <K,V>)search(key);
			if(!removido.isEmpty()){
				if(!removido.equals(getRoot())){
					if(removido.getColour() == Colour.RED){
						
						remove2(removido);
					}else{
						RBNode<K, V> filho = new RBNode<K,V>();
						if(!removido.getLeft().isEmpty()){//pego o filho nao nulo do no a ser removido para fazer o fixUP
							filho = (RBNode<K, V>) removido.getLeft();
						}else if(!removido.getRight().isEmpty()){
							filho = (RBNode<K, V>) removido.getRight();
						}
						
						fixUpRemove(removido);//fixUp pra ajustar as cores
						remove2(removido);//depois removo o no
					}
				}else if(removido.equals(getRoot())){//se o no for raiz tudo sera tratado no fixUp
					fixUpRemove(removido);
				}
			}
		}
		
		
	}
	
	
	private void fixUpRemove(RBNode<K, V> node){
		if(!(node.equals(getRoot()))){
		RBNode<K, V> irmao = new RBNode<K,V>();
		RBNode<K, V> pai = (RBNode<K, V>) node.getParent();
		
		boolean esquerda = false, direita = false;
		if(((RBNode<K, V>)node.getParent().getLeft()).equals(node)){//se eu sou filho da esquerda do meu pai
			irmao = (RBNode<K, V>) node.getParent().getRight();
			esquerda = true;
		}else if(((RBNode<K, V>)node.getParent().getRight()).equals(node)){//se eu sou filho da direito do meu pai
			irmao = (RBNode<K, V>) node.getParent().getLeft();
			direita = true;
		}
		
		if(irmao.getColour() == Colour.RED){//caso 1
			irmao.setColour(Colour.BLACK);
			pai.setColour(Colour.RED);
			if(esquerda){
				leftRotation(pai);
				
				///
				
				if(((RBNode<K, V>)irmao.getParent().getRight()).getColour() == Colour.BLACK){
					((RBNode<K, V>) irmao.getParent().getRight()).setColour(Colour.RED);
					((RBNode<K, V>)irmao.getParent()).setColour(Colour.BLACK);
				}
				
			}else if(direita){
				rightRotation(pai);
				
				//

				if(((RBNode<K, V>)irmao.getParent().getLeft()).getColour() == Colour.BLACK){
					((RBNode<K, V>) irmao.getParent().getLeft()).setColour(Colour.RED);
					((RBNode<K, V>)irmao.getParent()).setColour(Colour.BLACK);
				}
				
			}
		}
		if(irmao.getColour() == Colour.BLACK){//caso 2
			if((((RBNode<K, V>)irmao.getLeft()).getColour() == Colour.BLACK) && (((RBNode<K, V>)irmao.getRight()).getColour() == Colour.BLACK)){//dois sobrinhos sao pretos
				irmao.setColour(Colour.RED);
				
				//**//
				if(pai.getColour() == Colour.RED){
					pai.setColour(Colour.BLACK);
				}
			}
			
			//caso 3
			if((((RBNode<K, V>)irmao.getLeft()).getColour() == Colour.RED) || (((RBNode<K, V>)irmao.getRight()).getColour() == Colour.RED)){//um dos sobrinhos eh vermelho
				if(esquerda && ((((RBNode<K, V>)irmao.getLeft()).getColour() == Colour.RED) && ((RBNode<K, V>)irmao.getRight()).getColour() == Colour.BLACK)){//forma um joelho esquerdo
					irmao.setColour(Colour.RED);
					((RBNode<K, V>)irmao.getLeft()).setColour(Colour.BLACK);
					rightRotation(irmao);
					//irmao recebe left(pai(x))   
				}else if(direita && ((((RBNode<K, V>)irmao.getRight()).getColour() == Colour.RED) && ((RBNode<K, V>)irmao.getLeft()).getColour() == Colour.BLACK)){//forma joelho direito
					irmao.setColour(Colour.RED);
					((RBNode<K, V>)irmao.getRight()).setColour(Colour.BLACK);
					leftRotation(irmao);
					//irmao recebe left(pai(x))   
				}
				
				
				//caso 4
				if(esquerda && ((((RBNode<K, V>) irmao.getRight()).getColour() == Colour.RED) && ((RBNode<K, V>) irmao.getLeft()).getColour() == Colour.BLACK)){//sobrinho vermelho numa reta direita
					irmao.setColour(pai.getColour());
					pai.setColour(Colour.BLACK);
					((RBNode<K, V>)irmao.getRight()).setColour(Colour.BLACK);
					leftRotation(pai);
					//x passa a ser raiz
				}
				else if(direita && ((((RBNode<K, V>) irmao.getLeft()).getColour() == Colour.RED) && (((RBNode<K, V>) irmao.getRight()).getColour() == Colour.BLACK))){//sobrinho vermelho numa reta esquerda
					irmao.setColour(pai.getColour());
					pai.setColour(Colour.BLACK);
					((RBNode<K, V>) irmao.getLeft()).setColour(Colour.BLACK);
					rightRotation(pai);
					//x passa a ser raiz
				}
			}
			
				
		}
		}else if(node.equals(getRoot())) {
			RBNode<K, V> suc = (RBNode<K, V>) sucessor(node);
			if(suc != null){
				this.root.setKey(suc.getKey());
				this.root.setValue(suc.getValue());
				remove2(suc);
			}else if(suc == null){
				remove2(node);
			}
			
		}
		
	}
	
	

	/**
	 * Metodo da classe RBTreeImpl, que retorna uma string 
	 * contendo as chaves de uma arvore PV em preOrder
	 * 
	 */
	public String toString() {
		return Arrays.toString(this.preOrder());
	}

	
	/**
	 *  Metodo da classe RBTreeImpl, que faz as rotacaoes de acordo com os casos para a manutencao 
	 *  dos invariantes da arvore PV, rotacao a esquerda.
	 * @param node
	 * 		no a ser rotacionado
	 */
	private void leftRotation(RBNode<K, V> node) {
		RBNode<K, V> pivot = new RBNode<K, V>();
		if (!node.isEmpty() && node != null
				&& node.getRight().getRight() != null) {

			pivot.setKey(node.getRight().getKey());
			pivot.setValue(node.getRight().getValue());
			pivot.setLeft(node.getRight().getLeft());
			pivot.getLeft().setParent(pivot);
			pivot.setRight(node.getRight().getRight());
			pivot.getRight().setParent(pivot);
			pivot.setColour(((RBNode<K, V>) node.getRight()).getColour());

			node.getRight().setKey(pivot.getLeft().getKey());
			node.getRight().setValue(pivot.getLeft().getValue());
			node.getRight().setLeft(pivot.getLeft().getLeft());
			node.getRight().setRight(pivot.getLeft().getRight());
			node.getRight().setParent(node);
			((RBNode<K, V>) node.getRight()).setColour(((RBNode<K, V>) pivot
					.getLeft()).getColour());

			pivot.getLeft().setKey(node.getKey());
			pivot.getLeft().setValue(node.getValue());
			pivot.getLeft().setLeft(node.getLeft());
			pivot.getLeft().getLeft().setParent(pivot.getLeft());
			pivot.getLeft().setRight(node.getRight());
			pivot.getLeft().getRight().setParent(pivot.getLeft());
			pivot.getLeft().setParent(pivot);
			((RBNode<K, V>) pivot.getLeft()).setColour(((RBNode<K, V>) node)
					.getColour());

			node.setKey(pivot.getKey());
			node.setValue(pivot.getValue());
			node.setLeft(pivot.getLeft());
			node.getLeft().setParent(node);
			node.setRight(pivot.getRight());
			node.getRight().setParent(node);
			node.setParent(node.getParent());
			((RBNode<K, V>) node).setColour(((RBNode<K, V>) pivot).getColour());

		}
	}

	
	/**
	 *  Metodo da classe RBTreeImpl, que faz as rotacaoes de acordo com os casos para a manutencao 
	 *  dos invariantes da arvore PV, rotacao a direita.
	 * @param node
	 * 		no a ser rotacionado
	 */
	private void rightRotation(RBNode<K, V> node) {
		RBNode<K, V> pivot = new RBNode<K, V>();
		if (!node.isEmpty() && node != null && node.getLeft().getLeft() != null) {

			pivot.setKey(node.getLeft().getKey());
			pivot.setValue(node.getLeft().getValue());
			pivot.setLeft(node.getLeft().getLeft());
			pivot.getLeft().setParent(pivot);
			pivot.setRight(node.getLeft().getRight());
			pivot.getRight().setParent(pivot);
			pivot.setColour(((RBNode<K, V>) node.getLeft()).getColour());

			node.getLeft().setKey(pivot.getRight().getKey());
			node.getLeft().setValue(pivot.getRight().getValue());
			node.getLeft().setLeft(pivot.getRight().getLeft());
			node.getLeft().setRight(pivot.getRight().getRight());
			node.getLeft().setParent(node);
			((RBNode<K, V>) node.getLeft()).setColour(((RBNode<K, V>) pivot
					.getRight()).getColour());

			pivot.getRight().setKey(node.getKey());
			pivot.getRight().setValue(node.getValue());
			pivot.getRight().setLeft(node.getLeft());
			pivot.getRight().getLeft().setParent(pivot.getRight());
			pivot.getRight().setRight(node.getRight());
			pivot.getRight().getRight().setParent(pivot.getRight());
			pivot.getRight().setParent(pivot);
			((RBNode<K, V>) pivot.getRight()).setColour(((RBNode<K, V>) node)
					.getColour());

			node.setKey(pivot.getKey());
			node.setValue(pivot.getValue());
			node.setLeft(pivot.getLeft());
			node.getLeft().setParent(node);
			node.setRight(pivot.getRight());
			node.getRight().setParent(node);
			node.setParent(node.getParent());
			((RBNode<K, V>) node).setColour(((RBNode<K, V>) pivot).getColour());

		}
	}
	
	
	/**
	 * Metodo da classe RBTreeImpl, que retorna um arrayList contendo 
	 * os nos de uma arvore PV em preOrder com as seguintes informacoes: sua chave e 
	 * coloracao. A representacao do no eh da forma: (chave, B) para nos pretos, e 
	 * (chave, R) para nos vermelhos.
	 * @return
	 * 		um arrayList contendo todos os nos de uma PV em preOrder, com informacoes da chave e cor
	 */
	public ArrayList<String> preOrder2() {
		ArrayList<String> array = new ArrayList<String>();
		for( K node : this.preOrder()){
			RBNode<K, V> n = (RBNode<K, V>) search(node);
			array.add(n.toString());
		}
		return array;
	}
}
