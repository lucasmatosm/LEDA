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
	  //TODO Implement your code
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
	public void insert(int key, V newValue) {
		// TODO Auto-generated method stub

	}

	@Override
	public void insert(int key, V newValue, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void remove(int key) {
		// TODO Auto-generated method stub

	}

	@Override
	public int height() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public SkipNode<V> search(int key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size(){
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public SkipNode<V>[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

}
