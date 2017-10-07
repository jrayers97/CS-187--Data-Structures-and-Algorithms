package hanoi;

import structures.LinkedStack;
import structures.StackInterface;

/**
 * A {@link StackBasedHanoiPeg} is a {@link HanoiPeg} backed by a
 * {@link LinkedStack}
 * 
 * @author jcollard
 *
 */
public class StackBasedHanoiPeg implements HanoiPeg {
	/**
	 * Creates a new {@link StackBasedHanoiPeg} that has no rings.
	 */
	private LinkedStack<HanoiRing> peg;
	
	public StackBasedHanoiPeg() {
		peg = new LinkedStack<HanoiRing>();
	}

	@Override
	public void addRing(HanoiRing ring) throws IllegalHanoiMoveException {
		if(peg.isEmpty() == true || peg.peek().getSize() > ring.getSize()){
			peg.push(ring);
		}
		else{
			throw new IllegalHanoiMoveException("Attempting to add a ring larger than the current top ring");
		}
		
	}

	@Override
	public HanoiRing remove() throws IllegalHanoiMoveException {
        if(peg.isEmpty() == true){
        	throw new IllegalHanoiMoveException("Attempting to remove a nonexistant element from an empty ring");
        }
        return peg.pop();
	}

	@Override
	public HanoiRing getTopRing() throws IllegalHanoiMoveException {
		if(peg.isEmpty() == true){
			throw new IllegalHanoiMoveException("There is no top ring to return");
		}
        return peg.peek();
	}

	@Override
	public boolean hasRings() {
       if(peg.isEmpty() == true){
    	   return false;
       }
       else{
    	   return true;
       }
	}
}
