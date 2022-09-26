package adt.rbtree;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import adt.rbtree.RBNode.Colour;
import adt.util.TreeUnderflowException;

/**
 * Classe testes da classe RBTreeImpl.
 * 
 * @author Flavia Gangorra, Igor Candeia, Adilson Junior, Julio Andherson
 *
 */
public class TestRBTreeImpl {

	private RBNode<Integer, Integer> no = new RBNode<Integer, Integer>();
	
	public TestRBTreeImpl() {
    }

    @BeforeClass
    public static void IniciarClass() throws Exception {
    }

    @AfterClass
    public static void FinalizarClass() throws Exception {
    }

    @Before
    public void Iniciar() {
        no = new RBNode<Integer, Integer>();
        arvorePV = new RBTreeImpl<Integer, Integer>();
    }

    /**
     * Teste do metodo isEmpty, verifica se um no eh vazio, da classe RBNode.
     */
    @Test
    public void testIsEmpty() {
        Iniciar();

        assertTrue(no.isEmpty());
        assertTrue(no.getLeft() == null);
        assertTrue(no.getRight() == null);
        assertTrue(no.isEmpty());
        no.setRight(new RBNode<Integer,Integer>());//um no nil eh como se nao tivesse nenhum no, so sera considerado nao nil quando possuir uma chave
        assertTrue(no.isEmpty());

        no.setRight(null);
        assertTrue(no.isEmpty());
        no.setLeft(new RBNode<Integer,Integer>());
        assertTrue(no.isEmpty());

        no.setRight(new RBNode<Integer,Integer>());
        assertTrue(no.isEmpty());

    }

    /**
     * Teste se um no eh folha, verifica se um no nao possui filhos, da classe RBNode.
     */
    @Test
    public void testIsLeaf() {
        Iniciar();
        
        assertTrue(no.isEmpty());
        assertFalse(no.isLeaf());
        no.setKey(Integer.MAX_VALUE);
        assertFalse(no.isEmpty());
        assertTrue(no.isLeaf());//no continua sendo folha, pois no nil nao eh folha

        no.setRight(new RBNode<Integer, Integer>());
        assertFalse(no.isEmpty());
        assertTrue(no.isLeaf());

        no.setLeft(new RBNode<Integer, Integer>());
        assertFalse(no.isEmpty());
        assertTrue(no.isLeaf());

        no.setRight(null);
        assertFalse(no.isEmpty());
        assertTrue(no.isLeaf());


    }

    /**
     * Teste do metodo testToString, da classe RBNode.
     */
    @Test
    public void testToString() {
        Iniciar();
        no.setKey(100);
        no.setRight(new RBNode<Integer, Integer>());//NIL
        no.setLeft(new RBNode<Integer, Integer>());//NIL
        assertTrue(no.getColour() == Colour.BLACK);
        assertEquals("(100,B)", no.toString());

        no.setColour(Colour.RED);
        assertEquals("(100,R)", no.toString());
        assertTrue(no.getColour() == Colour.RED);
        
        no.setColour(Colour.BLACK);
        assertTrue(no.getColour() == Colour.BLACK);
        
        no.setColour(Colour.RED);
        assertTrue(no.getColour() == Colour.RED);

    }

    /**
     * Teste do metodo getParent, que retorna o pai de um no, na classe RBNode.
     */
    @Test
    public void testGetPai() {
        Iniciar();
        assertTrue(no.getParent() == null);
        RBNode<Integer, Integer> pai = new RBNode<Integer, Integer>();
        pai.setKey(10);
        no.setParent(pai);
        assertTrue(no.getParent() == pai);
        assertTrue(no.getParent().getParent() == null);
    }

    /**
     * Teste do metodo getLeft da classe RBNode, que retorna o filho a esquerda de um no.
     */
    @Test
    public void testGetLeft() {
        Iniciar();
        
        assertTrue(no.getLeft() == null);
        RBNode<Integer, Integer> node = new RBNode<Integer, Integer>();
        node.setKey(10);
        no.setLeft(node);
        assertTrue(no.getLeft() == node);
        assertTrue(no.getLeft().getLeft() == null);
    }

    /**
     * Teste do metodo getRight da classe RBNode, que retorna o filho a direita de um no.
     */
    @Test
    public void testGetRight() {
        Iniciar();

        assertTrue(no.getRight() == null);
        RBNode<Integer, Integer> node = new RBNode<Integer, Integer>();
        node.setKey(10);
        no.setRight(node);
        assertTrue(no.getRight() == node);
        assertTrue(no.getRight().getRight() == null);
    }

