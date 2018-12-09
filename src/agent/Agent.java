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

        for (Pieces piece : getPiecesForOwner(board, player))  {
            for (int[] dest : piece.possibleMoves(board)) {
                Move m = new Move(piece,dest);
                moves.add(m);
                // System.out.println(m);
            }
        }

        return moves;
    }

    // Simple evaluation fonction :
    // Own pieces give positive bonus, other negative
    // TODO: Could be improved with position evaluation (see example at Step 5 of https://medium.freecodecamp.org/simple-chess-ai-step-by-step-1d55a9266977)
    private int evaluateBoard(Case[][] board) {
        int boardScore = 0;

        //System.out.println("score = 0");

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j].getActualPieces() != null){
                    boardScore += (board[i][j].getActualPieces().getOwner() == aiOwner ? 1 : -1) * board[i][j].getActualPieces().getValue();
                }
            }
        }
        return boardScore;
    }

    public Move getRandomMove() {
        List<Move> moves = getPossibleMoves(this.belief, aiOwner);
        return moves.get((int)(Math.random() * (moves.size() + 1)));
    }

    public Move getBestMove() {
        int minimax_depth = 2;
        Move bestMove = null;
        int bestVal = Integer.MIN_VALUE;

        for (Move move : getPossibleMoves(this.belief, this.aiOwner)) {
            System.out.println(move);

            int moveVal = minimaxAlphaBeta(fakeMove(this.belief, move), minimax_depth, aiOwner, Integer.MIN_VALUE, Integer.MAX_VALUE);

            System.out.println("Value : " + moveVal);

            if (moveVal > bestVal)
            {
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
            }
        }

        //debugBoard(board);
        //System.out.println(m.getPiece());
        //System.out.println(m.getPiece().getPosX() + "  " + m.getPiece().getPosY());

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

    //TODO: endgame, timer
}
