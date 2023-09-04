package ule.ed.list;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.*;


public class ArrayNotOrderedListTest {
	private ArrayNotOrderedList<String> lista;
	
	@Before
	public void setUp() {
		 lista= new ArrayNotOrderedList<String>();
	}
	
	@Test
	public void ArrayVaciaTest() {
		assertEquals(0,lista.size());
	}
	
	// test addFirst comprueba que dispara excepción
	@Test(expected=NullPointerException.class)
	public void ArrayAddFirstElementoNuloTest() {
			lista.addFirst(null);
	}
	
	@Test
	public void ArrayAddFirstTest() {
		lista.addFirst("2");
		Assert.assertFalse(lista.isEmpty());
		Assert.assertEquals("(2 )", lista.toString());
		lista.addFirst("3");
		Assert.assertEquals("(3 2 )", lista.toString());
		lista.addFirst("7");
		Assert.assertEquals("(7 3 2 )", lista.toString());
	}
	
	@Test
	public void ArrayAddFirstExpandCapacityTest() {
		lista=new ArrayNotOrderedList<String>(3);
		
		lista.addFirst("2");
		Assert.assertFalse(lista.isEmpty());
		Assert.assertEquals("(2 )", lista.toString());
		lista.addFirst("3");
		Assert.assertEquals("(3 2 )", lista.toString());
		lista.addFirst("7");
		Assert.assertEquals("(7 3 2 )", lista.toString());
		lista.addFirst("10");
		Assert.assertEquals("(10 7 3 2 )", lista.toString());		
	}


