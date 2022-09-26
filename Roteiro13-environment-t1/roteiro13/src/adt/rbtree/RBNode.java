package adt.rbtree;

import adt.bst.BSTNode;

public class RBNode<K extends Comparable<? super K>, V> extends BSTNode<K, V> {
	enum Colour {BLACK, RED};
	private Colour colour;
	
	public RBNode() {
		this.colour = Colour.BLACK;
	}

	public Colour getColour() {
		return colour;
	}

	public void setColour(Colour colour) {
		if(isEmpty() && colour == Colour.RED){
			throw new RuntimeException("NIL node colour cannot be RED");
		}
		this.colour = colour;
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj) && this.colour == ((RBNode<K,V>) obj).getColour();
	}
	@Override
	public String toString(){
		String resp = "NIL";
		if(!isEmpty()){
			resp = "(" + key.toString();
			if(colour == Colour.RED){
				resp = resp + ",R)"; 
			}else{
				resp = resp + ",B)";
			}
		}
		return resp;
	}
	
}
