package structures;

public class DoublyCircularLinkedListImplementation<T extends Comparable<T>> implements
		DoublyCicularLinkedList<T> {
	
	protected DLLNode<T> next;
	protected DLLNode<T> front;
	protected DLLNode<T> previous;
	protected DLLNode<T> current;
	protected DLLNode<T> location;
	protected int size;
	
	public DoublyCircularLinkedListImplementation() {
			front = null;
            next = null;
            previous = null;
            current = null;
            location = front;
            size = 0;
	}
	
	@Override
	public int size() {
            return size;
	}

	@Override
	public void add(T element) {
		 DLLNode<T> node = new DLLNode<T>(element);
         if(front == null){
        	front = node;
        	front.setForward(front);
        	front.setBack(front);
        	current = front;
         }
         node.setBack(current.getBack());
         node.setForward(current);
         current.getBack().setForward(node);
         current.setBack(node);
         size++;
	}

	@Override
	public boolean remove(T element) {
			DLLNode<T> temp = front;
			int counter = 0;
			if(size == 0){
				return false;
			}
			while(counter != size){
				if(temp.getInfo().equals(element)){
					if(size == 1){
						front = null;
					}
					else{
						temp.getForward().setBack(temp.getBack());
						temp.getBack().setForward(temp.getForward());
						if(temp == front){
							front = front.getBack();
					}
						size--;
						return true;
				}
			}
			temp = temp.getForward();
			counter++;
			}
			return true;
	}

	
	@Override
	public boolean contains(T element) {
		   int counter = 0;
           if(size <= 0){
        	   return false;
           }
           else if(size == 1){
        	   if(front.equals(element)){
        		   return true;
        	   }
        	   else{
        		   return false;
        	   }
           }
           else{
        	   while(counter != size){
        		   if(current.equals(element)){
        			   return true;
        		   }
        		   else{
        			   counter++;
        			   current = current.getForward();
        		   }
        	   }
           }
           return true;
	}

	@Override
	public T get(T element) {
           if(contains(element)){
        	   return element;
           }
           else{
        	   return null;
           }
	}

	@Override
	public void reset() {
            location = current;
	}

	@Override
	public T getNext() {
         T output = current.getInfo();
         current = current.getForward();
         return output;
	}

	@Override
	public T getPrevious() {
		current = current.getBack();
         T output = current.getInfo();
         return output;
	}

}
