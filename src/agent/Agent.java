package agent;

import environment.Case;
import environment.Move;
import pieces.Pieces;

import java.util.ArrayList;
import java.util.List;

public class Agent {

    private Case[][] belief;
    private Boolean aiOwner = true; //TODO: decide true or false

    public Agent() {
        this.belief = null;
    }

    public void observ(Case[][] board) {
        this.belief = board;
    }

    private List<Pieces> getPiecesForOwner(boolean owner) {
        List<Pieces> pieces = new ArrayList<Pieces>();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (this.belief[i][j].getActualPieces() != null && this.belief[i][j].getActualPieces().getOwner() == owner) {
                    pieces.add(this.belief[i][j].getActualPieces());
                }
            }
        }

        return pieces;
    }

    private List<Move> getPossibleMoves(Case[][] board, Boolean player) {
        List<Move> moves = new ArrayList<Move>();

        for (Pieces piece : getPiecesForOwner(player))  {
            for (int[] dest : piece.possibleMoves(board)) {
                moves.add(new Move(piece, dest));
            }
        }

        return moves;
    }

    // Simple evaluation fonction :
    // Own pieces give positive bonus, other negative
    // TODO: Could be improved with position evaluation (see example at Step 5 of https://medium.freecodecamp.org/simple-chess-ai-step-by-step-1d55a9266977)
    private int evaluateBoard(Case[][] board) {
        int boardScore = 0;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (this.belief[i][j].getActualPieces() != null)
                    boardScore += (this.belief[i][j].getActualPieces().getOwner() == aiOwner ? 1 : -1) * this.belief[i][j].getActualPieces().getValue();
            }
        }

        return boardScore;
    }

    public Move getRandomMove() {
        List<Move> moves = getPossibleMoves(this.belief, aiOwner);
        return moves.get((int)(Math.random() * (moves.size() + 1)));
    }

    public Move getBestMove() {
        return getRandomMove();
    }

    private Case[][] fakeMove(Case[][] board, Move m) {
        Case[][] newBoard = board.clone();

        board[m.getPiece().getPosX()][m.getPiece().getPosY()].movePieceTo(board[m.getDest()[0]][m.getDest()[1]]);

        return newBoard;
    }

    private int minimaxAlphaBeta(Case[][] board, int depth, Boolean maxPlayer, int alpha, int beta) {
        if (depth == 0) // TODO: check endgame
            return evaluateBoard(board);

        int bestValue;

        if (maxPlayer)
        {
            bestValue = Integer.MIN_VALUE;
            for (Move move : getPossibleMoves(board, aiOwner)) {
                int value = minimaxAlphaBeta(fakeMove(board, move), depth - 1, false, alpha, beta);
                bestValue = Math.max(bestValue, value);
                alpha = Math.max(alpha, bestValue);
                if (beta <= alpha)
                    break;
            }
        }
        else {
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

    //TODO: endgame, value to move, timer
}
