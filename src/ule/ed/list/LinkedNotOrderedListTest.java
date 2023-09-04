package ule.ed.list;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class LinkedNotOrderedListTest {
	private LinkedNotOrderedList<String> lista;
	
	@Before
	public void setUp() {
		 lista= new LinkedNotOrderedList<String>();
	}

	@Test
	public void LinkedVaciaTest() {
		assertEquals(0,lista.size());
	}
	
	// test addFirst comprueba que dispara excepción
	@Test(expected=NullPointerException.class)
	public void LinkedAddFirstElementoNuloTest() {
			lista.addFirst(null);
	}
	
	@Test
	public void LinkedAddFirstTest() {
		assertTrue(lista.isEmpty());
		lista.addFirst("2");
		Assert.assertFalse(lista.isEmpty());
		Assert.assertEquals("(2 )", lista.toString());
		lista.addFirst("3");
		Assert.assertEquals("(3 2 )", lista.toString());
		lista.addFirst("7");
		Assert.assertEquals("(7 3 2 )", lista.toString());
	}
	

	//test de iteradores
	@Test
	public void LinkedIteratorTest() {
		lista.addFirst("2");
		Assert.assertFalse(lista.isEmpty());
		Assert.assertEquals("(2 )", lista.toString());
		lista.addFirst("3");
		Assert.assertEquals("(3 2 )", lista.toString());
		lista.addFirst("7");
		Assert.assertEquals("(7 3 2 )", lista.toString());
		Iterator<String>  iter=lista.iterator();
		assertTrue(iter.hasNext());
		assertEquals("7", iter.next());
		assertTrue(iter.hasNext());
		assertEquals("3", iter.next());
		assertTrue(iter.hasNext());
		assertEquals("2", iter.next());
		assertFalse(iter.hasNext());
	}
	
	@Test(expected = NoSuchElementException.class)
	public void LinkedEvenIteratorNElemsParTestException() {
		Iterator<String>  iter=lista.evenPosIterator();
		iter.next();
	}
	
	@Test
	public void LinkedEvenIteratorNElemsParTest() {
		lista.addFirst("2");
		Assert.assertFalse(lista.isEmpty());
		Assert.assertEquals("(2 )", lista.toString());
		lista.addFirst("3");
		Assert.assertEquals("(3 2 )", lista.toString());
		lista.addFirst("7");
		Assert.assertEquals("(7 3 2 )", lista.toString());
		lista.addFirst("8");
		Assert.assertEquals("(8 7 3 2 )", lista.toString());

		Iterator<String>  iter=lista.evenPosIterator();
		assertTrue(iter.hasNext());
		assertEquals("7", iter.next());
		assertTrue(iter.hasNext());
		assertEquals("2", iter.next());
		assertFalse(iter.hasNext());
		
		lista.addFirst("7");
		lista.addFirst("5");
		lista.addFirst("4");
		lista.addFirst("3");
		Assert.assertEquals("(3 4 5 7 8 7 3 2 )", lista.toString());
		Iterator<String>  iter1=lista.evenPosIterator();
		assertTrue(iter1.hasNext());
		assertEquals("4", iter1.next());
		assertTrue(iter1.hasNext());
		assertEquals("7", iter1.next());
		assertTrue(iter1.hasNext());
		assertEquals("7", iter1.next());
		assertTrue(iter1.hasNext());
		assertEquals("2", iter1.next());
		assertFalse(iter1.hasNext());
		
	}

	@Test
	public void LinkedEvenIteratorNElemsParTest2() {
		lista.addFirst("2");
		lista.addFirst("3");
		lista.addFirst("7");
		Assert.assertEquals("(7 3 2 )", lista.toString());
		Iterator<String>  iter1=lista.evenPosIterator();
		assertTrue(iter1.hasNext());
		assertEquals("3", iter1.next());
		assertFalse(iter1.hasNext());
	
	}
	
	
	// TEST ITERADORES EN LISTA VACÍA
	@Test(expected=NoSuchElementException.class)
	public void LinkedNextListaVaciaTest() {
			lista.iterator().next();	}
	
	
	//TEST reverse
	@Test
	public void LinkedtestReverse() {
	assertTrue(lista.isEmpty());
	Assert.assertEquals("()", lista.reverse().toString());
	
	lista.addFirst("6");
	Assert.assertFalse(lista.isEmpty());
	Assert.assertEquals("(6 )", lista.toString());
	lista.addFirst("5");
	Assert.assertEquals("(5 6 )", lista.toString());
	lista.addFirst("4");
	Assert.assertEquals("(4 5 6 )", lista.toString());
	lista.addFirst("4");
	Assert.assertEquals("(4 4 5 6 )", lista.toString());
	Assert.assertEquals("(6 5 4 4 )", lista.reverse().toString());
	Assert.assertEquals("(4 4 5 6 )", lista.toString()); // queda en el mismo estado
	
	}
	
	@Test 
	public void testAddLastAndSize() {
		lista.addLast("A");
		lista.addLast("B");
		lista.addLast("C");
		assertEquals("(A B C )", lista.toString());
		assertEquals(3, lista.size());
	}
	
	@Test 
	public void testAddPenult() {
		lista.addPenult("B");
		assertEquals("(B )", lista.toString());
		lista.addPenult("A");
		assertEquals("(A B )", lista.toString());
		
		lista.addPenult("Z");
		assertEquals("(A Z B )", lista.toString());
		
		lista.addPenult("X");
		assertEquals("(A Z X B )", lista.toString());
		
	}
	
	@Test 
	public void testAddPos() {
		lista.addPos("Z", 1);
		assertEquals("(Z )", lista.toString());
		
		lista.addPos("B", 2);
		assertEquals("(Z B )", lista.toString());
		
		lista.addPos("A", 1);
		assertEquals("(A Z B )", lista.toString());
		
		lista.addPos("X", 3);
		assertEquals("(A Z X B )", lista.toString());
		
		lista.addPos("Y", 2);
		assertEquals("(A Y Z X B )", lista.toString());
		
		lista.addPos("Ñ", 4);
		assertEquals("(A Y Z Ñ X B )", lista.toString());
		
		
	}
	
	@Test(expected = EmptyCollectionException.class)
	public void testRemoveFirstException() throws EmptyCollectionException {
		lista.removeFirst();
	}
	@Test
	public void testRemoveFirst() throws EmptyCollectionException {
		lista.addLast("A");
		lista.addLast("B");
		lista.addLast("C");
		lista.addLast("D");
		assertEquals("(A B C D )", lista.toString());
		
		assertEquals("A", lista.removeFirst().toString());
		assertEquals("(B C D )", lista.toString());
		
		assertEquals("B", lista.removeFirst().toString());
		assertEquals("(C D )", lista.toString());
		
		assertEquals("C", lista.removeFirst().toString());
		assertEquals("(D )", lista.toString());
	}
	
	@Test(expected = EmptyCollectionException.class)
	public void testRemoveLastException() throws EmptyCollectionException {
		lista.removelast();
	
	}
	
	@Test
	public void testRemoveLast() throws EmptyCollectionException {
		lista.addLast("A");
		lista.addLast("B");
		lista.addLast("C");
		lista.addLast("D");
		assertEquals("(A B C D )", lista.toString());
		
		assertEquals("D", lista.removelast().toString());
		assertEquals("(A B C )", lista.toString());
		
		assertEquals("C", lista.removelast().toString());
		assertEquals("(A B )", lista.toString());
		
		assertEquals("B", lista.removelast().toString());
		assertEquals("(A )", lista.toString());
		
		assertEquals("A", lista.removelast().toString());
		assertEquals("()", lista.toString());
		
		
	}
	
	@Test
	public void testRemovePenul() throws EmptyCollectionException {
		lista.addLast("A");
		lista.addLast("B");
		lista.addLast("C");
		lista.addLast("D");
		assertEquals("(A B C D )", lista.toString());
		
		assertEquals("C", lista.removePenult().toString());
		assertEquals("(A B D )", lista.toString());
		
		assertEquals("B", lista.removePenult().toString());
		assertEquals("(A D )", lista.toString());
		
		assertEquals("A", lista.removePenult().toString());
		assertEquals("(D )", lista.toString());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testGetElemPosException() {
		lista.addLast("A");
		lista.addLast("B");
		lista.addLast("C");
		lista.addLast("D");
		lista.getElemPos(0);
		lista.getElemPos(5);
	}	
	
	@Test
	public void testGetElemPos() {
		lista.addLast("A");
		lista.addLast("B");
		lista.addLast("C");
		lista.addLast("D");
		assertEquals("(A B C D )", lista.toString());
		
		assertEquals("A", lista.getElemPos(1).toString());
		assertEquals("B", lista.getElemPos(2).toString());
		assertEquals("C", lista.getElemPos(3).toString());
		assertEquals("D", lista.getElemPos(4).toString());
	}
	
	@Test(expected = NoSuchElementException.class)
	public void testGetPosLastException() {
		lista.addLast("A");
		lista.addLast("B");
		lista.addLast("C");
		lista.addLast("D");
		assertEquals("(A B C D )", lista.toString());
		lista.getPosLast("Z");
	}
	
	
	@Test
	public void testGetPosLast() {
		lista.addLast("A");
		lista.addLast("B");
		lista.addLast("C");
		lista.addLast("D");
		assertEquals("(A B C D )", lista.toString());
		assertEquals(1, lista.getPosLast("A"));
		assertEquals(2, lista.getPosLast("B"));
		assertEquals(4, lista.getPosLast("D"));
		
		lista.addLast("A");
		lista.addLast("F");
		assertEquals("(A B C D A F )", lista.toString());
		assertEquals(5, lista.getPosLast("A"));
		
	}
	
	@Test(expected = NoSuchElementException.class)
	public void testRemoveAllException() throws EmptyCollectionException {
		lista.addLast("A");
		lista.addLast("B");
		lista.addLast("C");
		lista.addLast("D");
		assertEquals("(A B C D )", lista.toString());
		lista.removeAll("Z");
	}
	
	
	
	@Test
	public void testRemoveAll() throws EmptyCollectionException {
		lista.addLast("A");
		lista.addLast("B");
		lista.addLast("C");
		lista.addLast("D");
		assertEquals("(A B C D )", lista.toString());
		
		assertEquals(1, lista.removeAll("A"));
		assertEquals("(B C D )", lista.toString());
		lista.addLast("B");
		lista.addLast("A");
		assertEquals("(B C D B A )", lista.toString());
		assertEquals(2, lista.removeAll("B"));
		assertEquals("(C D A )", lista.toString());
	}

	
	@Test(expected = NoSuchElementException.class)
	public void testRemoveElemException() throws EmptyCollectionException {
		lista.addLast("A");
		lista.addLast("B");
		lista.addLast("A");
		lista.addLast("D");
		assertEquals("(A B A D )", lista.toString());
		lista.removeElem("Z");
	}
	
	
	@Test
	public void testRemoveElem() throws EmptyCollectionException {
		lista.addLast("A");
		lista.addLast("B");
		lista.addLast("A");
		lista.addLast("D");
		assertEquals("(A B A D )", lista.toString());
		assertEquals(1, lista.removeElem("A"));
		assertEquals("(B A D )", lista.toString());
		assertEquals(1, lista.removeElem("B"));
		assertEquals("(A D )", lista.toString());
		assertEquals(2, lista.removeElem("D"));
		assertEquals("(A )", lista.toString());
		assertEquals(1, lista.removeElem("A"));
		assertEquals("()", lista.toString());
		
		lista.addLast("A");
		lista.addLast("B");
		lista.addLast("A");
		lista.addLast("X");
		assertEquals("(A B A X )", lista.toString());
		assertEquals(4, lista.removeElem("X"));
	}
	
	@Test
	public void testRemovePosLast() throws EmptyCollectionException {
		lista.addLast("A");
		lista.addLast("B");
		lista.addLast("C");
		lista.addLast("D");
		assertEquals("(A B C D )", lista.toString());
		assertEquals(1, lista.removePosLast("A"));
		assertEquals("(B C D )", lista.toString());
		lista.addLast("B");
		assertEquals("(B C D B )", lista.toString());
		assertEquals(4, lista.removePosLast("B"));
	}
	
	
	
	@Test(expected = IllegalArgumentException.class)
	public void testRemoveElemPosException() throws EmptyCollectionException {
		lista.addLast("A");
		lista.addLast("B");
		lista.addLast("C");
		lista.addLast("D");
		assertEquals("(A B C D )", lista.toString());
		lista.removeElemPos(0);
	}	
	
	
	
	@Test
	public void testRemoveElemPos() throws EmptyCollectionException {
		lista.addLast("A");
		lista.removeElemPos(1);
		assertEquals("()", lista.toString());	
		
		lista.addLast("A");
		lista.addLast("B");
		lista.addLast("C");
		lista.addLast("D");
		assertEquals("(A B C D )", lista.toString());
		assertEquals("A", lista.removeElemPos(1));
		assertEquals("(B C D )", lista.toString());
		assertEquals("C", lista.removeElemPos(2));
		assertEquals("(B D )", lista.toString());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testFromUntilNotIncludedStringException() {
		lista.addLast("A");
		lista.addLast("B");
		lista.addLast("C");
		lista.addLast("D");
		assertEquals("(A B C D )", lista.toString());
		lista.FromUntilNotIncluded(0, 0);
	}
	
	@Test
	public void testFromUntilNotIncludedString() {
		assertEquals("()", lista.FromUntilNotIncluded(1, 3));
		lista.addLast("A");
		lista.addLast("B");
		lista.addLast("C");
		lista.addLast("D");
		assertEquals("(A B C D )", lista.toString());
		assertEquals("(B )", lista.FromUntilNotIncluded(1, 3));
		assertEquals("(B C )", lista.FromUntilNotIncluded(1, 4));
		assertEquals("(B C D )", lista.FromUntilNotIncluded(1, 5));
	}
	
	@Test(expected = NoSuchElementException.class)
	public void testFromUntilNotIncludedIteratorException() {
		Iterator<String>  iter=lista.fromUntilIterator(1, 3);
		iter.next();
	}
	
	@Test
	public void testFromUntilNotIncludedIterator() {
		lista.addLast("A");
		lista.addLast("B");
		lista.addLast("C");
		lista.addLast("D");
		assertEquals("(A B C D )", lista.toString());
		
		Iterator<String>  iter=lista.fromUntilIterator(1, 3);	
		assertEquals("A", iter.next());
		assertTrue(iter.hasNext());
		
		assertEquals("B", iter.next());
		assertTrue(iter.hasNext());
		
		assertEquals("C", iter.next());
		assertFalse(iter.hasNext());
		
		Iterator<String>  iterSize=lista.fromUntilIterator(1, 4);	
		assertEquals("A", iterSize.next());
		assertTrue(iterSize.hasNext());

		assertEquals("B", iterSize.next());
		assertTrue(iterSize.hasNext());

		assertEquals("C", iterSize.next());
		assertTrue(iterSize.hasNext());

		assertEquals("D", iterSize.next());
		assertFalse(iterSize.hasNext());
		
		Iterator<String>  iterSizeMayor=lista.fromUntilIterator(1, 5);	
		assertEquals("A", iterSizeMayor.next());
		assertTrue(iterSizeMayor.hasNext());
		
		assertEquals("B", iterSizeMayor.next());
		assertTrue(iterSizeMayor.hasNext());
		
		assertEquals("C", iterSizeMayor.next());
		assertTrue(iterSizeMayor.hasNext());
		assertEquals("D", iterSizeMayor.next());
		assertFalse(iterSizeMayor.hasNext());
		assertEquals("(A B C D )", lista.toString());
		
		Iterator<String>  it=lista.fromUntilIterator(2, 4);	
	    assertTrue(it.hasNext());
	    assertEquals("B", it.next());
	    assertTrue(it.hasNext());
	    assertEquals("C", it.next());
	    assertTrue(it.hasNext());
	    assertEquals("D", it.next());
	    assertFalse(it.hasNext());
	}



}

		
				
