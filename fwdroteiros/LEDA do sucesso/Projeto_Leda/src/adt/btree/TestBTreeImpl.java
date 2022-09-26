package adt.btree;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

/**
 * Classe testes da classe BTreeImpl.
 * 
 * @author Flavia Gangorra, Igor Candeia, Adilson Junior, Julio Andherson
 *
 */
public class TestBTreeImpl {
	BTreeImpl<Integer, Integer> arvore1;
	BTreeImpl<Integer, Integer> arvore2;
	BTreeImpl<Integer, Integer> arvore3;
	
	//<<<<<DIRETORIO DO ARQUIVO DEVE SER ALTERADO PRA LOCAL DOS ARQUIVOS>>>>>
	//<<<<<CASO CONTRARIO OS TESTES IRAO QUEBRAR>>>>>
	String diretorioArquivo1 = "C:/Users/Julio/ProjetosEclipse/Projeto_Leda/src/ArvoreTeste1.txt";
	String diretorioArquivo2 = "C:/Users/Julio/ProjetosEclipse/Projeto_Leda/src/ArvoreTeste2.txt";
	String diretorioArquivo3 = "C:/Users/Julio/ProjetosEclipse/Projeto_Leda/src/ArvoreTeste3.txt";
	//<<<<<DIRETORIO DO ARQUIVO DEVE SER ALTERADO PRA LOCAL DOS ARQUIVOS>>>>>
	//<<<<<CASO CONTRARIO OS TESTES IRAO QUEBRAR>>>>>
		
	@Before
	public void Construtor(){
		arvore1 = new BTreeImpl<Integer, Integer>(3); 
		arvore2 = new BTreeImpl<Integer, Integer>(5);
		arvore3 = new BTreeImpl<Integer, Integer>(6);
	}
	
	@Test
	public void TestGetRoot(){
		Assert.assertEquals("Raiz Incorreta.", "[]", arvore1.getRoot().toString());
		arvore1.insert(1, 1);
		Assert.assertEquals("Raiz Incorreta.", "[1]", arvore1.getRoot().toString());
		arvore1.insert(2, 2);
		Assert.assertEquals("Raiz Incorreta.", "[1, 2]", arvore1.getRoot().toString());
		arvore1.insert(3, 3);
		Assert.assertEquals("Raiz Incorreta.", "[2]", arvore1.getRoot().toString());

		Assert.assertEquals("Raiz Incorreta.", "[]", arvore2.getRoot().toString());
		arvore2.insert(20, 20);
		Assert.assertEquals("Raiz Incorreta.", "[20]", arvore2.getRoot().toString());
		arvore2.insert(12, 12);
		Assert.assertEquals("Raiz Incorreta.", "[12, 20]", arvore2.getRoot().toString());
		arvore2.insert(5, 5);
		Assert.assertEquals("Raiz Incorreta.", "[5, 12, 20]", arvore2.getRoot().toString());
		arvore2.insert(15, 15);
		Assert.assertEquals("Raiz Incorreta.", "[5, 12, 15, 20]", arvore2.getRoot().toString());
		arvore2.insert(50, 50);
		Assert.assertEquals("Raiz Incorreta.", "[15]", arvore2.getRoot().toString());	
	
		Assert.assertEquals("Raiz Incorreta.", "[]", arvore3.getRoot().toString());
		arvore3.insert(35, 35);
		arvore3.insert(20, 20);
		arvore3.insert(5, 5);
		arvore3.insert(10, 10);
		arvore3.insert(15, 15);
		Assert.assertEquals("Raiz Incorreta.", "[5, 10, 15, 20, 35]", arvore3.getRoot().toString());
		arvore3.insert(77, 77);
		arvore3.insert(55, 55);
		arvore3.insert(19, 19);
		arvore3.insert(80, 80);
		arvore3.insert(85, 85);
		arvore3.insert(90, 90);
		Assert.assertEquals("Raiz Incorreta.", "[20, 80]", arvore3.getRoot().toString());
		arvore3.insert(36, 36);
		arvore3.insert(40, 40);
		arvore3.insert(41, 41);
		Assert.assertEquals("Raiz Incorreta.", "[20, 41, 80]", arvore3.getRoot().toString());
		arvore3.insert(7, 7);
		arvore3.insert(9, 9);
		Assert.assertEquals("Raiz Incorreta.", "[10, 20, 41, 80]", arvore3.getRoot().toString());
		arvore3.insert(21, 21);
		arvore3.insert(23, 23);
		arvore3.insert(24, 24);
		Assert.assertEquals("Raiz Incorreta.", "[10, 20, 35, 41, 80]", arvore3.getRoot().toString());
	}
	
	@Test
	public void TestIsEmpty(){
		Assert.assertTrue("Era pra Arvore estar Vazia.", arvore1.isEmpty());
		arvore1.insert(1, 1);
		Assert.assertFalse("Era pra Arvore nao estar Vazia.", arvore1.isEmpty());
		arvore1.remove(1);
		Assert.assertTrue("Era pra Arvore estar Vazia.", arvore1.isEmpty());

		Assert.assertTrue("Era pra Arvore estar Vazia.", arvore2.isEmpty());
		arvore2.insert(20, 20);
		arvore2.insert(30, 30);
		arvore2.insert(40, 40);
		Assert.assertFalse("Era pra Arvore nao estar Vazia.", arvore2.isEmpty());	
		arvore2.remove(20);
		arvore2.remove(30);
		arvore2.remove(40);
		Assert.assertTrue("Era pra Arvore estar Vazia.", arvore2.isEmpty());
		
		Assert.assertTrue("Era pra Arvore estar Vazia.", arvore3.isEmpty());
		arvore3.insert(3, 3);
		Assert.assertFalse("Era pra Arvore nao estar Vazia.", arvore3.isEmpty());
		arvore3.insert(5, 5);
		arvore3.insert(10, 10);
		arvore3.insert(7, 7);
		arvore3.insert(11, 11);
		arvore3.insert(20, 20);
		Assert.assertFalse("Era pra Arvore nao estar Vazia.", arvore3.isEmpty());
		arvore3.remove(3);
		arvore3.remove(5);
		arvore3.remove(10);
		arvore3.remove(7);
		arvore3.remove(11);
		arvore3.remove(20);
		Assert.assertTrue("Era pra Arvore estar Vazia.", arvore3.isEmpty());
	}
	
