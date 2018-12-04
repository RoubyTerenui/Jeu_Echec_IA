package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import environment.Case;
import pieces.Rook;

class Case_Test {

	@Test
	void test() {
		Case test_Case=new Case(0,0,null);
		assertNull(test_Case.getActualPieces());
		test_Case.setActualPieces(new Rook(1,1,false));
		assertNotNull(test_Case.getActualPieces());
		assertEquals(test_Case.getActualPieces().getPosX(), 0);
		assertEquals(test_Case.getActualPieces().getPosY(), 0);
	}	

}
