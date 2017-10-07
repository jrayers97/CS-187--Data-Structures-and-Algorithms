package structures;

import java.util.NoSuchElementException;
import java.util.Iterator;

public class myIterator<T> implements Iterator<T> {
	
	protected Node<T> first;
	
	public myIterator(Node<T> first){
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
	
}
