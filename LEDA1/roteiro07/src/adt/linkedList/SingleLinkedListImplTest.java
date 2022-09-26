package adt.linkedList;

import static org.junit.Assert.*;

import org.junit.*;

public class SingleLinkedListImplTest {

	private RecursiveSingleLinkedListImpl<Integer> list;
	
	@Before
	public void setUp() { 
		list = new RecursiveSingleLinkedListImpl<>();
	}
	
	@Test
	public void test() {
		assertTrue(list.isEmpty());
		assertEquals(0, list.size());
		assertArrayEquals(new Integer[]{}, list.toArray());
		list.insert(1);
		assertFalse(list.isEmpty());
		assertEquals(1, list.size());
		assertArrayEquals(new Integer[]{1}, list.toArray());
		list.remove(1);
		assertTrue(list.isEmpty());
		assertEquals(0, list.size());
		assertArrayEquals(new Integer[]{}, list.toArray());
		for (int i = 1; i <= 5; i++) {
			list.insert(i);
		}
		assertEquals(5, list.size());
		assertArrayEquals(new Integer[]{1, 2, 3, 4, 5}, list.toArray());
		list.remove(5);
		assertArrayEquals(new Integer[]{1, 2, 3, 4}, list.toArray());
		list.remove(1);
		assertArrayEquals(new Integer[]{2, 3, 4}, list.toArray());
		list.remove(3);
		assertArrayEquals(new Integer[]{2, 4}, list.toArray());
		assertEquals(new Integer(2), list.search(2));
		assertEquals(new Integer(4), list.search(4));
		assertEquals(null, list.search(3));
	}

}
