package pieces;

import java.util.ArrayList;
import java.util.List;

import environment.Case;

public class King extends Pieces {

	public King(int posX, int posY, Boolean owner) {
		super(posX, posY, owner, 1000);
	}

	@Override
	public Boolean isMoveLegal(int destPosX, int destPosY, Case[][] chessBoard) {
		Bishop dummy=new Bishop(this.getPosX(),this.getPosY(),this.getOwner());
		Rook dummy2=new Rook(this.getPosX(),this.getPosY(),this.getOwner());
		if ( Math.abs(this.getPosX()-destPosX)>1 || Math.abs(this.getPosX()-destPosX)>1) {
			return false;
		}
		else {
			if(!dummy.isMoveLegal(destPosX, destPosY, chessBoard) && !dummy2.isMoveLegal(destPosX, destPosY, chessBoard)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public List<int[]> possibleMoves(Case[][] chessBoard) {
		List<int[]> possiblemoves=new ArrayList<int[]>();
		int dirX = -1;
		while (dirX < 2) {
			int dirY = -1;
			while (dirY < 2) {
				int count=1;
				if(this.isMoveLegal(this.getPosX()+dirX*count, this.getPosY()+dirY*count, chessBoard)) {
					int[] newMove= {this.getPosX()+dirX*count, this.getPosY()+dirY*count};
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
}
