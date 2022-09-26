package sorting.combinationsOfSortingAlgorithms;

import org.junit.*;

public class CombinationBubbleSelectionSortTest {

	private CombinationBubbleSelectionSort<Integer> sorting;

	@Before
	public void setUp() throws Exception {
		sorting = new CombinationBubbleSelectionSort<>();
	}

	@Test
	public void test() {
		Integer[] array1 = { 3, 5, 1, 4, 2 };
		Integer[] sortedArray1 = { 1, 2, 3, 4, 5 };
		sorting.sort(array1);
		Assert.assertArrayEquals(sortedArray1, array1);
		Integer[] array2 = {};
		Integer[] sortedArray2 = {};
		sorting.sort(array2);
		Assert.assertArrayEquals(sortedArray2, array2);
		Integer[] array3 = { 2, 2, 2, 1, 1 };
		Integer[] sortedArray3 = { 1, 1, 2, 2, 2 };
		sorting.sort(array3);
		Assert.assertArrayEquals(sortedArray3, array3);
		Integer[] array4 = { -3, -5, -1, -4, -2 };
		Integer[] sortedArray4 = { -5, -4, -3, -2, -1 };
		sorting.sort(array4);
		Assert.assertArrayEquals(sortedArray4, array4);
		Integer[] array5 = { 1, 2, -3, 4, -5 };
		Integer[] sortedArray5 = { -5, -3, 1, 2, 4 };
		sorting.sort(array5);
		Assert.assertArrayEquals(sortedArray5, array5);
	}

}
