package pieces;

import java.util.ArrayList;
import java.util.List;

import environment.Case;

public class King extends Pieces {
	// --- FIELD ---
	private Boolean unmoved;

	// --- CONSTRUCTOR ---
	public King(int posX, int posY, Boolean owner) {
		super(posX, posY, owner, 1000);
		double[][] grid = {
				{-3.0,	-4.0,   -4.0,	-5.0,	-5.0,	-4.0,	-4.0,	-3.0},
				{-3.0,	-4.0,	-4.0,	-5.0,	-5.0,	-4.0,	-4.0,	-3.0},
				{-3.0,	-4.0,	-4.0,	-5.0,	-5.0,	-4.0,	-4.0,	-3.0},
				{-3.0,	-4.0,	-4.0,	-5.0,	-5.0,	-4.0,	-4.0,	-3.0},
				{-2.0,	-3.0,	-3.0,	-4.0,	-4.0,	-3.0,	-3.0,	-2.0},
				{-1.0,	-2.0,	-2.0,	-2.0, 	-2.0,	-2.0,	-2.0,	-1.0},
				{2.0,	2.0,	 0.0,	 0.0,	 0.0,	 0.0, 	 2.0, 	 2.0},
				{2.0,	3.0,	 1.0,	 0.0,	 0.0,	 1.0, 	 3.0, 	 2.0}
				};
		this.setGrid(grid);
		setUnmoved(true);
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
		Bishop dummy = new Bishop(this.getPosX(), this.getPosY(), this.getOwner());
		Rook dummy2 = new Rook(this.getPosX(), this.getPosY(), this.getOwner());
		if (Math.abs(this.getPosX() - destPosX) > 1 || Math.abs(this.getPosY() - destPosY) > 1) {
			return false;
		} else {
			if (!dummy.isMoveLegal(destPosX, destPosY, chessBoard)
					&& !dummy2.isMoveLegal(destPosX, destPosY, chessBoard)) {
				return false;
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
				if (this.isMoveLegal(this.getPosX() + dirX * count, this.getPosY() + dirY * count, chessBoard)) {
					int[] newMove = { this.getPosX() + dirX * count, this.getPosY() + dirY * count };
					possiblemoves.add(newMove);
				}
				dirY += 1;
			}
			dirX += 1;
		}
		return possiblemoves;
	}


	public Pieces clone() {
		return new King(this.getPosX(), this.getPosY(), this.getOwner());
	}

	// TO DO Voir avec Kevin
	public List<int[]> roqueLegal(Case[][] chessBoard) {
		List<int[]> possiblemoves = new ArrayList<int[]>();
		Rook rook;
		if (this.getUnmoved()) {
			if (chessBoard[this.getPosX()][7].getActualPieces() != null && chessBoard[this.getPosX()][7].getActualPieces().getClass().equals(Rook.class)) {
				rook = (Rook) chessBoard[this.getPosX()][7].getActualPieces();
				if (rook.getUnmoved() && rook.getOwner()==this.getOwner()) {
					boolean nopieces=true;
					int i=1;
					while(nopieces && i<3) {
						if (chessBoard[this.getPosX()][this.getPosY()+i].getActualPieces()!=null) {
							nopieces=false;
						}
						i++;
					}
					if (nopieces) {
						int[] newMove = { this.getPosX(), this.getPosY()+2};//ou ajouter 2 move?pour l'instant que le roi
						possiblemoves.add(newMove);
					}
					
				}
			}
			if (chessBoard[this.getPosX()][0].getActualPieces() != null && chessBoard[this.getPosX()][0].getActualPieces().getClass().equals(Rook.class)) {
				rook = (Rook) chessBoard[this.getPosX()][0].getActualPieces();
				if (rook.getUnmoved() && rook.getOwner()==this.getOwner()) {
					boolean nopieces=true;
					int i=1;
					while(nopieces && i<=3) {
						if (chessBoard[this.getPosX()][this.getPosY()-i].getActualPieces()!=null) {
							nopieces=false;
						}
						i++;
					}
					if (nopieces) {
						int[] newMove = { this.getPosX(), this.getPosY()-2};//ou ajouter 2 move?pour l'instant que le roi
						possiblemoves.add(newMove);
					}
				}
			}
		}

		return possiblemoves;
	}

}
