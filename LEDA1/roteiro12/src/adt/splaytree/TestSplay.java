package adt.splaytree;

import static org.junit.Assert.*;
import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import adt.bst.BSTImpl;

public class TestSplay {

	private SplayTreeImpl<Integer> teste1;
	private SplayTreeImpl<Integer> teste2;
	BSTImpl<Integer> teste3;

	@Before
	public void setup() {
		teste1 = new SplayTreeImpl<Integer>();
		teste2 = new SplayTreeImpl<Integer>();
		teste3 = new BSTImpl<Integer>();
		assertEquals(true, teste1.isEmpty());
		assertEquals(-1, teste1.height());
		assertEquals(null, teste2.getRoot().getData());
		teste2.insert(50);
		teste2.insert(75);
		teste2.insert(90);
		teste2.insert(60);
		teste2.insert(40);
		teste2.insert(22);
		teste2.insert(55);
		teste2.insert(65);
		teste2.insert(58);
		teste2.insert(63);
		teste2.insert(25);
		teste3.insert(25);
		teste3.insert(22);
		teste3.insert(63);
		teste3.insert(58);
		teste3.insert(65);
		teste3.insert(90);
		teste3.insert(75);
		teste3.insert(55);
		teste3.insert(60);
		teste3.insert(40);
		teste3.insert(50);
		// System.out.println("Completo"+Arrays.toString(teste3.preOrder()));
		System.out.println("Completo" + Arrays.toString(teste2.preOrder()));
		// System.out.println("certo"+Arrays.toString(teste3.preOrder()));
		/*
		 * No momento, teste2 está neste formato:
		 * 
		 * 25 / \ 22 63 / \ 58 65 / \ \ 55 60 90 / / 40 75 \ 50
		 */

		/*
		 * System.out.println(teste2.getRoot().getData());
		 * System.out.println(teste2.getRoot().getLeft().getData());
		 * System.out.println(teste2.getRoot().getRight().getData());
		 */
		assertEquals("[25, 22, 63, 58, 55, 40, 50, 60, 65, 90, 75]",
				Arrays.toString(teste2.preOrder()));
	}

	@Test
	public void testInsertT() {
		teste1.insert(100);
		assertEquals(teste1.getRoot().getData(), new Integer(100));
		assertEquals(1, teste1.size());
		teste1.insert(500);
		assertEquals(teste1.getRoot().getData(), new Integer(500));
		assertEquals(2, teste1.size());
		teste1.insert(300);
		assertEquals(teste1.getRoot().getData(), new Integer(300));
		assertEquals(3, teste1.size());
		teste1.insert(400);
		assertEquals(teste1.getRoot().getData(), new Integer(400));
		assertEquals(4, teste1.size());
		teste1.insert(900);
		assertEquals(teste1.getRoot().getData(), new Integer(900));
		assertEquals(5, teste1.size());
		teste1.insert(0);
		assertEquals(teste1.getRoot().getData(), new Integer(0));
		assertEquals(6, teste1.size());
		teste1.insert(-190909);
		assertEquals(teste1.getRoot().getData(), new Integer(-190909));
		assertEquals(7, teste1.size());
		teste1.insert(99999);
		assertEquals(teste1.getRoot().getData(), new Integer(99999));
		assertEquals(8, teste1.size());
		teste1.insert(888);
		assertEquals(teste1.getRoot().getData(), new Integer(888));
		assertEquals(9, teste1.size());
		teste1.insert(-888);
		assertEquals(teste1.getRoot().getData(), new Integer(-888));
		assertEquals(10, teste1.size());
		teste1.insert(11111);
		assertEquals(teste1.getRoot().getData(), new Integer(11111));
		assertEquals(11, teste1.size());
		teste1.insert(0101);
		assertEquals(teste1.getRoot().getData(), new Integer(0101));
		assertEquals(12, teste1.size());
		teste1.insert(-99999);
		assertEquals(teste1.getRoot().getData(), new Integer(-99999));
		assertEquals(13, teste1.size());
		teste1.insert(Integer.MIN_VALUE);
		assertEquals(teste1.getRoot().getData(), new Integer(Integer.MIN_VALUE));
		assertEquals(14, teste1.size());
		teste1.insert(Integer.MAX_VALUE);
		assertEquals(teste1.getRoot().getData(), new Integer(Integer.MAX_VALUE));
		assertEquals(15, teste1.size());
	}

	@Test
	public void testRemoveT() {
		assertEquals(11, teste2.size());
		teste2.remove(22);
		assertEquals(new Integer(25), teste2.getRoot().getData());
		assertEquals(10, teste2.size());
		teste2.remove(58);
		assertEquals(new Integer(63), teste2.getRoot().getData());
		assertEquals(9, teste2.size());
		teste2.remove(64);
		assertEquals(new Integer(65), teste2.getRoot().getData());
		assertEquals(9, teste2.size());
		teste2.remove(65);
		assertEquals(new Integer(90), teste2.getRoot().getData());
		assertEquals(8, teste2.size());
		teste2.remove(55);
		assertEquals(new Integer(25), teste2.getRoot().getData());
		assertEquals(7, teste2.size());
		teste2.remove(25);
		assertEquals(new Integer(90), teste2.getRoot().getData());
		assertEquals(6, teste2.size());
		teste2.remove(60);
		assertEquals(new Integer(63), teste2.getRoot().getData());
		assertEquals(5, teste2.size());
		teste2.remove(50);
		assertEquals(new Integer(40), teste2.getRoot().getData());
		assertEquals(4, teste2.size());
		teste2.remove(75);
		assertEquals(new Integer(90), teste2.getRoot().getData());
		assertEquals(3, teste2.size());
		teste2.remove(40);
		assertEquals(new Integer(90), teste2.getRoot().getData());
		assertEquals(2, teste2.size());
		teste2.remove(63);
		assertEquals(new Integer(90), teste2.getRoot().getData());
		assertEquals(1, teste2.size());
		teste2.remove(90);
		assertNull(teste2.getRoot().getData());
		assertEquals(0, teste2.size());
	}

	@Test
	public void testSearchT() {
		teste2.search(1);
		// System.out.println("errad"+Arrays.toString(teste2.preOrder()));
		assertEquals(new Integer(22), teste2.getRoot().getData());
		assertEquals(teste2.size(), 11);
		teste2.search(35);
		// System.out.println("errad"+Arrays.toString(teste2.preOrder()));
		assertEquals(new Integer(40), teste2.getRoot().getData());
		assertEquals(teste2.size(), 11);
		teste2.search(58);
		assertEquals(new Integer(58), teste2.getRoot().getData());
		assertEquals(teste2.size(), 11);
		teste2.search(100);
		assertEquals(new Integer(90), teste2.getRoot().getData());
		assertEquals(teste2.size(), 11);
	}
}