	@Test
	public void TestHeight(){
		Assert.assertEquals("Altura Inicial Incorreta.", -1, arvore1.height());
		arvore1.insert(5, 5);
		arvore1.insert(1, 1);
		Assert.assertEquals("Altura Incorreta.", 0, arvore1.height());
		arvore1.insert(10, 10);
		Assert.assertEquals("Altura Incorreta.", 1, arvore1.height());
		arvore1.insert(3, 3);
		arvore1.insert(9, 9);
		Assert.assertEquals("Altura Incorreta.", 1, arvore1.height());
		arvore1.insert(21, 21);
		Assert.assertEquals("Altura Incorreta.", 1, arvore1.height());
		arvore1.insert(2, 2);
		Assert.assertEquals("Altura Incorreta.", 2, arvore1.height());
		arvore1.remove(10);
		Assert.assertEquals("Altura Incorreta.", 1, arvore1.height());
		arvore1.remove(2);
		arvore1.remove(5);
		arvore1.remove(21);
		Assert.assertEquals("Altura Incorreta.", 1, arvore1.height());
		arvore1.remove(3);
		Assert.assertEquals("Altura Incorreta.", 0, arvore1.height());

		Assert.assertEquals("Altura Inicial Incorreta.", -1, arvore2.height());
		arvore2.insert(5, 5);
		arvore2.insert(13, 13);
		arvore2.insert(16, 16);
		arvore2.insert(2, 2);
		Assert.assertEquals("Altura Incorreta.", 0, arvore2.height());
		arvore2.insert(30, 30);
		arvore2.insert(1, 1);
		arvore2.insert(31, 31);
		arvore2.insert(6, 6);
		Assert.assertEquals("Altura Incorreta.", 1, arvore2.height());
		arvore2.insert(10, 10);
		arvore2.insert(12, 12);
		arvore2.insert(14, 14);
		arvore2.insert(15, 15);
		arvore2.insert(3, 3);
		arvore2.insert(4, 4);
		Assert.assertEquals("Altura Incorreta.", 1, arvore2.height());
		arvore2.insert(18, 18);
		arvore2.insert(19, 19);
		arvore2.insert(20, 20);
		arvore2.insert(21, 21);
		Assert.assertEquals("Altura Incorreta.", 1, arvore2.height());
		
		Assert.assertEquals("Altura Inicial Incorreta.", -1, arvore3.height());
		arvore3.insert(5, 5);
		arvore3.insert(7, 7);
		arvore3.insert(9, 9);
		arvore3.insert(20, 20);
		arvore3.insert(30, 30);
		Assert.assertEquals("Altura Incorreta.", 0, arvore3.height());
		arvore3.insert(35, 35);
		Assert.assertEquals("Altura Incorreta.", 1, arvore3.height());
		arvore3.insert(88, 88);
		arvore3.insert(95, 95);
		arvore3.insert(40, 40);
		arvore3.insert(99, 99);
		Assert.assertEquals("Altura Incorreta.", 1, arvore3.height());
		arvore3.insert(52, 52);
		arvore3.insert(55, 55);
		arvore3.insert(59, 59);
		arvore3.insert(24, 24);
		arvore3.insert(27, 27);
		arvore3.insert(28, 28);
		arvore3.insert(13, 13);
		arvore3.insert(17, 17);
		arvore3.insert(19, 19);
		Assert.assertEquals("Altura Incorreta.", 1, arvore3.height());
		arvore3.insert(41, 41);
		arvore3.insert(42, 42);
		arvore3.insert(43, 43);
		arvore3.insert(44, 44);
		Assert.assertEquals("Altura Incorreta.", 2, arvore3.height());
	}
	
	@Test
	public void TestDepthLeftOrder(){
		arvore1.insert(99, 99);
		arvore1.insert(50, 50);
		arvore1.insert(20, 20);
		arvore1.insert(30, 30);
		arvore1.insert(33, 33);
		arvore1.insert(66, 66);
		arvore1.insert(19, 19);
		BNode<Integer, Integer>[] arrayDephLeftOrder = arvore1.depthLeftOrder();
		String concatenaNoArvore = "[";
		for(BNode<Integer, Integer> noArvore : arrayDephLeftOrder){
			concatenaNoArvore += noArvore.toString();
		}
		concatenaNoArvore += "]";
		Assert.assertEquals("ToArray Incorreto.","[[30, 50][19, 20][33][66, 99]]", concatenaNoArvore);
		
		arvore2.insert(1, 1);
		arvore2.insert(2, 2);
		arvore2.insert(3, 3);
		arvore2.insert(4, 4);
		arvore2.insert(5, 5);
		arvore2.insert(6, 6);
		arvore2.insert(7, 7);
		arvore2.insert(8, 8);
		arvore2.insert(9, 9);
		arvore2.insert(10, 10);
		arvore2.insert(25, 25);
		arvore2.insert(33, 33);
		arvore2.insert(15, 15);
		arvore2.insert(44, 44);
		arvore2.insert(40, 40);
		arvore2.insert(32, 32);
		arvore2.insert(19, 19);
		arvore2.insert(17, 17);
		arvore2.insert(13, 13);
		arvore2.insert(69, 69);
		arvore2.insert(70, 70);
		arvore2.insert(71, 71);
		arvore2.insert(12, 12);
		arvore2.insert(14, 14);
		arvore2.insert(16, 16);
		arvore2.insert(37, 37);
		arvore2.insert(39, 39);
		arvore2.insert(51, 51);
		BNode<Integer, Integer>[] arrayDephLeftOrder2 = arvore2.depthLeftOrder();
		String concatenaNoArvore2 = "[";
		for(BNode<Integer, Integer> noArvore : arrayDephLeftOrder2){
			concatenaNoArvore2 += noArvore.toString();
		}
		concatenaNoArvore2 += "]";
		Assert.assertEquals("ToArray Incorreto.","[[9][3, 6][1, 2][4, 5][7, 8][15, 25, 40, 69][10, 12, 13, 14][16, 17, 19][32, 33, 37, 39][44, 51][70, 71]]", concatenaNoArvore2);
	
		arvore3.insert(13, 13);
		arvore3.insert(22, 22);
		arvore3.insert(20, 20);
		arvore3.insert(31, 31);
		arvore3.insert(88, 88);
		arvore3.insert(43, 43);
		arvore3.insert(91, 91);
		arvore3.insert(5, 5);
		arvore3.insert(24, 24);
		arvore3.insert(76, 76);
		arvore3.insert(51, 51);
		arvore3.insert(72, 72);
		arvore3.insert(12, 12);
		arvore3.insert(8, 8);
		arvore3.insert(1, 1);
		arvore3.insert(29, 29);
		arvore3.insert(32, 32);
		arvore3.insert(39, 39);
		arvore3.insert(82, 82);
		arvore3.insert(16, 16);
		BNode<Integer, Integer>[] arrayDephLeftOrder3 = arvore3.depthLeftOrder();
		String concatenaNoArvore3 = "[";
		for(BNode<Integer, Integer> noArvore : arrayDephLeftOrder3){
			concatenaNoArvore3 += noArvore.toString();
		}
		concatenaNoArvore3 += "]";
		Assert.assertEquals("ToArray Incorreto.","[[12, 20, 31, 76][1, 5, 8][13, 16][22, 24, 29][32, 39, 43, 51, 72][82, 88, 91]]", concatenaNoArvore3);
	}
	
