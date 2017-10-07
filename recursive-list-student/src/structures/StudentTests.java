package structures;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

public class StudentTests {
private ListInterface<String> list;
	
	@Before
	public void setup(){
        list = new RecursiveList<String>(); //will be :"first", "second", "third", "fourth", "last"
  		list.insertFirst("third");
  		list.insertFirst("first");
  		list.insertAt(1, "second");
  		list.insertLast("last");
  		list.insertAt(3, "fourth");
	}
	
	@Test
	public void testEmpty() {
		assertTrue(list.remove("third"));
		assertEquals(list.removeFirst(), "first");
		assertEquals(list.removeLast(), "last");
		assertEquals(list.removeFirst(), "second");
		assertEquals(list.removeLast(), "fourth");
	}
	
	@Test (timeout = 500)
	public void testInsertFirstIsEmptySizeAndGetFirst1() {
		list = new RecursiveList<String>();
		
		assertTrue("Newly constructed list should be empty.", list.isEmpty());
		assertEquals("Newly constructed list should be size 0.", 0, list.size());
		assertEquals("Insert First should return instance of self", list, list.insertFirst("hello"));
		assertFalse("List should now have elements.", list.isEmpty());
		assertEquals("List should now have 1 element.", 1, list.size());
		assertEquals("First element should .equals \"hello\".", "hello", list.getFirst());
		list.insertFirst("world");
		assertEquals(2, list.size());
		list.insertFirst("foo");
		assertEquals(3, list.size());
		assertEquals("First element should .equals \"foo\".", "foo", list.getFirst());
	}
	
	@Test (timeout=500)
	public void testInserts() {
		assertFalse(list.isEmpty());
		assertEquals(list.size(),5);
		list.insertAt(4,"hello");
			assertEquals(list.size(),6);
		list.insertFirst("newFirst");
			assertEquals(list.size(),7);
		list.insertAt(0, "newestFirst");
			assertEquals(list.size(),8);
		list.insertAt(list.size(), "superLast");
			assertEquals(list.size(),9);
		try {
			list.insertAt(list.size()+1, "bob");
		} catch(IndexOutOfBoundsException e) {}
		try {
			list.insertAt(-2, "suzie");
		} catch(IndexOutOfBoundsException e) {}
			
	}
	
	@Test (timeout=500)
	public void testGets() {
		assertEquals(list.getLast(), "last");
		assertEquals(list.getFirst(), "first");
		assertEquals(list.get(0),"first");
		assertEquals(list.get(list.size()-1),"last");
		assertEquals(list.get(1),"second");
		assertEquals(list.get(2), "third");
		try {
			assertEquals(list.get(list.size()), null);
		} catch(IndexOutOfBoundsException e) {
		}
	}
	
	@Test (timeout=500)
	public void testIndexes() {
		assertEquals(list.indexOf("last"),4);
		assertEquals(list.indexOf("first"),0);
		assertEquals(list.indexOf("second"),1);
		assertEquals(list.indexOf("third"),2);
		assertEquals(list.indexOf("fourth"),3);
		assertEquals(list.indexOf("bob"),-1);
	}
	
	@Test (timeout=500)
	public void testRemoves() {
		
		assertTrue(list.remove("second"));
		assertEquals(list.size(),4);
		assertEquals("third",list.removeAt(1));
		assertEquals(list.size(),3);
		assertEquals("first",list.removeFirst());
		assertEquals(list.size(),2);
		assertEquals("last",list.removeLast());
		assertEquals(list.size(),1);
		assertEquals(list.getFirst(),"fourth");
		assertEquals(list.size(),1);
	}
	
	@Test (timeout=500)
	public void testIndexOf() {
		assertEquals(list.indexOf("first"),0);
		assertEquals(list.indexOf("last"),4);
		assertEquals(list.indexOf("second"),1);
	}
	
	@Test (timeout=500)
	public void testIterator() {
		for(String s: list) {
			assertTrue(s != null);
			System.out.println(s);
		} System.out.println();
	}
	
	@Test (timeout=500)
	public void insertRemove() {
		list = new RecursiveList();
		assertTrue(list.isEmpty());
		list.insertAt(0, "first");
		assertEquals(list.size(),1);
		list.insertFirst("first");
		assertEquals(list.size(),2);
		list.insertLast("first");
		assertEquals(list.size(),3);
		list.remove("first");
		list.removeFirst();
		list.removeLast();
		assertTrue(list.isEmpty());
	}
	
	@Test (timeout = 5000, expected = IndexOutOfBoundsException.class)
	public void testUnderBounds() {
		list.get(-1);
	}
	
	@Test (timeout = 5000, expected = IndexOutOfBoundsException.class)
	public void testOutOfBounds() {
		list.get(list.size());
	}
	
	@Test (timeout = 500)
	public void testInsertAt() {
		System.out.println("Add a bunch at odd ends");
		list.insertAt(0, "initial");
		list.insertAt(list.size(), "superlast");
		list.insertAt(2,"middle");
		list.insertAt(list.size()-1, "last2");
		for(String s: list) {
			assertTrue(s != null);
			System.out.println(s);
		} System.out.println();
	}
	@Test (timeout = 500)
	public void testRemove() {
		assertFalse(list.remove("bob")); //nowhere
		assertTrue(list.remove("last")); //last
		assertTrue(list.remove("first")); //first
		assertTrue(list.remove("third")); //middle
	}
	
	@Test (timeout = 500)
	public void testRemoveLast() {
		assertEquals(list.removeLast(), "last");
		list.removeLast(); list.removeLast(); list.removeLast();
		assertEquals(list.removeLast(), "first");
	}
	
	@Test (timeout = 500)
	public void testRemoveFirst() {
		assertEquals(list.removeFirst(), "first");
		list.removeFirst(); list.removeFirst(); list.removeFirst();
		assertEquals(list.removeFirst(), "last");
	}
	
	@Test (timeout = 500)
	public void test() {
		
	}
}