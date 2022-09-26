package sorting.divideAndConquer.hybridMergesort;

import org.junit.*;

public class HybridMergesortTest {

	@Test
	public void setUp() throws Exception {
		test(new HybridMergesort<Integer>());
	}

	public void test(HybridMergesort<Integer> sorting) {
		Integer[] array1       = {3, 5, 1, 4, 2, 8, 10};
		Integer[] sortedArray1 = {3, 1, 2, 4, 5, 8, 10};
		sorting.sort(array1, 1, 5);
		Assert.assertArrayEquals(sortedArray1, array1);
		Assert.assertEquals(1, HybridMergesort.MERGESORT_APPLICATIONS);
		Assert.assertEquals(0, HybridMergesort.INSERTIONSORT_APPLICATIONS);
		Integer[] array2       = {1};
		Integer[] sortedArray2 = {1};
		sorting.sort(array2, 0, 1);
		Assert.assertArrayEquals(sortedArray2, array2);
		Assert.assertEquals(1, HybridMergesort.MERGESORT_APPLICATIONS);
		Assert.assertEquals(1, HybridMergesort.INSERTIONSORT_APPLICATIONS);
		Integer[] array3       = {2, 2, 2, 1, 1};
		Integer[] sortedArray3 = {1, 1, 2, 2, 2};
		sorting.sort(array3);
		Assert.assertArrayEquals(sortedArray3, array3);
		Assert.assertEquals(0, HybridMergesort.MERGESORT_APPLICATIONS);
		Assert.assertEquals(0, HybridMergesort.INSERTIONSORT_APPLICATIONS);
		Integer[] array4       = {-3, -5, -1, -4};
		Integer[] sortedArray4 = {-5, -4, -3, -1};
		sorting.sort(array4, 0, 3);
		Assert.assertArrayEquals(sortedArray4, array4);
		Assert.assertEquals(0, HybridMergesort.MERGESORT_APPLICATIONS);
		Assert.assertEquals(1, HybridMergesort.INSERTIONSORT_APPLICATIONS);
		Integer[] array5       = {1, 2, -3, 4, -5};
		Integer[] sortedArray5 = {-5, -3, 1, 2, 4};
		sorting.sort(array5, 0, 4);
		Assert.assertArrayEquals(sortedArray5, array5);
		Assert.assertEquals(1, HybridMergesort.MERGESORT_APPLICATIONS);
		Assert.assertEquals(1, HybridMergesort.INSERTIONSORT_APPLICATIONS);
	}
	
}