	@Test
	public void TestMinimum(){
		arvore1.insert(10, 10);
		arvore1.insert(20, 20);
		arvore1.insert(30, 30);
		arvore1.insert(40, 40);
		arvore1.insert(50, 50);
		arvore1.insert(60, 60);
		BNode<Integer, Integer> min1 = arvore1.minimum(arvore1.getRoot());
		Assert.assertEquals("Minimo Incorreto.", "[10]", min1.toString());
		
		arvore2.insert(4, 4);
		arvore2.insert(7, 7);
		arvore2.insert(13, 13);
		arvore2.insert(22, 22);
		arvore2.insert(21, 21);
		arvore2.insert(15, 15);
		arvore2.insert(19, 19);
		arvore2.insert(23, 23);
		BNode<Integer, Integer> min2 = arvore2.minimum(arvore2.getRoot());
		Assert.assertEquals("Minimo Incorreto.", "[4, 7]", min2.toString());
		
		arvore3.insert(77, 77);
		arvore3.insert(78, 78);
		arvore3.insert(22, 22);
		arvore3.insert(26, 26);
		arvore3.insert(66, 66);
		arvore3.insert(12, 12);
		arvore3.insert(2, 2);
		arvore3.insert(88, 88);
		BNode<Integer, Integer> min3 = arvore3.minimum(arvore3.getRoot());
		Assert.assertEquals("Minimo Incorreto.", "[2, 12, 22, 26]", min3.toString());
	}
	
	@Test
	public void TestMaximum(){
		arvore1.insert(10, 10);
		arvore1.insert(20, 20);
		arvore1.insert(30, 30);
		arvore1.insert(40, 40);
		arvore1.insert(50, 50);
		arvore1.insert(60, 60);
		BNode<Integer, Integer> max1 = arvore1.maximum(arvore1.getRoot());
		Assert.assertEquals("Minimo Incorreto.", "[50, 60]", max1.toString());
		
		arvore2.insert(4, 4);
		arvore2.insert(7, 7);
		arvore2.insert(13, 13);
		arvore2.insert(22, 22);
		arvore2.insert(21, 21);
		arvore2.insert(15, 15);
		arvore2.insert(19, 19);
		arvore2.insert(23, 23);
		BNode<Integer, Integer> max2 = arvore2.maximum(arvore2.getRoot());
		Assert.assertEquals("Minimo Incorreto.", "[22, 23]", max2.toString());
		
		arvore3.insert(77, 77);
		arvore3.insert(78, 78);
		arvore3.insert(22, 22);
		arvore3.insert(26, 26);
		arvore3.insert(66, 66);
		arvore3.insert(12, 12);
		arvore3.insert(2, 2);
		arvore3.insert(88, 88);
		BNode<Integer, Integer> max3 = arvore3.maximum(arvore3.getRoot());
		Assert.assertEquals("Minimo Incorreto.", "[77, 78, 88]", max3.toString());
	}
	
	@Test
	public void TestSize(){
		Assert.assertEquals("Tamanho da Arvore Incorreto.", 0, arvore1.size());
		arvore1.insert(50, 50);
		arvore1.insert(30, 30);
		arvore1.insert(40, 40);
		Assert.assertEquals("Tamanho da Arvore Incorreto.", 3, arvore1.size());
		arvore1.remove(50);
		Assert.assertEquals("Tamanho da Arvore Incorreto.", 2, arvore1.size());
		arvore1.remove(30);
		arvore1.remove(40);
		Assert.assertEquals("Tamanho da Arvore Incorreto.", 0, arvore1.size());

		Assert.assertEquals("Tamanho da Arvore Incorreto.", 0, arvore2.size());
		arvore2.insert(5, 5);
		arvore2.insert(3, 3);
		arvore2.insert(9, 9);
		arvore2.insert(1, 1);
		Assert.assertEquals("Tamanho da Arvore Incorreto.", 4, arvore2.size());
		arvore2.remove(5);
		arvore2.insert(5, 5);
		Assert.assertEquals("Tamanho da Arvore Incorreto.", 4, arvore2.size());
		arvore2.remove(5);
		arvore2.remove(3);
		arvore2.remove(9);
		arvore2.remove(1);
		Assert.assertEquals("Tamanho da Arvore Incorreto.", 0, arvore2.size());
		
		Assert.assertEquals("Tamanho da Arvore Incorreto.", 0, arvore3.size());
		arvore3.insert(10, 10);
		arvore3.insert(11, 11);
		arvore3.insert(5, 5);
		Assert.assertEquals("Tamanho da Arvore Incorreto.", 3, arvore3.size());
		arvore3.insert(7, 7);
		arvore3.insert(15, 15);
		arvore3.insert(22, 22);
		arvore3.insert(31, 31);
		arvore3.insert(61, 61);
		arvore3.insert(25, 25);
		arvore3.insert(77, 77);
		Assert.assertEquals("Tamanho da Arvore Incorreto.", 10, arvore3.size());
		arvore3.remove(10);
		arvore3.remove(77);
		Assert.assertEquals("Tamanho da Arvore Incorreto.", 8, arvore3.size());
		arvore3.remove(31);
		arvore3.remove(25);
		arvore3.remove(61);
		Assert.assertEquals("Tamanho da Arvore Incorreto.", 5, arvore3.size());
		arvore3.remove(11);
		arvore3.remove(5);
		arvore3.remove(7);
		arvore3.remove(15);
		arvore3.remove(22);
		Assert.assertEquals("Tamanho da Arvore Incorreto.", 0, arvore3.size());
	}
	
