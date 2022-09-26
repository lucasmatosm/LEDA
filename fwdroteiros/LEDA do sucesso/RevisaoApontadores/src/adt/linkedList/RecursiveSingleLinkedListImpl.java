package adt.linkedList;

public class RecursiveSingleLinkedListImpl<T> implements LinkedList<T> {

	protected T data;
	protected RecursiveSingleLinkedListImpl<T> next;
	
	@Override
	public boolean isEmpty() {
		if(data == null){
			return true;
		}
		return false;
	}

	@Override
	public int size() {
		if(isEmpty()){
			return 0;
		}
		return 1 + next.size();
	}

	@Override
	public T search(T element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(T element) {
		// TODO Auto-generated method stub

	}

	@Override
	public void remove(T element) {
		// TODO Auto-generated method stub

	}
	@Override
	public T[] toArray(){
		// TODO Auto-generated method stub
		return null;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public RecursiveSingleLinkedListImpl<T> getNext() {
		return next;
	}

	public void setNext(RecursiveSingleLinkedListImpl<T> next) {
		this.next = next;
	}
	
	public static void main(String args[]){
		RecursiveSingleLinkedListImpl l = new RecursiveSingleLinkedListImpl();
		System.out.println(l.isEmpty());
	}

}
