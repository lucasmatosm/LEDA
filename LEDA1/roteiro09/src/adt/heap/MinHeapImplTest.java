package adt.heap;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class MinHeapImplTest {

	private MinHeapImpl<Integer> minHeap;

	@Before
	public void createMinHeap() {
		minHeap = new MinHeapImpl<>();
	}

	@Test
	public void isEmptyTest() {
		assertTrue(minHeap.isEmpty());
		minHeap.insert(4);
		assertFalse(minHeap.isEmpty());
		minHeap.extractRootElement();
		assertTrue(minHeap.isEmpty());
	}

	@Test
	public void rootElementTest() {
		assertEquals(null, minHeap.rootElement());
		for (int i = 8; i < 15; i++) {
			minHeap.insert(i);
		}
		assertEquals(new Integer(8), minHeap.rootElement());
		minHeap.insert(2);
		assertEquals(new Integer(2), minHeap.rootElement());
		minHeap.extractRootElement();
		assertEquals(new Integer(8), minHeap.rootElement());
	}

	@Test
	public void insertTest() {
		for (int i = 8; i < 15; i++) {
			minHeap.insert(i);
		}
		assertArrayEquals(new Integer[] { 8, 9, 10, 11, 12, 13, 14 },
				minHeap.toArray());
	}
	
	@Test
	public void insertMaxTest(){
		for (int i = 8; i < 30; i++) {
			minHeap.insert(i);
		}
	}

	@Test
	public void builHeapTest() {
		minHeap.buildHeap(new Integer[] { 8, 9, 10, 11, 12, 13, 14 });
		assertArrayEquals(new Integer[] { 8, 9, 10, 11, 12, 13, 14 },
				minHeap.toArray());
		minHeap.buildHeap(new Integer[] {});
		assertArrayEquals(new Integer[] {}, minHeap.toArray());
	}

	@Test
	public void heapSortTest() {
		assertArrayEquals(new Integer[] { 0, 1, 2, 3, 5, 8 },
				minHeap.heapsort(new Integer[] { 1, 3, 2, 8, 0, 5 }));
	}

	@Test
	public void toArrayTest() {
		System.out.println(Arrays.toString(minHeap.toArray()));
		assertArrayEquals(new Integer[] {}, minHeap.toArray());
		insertTest();
	}

	@Test
	public void extractRootElementTest() {
		assertEquals(null, minHeap.extractRootElement());
		insertTest();
		assertEquals(new Integer(8), minHeap.extractRootElement());
		assertEquals(new Integer(9), minHeap.rootElement());
		assertEquals(new Integer(9), minHeap.extractRootElement());
		assertEquals(new Integer(10), minHeap.rootElement());
		assertEquals(new Integer(10), minHeap.extractRootElement());
		assertEquals(new Integer(11), minHeap.rootElement());
		assertEquals(new Integer(11), minHeap.extractRootElement());
		assertEquals(new Integer(12), minHeap.rootElement());
		assertEquals(new Integer(12), minHeap.extractRootElement());
		assertEquals(new Integer(13), minHeap.rootElement());
		assertEquals(new Integer(13), minHeap.extractRootElement());
		assertEquals(new Integer(14), minHeap.rootElement());
		assertEquals(new Integer(14), minHeap.extractRootElement());
		assertEquals(null, minHeap.extractRootElement());
	}
}
