package heap;


import java.util.Arrays;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class testestsets<T> {

    private MaxHeapImpl<Integer> heap;

    @Before
    public void setUp() throws Exception {
        heap = new MaxHeapImpl<Integer>();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testIsEmpty() {
        Assert.assertTrue(heap.isEmpty());
        heap.insert(6);
        Assert.assertFalse(heap.isEmpty());
        heap.extractMaximum();
        Assert.assertTrue(heap.isEmpty());
    }

    @Test
    public void testInsert() {
        for (int i = 0; i < 20; i++) {
            heap.insert(i);
            Assert.assertEquals(i, (int) heap.maximum());
        }
    } 

    @Test
    public void testExtractMaximum() {
        for (int i = 0; i < 20; i++) {
            heap.insert(i);
            Assert.assertEquals(i, (int) heap.maximum());
        }

    }

    @Test
    public void testMaximum() {
        for (int i = 0; i < 20; i++) {
            heap.insert(i);
            Assert.assertEquals(i, (int) heap.maximum());
        }
    }

/*    @Test
    public void testHeapsort() {
        Vector<Integer> ordered = new Vector<Integer>();
        for (int i = 0; i < 25; i++) {
            heap.insert(i);
            ordered.addElement(i);
        }
        Assert.assertArrayEquals(ordered.toArray(new HeapElement[30]),heap.heapsort());
        heap.buildHeap();
    }*/
    
    @Test
    public void testBuildHeap(){
        Integer[] vetor1 = {27,17,3,16,13,10,1,5,7,12,4,8,9,0};
        Integer[] heap1 = {27,17,10,16,13,9,1,5,7,12,4,8,3,0};
        
        MaxHeapImpl<Integer> heap = new MaxHeapImpl<Integer>();
        
        heap.buildHeap(vetor1);
        
       Assert.assertArrayEquals(heap1,heap.toArray());    
        
    }
    
    @Test
    public void extractMaximum(){
        MaxHeapImpl<Integer> heap = new MaxHeapImpl<Integer>();
        
        for(int i=0; i<25; i++){
            heap.insert(new Integer(i));
        }
//        Integer a[] = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22};
//        heap.buildHeap(a);
        
        for(T t : ((T[]) heap.toArray())){
            System.out.print(t + " ");
        }
        System.out.println();
        
        //heap.extractMaximum();
        Assert.assertEquals(new Integer(24), heap.extractMaximum());
        
        for(T t : ((T[]) heap.toArray())){
            System.out.print(t + " ");
        }
        System.out.println();
        
        //heap.extractMaximum();
        Assert.assertEquals(new Integer(23), heap.extractMaximum());
        
        for(T t : ((T[]) heap.toArray())){
            System.out.print(t + " ");
        }
        System.out.println();
    }
    
    @Test
    public void testHeapSort(){
        
        MaxHeapImpl<Integer> heap = new MaxHeapImpl<Integer>();
        
//        for(int i=0; i<25; i++){
//            heap.insert(new Integer(i));
//        }
         Integer[] a = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23};
         MaxHeapImpl<Integer> b = new MaxHeapImpl<Integer>();
         b.buildHeap(a);
         System.out.println(Arrays.toString(b.toArray()));
         System.out.println(Arrays.toString(b.heapsort(a)));
    }
    
} 