	@Test
	public void TestSearch(){
		Assert.assertEquals("No Procurado Incorreto.", null, arvore1.search(1).node);
		arvore1.insert(2, 2);
		arvore1.insert(3, 3);
		arvore1.insert(5, 5);
		arvore1.insert(4, 4);
		Assert.assertEquals("No Procurado Incorreto.", "[4, 5]", arvore1.search(4).node.toString());
		Assert.assertEquals("No Procurado Incorreto.", "[4, 5]", arvore1.search(5).node.toString());
		Assert.assertEquals("No Procurado Incorreto.", "[2]", arvore1.search(2).node.toString());
		arvore1.insert(10, 10);
		Assert.assertEquals("No Procurado Incorreto.", "[10]", arvore1.search(10).node.toString());
		Assert.assertEquals("No Procurado Incorreto.", null, arvore1.search(999).node);
		arvore1.remove(10);
		Assert.assertEquals("No Procurado Incorreto.", null, arvore1.search(10).node);
		
		Assert.assertEquals("No Procurado Incorreto.", null, arvore2.search(10).node);
		arvore2.insert(35, 35);
		arvore2.insert(40, 40);
		arvore2.insert(45, 45);
		arvore2.insert(50, 50);
		arvore2.insert(60, 60);
		arvore2.insert(10, 10);
		arvore2.insert(20, 20);
		arvore2.insert(99, 99);
		Assert.assertEquals("No Procurado Incorreto.", "[10, 20, 35, 40]", arvore2.search(35).node.toString());
		Assert.assertEquals("No Procurado Incorreto.", null, arvore2.search(36).node);
		Assert.assertEquals("No Procurado Incorreto.", "[50, 60, 99]", arvore2.search(99).node.toString());
		arvore2.insert(65, 65);
		Assert.assertEquals("No Procurado Incorreto.", "[50, 60, 65, 99]", arvore2.search(65).node.toString());
		arvore2.remove(45);
		Assert.assertEquals("No Procurado Incorreto.", null, arvore2.search(45).node);
		arvore2.remove(50);
		arvore2.remove(99);
		Assert.assertEquals("No Procurado Incorreto.", null, arvore2.search(50).node);
		Assert.assertEquals("No Procurado Incorreto.", null, arvore2.search(99).node);
		Assert.assertEquals("No Procurado Incorreto.", "[10, 20, 35]", arvore2.search(10).node.toString());
	
		Assert.assertEquals("No Procurado Incorreto.", null, arvore3.search(1).node);
		arvore3.insert(20, 20);
		arvore3.insert(13, 13);
		arvore3.insert(52, 52);
		arvore3.insert(98, 98);
		arvore3.insert(1, 1);
		arvore3.insert(21, 21);
		arvore3.insert(66, 66);
		arvore3.insert(77, 77);
		Assert.assertEquals("No Procurado Incorreto.", "[1, 13, 20]", arvore3.search(1).node.toString());
		Assert.assertEquals("No Procurado Incorreto.", "[52, 66, 77, 98]", arvore3.search(52).node.toString());
		Assert.assertEquals("No Procurado Incorreto.", "[21]", arvore3.search(21).node.toString());
		arvore3.remove(21);
		Assert.assertEquals("No Procurado Incorreto.", null, arvore3.search(21).node);
		arvore3.insert(95, 95);
		arvore3.insert(36, 36);
		arvore3.insert(22, 22);
		arvore3.insert(49, 49);
		arvore3.insert(71, 71);
		arvore3.insert(2, 2);
		arvore3.insert(3, 3);
		Assert.assertEquals("No Procurado Incorreto.", "[22, 52]", arvore3.search(22).node.toString());
		Assert.assertEquals("No Procurado Incorreto.", "[1, 2, 3, 13, 20]", arvore3.search(1).node.toString());
		Assert.assertEquals("No Procurado Incorreto.", "[36, 49]", arvore3.search(36).node.toString());
		Assert.assertEquals("No Procurado Incorreto.", "[66, 71, 77, 95, 98]", arvore3.search(66).node.toString());
		arvore3.remove(1);
		arvore3.remove(20);
		arvore3.remove(98);
		arvore3.remove(95);
		Assert.assertEquals("No Procurado Incorreto.", "[22, 52]", arvore3.search(22).node.toString());
		Assert.assertEquals("No Procurado Incorreto.", "[2, 3, 13]", arvore3.search(2).node.toString());
		Assert.assertEquals("No Procurado Incorreto.", "[36, 49]", arvore3.search(36).node.toString());
		Assert.assertEquals("No Procurado Incorreto.", "[66, 71, 77]", arvore3.search(66).node.toString());	
	}
	
	@Test
	public void TestInsert(){
		Assert.assertEquals("Tamanho Inicial Incorreto.", 0, arvore1.size());
		arvore1.insert(1, 1);
		arvore1.insert(2, 2);
		arvore1.insert(5, 5);
		arvore1.insert(10, 10);
		arvore1.insert(7, 7);
		Assert.assertEquals("Tamanho Incorreto.", 5, arvore1.size());

		Assert.assertEquals("Tamanho Inicial Incorreto.", 0, arvore2.size());
		arvore2.insert(11, 11);
		arvore2.insert(13, 13);
		arvore2.insert(20, 20);
		arvore2.insert(17, 17);
		arvore2.insert(21, 21);
		arvore2.insert(22, 22);
		arvore2.insert(23, 23);
		arvore2.insert(19, 19);
		arvore2.insert(8, 8);
		arvore2.insert(9, 9);
		arvore2.insert(0, 0);
		arvore2.insert(25, 25);
		arvore2.insert(2, 2);
		arvore2.insert(3, 3);
		arvore2.insert(4, 4);
		arvore2.insert(26, 26);
		Assert.assertEquals("Tamanho Incorreto.", 16, arvore2.size());
		
		Assert.assertEquals("Tamanho Inicial Incorreto.", 0, arvore3.size());
		arvore3.insert(55, 55);
		arvore3.insert(22, 22);
		arvore3.insert(35, 35);
		arvore3.insert(89, 89);
		arvore3.insert(12, 12);
		arvore3.insert(17, 17);
		arvore3.insert(2, 2);
		arvore3.insert(4, 4);
		arvore3.insert(49, 49);
		arvore3.insert(96, 96);
		arvore3.insert(34, 34);
		arvore3.insert(11, 11);
		arvore3.insert(79, 79);
		arvore3.insert(1, 1);
		arvore3.insert(67, 67);
		arvore3.insert(40, 40);
		arvore3.insert(20, 20);
		arvore3.insert(82, 82);
		Assert.assertEquals("Tamanho Inicial Incorreto.", 18, arvore3.size());
	}
	
	@Test
	public void TestArvoreDoArquivo(){
		//FOI ADICIONADO [20, 15, 13, 3, 7, 9]
		//FOI REMOVIDO [3]
		arvore1.leituraArquivo(diretorioArquivo1, arvore1);
		Assert.assertEquals("Raiz Incorreta.", "[9, 15]", arvore1.getRoot().toString());
		Assert.assertEquals("Filhos da Raiz Incorreto.", "[[7], [13], [20]]", arvore1.getRoot().getChildren().toString());
		Assert.assertEquals("No-Filho Central Incorreto.", "[7]", arvore1.search(7).node.toString());
		Assert.assertEquals("No-Filho Direito Incorreto.", "[20]", arvore1.search(20).node.toString());
		
		//FOI ADICIONADO [10, 20, 30, 40, 50, 5, 15, 25, 35]
		//FOI REMOVIDO [10, 20]
		arvore2.leituraArquivo(diretorioArquivo2, arvore2);
		Assert.assertEquals("Raiz Incorreta.", "[30]", arvore2.getRoot().toString());
		Assert.assertEquals("Filhos da Raiz Incorreto.", "[[5, 15, 25], [35, 40, 50]]", arvore2.getRoot().getChildren().toString());
		Assert.assertEquals("No-Filho Esquerdo Incorreto.", "[5, 15, 25]", arvore2.search(5).node.toString());
		Assert.assertEquals("No-Filho Direito Incorreto.", "[35, 40, 50]", arvore2.search(35).node.toString());	
	
		//FOI ADICIONADO [22, 33, 37, 2, 17, 19, 65, 55, 40, 78, 98, 90, 21]
		//FOI REMOVIDO [98, 21]
		arvore3.leituraArquivo(diretorioArquivo3, arvore3);
		Assert.assertEquals("Raiz Incorreta.", "[22, 55]", arvore3.getRoot().toString());
		Assert.assertEquals("Filhos da Raiz Incorreto.", "[[2, 17, 19], [33, 37, 40], [65, 78, 90]]", arvore3.getRoot().getChildren().toString());
		Assert.assertEquals("No-Filho Esquerdo Incorreto.", "[2, 17, 19]", arvore3.search(2).node.toString());
		Assert.assertEquals("No-Filho Cental Incorreto.", "[33, 37, 40]", arvore3.search(33).node.toString());
		Assert.assertEquals("No-Filho Direito Incorreto.", "[65, 78, 90]", arvore3.search(65).node.toString());	
	}
	
