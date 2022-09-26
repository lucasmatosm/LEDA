package adt.stack;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestStackLinkedList {

	private Stack<String> stack1;
	private Stack<String> stack2;
	private Stack<String> stack3;
	
	@Before
	public void setUp() throws Exception {
		stack1 = new StackLinkedListFullImpl<String>();
		stack1.push("a");
		stack1.push("b");
		stack1.push("c");
		stack1.push("d");
		
		stack2 = new StackLinkedListFullImpl<String>();
		stack2.push("a");
		stack2.push("b");
		stack2.push("c");
		
		stack3 = new StackLinkedListFullImpl<String>();
	}

	@Test
	public void testTop() {
		assertEquals("d", stack1.top());
		assertEquals("c", stack2.top());
		assertEquals(null, stack3.top());
	}

	@Test
	public void testIsEmpty() {
		assertFalse(stack1.isEmpty());
		assertFalse(stack2.isEmpty());
		assertTrue(stack3.isEmpty());
	}

	@Test
	public void testPushTC01() {
		assertEquals("d", stack1.top());
		try {
			stack1.push("e");
		} catch (StackOverflowException e) {
			fail("No exception expected!");
		}
		assertEquals("e", stack1.top());
		

		assertEquals(null, stack3.top());
		try {
			stack3.push("a");
		} catch (StackOverflowException e) {
			fail("No exception expected!");
		}
		assertEquals("a", stack3.top());
	}
	
	@Test
	public void testPopTC01() {
		assertEquals("d", stack1.top());
		try {
			assertEquals("d", stack1.pop());
		} catch (StackUnderflowException e) {
			fail("No exception expected!");
		}
		assertEquals("c", stack1.top());
		
		assertEquals("c", stack2.top());
		try {
			assertEquals("c", stack2.pop());
		} catch (StackUnderflowException e) {
			fail("No exception expected!");
		}
		assertEquals("b", stack2.top());
		
		try {
			assertEquals("b", stack2.pop());
		} catch (StackUnderflowException e) {
			fail("No exception expected!");
		}
		assertEquals("a", stack2.top());
		
		try {
			assertEquals("a", stack2.pop());
		} catch (StackUnderflowException e) {
			fail("No exception expected!");
		}
		assertEquals(null, stack2.top());
	}
	
	@Test(expected=StackUnderflowException.class)
	public void testPopTC02() throws StackUnderflowException {
		stack3.pop();
	}

}
