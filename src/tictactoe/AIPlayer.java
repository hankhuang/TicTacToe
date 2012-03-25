package tictactoe;


import java.util.Arrays;

import tictactoe.model.Board;
import tictactoe.model.Piece;

public class AIPlayer {
	
	public int getMove(final Board board) {
		final int[] scores = new int[9];
		Arrays.fill(scores, 0);
		// Check for Friend
		for (int i = 0; i < 9; i++) {
			if (!board.isEmpty(i)) {
				continue;
			}
			if (board.columnContains(i % 3, Piece.O)==2 || board.rowContains(i / 3, Piece.O)==2 || board.diagonalContains(i, Piece.O)==2) {
				scores[i] = Integer.MAX_VALUE;
				break;
			}
			if (board.columnContains(i % 3, Piece.X) > 0 ^ board.columnContains(i % 3, Piece.O) > 0) {
				scores[i]++;
			}
			if (board.rowContains(i / 3, Piece.X) > 0 ^ board.rowContains(i / 3, Piece.O) > 0) {
				scores[i]++;
			}
			if (board.diagonalContains(i, Piece.X) > 0 ^ board.diagonalContains(i, Piece.O) > 0) {
				scores[i]++;
			}
		}
		
		int maxScore = 0;
		int maxMove = -1;
		for (int i = 0; i < 9; i++) {
			if (scores[i] > maxScore) {
				maxScore = scores[i];
				maxMove = i;
			}
		}
		return maxMove;
	}

}
