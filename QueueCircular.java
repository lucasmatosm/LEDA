package adt.queue;

public class QueueCircular<T> implements Queue<T> {

	private T[] array;
	private int tail, head;
	private int fim = 0, inicio = 0, tamanho = 0;
		
	
	@SuppressWarnings("unchecked")
	public QueueCircular(int size) {
		array = (T[])new Object[size];
		tail = -1;
		head = 0;
		
	}

	@Override
	public T head() {
		if(isEmpty()){
			return null;
		}else{
			return array[head];
		}
	}

	@Override
	public boolean isEmpty() {
		if (tamanho == 0) {
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean isFull() {
		if(!isEmpty()){
			if (tamanho == array.length) {
				return true;
			}else{
				return false;
			}
		}
		return false;
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if (isFull()) {
			throw new QueueOverflowException();
		}else{
			if(tail == array.length-1){
				array[0] = element;
				tail = 0;
				tamanho++;
			}else{
				array[tail + 1] = element;
				tail++;
				tamanho++;
			}
		}
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		if (isEmpty()) {
			throw new QueueUnderflowException();
			
		}else{
			
			T resultado = array[head];
			if(head == array.length-1){
				head = 0;
				tamanho--;
			}else{
				head++;
				tamanho--;
			}
			return resultado;
		}
	}


}
