package ule.ed.list;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class LinkedNotOrderedList<T> implements INotOrderedList<T>{

	//	referencia al primer  de la lista
	private Node<T> front;

	// NO SE PUEDEN AÑADIR MÁS ATRIBUTOS A LA LISTA

	private class Node<T> {

		Node(T element) {	
			this.elem = element;
			this.next = null;
		}

		T elem;

		Node<T> next;
	}
	///////
	///// ITERADOR normal //////////

	//@SuppressWarnings("hiding")
	private class LinkedListIterator<T> implements Iterator<T> {
		// declarar atributos del iterador
		
		private Node<T> current;
		
		public LinkedListIterator(Node<T> aux) {
			//TODO
			current = aux;
		}

		@Override
		public boolean hasNext() {
			//TODO
			return (current != null);
		}

		@Override
		public T next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			
			T result = current.elem;
			current = current.next;
			return result; 
		}	
	}
	
	///////
	///// ITERADOR Par //////////

	//@SuppressWarnings("hiding")
	private class LinkedListIteratorPar<T> implements Iterator<T> {
		// declarar atributos del iterador
		
		private Node<T> current;
		
		public LinkedListIteratorPar(Node<T> aux) {
			//TODO

			if(isEmpty()) {
				this.current = null;
			}else {
				this.current = aux.next;
			}
		}

		@Override
		public boolean hasNext() {
			//TODO
			return this.current != null;
		}

		@Override
		public T next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			
			T result;
			
			if(current.next != null) {
				result = current.elem;
				current = current.next.next;
				
			}else {
				result = current.elem;
				current = current.next;
			}
			
			
			return result; 
		}	
	}

	///////
	///// ITERADOR From until //////////

	//@SuppressWarnings("hiding")
	private class LinkedListIteratorFromUntil<T> implements Iterator<T> {
		// declarar atributos del iterador
		
	    private Node<T> current;
	    private int currentIndex;
	    private int from;
	    private int until;

	    public LinkedListIteratorFromUntil(int from, int until, Node<T> current) {
	        this.current = current;
	        this.currentIndex = 1;
	        this.from = from;
	        this.until = until;
	        
	        while (currentIndex < from) {
	            this.current = this.current.next;
	            currentIndex++;
	        }
	    }

	    @Override
	    public boolean hasNext() {
	        return current != null && currentIndex <= until;
	    }

	    @Override
	    public T next() {
	        if (!hasNext()) {
	            throw new NoSuchElementException();
	        }

	        T elem = current.elem;
	        current = current.next;
	        currentIndex++;
	        return elem;
	    }
		
	}
	
	/// TODO :  AÑADIR OTRAS CLASES PARA LOS OTROS ITERADORES
	
		// FIN ITERADORES


	@Override
	public int size() {
		// TODO 
		
	    int size = 0;
	    Node<T> aux = front;
	    while(aux != null){
	    	size++;     
	    	aux = aux.next;
	    }
	    return size;
	}

	@Override
	public boolean isEmpty() {
		// TODO 
		return (this.front == null) ? true : false; 
		
	}

	@Override
	public void addFirst(T elem) {
		// TODO 
		this.checkNull(elem);
		
		Node<T> node = new Node<T>(elem);
		node.next = front;
		front = node;
	}

	@Override
	public void addLast(T elem) {
		// TODO 
		
		this.checkNull(elem);
		
		if(isEmpty()) {
			front = new Node<T>(elem);
		}else {
		    Node<T> aux = front;
		    while(aux.next != null){
		      aux = aux.next;
		    }
		    
		    aux.next = new Node<T>(elem);
		    
		}
	}

	@Override
	public void addPenult(T elem) {
		// TODO 
		
		this.checkNull(elem);
		
		if(this.isEmpty()) {
			front = new Node<T>(elem);
		}
		else if(front.next == null) {
			
			Node<T> node = new Node<T>(elem);
			node.next = front;
			front = node;
			
		}
		else {
			
			Node<T> aux = front;
		    while(aux.next.next != null){
		      aux = aux.next;
		    }
			
		    Node<T> node = new Node<T>(elem);
		    node.next= aux.next;
		    aux.next = node;
		}
	}

	@Override
	public void addPos(T elem, int position) {
		// TODO 
		this.checkNull(elem);
		
		if(position <= 0) {
			throw new IllegalArgumentException("ERROR: La posicion tiene que ser mayor que 0");
		}
		
		if(isEmpty()) {
			front = new Node<T>(elem);
		}
		else if(position > size()) {
			this.addLast(elem);
		}
		else if(position == 1) {
			this.addFirst(elem);
		}
		else if(position == 2) {
			Node<T> aux = front;
			Node<T> node = new Node<T>(elem);
			
			node.next = aux.next;
			aux.next = node;
			 
		}else {
			
			
			int currentPos = 1;
			Node<T> aux = front;
			
			
			while(currentPos < position-1) {
				aux = aux.next;
				currentPos++;
			}
			
			Node<T> node = new Node<T>(elem);
			node.next = aux.next;
			aux.next = node;
		}
	}

	@Override
	public T removeFirst() throws EmptyCollectionException {
		// TODO 
	    this.checkIsEmpty();
	    T elem = front.elem;
	    front = front.next;
	    
		return elem ;
	}

	@Override
	public T removelast() throws EmptyCollectionException {
		// TODO 
		this.checkIsEmpty();		
		
		T auxElem;
		
		if(front.next == null) {
			auxElem = front.elem;
			front = null;
		}else {
			Node<T> aux = front;
			while(aux.next.next != null) {
				aux = aux.next;
			}
			
			auxElem = aux.next.elem;
			aux.next = null;
		}
		

		this.checkElemNull(auxElem);
		
		return auxElem;
	}

	@Override
	public T removePenult() throws EmptyCollectionException {
		// TODO 
		T elem;
		this.checkIsEmpty();
		
		if(size()==1) {
			throw new NoSuchElementException("ERROR: SOlo hay un elemento en la lista");		
		}
		else if (size() == 2){
			elem = front.elem;
			front = front.next;
		}else {
			Node<T> aux = front;
		    while(aux.next.next.next != null){
		      aux = aux.next;
		    }
		    
		    elem = aux.next.elem;
		    aux.next = aux.next.next;
		    this.checkElemNull(elem);
		}

		return elem;
				
	}
	
	@Override
	public T getElemPos(int position) {
		// TODO 
		if(position < 1 ||position>size()) {
			throw new IllegalArgumentException("ERROR: Posicion incorrecta");
		}
		
		T elem;
		int countAux = 0;
		Node<T> aux = front; 
		while(countAux < (position-1)) {
			countAux++;
			aux = aux.next;
		}
		
		elem = aux.elem;
		
		return elem;
	}

	@Override
	public int getPosLast(T elem) {
		// TODO	
		
		this.checkNull(elem);
		Node<T> aux = front;
		int numElem = 0;
		
		while(aux != null) {
			if(aux.elem.equals(elem)) {
				numElem++;
			}
			aux = aux.next;
		}
		
		if(numElem == 0) {
			throw new NoSuchElementException("ERROR: El elemento no está en la lista");
		}

		Node<T> auxNu = front;
		int lasPos = 0;
		int cantElem = 0;
		while(auxNu != null) {
			if(auxNu.elem.equals(elem)) {
				lasPos = cantElem + 1;
			}
			cantElem++;
			auxNu = auxNu.next;
		}
		
		return lasPos;
	 }
	

	@Override
	public int removeAll(T elem) throws EmptyCollectionException {
		// TODO 
		this.checkIsEmpty();
		this.checkNull(elem);
		
		Node<T> aux = front;
		int numElem = 0;
		
		while(aux != null) {
			if(aux.elem.equals(elem)) {
				numElem++;
			}
			aux = aux.next;
		}
		
		if(numElem == 0) {
			throw new NoSuchElementException("ERROR: El elemento no está en la lista");
		}
		
		Node<T> currentAux = front;
		Node<T> prevAux2 = null;
		
		while(currentAux != null) {
			if(currentAux.elem.equals(elem)) {
				if(prevAux2 == null) {
					front = currentAux.next;
				}else {
					prevAux2.next = currentAux.next;
				}
			}else {
				prevAux2 = currentAux;
			}
			currentAux = currentAux.next;
		}
		
		return numElem;

	}

	@Override
	public INotOrderedList<T> reverse() {
		// TODO 
		LinkedNotOrderedList<T> lista = new LinkedNotOrderedList<T>();
		Node<T>aux = front;
		
		if(isEmpty()) {
			return lista;
		}else {
			while(aux != null) {
				lista.addFirst(aux.elem);
				aux = aux.next;
			}
			return lista;
		}
	}

	
	@Override
	public String toString() {
		// TODO
		
		StringBuilder sc = new StringBuilder();
		sc.append("(");
		Node<T>aux = front;
		
		while(aux != null) {
			sc.append(aux.elem.toString() + " ");
			aux = aux.next;
		}
		
		sc.append(")");
		return sc.toString();
	}


		@Override
		public int removeElem(T elem) throws EmptyCollectionException {
			// TODO Auto-generated method stub
			this.checkIsEmpty();
			this.checkNull(elem);
			
			Node<T> aux = front;
			Node<T> prev = null;
			int pos = 1;
			
			while(aux != null && !(aux.elem.equals(elem))) {
				prev = aux;
				aux = aux.next;
				
				pos++;
			}
			
			if(aux == null) {
				throw new NoSuchElementException("ERROR: El elemento no está en la lista.");
			}
			
			this.removeElemPos(pos);

			return pos;
			
		}

		@Override
		public T removeElemPos(int position) throws EmptyCollectionException  {
			// TODO 
			this.checkIsEmpty();
			
			if(position < 1 || position > this.size()) {
				throw new IllegalArgumentException("ERROR: La posicion tiene que estar entre 1 y la size");
			}
			
			T elem;
			if(position == 1) {
				
				if(front.next == null) {
					elem = front.elem;
					front = null;
					return elem;
				}else {
					elem = front.elem;
					front = front.next;
					return elem;
				}
				
			}else {
				
				int countAux = 1;
				Node<T> aux = front;
				Node<T> prev = null;
				while(countAux < position) {
					countAux++;
					prev = aux;
					aux = aux.next;
				}
				elem = aux.elem;
				
				prev.next = aux.next;
				
				return elem;
			}
		}

		@Override
		public int removePosLast(T elem) throws EmptyCollectionException {
			// TODO 
			this.checkIsEmpty();
			this.checkNull(elem);
			int position = this.getPosLast(elem);
			
			this.removeElemPos(position);
			
			return position;
		}

		@Override
		public String FromUntilNotIncluded(int from, int until) {
			// TODO 
			StringBuilder sc = new StringBuilder();
			Node<T> aux = front;
			int position = from;
			
			if(from <= 0 || until <= 0 || until < from) {
				throw new IllegalArgumentException("ERROR");
			}

			else if(from > this.size()) {
				sc.append("()");
			}
			
			
			else if(until > this.size()) {
				int auxCount = 0;
				
				while(auxCount < from) {
					
					aux = aux.next;
					auxCount++;
				}
				sc.append("(");
				while(aux != null && position < until-1 ) {
					sc.append(aux.elem + " ");
					aux = aux.next;
					position++;
				}
				sc.append(")");
				
			}else {
				int auxCount = 0;
				
				while(auxCount < from) {
					
					aux = aux.next;
					auxCount++;
				}
				
				sc.append("(");
				while(position < until-1) {
					sc.append(aux.elem + " ");
					aux = aux.next;
					position++;
				}
				sc.append(")");

			}
			return sc.toString();
		}

		@Override
		public Iterator<T> iterator() {
			// TODO 
			return new LinkedListIterator<T>(front);
		}
		
		@Override
		public Iterator<T> evenPosIterator() {
			// TODO 
			return new LinkedListIteratorPar<>(front);
		}
		
		@Override
		public Iterator<T> fromUntilIterator(int from, int until) {
			// TODO 
			return new LinkedListIteratorFromUntil(from, until, front);
		}
	
		private void checkIsEmpty() throws EmptyCollectionException {
			if(isEmpty()) {
				throw new EmptyCollectionException("ERROR: La lista está vacía");
			}
		}
		
		private void checkNull(T elem) {
			if(elem == null) {
				throw new NullPointerException("ERROR: El elemento no puede ser nulo");
			}
		}
		
		private void checkElemNull(T elem) {
			if(elem == null) {
				throw new NoSuchElementException("ERROR:El elemento no está en la lista");
			}
		}
}

	