    /**
     * Teste do metodo getKey da classe RBNode, que retorna a chave de um no.
     */
    @Test
    public void testgetKey() {
    	Iniciar();

        assertTrue(no.getKey() == null);
        no.setKey(100);
        assertTrue(no.getKey().equals(100));
        no.setKey(284);
        assertTrue(no.getKey().equals(284));

    }

    /**
     * Teste do metodo getValue da classe RBNode, que retorna a valor de um no.
     */
    @Test
    public void testGetValue() {
    	Iniciar();

        assertTrue(no.getValue() == null);
        no.setValue(100);
        assertTrue(no.getValue().equals(100));
        no.setValue(587);
        assertTrue(no.getValue().equals(587));
    }

    /**
     * Teste do metodo setParent da classe RBNode, que modifica o pai os atributos do pai de um no.
     */
    @Test
    public void testSetParent() {
    	Iniciar();
        RBNode<Integer, Integer> pai = new RBNode<Integer, Integer>();
        pai.setKey(100);
        no.setParent(pai);
        assertTrue(no.getParent().equals(pai));
        RBNode<Integer, Integer> avo = new RBNode<Integer, Integer>();
        pai.setParent(avo);

    }

    /**
     * Teste do metodo setLeft da classe RBNode, que modifica os atributos do filho a esquerda de um no.
     */
    @Test
    public void testsetLeft() {
        Iniciar();
        RBNode<Integer, Integer> node = new RBNode<Integer, Integer>();
        node.setKey(100);
        no.setLeft(node);
        assertTrue(no.getLeft().equals(node));
        
        no.getLeft().setLeft(new RBNode<Integer, Integer>());
        assertTrue(no.getLeft().getLeft().equals(new RBNode<Integer, Integer>()));
    }

    /**
     * Teste do metodo setLRight da classe RBNode, que modifica os atributos do filho a direita de um no.
     */
    @Test
    public void testsetRight() {
        Iniciar();
        RBNode<Integer, Integer> node = new RBNode<Integer, Integer>();
        node.setKey(100);
        no.setRight(node);
        assertTrue(no.getRight().equals(node));
        no.getRight().setRight(new RBNode<Integer, Integer>());
        assertTrue(no.getRight().getRight().equals(new RBNode<Integer, Integer>()));
    }

    /**
     * Teste do metodo setKey da classe RBNode, que modifica a chave armazenada em um no.
     */
    @Test
    public void testsetKey() {
        Iniciar();

        assertTrue(no.getKey() == null);
        no.setKey(10);
        assertTrue(no.getKey() == 10);
        no.setKey(356);
        assertTrue(no.getKey() == 356);
    }

    /**
     * Teste do metodo setValue da classe RBNode, que modifica o valor armazenada em um no.
     */
    @Test
    public void testSetValue() {
        Iniciar();

        assertTrue(no.getValue() == null);
        no.setValue(25);
        assertTrue(no.getValue() == 25);
        no.setValue(78);
        assertTrue(no.getValue() == 78);
    }

    /**
     * Teste do metodo setColour da classe RBNode, que modifica o atributo de cor referente ao no.
     */
    @Test
    public void testSetBlack_And_SetRed() {
        Iniciar();
        
        no.setKey(1);
        assertTrue(no.getColour() == Colour.BLACK);
        no.setColour(Colour.RED);
        assertTrue(no.getColour() == Colour.RED);
        no.setColour(Colour.BLACK);
        assertTrue(no.getColour() == Colour.BLACK);
        
    }
    
    

	RBTreeImpl<Integer, Integer> arvorePV;
	
