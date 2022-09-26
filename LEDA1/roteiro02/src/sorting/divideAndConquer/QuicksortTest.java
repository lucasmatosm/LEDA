package sorting.divideAndConquer;

import org.junit.*;

public class QuicksortTest {
	
	@Test
	public void setUp() throws Exception {
		Quicksort<Integer> q = new Quicksort<>();
		test(q);
		partialTest(q);
	}

	public void test(Quicksort<Integer> sorting) {
		Integer[] array1       = {3, 5, 1, 4, 2};
		Integer[] sortedArray1 = {1, 2, 3, 4, 5};
		sorting.sort(array1);
		Assert.assertArrayEquals(sortedArray1, array1);
		Integer[] array2       = {};
		Integer[] sortedArray2 = {};
		sorting.sort(array2);
		Assert.assertArrayEquals(sortedArray2, array2);
		Integer[] array3       = {2, 2, 2, 1, 1};
		Integer[] sortedArray3 = {1, 1, 2, 2, 2};
		sorting.sort(array3);
		Assert.assertArrayEquals(sortedArray3, array3);
		Integer[] array4       = {-3, -5, -1, -4, -2};
		Integer[] sortedArray4 = {-5, -4, -3, -2, -1};
		sorting.sort(array4);
		Assert.assertArrayEquals(sortedArray4, array4);
		Integer[] array5       = {1, 2, -3, 4, -5};
		Integer[] sortedArray5 = {-5, -3, 1, 2, 4};
		sorting.sort(array5);
		Assert.assertArrayEquals(sortedArray5, array5);
	}
	
	public void partialTest(Quicksort<Integer> sorting) {
		Integer[] array1       = {3, 5, 1, 4, 2};
		Integer[] sortedArray1 = {3, 1, 4, 5, 2};
		sorting.sort(array1, 1, 3);
		Assert.assertArrayEquals(sortedArray1, array1);
		Integer[] array2       = {};
		Integer[] sortedArray2 = {};
		sorting.sort(array2);
		Assert.assertArrayEquals(sortedArray2, array2);
		Integer[] array3       = {2, 2, 2, 1, 1};
		Integer[] sortedArray3 = {2, 2, 1, 2, 1};
		sorting.sort(array3, 2, 3);
		Assert.assertArrayEquals(sortedArray3, array3);
		Integer[] array4       = {-3, -5, -1, -4, -2};
		Integer[] sortedArray4 = {-5, -4, -3, -1, -2};
		sorting.sort(array4, 0, 3);
		Assert.assertArrayEquals(sortedArray4, array4);
		Integer[] array5       = {1, 2, -3, 4, -5};
		Integer[] sortedArray5 = {1, 2, -5, -3, 4};
		sorting.sort(array5, 2, 4);
		Assert.assertArrayEquals(sortedArray5, array5);
	}
	
}
