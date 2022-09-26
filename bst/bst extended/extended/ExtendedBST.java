package adt.bst.extended; 
  
import adt.bst.BST; 
  
public interface ExtendedBST<T extends Comparable<T>> extends BST<T> { 
    public boolean equals(ExtendedBST<T> tree); 
      
    public boolean isSimilar(ExtendedBST<T> tree); 
      
    public boolean contains(ExtendedBST<T> subtree); 
      
    public void mirror(); 
} 