package com.lolchess.engine.player;

import com.lolchess.engine.Alliance;
import com.lolchess.engine.board.Board;
import com.lolchess.engine.board.Move;
import com.lolchess.engine.board.Move.MoveStatus;
import com.lolchess.engine.board.MoveTransition;
import com.lolchess.engine.pieces.Piece;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.collectingAndThen;

public abstract class Player {

    /**
     * Definition classe joueur
     */

    protected final Board board;
    protected final Collection<Move> legalMoves;


    Player(final Board board,
           final Collection<Move> playerLegals,
           final Collection<Move> opponentLegals) {
        this.board = board;
        this.legalMoves = Collections.unmodifiableCollection(playerLegals);
    }


    private boolean hasEscapeMoves() {
        return this.legalMoves.stream()
                .anyMatch(move -> makeMove(move)
                        .getMoveStatus().isDone());
    }

    public Collection<Move> getLegalMoves() {
        return this.legalMoves;
    }

    static Collection<Move> calculateAttacksOnTile(final int tile,
                                                   final Collection<Move> moves) {
        return moves.stream()
                .filter(move -> move.getDestinationCoordinate() == tile)
                .collect(collectingAndThen(Collectors.toList(), Collections::unmodifiableList));
    }

    public MoveTransition makeMove(final Move move) {
        if (!this.legalMoves.contains(move)) {
            return new MoveTransition(this.board, this.board, move, MoveStatus.ILLEGAL_MOVE);
        }
        final Board transitionedBoard = move.execute();
        return new MoveTransition(this.board, transitionedBoard, move, MoveStatus.DONE);
    }

    public MoveTransition unMakeMove(final Move move) {
        return new MoveTransition(this.board, move.undo(), move, MoveStatus.DONE);
    }

    public abstract Collection<Piece> getActivePieces();
    public abstract Alliance getAlliance();
    public abstract Player getOpponent();

}