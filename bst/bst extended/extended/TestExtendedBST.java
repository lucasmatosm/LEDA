package adt.bst.extended;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class TestExtendedBST {

	protected ExtendedBST<Integer> tree1;
	protected ExtendedBST<Integer> tree2;
	protected ExtendedBST<Integer> tree3;
	protected ExtendedBST<Integer> tree4;
	protected static final int SIZE = 5;

	@Before
	public void setUp() throws Exception {
		// Somente filhos a direita
		tree1 = new ExtendedBSTImpl<Integer>();
		for (int i = 0; i < SIZE; i++) {
			tree1.insert(i);
		}

		// Somente filhos a esquerda
		tree2 = new ExtendedBSTImpl<Integer>();
		for (int i = SIZE - 1; i >= 0; i--) {
			tree2.insert(i);
		}

		// ?rvore completa
		tree3 = new ExtendedBSTImpl<Integer>();
		tree3.insert(5);
		tree3.insert(3);
		tree3.insert(1);
		tree3.insert(4);
		tree3.insert(8);
		tree3.insert(6);
		tree3.insert(9);

		// ?rvore vazia
		tree4 = new ExtendedBSTImpl<Integer>();
	}

	@Test
	public void testEquals01() {
		tree4.insert(5);
		tree4.insert(3);
		tree4.insert(1);
		tree4.insert(4);
		tree4.insert(8);
		tree4.insert(6);
		tree4.insert(9);

		assertTrue(tree3.equals(tree4));

		tree4.insert(10);
		assertFalse(tree3.equals(tree4));
	}

	@Test
	public void testEquals02() {
		// Somente filhos a direita
		for (int i = 0; i < SIZE; i++) {
			tree4.insert(i);
		}

		assertTrue(tree1.equals(tree4));
	}

	@Test
	public void testEquals03() {
		// Somente filhos a esquerda
		for (int i = SIZE - 1; i >= 0; i--) {
			tree4.insert(i);
		}

		assertTrue(tree2.equals(tree4));
	}

	@Test
	public void testIsSimilar01() {
		tree4.insert(7);
		tree4.insert(5);
		tree4.insert(3);
		tree4.insert(6);
		tree4.insert(10);
		tree4.insert(8);
		tree4.insert(11);

		assertTrue(tree3.isSimilar(tree4));

		tree4.insert(12);
		assertFalse(tree3.isSimilar(tree4));
	}

	@Test
	public void testIsSimilar02() {
		// Somente filhos a esquerda
		for (int i = SIZE - 1; i >= 0; i--) {
			tree4.insert(i + i);
		}

		assertTrue(tree2.isSimilar(tree4));
	}

	@Test
	public void testIsSimilar03() {
		// Somente filhos a esquerda
		for (int i = SIZE - 1; i >= 0; i--) {
			tree4.insert(i + i);
		}

		assertTrue(tree2.isSimilar(tree4));
	}

	@Test
	public void testIsSimilar04() {
		ExtendedBST<Integer> tree5 = new ExtendedBSTImpl<Integer>();
		assertTrue(tree5.isSimilar(tree4));
	}

	@Test
	public void testContains01() {
		tree4.insert(5);
		tree4.insert(3);
		tree4.insert(8);
		tree4.insert(6);

		assertTrue(tree3.contains(tree4));

		tree4.insert(10);
		assertFalse(tree3.contains(tree4));
	}

	@Test
	public void testContains02() {
		// Somente filhos a esquerda
		for (int i = SIZE - 3; i >= 0; i--) {
			tree4.insert(i);
		}

		assertTrue(tree2.contains(tree4));
	}

	@Test
	public void testContains03() {
		// Somente filhos a esquerda
		for (int i = SIZE - 3; i >= 0; i--) {
			tree4.insert(i);
		}

		assertTrue(tree2.contains(tree4));
	}

	@Test
	public void testContains04() {
		// ?rvore vazia
		assertTrue(tree2.contains(tree4));
	}

	@Test
	public void testContains05() {
		tree4.insert(5);
		assertTrue(tree3.contains(tree4));
	}

}