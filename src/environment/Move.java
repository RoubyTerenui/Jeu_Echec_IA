package environment;

import pieces.Pieces;

import java.util.Arrays;

public class Move {

    private Pieces piece;
    private int[] dest;

    public Move(Pieces p, int[] d) {
        this.piece = p;
        this.dest = d;
    }

    public Pieces getPiece() {
        return piece;
    }

    public void setPiece(Pieces piece) {
        this.piece = piece;
    }

    public int[] getDest() {
        return dest;
    }

    @Override
    public String toString() {
        return "Move Piece " + piece.getClass().getName() + " from (" + piece.getPosX() + ", " + piece.getPosY() + ") "
                + " to (" + dest[0] + ", " + dest[1] + ")";
    }

    public void setDest(int[] dest) {
        this.dest = dest;
    }

}
