package filesystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.NoSuchElementException;

import structures.Queue;
import structures.UnboundedQueueInterface;
/**
 * An iterator to perform a level order traversal of part of a 
 * filesystem. A level-order traversal is equivalent to a breadth-
 * first search.
 * 
 * @author liberato
 *
 */
public class LevelOrderIterator extends FileIterator<File> {
	
	protected Queue<File> queue = new Queue<File>();
	
	/**
	 * Instantiate a new LevelOrderIterator, rooted at the rootNode.
	 * @param rootNode
	 * @throws FileNotFoundException if the rootNode does not exist
	 */
	public LevelOrderIterator(File rootNode) throws FileNotFoundException {
        if(!rootNode.exists()){ //a simple check to see if there is a file 
        	throw new FileNotFoundException("No file found");
        }
        queue.enqueue(rootNode); //if there is I enqueue it onto my new queue
	}
	
	@Override
	public boolean hasNext() {
		if(queue.isEmpty()){
			return false;
		}
		return true;
	}

	@Override
	public File next() throws NoSuchElementException {
        if(!this.hasNext()){
        	throw new NoSuchElementException("Cannot find file");
        }
        File file = queue.dequeue(); //I begin by dequeuing the first file element
        if(file.isDirectory()){ //if the file is a directory
        	//I make an array of all the directory's children
        	File[] fileChildren = file.listFiles(); 
        	for(int i = 0; i< fileChildren.length; i++){
        		//First I sort the files using Arrays.sort
        		Arrays.sort(fileChildren);
        		//then I enqueue the sorted files into my queue
        		queue.enqueue(fileChildren[i]);
        	}
        }
        //I finally return the next file
        return file; 
	}

	@Override
	public void remove() {
		// Leave this one alone.
		throw new UnsupportedOperationException();		
	}

}
