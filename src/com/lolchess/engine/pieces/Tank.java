package com.lolchess.engine.pieces;

import com.lolchess.engine.Alliance;
import com.lolchess.engine.board.Board;
import com.lolchess.engine.board.BoardUtils;
import com.lolchess.engine.board.Move;
import com.lolchess.engine.board.Move.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public final class Tank
        extends Piece {

    /**
     * Tank et methodes uniques
     */

    private final static int[] CANDIDATE_MOVE_COORDINATES = {8, 16, 7, 9};

    public Tank(final Alliance allegiance,
                final int piecePosition) {
        super(PieceType.TANK, allegiance, piecePosition, true);
    }

    public Tank(final Alliance alliance,
                final int piecePosition,
                final boolean isFirstMove) {
        super(PieceType.TANK, alliance, piecePosition, isFirstMove);
    }


    @Override
    public Collection<Move> calculateLegalMoves(final Board board) {
        final List<Move> legalMoves = new ArrayList<>();
        for (final int currentCandidateOffset : CANDIDATE_MOVE_COORDINATES) {
            int candidateDestinationCoordinate =
                    this.piecePosition + (this.pieceAlliance.getDirection() * currentCandidateOffset);
            if (!BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)) {
                continue;
            }
            if (currentCandidateOffset == 8 && board.getPiece(candidateDestinationCoordinate) == null) {
                if (this.pieceAlliance.isTankPromotionSquare(candidateDestinationCoordinate)) {
                    legalMoves.add(new TankPromotion(
                            new TankMove(board, this, candidateDestinationCoordinate), PieceUtils.INSTANCE.getMovedTireur(this.pieceAlliance, candidateDestinationCoordinate)));
                    legalMoves.add(new TankPromotion(
                            new TankMove(board, this, candidateDestinationCoordinate), PieceUtils.INSTANCE.getMovedMage(this.pieceAlliance, candidateDestinationCoordinate)));
                    legalMoves.add(new TankPromotion(
                            new TankMove(board, this, candidateDestinationCoordinate), PieceUtils.INSTANCE.getMovedEnchanteur(this.pieceAlliance, candidateDestinationCoordinate)));
                    legalMoves.add(new TankPromotion(
                            new TankMove(board, this, candidateDestinationCoordinate), PieceUtils.INSTANCE.getMovedCombattant(this.pieceAlliance, candidateDestinationCoordinate)));
                }
                else {
                    legalMoves.add(new TankMove(board, this, candidateDestinationCoordinate));
                }
            }
            else if (currentCandidateOffset == 16 && this.isFirstMove() &&
                    ((BoardUtils.INSTANCE.SECOND_ROW.get(this.piecePosition) && this.pieceAlliance.isP1()) ||
                            (BoardUtils.INSTANCE.SEVENTH_ROW.get(this.piecePosition) && this.pieceAlliance.isP2()))) {
                final int behindCandidateDestinationCoordinate =
                        this.piecePosition + (this.pieceAlliance.getDirection() * 8);
                if (board.getPiece(candidateDestinationCoordinate) == null &&
                        board.getPiece(behindCandidateDestinationCoordinate) == null) {
                    legalMoves.add(new TankJump(board, this, candidateDestinationCoordinate));
                }
            }
            else if (currentCandidateOffset == 7 &&
                    !((BoardUtils.INSTANCE.EIGHTH_COLUMN.get(this.piecePosition) && this.pieceAlliance.isP2()) ||
                            (BoardUtils.INSTANCE.FIRST_COLUMN.get(this.piecePosition) && this.pieceAlliance.isP1()))) {
                if(board.getPiece(candidateDestinationCoordinate) != null) {
                    final Piece pieceOnCandidate = board.getPiece(candidateDestinationCoordinate);
                    if (this.pieceAlliance != pieceOnCandidate.getPieceAlliance()) {
                        if (this.pieceAlliance.isTankPromotionSquare(candidateDestinationCoordinate)) {
                            legalMoves.add(new TankPromotion(
                                    new TankAttackMove(board, this, candidateDestinationCoordinate, pieceOnCandidate), PieceUtils.INSTANCE.getMovedTireur(this.pieceAlliance, candidateDestinationCoordinate)));
                            legalMoves.add(new TankPromotion(
                                    new TankAttackMove(board, this, candidateDestinationCoordinate, pieceOnCandidate), PieceUtils.INSTANCE.getMovedMage(this.pieceAlliance, candidateDestinationCoordinate)));
                            legalMoves.add(new TankPromotion(
                                    new TankAttackMove(board, this, candidateDestinationCoordinate, pieceOnCandidate), PieceUtils.INSTANCE.getMovedCombattant(this.pieceAlliance, candidateDestinationCoordinate)));
                            legalMoves.add(new TankPromotion(
                                    new TankAttackMove(board, this, candidateDestinationCoordinate, pieceOnCandidate), PieceUtils.INSTANCE.getMovedCombattant(this.pieceAlliance, candidateDestinationCoordinate)));
                        }
                        else {
                            legalMoves.add(
                                    new TankAttackMove(board, this, candidateDestinationCoordinate, pieceOnCandidate));
                        }
                    }
                } else if (board.getEnPassantTank() != null && board.getEnPassantTank().getPiecePosition() ==
                        (this.piecePosition + (this.pieceAlliance.getOppositeDirection()))) {
                    final Piece pieceOnCandidate = board.getEnPassantTank();
                    if (this.pieceAlliance != pieceOnCandidate.getPieceAlliance()) {
                        legalMoves.add(
                                new TankEnPassantAttack(board, this, candidateDestinationCoordinate, pieceOnCandidate));

                    }
                }
            }
            else if (currentCandidateOffset == 9 &&
                    !((BoardUtils.INSTANCE.FIRST_COLUMN.get(this.piecePosition) && this.pieceAlliance.isP2()) ||
                            (BoardUtils.INSTANCE.EIGHTH_COLUMN.get(this.piecePosition) && this.pieceAlliance.isP1()))) {
                if(board.getPiece(candidateDestinationCoordinate) != null) {
                    if (this.pieceAlliance !=
                            board.getPiece(candidateDestinationCoordinate).getPieceAlliance()) {
                        if (this.pieceAlliance.isTankPromotionSquare(candidateDestinationCoordinate)) {
                            legalMoves.add(new TankPromotion(
                                    new TankAttackMove(board, this, candidateDestinationCoordinate,
                                            board.getPiece(candidateDestinationCoordinate)), PieceUtils.INSTANCE.getMovedTireur(this.pieceAlliance, candidateDestinationCoordinate)));
                            legalMoves.add(new TankPromotion(
                                    new TankAttackMove(board, this, candidateDestinationCoordinate,
                                            board.getPiece(candidateDestinationCoordinate)), PieceUtils.INSTANCE.getMovedMage(this.pieceAlliance, candidateDestinationCoordinate)));
                            legalMoves.add(new TankPromotion(
                                    new TankAttackMove(board, this, candidateDestinationCoordinate,
                                            board.getPiece(candidateDestinationCoordinate)), PieceUtils.INSTANCE.getMovedEnchanteur(this.pieceAlliance, candidateDestinationCoordinate)));
                            legalMoves.add(new TankPromotion(
                                    new TankAttackMove(board, this, candidateDestinationCoordinate,
                                            board.getPiece(candidateDestinationCoordinate)), PieceUtils.INSTANCE.getMovedCombattant(this.pieceAlliance, candidateDestinationCoordinate)));
                        }
                        else {
                            legalMoves.add(
                                    new TankAttackMove(board, this, candidateDestinationCoordinate,
                                            board.getPiece(candidateDestinationCoordinate)));
                        }
                    }
                } else if (board.getEnPassantTank() != null && board.getEnPassantTank().getPiecePosition() ==
                        (this.piecePosition - (this.pieceAlliance.getOppositeDirection()))) {
                    final Piece pieceOnCandidate = board.getEnPassantTank();
                    if (this.pieceAlliance != pieceOnCandidate.getPieceAlliance()) {
                        legalMoves.add(
                                new TankEnPassantAttack(board, this, candidateDestinationCoordinate, pieceOnCandidate));

                    }
                }
            }
        }
        return Collections.unmodifiableList(legalMoves);
    }

    @Override
    public String toString() {
        return this.pieceType.toString();
    }

    @Override
    public Tank movePiece(final Move move) {
        return PieceUtils.INSTANCE.getMovedTank(move.getMovedPiece().getPieceAlliance(), move.getDestinationCoordinate());
    }

}