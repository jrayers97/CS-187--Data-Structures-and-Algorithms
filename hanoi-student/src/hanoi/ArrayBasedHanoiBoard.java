package hanoi;

/**
 * A {@link ArrayBasedHanoiBoard} is a simple implementation of
 * {@link HanoiBoard}
 * 
 * @author jcollard
 * 
 */
public class ArrayBasedHanoiBoard implements HanoiBoard {

		HanoiPeg[] board = new StackBasedHanoiPeg[3];
	
	/**
	 * Creates a {@link ArrayBasedHanoiBoard} with three empty {@link HanoiPeg}s
	 * and {@code n} rings on peg 0.
	 */
	public ArrayBasedHanoiBoard(int n) {
		if(n < 0){
			throw new IllegalArgumentException("Cannot add negative rings");
		}
		for(int i = 0; i < 3; i++){
			board[i] = new StackBasedHanoiPeg();
		}
		for(int i = n; i > 0; i--){
			board[0].addRing(new HanoiRing(i));
		}
	}

	@Override
	public void doMove(HanoiMove move) throws IllegalHanoiMoveException {
		if(isLegalMove(move) == false){
			throw new IllegalHanoiMoveException("Attempting an illegal move");
		}
		if(move == null){
			throw new NullPointerException("You must make a move");
		}
		HanoiPeg startPeg = board[move.getFromPeg()];
		HanoiPeg destPeg = board[move.getToPeg()];
		if(isLegalMove(move) == true){
			HanoiRing ring = startPeg.remove();
			destPeg.addRing(ring);
		}
	}

	@Override
	public boolean isSolved() {
		boolean answer = false;
        if(board[0].hasRings() == false && board[1].hasRings() == false){
        	answer = true;
        }
        return answer;
	}

	/**
	 * <p>
	 * A {@link HanoiMove} is not legal if either is true:
	 * 
	 * <ul>
	 * <li>The from peg has no rings</li>
	 * <li>The to peg has rings AND the ring to be moved has a size larger than
	 * the topmost ring on the to peg.</li>
	 * </ul>
	 * 
	 * Otherwise, the move is legal.
	 * </p>
	 */
	@Override
	public boolean isLegalMove(HanoiMove move) {
		HanoiPeg startPeg = board[move.getFromPeg()];
		HanoiPeg destPeg = board[move.getToPeg()];
		if(startPeg.hasRings() == false){
        	return false;
        }
        if(destPeg.hasRings() == true && destPeg.getTopRing().getSize() <= startPeg.getTopRing().getSize()){
        	return false;
        }
        return true;
	}
}
