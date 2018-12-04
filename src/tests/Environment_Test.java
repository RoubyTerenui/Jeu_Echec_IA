package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import environment.Environment;

class Environment_Test {

	@Test
	void test() {
		Environment environment=new Environment();
		assertEquals(environment.getChessBoard().length, 8);
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if ( environment.getChessBoard()[i][j].getActualPieces()!=null) {
					System.out.println("Position X : "+ i + "| Position Y : " + j);
					System.out.println(" Pieces " + environment.getChessBoard()[i][j].getActualPieces().getClass());
				}
			}
		}
	}

}