	/**
	 * Metodo verifyProperties da classe RBTreeImpl, que verifica se os cinco invariantes de uma arvore rubro-negra estao sendo mantidos.
	 */
    public void testaPropriedades(){
        // Testa as propriedades
        // - Todo no eh vermelho ou preto.
        // - A raiz eh preta.
        // - Toda folha eh Preta.
        // - Os filhos de um no vermelho, sao pretos.
    	// - todo caminho simples de um no a uma folha descendente contem mesmo numero de nos != NIL pretos(black-height)
        Assert.assertTrue(arvorePV.verifyProperties());
    }

    
    /**
     * Teste do metodo insert da classe RBTreeImpl, que insere novos nos na arvore.
     */
    @Test
    public void insertTest() {//throws ADTOverflowException {

        testaPropriedades();

        //Adiciona 10 elementos
        for (int i = 0; i < 10; i++) {
            arvorePV.insert(i, i * 2);
        }
        Assert.assertEquals(10, arvorePV.size());

        testaPropriedades();

        //Adiciona mais 2 elementos
        for (int i = 10; i < 15; i++) {
            arvorePV.insert(i, i * 2);
        }
        Assert.assertEquals(15, arvorePV.size());
        Assert.assertEquals(5, arvorePV.height());
        Assert.assertEquals(3, arvorePV.blackHeight());

        testaPropriedades();

        
        arvorePV.insert(15, 30);
        arvorePV.insert(14, 32);//nao adiciona
        arvorePV.insert(13, 34);//nao adiciona
        Assert.assertEquals(16, arvorePV.size());
        Assert.assertEquals(5, arvorePV.height());
        Assert.assertEquals(3, arvorePV.blackHeight());


        testaPropriedades();

    }


    /**
     *  Teste do metodo height da classe RBTreeImpl, que verifica a altura da arvorePV.
     */
    @Test
    public void height() {
        //OBS: os valores entre parenteses indicam nos vermelhos

        //	    Arvore      height     heightPreta
        //
        //     {Vazia}       {-1}         {0}
        Assert.assertEquals(-1, arvorePV.height());
        Assert.assertEquals(0, arvorePV.blackHeight());


        arvorePV.insert(1, 1);
        //	    Arvore      height     heightPreta
        //
        //        1           {0}          {1}
        Assert.assertEquals(0, arvorePV.height());
        Assert.assertEquals(1, arvorePV.blackHeight());

        arvorePV.insert(3, 6);
        //	    Arvore      height     heightPreta
        //
        //        1           {0}          {1}
        //         \
        //         (3)        {1}e(arvorePV.checkProperties());
        Assert.assertEquals(1, arvorePV.height());
        Assert.assertEquals(1, arvorePV.blackHeight());

        arvorePV.insert(8, 16);
        //	    Arvore      height     heightPreta
        //
        //        3           {0}          {1}
        //       / \
        //     (1)(8)        {1}
       
        Assert.assertEquals(1, arvorePV.height());
        Assert.assertEquals(1, arvorePV.blackHeight());


        for (int i = 0; i < 6; i++) {

            arvorePV.insert(i, i);
        }
        //    Arvore      height     heightPreta
        //
        //        3         {0}         {1}
        //      /   \
        //     1     5      {1}         {2}
        //    / \   / \
        //   (0)(2)(4)(8)   {2}         {2}
        //                  
        
        Assert.assertEquals(2, arvorePV.height());
        Assert.assertEquals(2, arvorePV.blackHeight());
        
        RBNode<Integer,Integer> no = new RBNode<Integer, Integer>();
        no.setKey(0);
        no.setValue(0);
        
        Assert.assertEquals(no.getKey(), arvorePV.minimum().getKey());
        Assert.assertEquals(no.getKey(), arvorePV.minimum().getKey());
        
        no.setKey(8);
        no.setValue(16);
        
        Assert.assertEquals(no.getValue(), arvorePV.maximun().getValue());
        
        Assert.assertEquals(no.getKey(), arvorePV.maximun().getKey());
        
    }

