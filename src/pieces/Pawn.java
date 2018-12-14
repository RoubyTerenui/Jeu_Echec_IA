package pieces;

import java.util.ArrayList;
import java.util.List;

import environment.Case;

public class Pawn extends Pieces {
	// --- FIELDS ---
	private boolean initialPosition;
	private boolean canBepassTaken;

	// --- CONSTRUCTOR ---
	public Pawn(int posX, int posY, Boolean owner) {
		super(posX, posY, owner, 1);
		double[][] grid= {
				{0.0,  0.0,  0.0,  0.0,  0.0,  0.0,  0.0, 0.0},
				{5.0,  5.0,  5.0,  5.0,  5.0,  5.0,  5.0, 5.0},
				{1.0,  1.0,  2.0,  3.0,  3.0,  2.0,  1.0, 1.0},
				{0.5,  0.5,  1.0,  2.5,  2.5,  1.0,  0.5, 0.5},
				{0.0,  0.0,  0.0,  2.0,  2.0,  0.0,  0.0, 0.0},
				{0.5, -0.5, -1.0,  0.0,  0.0, -1.0, -0.5, 0.5},
				{0.5,  1.0,  1.0, -2.0, -2.0,  1.0,  1.0, 0.5},
				{0.0,  0.0,  0.0,  0.0,  0.0,  0.0,  0.0, 0.0}
		};
		this.setGrid(grid);
		this.setInitialPosition(true);
	}

	// --- GETTER ---
	public boolean isInitialPosition() {
		return initialPosition;
	}

	public boolean isCanBepassTaken() {
		return canBepassTaken;
	}

	// --- SETTER ---
	public void setInitialPosition(boolean initialPosition) {
		this.initialPosition = initialPosition;
	}

	public void setCanBepassTaken(boolean canBepassTaken) {
		this.canBepassTaken = canBepassTaken;
	}

	// --- METHODS TO IMPLEMENT PIECES ---
	@Override
	public Boolean isMoveLegal(int destPosX, int destPosY, Case[][] chessBoard) {
		if (destPosX < 0 || destPosY < 0 || destPosX > 7 || destPosY > 7) {
			return false;
		} else {
			// Le pion est noir il incremente sa position
			if (this.getOwner() == true) {
				// Si le mouvement est vertical
				if (destPosY - this.getPosY() == 0) {
					// Si le pion est sur sa position initial
					if (this.isInitialPosition()) {
						// Si le d�placement est de plus de 2 case ou inferieur � 0
						if (destPosX - this.getPosX() > 2 || destPosX - this.getPosX() <= 0) {
							return false;
						}
						// Si le d�placement est de 2
						if (destPosX - this.getPosX() == 2) {
							// Si il y a une piece sur le chemin ou sur la destination
							if (chessBoard[this.getPosX() + 2][this.getPosY()].getActualPieces() != null
									|| chessBoard[this.getPosX() + 1][this.getPosY()].getActualPieces() != null) {
								return false;
							}
						}
					}
					// Si il y a une piece sur la position ou l'on souhaite acceder
					if (chessBoard[this.getPosX() + 1][destPosY].getActualPieces() != null) {
						return false;
					}
					// Si le mouvement n'est pas purement vertical
				} else {
					// Si le mouvement est different de 1 case sur X
					if (destPosX - this.getPosX() != 1) {
						return false;
					} else {
						// Si le mouvement diagonal est de plus de 1
						if (Math.abs(destPosY - this.getPosY()) != 1) {
							return false;
						} else {
							// Si le mouvement possede une piece
							if (chessBoard[this.getPosX() + 1][destPosY].getActualPieces() != null) {
								// Si cette piece est alli�
								if (chessBoard[this.getPosX() + 1][destPosY].getActualPieces().getOwner() == this
										.getOwner()) {
									return false;
								}
								// Si la case diagonal n'a pas de piece
							} else {
								return false;
							}
						}

					}
				}
			}
			// Le pion est blanc il decremente sa position
			else {
				if (this.getOwner() == false) {
					if (this.isInitialPosition() && destPosY - this.getPosY() == 0) {
						if (this.getPosX() - destPosX > 2 || this.getPosX() - destPosX <= 0) {
							return false;
						}
						if (this.getPosX() - destPosX == 2) {
							if (chessBoard[this.getPosX() - 2][this.getPosY()].getActualPieces() != null
									|| chessBoard[this.getPosX() - 1][this.getPosY()].getActualPieces() != null) {
								return false;
							}
							if (this.getPosX() - destPosX == 2) {
								if (chessBoard[this.getPosX() - 2][this.getPosY()].getActualPieces() != null
										|| chessBoard[this.getPosX() - 1][this.getPosY()].getActualPieces() != null) {
									return false;
								}
							}
						}
						if (chessBoard[this.getPosX() - 1][this.getPosY()].getActualPieces() != null) {
							return false;
						}
					} else {
						if (this.getPosX() - destPosX > 1) {
							return false;
						} else {
							if (destPosY - this.getPosY() != 0) {
								if (Math.abs(destPosY - this.getPosY()) != 1) {
									return false;
								} else {
									if (chessBoard[this.getPosX() - 1][destPosY].getActualPieces() != null) {
										if (chessBoard[this.getPosX() - 1][destPosY].getActualPieces()
												.getOwner() == this.getOwner()) {
											return false;
										}
									} else {
										return false;
									}
								}
							}
						}
					}
				}
			}
		}
		if (chessBoard[destPosX][destPosY].getActualPieces() != null) {
			if (chessBoard[destPosX][destPosY].getActualPieces().getOwner() != this.getOwner()) {
				if (Math.abs(destPosY - this.getPosY()) == 1) {
					if (!chessBoard[destPosX][destPosY].getActualPieces().isEndangeredPieces()) {
						chessBoard[destPosX][destPosY].getActualPieces().setEndangeredPieces(true);
					}
				}
			}
		}
                if (Math.abs(destPosY - this.getPosY()) == 1) {
                    chessBoard[destPosX][destPosY].setEndangered(true);
                }
		return true;
	}

	@Override
	public List<int[]> possibleMoves(Case[][] chessBoard) {
		List<int[]> possiblemoves = new ArrayList<int[]>();
		int dirX;
		if (!this.getOwner()) {
			dirX = -1;
		} else {
			dirX = 1;
		}
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
		return possiblemoves;
	}

	public Pieces clone() {
		return new Pawn(this.getPosX(), this.getPosY(), this.getOwner());
	}

	// TO DO trouver un moyen de l'ajouter a la liste des mouvement possibles
	// verifier son efficacit�
	public List<int[]> passTakenMove(Case[][] chessBoard) {
		List<int[]> possiblemoves = new ArrayList<int[]>();
		Pieces adjacent = chessBoard[this.getPosX()][this.getPosY() - 1].getActualPieces();
		if (adjacent.getClass().equals(Pawn.class)) {
			if (adjacent.getOwner() != this.getOwner()) {
				if (((Pawn) adjacent).isCanBepassTaken()) {
					int[] newMove = { this.getPosX(), this.getPosY() - 1 };
					possiblemoves.add(newMove);
				}
			}
		}
		adjacent = chessBoard[this.getPosX()][this.getPosY() + 1].getActualPieces();
		if (adjacent.getClass().equals(Pawn.class)) {
			if (adjacent.getOwner() != this.getOwner()) {
				if (((Pawn) adjacent).isCanBepassTaken()) {
					int[] newMove = { this.getPosX(), this.getPosY() + 1 };
					possiblemoves.add(newMove);
				}
			}
		}

		return possiblemoves;

	}
}
