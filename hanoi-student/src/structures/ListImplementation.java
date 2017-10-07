package structures;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * An {@code ListImplementation} is a Linked List that wraps {@link Node}s and
 * provides useful operations.
 * 
 * @author jcollard
 * 
 */
public class ListImplementation<T> implements ListInterface<T> {
	
	protected int size;
	protected Node<T> firstNode = null;
	protected Node<T> lastNode = null;
	
	public ListImplementation() {
	}

	/**
	 * Returns the number of nodes in this list.
	 */
	@Override
	public int size() {
        return size;
	}

	@Override
	public boolean isEmpty() {
		if(size != 0){
			return false;
		}
		else{
			return true;
		}
	}

	/**
	 * Appends {@code elem} to the end of this {@code ListImplementation} and
	 * returns itself for convenience.
	 */
	@Override
	public ListImplementation<T> append(T elem) {
			ListImplementation<T> newT = this;
			Node<T> newNode = new Node<T>(elem, null);
			if(elem == null){
				throw new NullPointerException();
			}
			else if(isEmpty() == false){
				lastNode.setNext(newNode); 
			}
			else{
				firstNode = newNode;
			}
			lastNode = newNode;
			size++;
			return this;
	}

	/**
	 * Gets the {@code n}th element from this list.
	 */
	@Override
	public T get(int n){
          myIterator<T> newIterator = new myIterator<T>(firstNode);
			Node<T> newNode = firstNode;
          int i = 0;
          if(n < 0 || n >= size){
        	  throw new NoSuchElementException("The int n cannot be negative or exceed the list size");
          }
          while((newIterator.hasNext() == true) && (i < n)){
        	  newNode = newNode.getNext();
        	  i++;
          }
          T output = newNode.getData();
          return output;
	}

	/**
	 * Returns an iterator over this list. The iterator does not support the
	 * {@code remove()} method.
	 */
	@Override
	public Iterator<T> iterator() {
        return new myIterator<T>(firstNode);
	}
}

