package pieces;

import java.util.List;

import environment.Case;

public abstract class Pieces {
	private int posX;
	private int posY;
	private int value;
	private double[][] grid; 
	private boolean endangeredPieces;
	private Boolean owner;// TO DO determiner quel etat du boolean correspond a quel joueur
	
	
	
	public Pieces(int posX, int posY, Boolean owner,int value) {
		super();
		this.posX = posX;
		this.posY = posY;
		this.owner = owner;
		this.value=value;
		grid=new double[8][8];
		for (int i=0;i<8;i++) {
			for (int j=0;j<8;j++) {
				grid[i][j]=0;
			}
		}
		
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
	public boolean isEndangeredPieces() {
		return endangeredPieces;
	}
	public double[][] getGrid() {
		return grid;
	}
	
	// --- SETTER ---
	
	public void setGrid(double[][] grid) {
		this.grid = grid;
	}
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
	public void setEndangeredPieces(boolean endangeredPieces) {
		this.endangeredPieces = endangeredPieces;
	}

	// Method that state if the move is legal or not for a special kind of pieces
	public abstract Boolean isMoveLegal(int destPosX,int destPosY , Case[][] chessBoard);
	
	// Method that return all the possible moves by the selected Piece
	public abstract List<int[]> possibleMoves(Case[][] chessBoard);

	public abstract Pieces clone();

	


}
