package leda;

/*
 * As �rvores s�o estruturas de dados baseadas em listas encadeadas que possuem um n� superior 
 * tamb�m chamado de raiz que aponta para outros n�s, chamados de n�s filhos, que podem ser pais 
 * de outros n�s. Uma �rvore de busca bin�ria tem as seguintes propriedades:
 * - todos os elementos na sub�rvore esquerda de um determinado n� n s�o menores que n;
 * - todos os elementos na sub�rvore direita de um determinado n� n s�o maiores ou iguais a n.
 * 
 * Leia mais em: Trabalhando com �rvores bin�rias em Java 
 * http://www.devmedia.com.br/trabalhando-com-arvores-binarias-em-java/25749
 */

public class ArvoreBinaria {
	private NoIF raiz;

	public ArvoreBinaria(NoIF raiz) {
		this.raiz = raiz;
	}

	public void varrerEmOrdem() {
		varrer(raiz);
	}

	private void varrer(NoIF node) {
		if (node != null) {
			varrer(node.esquerda());
			System.out.printf("%d\n", node.valor());
			varrer(node.direita());
		}
	}

	public void inserir(int valor) {
		inserir(raiz, valor);
	}

	private void inserir(NoIF node, int valor) {
		// Verifica se o valor a ser inserido � menor que o nodo corrente da
		// �rovre, se sim vai para subarvore esquerda
		if (valor < node.valor()) {
			// Se tiver elemento no nodo esquerdo continua a busca
			if (node.esquerda() != null) {
				inserir(node.esquerda(), valor);
			} else {
				// Se nodo esquerdo vazio insere o novo nodo aqui
				System.out.println("  Inserindo " + valor + " a esquerda de "
						+ node.valor());
				node.setEsquerda(new No(valor));
			}
			// Verifica se o valor a ser inserido � maior que o nodo corrente da
			// �rvore, se sim vai para subarvore direita
		} else if (valor > node.valor()) {
			// Se tiver elemento no nodo direito continua a busca
			if (node.direita() != null) {
				inserir(node.direita(), valor);
			} else {
				// Se nodo direito vazio insere o novo nodo aqui
				System.out.println("  Inserindo " + valor + " a direita de "
						+ node.valor());
				node.setDireita(new No(valor));
			}
		}
	}

	public static void main(String[] args) {
		ArvoreBinaria bTree = new ArvoreBinaria(new No(12));
		for (int i = 0; i <= 25; i+=2) {
			bTree.inserir(i);
		}
		bTree.varrerEmOrdem();
		
		for (int i = 1; i <= 25; i+=2) {
			bTree.inserir(i);
		}
		
		bTree.varrerEmOrdem();
	}

}
