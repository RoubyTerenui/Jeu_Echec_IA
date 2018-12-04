package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import environment.Environment;
import pieces.Rook;

class Rook_Test {

	@Test
	void test() {
		Environment environment=new Environment();
		List<int[]>testPoss=environment.getChessBoard()[0][0].getActualPieces().possibleMoves(environment.getChessBoard());
		assertEquals(testPoss.size(), 0);
		environment.getChessBoard()[3][4].setActualPieces(new Rook(0,0,false));
		testPoss.clear();
		testPoss=environment.getChessBoard()[3][4].getActualPieces().possibleMoves(environment.getChessBoard());
		for (int[] is : testPoss) {
			System.out.println("Position X"+is[0]+"PositionY"+is[1]);
		}
	}

}
