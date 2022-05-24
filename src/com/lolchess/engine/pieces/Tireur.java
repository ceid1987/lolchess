package com.lolchess.engine.pieces;

import com.google.common.collect.ImmutableList;
import com.lolchess.engine.Alliance;
import com.lolchess.engine.board.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.lolchess.engine.board.Move.*;

public class Tireur extends Piece {

    private final static int[] CANDIDATE_MOVE_COORDINATES = {-9, -8, -7, -1, 1, 2, 3, 7, 8, 9};

    public Tireur(final Alliance pieceAlliance, final int piecePosition) {
        super(piecePosition, pieceAlliance);
    }


    @Override
    public Collection<Move> calculateLegalMoves(final Board board) {
        final List<Move> legalMoves = new ArrayList<>();

        for (final int currentCandidateOffset : CANDIDATE_MOVE_COORDINATES) {
            final int candidateDestinationCoordinate = this.piecePosition + currentCandidateOffset;
            if (BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)) {
                if (isFirstColumnExclusion(this.piecePosition, currentCandidateOffset) || isEighthColumnExclusion(this.piecePosition, currentCandidateOffset) || isFirstLineExclusion(this.piecePosition, currentCandidateOffset)) {
                    continue;
                }
                final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinate);
                if (!candidateDestinationTile.isTileOccupied()) {
                    legalMoves.add(new MajorMove(board, this, candidateDestinationCoordinate));
                } else {
                    final Piece pieceAtDestination = candidateDestinationTile.getPiece();
                    final Alliance pieceAlliance = pieceAtDestination.getPieceAlliance();
                    if (this.pieceAlliance != pieceAlliance) {
                        legalMoves.add(new AttackMove(board, this, candidateDestinationCoordinate, pieceAtDestination));
                    }
                }
            }
        }
        return ImmutableList.copyOf(legalMoves);
    }

    @Override
    public String toString() {
        return PieceType.TIREUR.toString();
    }

    private static boolean isFirstColumnExclusion(final int currentPosition, final int candidateOffset) {
        return BoardUtils.FIRST_COLUMN[currentPosition] && (candidateOffset == -1 || candidateOffset == -9);
    }

    private static boolean isEighthColumnExclusion(final int currentPosition, final int candidateOffset) {
        return BoardUtils.EIGHTH_COLUMN[currentPosition] && (candidateOffset == 1 || candidateOffset == -7);
    }


 /* UNCOMMENT LATER
    private static boolean isFirstLineExclusion(final int currentPosition, final int candidateOffset) {
        return BoardUtils.FIRST_LINE[currentPosition] && (candidateOffset == -7 || candidateOffset == -8 || candidateOffset == -9);
    }


*/

}