	@Test
	public void TestRemoveFolha1(){
		//-------------REMOVENDO ELEMENTO EM UM FOLHA - NAO ACONTECE UNDERFLOW SLIDE 26-------------
		arvore1.insert(10, 10);
		arvore1.insert(12, 12);
		arvore1.insert(15, 15);
		arvore1.insert(14, 14);
		arvore1.insert(16, 16);
		arvore1.insert(20, 20);
		//RAIZ
		Assert.assertEquals("Raiz Incorreta.", "[12, 15]", arvore1.getRoot().toString());
		//FILHOS DA RAIZ
		Assert.assertEquals("Filhos da Raiz Incorreto.", "[[10], [14], [16, 20]]", arvore1.getRoot().getChildren().toString());
		//NO-FILHO ESQUERDO
		Assert.assertEquals("No-Filho Esquerdo Incorreto.", "[10]", arvore1.search(10).node.toString());
		//NO-FILHO CENTRAL
		Assert.assertEquals("No-Filho Central Incorreto.", "[14]", arvore1.search(14).node.toString());
		//NO-FILHO DIREITO
		Assert.assertEquals("No-Filho Direito Incorreto.", "[16, 20]", arvore1.search(16).node.toString());
		
		arvore1.remove(16);
		
		//RAIZ APOS REMOCAO
		Assert.assertEquals("Raiz Incorreta.", "[12, 15]", arvore1.getRoot().toString());
		//FILHOS DA RAIZ APOS REMOCAO
		Assert.assertEquals("Filhos da Raiz Incorreto.", "[[10], [14], [20]]", arvore1.getRoot().getChildren().toString());
		//NO-FILHO ESQUERDO APOS REMOCAO
		Assert.assertEquals("No-Filho Esquerdo Incorreto.", "[10]", arvore1.search(10).node.toString());
		//NO-FILHO CENTRAL APOS REMOCAO
		Assert.assertEquals("No-Filho Central Incorreto.", "[14]", arvore1.search(14).node.toString());
		//NO-FILHO DIREITO APOS REMOCAO
		Assert.assertEquals("No-Filho Direito Incorreto.", "[20]", arvore1.search(20).node.toString());
		
		arvore2.insert(1,1);
		arvore2.insert(8,8);
		arvore2.insert(15,15);
		arvore2.insert(36,36);
		arvore2.insert(22,22);
		arvore2.insert(29,29);
		arvore2.insert(43,43);
		arvore2.insert(47,47);
		arvore2.insert(50,50);
		//RAIZ
		Assert.assertEquals("Raiz Incorreta.", "[15, 36]", arvore2.getRoot().toString());
		//FILHOS DA RAIZ
		Assert.assertEquals("Filhos da Raiz Incorreto.", "[[1, 8], [22, 29], [43, 47, 50]]", arvore2.getRoot().getChildren().toString());
		//NO-FILHO ESQUERDO
		Assert.assertEquals("No-Filho Esquerdo Incorreto.", "[1, 8]", arvore2.search(1).node.toString());
		//NO-FILHO CENTRAL
		Assert.assertEquals("No-Filho Central Incorreto.", "[15, 36]", arvore2.search(15).node.toString());
		//NO-FILHO DIREITO
		Assert.assertEquals("No-Filho Direito Incorreto.", "[43, 47, 50]", arvore2.search(43).node.toString());
		
		arvore2.remove(50);
		
		//RAIZ APOS REMOCAO
		Assert.assertEquals("Raiz Incorreta.", "[15, 36]", arvore2.getRoot().toString());
		//FILHOS DA RAIZ APOS REMOCAO
		Assert.assertEquals("Filhos da Raiz Incorreto.", "[[1, 8], [22, 29], [43, 47]]", arvore2.getRoot().getChildren().toString());
		//NO-FILHO ESQUERDO APOS REMOCAO
		Assert.assertEquals("No-Filho Esquerdo Incorreto.", "[1, 8]", arvore2.search(1).node.toString());
		//NO-FILHO CENTRAL APOS REMOCAO
		Assert.assertEquals("No-Filho Central Incorreto.", "[15, 36]", arvore2.search(15).node.toString());
		//NO-FILHO DIREITO APOS REMOCAO
		Assert.assertEquals("No-Filho Direito Incorreto.", "[43, 47]", arvore2.search(43).node.toString());
	
		arvore3.insert(50, 50);
		arvore3.insert(23, 23);
		arvore3.insert(27, 27);
		arvore3.insert(29, 29);
		arvore3.insert(87, 87);
		arvore3.insert(77, 77);
		arvore3.insert(49, 49);
		arvore3.insert(17, 17);
		arvore3.insert(13, 13);
		arvore3.insert(15, 15);
		
		arvore3.remove(23);
		
		//RAIZ APOS REMOCAO
		Assert.assertEquals("Raiz Incorreta.", "[27, 50]", arvore3.getRoot().toString());
		//FILHOS DA RAIZ APOS REMOCAO
		Assert.assertEquals("Filhos da Raiz Incorreto.", "[[13, 15, 17], [29, 49], [77, 87]]", arvore3.getRoot().getChildren().toString());
		//NO-FILHO ESQUERDO APOS REMOCAO
		Assert.assertEquals("No-Filho Esquerdo Incorreto.", "[13, 15, 17]", arvore3.search(13).node.toString());
		//NO-FILHO CENTRAL APOS REMOCAO
		Assert.assertEquals("No-Filho Central Incorreto.", "[29, 49]", arvore3.search(29).node.toString());
		//NO-FILHO DIREITO APOS REMOCAO
		Assert.assertEquals("No-Filho Direito Incorreto.", "[77, 87]", arvore3.search(77).node.toString());
	}
	
