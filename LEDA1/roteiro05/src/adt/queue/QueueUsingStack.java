package adt.queue;

import adt.stack.Stack;
import adt.stack.StackImpl;
import adt.stack.StackOverflowException;
import adt.stack.StackUnderflowException;

public class QueueUsingStack<T> implements Queue<T> {

	private Stack<T> stack1;
	private Stack<T> stack2;
	private int qt;
	private int size;

	public QueueUsingStack(int size) {
		stack1 = new StackImpl<T>(size);
		stack2 = new StackImpl<T>(size);
		qt = 0;
		this.size = size;
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if (element != null) {
			if (qt < size) {
				try {
					stack1.push(element);
					qt++;
				} catch (StackOverflowException e) {/* Nunca entra */
				}
			} else {
				throw new QueueOverflowException();
			}
		}
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		T result = head();
		if (result != null) {
			try {
				stack2.pop();
				qt--;
			} catch (StackUnderflowException e) {/* Nunca entra */
			}
			return result;
		} else
			throw new QueueUnderflowException();
	}

	@Override
	public T head() {
		T result = null;
		if (stack2.isEmpty()) {
			while (!stack1.isEmpty() && !stack2.isFull()) {
				try {
					stack2.push(stack1.top());
					stack1.pop();
				} catch (StackOverflowException | StackUnderflowException e) {
				}
			}
		}
		if (!stack2.isEmpty()) {
			result = stack2.top();
		}
		return result;
	}

	@Override
	public boolean isEmpty() {
		return stack1.isEmpty() && stack2.isEmpty();
	}

	@Override
	public boolean isFull() {
		return qt == size;
	}

}
