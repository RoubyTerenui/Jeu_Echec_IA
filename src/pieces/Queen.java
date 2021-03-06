package pieces;

import java.util.ArrayList;
import java.util.List;

import environment.Case;

public class Queen extends Pieces {

	public Queen(int posX, int posY, Boolean owner) {
		super(posX, posY, owner, 9);
		double grid[][]= {
						{-2.0,	-1.0,	-1.0,	-0.5,	-0.5,	-1.0,	-1.0,	-2.0},
						{-1.0,	 0.0,	 0.0,	 0.0,	 0.0,	 0.0,	 0.0,	-1.0},
						{-1.0,	 0.0,	 0.5,	 0.5,	 0.5,	 0.5,	 0.0,	-1.0},
						{-0.5,	 0.0,	 0.5,	 0.5,	 0.5,	 0.5,	 0.0,	-0.5},
						{ 0.0,	 0.0,	 0.5,	 0.5,	 0.5,	 0.5,	 0.0,	-0.5},
						{-1.0,	 0.5,	 0.5,	 0.5,	 0.5,	 0.5,	 0.0,	-1.0},
						{-1.0,	 0.0,	 0.5,	 0.0,	 0.0,	 0.0,	 0.0,	-1.0},
						{-2.0,	-1.0,	-1.0,	-0.5,	-0.5,	-1.0,	-1.0,	-2.0}
		};
		this.setGrid(grid);
	}

	@Override
	public Boolean isMoveLegal(int destPosX, int destPosY, Case[][] chessBoard) {
		Bishop dummy = new Bishop(this.getPosX(), this.getPosY(), this.getOwner());
		Rook dummy2 = new Rook(this.getPosX(), this.getPosY(), this.getOwner());
		if (!dummy.isMoveLegal(destPosX, destPosY, chessBoard) && !dummy2.isMoveLegal(destPosX, destPosY, chessBoard)) {
			return false;
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
				dirY += 1;
			}
			dirX += 1;
		}
		return possiblemoves;
	}

	public Pieces clone() {
		return new Queen(this.getPosX(), this.getPosY(), this.getOwner());
	}

}
