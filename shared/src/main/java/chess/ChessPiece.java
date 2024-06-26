package chess;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

/**
 * Represents a single chess piece
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessPiece {
    private ChessGame.TeamColor pieceColor;
    private ChessPiece.PieceType pieceType;


    public ChessPiece(ChessGame.TeamColor pieceColor, ChessPiece.PieceType type) {
        this.pieceColor = pieceColor;
        this.pieceType = type;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChessPiece that)) return false;
        return pieceColor == that.pieceColor && pieceType == that.pieceType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pieceColor, pieceType);
    }

    /**
     * @return Which team this chess piece belongs to
     */
    public ChessGame.TeamColor getTeamColor() {
        return pieceColor;
    }

    /**
     * @return which type of chess piece this piece is
     */
    public PieceType getPieceType() {
        return pieceType;
    }

    public void setPieceType(PieceType pieceType){
        this.pieceType = pieceType;

    }

    /**
     * Calculates all the positions a chess piece can move to
     * Does not take into account moves that are illegal due to leaving the king in
     * danger
     *
     * @return Collection of valid moves
     */
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        switch (pieceType){

            case PieceType.KNIGHT:
                KnightMoves knight = new KnightMoves(board,myPosition);
                return knight.calculator();

            case PieceType.KING:
                KingMoves king = new KingMoves(board,myPosition);
                return king.calculator();

            case PieceType.ROOK:
                RookMoves rook = new RookMoves(board,myPosition);
                return rook.calculator();

            case PieceType.BISHOP:
                BishopMoves bishop = new BishopMoves(board,myPosition);
                return bishop.calculator();

            case PieceType.QUEEN:
                Collection<ChessMove> availableMoves = new HashSet<ChessMove>();

                RookMoves queenRook = new RookMoves(board,myPosition);
                BishopMoves queenBishop = new BishopMoves(board,myPosition);

                availableMoves.addAll(queenRook.calculator());
                availableMoves.addAll(queenBishop.calculator());
                return availableMoves;

            case PieceType.PAWN:
                PawnMoves pawn = new PawnMoves(board,myPosition);
                return pawn.calculator();
        }
        return null;
    }
}