	@Test
	public void TestRemoveFolha2(){
		//-------------REMOVENDO ELEMENTO EM UM FOLHA - ACONTECE UNDERFLOW SLIDE 27-------------
		arvore1.insert(10, 10);
		arvore1.insert(12, 12);
		arvore1.insert(15, 15);
		arvore1.insert(14, 14);
		arvore1.insert(20, 20);
		//RAIZ
		Assert.assertEquals("Raiz Incorreta.", "[12, 15]", arvore1.getRoot().toString());
		//FILHOS DA RAIZ
		Assert.assertEquals("Filhos da Raiz Incorreto.", "[[10], [14], [20]]", arvore1.getRoot().getChildren().toString());
		//NO-FILHO ESQUERDO
		Assert.assertEquals("No-Filho Esquerdo Incorreto.", "[10]", arvore1.search(10).node.toString());
		//NO-FILHO CENTRAL
		Assert.assertEquals("No-Filho Central Incorreto.", "[14]", arvore1.search(14).node.toString());
		//NO-FILHO DIREITO
		Assert.assertEquals("No-Filho Direito Incorreto.", "[20]", arvore1.search(20).node.toString());
		
		arvore1.remove(14);
		
		//RAIZ APOS REMOCAO
		Assert.assertEquals("Raiz Incorreta.", "[15]", arvore1.getRoot().toString());
		//FILHOS DA RAIZ APOS REMOCAO
		Assert.assertEquals("Filhos da Raiz Incorreto.", "[[10, 12], [20]]", arvore1.getRoot().getChildren().toString());
		//NO-FILHO ESQUERDO APOS REMOCAO
		Assert.assertEquals("No-Filho Esquerdo Incorreto.", "[10, 12]", arvore1.search(10).node.toString());
		//NO-FILHO DIREITO APOS REMOCAO
		Assert.assertEquals("No-Filho Direito Incorreto.", "[20]", arvore1.search(20).node.toString());
		
		arvore2.insert(1,1);
		arvore2.insert(8,8);
		arvore2.insert(15,15);
		arvore2.insert(36,36);
		arvore2.insert(22,22);
		arvore2.insert(29,29);
		arvore2.insert(43,43);
		arvore2.insert(47,47);
		arvore2.insert(50,50);
		//RAIZ
		Assert.assertEquals("Raiz Incorreta.", "[15, 36]", arvore2.getRoot().toString());
		//FILHOS DA RAIZ
		Assert.assertEquals("Filhos da Raiz Incorreto.", "[[1, 8], [22, 29], [43, 47, 50]]", arvore2.getRoot().getChildren().toString());
		//NO-FILHO ESQUERDO
		Assert.assertEquals("No-Filho Esquerdo Incorreto.", "[1, 8]", arvore2.search(1).node.toString());
		//NO-FILHO CENTRAL
		Assert.assertEquals("No-Filho Central Incorreto.", "[15, 36]", arvore2.search(15).node.toString());
		//NO-FILHO DIREITO
		Assert.assertEquals("No-Filho Direito Incorreto.", "[43, 47, 50]", arvore2.search(43).node.toString());
		
		arvore2.remove(29);
		
		//RAIZ APOS REMOCAO
		Assert.assertEquals("Raiz Incorreta.", "[15, 43]", arvore2.getRoot().toString());
		//FILHOS DA RAIZ APOS REMOCAO
		Assert.assertEquals("Filhos da Raiz Incorreto.", "[[1, 8], [22, 36], [47, 50]]", arvore2.getRoot().getChildren().toString());
		//NO-FILHO ESQUERDO APOS REMOCAO
		Assert.assertEquals("No-Filho Esquerdo Incorreto.", "[1, 8]", arvore2.search(1).node.toString());
		//NO-FILHO CENTRAL APOS REMOCAO
		Assert.assertEquals("No-Filho Central Incorreto.", "[22, 36]", arvore2.search(22).node.toString());
		//NO-FILHO DIREITO APOS REMOCAO
		Assert.assertEquals("No-Filho Direito Incorreto.", "[47, 50]", arvore2.search(47).node.toString());
	}
	
	@Test
	public void TestRemoveFolha3(){
		//-------------REMOVENDO ELEMENTO EM UM FOLHA - ACONTECE UNDERFLOW SLIDE 28-------------
		arvore1.insert(52, 52);
		arvore1.insert(17, 17);
		arvore1.insert(33, 33);
		arvore1.insert(22, 22);
		arvore1.insert(24, 24);
		arvore1.insert(30, 30);
		
		//RAIZ
		Assert.assertEquals("Raiz Incorreta.", "[22, 33]", arvore1.getRoot().toString());
		//FILHOS DA RAIZ
		Assert.assertEquals("Filhos da Raiz Incorreto.", "[[17], [24, 30], [52]]", arvore1.getRoot().getChildren().toString());
		//NO-FILHO ESQUERDO
		Assert.assertEquals("No-Filho Esquerdo Incorreto.", "[17]", arvore1.search(17).node.toString());
		//NO-FILHO CENTRAL
		Assert.assertEquals("No-Filho Central Incorreto.", "[24, 30]", arvore1.search(24).node.toString());
		//NO-FILHO DIREITO
		Assert.assertEquals("No-Filho Direito Incorreto.", "[52]", arvore1.search(52).node.toString());
		
		arvore1.remove(52);

		//RAIZ APOS REMOCAO
		Assert.assertEquals("Raiz Incorreta.", "[22, 30]", arvore1.getRoot().toString());
		//FILHOS DA RAIZ APOS REMOCAO
		Assert.assertEquals("Filhos da Raiz Incorreto.", "[[17], [24], [33]]", arvore1.getRoot().getChildren().toString());
		//NO-FILHO ESQUERDO APOS REMOCAO
		Assert.assertEquals("No-Filho Esquerdo Incorreto.", "[17]", arvore1.search(17).node.toString());
		//NO-FILHO CENTRAL APOS REMOCAO
		Assert.assertEquals("No-Filho Central Incorreto.", "[24]", arvore1.search(24).node.toString());
		//NO-FILHO DIREITO APOS REMOCAO
		Assert.assertEquals("No-Filho Direito Incorreto.", "[33]", arvore1.search(33).node.toString());
	
		arvore2.insert(1,1);
		arvore2.insert(8,8);
		arvore2.insert(15,15);
		arvore2.insert(43,43);
		arvore2.insert(22,22);
		arvore2.insert(36,36);
		arvore2.insert(47,47);
		arvore2.insert(50,50);
		//RAIZ
		Assert.assertEquals("Raiz Incorreta.", "[15, 43]", arvore2.getRoot().toString());
		//FILHOS DA RAIZ
		Assert.assertEquals("Filhos da Raiz Incorreto.", "[[1, 8], [22, 36], [47, 50]]", arvore2.getRoot().getChildren().toString());
		//NO-FILHO ESQUERDO
		Assert.assertEquals("No-Filho Esquerdo Incorreto.", "[1, 8]", arvore2.search(1).node.toString());
		//NO-FILHO CENTRAL
		Assert.assertEquals("No-Filho Central Incorreto.", "[22, 36]", arvore2.search(22).node.toString());
		//NO-FILHO DIREITO
		Assert.assertEquals("No-Filho Direito Incorreto.", "[47, 50]", arvore2.search(47).node.toString());
		
		arvore2.remove(22);
		
		//RAIZ APOS REMOCAO
		Assert.assertEquals("Raiz Incorreta.", "[43]", arvore2.getRoot().toString());
		//FILHOS DA RAIZ APOS REMOCAO
		Assert.assertEquals("Filhos da Raiz Incorreto.", "[[1, 8, 15, 36], [47, 50]]", arvore2.getRoot().getChildren().toString());
		//NO-FILHO ESQUERDO APOS REMOCAO
		Assert.assertEquals("No-Filho Esquerdo Incorreto.", "[1, 8, 15, 36]", arvore2.search(1).node.toString());
		//NO-FILHO DIREITO APOS REMOCAO
		Assert.assertEquals("No-Filho Direito Incorreto.", "[47, 50]", arvore2.search(47).node.toString());
		
		arvore2.remove(47);
		
		//RAIZ APOS REMOCAO
		Assert.assertEquals("Raiz Incorreta.", "[36]", arvore2.getRoot().toString());
		//FILHOS DA RAIZ APOS REMOCAO
		Assert.assertEquals("Filhos da Raiz Incorreto.", "[[1, 8, 15], [43, 50]]", arvore2.getRoot().getChildren().toString());
		//NO-FILHO ESQUERDO APOS REMOCAO
		Assert.assertEquals("No-Filho Esquerdo Incorreto.", "[1, 8, 15]", arvore2.search(1).node.toString());
		//NO-FILHO DIREITO APOS REMOCAO
		Assert.assertEquals("No-Filho Direito Incorreto.", "[43, 50]", arvore2.search(43).node.toString());
	}
	
