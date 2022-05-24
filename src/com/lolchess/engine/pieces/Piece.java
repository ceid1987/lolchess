package com.lolchess.engine.pieces;

import com.lolchess.engine.Alliance;
import com.lolchess.engine.board.Board;
import com.lolchess.engine.board.Move;

import java.util.Collection;

public abstract class Piece {

    protected final int piecePosition;      //position
    protected final Alliance pieceAlliance; // team

    Piece (final int piecePosition, final Alliance pieceAlliance) {
        this.pieceAlliance = pieceAlliance;
        this.piecePosition = piecePosition;
    }
    public int getPiecePosition(){
        return this.piecePosition;
    }


    public Alliance getPieceAlliance() {
        return this.pieceAlliance;
    }

    public abstract Collection<Move> calculateLegalMoves(final Board board);

    public enum PieceType {
        TIREUR("TI"),
        TANK("TA"),
        ENCHANTEUR("EN"),
        COMBATTANT("CB"),
        MAGE("MG");
        private String pieceName;
        PieceType(final String pieceName) {
            this.pieceName = pieceName;
        }
        @Override
        public String toString() {
            return this.pieceName;
        }
    }
}



