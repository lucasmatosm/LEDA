package adt.stack;

import org.junit.*;

public class StackDoubleLinkedListImplTest {

	private Stack<Integer> stack;
	
	@Before
	public void setUp() throws Exception {
		stack = new StackRecursiveDoubleLinkedListImpl<>(10);
	}

	@Test
	public void test() throws Exception {
		Assert.assertFalse(stack.isFull());
		Assert.assertTrue(stack.isEmpty());
		stack.push(1);
		Assert.assertFalse(stack.isEmpty());
		stack.pop();
		Assert.assertTrue(stack.isEmpty());
		Assert.assertEquals(null, stack.top());
		try {
			stack.pop();
			Assert.fail();
		} catch(StackUnderflowException e) { }
		stack.push(null);
		Assert.assertTrue(stack.isEmpty());
		for (int i = 0; i < 10; i++) {
			stack.push(i);
		}
		Assert.assertTrue(stack.isFull());
		Assert.assertTrue(stack.top() == 9);
		try {
			stack.push(10);
			Assert.fail();
		} catch(StackOverflowException e) { }
		Assert.assertTrue(stack.pop() == 9);
		Assert.assertTrue(stack.top() == 8);
	}

}
