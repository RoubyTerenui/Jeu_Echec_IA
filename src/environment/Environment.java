package environment;

import pieces.Bishop;
import pieces.King;
import pieces.Knight;
import pieces.Pawn;
import pieces.Queen;
import pieces.Rook;

public class Environment implements Runnable {

	// --- FIELDS ---
	
	Case[][] chessBoard;
	int mesureDePerformance;
	// permet de savoir si le jeu est termin� ou est encore en cours
	boolean environmentRunning;
	
	
	// --- CONSTRUCTOR ---

	public Environment() {
		super();
		chessBoard=new Case[8][8];
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				this.chessBoard[i][j] = new Case(i,j,null);
				if ( i == 7 ) {
					// false pour les blancs
					initializeMainPieces(false, i , j);
				}
				else {
					if ( i == 6 ) {
						// false pour les blancs
						this.chessBoard[i][j].setActualPieces(new Pawn(i,j,false));
					}
					else {
						if (i==0) {
							// true pour les noirs
							initializeMainPieces(true, i , j);
						}
						else {
							if ( i == 1 ) {
								// false pour les blancs
								this.chessBoard[i][j].setActualPieces(new Pawn(i,j,true));
							}
						}
					}
				}
			}
		}
		this.mesureDePerformance = 0;
		this.environmentRunning = true;
	}

	private void initializeMainPieces(boolean owner,int i,int j) {
		switch( j ) {
			case 0 :case 7:
				this.chessBoard[i][j].setActualPieces(new Rook(i,j,owner));
				break;
			case 2 :case 5:
				this.chessBoard[i][j].setActualPieces(new Bishop(i,j,owner));
				break;
			case 1 :case 6:
				this.chessBoard[i][j].setActualPieces(new Knight(i,j,owner));
				break;
			case 3 :
				this.chessBoard[i][j].setActualPieces(new Queen(i,j,owner));
				break;
			case 4 :
				this.chessBoard[i][j].setActualPieces(new King(i,j,owner));
				break;
		}
		
	}

	// --- GETTERS ---
	
	public Case[][] getChessBoard() {
		return chessBoard;
	}
	public int getMesureDePerformance() {
		return mesureDePerformance;
	}
	public boolean isEnvironmentRunning() {
		return environmentRunning;
	}


	// --- SETTERS ---
	
	public void setMesureDePerformance(int mesureDePerformance) {
		this.mesureDePerformance = mesureDePerformance;
	}
	public void setChessBoard(Case[][] chessBoard) {
		this.chessBoard = chessBoard;
	}
	public void setEnvironmentRunning(boolean environmentRunning) {
		this.environmentRunning = environmentRunning;
	}
	
	// --- Method that run the environment on a specific thread
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
