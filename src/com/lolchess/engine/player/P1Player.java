package com.lolchess.engine.player;

import com.lolchess.engine.Alliance;
import com.lolchess.engine.board.Board;
import com.lolchess.engine.board.Move;
import com.lolchess.engine.pieces.Piece;

import java.util.Collection;

public class P1Player extends Player {
    public P1Player(final Board board, final Collection<Move> p1StandardLegalMoves, final Collection<Move> p2StandardLegalMoves) {

        super(board, p1StandardLegalMoves, p2StandardLegalMoves);
    }

    @Override
    public Collection<Piece> getActivePieces() {
        return this.board.getP1Pieces();
    }

    @Override
    public Alliance getAlliance() {
        return Alliance.P1;
    }

    @Override
    public Player getOpponent() {
        return this.board.p2Player();
    }
}

