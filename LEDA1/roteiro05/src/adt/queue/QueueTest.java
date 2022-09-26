package adt.queue;

import org.junit.*;

public class QueueTest {

	private Queue<Integer> queue; 
	
	@Before
	public void setUp() throws Exception {
		queue = new QueueImpl<>(10);
	}

	@Test
	public void test() throws Exception {
		Assert.assertTrue(queue.isEmpty());
		Assert.assertFalse(queue.isFull());
		queue.enqueue(null);
		Assert.assertTrue(queue.isEmpty());
		queue.enqueue(1);
		Assert.assertFalse(queue.isEmpty());
		Assert.assertTrue(queue.dequeue() == 1);
		Assert.assertTrue(queue.isEmpty());
		try {
			queue.dequeue();
			Assert.fail();
		} catch(QueueUnderflowException e) { }
		for (int i = 0; i < 10; i++) {
			queue.enqueue(i);
		}
		try {
			queue.enqueue(10);
			Assert.fail();
		} catch(QueueOverflowException e) { }
		Assert.assertTrue(queue.isFull());
		Assert.assertTrue(queue.head() == 0);
		Assert.assertTrue(queue.dequeue() == 0);
		Assert.assertTrue(queue.head() == 1);
	}

}
