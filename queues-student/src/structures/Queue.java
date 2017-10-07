package structures;

import java.util.NoSuchElementException;

public class Queue<T> implements UnboundedQueueInterface<T> {
	
	protected Node<T> front; //The front node
	protected Node<T> rear;	 //The rear node
	protected int size;		//The queue size

	public Queue() {		
		front = null;
		rear = null;
    }
	
	public Queue(Queue<T> other) {
       Node<T> front = other.front;
       while(front != null){
    	   enqueue(front.getData());
    	   front = front.getNext();
    	   /* while the front node is not null, so when we've not reached the end 
    	    * enqueue the data from the current "other" node and into the "front" node
    	    * now set the front node to be the next one and continue 
    	    */
       }
	}
	
	@Override
	public boolean isEmpty() {
           if(front == null){
        	   return true;
           }
           else{
        	   return false;
           }
	}

	@Override
	public int size() {
       return size;
	}

	@Override
	public void enqueue(T element) {
		size++; //we first want to increase the queue size
       Node<T> newNode = new Node<T>(element);
       if(rear == null){
    	   front = newNode; //if there is no nodes in the queue, we start the queue 
       }
       else{
    	   rear.setNext(newNode); 
    	   /*if there are nodes, we simple make a new one 
    	    * and encode the data into it 
    	    */
       }
       rear = newNode; //now the last node is our new node
	}

	@Override
	public T dequeue() throws NoSuchElementException {
       if(isEmpty() == true){
    	   throw new NoSuchElementException("Cannot dequeue from an empty queue");
       }
       else{
    	   T element; //we make an element to collect the data 
    	   element = front.getData(); //collect the data from the node
    	   front = front.getNext(); //get the next node
    	   if(front == null){ //if the front is now null, this means our rear is null
    		   rear = null;
    	   }
    	   size--; //we reduce the size of the queue
    	   return element; //return the data in the dequeued node
       }
	}

	@Override
	public T peek() throws NoSuchElementException {
         if(isEmpty() == true){
        	 throw new NoSuchElementException("No elements to view");
         }
         else{
        	 T element;
        	 element = front.getData();
        	 return element;
         }
	}

	@Override
	public UnboundedQueueInterface<T> reversed() {
		/*I chose to use a stack for this method since 
		 * the LIFO style is perfectly suited for this task
          */
          StackInterface<T> stack = new LinkedStack<T>();
          Queue<T> output;
          //I created a new queue with exactly the same elements as the original
          output = new Queue<T>(this); 
          //I take all the elements in the queue and push them onto the stack
          for(int i = 0; i < size; i++){
        	 stack.push(output.dequeue());
          }
          while(stack.isEmpty() == false){ //while there are still elements left
        	  T element = stack.peek(); //get the data from the top of the stack
        	  stack.pop(); //remove the top element
        	  output.enqueue(element); //requeue it to output
          }
          return output; //return the reversed queue
	}
}
