package ule.ed.list;

import java.util.Iterator;
import java.util.NoSuchElementException;


public class ArrayNotOrderedList<T> implements INotOrderedList<T> {
	static final int DEFAULT_CAPACITY=10;

    private T[] data;
	private int count;
	
	// NO SE PUEDEN AÑADIR MÁS ATRIBUTOS A LA LISTA IMPLEMENTADA CON ARRAYS

	private class ArrayNotOrderedListIterator<T> implements Iterator<T> {
		private int current=0;

		@Override
		public boolean hasNext() {
		//TODO
			if(current < count) {
				return true;
			}
			return false;
		}

		@SuppressWarnings("unchecked")
		@Override
		public T next() {
			 if (!hasNext()) {
				 throw new NoSuchElementException("No hay elemento siguiente");
			 }
			 current ++;
			 return (T) data[current - 1];
		}
}

	
	private class ArrayNotOrderedListIteratorPar<T> implements Iterator<T> {
		private int current=1;

		@Override
		public boolean hasNext() {
			if(current < count) {
				return true;
			}
			return false;
		//TODO
			
		}

		@SuppressWarnings("unchecked")
		@Override
		public T next() {
			 if (!hasNext()) {
				 throw new NoSuchElementException("No hay elemento siguiente");
			 }
			 current ++;
			 current ++;
			 return (T) data[current-2];
			
		}
	
	}
	
	
	
	private class ArrayNotOrderedListIteratorFromUntil<T> implements Iterator<T> {
		private int current=0;
		private int until;

		public ArrayNotOrderedListIteratorFromUntil(int from , int until){
			if(from <= 0 && until <= 0) {
				this.current = 0;
				this.until = until;
			} else if(from <= 0 && until >= 0) {
				this.current = 0;
				this.until = until-1;
			}
			else if(until > size()) {
				this.current = from - 1;
				this.until = count;
			}
			else if(from > size()) {
				 this.current = 0;
				 this.until = size();
				 
			 }
			else {
				this.current = (from-1);
				this.until = until;
			}
			
		}
		
		
		@Override
		public boolean hasNext() {
		//TODO
			if(current < until) {
				return true;
			}
			return false;
		}

		@SuppressWarnings("unchecked")
		@Override
		public T next() {
			 if (!hasNext()) {
				 throw new NoSuchElementException("No hay elemento siguiente");
			 }
			 current++;
			 return (T) data[current - 1];
		}
}
	
	/// TODO :  AÑADIR OTRAS CLASES PARA LOS OTROS ITERADORES
	
	// FIN ITERADORES
	
	
	@SuppressWarnings("unchecked")
	public  ArrayNotOrderedList() {
	   // TODO: inicializar los atributos (crear el array con capacidad por defecto)
		this.data = (T[]) (new Object[DEFAULT_CAPACITY]);
	}

	@SuppressWarnings("unchecked")
	public  ArrayNotOrderedList(int capacity) {
	  // TODO
		this.data = (T[]) (new Object[capacity]);
	}
	
	@Override
	public int size(){
		// TODO 
		return this.count;
	}

	@Override
	public boolean isEmpty() {
		// TODO 
		if(this.count == 0) {
			return true;
		}
		return false;
	}
	
	@Override
	public void addFirst(T elem) {
		// TODO 
		
		this.checkNull(elem);

		this.expandCapacity();
		
		for(int i = count; i > 0; i--) {
			this.data[i] = this.data[i - 1];
		}
	
		this.data[0] = elem;
		this.count++;
	}


	@Override
	public void addLast(T elem) {
		// TODO 
		
		this.checkNull(elem); 
		
		this.expandCapacity();
		
		this.data[count]= elem;
		this.count++;
	}

	@Override
	public void addPenult(T elem) {
		// TODO	
		this.checkNull(elem);		

		this.expandCapacity();
		
		if(isEmpty()) {
			addFirst(elem);
		}else if(count == 1) {
			data[count]= data[count -1];
			data[count -1]= elem;
			count++;
		}else {
			data[count] = data[count-1];
			data[count -1] = elem;
			count++;
		}

	}

	@Override
	public void addPos(T elem, int position) {
		// TODO 
		this.checkNull(elem);
		this.expandCapacity();
		
		if(position <= 0) {
			throw new IllegalArgumentException("ERROR: la posicion tne que ser mayor que 0");
		}
		
		if(position > this.size()) {
			this.addLast(elem);
			return;
		}
		
		for (int i = count; i > (position - 1); i--) {
			data[i] = data[i-1];
		}
		
		data[position-1] = elem;
		count++;
	}

	@Override
	public T removeFirst() throws EmptyCollectionException {
		// TODO 
		this.checkEmpty();
		T aux = data[0];
		for (int i = 0; i < count; i++) {
			data[i]=data[i+1];
		}
		count--;
		return aux;
		
	}

	@Override
	public T removelast() throws EmptyCollectionException {
		// TODO 
		this.checkEmpty();
		
		T aux = data[count - 1];
		data[count - 1] = null;
		count--;
		return aux;
	}

	@Override
	public T removePenult() throws EmptyCollectionException {
		// TODO 
		
		this.checkEmpty();
		
		if(count <= 1) {
			throw new NoSuchElementException("ERROR: La Lista solo tiene un elemento");
		}	
		
		T aux = data[count - 2];
		data[count - 2] = data[count - 1];
		data[count - 1] = null;
		count--;
		return aux;
	}