	@Test
	public void TestRemoveFolha4(){
		//-------------REMOVENDO ELEMENTO EM UM FOLHA - ACONTECE UNDERFLOW SLIDE 29-------------
		arvore1.insert(1, 1);
		arvore1.insert(4, 4);
		arvore1.insert(6, 6);
		arvore1.insert(33, 33);
		//RAIZ
		Assert.assertEquals("Raiz Incorreta.", "[4]", arvore1.getRoot().toString());
		//FILHOS DA RAIZ
		Assert.assertEquals("Filhos da Raiz Incorreto.", "[[1], [6, 33]]", arvore1.getRoot().getChildren().toString());
		//NO-FILHO ESQUERDO
		Assert.assertEquals("No-Filho Esquerdo Incorreto.", "[1]", arvore1.search(1).node.toString());
		//NO-FILHO DIREITO
		Assert.assertEquals("No-Filho Direito Incorreto.", "[6, 33]", arvore1.search(6).node.toString());
		
		arvore1.remove(33);
		
		//RAIZ APOS REMOCAO
		Assert.assertEquals("Raiz Incorreta.", "[4]", arvore1.getRoot().toString());
		//FILHOS DA RAIZ APOS REMOCAO
		Assert.assertEquals("Filhos da Raiz Incorreto.", "[[1], [6]]", arvore1.getRoot().getChildren().toString());
		//NO-FILHO ESQUERDO APOS REMOCAO
		Assert.assertEquals("No-Filho Esquerdo Incorreto.", "[1]", arvore1.search(1).node.toString());
		//NO-FILHO DIREITO APOS REMOCAO
		Assert.assertEquals("No-Filho Direito Incorreto.", "[6]", arvore1.search(6).node.toString());
		
		arvore2.insert(1,1);
		arvore2.insert(67,67);
		arvore2.insert(70,70);
		arvore2.insert(74,74);
		arvore2.insert(76,76);
		//RAIZ
		Assert.assertEquals("Raiz Incorreta.", "[70]", arvore2.getRoot().toString());
		//FILHOS DA RAIZ
		Assert.assertEquals("Filhos da Raiz Incorreto.", "[[1, 67], [74, 76]]", arvore2.getRoot().getChildren().toString());
		//NO-FILHO ESQUERDO
		Assert.assertEquals("No-Filho Esquerdo Incorreto.", "[1, 67]", arvore2.search(1).node.toString());
		//NO-FILHO DIREITO
		Assert.assertEquals("No-Filho Direito Incorreto.", "[74, 76]", arvore2.search(74).node.toString());
		
		arvore2.remove(74);
		
		//RAIZ APOS REMOCAO
		Assert.assertEquals("Raiz Incorreta.", "[1, 67, 70, 76]", arvore2.getRoot().toString());
	}
	
