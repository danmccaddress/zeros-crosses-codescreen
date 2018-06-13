package uk.co.mccaddress.noughtsandcrosses;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static uk.co.mccaddress.noughtsandcrosses.Position.*;

public class Game {
	
	private Move nextMove = Move.CROSS;
	private Map<Position, Move> boardMap = new HashMap<>();
	
	public Result makeAMove(Position position, Move move) {
		Result result;
		if (move == nextMove) {
			if (boardMap.containsKey(position)) {
				result = Result.UNSUCCESSFUL_MOVE_POSITION_TAKEN;
			} else {
				boardMap.put(position, move);
				result = Result.SUCCESSFUL_MOVE;
				
				if (weHaveAWinner(move)) {
					result = Result.WINNER;
				}
				
				updateNextMove();
			}
		} else {
			result = Result.UNSUCCESSFUL_MOVE_OUT_OF_ORDER;
		}
		
		return result;
	}
	
	private void updateNextMove() {
		if (nextMove == Move.CROSS) {
			nextMove = Move.NOUGHT;
		} else {
			nextMove = Move.CROSS;
		}
	}
	
	private boolean weHaveAWinner(Move move) {
		Stream<Position> winningCombination = Stream.of(
				LEFT_BOTTOM, MIDDLE_BOTTOM, RIGHT_BOTTOM
		);
		return winningCombination.allMatch(position ->
		positionsTakenBy(move).contains(position));
	}
	
	private Collection<Position> positionsTakenBy(Move move) {
		return boardMap.entrySet().stream()
				.filter(entry -> entry.getValue() == move)
				.map(entry -> entry.getKey())
				.collect(toList());
	}
}
