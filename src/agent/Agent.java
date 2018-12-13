package agent;

import environment.Case;
import environment.Move;
import pieces.Bishop;
import pieces.King;
import pieces.Pawn;
import pieces.Pieces;
import pieces.Rook;

import java.util.ArrayList;
import java.util.List;

public class Agent {

	private Case[][] belief;
	private Boolean aiOwner = true; // TODO: decide true or false

	public Agent() {
		this.belief = null;
	}

	public void observ(Case[][] board) {
		this.belief = board;
	}

	private List<Pieces> getPiecesForOwner(Case[][] board, boolean owner) {
		List<Pieces> pieces = new ArrayList<Pieces>();

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (board[i][j].getActualPieces() != null && board[i][j].getActualPieces().getOwner() == owner) {
					pieces.add(board[i][j].getActualPieces());
				}
			}
		}

		return pieces;
	}

	private List<Move> getPossibleMoves(Case[][] board, Boolean player) {
		List<Move> moves = new ArrayList<Move>();

		for (Pieces piece : getPiecesForOwner(board, player)) {
			for (int[] dest : piece.possibleMoves(board)) {
				Move m = new Move(piece, dest);
				moves.add(m);
				// System.out.println(m);
			}
			if (piece.getClass().equals(Pawn.class))
			{
				for (int[] dest : ((Pawn) piece).passTakenMove(board)) {
					moves.add(new Move(piece, dest));
				}
			}
			else if (piece.getClass().equals(King.class)) {
				for (int[] dest : ((King) piece).roqueLegal(board)) {
					moves.add(new Move(piece, dest));
				}
			}
		}

		return moves;
	}

	// Simple evaluation fonction :
	// Own pieces give positive bonus, other negative
	// TODO: Could be improved with position evaluation (see example at Step 5 of
	// https://medium.freecodecamp.org/simple-chess-ai-step-by-step-1d55a9266977)
	private int evaluateBoard(Case[][] board) {
		int boardScore = 0;

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (board[i][j].getActualPieces() != null) {
					// Maximise le nombre de coups possible / Minimise le nombre de coup pour
					// l'adversaire
					boardScore += (board[i][j].getActualPieces().getOwner() == aiOwner ? 1 : -1)
							* board[i][j].getActualPieces().possibleMoves(board).size();
					// Maximise la valeur des pieces pour l'owner et minimiser la valeur pour
					// l'adversaire
					boardScore += (board[i][j].getActualPieces().getOwner() == aiOwner ? 1 : -1)
							* board[i][j].getActualPieces().getValue();
					// Choisir les meilleurs positions des pieces pour l'owner et minimiser la valeur pour
					// l'adversaire
					boardScore += (board[i][j].getActualPieces().getOwner() == aiOwner ? 1 : -1)
							*  ((int)2*board[i][j].getActualPieces().getGrid()[i][j]);
					// Reduire le nombre de piece en danger
					boardScore += (board[i][j].getActualPieces().getOwner() == aiOwner ? 1 : -1)
							* (board[i][j].getActualPieces().isEndangeredPieces() == true ? -1 : 1);
					// Favoriser le centre pour les pions centraux
					if (this.aiOwner) {
						if (board[3][3].getActualPieces() != null) {
							if (board[3][3].getActualPieces().getClass() == Pawn.class) {
								boardScore++;

							}
						}
						if (board[4][3].getActualPieces() != null) {
							if (board[4][3].getActualPieces().getClass() == Pawn.class) {
								boardScore++;
							}
						}
					} else {
						if (board[3][4].getActualPieces() != null) {
							if (board[3][4].getActualPieces().getClass() == Pawn.class) {
								boardScore++;

							}
						}
						if (board[4][4].getActualPieces() != null) {
							if (board[4][4].getActualPieces().getClass() == Pawn.class) {
								boardScore++;
							}
						}
					}

				}
			}
			// Faciliter le petit roque et le rendre plus difficile pour l'adversaire
			if (i % 7 == 0) {
				System.out.println(i);
				if (board[i][7].getActualPieces() != null && board[i][4].getActualPieces() != null) {
					if (board[i][7].getActualPieces().getClass() == Rook.class
							&& board[i][4].getActualPieces().getClass() == King.class) {
						if (((Rook) board[i][7].getActualPieces()).getUnmoved()
								&& ((King) board[i][4].getActualPieces()).getUnmoved()) {
							for (int j = 1; j <= 2; j++) {
								if (board[i][4 + j].getActualPieces() == null) {
									if (i == 0) {
										boardScore += 1;
									} else {
										boardScore -= 1;
									}
								}
							}
						}
					}
				}
			}
		}
		// Fonction qui maximise l'attaque du centre (sauf pour le roi)

		return boardScore;
	}

	public Move getRandomMove() {
		List<Move> moves = getPossibleMoves(this.belief, aiOwner);
		return moves.get((int) (Math.random() * (moves.size() + 1)));
	}

	public Move getBestMove(int minimax_depth) {
		Move bestMove = null;
		int bestVal = Integer.MIN_VALUE;

		for (Move move : getPossibleMoves(this.belief, this.aiOwner)) {
			System.out.println(move);

			int moveVal = minimaxAlphaBeta(fakeMove(this.belief, move), minimax_depth, aiOwner, Integer.MIN_VALUE,
					Integer.MAX_VALUE);

			System.out.println("Value : " + moveVal);

			if (moveVal > bestVal) {
				bestVal = moveVal;
				bestMove = move;
			}
		}
		return bestMove;
	}

	private Case[][] fakeMove(Case[][] board, Move m) {
		Case[][] newBoard = new Case[8][8];

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				newBoard[i][j] = new Case(board[i][j]);
				if (newBoard[i][j].getActualPieces() != null && newBoard[i][j].getActualPieces().getClass().equals(Pawn.class) &&
						(((Pawn) newBoard[i][j].getActualPieces()).isCanBepassTaken()))
				{
					((Pawn) newBoard[i][j].getActualPieces()).setCanBepassTaken(false);
				}
			}
		}

		if (m.getPiece().getClass().equals(Pawn.class))
		{
			if (Math.abs(m.getPiece().getPosX() - m.getDest()[0]) == 1 && Math.abs(m.getPiece().getPosY() - m.getDest()[1]) == 1) {
				Pieces adjacent = board[m.getPiece().getPosX()][m.getDest()[1]].getActualPieces(); // use old board because CanBePassTaken is cleared during init
				if (adjacent != null && adjacent.getClass().equals(Pawn.class) && ((Pawn)adjacent).isCanBepassTaken()) {
					newBoard[adjacent.getPosX()][adjacent.getPosX()].setActualPieces(null);
				}
			}
			else if (Math.abs(m.getPiece().getPosX() - m.getDest()[0]) == 2)
			{
				((Pawn) m.getPiece()).setCanBepassTaken(true);
			}
		}
		else if (m.getPiece().getClass().equals(King.class)) {
			((King) newBoard[m.getPiece().getPosX()][m.getPiece().getPosY()].getActualPieces()).setUnmoved(false);
			if (Math.abs(m.getPiece().getPosY() - m.getDest()[1]) > 1) {
				System.out.println("Roque : " + m);
				if (m.getDest()[1] == 6) {
					newBoard[m.getPiece().getPosX()][7].movePieceTo(newBoard[m.getPiece().getPosX()][5]);
				}
				else if (m.getDest()[1] == 2) {
					newBoard[m.getPiece().getPosX()][0].movePieceTo(newBoard[m.getPiece().getPosX()][3]);
				}
			}
		}
		else if (m.getPiece().getClass().equals(Rook.class)) {
			((Rook) newBoard[m.getPiece().getPosX()][m.getPiece().getPosY()].getActualPieces()).setUnmoved(false);
		}

		// debugBoard(board);
		// System.out.println(m.getPiece());
		// System.out.println(m.getPiece().getPosX() + " " + m.getPiece().getPosY());

		newBoard[m.getPiece().getPosX()][m.getPiece().getPosY()].movePieceTo(newBoard[m.getDest()[0]][m.getDest()[1]]);

		return newBoard;
	}

	private void debugBoard(Case[][] board) {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				System.out.print(board[i][j].getActualPieces() + ",");
			}
			System.out.println();
		}
	}

	private int minimaxAlphaBeta(Case[][] board, int depth, Boolean maxPlayer, int alpha, int beta) {
		if (depth == 0) // TODO: check endgame
			return evaluateBoard(board);

		int bestValue;

		if (maxPlayer) {
			bestValue = Integer.MIN_VALUE;
			for (Move move : getPossibleMoves(board, aiOwner)) {
				int value = minimaxAlphaBeta(fakeMove(board, move), depth - 1, false, alpha, beta);
				bestValue = Math.max(bestValue, value);
				alpha = Math.max(alpha, bestValue);
				if (beta <= alpha)
					break;
			}
		} else {
			bestValue = Integer.MAX_VALUE;
			for (Move move : getPossibleMoves(board, !aiOwner)) {
				int value = minimaxAlphaBeta(fakeMove(board, move), depth - 1, true, alpha, beta);
				bestValue = Math.min(bestValue, value);
				beta = Math.min(beta, bestValue);
				if (beta <= alpha)
					break;
			}
		}
		return bestValue;
	}
	// TODO: endgame, timer
	public class threadRunMinMaxProfondeur implements Runnable{
		private int depth;
		private int id;
		@Override
		public void run() {
			// TODO Auto-generated method stub
			
		}

	}
}

