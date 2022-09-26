package adt.rbtree;
import adt.avltree.AVLTreeImpl;
import adt.rbtree.RBNode.Colour;
import java.util.Arrays;


public class RBTreeImpl<K extends Comparable<? super K>, V> extends
		AVLTreeImpl<K, V> implements RBTree<K, V> {

	public RBTreeImpl() {
		this.root = new RBNode<K, V>();
	}

	public int blackHeight() {
		return blackHeightAuxiliar((RBNode<K, V>) root);
	}

	private int blackHeightAuxiliar(RBNode<K, V> node) {
		int result = 0;
		if (!node.isEmpty()) {
			if (Colour.BLACK.equals(node.getColour())) {
				result = 1 + maior(blackHeightAuxiliar((RBNode<K, V>) node.getLeft()),blackHeightAuxiliar((RBNode<K, V>) node.getLeft()));
			} else {
				result = maior(blackHeightAuxiliar((RBNode<K, V>) node.getLeft()),blackHeightAuxiliar((RBNode<K, V>) node.getLeft()));
			}
		}
		return result;
	}

	public boolean verifyProperties() {
		boolean resp = verifyNodesColour() && verifyNILNodeColour()&& verifyRootColour() && verifyChildrenOfRedNodes()&& verifyBlackHeight();
		return resp;
	}

	/**
	 * The colour of each node of a RB tree is black or red. This is guaranteed
	 * by the type Colour.
	 */
	private boolean verifyNodesColour() {
		return true;
	}

	/**
	 * The colour of the root must be black.
	 * 
	 * @return
	 */
	private boolean verifyRootColour() {
		return ((RBNode<K, V>) root).getColour() == Colour.BLACK;
	}

	/**
	 * This is guaranteed by the constructor.
	 */
	private boolean verifyNILNodeColour() {
		return true;
	}

	private boolean verifyNILNodeColour(RBNode<K, V> node) {
		boolean resp = false;
		if (node.isEmpty()) {
			resp = node.getColour().equals(Colour.BLACK);
		} else {
			resp = verifyNILNodeColour((RBNode<K, V>) node.getLeft())
					&& verifyNILNodeColour((RBNode<K, V>) node.getRight());
		}
		return resp;
	}

	/**
	 * Verifies the property for all RED nodes: the children of a red node must
	 * be BLACK.
	 */
	private boolean verifyChildrenOfRedNodes() {
		boolean resp = true;
		if(!this.isEmpty()){
			resp = verifyChildrenOfRedNodes((RBNode<K, V>) root);
		}
		return resp;
	}

	private boolean verifyChildrenOfRedNodes(RBNode<K, V> node) {
		boolean resp = true;
		if (!node.isEmpty()) {
			if (node.getColour().equals(Colour.RED)) {
				if (((RBNode<K, V>) node.getRight()).getColour().equals(Colour.BLACK)) {
					resp = ((RBNode<K, V>) node.getLeft()).getColour().equals(Colour.BLACK)&& verifyChildrenOfRedNodes((RBNode<K, V>) node.getLeft())&& verifyChildrenOfRedNodes((RBNode<K, V>) node.getRight());
				}
			} else {
				resp = verifyChildrenOfRedNodes((RBNode<K, V>) node.getLeft())&& verifyChildrenOfRedNodes((RBNode<K, V>) node.getRight());
			}
		}		
		return resp;
	}

	/**
	 * Verifies the black-height property from the root. The method blackHeight
	 * returns an exception if the black heights are different.
	 */
	private boolean verifyBlackHeight() {
		return blackHeightAuxiliar((RBNode<K, V>) root.getLeft()) == blackHeightAuxiliar((RBNode<K, V>) root.getRight());
	}
	
	private void fixUpInsert(RBNode<K, V> node) {
		//SE PAI NAO FOR A NULL(RAIZ) E SE AVO NAO FOR NULL
		if (!node.getParent().equals(root) && !node.getParent().equals(null)&& !node.getParent().getParent().equals(null)){
			RBNode<K, V> noPai = (RBNode<K, V>) node.getParent();
			RBNode<K, V> noAvo = (RBNode<K, V>) noPai.getParent();
			RBNode<K, V> noTio = new RBNode<K, V>();
			
			if ((node.getColour().compareTo(Colour.RED) == 0) && (noPai.getColour().compareTo(Colour.RED) == 0)) {
				if (noAvo.getLeft().equals(noPai)) {
					noTio = (RBNode<K, V>) noAvo.getRight();
				} else if (noAvo.getRight().equals(noPai)) {
					noTio = (RBNode<K, V>) noAvo.getLeft();
				}
				
				//SE O TIO FOR PRETO (CONDICAO 2 e 3)
				if ((noTio.getColour().compareTo(Colour.BLACK) == 0)) {
					boolean condicao2 = false;
					if ((noPai.getRight().equals(node))
							&& noAvo.getLeft().equals(noPai)) {
						leftRotation(noPai);
						noPai = (RBNode<K, V>) noAvo.getLeft();
						node = (RBNode<K, V>) noPai.getLeft();
						condicao2 = true;

					} else if (noPai.getLeft().equals(node)&& noAvo.getRight().equals(noPai)) {
						rightRotation(noPai);
						noPai = (RBNode<K, V>) noAvo.getRight();
						node = (RBNode<K, V>) noPai.getRight();
						condicao2 = true;
					}
					
					if (condicao2 == true)
						fixUpInsert(node);
					//SE FILHO ESQUERDO DO PAI == NODE E FILHO ESQUERDO DO AVO == PAI (CONDICAO 3)
					else if (noPai.getLeft().equals(node) && noAvo.getLeft().equals(noPai)) {
						noAvo.setColour(Colour.RED);
						noPai.setColour(Colour.BLACK);
						rightRotation(noAvo);
					} else if (noPai.getRight().equals(node) && noAvo.getRight().equals(noPai)) {
						noAvo.setColour(Colour.RED);
						noPai.setColour(Colour.BLACK);
						leftRotation(noAvo);
					}
				//SE O TIO FOR VERMELHO (CONDICAO 1)
				}else if (noTio.getColour().compareTo(Colour.RED) == 0) {
					if (!noAvo.equals(root)) {
						noPai.setColour(Colour.BLACK);
						noTio.setColour(Colour.BLACK);
						noAvo.setColour(Colour.RED);
						fixUpInsert(noAvo);
					} else {
						noTio.setColour(Colour.BLACK);
						noPai.setColour(Colour.BLACK);
					}
				}
			}
		}
	}

	public int maior(int heightLeft, int heightRight) {
		return heightLeft >= heightRight ? heightLeft : heightRight;
	}
	
	@Override
	public void insert(K chave, V valor) {
		RBNode<K, V> node = new RBNode<K, V>();
		if (isEmpty()) {
			node.setColour(Colour.BLACK);
			node.setKey(chave);
			node.setValue(valor);
			node.setLeft(new RBNode<K, V>());
			node.setParent(null);
			node.setRight(new RBNode<K, V>());
			node.getLeft().setParent(node);
			node.getRight().setParent(node);
			root = node;
		} else {
			insertAuxiliar(chave, valor, (RBNode<K, V>) this.root);
		}
	}

	private void insertAuxiliar(K chave, V valor, RBNode<K, V> no) {
		if (no.isEmpty()) {
			no.setKey(chave);
			no.setValue(valor);
			no.setLeft(new RBNode<K, V>());
			no.setRight(new RBNode<K, V>());
			no.getLeft().setParent(no);
			no.getRight().setParent(no);
			((RBNode<K, V>) no).setColour(Colour.RED);
			fixUpInsert(no);

		} else {
			if (chave.compareTo(no.getKey()) > 0) {
				insertAuxiliar(chave, valor, (RBNode<K, V>) no.getRight());
			} else
				insertAuxiliar(chave, valor, (RBNode<K, V>) no.getLeft());
		}
	}

	public String toString() {
		return Arrays.toString(this.preOrder());
	}

	// AUXILIARY
	private void rightRotation(RBNode<K, V> node) {
		RBNode<K, V> noAuxiliar = new RBNode<K, V>();
		if (!node.isEmpty() && node != null && node.getLeft().getLeft() != null) {
			noAuxiliar.setKey(node.getLeft().getKey());
			noAuxiliar.setValue(node.getLeft().getValue());
			noAuxiliar.setLeft(node.getLeft().getLeft());
			noAuxiliar.getLeft().setParent(noAuxiliar);
			noAuxiliar.setRight(node.getLeft().getRight());
			noAuxiliar.getRight().setParent(noAuxiliar);
			noAuxiliar.setColour(((RBNode<K, V>) node.getLeft()).getColour());

			node.getLeft().setKey(noAuxiliar.getRight().getKey());
			node.getLeft().setValue(noAuxiliar.getRight().getValue());
			node.getLeft().setLeft(noAuxiliar.getRight().getLeft());
			node.getLeft().setRight(noAuxiliar.getRight().getRight());
			node.getLeft().setParent(node);
			((RBNode<K, V>) node.getLeft()).setColour(((RBNode<K, V>) noAuxiliar.getRight()).getColour());

			noAuxiliar.getRight().setKey(node.getKey());
			noAuxiliar.getRight().setValue(node.getValue());
			noAuxiliar.getRight().setLeft(node.getLeft());
			noAuxiliar.getRight().getLeft().setParent(noAuxiliar.getRight());
			noAuxiliar.getRight().setRight(node.getRight());
			noAuxiliar.getRight().getRight().setParent(noAuxiliar.getRight());
			noAuxiliar.getRight().setParent(noAuxiliar);
			((RBNode<K, V>) noAuxiliar.getRight()).setColour(((RBNode<K, V>) node).getColour());

			node.setKey(noAuxiliar.getKey());
			node.setValue(noAuxiliar.getValue());
			node.setLeft(noAuxiliar.getLeft());
			node.getLeft().setParent(node);
			node.setRight(noAuxiliar.getRight());
			node.getRight().setParent(node);
			node.setParent(node.getParent());
			((RBNode<K, V>) node).setColour(((RBNode<K, V>) noAuxiliar).getColour());
		}
	}
	
	private void leftRotation(RBNode<K, V> node) {
		RBNode<K, V> noAuxiliar = new RBNode<K, V>();
		if (!node.isEmpty() && node != null && node.getRight().getRight() != null) {

			noAuxiliar.setKey(node.getRight().getKey());
			noAuxiliar.setValue(node.getRight().getValue());
			noAuxiliar.setLeft(node.getRight().getLeft());
			noAuxiliar.getLeft().setParent(noAuxiliar);
			noAuxiliar.setRight(node.getRight().getRight());
			noAuxiliar.getRight().setParent(noAuxiliar);
			noAuxiliar.setColour(((RBNode<K, V>) node.getRight()).getColour());

			node.getRight().setKey(noAuxiliar.getLeft().getKey());
			node.getRight().setValue(noAuxiliar.getLeft().getValue());
			node.getRight().setLeft(noAuxiliar.getLeft().getLeft());
			node.getRight().setRight(noAuxiliar.getLeft().getRight());
			node.getRight().setParent(node);
			((RBNode<K, V>) node.getRight()).setColour(((RBNode<K, V>) noAuxiliar.getLeft()).getColour());

			noAuxiliar.getLeft().setKey(node.getKey());
			noAuxiliar.getLeft().setValue(node.getValue());
			noAuxiliar.getLeft().setLeft(node.getLeft());
			noAuxiliar.getLeft().getLeft().setParent(noAuxiliar.getLeft());
			noAuxiliar.getLeft().setRight(node.getRight());
			noAuxiliar.getLeft().getRight().setParent(noAuxiliar.getLeft());
			noAuxiliar.getLeft().setParent(noAuxiliar);
			((RBNode<K, V>) noAuxiliar.getLeft()).setColour(((RBNode<K, V>) node).getColour());

			node.setKey(noAuxiliar.getKey());
			node.setValue(noAuxiliar.getValue());
			node.setLeft(noAuxiliar.getLeft());
			node.getLeft().setParent(node);
			node.setRight(noAuxiliar.getRight());
			node.getRight().setParent(node);
			node.setParent(node.getParent());
			((RBNode<K, V>) node).setColour(((RBNode<K, V>) noAuxiliar).getColour());
		}
	}
}