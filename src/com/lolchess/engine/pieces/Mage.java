package com.lolchess.engine.pieces;

import com.lolchess.engine.Alliance;
import com.lolchess.engine.board.Board;
import com.lolchess.engine.board.BoardUtils;
import com.lolchess.engine.board.Move;
import com.lolchess.engine.board.Move.MajorAttackMove;
import com.lolchess.engine.board.Move.MajorMove;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public final class Mage extends Piece {

    private final static int[] CANDIDATE_MOVE_COORDINATES = { -8, -1, 1, 8 };

    public Mage(final Alliance alliance, final int piecePosition) {
        super(PieceType.MAGE, alliance, piecePosition, true);
    }

    public Mage(final Alliance alliance,
                final int piecePosition,
                final boolean isFirstMove) {
        super(PieceType.MAGE, alliance, piecePosition, isFirstMove);
    }

    @Override
    public Collection<Move> calculateLegalMoves(final Board board) {
        final List<Move> legalMoves = new ArrayList<>();
        for (final int currentCandidateOffset : CANDIDATE_MOVE_COORDINATES) {
            int candidateDestinationCoordinate = this.piecePosition;
            while (BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)) {
                if (isColumnExclusion(currentCandidateOffset, candidateDestinationCoordinate)) {
                    break;
                }
                candidateDestinationCoordinate += currentCandidateOffset;
                if (BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)) {
                    final Piece pieceAtDestination = board.getPiece(candidateDestinationCoordinate);
                    if (pieceAtDestination == null) {
                        legalMoves.add(new MajorMove(board, this, candidateDestinationCoordinate));
                    } else {
                        final Alliance pieceAtDestinationAllegiance = pieceAtDestination.getPieceAlliance();
                        if (this.pieceAlliance != pieceAtDestinationAllegiance) {
                            legalMoves.add(new MajorAttackMove(board, this, candidateDestinationCoordinate,
                                    pieceAtDestination));
                        }
                        break;
                    }
                }
            }
        }
        return Collections.unmodifiableList(legalMoves);
    }

    @Override
    public Mage movePiece(final Move move) {
        return PieceUtils.INSTANCE.getMovedMage(move.getMovedPiece().getPieceAlliance(), move.getDestinationCoordinate());
    }

    @Override
    public String toString() {
        return this.pieceType.toString();
    }

    private static boolean isColumnExclusion(final int currentCandidate,
                                             final int candidateDestinationCoordinate) {
        return (BoardUtils.INSTANCE.FIRST_COLUMN.get(candidateDestinationCoordinate) && (currentCandidate == -1)) ||
                (BoardUtils.INSTANCE.EIGHTH_COLUMN.get(candidateDestinationCoordinate) && (currentCandidate == 1));
    }

}