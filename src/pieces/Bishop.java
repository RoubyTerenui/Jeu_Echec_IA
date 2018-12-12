package pieces;

import java.util.ArrayList;
import java.util.List;

import environment.Case;

public class Bishop extends Pieces {

	public Bishop(int posX, int posY, Boolean owner) {
		super(posX, posY, owner, 3);
		double[][] grid = { 
				{ -2, -1, -1, -1, -1, -1, -1, -2 }, 
				{ -1,  0,  0,  0,  0,  0,  0, -1 },
				{ -1,  0,0.5, -1, -1, -1, -1, -1 }, 
				{ -1, -1, -1, -1, -1, -1, -1, -1 },
				{ -1, -1, -1, -1, -1, -1, -1, -1 }, 
				{ -1, -1, -1, -1, -1, -1, -1, -1 },
				{ -1, -1, -1, -1, -1, -1, -1, -1 }, 
				{ -2, -1, -1, -1, -1, -1, -1, -2 } 
				};
		this.setGrid(grid);
	}

	@Override
	public Boolean isMoveLegal(int destPosX, int destPosY, Case[][] chessBoard) {
		// Si en-dehors de l'echiquier illégal
		if (destPosX < 0 || destPosY < 0 || destPosX > 7 || destPosY > 7) {
			return false;
		} else {
			// Si le mouvement n'est pas diagonal illégal
			if (Math.abs(destPosX - this.getPosX()) != Math.abs(destPosY - this.getPosY())) {
				return false;
			} else {
				// Si il y a deja une piece du joueur a la destination illegal
				if (chessBoard[destPosX][destPosY].getActualPieces() != null) {
					if (chessBoard[destPosX][destPosY].getActualPieces().getOwner() == this.getOwner()) {
						return false;
					}
				}
				// Si il y a une piece sur la trajectoire la destination est illegal
				int end = Math.abs(destPosX - this.getPosX());
				if (destPosX > this.getPosX()) {
					for (int path = 1; path < end; path++) {
						if (destPosY > this.getPosY()) {
							if (chessBoard[this.getPosX() + path][this.getPosY() + path].getActualPieces() != null) {
								return false;
							}
						} else {
							if (chessBoard[this.getPosX() + path][this.getPosY() - path].getActualPieces() != null) {
								return false;
							}
						}
					}
				} else {
					for (int path = 1; path < end; path++) {
						if (destPosY > this.getPosY()) {
							if (chessBoard[this.getPosX() - path][this.getPosY() + path].getActualPieces() != null) {
								return false;
							}
						} else {
							if (chessBoard[this.getPosX() - path][this.getPosY() - path].getActualPieces() != null) {
								return false;
							}
						}
					}
				}

			}
		}
		// SI la case a destination possede une piece ennemie la met en danger
		if (chessBoard[destPosX][destPosY].getActualPieces() != null) {
			if (chessBoard[destPosX][destPosY].getActualPieces().getOwner() != this.getOwner()) {
				if (chessBoard[destPosX][destPosY].getActualPieces().isEndangeredPieces()) {
					chessBoard[destPosX][destPosY].getActualPieces().setEndangeredPieces(true);
				}
			}
		}
		return true;
	}

	@Override
	public List<int[]> possibleMoves(Case[][] chessBoard) {
		List<int[]> possiblemoves = new ArrayList<int[]>();
		int dirX = -1;
		while (dirX < 2) {
			int dirY = -1;
			while (dirY < 2) {
				int count = 1;
				while (this.isMoveLegal(this.getPosX() + dirX * count, this.getPosY() + dirY * count, chessBoard)) {
					int[] newMove = { this.getPosX() + dirX * count, this.getPosY() + dirY * count };
					possiblemoves.add(newMove);
					count++;
				}
				dirY += 2;
			}
			dirX += 2;
		}
		return possiblemoves;
	}

	public Pieces clone() {
		return new Bishop(this.getPosX(), this.getPosY(), this.getOwner());
	}
}
