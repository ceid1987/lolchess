package com.lolchess.engine.pieces;

import com.lolchess.engine.Alliance;
import com.lolchess.engine.board.Board;
import com.lolchess.engine.board.Move;

import java.util.Collection;

public abstract class Piece {
    final PieceType pieceType;
    final int piecePosition;      //position
    final Alliance pieceAlliance; // team
    private final int privateHashCode;


    private final boolean isFirstMove;

    Piece(final PieceType type, final Alliance alliance, final int piecePosition, final boolean isFirstMove) {
        this.pieceType = type;
        this.pieceAlliance = alliance;
        this.piecePosition = piecePosition;
        this.isFirstMove = isFirstMove;
        this.privateHashCode = computeHashCode();
    }

    public Alliance getPieceAlliance() {
        return this.pieceAlliance;
    }

    public PieceType getPieceType() {
        return this.pieceType;
    }

    public int getPiecePosition() {
        return this.piecePosition;
    }

    public boolean isFirstMove() {
        return this.isFirstMove;
    }

    public int getPieceValue() {
        return this.pieceType.getPieceValue();
    }

    public abstract Piece movePiece(Move move);
    public abstract Collection<Move> calculateLegalMoves(final Board board);

    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Piece)) {
            return false;
        }
        final Piece otherPiece = (Piece) other;
        return this.piecePosition == otherPiece.piecePosition && this.pieceType == otherPiece.pieceType &&
                this.pieceAlliance == otherPiece.pieceAlliance && this.isFirstMove == otherPiece.isFirstMove;
    }

    @Override
    public int hashCode() {
        return this.privateHashCode;
    }

    private int computeHashCode() {
        int result = this.pieceType.hashCode();
        result = 31 * result + this.pieceAlliance.hashCode();
        result = 31 * result + this.piecePosition;
        result = 31 * result + (this.isFirstMove ? 1 : 0);
        return result;
    }

    public enum PieceType {

        TANK(100, "TA"),
        COMBATTANT(300, "CB"),
        ENCHANTEUR(330, "EN"),
        MAGE(500, "MG"),
        TIREUR(900, "TI");

        private final int value;
        private final String pieceName;

        public int getPieceValue() {
            return this.value;
        }

        @Override
        public String toString() {
            return this.pieceName;
        }

        PieceType(final int val,
                  final String pieceName) {
            this.value = val;
            this.pieceName = pieceName;
        }

    }

}
