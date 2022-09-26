package adt.splaytree;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

public class SplayTreeImplTest {
	
	private SplayTree<Integer> splay;

	@Before
	public void setUp() throws Exception {
		splay = new SplayTreeImpl<Integer>();
	}

	@Test
	public void testInsertT() {
		assertTrue(splay.isEmpty());
		splay.insert(10);
		assertTrue(10 == splay.getRoot().getData());
		splay.insert(100);
		assertTrue(100 == splay.getRoot().getData());
	}

	@Test
	public void testRemoveT() {
		fail("Not yet implemented");
	}

	@Test
	public void testSearchT() {
		fail("Not yet implemented");
	}

}
