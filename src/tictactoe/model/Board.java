package tictactoe.model;


import java.util.Arrays;



public class Board {

	private Piece[] tiles = new Piece[9];

	public Board(){
		Arrays.fill(tiles, Piece.EMPTY);
	};

	public boolean addMove(int square, boolean isO) {
		if (isEmpty(square)) {
			tiles[square] = isO ? Piece.O : Piece.X;
			return true;
		}
		System.out.println(square);
		return false;
	}

	public boolean isEmpty(final int square) {
		return tiles[square] == Piece.EMPTY;
	}

	public boolean checkDiagonal(Piece piece) {
		return tiles[0] == piece && tiles[4] == piece && tiles[8] == piece || 
				tiles[2] == piece && tiles[4] == piece && tiles[6] == piece;
	}

	public boolean checkColumn(final int col, final Piece piece) {
		return tiles[col] == piece && tiles[col + 3] == piece && tiles[col + 6] == piece;
	}

	public boolean checkRow(final int row, final Piece piece) {
		return tiles[row * 3] == piece && tiles[row * 3 + 1] == piece && tiles[row * 3 + 2] == piece;
	}

	public int columnContains(final int col, final Piece piece) {
		int count = 0;
		for (int i = col; i < 9; i += 3) {
			if (tiles[i] == piece) count++;
		}
		return count;
	}

	public int rowContains(final int row, final Piece piece) {
		int count = 0;
		for (int i = 0; i < 3; i++) {
			if (tiles[row * 3 + i] == piece) count++;
		}
		return count;
	}

	public int diagonalContains(final int square, final Piece piece) {
		int count1 = 0, count2 = 0;
		if (square % 4 == 0) {
			if (tiles[0] == piece) count1++;
			if (tiles[4] == piece) count1++;
			if (tiles[8] == piece) count1++;
		}
		if (square % 4 == 2) {
			if (tiles[2] == piece) count2++;
			if (tiles[4] == piece) count2++;
			if (tiles[6] == piece) count2++;
		}
		return Math.max(count1, count2);
	}

	public void print() {
		System.out.println(" --- --- --- ");
		for (int i = 0; i < 9; i++) {
			if (i % 3 == 0) {
				System.out.print("| ");
			}
			if (tiles[i] == Piece.EMPTY) {
				System.out.print(i + 1 + " | ");
			} else {
				System.out.print(tiles[i] + " | ");
			}
			if (i % 3 == 2) {
				System.out.println();
				System.out.println(" --- --- --- ");
			}
		}
	}
}
