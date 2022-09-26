package adt.skipList;

public class SkipListImpl<V> implements SkipList<V> {

	protected SkipNode<V> root;
	protected int level;
	protected int maxLevel;

	//SET THIS VALUE TO TRUE IF YOU ARE IMPLEMENTING MAX_LEVEL = LEVEL
	//SET THIS VALUE TO FALSE IF YOU ARE IMPLEMENTING MAX_LEVEL != LEVEL
	protected boolean useMaxLevelAsLevel;
	protected double probability = 0.5; 
	protected SkipNode<V> NIL;
	
	public SkipListImpl(int maxLevel) {
		if(useMaxLevelAsLevel){
			this.level = maxLevel;
		}else{
			this.level = 1;
		}
		this.maxLevel = maxLevel;
		root = new SkipNode(Integer.MIN_VALUE, maxLevel, new Integer(Integer.MIN_VALUE));
		NIL = new SkipNode(Integer.MAX_VALUE, maxLevel, new Integer(Integer.MAX_VALUE));
		connectRootToNil();
	}
	
	/**
	 * Faz a ligacao inicial entre os apontadores forward do ROOT e o NIL
	 * Caso esteja-se usando o level do ROOT igual ao maxLevel esse metodo deve
	 * conectar todos os forward. Senao o ROOT eh inicializado com 
	 * level=1 e o metodo deve conectar apenas o forward[0].  
	 */
	private void connectRootToNil(){
		for (int i = 0; i < root.height; i++) {
			root.forward[i] = NIL;
		}
	}
	
	/**
	 * Metodo que gera uma altura aleatoria para ser atribuida a um novo no no metodo
	 * insert(int,V) 
	 */
	private int randomLevel(){
		int randomLevel = 1;
		double random = Math.random();
		while(Math.random() <= probability && randomLevel < maxLevel){
			randomLevel = randomLevel + 1;
		}
		return randomLevel;
	}
	
	@Override
	public void insert(int key, Object newValue) {
		insert(key, newValue, randomLevel());

	}

	@Override
	public void insert(int key, Object novoValor, int altura) {
		if (altura > maxLevel)
			throw new RuntimeException("extrapolou nível!");

		try {
			search(key).satteliteData = (V) novoValor;
			return;
		} catch (RuntimeException e) {
		}

		SkipNode<V> inserido = new SkipNode(key, altura, novoValor);
		if (altura > this.level)
			this.level = altura;

		SkipNode<V> atual = root;
		for (int i = this.level-1; i >= 0; i--) {
			while (atual.forward[i].key < key) {
				atual = atual.forward[i];
			}
			if (i < altura) {
				inserido.forward[i] = atual.forward[i];
				atual.forward[i] = inserido;
			}

		}
	}


	@Override
	public void remove(int key) {
		
		SkipNode<V> atual = root;
		SkipNode<V> removido = search(key);
		for (int i = removido.height - 1; i >= 0; i--) {
			while (atual.forward[i].key < key){
				atual = atual.forward[i];
			}
			atual.forward[i] = removido.forward[i]; 
		}

	}

	@Override
	public int height() {
		return this.level;
		
	}

	@Override
	public SkipNode<V> search(int key) {
		SkipNode<V> NOatual = root;
		for (int i = this.level-1; i >= 0; i--) {
			while (NOatual.forward[i].key <= key) {
				NOatual = NOatual.forward[i];
				if (NOatual.key == key) 
					return NOatual;
			}
		}
		return null; 
		
	}

	@Override
	public int size(){
		return size(root);
	}
	private int size(SkipNode<V> node){
		int size = 0;
		if(!node.forward[0].equals(NIL)){
			size = 1 + size(node.forward[0]);
		}
		return size;
		
	}
	
	@Override
	public SkipNode<V>[] toArray(){
		SkipNode<V>[] resultado = new SkipNode[size()];;
		toArrayRecursivo(resultado, root, 0);
		return resultado;
	}
	
	public void toArrayRecursivo(SkipNode<V>[] array,SkipNode<V> no, int aux){
		if(!(no.forward[0].equals(NIL))){
			array[aux] = no;
			toArrayRecursivo(array, no.forward[0], aux+1);
		}
	}
	

}
