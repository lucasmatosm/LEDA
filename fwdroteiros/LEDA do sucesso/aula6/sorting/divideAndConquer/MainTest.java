package sorting.divideAndConquer;

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
	private DivideAndConquerSorting implementation;
	
	@Before
	public void setUp() throws Exception {
		implementation = new DivideAndConquerSortingImpl();
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
	
	// array com números em posições variadas
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
	
	// array com números repetidos
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
	public void testMergeSortTC01() {
		int[] copy1 = Arrays.copyOf(vetor1,vetor1.length);
		implementation.mergeSort(vetor1);
		Arrays.sort(copy1);
		Assert.assertArrayEquals(copy1,vetor1);
	}
	
	@Test
	public void testMergeSortTC02() {
		int[] copy1 = Arrays.copyOf(vetor2,vetor2.length);
		implementation.mergeSort(vetor2);
		Arrays.sort(copy1);
		Assert.assertArrayEquals(copy1,vetor2);
	}
	
	@Test
	public void testMergeSortTC03() {
		int[] copy1 = Arrays.copyOf(vetor3,vetor3.length);
		implementation.mergeSort(vetor3);
		Arrays.sort(copy1);
		Assert.assertArrayEquals(copy1,vetor3);
	}
	
	@Test
	public void testMergeSortTC04() {
		int[] copy1 = Arrays.copyOf(vetor4,vetor4.length);
		implementation.mergeSort(vetor4);
		Arrays.sort(copy1);
		Assert.assertArrayEquals(copy1,vetor4);
	}
	
	@Test
	public void testMergeSortTC05() {
		int[] copy1 = Arrays.copyOf(vetor5,vetor5.length);
		implementation.mergeSort(vetor5);
		Arrays.sort(copy1);
		Assert.assertArrayEquals(copy1,vetor5);
	}
	
	@Test
	public void testMergeSortTC06() {
		int[] copy1 = Arrays.copyOf(vetor6,vetor6.length);
		implementation.mergeSort(vetor6);
		Arrays.sort(copy1);
		Assert.assertArrayEquals(copy1,vetor6);
	}
	
	@Test
	public void testMergeSortTC07() {
		int[] copy1 = Arrays.copyOf(vetor7,vetor7.length);
		implementation.mergeSort(vetor7);
		Arrays.sort(copy1);
		Assert.assertArrayEquals(copy1,vetor7);
	}

	@Test
	public void testQuickSortTC01() {
		int[] copy1 = Arrays.copyOf(vetor1,vetor1.length);
		implementation.quicksort(vetor1,0,vetor1.length-1);
		Arrays.sort(copy1);
		Assert.assertArrayEquals(copy1,vetor1);
	}
	
	@Test
	public void testQuickSortTC02() {
		int[] copy1 = Arrays.copyOf(vetor2,vetor2.length);
		implementation.quicksort(vetor2,0,vetor2.length-1);
		Arrays.sort(copy1);
		Assert.assertArrayEquals(copy1,vetor2);
	}
	
	@Test
	public void testQuickSortTC03() {
		int[] copy1 = Arrays.copyOf(vetor3,vetor3.length);
		implementation.quicksort(vetor3,0,vetor3.length-1);
		Arrays.sort(copy1);
		Assert.assertArrayEquals(copy1,vetor3);
	}
	
	@Test
	public void testQuickSortTC04() {
		int[] copy1 = Arrays.copyOf(vetor4,vetor4.length);
		implementation.quicksort(vetor4,0,vetor4.length-1);
		Arrays.sort(copy1);
		Assert.assertArrayEquals(copy1,vetor4);
	}
	
	@Test
	public void testQuickSortTC05() {
		int[] copy1 = Arrays.copyOf(vetor5,vetor5.length);
		implementation.quicksort(vetor5,0,vetor5.length-1);
		Arrays.sort(copy1);
		Assert.assertArrayEquals(copy1,vetor5);
	}
	
	@Test
	public void testQuickSortTC06() {
		int[] copy1 = Arrays.copyOf(vetor6,vetor6.length);
		implementation.quicksort(vetor6,0,vetor6.length-1);
		Arrays.sort(copy1);
		Assert.assertArrayEquals(copy1,vetor6);
	}
	
	@Test
	public void testQuickSortTC07() {
		int[] copy1 = Arrays.copyOf(vetor7,vetor7.length);
		implementation.quicksort(vetor7,0,vetor7.length-1);
		Arrays.sort(copy1);
		Assert.assertArrayEquals(copy1,vetor7);
	}

}
