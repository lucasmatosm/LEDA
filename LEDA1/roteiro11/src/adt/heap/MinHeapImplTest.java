package adt.heap;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class MinHeapImplTest {
	private MinHeapImpl<Integer> heap1;
	private MinHeapImpl<Integer> heap2;
	private MinHeapImpl<Integer> heap3;

	@Before
	public void setUp() throws Exception {
		heap1 = new MinHeapImpl<Integer>();
		heap2 = new MinHeapImpl<Integer>();
		heap3 = new MinHeapImpl<Integer>();
	}

	@Test
	public void testIsEmpty() {
		assertTrue(heap1.isEmpty());
		assertTrue(heap2.isEmpty());
		assertTrue(heap3.isEmpty());	
		
		heap1.insert(new Integer(3));
		assertFalse(heap1.isEmpty());
		
		heap2.insert(new Integer(3));
		assertFalse(heap2.isEmpty());
		
		heap3.insert(new Integer(3));
		assertFalse(heap3.isEmpty());
	}
	
	@Test
	public void testExtractRooTElement() {
		int[] elements = {40,19,36,17,3,25,1,2,7};
		for(int element: elements) {
			heap1.insert(element);
		}
		
		assertEquals(new Integer(1), heap1.extractRootElement());
		assertEquals(new Integer(2), heap1.rootElement());
		
		int[] elements2 = {16,14,10,8,7,9,3,2,4, 1};
		for(int element: elements2) {
			heap2.insert(element);
		}
		
		assertEquals(new Integer(1), heap2.extractRootElement());
		assertEquals(new Integer(2), heap2.rootElement());
	}
	
	@Test
	public void testHeapSort() {
		Integer[] elements = {40,19,36,17,3,25,1,2,7};
		for(int element: elements) {
			heap1.insert(element);
		}
		
		assertArrayEquals(new Integer[]{1,2,3,7,17,19,25,36,40}, heap1.heapsort(elements));
		System.out.println(Arrays.toString((heap1.toArray())));
		
		Integer[] elements2 = {16,14,10,8,7,9,3,2,4, 1};
		for(int element: elements2) {
			heap2.insert(element);
		}
		
		assertArrayEquals(new Integer[]{1,2,3,4,7,8,9,10,14,16}, heap2.heapsort(elements2));
		System.out.println(Arrays.toString((heap2.toArray())));
	}
}