    /**
     *  Teste do metodo preOrder da classe RBTreeImpl, retorna um array com as chaves dos nos
     *  da arvorePV da seguinte mandeira: primeiro a raiz, depois elementos a esquerda e por
     *  ultimo elementos a direita .
     */
    @Test
    public void preOrdemTest(){
        Assert.assertEquals("[]", Arrays.toString(arvorePV.preOrder()));
        
        for(int i=0; i < 5; i++){
        	arvorePV.insert(i,i);
        }
        Assert.assertEquals("[1, 0, 3, 2, 4]", Arrays.toString(arvorePV.preOrder()));

        arvorePV.insert(9, 20);
        //     Arvore       height     heightPreta
        //
        //        1          {0}          {0}
        //      /   \
        //     0    (3)      {1}          {1}
        //          / \
        //         2   4     {2}          {2}
        //              \
        //              (9)  {3}
        Assert.assertEquals(3, arvorePV.height());
        Assert.assertEquals(2, arvorePV.blackHeight());
        
        Assert.assertEquals("[1, 0, 3, 2, 4, 9]", Arrays.toString(arvorePV.preOrder()));
    }
    
    
    /**
     *  Teste do metodo remove da classe RBTreeImpl, que remove nos de um arvorePV caso ela os contenha.
     * @throws TreeUnderflowException
     * 		caso a remocao seja executada em uma arvore ja vazia.
     */
    @Test
    public void RemoveTest()  {
    	
    	
    	arvorePV.insert(10, 0);
    	arvorePV.insert(3, 1);
    	arvorePV.insert(20, 2);
    	arvorePV.insert(15, 3);
    	arvorePV.insert(7, 4);
    	arvorePV.insert(1, 5);
    	arvorePV.insert(5, 6);
    	//    Arvore      height     heightPreta
        //
        //       10          {0}         {1}
        //      /   \
        //    (3)   20       {1}         {2}
        //    / \   /  
        //   1   7(15)       {2}         {2}
        //       / 
        //      (5)          {3}
    	Assert.assertEquals("[(10,B), (3,R), (1,B), (7,B), (5,R), (20,B), (15,R)]", arvorePV.preOrder2().toString());
    	Assert.assertEquals(3, arvorePV.height());
    	Assert.assertEquals(2, arvorePV.blackHeight());
    	
    	
    	arvorePV.remove(0);
    	 //    Arvore      height     heightPreta
        //
        //       10          {0}         {1}
        //      /   \
        //    (3)   20       {1}         {2}
        //    / \   /  
        //   1   7(15)       {2}         {2}
        //       / 
        //      (5)          {3}
    	Assert.assertEquals("[(10,B), (3,R), (1,B), (7,B), (5,R), (20,B), (15,R)]", arvorePV.preOrder2().toString());
    	Assert.assertEquals(3, arvorePV.height());
    	Assert.assertEquals(2, arvorePV.blackHeight());
    	
    	
    	
    	arvorePV.remove(10);

    	Assert.assertEquals("[(15,B), (3,R), (1,B), (7,B), (5,R), (20,B)]", arvorePV.preOrder2().toString());
    	Assert.assertEquals(3, arvorePV.height());
    	Assert.assertEquals(2, arvorePV.blackHeight());
    	
    	
    	arvorePV.remove(5);
    	//      Arvore      height     heightPreta
        //
        //       15          {0}         {1}
        //      /   \
        //    (3)   20       {1}         {2}
        //    / \    
        //   1   7           {2}         {2}
        //      
    	Assert.assertEquals("[(15,B), (3,R), (1,B), (7,B), (20,B)]", arvorePV.preOrder2().toString());
    	Assert.assertEquals(2, arvorePV.height());
    	Assert.assertEquals(2, arvorePV.blackHeight());
    	
    	
    	arvorePV.remove(7);
    	//      Arvore      height     heightPreta
        //
        //       15          {0}         {1}
        //      /   \
        //     3   20        {1}         {2}
        //    /     
        //   (1)             {2}         {2}
        //   
    	Assert.assertEquals("[(15,B), (3,B), (1,R), (20,B)]", arvorePV.preOrder2().toString());
    	Assert.assertEquals(2, arvorePV.height());
    	Assert.assertEquals(2, arvorePV.blackHeight());
    	
    	arvorePV.remove(1);
 
    	Assert.assertEquals("[(15,B), (3,B), (20,B)]", arvorePV.preOrder2().toString());
    	Assert.assertEquals(1, arvorePV.height());
    	Assert.assertEquals(2, arvorePV.blackHeight());
    	
    	
    	arvorePV.remove(3);
    	//      Arvore      height     heightPreta
        //
        //       15          {0}         {1}
        //         \
        //        (20)       {1}         {1}
    	Assert.assertEquals("[(15,B), (20,R)]", arvorePV.preOrder2().toString());
    	Assert.assertEquals(1, arvorePV.height());
    	Assert.assertEquals(1, arvorePV.blackHeight());
    	
    	
    	arvorePV.remove(15);
    	//      Arvore      height     heightPreta
        //
        //       20          {0}         {1}
    	Assert.assertEquals("[(20,B)]", arvorePV.preOrder2().toString());
    	Assert.assertEquals(0, arvorePV.height());
    	Assert.assertEquals(1, arvorePV.blackHeight());
        
    	
    	arvorePV.remove(20);
    	
    	Assert.assertEquals("[]", arvorePV.preOrder2().toString());
    	Assert.assertEquals(-1, arvorePV.height());
    	Assert.assertEquals(0, arvorePV.blackHeight());
    	
    	arvorePV.remove(0);
    	
        

    	Assert.assertEquals("[]", arvorePV.preOrder2().toString());
    	Assert.assertEquals(-1, arvorePV.height());
    	Assert.assertEquals(0, arvorePV.blackHeight());
    	

    }
    
