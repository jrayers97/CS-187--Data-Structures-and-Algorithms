package sorting;

import structures.Queue;

/**
 * A class containing methods to sort queues and merge sorted queues.
 * 
 * "Sorted" means in ascending order: the front of the queue is the smallest
 * element, and the rear of the queue is the largest.
 * 
 * e1 is less than or equal to e2 if and only if (e1.compareTo(e2) <= 0)
 *
 * You may not use loops (for, while, do, etc.) in this class. You must
 * instead use recursion.
 */
public class MergeSorter<T extends Comparable<T>> {
	/**
	 * Returns a new queue containing the elements from the input queue
	 * in sorted order.
	 * 
	 * Do not modify the input queue! Work on a copy of the input.
	 * 
	 * Implement this method recursively:
	 * 
	 *   In the base case, return the sorted queue.
	 *
	 *   Otherwise:
	 * 
	 *     First, divide the input queue into two smaller output queues.
	 * 
	 *     Then, recursively mergeSort each of these smaller queues. 
	 * 
	 *     Finally, return the result of merging these two queues.
	 * 
	 * @param queue an input queue
	 * @return a sorted copy of the input queue
	 */
	public Queue<T> mergeSort(Queue<T> queue) {
		//First I make a copy queue with the info from the queue object
        	Queue<T> copy = new Queue<T>(queue);
        	//then I make two smaller queues
        	Queue<T> smallQ1 = new Queue<T>();
        	Queue<T> smallQ2 = new Queue<T>();
        	//if the copy queue has 1 or no elements I simply return the queue
        	if(copy.size() == 0 || copy.size() == 1){
        		return copy;
        	}
        	/* Otherwise I recursively call divide onto the three queues
        	 * then I individually recursively call mergeSort onto my small queues
        	 * finally I recursively call merge on the queues and return them 
        	 */
        	divide(copy, smallQ1, smallQ2);
        	smallQ1 = mergeSort(smallQ1);
       		smallQ2 = mergeSort(smallQ2);
            return merge(smallQ1, smallQ2);
	}

	/**
	 * Places elements from the input queue into the output queues, roughly
	 * half and half.
	 * 
	 * Implement this method recursively:
	 * 
	 *   In the base case, there's nothing left to do.
	 *   
	 *   Otherwise:
	 *   
	 *     Make progress on moving elements from the input to the output.
	 *     
	 *     Then make a recursive call to divide.
	 *   
	 * @param input a queue
	 * @param output1 a queue into which about half of the elements in input should go
	 * @param output2 a queue into which the other half of the elements in input should go
	 */
	void divide(Queue<T> input, Queue<T> output1, Queue<T> output2) {
		//if the input queue is empty I'm done
        if(input.isEmpty()){
        	return;
        }
        //next I enqueue one element from the input onto output1, and remove it from input
        output1.enqueue(input.dequeue());
        /* Before moving on I check to see if this queue 
         * had only a single element, and if so, I'm done 
         */
        if(input.isEmpty()){
        	return;
        }
        /* After these checks, I enqueue the dequeued element from input
         * and remove it from input
         * then I repeat the process until completion
         */
        output2.enqueue(input.dequeue());
        divide(input, output1, output2);
	}
	
	/**
	 * Merges sorted input queues into an output queue in sorted order,
	 * and returns that queue. 
	 * 
	 * Use mergeHelper to accomplish this goal.
	 *  
	 * @param input1 a sorted queue
	 * @param input2 a sorted queue
	 * @return a sorted queue consisting of all elements from input1 and input2
	 */
	Queue<T> merge(Queue<T> input1, Queue<T> input2) {
		//A simple method that calls itself and returns a merged queue
        Queue<T> outputQueue = new Queue<T>();
        mergeHelper(input1, input2, outputQueue);
        return outputQueue;
	}	
	
	/**
	 * Merges the sorted input queues into the output queue in sorted order.
	 * 
	 * Implement this method recursively:
	 * 
	 *   In the base case, there's nothing left to do.
	 *   
	 *   Otherwise:
	 *   
	 *     Make progress on moving elements from an input to the output.
	 *     
	 *     Then make a recursive call to mergeHelper.
	 *     
	 * @param input1 a sorted queue
	 * @param input2 a sorted queue
	 * @param output a sorted queue containing the accumulated progress so far
	 */
	void mergeHelper(Queue<T> input1, Queue<T> input2, Queue<T> output) {
		T element1;
		T element2;
		//If both input queues are empty I'm done
		if(input1.isEmpty() && input2.isEmpty()){
			return;
		}
		//If input1 is empty I enqueue an element from input2 onto output
		if(input1.isEmpty()){
			element2 = input2.dequeue();
			output.enqueue(element2);
		}
		//likewise if input2 is empty I enqueue from input1
		else if (input2.isEmpty()){
			element1 = input1.dequeue();
			output.enqueue(element1);
		}
		else{
			/* If neither are empty I look at the top elements of both queues
			 * I then compare to see if one is less than another
			 * Whichever one is smaller I dequeue it and enqueue it onto output
			 */
			element1 = input1.peek();
			element2 = input2.peek();
			if(element1.compareTo(element2) <= 0){
				output.enqueue(input1.dequeue());
			}
			else{
				output.enqueue(input2.dequeue());
			}
		}
		//I then repeat the process until completion 
		mergeHelper(input1, input2, output);
	}
}
