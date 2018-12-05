package pieces;

import java.util.ArrayList;
import java.util.List;

import environment.Case;

public class Pawn extends Pieces {
	// --- FIELDS ---
	private boolean initialPosition;

	// --- CONSTRUCTOR ---
	public Pawn(int posX, int posY, Boolean owner) {
		super(posX, posY, owner, 1);
		this.setInitialPosition(true);
	}

	// --- GETTER ---
	public boolean isInitialPosition() {
		return initialPosition;
	}

	// --- SETTER ---
	public void setInitialPosition(boolean initialPosition) {
		this.initialPosition = initialPosition;
	}

	// --- METHODS TO IMPLEMENT PIECES ---
	@Override
	public Boolean isMoveLegal(int destPosX, int destPosY, Case[][] chessBoard) {
		if (destPosX < 0 || destPosY < 0 || destPosX > 7 || destPosY > 7) {
			return false;
		} else {
			// Le pion est noir il incremente sa position
			if (this.getOwner()) {
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
				if (this.getOwner()) {
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

}
