package structures;

import java.util.Iterator;

public class ScapegoatTree<T extends Comparable<T>> extends
		BinarySearchTree<T> {
	private int upperBound;

	
	@Override
	public void add(T t) {
		upperBound++;
		//I made a new BST node and added it to the subtree with respect to the root
		BSTNode<T> newNode = new BSTNode<T>(t, null, null);
		root = addToSubtree(root, newNode);
		double logPow = 3.0/2.0;
		//Using the given scapegoat rule I check if the the height is greater than the log
		if((double)this.height() > (Math.log(upperBound)/Math.log(logPow))){
			//I made a child node and a parent node
			BSTNode<T> w = parent(newNode);
			BSTNode<T> childNode = newNode;
			parent(newNode);
			//While this condition is met, backtrack through the tree to find w
			while((double)subtreeSize(childNode)/subtreeSize(w) <= (1/logPow)){
				w = parent(w);
				childNode = parent(childNode);
			}
			//I now make a subtree starting at the scapegoat node
			BinarySearchTree<T> scape = new BinarySearchTree<T>();
			scape.root = w;
			BSTNode<T> newParent = parent(w);
			//finally I balance, and rebuild the subtree
			scape.balance();
			if(newParent.getLeft()== w){
				newParent.setLeft(scape.root);
			}
			else{
				newParent.setRight(scape.root);
			}
		}
	}
	
	@Override
	//Straightforward remove method based on the directions
	public boolean remove(T element) {
		if(super.remove(element)){
			if(upperBound > 2*size()){
				balance();
				upperBound = size();
			}
			return true;
		}
		return false;
	}
	
	public BSTNode<T> parent(BSTNode<T> input){
		return parentHelper(root, input);
	}
	
	public BSTNode<T> parentHelper(BSTNode<T> currNode, BSTNode<T> input){
		if(currNode == null || input == null){
			throw new NullPointerException();
		}
		else{
			if(currNode.getLeft() == input || currNode.getRight() == input){
				return currNode;
			}
			else if(currNode.getData().compareTo(input.getData()) < 0){
				return parentHelper(currNode.getRight(), input);
			}
			else{
				return parentHelper(currNode.getLeft(), input);
			}
		}
	}
}
