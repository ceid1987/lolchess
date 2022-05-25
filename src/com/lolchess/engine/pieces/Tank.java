package com.lolchess.engine.pieces;

import com.google.common.collect.ImmutableList;
import com.lolchess.engine.Alliance;
import com.lolchess.engine.board.Board;
import com.lolchess.engine.board.BoardUtils;
import com.lolchess.engine.board.Move;
import com.lolchess.engine.board.Tile;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.lolchess.engine.board.Move.*;

public class Tank extends Piece{

    private final static int[] CANDIDATE_MOVE_COORDINATE = {7, 8, 9, 16};

    public Tank(final int piecePosition, final Alliance pieceAlliance) {
        super(PieceType.TANK, piecePosition, pieceAlliance, 150);
    }

    @Override
    public Collection<Move> calculateLegalMoves(final Board board){

        final List<Move> legalMoves = new ArrayList<>();

        for(final int currentCandidateOffset : CANDIDATE_MOVE_COORDINATE) {
           final int candidateDestinationCoordinate = this.piecePosition + (this.getPieceAlliance().getDirection() * currentCandidateOffset);
            if(!BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)) {
                continue;
            }
            if(currentCandidateOffset == 8 && board.getTile(candidateDestinationCoordinate).isTileOccupied()) {
                //fix later (promotion)
                legalMoves.add(new Move.MajorMove(board, this, candidateDestinationCoordinate));
            } else if (currentCandidateOffset == 16 && this.isFirstMove() &&
                    (BoardUtils.SECOND_ROW[this.piecePosition] && this.getPieceAlliance().isP2()) ||
                    (BoardUtils.SEVENTH_ROW[this.piecePosition] && this.getPieceAlliance().isP1())) {
                final int behindCandidateDestinationCoordinate = this.piecePosition + (this.pieceAlliance.getDirection() * 8);

                if(!board.getTile(behindCandidateDestinationCoordinate).isTileOccupied() &&
                !board.getTile(candidateDestinationCoordinate).isTileOccupied()) {
                    legalMoves.add(new MajorMove(board, this, candidateDestinationCoordinate));
                }

            } else if(currentCandidateOffset == 7 &&
                    !((BoardUtils.EIGHTH_COLUMN[this.piecePosition] && this.pieceAlliance.isP1() ||
                    (BoardUtils.FIRST_COLUMN[this.piecePosition] && this.pieceAlliance.isP2())))) {
                if(board.getTile(candidateDestinationCoordinate).isTileOccupied()) {
                    final Piece pieceOnCandidate = board.getTile(candidateDestinationCoordinate).getPiece();
                    if(this.pieceAlliance != pieceOnCandidate.getPieceAlliance()) {
                        //TODO
                        legalMoves.add(new MajorMove(board, this, candidateDestinationCoordinate));
                    }
                }


            } else if(currentCandidateOffset == 9 &&
                    !((BoardUtils.FIRST_COLUMN[this.piecePosition] && this.pieceAlliance.isP1() || (BoardUtils.EIGHTH_COLUMN[this.piecePosition] && this.pieceAlliance.isP2())))) {
                if(board.getTile(candidateDestinationCoordinate).isTileOccupied()) {
                    final Piece pieceOnCandidate = board.getTile(candidateDestinationCoordinate).getPiece();
                    if (this.pieceAlliance != pieceOnCandidate.getPieceAlliance()) {
                        //TODO
                        legalMoves.add(new MajorMove(board, this, candidateDestinationCoordinate));
                    }
                }
            }

        }

        return ImmutableList.copyOf(legalMoves);
    }

    @Override
    public Tank movePiece(final Move move){
        return new Tank(move.getDestinationCoordinate(), move.getMovedPiece().getPieceAlliance());
    }

    @Override
    public String toString(){
        return PieceType.TANK.toString();
    }

}
