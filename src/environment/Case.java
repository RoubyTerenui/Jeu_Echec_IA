package environment;

//import com.sun.istack.internal.Nullable;
import pieces.Pieces;

public class Case {

	// --- FIELDS ---

	private int positionY;
	private int positionX;
	//@Nullable
	private Pieces actualPieces;
        boolean endangered;

	// --- CONSTRUCTOR ---

	public Case(int positionX, int positionY,/* @Nullable*/ Pieces actualPieces) {
		super();
		this.positionY = positionY;
		this.positionX = positionX;
		this.actualPieces = actualPieces;
	}

	public Case(Case c)
	{
		super();
		this.positionX = c.positionX;
		this.positionY = c.positionY;
		this.actualPieces = (c.actualPieces != null) ? c.actualPieces.clone() : null;
	}

	// --- GETTERS ---
	public int getPositionY() {
		return positionY;
	}

	public int getPositionX() {
		return positionX;
	}

	public Pieces getActualPieces() {
		return actualPieces;
	}
        public Boolean getEndangerd(){
            return this.endangered;
        }

	// --- SETTERS ---
	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}

	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}

	public void setActualPieces(Pieces actualPieces) {
		
		this.actualPieces = actualPieces;
		// On verifie que la piece est sur la bonne position
		this.actualPieces.setPosX(this.getPositionX());
		this.actualPieces.setPosY(this.getPositionY());
	}

	public void movePieceTo(Case dest) {
		dest.setActualPieces(this.getActualPieces());
		this.actualPieces = null;
	}
        public void setEndangered(Boolean danger){
            this.endangered=danger;
        }
}
