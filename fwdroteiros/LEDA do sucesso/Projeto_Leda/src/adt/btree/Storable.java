package adt.btree;

public class Storable<K extends Comparable<? super K>, V> implements
		Comparable<Storable<K,V>> {

	protected K key;
	protected V value;
	
	public Storable(K key, V value) {
		this.key = key;
		this.value = value;
	}
	
	@Override
	public int compareTo(Storable<K, V> o) {
		return this.key.compareTo(o.key);
	}

	@Override
	public String toString() {
		return this.key.toString();
	}

	@Override
	public boolean equals(Object obj) {
		boolean resp = false;
		if(obj != null){
			if(obj instanceof Storable){
				resp = this.key.equals(((Storable)obj).key);
			}
		}
		return resp;
	}
}
