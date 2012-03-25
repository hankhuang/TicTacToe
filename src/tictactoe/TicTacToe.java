package tictactoe;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import tictactoe.model.Board;
import tictactoe.model.Piece;

public class TicTacToe {

	private final BufferedReader sysIn = new BufferedReader(new InputStreamReader(System.in));
	private final Board main;
	private boolean oTurn = true;

	private final AIPlayer ai = new AIPlayer();

	public static void main(String[] args) {
		try {
			new TicTacToe();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public TicTacToe() throws IOException {
		main = new Board();

		while(true) {
			try {
				int square;
				if (oTurn) {
					main.print();
					System.out.print("Your move: ");
					final String move = sysIn.readLine();
					square = Integer.parseInt(move) - 1;
				} else {
					square = ai.getMove(main);
					if (square == -1) {
						main.print();
						System.out.println("You tied!");
						break;
					}
				}
				if (square < 0 || square > 8) {
					System.out.println("Please input a valid move from (1-9)");
					continue;
				}
				if (main.addMove(square, oTurn)) {
					if (checkWin(square, oTurn)) {
						main.print();
						System.out.println(oTurn ? "You won!" : "You lost!");
						break;
					}
					oTurn = !oTurn;
					continue;
				} else {
					System.out.println("This square is already taken. Please pick another.");
					continue;
				}
			} catch (Exception e) {
				System.out.println("Please input a valid move from (1-9)");
				continue;
			}
		}
	}

	private boolean checkWin(final int square, final boolean isO) {
		boolean win = false;
		Piece piece = isO ? Piece.O : Piece.X;
		if (square % 2 == 0) {
			// check diagonal
			win |= main.checkDiagonal(piece);
		}
		win |= main.checkColumn(square % 3, piece);
		win |= main.checkRow(square / 3, piece);
		return win;
	}

}
