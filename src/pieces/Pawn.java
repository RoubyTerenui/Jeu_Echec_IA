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
				if (this.isInitialPosition() && destPosY - this.getPosY() == 0) {
					if (destPosX - this.getPosX() > 2 || destPosX - this.getPosX() <= 0) {
						return false;
					}
					if (destPosX - this.getPosX() == 2) {
						if (chessBoard[this.getPosX() + 2][this.getPosY()].getActualPieces() != null
								|| chessBoard[this.getPosX() + 1][this.getPosY()].getActualPieces() != null) {
							return false;
						}
					}
				} else {
					if (destPosX - this.getPosX() > 1) {
						return false;
					} else {
						if (destPosY - this.getPosY() != 0) {
							if (Math.abs(destPosY - this.getPosY()) != 1) {
								return false;
							} else {
								if (chessBoard[this.getPosX() + 1][destPosY].getActualPieces() != null) {
									if (chessBoard[this.getPosX() + 1][destPosY].getActualPieces()
											.getOwner() == this.getOwner()) {
										return false;
									}
								} else {
									return false;
								}
							}
						} else {
							if (chessBoard[this.getPosX() + 1][this.getPosY()].getActualPieces() != null) {
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
							} else {
								if (chessBoard[this.getPosX() - 1][this.getPosY()].getActualPieces() != null) {
									return false;
								}
							}

						}
					}
				}
			}
		}
		return true;
	}

	@Override
	public List<int[]> possibleMoves(Case[][] chessBoard) {
		List<int[]> possiblemoves = new ArrayList<int[]>();
		int dirX;
		if (this.getOwner() == false) {
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

<<<<<<< Updated upstream
	public Pieces clone() {
		return new Pawn(this.getPosX(), this.getPosY(), this.getOwner());
	}

=======
	// TO DO trouver un moyen de l'ajouter a la liste des mouvement possibles verifier son efficacité
	public List<int[]> passTakenMove(Case[][] chessBoard){
		List<int[]> possiblemoves = new ArrayList<int[]>();
		Pieces adjacent=chessBoard[this.getPosX()][this.getPosY()-1].getActualPieces();
		if(adjacent.getClass().equals(Pawn.class)) {
			if(adjacent.getOwner()!=this.getOwner()) {
				if(((Pawn)adjacent).isCanBepassTaken()) {
					int[] newMove = { this.getPosX(), this.getPosY()-1 };
					possiblemoves.add(newMove);
				}
			}
		}
		adjacent=chessBoard[this.getPosX()][this.getPosY()+1].getActualPieces();
		if(adjacent.getClass().equals(Pawn.class)) {
			if(adjacent.getOwner()!=this.getOwner()) {
				if(((Pawn)adjacent).isCanBepassTaken()) {
					int[] newMove = { this.getPosX(), this.getPosY()+1 };
					possiblemoves.add(newMove);
				}
			}
		}
		
		return possiblemoves;
		
	}
>>>>>>> Stashed changes
}
