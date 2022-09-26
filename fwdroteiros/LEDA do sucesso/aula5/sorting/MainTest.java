package sorting;



import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MainTest {

	private int[] vetor1;  
	private int[] vetor2; 
	private int[] vetor3; 
	private int[] vetor4; 
	private int[] vetor5; 
	private int[] vetor6; 
	private int[] vetor7; 
	private SimpleSorting implementation;
	
	@Before
	public void setUp() throws Exception {
		implementation = new SimpleSortingImpl();
		this.vetor1 = criaVetor1();
		this.vetor2 = criaVetor2();
		this.vetor3 = criaVetor3();
		this.vetor4 = criaVetor4();
		this.vetor5 = criaVetor5();
		this.vetor6 = criaVetor6();
		this.vetor7 = criaVetor7();
	}
	
	// array ordenado de forma decrescente
	private int[] criaVetor1(){
		int[] vetor = new int[20];
		for (int i = vetor.length; i > 0; i--) {
			vetor[vetor.length - i] = i;
		}
		return vetor;
	}
	
	// array com n�meros em posi��es variadas
	private int[] criaVetor2(){
		int[] vetor = {20, 1, 30, 5, 2, 4, 10, 9, 12, 19, 13, 0};
		return vetor;
	}
	
	// array com valores positivos e negativos
	private int[] criaVetor3(){
		int[] vetor = {5, -2, 7, -4, 8, -1, 0, 6, 9, -5, -10, 1};
		return vetor;
	}
	
	// array sem elementos
	private int[] criaVetor4(){
		int[] vetor = {};
		return vetor;
	}
	
	// array com um elemento
	private int[] criaVetor5(){
		int[] vetor = {10};
		return vetor;
	}
	
	// array com n�meros repetidos
	private int[] criaVetor6(){
		int[] vetor = {2, 3, 1, 3, 5, 0, 3, 2, 8, 7};
		return vetor;
	}
	
	// array com todos os valores iguais
	private int[] criaVetor7(){
		int[] vetor = {4, 4, 4, 4, 4, 4, 4, 4};
		return vetor;
	}

	@Test
	public void testBubbleSortTC01() {
		int[] copy1 = Arrays.copyOf(vetor1,vetor1.length);
		implementation.bubbleSort(vetor1);
		Arrays.sort(copy1);
		Assert.assertArrayEquals(copy1,vetor1);
	}
	
	@Test
	public void testBubbleSortTC02() {
		int[] copy1 = Arrays.copyOf(vetor2,vetor2.length);
		implementation.bubbleSort(vetor2);
		Arrays.sort(copy1);
		Assert.assertArrayEquals(copy1,vetor2);
	}
	
	@Test
	public void testBubbleSortTC03() {
		int[] copy1 = Arrays.copyOf(vetor3,vetor3.length);
		implementation.bubbleSort(vetor3);
		Arrays.sort(copy1);
		Assert.assertArrayEquals(copy1,vetor3);
	}
	
	@Test
	public void testBubbleSortTC04() {
		int[] copy1 = Arrays.copyOf(vetor4,vetor4.length);
		implementation.bubbleSort(vetor4);
		Arrays.sort(copy1);
		Assert.assertArrayEquals(copy1,vetor4);
	}
	
	@Test
	public void testBubbleSortTC05() {
		int[] copy1 = Arrays.copyOf(vetor5,vetor5.length);
		implementation.bubbleSort(vetor5);
		Arrays.sort(copy1);
		Assert.assertArrayEquals(copy1,vetor5);
	}
	
	@Test
	public void testBubbleSortTC06() {
		int[] copy1 = Arrays.copyOf(vetor6,vetor6.length);
		implementation.bubbleSort(vetor6);
		Arrays.sort(copy1);
		Assert.assertArrayEquals(copy1,vetor6);
	}
	
	@Test
	public void testBubbleSortTC07() {
		int[] copy1 = Arrays.copyOf(vetor7,vetor7.length);
		implementation.bubbleSort(vetor7);
		Arrays.sort(copy1);
		Assert.assertArrayEquals(copy1,vetor7);
	}

	@Test
	public void testSelectionSortTC01() {
		int[] copy1 = Arrays.copyOf(vetor1,vetor1.length);
		implementation.selectionSort(vetor1);
		Arrays.sort(copy1);
		Assert.assertArrayEquals(copy1,vetor1);
	}
	
	@Test
	public void testSelectionSortTC02() {
		int[] copy1 = Arrays.copyOf(vetor2,vetor2.length);
		implementation.selectionSort(vetor2);
		Arrays.sort(copy1);
		Assert.assertArrayEquals(copy1,vetor2);
	}
	
	@Test
	public void testSelectionSortTC03() {
		int[] copy1 = Arrays.copyOf(vetor3,vetor3.length);
		implementation.selectionSort(vetor3);
		Arrays.sort(copy1);
		Assert.assertArrayEquals(copy1,vetor3);
	}
	
	@Test
	public void testSelectionSortTC04() {
		int[] copy1 = Arrays.copyOf(vetor4,vetor4.length);
		implementation.selectionSort(vetor4);
		Arrays.sort(copy1);
		Assert.assertArrayEquals(copy1,vetor4);
	}
	
	@Test
	public void testSelectionSortTC05() {
		int[] copy1 = Arrays.copyOf(vetor5,vetor5.length);
		implementation.selectionSort(vetor5);
		Arrays.sort(copy1);
		Assert.assertArrayEquals(copy1,vetor5);
	}
	
	@Test
	public void testSelectionSortTC06() {
		int[] copy1 = Arrays.copyOf(vetor6,vetor6.length);
		implementation.selectionSort(vetor6);
		Arrays.sort(copy1);
		Assert.assertArrayEquals(copy1,vetor6);
	}
	
	@Test
	public void testSelectionSortTC07() {
		int[] copy1 = Arrays.copyOf(vetor7,vetor7.length);
		implementation.selectionSort(vetor7);
		Arrays.sort(copy1);
		Assert.assertArrayEquals(copy1,vetor7);
	}

	@Test
	public void testInsertionSortTC01() {
		int[] copy1 = Arrays.copyOf(vetor1,vetor1.length);
		implementation.insertionSort(vetor1);
		Arrays.sort(copy1);
		Assert.assertArrayEquals(copy1,vetor1);
	}

	@Test
	public void testInsertionSortTC02() {
		int[] copy1 = Arrays.copyOf(vetor2,vetor2.length);
		implementation.insertionSort(vetor2);
		Arrays.sort(copy1);
		Assert.assertArrayEquals(copy1,vetor2);
	}
	
	@Test
	public void testInsertionSortTC03() {
		int[] copy1 = Arrays.copyOf(vetor3,vetor3.length);
		implementation.insertionSort(vetor3);
		Arrays.sort(copy1);
		Assert.assertArrayEquals(copy1,vetor3);
	}
	
	@Test
	public void testInsertionSortTC04() {
		int[] copy1 = Arrays.copyOf(vetor4,vetor4.length);
		implementation.insertionSort(vetor4);
		Arrays.sort(copy1);
		Assert.assertArrayEquals(copy1,vetor4);
	}
	
	@Test
	public void testInsertionSortTC05() {
		int[] copy1 = Arrays.copyOf(vetor5,vetor5.length);
		implementation.insertionSort(vetor5);
		Arrays.sort(copy1);
		Assert.assertArrayEquals(copy1,vetor5);
	}
	
	@Test
	public void testInsertionSortTC06() {
		int[] copy1 = Arrays.copyOf(vetor6,vetor6.length);
		implementation.insertionSort(vetor6);
		Arrays.sort(copy1);
		Assert.assertArrayEquals(copy1,vetor6);
	}
	
	@Test
	public void testInsertionSortTC07() {
		int[] copy1 = Arrays.copyOf(vetor7,vetor7.length);
		implementation.insertionSort(vetor7);
		Arrays.sort(copy1);
		Assert.assertArrayEquals(copy1,vetor7);
	}
	
}
