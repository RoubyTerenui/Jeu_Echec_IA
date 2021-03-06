package pieces;

import java.util.ArrayList;
import java.util.List;

import environment.Case;

public class Knight extends Pieces {

	public Knight(int posX, int posY, Boolean owner) {
		super(posX, posY, owner, 3);
		double[][] grid = {
				{-5.0, 	-4.0,	 -3.0,	-3.0,	-3.0,	 -3.0,	-4.0,	-5.0},
				{-4.0, 	-2.0,	  0.0, 	 0.0,	 0.0,	  0.0,	-2.0,	-4.0},
				{-3.8, 	 0.0,	  1.0,	 1.5,	 1.5,	  1.0,	 0.0,	-3.0},
				{-3.0,	 0.5,	  1.5,	 2.0,	 2.0,	  1.5,	 0.5,	-3.0},
				{-3.0, 	 0.0,	  1.5,	 2.0, 	 2.0,	  1.5,	 0.0,	-3.0},
				{-3.0,	 0.5,	  1.0,	 1.5, 	 1.5,	  1.0,	 0.5,	-3.0},
				{-4.0,	-2.0,	  0.0,	 0.5, 	 0.5,	  0.0,	-2.0,	-4.0},
				{-5.0,	-4.0,	 -3.0,	-3.0,	-3.0,	 -3.0,	-4.0,	-5.0}
		};
		this.setGrid(grid);
	}

	@Override
	public Boolean isMoveLegal(int destPosX, int destPosY, Case[][] chessBoard) {
		// Si en-dehors de l'echiquier illegal
		if (destPosX < 0 || destPosY < 0 || destPosX > 7 || destPosY > 7) {
			return false;
		} else {
			// Si il y a deja une piece du joueur a la destination illegal
			if (chessBoard[destPosX][destPosY].getActualPieces() != null) {
				if (chessBoard[destPosX][destPosY].getActualPieces().getOwner() == this.getOwner()) {
					return false;
				}
			}
			// Si le mouvement n'est pas 1 L
			if ((Math.abs(this.getPosX() - destPosX) != 2 || Math.abs(this.getPosY() - destPosY) != 1)
					&& (Math.abs(this.getPosY() - destPosY) != 2 || Math.abs(this.getPosX() - destPosX) != 1)) {
				return false;
			}
		}
		if (chessBoard[destPosX][destPosY].getActualPieces() != null) {
			if (chessBoard[destPosX][destPosY].getActualPieces().getOwner() != this.getOwner()) {
				if(!chessBoard[destPosX][destPosY].getActualPieces().isEndangeredPieces()) {
					chessBoard[destPosX][destPosY].getActualPieces().setEndangeredPieces(true);
				}
			}
		}
                chessBoard[destPosX][destPosY].setEndangered(true);
		return true;
	}

	@Override
	public List<int[]> possibleMoves(Case[][] chessBoard) {
		List<int[]> possiblemoves = new ArrayList<int[]>();
		int dirX = -1;
		while (dirX < 2) {
			int dirY = -1;
			while (dirY < 2) {
				if (this.isMoveLegal(this.getPosX() + dirX * 2, this.getPosY() + dirY, chessBoard)) {
					int[] newMove = { this.getPosX() + dirX * 2, this.getPosY() + dirY };
					possiblemoves.add(newMove);
				}
				if (this.isMoveLegal(this.getPosX() + dirX, this.getPosY() + dirY * 2, chessBoard)) {
					int[] newMove = { this.getPosX() + dirX, this.getPosY() + dirY * 2 };
					possiblemoves.add(newMove);
				}
				dirY += 2;
			}
			dirX += 2;
		}
		return possiblemoves;
	}

	public Pieces clone() {
		return new Knight(this.getPosX(), this.getPosY(), this.getOwner());
	}

}
