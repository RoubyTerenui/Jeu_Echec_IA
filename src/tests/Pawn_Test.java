package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import environment.Case;
import environment.Environment;
import pieces.Pawn;

class Pawn_Test {

	@Test
	void test() {
		Environment environment=new Environment();
		List<int[]>testPoss=environment.getChessBoard()[1][0].getActualPieces().possibleMoves(environment.getChessBoard());
		assertEquals(testPoss.size(), 2);
		environment.getChessBoard()[3][4].setActualPieces(new Pawn(0,0,true));
		((Pawn) environment.getChessBoard()[3][4].getActualPieces()).setInitialPosition(false);
		environment.getChessBoard()[4][3].setActualPieces(new Pawn(0,0,false));
		environment.getChessBoard()[4][4].setActualPieces(new Pawn(0,0,false));
		environment.getChessBoard()[4][5].setActualPieces(new Pawn(0,0,true));
		testPoss.clear();
		testPoss=environment.getChessBoard()[3][4].getActualPieces().possibleMoves(environment.getChessBoard());
		for (int[] is : testPoss) {
			System.out.println("Position X"+is[0]+"PositionY"+is[1]);
		}
		
		environment.setChessBoard(new Case[8][8]);
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				environment.getChessBoard()[i][j] = new Case(i,j,null);
			}
		}
		environment.getChessBoard()[7][4].setActualPieces(new Pawn(0,0,true));
		testPoss.clear();
		testPoss=environment.getChessBoard()[7][4].getActualPieces().possibleMoves(environment.getChessBoard());
		for (int[] is : testPoss) {
			System.out.println("Position X"+is[0]+"PositionY"+is[1]);
		}
	}

}
