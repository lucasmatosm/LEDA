package heap;


public class MaxHeapImpl<T extends Comparable<? super T>> implements MaxHeap<T> {
	
	private static final int INITIAL_SIZE = 20;
	private static final int INCREASING_FACTOR = 10;
	T[] heap;
	int size;
	
	public void setCompareTo(Object t){
		T[] copyHeap = (T[])new Comparable[heap.length];
		System.arraycopy(heap, 0, copyHeap, 0, heap.length);
		T[] heap = (T[])new Comparable[copyHeap.length];
		buildHeap(copyHeap);
		
	}
	
	public MaxHeapImpl(){
		heap = (T[])new Comparable[INITIAL_SIZE];
		size = 0;
	}

	private int parent(int i){
		return (i-1)/2;
		
	}
	
	private int left(int i){
		return (2*i) +1;
	}

	private int right(int i){
		return (2*i) +2;
	}

	@Override
    public void buildHeap(T[] array){
                            
            size  = array.length;
            
            setSize(size);
            
            for(int j = 0;j<heap.length;j++){
                    heap[j]=array[j];
                    
            }
            
            for(int i=(size - 1)/2 ;i>=0;i--){
                    heapify(i);
            }
            
            
    }
    
    public void setSize(int novoTamanho){
            heap = (T[]) new Comparable[novoTamanho];
    }
	
	private void heapify(int position){
		
		int maior = position;
		int left = left(position);
		int right = right(position);
		if(left < size && heap[left].compareTo(heap[position])>0){
			maior = left;		
		}else{
			maior = position;
		}
		if((right < size) && (heap[right].compareTo(heap[maior])>0)){
			maior = right;
		}
		if(maior != position){
			Util.swap(heap,position, maior);
			heapify(maior);
		}
	
	}
	
	@Override
	public boolean isEmpty(){
		return size==0;
	}
	
	@Override
	public void insert(T element) {
		if(size == INITIAL_SIZE){
			int tamanhoNovoHeap = heap.length + INCREASING_FACTOR;
			T[] novoHeap = (T[]) new Comparable[tamanhoNovoHeap];
			for(int i = 0; i < heap.length; i++){
				novoHeap[i] = heap[i];
			}
			heap = novoHeap;
		}
		
		if (size == 0) {
			heap[size] = element;
			size++;
		} else { 
			int i = size;
			heap[size] = element;
			
			while (i > 0 && (heap[parent(i)].compareTo(element) < 0)){
				Util.swap(heap,i, parent(i));
				i = parent(i);
				element = heap[i];
			}
			
			size++; 
		}
	}

	@Override
	public T extractMaximum(){
		if(size <= 0){
			return null;
		}
		T max = heap[0];
		heap[0] = heap[size-1];
		size--;
		heapify(0);
		
		return max;
	}

	@Override
	public T maximum() {
		if (!isEmpty()) {
			return heap[0];
		}
		return null;
	}

	@Override
	public T[] heapsort(T[] array) {
		buildHeap(heap);
		for(int i = size - 1 ; size >= 1; i--){
			Util.swap(heap,0, i);
			size--;
			heapify(0);
		}
		return heap;
	}
	
	@Override
	public T[] toArray() {
		return heap;
	}
	
}
