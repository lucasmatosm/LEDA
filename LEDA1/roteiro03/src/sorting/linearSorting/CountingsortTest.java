package sorting.linearSorting;

import org.junit.*;

public class CountingsortTest {

	private Countingsort c;
	
	@Before
	public void setUp() throws Exception {
		c = new Countingsort();
	}

	@Test
	public void test() {
		Integer[] array1 = {5, 4, 3, 2, 1};
		Integer[] sortedArray1 = {1, 2, 3, 4, 5};
		c.sort(array1);
		Assert.assertArrayEquals(sortedArray1, array1);
		Integer[] array2 = {1, 1, 1};
		Integer[] sortedArray2 = {1, 1, 1};
		c.sort(array2);
		Assert.assertArrayEquals(sortedArray2, array2);
		Integer[] array3 = {};
		Integer[] sortedArray3 = {};
		c.sort(array3);
		Assert.assertArrayEquals(sortedArray3, array3);
		Integer[] array4 = {1};
		Integer[] sortedArray4 = {1};
		c.sort(array4);
		Assert.assertArrayEquals(sortedArray4, array4);
		Integer[] array5 = {9, 8, 5, 4, 3, 2, 1, 11, 10};
		Integer[] sortedArray5 = {9, 8, 1, 2, 3, 4, 5, 11, 10};
		c.sort(array5, 2, 6);
		Assert.assertArrayEquals(sortedArray5, array5);
		Integer[] array6 = {6, 5, 2, 2, 4, 4, 1, 3, 1, 0, 0};
		Integer[] sortedArray6 = {6, 5, 1, 1, 2, 2, 3, 4, 4, 0, 0};
		c.sort(array6, 2, 8);
		Assert.assertArrayEquals(sortedArray6, array6);
	}

}