    /**
     *  Teste do metodo posOrder da classe RBTreeImpl, retorna um array com as chaves dos nos
     *  da arvorePV da seguinte mandeira: primeiro elementos a esquerda, depois elementos a direita e por
     *  ultimo o elemento da raiz.
     */
    @Test
    public void posOrdem() {
    	
    	Assert.assertEquals("[]", Arrays.toString(arvorePV.preOrder()));
 
    	for(int i=0; i < 6; i++){
    		arvorePV.insert(i, i*3);
    	}
    	
    	
        Assert.assertEquals("[0, 2, 5, 4, 3, 1]", Arrays.toString(arvorePV.postOrder()));
        arvorePV.insert(9, 20);
        Assert.assertEquals("[0, 2, 4, 9, 5, 3, 1]", Arrays.toString(arvorePV.postOrder()));
    }

    
    /**
     *  Teste do metodo order da classe RBTreeImpl, retorna um array com as chaves dos nos
     *  da arvorePV da seguinte mandeira: primeiro elementos a esquerda, depois elementos o elemento da raiz e 
     *  por ultimo elementos a direita .
     */
    @Test
    public void emOrdem() {
    	
    	Assert.assertEquals("[]", Arrays.toString(arvorePV.order()));
    	
    	for(int i=1; i < 7;i++){
    		arvorePV.insert(i*2, i);
    	}
    	
        Assert.assertEquals("[2, 4, 6, 8, 10, 12]", Arrays.toString(arvorePV.order()));
        arvorePV.insert(9, 20);
        
        Assert.assertEquals("[2, 4, 6, 8, 9, 10, 12]", Arrays.toString(arvorePV.order()));
    }

    
   
    //////////////// Testa casos do fixUP /////////////////
   
    @Test
    public void Caso1FixUpInsert(){
    	arvorePV.insert(0, 0);
    	arvorePV.insert(1, 1);
    	arvorePV.insert(2, 2);
    	//ANTES DO FIXUP	
    	//      Arvore      height     heightPreta
        //
        //       0           {0}         {1}
        //         \
        //         (1)       {1}         {1}
    	//           \  
    	//           (2)     {2}         {1}
    	//APOS
    	//      Arvore      height     heightPreta
        //
        //       1           {0}         {1}
        //      /  \
        //    (0)  (2)       {1}         {1}
    	Assert.assertEquals("[(1,B), (0,R), (2,R)]", arvorePV.preOrder2().toString());
    	Assert.assertEquals(1, arvorePV.height());
    	Assert.assertEquals(1, arvorePV.blackHeight());
    	
    	arvorePV.insert(3, 3);
    	//      Arvore      height     heightPreta
        //
        //       1           {0}         {1}
        //      /  \
        //    (0)  (2)       {1}         {1}
    	//           \
    	//           (3)     {2}         {1}
    	
    	//APOS O FIXUP
    	//      Arvore      height     heightPreta
        //
        //       1           {0}         {1}
        //      /  \
        //     0    2        {1}         {1}
    	//           \
    	//           (3)     {2}         {1}
    	Assert.assertEquals("[(1,B), (0,B), (2,B), (3,R)]", arvorePV.preOrder2().toString());
    	Assert.assertEquals(2, arvorePV.height());
    	Assert.assertEquals(2, arvorePV.blackHeight());
    }
    
    
    
    @Test
    public void Caso1FixUpRemove(){
    	
  
    	arvorePV.insert(10, 0);
    	arvorePV.insert(12, 0);
    	arvorePV.insert(8, 0);
    	arvorePV.insert(11, 0);
    	arvorePV.insert(13, 0);
    	arvorePV.insert(14, 0);
    	
    	//      Arvore      height     heightPreta
        //
        //       10           {0}         {1}
        //      /  \
        //     8   (12)       {1}         {1}
    	//         /  \  
    	//        11  13      {2}         {2}
    	//             \
    	//            (14)    {3}         {2}
    	Assert.assertEquals("[(10,B), (8,B), (12,R), (11,B), (13,B), (14,R)]", arvorePV.preOrder2().toString());
    	Assert.assertEquals(3, arvorePV.height());
    	Assert.assertEquals(2, arvorePV.blackHeight());
    	
    
    	arvorePV.remove(8);
    	
    	
    	Assert.assertEquals("[(12,B), (10,B), (11,R), (13,B), (14,R)]", arvorePV.preOrder2().toString());
    	Assert.assertEquals(2, arvorePV.height());
    	Assert.assertEquals(2, arvorePV.blackHeight());
    	
    }
    
