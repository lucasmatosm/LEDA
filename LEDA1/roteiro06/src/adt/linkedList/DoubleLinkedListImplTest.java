package adt.linkedList;

import static org.junit.Assert.*;

import org.junit.*;

public class DoubleLinkedListImplTest {

	private DoubleLinkedListImpl<Integer> list;
	
	@Before
	public void setUp() { 
		list = new DoubleLinkedListImpl<>();
	}
	
	@Test
	public void test2() {
		for (int i = 0; i < 5; i++) {
			list.insert(i);
		}
		assertEquals(5, list.size());
		assertEquals(new Integer(0), list.head.data);
		assertEquals(new Integer(4), list.last.data);
		list.removeLast();
		assertEquals(new Integer(3), list.last.data);
		list.removeFirst();
		assertEquals(new Integer(1), list.head.data);
		list.insertFirst(10);
		assertEquals(new Integer(10), list.head.data);
		assertEquals(new Integer(1), list.head.next.data);
		list.remove(10);
		assertEquals(new Integer(1), list.head.data);
		assertEquals(3, list.size());
		assertArrayEquals(new Integer[]{1, 2, 3}, list.toArray());
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
