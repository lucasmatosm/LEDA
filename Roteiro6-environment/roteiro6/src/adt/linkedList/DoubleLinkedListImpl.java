package adt.linkedList;

public class DoubleLinkedListImpl<T> extends SingleLinkedListImpl<T> implements
		DoubleLinkedList<T> {
	
	DoubleLinkedListNode<T> head;
	DoubleLinkedListNode<T> last;
	
	public DoubleLinkedListImpl(){
		head = new DoubleLinkedListNode<T>();
		last = new DoubleLinkedListNode<T>();
	}
	
	@Override
	public void remove(T item) { //REMOVE OTIMIZADO
        if (last.data == item){
        	removeFirst();
        }
        else if(head.data == item){
        	removeLast();
        }
        else{
            DoubleLinkedListNode<T> auxiliar = head;
            while(!(auxiliar.isNIL()) && auxiliar.data != item)
            	auxiliar = (DoubleLinkedListNode<T>) auxiliar.next;
            if(!(auxiliar.isNIL())){
            	auxiliar.previous.next = auxiliar.next;
                ((DoubleLinkedListNode<T>)auxiliar.next).previous = auxiliar.previous;
            }
        }
	}
	
	@Override
	public T search(T element){ //SEARCH OTIMIZADA
		DoubleLinkedListNode<T> auxHead = head;
		DoubleLinkedListNode<T> auxLast = last;
		while(auxHead != auxLast && auxHead.data != element && auxLast.data != element){
			auxHead = (DoubleLinkedListNode<T>) auxHead.next;
			auxLast = auxLast.previous;
		}
		if(auxHead.data == element){
			return auxHead.data;
		}
		if(auxLast.data == element){
			return auxHead.data;
		}
		return null;
	}
	
	public void insertLast(T element){
		DoubleLinkedListNode<T> newLast = new DoubleLinkedListNode<T>(element,new DoubleLinkedListNode<T>(), last);
		newLast.previous = last;
		newLast.next = new DoubleLinkedListNode();
		last.next = newLast;
		if(last.isNIL()){
			head = newLast;
		}
		last = newLast;
	}
	
	@Override
	public void insertFirst(T element) {
		DoubleLinkedListNode<T> newHead = new DoubleLinkedListNode<T>(element, head, new DoubleLinkedListNode<T>());
		newHead.next = head;
		newHead.previous = new DoubleLinkedListNode<T>();
		head.previous = newHead;
		if(head.isNIL()){
			last = newHead;
		}
		head = newHead;
	}
	
	

	@Override
	public void removeFirst() {
		if(head.isNIL() == false){
			head = (DoubleLinkedListNode<T>) head.next;
			if(head.isNIL()){
				last = head;
			}
			head.previous = new DoubleLinkedListNode<T>();
		}
	}

	@Override
	public void removeLast() {
		if(last.isNIL() == false){
			last = last.previous;
			if(last.isNIL()){
				head = last;
			}
			last.next = new DoubleLinkedListNode<T>();
		}
	}
}
