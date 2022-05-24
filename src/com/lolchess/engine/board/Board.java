package com.lolchess.engine.board;
import java.util.*;
import com.google.common.collect.ImmutableList;
import com.lolchess.engine.Alliance;
import com.lolchess.engine.pieces.Piece;
import com.lolchess.engine.pieces.Tireur;



public class Board {

    private final List<Tile> gameBoard;
    private final Collection<Piece> P1Pieces;
    private final Collection<Piece> P2Pieces;

    private Board(Builder builder){
        this.gameBoard = createGameboard(builder);
        this.P1Pieces = calculateActivePieces(this.gameBoard, Alliance.P1);
        this.P2Pieces = calculateActivePieces(this.gameBoard, Alliance.P2);
        final Collection<Move> P1StandardLegalMoves = calculateLegalMoves(this.P1Pieces);
        final Collection<Move> P2StandardLegalMoves = calculateLegalMoves(this.P2Pieces);

    }


    @Override
    public String toString(){
        final StringBuilder builder = new StringBuilder();
        for (int i = 0; i < BoardUtils.NUM_TILES; i++) {
            final String tileText = this.gameBoard.get(i).toString();
            builder.append(String.format("%3s", tileText));
            if ((i + 1) % BoardUtils.NUM_TILES_PER_ROW == 0) {
                builder.append("\n");
            }
        }
        return builder.toString();
    }

    private static String prettyPrint(final Tile tile){
        return tile.toString();
    }

    private Collection<Move> calculateLegalMoves(final Collection<Piece> pieces){
        final List<Move> legalMoves = new ArrayList<>();
        for(final Piece piece : pieces){
            legalMoves.addAll(piece.calculateLegalMoves(this));
        }
        return ImmutableList.copyOf(legalMoves);
    }

    private static Collection<Piece> calculateActivePieces(final List<Tile> gameBoard, final Alliance alliance){

        final List<Piece> activePieces = new ArrayList<>();
        for(final Tile tile : gameBoard){
            if(tile.isTileOccupied()){
                final Piece piece = tile.getPiece();
                if(piece.getPieceAlliance()== alliance){
                    activePieces.add(piece);
                }
            }

        }
        return ImmutableList.copyOf(activePieces);

    }

    public Tile getTile(final int tileCoordinate) {
        return gameBoard.get(tileCoordinate);
    }

    private static List<Tile>createGameboard(final Builder builder){
        final Tile[] tiles = new Tile[BoardUtils.NUM_TILES];
        for (int i=0; i< BoardUtils.NUM_TILES; i++){
            tiles[i] = Tile.createTile(i, builder.boardConfig.get(i));
        }
        return ImmutableList.copyOf(tiles);
    }

    public static Board createStandartBoard(){
        final Builder builder = new Builder();
        // Black Layout
        builder.setPiece(new Tireur(Alliance.P1, 0));
        builder.setPiece(new Tireur(Alliance.P1, 1));
        builder.setPiece(new Tireur(Alliance.P1, 2));
        builder.setPiece(new Tireur(Alliance.P1, 3));
        builder.setPiece(new Tireur(Alliance.P1, 4));
        builder.setPiece(new Tireur(Alliance.P1, 5));
        builder.setPiece(new Tireur(Alliance.P1, 6));
        builder.setPiece(new Tireur(Alliance.P1, 7));
        builder.setPiece(new Tireur(Alliance.P1, 8));
        builder.setPiece(new Tireur(Alliance.P1, 9));
        builder.setPiece(new Tireur(Alliance.P1, 10));
        builder.setPiece(new Tireur(Alliance.P1, 11));
        builder.setPiece(new Tireur(Alliance.P1, 12));
        builder.setPiece(new Tireur(Alliance.P1, 13));
        builder.setPiece(new Tireur(Alliance.P1, 14));
        builder.setPiece(new Tireur(Alliance.P1, 15));
        // White Layout
        builder.setPiece(new Tireur(Alliance.P2, 112));
        builder.setPiece(new Tireur(Alliance.P2, 113));
        builder.setPiece(new Tireur(Alliance.P2, 114));
        builder.setPiece(new Tireur(Alliance.P2, 115));
        builder.setPiece(new Tireur(Alliance.P2, 116));
        builder.setPiece(new Tireur(Alliance.P2, 117));
        builder.setPiece(new Tireur(Alliance.P2, 118));
        builder.setPiece(new Tireur(Alliance.P2, 119));
        builder.setPiece(new Tireur(Alliance.P2, 120));
        builder.setPiece(new Tireur(Alliance.P2, 121));
        builder.setPiece(new Tireur(Alliance.P2, 122));
        builder.setPiece(new Tireur(Alliance.P2, 123));
        builder.setPiece(new Tireur(Alliance.P2, 124));
        builder.setPiece(new Tireur(Alliance.P2, 125));
        builder.setPiece(new Tireur(Alliance.P2, 126));
        builder.setPiece(new Tireur(Alliance.P2, 127));
        //white to move
        builder.setMoveMaker(Alliance.P2);
        //build the board
        return builder.build();
    }

    public static class Builder{
        Map<Integer, Piece> boardConfig;
        Alliance nextMoveMaker;

        public Builder() {
            this.boardConfig = new HashMap<>();
        }

        public Builder setPiece(final Piece piece){
            this.boardConfig.put(piece.getPiecePosition(), piece);
            return this;
        }

        public Builder setMoveMaker(final Alliance nextMoveMaker){
            this.nextMoveMaker = nextMoveMaker;
            return this;
        }

        public Board build(){
            return new Board(this);
        }

    }
}
