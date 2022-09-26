package adt.skipList;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;
import org.junit.Before;




public class TestSkipList {
    
    SkipListImpl<Integer> list;
    
    @Before
    public void setUp(){
        list = new SkipListImpl<Integer>(5);
    }
    
    @Test
    public void size(){
        assertEquals(0, list.size());
        
        list.insert(1, 2, 3);
        list.insert(2, 4);
        assertEquals(2, list.size());
        
        list.insert(3, 5);
        list.insert(7, 90);
        assertEquals(4, list.size());
        
        System.out.println(list.search(1));
    }
    
    @Test
    public void testInsert(){
//        list.insert(0, 6);
//        Assert.assertEquals(1, list.size());
        
        for(int i=0; i<10; i++){
            list.insert(i, i);
        }
        System.out.println(Arrays.toString(list.toArray()));
        //assertEquals(list.size(), 10);
    }
    @Test
    public void testHeight(){
        for(int i=0; i<10; i++){
            list.insert(i, i);        
        }
        assertEquals(list.height(), 5);
    }
    
    @Test
    public void testeAltura(){
        list.insert(0, 2, 1);
        list.insert(2, 5, 3);
        assertEquals(3, list.height());
    }
    
    @Test
    public void testRemove(){
        for(int i=0; i<10; i++){
            list.insert(i, i);
        }
        System.out.println("\n" + Arrays.toString(list.toArray()));
        list.remove(1);
        System.out.println("\n" + Arrays.toString(list.toArray()));
    }
    
}