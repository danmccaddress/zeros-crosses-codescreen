import org.junit.Test;
import uk.co.mccaddress.noughtsandcrosses.Game;
import uk.co.mccaddress.noughtsandcrosses.Move;
import uk.co.mccaddress.noughtsandcrosses.Position;
import uk.co.mccaddress.noughtsandcrosses.Result;

import static org.junit.Assert.assertEquals;

public class MoveTest {
	
	@Test
	public void testCrossFirst() {
		Game game = new Game();
		Result result = game.makeAMove(Position.LEFT_BOTTOM, Move.CROSS);
		
		assertEquals(Result.SUCCESSFUL_MOVE, result);
	}
	
	@Test
	public void testCrossNotFirstFails() {
		Game game = new Game();
		Result result = game.makeAMove(Position.LEFT_BOTTOM, Move.NOUGHT);
		
		assertEquals(Result.UNSUCCESSFUL_MOVE_OUT_OF_ORDER, result);
	}
	
	@Test
	public void testSecondMoveOnSamePos() {
		Game game = new Game();
		Result firstResult = game.makeAMove(Position.LEFT_BOTTOM, Move.CROSS);
		Result secondResult = game.makeAMove(Position.LEFT_BOTTOM, Move.NOUGHT);
		
		assertEquals(Result.SUCCESSFUL_MOVE, firstResult);
		assertEquals(Result.UNSUCCESSFUL_MOVE_POSITION_TAKEN, secondResult);
	}
	
	@Test
	public void testCrossWinsBottomRow() {
		Game game = new Game();
		Result result = game.makeAMove(Position.LEFT_BOTTOM, Move.CROSS);
		assertEquals(Result.SUCCESSFUL_MOVE, result);
		result = game.makeAMove(Position.LEFT_TOP, Move.NOUGHT);
		assertEquals(Result.SUCCESSFUL_MOVE, result);
		result = game.makeAMove(Position.MIDDLE_BOTTOM, Move.CROSS);
		assertEquals(Result.SUCCESSFUL_MOVE, result);
		result = game.makeAMove(Position.MIDDLE_MIDDLE, Move.NOUGHT);
		assertEquals(Result.SUCCESSFUL_MOVE, result);
		result = game.makeAMove(Position.RIGHT_BOTTOM, Move.CROSS);
		
		assertEquals(Result.WINNER, result);
		
	}
	
}