	@Override
	public int removeElem(T elem) throws EmptyCollectionException {
		// TODO 
		this.checkEmpty();
		this.checkNull(elem);
		
		int elemAux = 0;
		for(int i = 0; i < count; i++ ) {
			if(data[i].equals(elem)) {
				elemAux++;
			}
		}
		
		if(elemAux == 0) {
			throw new NoSuchElementException("ERROR: El elemento no está en la lista");
		}
		
		int aux = 0;
		for(int i = 0; i<count; i++) {
			if(data[i].equals(elem)) {
				aux = i;
				data[i] = null;
				break;
			}
		}
		
		for(int i = aux; i < count-1; i++) {
			data[i] = data[i+1];
		}
		
		data[count-1]=null;
		count--;
		return aux+1;	
	}
	
	@Override
	public T getElemPos(int position) {
		// TODO 
		if(position < 1 || position > this.size()) {
			throw new IllegalArgumentException("ERROR: La posicion tiene que estar entre 1 y la size");
		}
		
		return data[position - 1];
	}

	@Override
	public int getPosLast(T elem) {
		// TODO 
		this.checkNull(elem);
		
		int numElem = 0;
		
		for(int i = 0; i < count; i++) {
			if(data[i].equals(elem)) {
				numElem++;
			}
		}
		
		if(numElem == 0) {
			throw new NoSuchElementException("El elemento no se encuentra en la lista");
		}
		
		int lasPos = 0;
		for(int i = 0; i < count; i++) {
			if(data[i].equals(elem)) {
				
				lasPos = i;	
			}
		}
		
		return lasPos+1;
	}

	@Override
	public int removeAll(T elem) throws EmptyCollectionException {
		// TODO 
		this.checkNull(elem);
		this.checkEmpty();
		int numElem = 0;
		
		for(int i = 0; i < count; i++) {
			if(data[i].equals(elem)) {
				numElem++;
			}
		}
		
		if(numElem == 0) {
			throw new NoSuchElementException("El elemento no se encuentra en la lista");
		}
	    
		T[] dataAux = (T[]) (new Object[data.length]);
		
		int index = 0;
		int j = 0;
		while(index < count) {
			if(data[index]!=elem) {
				dataAux[j] = data[index];
				j++;
			}
			index++;
		}
		
	
		data = dataAux;
		
		count -= numElem;
		//data = dataAux; 
		return numElem;
		
	}

	@Override
	public INotOrderedList<T> reverse() {
		// TODO 
		ArrayNotOrderedList<T> lista = new ArrayNotOrderedList<T>();
		if(count == 0) {
			return lista;
		}else {
			for(int i = 0; i < count; i++) {
				lista.addFirst(data[i]);
			}
			
			return lista;
			
		}
	}

	@Override
	public String toString() {
		// TODO 
		StringBuilder sc = new StringBuilder();
		sc.append("(");
		for(int i = 0; i < count; i++) {
			sc.append(data[i] + " ");
		}
		sc.append(")");
		return sc.toString();
	}
   
	
	@Override
	public T removeElemPos(int position) throws EmptyCollectionException {
		// TODO
		
		this.checkEmpty();
		
		if(position < 1 || position > this.size()) {
			throw new IllegalArgumentException("ERROR: La posicion tiene que estar entre 1 y la size");
		}
		
		T aux = this.getElemPos(position);
		
		for (int i = position - 1; i < count; i++) {
			data[i] = data[i+1];
		}
		
		count--;
		return aux;
	}

	@Override
	public int removePosLast(T elem) throws EmptyCollectionException {
		// TODO 
		this.checkEmpty();
		this.checkNull(elem);
		int pos = this.getPosLast(elem);
		this.removeElemPos(this.getPosLast(elem));
		
		return pos;
	}

	@Override
	public String FromUntilNotIncluded(int from, int until) {
		// TODO Auto-generated method stub
		//T[] dataAux = (T[]) (new Object[data.length]);
		StringBuilder sc = new StringBuilder();
		
		if(from <= 0 || until <= 0 || until < from) {
			throw new IllegalArgumentException("ERROR");
		}

		else if(from > this.size()) {
			sc.append("()");
		}
		
		else if(until > this.size()) {
			sc.append("(");
			for(int i = from; i < count; i++) {
				sc.append(data[i]+ " ");	
			}
			sc.append(")");
			
		}else {
			sc.append("(");
			for(int i = from; i < until-1; i++) {
				sc.append(data[i] + " ");
			}
			sc.append(")");

		}
		return sc.toString();
	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return new ArrayNotOrderedListIterator<T>();
	}

	
	@Override
	public Iterator<T> fromUntilIterator(int from, int until) {
		// TODO Auto-generated method stub
		
		return new ArrayNotOrderedListIteratorFromUntil<>(from , until);
	}

	@Override
	public Iterator<T> evenPosIterator() {
		// TODO Auto-generated method stub
		return new ArrayNotOrderedListIteratorPar<>();
	}

	private void expandCapacity() {
		if(size() == this.data.length) {
			T[] larger = (T[]) (new Object[data.length * 2]);
	
			for (int i=0; i < data.length; i++) {
				 larger[i] = data[i];
			}
			data = larger; 
		}
	}
	
	private void checkNull(T elem) {
		if(elem == null) {
			throw new NullPointerException("ERROR: El elemento no puede ser nulo");
		}
	}
	
	private void checkEmpty() throws EmptyCollectionException {
		if(this.isEmpty()) {
			throw new EmptyCollectionException("ERROR:No se ha podido borrar el elemento. La lista está vacía.");
		}
	}
	
	
}
