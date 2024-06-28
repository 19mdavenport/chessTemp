package chess;

import calculator.KingMoveCalculator;
import calculator.KnightMoveCalculator;
import calculator.RookMoveCalculator;
import calculator.QueenMoveCalculator;
import calculator.PawnMoveCalculator;
import calculator.BishopMoveCalculator;

import java.util.Collection;

/**
 * Represents a single chess piece
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessPiece {

    private final ChessGame.TeamColor color;
    private ChessPiece.PieceType type;

    public ChessPiece(ChessGame.TeamColor pieceColor, ChessPiece.PieceType pieceType) {
        color = pieceColor;
        type = pieceType;
    }

    /**
     * The various different chess piece options
     */
    public enum PieceType {
        KING,
        QUEEN,
        BISHOP,
        KNIGHT,
        ROOK,
        PAWN
    }

    /**
     * @return Which team this chess piece belongs to
     */
    public ChessGame.TeamColor getTeamColor() {
        return color;
    }

    /**
     * @return which type of chess piece this piece is
     */
    public PieceType getPieceType() {
        return type;
    }

    /**
     * Calculates all the positions a chess piece can move to
     * Does not take into account moves that are illegal due to leaving the king in
     * danger
     *
     * @return Collection of valid moves
     */
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        if (type == PieceType.KNIGHT) {
            KnightMoveCalculator p = new KnightMoveCalculator(board, myPosition, this);
            return p.calculateMoves();
        }
        else if (type == PieceType.ROOK) {
            RookMoveCalculator p = new RookMoveCalculator(board, myPosition, this);
            return p.calculateMoves();
        }
        else if (type == PieceType.PAWN) {
            PawnMoveCalculator p = new PawnMoveCalculator(board, myPosition, this);
            return p.calculateMoves();
        }
        else if (type == PieceType.KING) {
            KingMoveCalculator p = new KingMoveCalculator(board, myPosition, this);
            return p.calculateMoves();
        }
        else if (type == PieceType.BISHOP) {
            BishopMoveCalculator p = new BishopMoveCalculator(board, myPosition, this);
            return p.calculateMoves();
        }
        else {
            QueenMoveCalculator p = new QueenMoveCalculator(board, myPosition, this);
            return p.calculateMoves();
        }
    }
}
