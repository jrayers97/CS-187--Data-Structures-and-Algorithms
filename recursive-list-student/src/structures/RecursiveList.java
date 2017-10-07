package structures;

import java.util.Iterator;

public class RecursiveList<T> implements ListInterface<T> {
	
	protected Node<T> first;
	protected Node<T> last;
	protected int size;
	
	public RecursiveList(){
		first = null;
		last = null;
		size = 0;
	}
	
	public int size(){
		return size;
	}
	
	public ListInterface<T> insertFirst(T elem){
		if(elem == null){
			throw new NullPointerException("Attempting to add a null element");
		}
		return insertAt(0, elem);
		
	}
	
	public ListInterface<T> insertLast(T elem){
		if(elem == null){
			throw new NullPointerException("Attempting to add a null element");
		}
		return insertAt(size, elem);
	}
	
	public ListInterface<T> insertAt(int index, T elem){
		if(elem == null){
			throw new NullPointerException("Attempting to add a null element");
		}
		if(index > this.size() || index < 0){
			throw new IndexOutOfBoundsException("Index is larger than the list size");
		}
		Node<T> newNode = new Node<T>(elem);
		if(index == 0){
			newNode.setNext(first);
			first = newNode;
		}
		else{
			insertHelp(index, first, newNode);
		}
		size++;
		return this;
	}
	
	public void insertHelp(int index, Node<T> currNode, Node<T> output){
		if(index != 1){
			insertHelp(index-1, currNode.getNext(), output);
		}
		else{
			Node<T> nextCurrNode = currNode.getNext();
			output.setNext(nextCurrNode);
			currNode.setNext(output);
		}
	}
	
	public T removeFirst(){
		if(isEmpty()){
			throw new IllegalStateException("Attemping to remove from an empty list");
		}
		T elem;
		elem = first.getData();
		first = first.getNext();
		size--;
		return elem;
	}
	
	public T removeLast(){
		if(isEmpty()){
			throw new IllegalStateException("Attemping to remove from an empty list");
		}
		return removeAt(size()-1);
	}
	
	public T removeLastHelper(Node<T> node){
		if(node.getNext() == null){
			T elem = node.getData();
			node = null;
			return elem;
		}
		Node<T> skipNode = node.getNext().getNext();
		if(skipNode == null){
			T elem = node.getNext().getData();
			node.setNext(null);
			return elem;
		}
		return removeLastHelper(node.getNext());
	}
	
	public T removeAt(int i){
		T elem;
		if(i < 0 || i >= size){
			throw new IndexOutOfBoundsException("Index is out of bounds");
		}
		if(i == 0){
			return removeFirst();
		}
		else{
			elem = removalHelper(i, first);
		}
		size--;
		return elem;
	}
	
	public T removalHelper(int index, Node<T> node){
		T elem;
		if(index == 1){
			Node<T> skipNode = node.getNext().getNext();
			if(skipNode == null){
				elem = node.getNext().getData();
				Node<T> newNode = node.getNext();
				newNode.setNext(null);
				return elem;
			}
			else{
				Node<T> newNode2 = node.getNext();
				elem = newNode2.getData();
				node.setNext(skipNode);
				newNode2.setData(null);
				return elem;
			}
		}
		return removalHelper(index-1, node.getNext());
	}
	
	public T getFirst(){
		if(isEmpty() == true){
			throw new IllegalStateException();
		}
		return get(0);
	}
	
	public T getLast(){
		if(isEmpty() == true){
			throw new IllegalStateException();
		}
		return get(this.size() -1);
	}
	
	public T get(int i){
		if(i < 0 || i >= size){
			throw new IndexOutOfBoundsException();
		}
		return getHelper(i, first);
	}
	
	public T getHelper(int index, Node<T> node){
		if(index == 0){
			return node.getData();
		}
		return getHelper(index-1, node.getNext());
	}
	
	public boolean remove(T elem){
		if(elem == null){
			throw new NullPointerException();
		}
		if(indexOf(elem) < 0){
			return false;
		}
		else{
			int index = indexOf(elem);
			removeAt(index);
			return true;
		}
	}
	
	public int indexOf(T elem){
		if(elem == null){
			throw new NullPointerException();
		}
		return indexOfHelper(elem, first, 0);
	}
	
	public int indexOfHelper(T elem, Node<T> node, int index){
		if(node == null){
			return -1;
		}
		if(index >= size()){
			return -1;
		}
		if(node.getData().equals(elem)){
			return index;
		}
		else{
		return indexOfHelper(elem, node.getNext(), index+1);
		}
	}
	
	public boolean isEmpty(){
		if(size == 0){
			return true;
		}
		return false;
	}
	
	public Iterator<T> iterator(){
		return new listIterator<T>(first);
	}
}
