package adt.avltree;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestAVL {

	private AVLTree<Integer> tree1, tree2;
	@Before
	public void setUp() { 
		tree1 = new AVLTreeImpl<>();
		tree2 = new AVLTreeImpl<>();
	}
	
	@Test
	public void testSimpleRotation() { 
		//ROTATION TO RIGHT
		tree1.insert(1);
		tree1.insert(2);
		tree1.insert(3);
		Assert.assertArrayEquals(new Integer[]{2, 1, 3}, tree1.preOrder());
		Assert.assertArrayEquals(new Integer[]{1, 2, 3}, tree1.order());
		Assert.assertArrayEquals(new Integer[]{1, 3, 2}, tree1.postOrder());
		//ROTATION TO LEFT
		tree2.insert(3);
		tree2.insert(2);
		tree2.insert(1);
		Assert.assertArrayEquals(new Integer[]{2, 1, 3}, tree2.preOrder());
		Assert.assertArrayEquals(new Integer[]{1, 2, 3}, tree2.order());
		Assert.assertArrayEquals(new Integer[]{1, 3, 2}, tree2.postOrder());
	}
	
	@Test
	public void testDoubleRotation() { 
		//ROTATION RIGHT - LEFT
		tree1.insert(10);
		tree1.insert(20);
		tree1.insert(15);
		Assert.assertArrayEquals(new Integer[]{15, 10, 20}, tree1.preOrder());
		Assert.assertArrayEquals(new Integer[]{10, 15, 20}, tree1.order());
		Assert.assertArrayEquals(new Integer[]{10, 20, 15}, tree1.postOrder());
		//ROTATION LEFT - RIGHT
		tree2.insert(20);
		tree2.insert(10);
		tree2.insert(15);
		Assert.assertArrayEquals(new Integer[]{15, 10, 20}, tree2.preOrder());
		Assert.assertArrayEquals(new Integer[]{10, 15, 20}, tree2.order());
		Assert.assertArrayEquals(new Integer[]{10, 20, 15}, tree2.postOrder());
	}

	@Test
	public void testRemove() { 
		tree1.insert(15);
		tree1.insert(20);
		tree1.insert(10);
		tree1.insert(13);
		tree1.insert(5);
		Assert.assertArrayEquals(new Integer[]{5, 10, 13, 15, 20}, tree1.order());
		tree1.remove(20);
		Assert.assertArrayEquals(new Integer[]{5, 10, 13, 15}, tree1.order());
	}
	
}