    @Test
    public void Caso2FixUPInsert() {
        arvorePV.insert(15, 0);
        arvorePV.insert(12, 0);
        arvorePV.insert(17, 0);
        arvorePV.insert(16, 0);
        arvorePV.insert(10, 0);
        arvorePV.insert(11, 0);
        //ANTES DO FIXUP
        //      Arvore      height     heightPreta
        //
        //        15          {0}         {1}
        //      /    \
        //    12     17       {1}         {2}
    	//   /       /    
    	// (10)    (16)       {2}         {2}
    	//    \
        //   (11)             {3}         {2}
        
        //APOS
        //      Arvore      height     heightPreta
        //
        //        15          {0}         {1}
        //      /    \
        //    11     17       {1}         {2}
    	//   /  \    /    
    	// (10)(12) (16)      {2}         {2}
        Assert.assertEquals("[(15,B), (11,B), (10,R), (12,R), (17,B), (16,R)]", arvorePV.preOrder2().toString());
        Assert.assertEquals(2, arvorePV.height());
        Assert.assertEquals(2, arvorePV.blackHeight());
        
    }

    @Test
    public void Caso2FixUpRemove(){
    	arvorePV.insert(5, 5);
        arvorePV.insert(3, 3);
        arvorePV.insert(4, 4);
        arvorePV.insert(6, 6);
        arvorePV.insert(7, 7);
        arvorePV.insert(8, 8);
        //      Arvore      height     heightPreta
        //
        //       4           {0}         {1}
        //      /  \
        //     3   (6)       {1}         {1}
    	//         /  \
    	//         5   7     {2}         {2}
        //              \ 
        //              (8)  {3}         {2}
        Assert.assertEquals("[(4,B), (3,B), (6,R), (5,B), (7,B), (8,R)]", arvorePV.preOrder2().toString());
        Assert.assertEquals(3, arvorePV.height());
        Assert.assertEquals(2, arvorePV.blackHeight());
        arvorePV.remove(7);
        
        Assert.assertEquals("[(4,B), (3,B), (6,B), (5,R), (8,R)]", arvorePV.preOrder2().toString());
        Assert.assertEquals(2, arvorePV.height());
        Assert.assertEquals(2, arvorePV.blackHeight());
    }

    @Test
    public void Caso3FixUpInsert(){
    	arvorePV.insert(11, 0);
        arvorePV.insert(8, 0);
        arvorePV.insert(12, 0);
        arvorePV.insert(9, 0);
        arvorePV.insert(10, 0);
        //ARVORE ANTES DO FIXUP
        //      Arvore      height     heightPreta
        //
        //       11           {0}         {1}
        //      /  \
        //     8    12        {1}         {1}
    	//      \          
    	//      (9)           {2}         {2}
    	//        \
    	//       (10)         {3}         {2}
        
        //APOS
        //      Arvore      height     heightPreta
        //
        //       11           {0}         {1}
        //      /  \
        //     9    12        {1}         {1}
    	//    / \          
    	//  (8) (10)          {2}         {2}
    
        Assert.assertEquals("[(11,B), (9,B), (8,R), (10,R), (12,B)]", arvorePV.preOrder2().toString());
        Assert.assertEquals(2, arvorePV.height());
        Assert.assertEquals(2, arvorePV.blackHeight());
    }

