package pieces;

import java.util.ArrayList;
import java.util.List;

import environment.Case;

public class Rook extends Pieces {

	// --- FIELD ---
	private boolean unmoved;

	// --- CONSTRUCTOR ---
	public Rook(int posX, int posY, Boolean owner) {
		super(posX, posY, owner, 5);
		double[][] grid={
		{ 0.0,	0.0,	0.0,	0.0,	0.0,	0.0,	0.0,	 0.0},
		{ 0.5,	1.0,	1.0,	1.0,	1.0,	1.0,	1.0,	 0.5},
		{-0.5,	0.0,	0.0,	0.0,	0.0,	0.0,	0.0,	-0.5},
		{-0.5,	0.0,	0.0,	0.0,	0.0,	0.0,	0.0,	-0.5},
		{-0.5,	0.0,	0.0,	0.0,	0.0,	0.0,	0.0,	-0.5},
		{-0.5,	0.0,	0.0,	0.0,	0.0,	0.0,	0.0,	-0.5},
		{-0.5,	0.0,	0.0,	0.0,	0.0,	0.0,	0.0,	-0.5},
		{ 0.0,	0.0,	0.0,	0.5,	0.5,	0.0,	0.0,	 0.0}
		};
		this.setGrid(grid);
		unmoved=true;
	}

	// --- GETTER ---
	public Boolean getUnmoved() {
		return unmoved;
	}

	// --- SETTER ---
	public void setUnmoved(Boolean unmoved) {
		this.unmoved = unmoved;
	}

	// ---OTHER METHOD ---
	@Override
	public Boolean isMoveLegal(int destPosX, int destPosY, Case[][] chessBoard) {
		// Si en-dehors de l'echiquier illegal
		if (destPosX < 0 || destPosY < 0 || destPosX > 7 || destPosY > 7) {
			return false;
		} else {
			// Si le mouvement n'est pas diagonal illegal
			if (Math.abs(destPosX - this.getPosX()) != 0 && Math.abs(destPosY - this.getPosY()) != 0) {
				return false;
			} else {
				// Si il y a deja une piece du joueur a la destination illegal
				if (chessBoard[destPosX][destPosY].getActualPieces() != null) {
					if (chessBoard[destPosX][destPosY].getActualPieces().getOwner() == this.getOwner()) {
						return false;
					}
				}
				// Si il y a une piece sur la trajectoire la destination est illegal
				int end;
				if (destPosX > this.getPosX()) {
					end = destPosX - this.getPosX();
					for (int path = 1; path < end; path++) {
						if (chessBoard[this.getPosX() + path][this.getPosY()].getActualPieces() != null) {
							return false;
						}
					}
				} else {
					if (destPosX < this.getPosX()) {
						end = this.getPosX() - destPosX;
						for (int path = 1; path < end; path++) {
							if (chessBoard[this.getPosX() - path][this.getPosY()].getActualPieces() != null) {
								return false;
							}
						}
					} else {
						if (destPosY > this.getPosY()) {
							end = destPosY - this.getPosY();
							for (int path = 1; path < end; path++) {
								if (chessBoard[this.getPosX()][this.getPosY() + path].getActualPieces() != null) {
									return false;
								}
							}
						} else {
							end = this.getPosY() - destPosY;
							for (int path = 1; path < end; path++) {
								if (chessBoard[this.getPosX()][this.getPosY() - path].getActualPieces() != null) {
									return false;
								}
							}
						}
					}

				}
			}
		}
		if (chessBoard[destPosX][destPosY].getActualPieces() != null) {
			if (chessBoard[destPosX][destPosY].getActualPieces().getOwner() != this.getOwner()) {
				if (!chessBoard[destPosX][destPosY].getActualPieces().isEndangeredPieces()) {
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
			int count = 1;
			while (this.isMoveLegal(this.getPosX() + dirX * count, this.getPosY(), chessBoard)) {
				int[] newMove = { this.getPosX() + dirX * count, this.getPosY() };
				possiblemoves.add(newMove);
				count++;
			}
			dirX += 2;
		}
		int dirY = -1;
		while (dirY < 2) {
			int count = 1;
			while (this.isMoveLegal(this.getPosX(), this.getPosY() + dirY * count, chessBoard)) {
				int[] newMove = { this.getPosX(), this.getPosY() + dirY * count };
				possiblemoves.add(newMove);
				count++;
			}
			dirY += 2;
		}
		return possiblemoves;
	}

	public Pieces clone() {
		return new Rook(this.getPosX(), this.getPosY(), this.getOwner());
	}

}
