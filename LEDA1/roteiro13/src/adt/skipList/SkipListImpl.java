package adt.skipList;

public class SkipListImpl<V> implements SkipList<V> {

	public SkipNode<V> root;
	protected int level;
	protected int maxLevel;

	// SET THIS VALUE TO TRUE IF YOU ARE IMPLEMENTING MAX_LEVEL = LEVEL
	// SET THIS VALUE TO FALSE IF YOU ARE IMPLEMENTING MAX_LEVEL != LEVEL
	protected boolean useMaxLevelAsLevel = true;
	protected double probability = 0.5;
	public SkipNode<V> NIL;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public SkipListImpl(int maxLevel) {
		if (useMaxLevelAsLevel) {
			this.level = maxLevel;
		} else {
			this.level = 1;
		}
		this.maxLevel = maxLevel;
		root = new SkipNode(Integer.MIN_VALUE, maxLevel, new Integer(
				Integer.MIN_VALUE));
		NIL = new SkipNode(Integer.MAX_VALUE, maxLevel, new Integer(
				Integer.MAX_VALUE));
		connectRootToNil();
	}

	/**
	 * Faz a ligacao inicial entre os apontadores forward do ROOT e o NIL Caso
	 * esteja-se usando o level do ROOT igual ao maxLevel esse metodo deve
	 * conectar todos os forward. Senao o ROOT eh inicializado com level=1 e o
	 * metodo deve conectar apenas o forward[0].
	 */
	private void connectRootToNil() {
		for (int i = 0; i < root.getForward().length; i++) {
			root.getForward()[i] = NIL;
		}
	}

	/**
	 * Metodo que gera uma altura aleatoria para ser atribuida a um novo no no
	 * metodo insert(int,V)
	 */
	@SuppressWarnings("unused")
	private int randomLevel() {
		int randomLevel = 1;
		double random = Math.random();
		while (Math.random() <= probability && randomLevel < maxLevel) {
			randomLevel = randomLevel + 1;
		}
		return randomLevel;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void insert(int key, V newValue) {
		SkipNode[] update = new SkipNode[maxLevel];
		SkipNode aux = root;
		for (int i = level - 1; i >= 0; i--) {
			while (aux.getForward(i) != null
					&& aux.getForward(i).getKey() < key) {
				aux = aux.getForward(i);
			}
			update[i] = aux;
		}
		aux = aux.getForward(0);
		if (aux.key == key) {
			remove(key);
			insert(key, newValue);
		} else {
			SkipNode node = new SkipNode(key, randomLevel(), newValue);
			for (int i = 0; i < node.height; i++) {
				node.forward[i] = update[i].forward[i];
				update[i].forward[i] = node;
			}
		}

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void insert(int key, V newValue, int height) {
		SkipNode[] update = new SkipNode[maxLevel];
		SkipNode aux = root;
		for (int i = level - 1; i >= 0; i--) {
			while (i < aux.getForward().length
					&& aux.getForward(i).getKey() < key) {
				aux = aux.getForward(i);
			}
			update[i] = aux;
		}
		aux = aux.getForward(0);
		if (aux.key == key) {
			remove(key);
			insert(key, newValue, height);
		} else {
			SkipNode node = new SkipNode(key, height, newValue);
			for (int i = 0; i < node.height; i++) {
				node.forward[i] = update[i].forward[i];
				update[i].forward[i] = node;
			}
		}

	}

	@SuppressWarnings("rawtypes")
	@Override
	public void remove(int key) {
		SkipNode[] update = new SkipNode[maxLevel];
		SkipNode<V> aux = root;
		for (int i = level - 1; i >= 0; i--) {
			while (i < aux.getForward().length
					&& aux.getForward(i).getKey() < key) {
				aux = aux.getForward(i);
			}
			update[i] = aux;
		}
		aux = aux.getForward(0);
		if (aux.getKey() == key) {
			for (int i = 0; i < level; i++) {
				if (!update[i].getForward()[i].equals(aux)) {
					break;
				}
				update[i].forward[i] = aux.getForward(i);
			}
		}
	}

	@Override
	public int height() {
		SkipNode<V> aux = root;
		for (int i = maxLevel - 1; i >= 0; i--) {
			if (!(aux.getForward()[i].equals(NIL))) {
				return i + 1;
			}
		}
		return 0;
	}

	@Override
	public SkipNode<V> search(int key) {
		SkipNode<V> aux = root;
		for (int i = level - 1; i >= 0; i--) {
			while (aux.getForward(i).getKey() < key) {
				aux = aux.getForward(i);
			}
		}
		aux = aux.getForward(0);

		if (aux.getKey() == key) {
			return aux;
		}
		return null;
	}

	@Override
	public int size() {
		SkipNode<V> aux = root;
		int size = 0;
		while (!aux.getForward()[0].equals(NIL)) {
			size++;
			aux = aux.getForward()[0];
		}

		return size;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public SkipNode<V>[] toArray() {
		int indice = 0;
		SkipNode<V> aux = root;
		SkipNode[] array = new SkipNode[size() + 2];
		array[0] = root;
		aux = aux.getForward()[0];
		while (!aux.equals(NIL)) {
			array[++indice] = aux;
			aux = aux.getForward()[0];

		}
		array[size() + 1] = aux;

		return array;
	}

}
