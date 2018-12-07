package pieces;

import java.util.List;

import environment.Case;

public abstract class Pieces {
	private int posX;
	private int posY;
	private int value;
	private Boolean owner;// TO DO determiner quel etat du boolean correspond a quel joueur
	
	
	
	public Pieces(int posX, int posY, Boolean owner,int value) {
		super();
		this.posX = posX;
		this.posY = posY;
		this.owner = owner;
		this.value=value;
		
	}
	
	// --- GETTER ---
	
	public int getPosX() {
		return posX;
	}
	public Boolean getOwner() {
		return owner;
	}
	public int getPosY() {
		return posY;
	}
	public int getValue() {
		return value;
	}
	
	// --- SETTER ---
	
	public void setPosY(int posY) {
		this.posY = posY;
	}
	public void setPosX(int posX) {
		this.posX = posX;
	}
	public void setOwner(Boolean owner) {
		this.owner = owner;
	}
	public void setValue(int value) {
		this.value = value;
	}

	// Method that state if the move is legal or not for a special kind of pieces
	public abstract Boolean isMoveLegal(int destPosX,int destPosY , Case[][] chessBoard);
	
	// Method that return all the possible moves by the selected Piece
	public abstract List<int[]> possibleMoves(Case[][] chessBoard);

	public abstract Pieces clone();
}
