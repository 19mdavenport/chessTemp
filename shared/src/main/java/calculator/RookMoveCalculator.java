package calculator;

import java.util.ArrayList;
import java.util.Collection;

public class RookMoveCalculator implements MoveCalculator {
    private final chess.ChessBoard board;
    private final chess.ChessPosition start;
    private final chess.ChessPiece piece;

    public RookMoveCalculator(chess.ChessBoard b, chess.ChessPosition s, chess.ChessPiece p) {
        board = b;
        start = s;
        piece = p;
    }

    public Collection<chess.ChessMove> calculateMoves() {
        Collection<chess.ChessMove> moves = new ArrayList<chess.ChessMove>();
        int row = start.getRow();
        int column = start.getColumn();

        int r = row;
        int c = column;

        while (r < 8) {
            r++;
            chess.ChessPosition p = new chess.ChessPosition(r, c);
            chess.ChessMove m = getMove(board, start, p, piece);
            if (m != null) {
                moves.add(m);
            }
            else {
                break;
            }
        }

        r = row;

        while (c > 1) {
            c--;
            chess.ChessPosition p = new chess.ChessPosition(r, c);
            chess.ChessMove m = getMove(board, start, p, piece);
            if (m != null) {
                moves.add(m);
            }
            else {
                break;
            }
        }

        c = column;

        while (r > 1) {
            r--;
            chess.ChessPosition p = new chess.ChessPosition(r, c);
            chess.ChessMove m = getMove(board, start, p, piece);
            if (m != null) {
                moves.add(m);
            }
            else {
                break;
            }
        }

        r = row;

        while (c < 8) {
            c++;
            chess.ChessPosition p = new chess.ChessPosition(r, c);
            chess.ChessMove m = getMove(board, start, p, piece);
            if (m != null) {
                moves.add(m);
            }
            else {
                break;
            }
        }

        return moves;
    }

    public chess.ChessMove getMove(chess.ChessBoard board, chess.ChessPosition start, chess.ChessPosition end, chess.ChessPiece piece) {
        chess.ChessMove move = null;
        if (isValidMove(board, start, end, piece.getTeamColor())) {
            move = new chess.ChessMove(start, end, piece.getPieceType());
        }
        return move;
    }

    public boolean isValidMove(chess.ChessBoard board, chess.ChessPosition start, chess.ChessPosition end, chess.ChessGame.TeamColor team) {
        chess.ChessPiece piece = board.getPiece(end);
        if (piece == null) {
            return true;
        }
        return piece.getTeamColor() != team;
    }
}
