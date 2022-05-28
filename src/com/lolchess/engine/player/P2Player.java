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

import static com.lolchess.engine.pieces.Piece.PieceType.MAGE;

public final class P2Player extends Player {

    /**
     * Joueur 2 (En bas)
     * @param board
     * @param p1StandardLegals
     * @param p2StandardLegals
     */

    public P2Player(final Board board,
                       final Collection<Move> p1StandardLegals,
                       final Collection<Move> p2StandardLegals) {
        super(board, p1StandardLegals, p2StandardLegals);
    }



    @Override
    public P1Player getOpponent() {
        return this.board.p1Player();
    }

    @Override
    public Collection<Piece> getActivePieces() {
        return this.board.getP2Pieces();
    }

    @Override
    public Alliance getAlliance() {
        return Alliance.P2;
    }

    @Override
    public String toString() {
        return Alliance.P2.toString();
    }

}