	//test de iteradores
	@Test
	public void ArrayIteratorTest() {
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
	
	@Test
	public void ArrayEvenIteratorNElemesParTest() {
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
	}
	
	
	@Test(expected = NoSuchElementException.class)
	public void ArrayEvenIteratorNElemesParTestException() {
		Iterator<String>  iter=lista.evenPosIterator();
		iter.next();
	}
	
	
	
	
	// TEST ITERADORES EN LISTA VACÍA
	@Test(expected=NoSuchElementException.class)
	public void ArrayNextListaVaciaTest() {
			lista.iterator().next();	}
	
	
	//TEST reverse
	@Test
	public void ArraytestReverse() {
	
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
	public void testIsEmpty() {
		lista = new ArrayNotOrderedList<>(2);
		assertTrue(lista.isEmpty());
		
	}
	
	@Test
	public void testRemoveFirst() throws EmptyCollectionException {
		ArrayNotOrderedList<String> lista = new ArrayNotOrderedList<>(4);
		lista.addFirst("C");
		lista.addFirst("B");
		lista.addFirst("A");
		
		assertEquals("(A B C )", lista.toString());
		lista.removeFirst();
		assertEquals("(B C )", lista.toString());
		
	}
	
	@Test
	public void testRemoveLast() throws EmptyCollectionException {
		ArrayNotOrderedList<String> lista = new ArrayNotOrderedList<>(4);
		lista.addFirst("C");
		lista.addFirst("B");
		lista.addFirst("A");
		
		assertEquals("(A B C )", lista.toString());
		lista.removelast();
		assertEquals("(A B )", lista.toString());
	}
	
	
	@Test
	public void testRemovePenult() throws EmptyCollectionException {
		ArrayNotOrderedList<String> lista = new ArrayNotOrderedList<>(4);
		lista.addFirst("C");
		lista.addFirst("B");
		lista.addFirst("A");
		
		assertEquals("(A B C )", lista.toString());
		lista.removePenult();
		assertEquals("(A C )", lista.toString());
	}
	
	@Test(expected = NoSuchElementException.class)
	public void testRemovePenult1ElementException() throws EmptyCollectionException {
		ArrayNotOrderedList<String> lista = new ArrayNotOrderedList<>(4);
		lista.addFirst("C");
		lista.removePenult();
	}
	
	@Test(expected = EmptyCollectionException.class)
	public void testRemovePenultListaVaciaException() throws EmptyCollectionException {
		ArrayNotOrderedList<String> lista = new ArrayNotOrderedList<>(4);
		lista.removePenult();
	}
	
	@Test
	public void testRemoveElement() throws EmptyCollectionException {
		ArrayNotOrderedList<String> lista = new ArrayNotOrderedList<>(4);
		lista.addFirst("C");
		lista.addFirst("B");
		lista.addFirst("A");
		assertEquals("(A B C )", lista.toString());
		lista.removeElem("A");
		assertEquals("(B C )", lista.toString());
		
		lista.addFirst("B");
		lista.addFirst("A");
		assertEquals("(A B B C )", lista.toString());
		lista.removeElem("B");
		assertEquals("(A B C )", lista.toString());
	}
	
	@Test(expected = NoSuchElementException.class)
	public void testRemoveElementNoSUchElementException() throws EmptyCollectionException {
		ArrayNotOrderedList<String> lista = new ArrayNotOrderedList<>(4);
		lista.addFirst("C");
		lista.addFirst("B");
		lista.addFirst("A");
		lista.removeElem("D");
	}
	
	@Test
	public void testGetElementPos() {
		ArrayNotOrderedList<String> lista = new ArrayNotOrderedList<>(4);
		lista.addFirst("C");
		lista.addFirst("B");
		lista.addFirst("A");
		assertEquals("A", lista.getElemPos(1));
		assertEquals("B", lista.getElemPos(2));
		assertEquals("C", lista.getElemPos(3));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testGetElementPosException() {
		ArrayNotOrderedList<String> lista = new ArrayNotOrderedList<>(4);
		lista.addFirst("C");
		lista.addFirst("B");
		lista.addFirst("A");
		lista.getElemPos(0);
		lista.getElemPos(3);
		lista.getElemPos(5);
		
	}
	
	
	@Test
	public void testRemoveElemesPos() throws EmptyCollectionException {
		lista.addFirst("C");
		lista.addFirst("B");
		lista.addFirst("A");
		assertEquals("(A B C )", lista.toString());
		
		lista.removeElemPos(1);
		assertEquals("(B C )", lista.toString());
		lista.removeElemPos(1);
		assertEquals("(C )", lista.toString());
		lista.removeElemPos(1);
		assertEquals("()", lista.toString());
		
		lista.addFirst("F");
		lista.addFirst("E");
		lista.addFirst("D");
		lista.addFirst("C");
		lista.addFirst("B");
		lista.addFirst("A");
		
		lista.removeElemPos(6);
		assertEquals("(A B C D E )", lista.toString());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testRemoveElemesPosException() throws EmptyCollectionException {
		lista.addFirst("C");
		lista.addFirst("B");
		lista.addFirst("A");
		assertEquals("(A B C )", lista.toString());
		lista.removeElemPos(0);
	}
	
	@Test
	public void testGetPosLast() {
		ArrayNotOrderedList<String> list = new ArrayNotOrderedList<>(4);
		list.addFirst("D");
		list.addFirst("B");
		list.addFirst("B");
		list.addFirst("A");
		
		assertEquals("(A B B D )", list.toString());
		assertEquals(3, list.getPosLast("B"));
	}
	
	@Test(expected = NoSuchElementException.class)
	public void testGetPosLastException() {
		ArrayNotOrderedList<String> list = new ArrayNotOrderedList<>(4);
		list.addFirst("D");
		list.addFirst("B");
		list.addFirst("B");
		list.addFirst("A");
		
		assertEquals("(A B B D )", list.toString());
		list.getPosLast("Z");
	}
	
	
	
	@Test
	public void testRemovePosLast() throws EmptyCollectionException {
		lista.addFirst("B");
		lista.addFirst("C");
		lista.addFirst("B");
		lista.addFirst("A");
		assertEquals("(A B C B )", lista.toString());
		lista.removePosLast("B");
		assertEquals("(A B C )", lista.toString());
	}
	
	
	@Test
	public void testAddlast() {
		lista.addFirst("B");
		lista.addFirst("C");
		lista.addFirst("B");
		lista.addFirst("A");
		assertEquals("(A B C B )", lista.toString());
		lista.addLast("Z");
		assertEquals("(A B C B Z )", lista.toString());
	}
	
	@Test
	public void testAddPenul() {
		lista.addFirst("B");
		lista.addFirst("C");
		lista.addFirst("B");
		lista.addFirst("A");
		assertEquals("(A B C B )", lista.toString());
		lista.addPenult("Z");
		assertEquals("(A B C Z B )", lista.toString());
	}
	
	
	@Test
	public void testAddPos() {
		lista.addFirst("D");
		lista.addFirst("C");
		lista.addFirst("A");
		assertEquals("(A C D )", lista.toString());
		
		lista.addPos("B", 2);
		assertEquals("(A B C D )", lista.toString());
		
		lista.addPos("E", 5);
		assertEquals("(A B C D E )", lista.toString());
	}
	
	
	@Test
	public void testRemoveAll() throws EmptyCollectionException {
		ArrayNotOrderedList<String> lista = new ArrayNotOrderedList<>(4);
		lista.addFirst("D");
		lista.addFirst("C");
		lista.addFirst("A");
		lista.addFirst("D");
		assertEquals("(D A C D )", lista.toString());
		lista.removeAll("D");
		assertEquals("(A C )", lista.toString());
		
		ArrayNotOrderedList<String> lista2 = new ArrayNotOrderedList<>(4);
		lista2.addFirst("S");
		lista2.addFirst("A");
		lista2.addFirst("A");
		lista2.addFirst("A");
		assertEquals("(A A A S )", lista2.toString());
		lista2.removeAll("A");
		assertEquals("(S )", lista2.toString());
	}
	
	@Test(expected = NoSuchElementException.class)
	public void testRemoveAllException() throws EmptyCollectionException {
		ArrayNotOrderedList<String> lista = new ArrayNotOrderedList<>(4);
		lista.addFirst("D");
		lista.addFirst("C");
		lista.addFirst("B");
		lista.addFirst("A");
		assertEquals("(A B C D )", lista.toString());
		lista.removeAll("Z");
	}

	
	@Test
	public void testFromUntilNotIncluded() {
		ArrayNotOrderedList<String> lista = new ArrayNotOrderedList<>(4);
		lista.addFirst("H");
		lista.addFirst("G");
		lista.addFirst("F");
		lista.addFirst("E");
		lista.addFirst("D");
		lista.addFirst("C");
		lista.addFirst("B");
		lista.addFirst("A");
		assertEquals("(C D )", lista.FromUntilNotIncluded(2, 5));
		assertEquals("(B C D E F G )", lista.FromUntilNotIncluded(1, 8));
		assertEquals("(B C D E F G H )", lista.FromUntilNotIncluded(1, 15));
		assertEquals("()", lista.FromUntilNotIncluded(15, 15));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testFromUntilNotIncludedException() {
		ArrayNotOrderedList<String> lista = new ArrayNotOrderedList<>(4);
		lista.addFirst("E");
		lista.addFirst("D");
		lista.addFirst("C");
		lista.addFirst("B");
		lista.addFirst("A");
		lista.FromUntilNotIncluded(-2, 4);
		lista.FromUntilNotIncluded(0, 4);
		lista.FromUntilNotIncluded(2, 1);
		lista.FromUntilNotIncluded(2, -5);
		lista.FromUntilNotIncluded(-2, -5);
		lista.FromUntilNotIncluded(-2, 8);
	}
	
	@Test
	public void testReverse() {
		ArrayNotOrderedList<String> lista = new ArrayNotOrderedList<>(4);
		lista.addFirst("E");
		lista.addFirst("D");
		lista.addFirst("C");
		lista.addFirst("B");
		lista.addFirst("A");
		assertEquals("(A B C D E )", lista.toString());
		assertEquals("(E D C B A )", lista.reverse().toString());
		ArrayNotOrderedList<String> listaVacia = new ArrayNotOrderedList<>(4);
		assertEquals("()", listaVacia.reverse().toString());
	}
	
	
	@Test
	public void testIteratorFromUntil() {
		ArrayNotOrderedList<String> lista = new ArrayNotOrderedList<>(4);
		lista.addFirst("D");
		lista.addFirst("C");
		lista.addFirst("B");
		lista.addFirst("A");
		assertEquals("(A B C D )", lista.toString());
		
		Iterator<String>  iterFallo = lista.fromUntilIterator(0, 0);
		Iterator<String>  iterFallo2 = lista.fromUntilIterator(-1, -2);
		Iterator<String>  iterFallo3 = lista.fromUntilIterator(-1, 0);
		Iterator<String>  iterFallo4= lista.fromUntilIterator(0, -2);
		assertFalse(iterFallo.hasNext());
		assertFalse(iterFallo2.hasNext());
		assertFalse(iterFallo3.hasNext());
		assertFalse(iterFallo4.hasNext());
		
		Iterator<String>  iterFallo5= lista.fromUntilIterator(0, 5);
		assertTrue(iterFallo5.hasNext());
		assertEquals("A", iterFallo5.next());
		
		assertTrue(iterFallo5.hasNext());
		assertEquals("B", iterFallo5.next());
		
		assertTrue(iterFallo5.hasNext());
		assertEquals("C", iterFallo5.next());
		
		assertTrue(iterFallo5.hasNext());
		assertEquals("D", iterFallo5.next());
	
		assertFalse(iterFallo5.hasNext());
		
		
		
		Iterator<String> iterBien = lista.fromUntilIterator(1, 4);
		assertTrue(iterBien.hasNext());
		assertEquals("A", iterBien.next());
		
		assertTrue(iterBien.hasNext());
		assertEquals("B", iterBien.next());
		
		assertTrue(iterBien.hasNext());
		assertEquals("C", iterBien.next());
		
		assertTrue(iterBien.hasNext());
		assertEquals("D", iterBien.next());
	
		assertFalse(iterBien.hasNext());
		
		ArrayNotOrderedList<String> lista1 = new ArrayNotOrderedList<>(4);
		lista1.addFirst("D");
		lista1.addFirst("C");
		lista1.addFirst("B");
		lista1.addFirst("A");
		assertEquals("(A B C D )", lista1.toString());
		
		Iterator<String> iterCaso = lista1.fromUntilIterator(1, 5);

		assertTrue(iterCaso.hasNext());
		assertEquals("A", iterCaso.next());
		
		assertTrue(iterCaso.hasNext());
		assertEquals("B", iterCaso.next());
		
		assertTrue(iterCaso.hasNext());
		assertEquals("C", iterCaso.next());
		
		assertTrue(iterCaso.hasNext());
		assertEquals("D", iterCaso.next());
	
		assertFalse(iterCaso.hasNext());
		
		Iterator<String> iterCaso2 = lista1.fromUntilIterator(5, 4);
		assertTrue(iterCaso2.hasNext());
		assertEquals("A", iterCaso2.next());
		
		assertTrue(iterCaso2.hasNext());
		assertEquals("B", iterCaso2.next());
		
		assertTrue(iterCaso2.hasNext());
		assertEquals("C", iterCaso2.next());
		
		assertTrue(iterCaso2.hasNext());
		assertEquals("D", iterCaso2.next());
	
		assertFalse(iterCaso2.hasNext());
		
	}
	
	@Test(expected = NoSuchElementException.class)
	public void testArrayNotOrderedListIteratorFromUntilException() {
		ArrayNotOrderedList<String> lista = new ArrayNotOrderedList<>(4);
		Iterator<String> iter = lista.fromUntilIterator(1, 3);
		iter.next();
	}
	
	
	@Test
	public void ArrayEvenIteratorNElemesParTest2() {
		lista.addFirst("2");
		Assert.assertFalse(lista.isEmpty());
		Assert.assertEquals("(2 )", lista.toString());
		lista.addFirst("3");
		Assert.assertEquals("(3 2 )", lista.toString());
		lista.addFirst("7");
		Assert.assertEquals("(7 3 2 )", lista.toString());
		lista.addFirst("8");
		lista.addFirst("7");
		lista.addFirst("6");
		lista.addFirst("5");
		lista.addFirst("4");
		Assert.assertEquals("(4 5 6 7 8 7 3 2 )", lista.toString());

		Iterator<String>  iter=lista.evenPosIterator();
		assertTrue(iter.hasNext());
		assertEquals("5", iter.next());
		assertTrue(iter.hasNext());
		assertEquals("7", iter.next());
		assertTrue(iter.hasNext());
		assertEquals("7", iter.next());
		assertTrue(iter.hasNext());
		assertEquals("2", iter.next());
		assertFalse(iter.hasNext());
	}
	
	
	
	
	
}
	
				
				