    @Test
    public void Caso3FixUpRemove() { 
    	arvorePV.insert(8, 0);
        arvorePV.insert(6, 0);
        arvorePV.insert(5, 0);
        arvorePV.insert(7, 0);
        arvorePV.insert(4, 0);

        //      Arvore      height     heightPreta
        //
        //       6           {0}         {1}
        //      /  \
        //     5    8        {1}         {2}
    	//    /    / 
        //   (4)  (7)        {2}         {2}
        Assert.assertEquals("[(6,B), (5,B), (4,R), (8,B), (7,R)]", arvorePV.preOrder2().toString());
        Assert.assertEquals(2, arvorePV.height());
        Assert.assertEquals(2, arvorePV.blackHeight());
        
        arvorePV.remove(5);
        //      Arvore      height     heightPreta
        //
        //       7           {0}         {1}
        //      /  \
        //     6    8        {1}         {2}
    	//    /       
        //   (4)             {2}         {2}
        Assert.assertEquals("[(7,B), (6,B), (4,R), (8,B)]", arvorePV.preOrder2().toString());
        Assert.assertEquals(2, arvorePV.height());
        Assert.assertEquals(2, arvorePV.blackHeight());
         
    }
    
    @Test
    public void Caso4FixUpRemove(){
    	arvorePV.insert(6, 0);
        arvorePV.insert(5, 0);
        arvorePV.insert(8, 0);
        arvorePV.insert(3, 0);
        arvorePV.insert(9, 0);

        //      Arvore      height     heightPreta
        //
        //       6           {0}         {1}
        //      /  \
        //     5    8        {1}         {2}
    	//    /      \
        //   (3)     (9)     {2}         {2}
        Assert.assertEquals("[(6,B), (5,B), (3,R), (8,B), (9,R)]", arvorePV.preOrder2().toString());
        Assert.assertEquals(2, arvorePV.height());
        Assert.assertEquals(2, arvorePV.blackHeight());
        
        arvorePV.remove(8);
        //      Arvore      height     heightPreta
        //
        //       5           {0}         {1}
        //      /  \
        //     3    6        {1}         {2}
    	//           \
        //           (9)     {2}         {2}
        Assert.assertEquals("[(5,B), (3,B), (6,B), (9,R)]", arvorePV.preOrder2().toString());
        Assert.assertEquals(2, arvorePV.height());
        Assert.assertEquals(2, arvorePV.blackHeight());

    }



    
    
    /**
     * Teste dos metodos sucessor e predecessor da classe RBTreeImpl, que retornam respectivamente, 
     * o valor da chave imediatamente superior ao valor da chave passada como parametro ao metodo, e
     * o valor da chave imediatamente inferior ao valor da chave passada como parametro ao metodo. 
     */
    @Test
    public void sucessorEPredecessorTest() {
    	arvorePV.insert(1, 0);
    	arvorePV.insert(15, 1);
    	arvorePV.insert(13, 2);
    	arvorePV.insert(27, 3);
    	arvorePV.insert(42, 4);
    	arvorePV.insert(3, 5);
    	arvorePV.insert(19, 6);
    	arvorePV.insert(8, 7);
    	arvorePV.insert(36, 8);
    	arvorePV.insert(75, 9);
    	arvorePV.insert(51, 10);
    	
		Assert.assertTrue(13 == arvorePV.predecessor(arvorePV.search(15)).getKey());
		Assert.assertTrue(2 == arvorePV.predecessor(arvorePV.search(15)).getValue());
		Assert.assertTrue(36 == arvorePV.predecessor(arvorePV.search(42)).getKey());
		Assert.assertTrue(8 == arvorePV.predecessor(arvorePV.search(42)).getValue());
		Assert.assertTrue(51 == arvorePV.predecessor(arvorePV.search(75)).getKey());
		Assert.assertTrue(10 == arvorePV.predecessor(arvorePV.search(75)).getValue());
		Assert.assertTrue(3 == arvorePV.predecessor(arvorePV.search(8)).getKey());
		Assert.assertTrue(5 == arvorePV.predecessor(arvorePV.search(8)).getValue());
		
		Assert.assertTrue(3 == arvorePV.sucessor(arvorePV.search(1)).getKey());
		Assert.assertTrue(5 == arvorePV.sucessor(arvorePV.search(1)).getValue());
		Assert.assertTrue(42 == arvorePV.sucessor(arvorePV.search(36)).getKey());
		Assert.assertTrue(4 == arvorePV.sucessor(arvorePV.search(36)).getValue());
		Assert.assertTrue(15 == arvorePV.sucessor(arvorePV.search(13)).getKey());
		Assert.assertTrue(1 == arvorePV.sucessor(arvorePV.search(13)).getValue());
		Assert.assertTrue(36 == arvorePV.sucessor(arvorePV.search(27)).getKey());
		Assert.assertTrue(8 == arvorePV.sucessor(arvorePV.search(27)).getValue());

	}
	
	
}
