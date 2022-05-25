package com.lolchess.engine.board;

import com.lolchess.engine.pieces.Piece;
import com.lolchess.engine.board.Board.Builder;

public abstract class Move {
    final Board board;
    final Piece movedPiece;
    final int destinationCoordinate;
    private static final Move NULL_MOVE = new NullMove();

    private Move(final Board board,
         final Piece movedPiece,
         final int destinationCoordinate) {
        this.board = board;
        this.movedPiece = movedPiece;
        this.destinationCoordinate = destinationCoordinate;
    }

    public int getCurrentCoordinate(){
        return this.getMovedPiece().getPiecePosition();
    }


    public int getDestinationCoordinate() {
        return this.destinationCoordinate;
    }

    public Piece getMovedPiece(){
        return this.movedPiece;
    }

    public abstract Board execute();

    public static final class MajorMove extends Move {
        public MajorMove(final Board board,
                         final Piece movedPiece,
                         final int destinationCoordinate) {
            super(board, movedPiece, destinationCoordinate);
        }

        @Override
        public Board execute() {
            final Builder builder = new Builder();

            for(final Piece piece : this.board.currentPlayer().getActivePieces()){
                if(!this.movedPiece.equals(piece)){
                    builder.setPiece(piece);
                }
            }
            for(final Piece piece : this.board.currentPlayer().getOpponent().getActivePieces()){
                builder.setPiece(piece);
            }
            //permer de bouger les pieces
            builder.setPiece(this.movedPiece.movePiece(this));
            builder.setMoveMaker(this.board.currentPlayer().getOpponent().getAlliance());
            return builder.build();
        }
    }

    public static class AttackMove extends Move {

        final Piece attackedPiece;

        public AttackMove(final Board board,
                          final Piece movedPiece,
                          final int destinationCoordinate,
                          final Piece attackedPiece) {
            super(board, movedPiece, destinationCoordinate);
            this.attackedPiece = attackedPiece;
        }
        @Override
        public Board execute(){
            return null;
        }
    }

    public static abstract class TankMove extends Move {
        public TankMove(final Board board,
                        final Piece movedPiece,
                        final int destinationCoordinate){
                        super(board, movedPiece, destinationCoordinate);
           }
      }

        public static class TankAttackMove extends AttackMove {
         public TankAttackMove(final Board board,
                                final Piece movedPiece,
                                final int destinationCoordinate,
                               final Piece attackedPiece){
             super(board, movedPiece, destinationCoordinate, attackedPiece);
         }
      }
      public static class TankEnPassantAttack extends TankAttackMove {

        public TankEnPassantAttack(final Board board,
                               final Piece pieceMoved,
                                final int destinationCoordinate,
                                final Piece pieceAttacked) {
           super(board, pieceMoved, destinationCoordinate, pieceAttacked);
          }
    }

        public static abstract class TankJump extends Move{
           public TankJump(final Board board,
                           final Piece movedPiece,
                           final int destinationCoordinate){
             super(board, movedPiece, destinationCoordinate);
           }
       }

        public static final class NullMove extends Move {
            public NullMove() {
                super(null, null, -1);
            }
            @Override
            public Board execute(){
            throw new RuntimeException("cannot execute the null move!");
            }
        }


        public class MoveFactory {
            private MoveFactory(){
                throw new RuntimeException("not instantiable!");
            }
            public static Move createMove(final Board board,
                                          final int currentCoordinate,
                                          final int destinationCoordinate){
                for(final Move move : board.AllLegalMoves()){
                    if(move.getCurrentCoordinate() == currentCoordinate && move.getDestinationCoordinate()
                            == destinationCoordinate){
                        return move;
                    }

                }
                return NULL_MOVE;

        }

}
}