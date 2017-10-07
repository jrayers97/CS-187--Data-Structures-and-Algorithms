package structures;

import java.util.NoSuchElementException;
import java.util.Iterator;

public class listIterator<T> implements Iterator<T> {

	protected Node<T> first;
	
	public listIterator(Node<T> first){
		this.first = first;
	}
	
	public boolean hasNext(){
		if(first == null){
			return false;
		}
		else{
			return true;
		}
	}
	
	public T next(){
		//T newT = null;
		if(hasNext() == true){
			T newT = first.getData();
			first = first.getNext();
			return newT;
		}
		else{
			throw new NoSuchElementException();
		}
	}
	
	public void remove() {
		throw new UnsupportedOperationException();
	}
}
