package adt.skipList;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class SkipListImplTest {

	private SkipList<Integer> list;
	
	@Before
	public void setUp() { 
		list = new SkipListImpl<>(50);
	}
	
	@Test
	public void test() {
		list.insert(20,50);
		list.insert(10, 20);
		list.insert(30, 1);
		list.insert(1, 2);
		System.out.println(list.search(50));
		System.out.println(list.search(30));
		System.out.println(list.search(10));
		System.out.println(list.search(5));
		System.out.println(list.size());
		System.out.println(list.height());
		//list.insert(5, 10);
		System.out.println(Arrays.toString(list.toArray()));
		list.remove(10);
		System.out.println(Arrays.toString(list.toArray()));
		list.remove(20);
		System.out.println(Arrays.toString(list.toArray()));
		list.remove(1);
		System.out.println(Arrays.toString(list.toArray()));
		list.remove(30);
		System.out.println(Arrays.toString(list.toArray()));
	}

}
