package com.lolchess.engine.player;

import com.lolchess.engine.Alliance;
import com.lolchess.engine.board.Board;
import com.lolchess.engine.board.BoardUtils;
import com.lolchess.engine.board.Move;
import com.lolchess.engine.pieces.Piece;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public final class P1Player extends Player {

    /**
     * Joueur 1 (En haut)
     * @param board
     * @param p2StandardLegals
     * @param p1StandardLegals
     */

    public P1Player(final Board board,
                    final Collection<Move> p2StandardLegals,
                    final Collection<Move> p1StandardLegals) {
        super(board, p1StandardLegals, p2StandardLegals);
    }


    @Override
    public P2Player getOpponent() {
        return this.board.p2Player();
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
    public String toString() {
        return Alliance.P1.toString();
    }

}
