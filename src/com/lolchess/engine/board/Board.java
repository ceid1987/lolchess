package com.lolchess.engine.board;
import com.google.common.collect.ImmutableList;
import com.lolchess.engine.Alliance;
import com.lolchess.engine.pieces.*;
import com.lolchess.engine.player.P1Player;
import com.lolchess.engine.player.P2Player;
import com.lolchess.engine.player.Player;

import java.util.*;

public class Board {

    private final List<Tile> gameBoard;
    private final Collection<Piece> P1Pieces;
    private final Collection<Piece> P2Pieces;

    private final P1Player p1Player;
    private final P2Player p2Player;
    private final Player currentPlayer;

    private Board(final Builder builder){
        this.gameBoard = createGameboard(builder);
        this.P1Pieces = calculateActivePieces(this.gameBoard, Alliance.P1);
        this.P2Pieces = calculateActivePieces(this.gameBoard, Alliance.P2);
        final Collection<Move> p1StandardLegalMoves = calculateLegalMoves(this.P1Pieces);
        final Collection<Move> p2StandardLegalMoves = calculateLegalMoves(this.P2Pieces);

        this.p1Player = new P1Player(this, p1StandardLegalMoves, p2StandardLegalMoves);
        this.p2Player = new P2Player(this, p1StandardLegalMoves, p2StandardLegalMoves);
        this.currentPlayer = builder.nextMoveMaker.choosePlayer(this.p1Player, this.p2Player);

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

    public Player p1Player() {
        return this.p1Player;
    }

    public Player p2Player() {
        return this.p2Player;
    }

    public Player currentPlayer() {
        return this.currentPlayer;
    }
    public Collection<Piece> getP1Pieces(){
        return this.P1Pieces;
    }
    public Collection<Piece> getP2Pieces(){
        return this.P2Pieces;
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
        // P1 Layout
        builder.setPiece(new Tireur(0, Alliance.P1));
        builder.setPiece(new Mage(1, Alliance.P1));
        builder.setPiece(new Enchanteur(2, Alliance.P1));
        builder.setPiece(new Mage(3, Alliance.P1));
        builder.setPiece(new Tireur(4, Alliance.P1));
        builder.setPiece(new Enchanteur(5, Alliance.P1));
        builder.setPiece(new Mage(6, Alliance.P1));
        builder.setPiece(new Tireur(7, Alliance.P1));
        builder.setPiece(new Tank(8, Alliance.P1));
        builder.setPiece(new Combattant(9, Alliance.P1));
        builder.setPiece(new Tank(10, Alliance.P1));
        builder.setPiece(new Tank(11, Alliance.P1));
        builder.setPiece(new Tank(12, Alliance.P1));
        builder.setPiece(new Tank(13, Alliance.P1));
        builder.setPiece(new Combattant(14, Alliance.P1));
        builder.setPiece(new Tank(15, Alliance.P1));
        // P2 Layout
        builder.setPiece(new Tank(112, Alliance.P2));
        builder.setPiece(new Combattant(113, Alliance.P2));
        builder.setPiece(new Tank(114, Alliance.P2));
        builder.setPiece(new Tank(115, Alliance.P2));
        builder.setPiece(new Tank(116, Alliance.P2));
        builder.setPiece(new Tank(117, Alliance.P2));
        builder.setPiece(new Combattant(118, Alliance.P2));
        builder.setPiece(new Tank(119, Alliance.P2));
        builder.setPiece(new Tireur(120, Alliance.P2));
        builder.setPiece(new Mage(121, Alliance.P2));
        builder.setPiece(new Enchanteur(122, Alliance.P2));
        builder.setPiece(new Mage(123, Alliance.P2));
        builder.setPiece(new Mage(124, Alliance.P2));
        builder.setPiece(new Enchanteur(125, Alliance.P2));
        builder.setPiece(new Mage(126, Alliance.P2));
        builder.setPiece(new Tireur(127, Alliance.P2));
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