	@Test
	public void TestRemoveNoInterno1(){
		//-------------REMOVENDO ELEMENTO EM UM NO INTERNO - ACONTECE UNDERFLOW SLIDE 30-------------
		arvore1.insert(1,1);
		arvore1.insert(3,3);
		arvore1.insert(12,12);
		arvore1.insert(15, 15);
		arvore1.insert(16, 16);
		arvore1.insert(22, 22);
		arvore1.insert(55, 55);
		arvore1.insert(33, 33);
		arvore1.insert(67, 67);
		//RAIZ
		Assert.assertEquals("Raiz Incorreta.", "[15]", arvore1.getRoot().toString());
		//FILHOS DA RAIZ
		Assert.assertEquals("Filhos da Raiz Incorreto.", "[[3], [22, 55]]", arvore1.getRoot().getChildren().toString());
		//NO-FILHO ESQUERDO
		Assert.assertEquals("No-Filho Esquerdo Incorreto.", "[3]", arvore1.search(3).node.toString());
		//NO-FILHO DIREITO
		Assert.assertEquals("No-Filho Direito Incorreto.", "[22, 55]", arvore1.search(22).node.toString());
		
		//NO-FILHO DO NO-FILHO ESQUERDO
		Assert.assertEquals("No-Filho do No-Filho Esquerdo Incorreto.", "[[1], [12]]", arvore1.search(3).node.getChildren().toString());
		//NO-FILHO ESQUERDO DO NO-FILHO ESQUERDO
		Assert.assertEquals("No-Filho Esquerdo do No-Filho Esquerdo Incorreto.", "[1]", arvore1.search(1).node.toString());
		//NO-FILHO DIREITO DO NO-FILHO ESQUERDO
		Assert.assertEquals("No-Filho Direito do No-Filho Esquerdo.", "[12]", arvore1.search(12).node.toString());
		
		//NO-FILHO DO NO-FILHO DIREITO
		Assert.assertEquals("No-Filho do No-Filho Direito Incorreto.", "[[16], [33], [67]]", arvore1.search(22).node.getChildren().toString());
		//NO-FILHO ESQUERDO DO NO-FILHO DIREITO
		Assert.assertEquals("No-Filho Esquerdo do No-Filho Direito Incorreto.", "[16]", arvore1.search(16).node.toString());
		//NO-FILHO CENTRAL DO NO-FILHO DIREITO
		Assert.assertEquals("No-Filho Central do No-Filho Direito Incorreto.", "[33]", arvore1.search(33).node.toString());
		//NO-FILHO DIREITO DO NO-FILHO DIREITO
		Assert.assertEquals("No-Filho Direito do No-Filho Direito Incorreto.", "[67]", arvore1.search(67).node.toString());
		
		arvore1.remove(3);
		
		//RAIZ APOS REMOCAO
		Assert.assertEquals("Raiz Incorreta.", "[22]", arvore1.getRoot().toString());
		//FILHOS DA RAIZ APOS REMOCAO
		Assert.assertEquals("Filhos da Raiz Incorreto.", "[[15], [55]]", arvore1.getRoot().getChildren().toString());
		//NO-FILHO ESQUERDO APOS REMOCAO
		Assert.assertEquals("No-Filho Esquerdo Incorreto.", "[15]", arvore1.search(15).node.toString());
		//NO-FILHO DIREITO APOS REMOCAO
		Assert.assertEquals("No-Filho Direito Incorreto.", "[55]", arvore1.search(55).node.toString());
		
		//NOS-FILHOS DO NO-FILHO ESQUERDO
		Assert.assertEquals("Nos-Filhos do No-Filho Esquerdo Incorreto.", "[[1, 12], [16]]", arvore1.search(15).node.getChildren().toString());
		//NO-FILHO ESQUERDO DO NO-FILHO ESQUERDO
		Assert.assertEquals("No-Filho Esquerdo do No-Filho Esquerdo Incorreto.", "[1, 12]", arvore1.search(1).node.toString());
		//NO-FILHO DIREITO DO NO-FILHO ESQUERDO
		Assert.assertEquals("No-Filho Direito do No-Filho Esquerdo Incorreto.", "[16]", arvore1.search(16).node.toString());
		//NO-FILHO ESQUERDO DO NO-FILHO DIREITO
		Assert.assertEquals("No-Filho Esquerdo do No-Filho Direito Incorreto.", "[33]", arvore1.search(33).node.toString());
		//NO-FILHO DIREITO DO NO-FILHO DIREITO
		Assert.assertEquals("No-Filho Direito do No-Filho Direito Incorreto.", "[67]", arvore1.search(67).node.toString());
		
		arvore2.insert(1,1);
		arvore2.insert(8,8);
		arvore2.insert(15,15);
		arvore2.insert(36,36);
		arvore2.insert(20,20);
		arvore2.insert(28,28);
		arvore2.insert(43,43);
		arvore2.insert(45,45);
		arvore2.insert(47,47);
		arvore2.insert(49,49);
		arvore2.insert(50,50);
		arvore2.insert(64,64);
		arvore2.insert(70,70);
		arvore2.insert(66,66);
		arvore2.insert(67,67);
		arvore2.insert(74,74);
		arvore2.insert(76,76);
		//RAIZ
		Assert.assertEquals("Raiz Incorreta.", "[47]", arvore2.getRoot().toString());
		//FILHOS DA RAIZ
		Assert.assertEquals("Filhos da Raiz Incorreto.", "[[15, 36], [64, 70]]", arvore2.getRoot().getChildren().toString());
		//NO-FILHO ESQUERDO
		Assert.assertEquals("No-Filho Esquerdo Incorreto.", "[15, 36]", arvore2.search(15).node.toString());
		//NO-FILHO DIREITO
		Assert.assertEquals("No-Filho Direito Incorreto.", "[64, 70]", arvore2.search(64).node.toString());
		
		//NO-FILHO DO NO-FILHO ESQUERDO
		Assert.assertEquals("No-Filho do No-Filho Esquerdo Incorreto.", "[[1, 8], [20, 28], [43, 45]]", arvore2.search(15).node.getChildren().toString());
		//NO-FILHO ESQUERDO DO NO-FILHO ESQUERDO
		Assert.assertEquals("No-Filho Esquerdo do No-Filho Esquerdo Incorreto.", "[1, 8]", arvore2.search(1).node.toString());
		//NO-FILHO CENTRAL DO NO-FILHO ESQUERDO
		Assert.assertEquals("No-Filho Central do No-Filho Esquerdo.", "[20, 28]", arvore2.search(20).node.toString());
		//NO-FILHO DIREITO DO NO-FILHO ESQUERDO
		Assert.assertEquals("No-Filho Direito do No-Filho Esquerdo.", "[43, 45]", arvore2.search(43).node.toString());
		
		//NO-FILHO DO NO-FILHO DIREITO
		Assert.assertEquals("No-Filho do No-Filho Direito Incorreto.", "[[49, 50], [66, 67], [74, 76]]", arvore2.search(64).node.getChildren().toString());
		//NO-FILHO ESQUERDO DO NO-FILHO DIREITO
		Assert.assertEquals("No-Filho Esquerdo do No-Filho Direito Incorreto.", "[49, 50]", arvore2.search(49).node.toString());
		//NO-FILHO CENTRAL DO NO-FILHO DIREITO
		Assert.assertEquals("No-Filho Central do No-Filho Direito Incorreto.", "[66, 67]", arvore2.search(66).node.toString());
		//NO-FILHO DIREITO DO NO-FILHO DIREITO
		Assert.assertEquals("No-Filho Direito do No-Filho Direito Incorreto.", "[74, 76]", arvore2.search(74).node.toString());
		
		arvore2.remove(15);
		
		//RAIZ APOS REMOCAO
		Assert.assertEquals("Raiz Incorreta.", "[36, 47, 64, 70]", arvore2.getRoot().toString());
		//FILHOS DA RAIZ APOS REMOCAO
		Assert.assertEquals("Filhos da Raiz Incorreto.", "[[1, 8, 20, 28], [43, 45], [49, 50], [66, 67], [74, 76]]", arvore2.getRoot().getChildren().toString());
		//NO-FILHO MAIS ESQUERDO APOS REMOCAO
		Assert.assertEquals("No-Filho Mais Esquerdo Incorreto.", "[1, 8, 20, 28]", arvore2.search(1).node.toString());
		//NO-FILHO ESQUERDO APOS REMOCAO
		Assert.assertEquals("No-Filho Esquerdo Incorreto.", "[43, 45]", arvore2.search(43).node.toString());
		//NO-FILHO CENTRAL APOS REMOCAO
		Assert.assertEquals("No-Filho Central Incorreto.", "[49, 50]", arvore2.search(49).node.toString());
		//NO-FILHO DIREITO APOS REMOCAO
		Assert.assertEquals("No-Filho Direito Incorreto.", "[66, 67]", arvore2.search(66).node.toString());
		//NO-FILHO MAIS DIREITO APOS REMOCAO
		Assert.assertEquals("No-Filho Mais Direito Incorreto.", "[74, 76]", arvore2.search(74).node.toString());
	}
}