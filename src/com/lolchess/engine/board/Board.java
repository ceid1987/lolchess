package com.lolchess.engine.board;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.lolchess.engine.Alliance;
import com.lolchess.engine.pieces.*;
import com.lolchess.engine.player.P1Player;
import com.lolchess.engine.player.P2Player;
import com.lolchess.engine.player.Player;
import com.lolchess.engine.board.Move.MoveFactory;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class Board {
    private final Map<Integer, Piece> boardConfig;
    private final Collection<Piece> p1Pieces;
    private final Collection<Piece> p2Pieces;
    private final P1Player p1Player;
    private final P2Player p2Player;
    private final Player currentPlayer;

    private static final Board STANDARD_BOARD = createStandardBoardImpl();
    private final Move transitionMove;
    private final Tank enPassantTank;

    private Board(final Builder builder){
        this.boardConfig = Collections.unmodifiableMap(builder.boardConfig);
        this.p1Pieces = calculateActivePieces(builder, Alliance.P1);
        this.p2Pieces = calculateActivePieces(builder, Alliance.P2);
        this.enPassantTank = builder.enPassantTank;
        final Collection<Move> p2StandardMoves = calculateLegalMoves(this.p1Pieces);
        final Collection<Move> p1StandardMoves = calculateLegalMoves(this.p2Pieces);
        this.p1Player = new P1Player(this, p2StandardMoves, p1StandardMoves);
        this.p2Player = new P2Player(this, p2StandardMoves, p1StandardMoves);
        this.currentPlayer = builder.nextMoveMaker.choosePlayerByAlliance(this.p2Player, this.p1Player);
        this.transitionMove = builder.transitionMove != null ? builder.transitionMove : MoveFactory.getNullMove();

    }


    @Override
    public String toString(){
        final StringBuilder builder = new StringBuilder();
        for (int i = 0; i < BoardUtils.NUM_TILES; i++) {
            final String tileText = prettyPrint(this.boardConfig.get(i));
            builder.append(String.format("%3s", tileText));
            if ((i + 1) % BoardUtils.NUM_TILES_PER_ROW == 0) {
                builder.append("\n");
            }
        }
        return builder.toString();
    }

    public P1Player p1Player() {
        return this.p1Player;
    }

    public P2Player p2Player() {
        return this.p2Player;
    }

    public Player currentPlayer() {
        return this.currentPlayer;
    }
    public Collection<Piece> getP1Pieces(){
        return this.p1Pieces;
    }
    public Collection<Piece> getP2Pieces(){
        return this.p2Pieces;
    }


    private static String prettyPrint(final Piece piece){
        if(piece!=null) {
            return piece.getPieceAlliance().isP1() ?
                    piece.toString().toLowerCase() : piece.toString();
        }
        return "-";
    }

    private Collection<Move> calculateLegalMoves(final Collection<Piece> pieces){
        /*
        final List<Move> legalMoves = new ArrayList<>();
        for(final Piece piece : pieces){
            legalMoves.addAll(piece.calculateLegalMoves(this));
        }
        return ImmutableList.copyOf(legalMoves);
         */
        return pieces.stream().flatMap(piece -> piece.calculateLegalMoves(this).stream()).collect(Collectors.toList());
    }

    private static Collection<Piece> calculateActivePieces(final Builder builder,
                                                           final Alliance alliance) {

        return builder.boardConfig.values().stream()
                .filter(piece -> piece.getPieceAlliance() == alliance)
                .collect(Collectors.toList());
    }

    public Collection<Piece> getAllPieces() {
        return Stream.concat(this.p1Pieces.stream(),
                this.p2Pieces.stream()).collect(Collectors.toList());
    }

    public Collection<Move> getAllLegalMoves() {
        return Stream.concat(this.p1Player.getLegalMoves().stream(),
                this.p2Player.getLegalMoves().stream()).collect(Collectors.toList());
    }

    public Piece getPiece(final int coordinate) {
        return this.boardConfig.get(coordinate);
    }

    public Move getTransitionMove() {
        return this.transitionMove;
    }

    public Tank getEnPassantTank() {
        return this.enPassantTank;
    }


    public static Board createStandardBoard() {
        return STANDARD_BOARD;
    }

    private static Board createStandardBoardImpl(){
        final Builder builder = new Builder();
        // P1 Layout
        builder.setPiece(new Tireur(Alliance.P1, 0));
        builder.setPiece(new Mage(Alliance.P1, 1));
        builder.setPiece(new Enchanteur(Alliance.P1, 2));
        builder.setPiece(new Mage(Alliance.P1, 3));
        builder.setPiece(new Tireur(Alliance.P1, 4));
        builder.setPiece(new Enchanteur(Alliance.P1, 5));
        builder.setPiece(new Mage(Alliance.P1, 6));
        builder.setPiece(new Tireur(Alliance.P1, 7));
        builder.setPiece(new Tank(Alliance.P1, 8));
        builder.setPiece(new Combattant(Alliance.P1, 9));
        builder.setPiece(new Tank(Alliance.P1, 10));
        builder.setPiece(new Tank(Alliance.P1, 11));
        builder.setPiece(new Tank(Alliance.P1, 12));
        builder.setPiece(new Tank(Alliance.P1, 13));
        builder.setPiece(new Combattant(Alliance.P1, 14));
        builder.setPiece(new Tank(Alliance.P1, 15));
        // P2 Layout
        builder.setPiece(new Tank(Alliance.P2, 112));
        builder.setPiece(new Combattant(Alliance.P2, 113));
        builder.setPiece(new Tank(Alliance.P2, 114));
        builder.setPiece(new Tank(Alliance.P2, 115));
        builder.setPiece(new Tank(Alliance.P2, 116));
        builder.setPiece(new Tank(Alliance.P2, 117));
        builder.setPiece(new Combattant(Alliance.P2, 118));
        builder.setPiece(new Tank(Alliance.P2, 119));
        builder.setPiece(new Tireur(Alliance.P2, 120));
        builder.setPiece(new Mage(Alliance.P2, 121));
        builder.setPiece(new Enchanteur(Alliance.P2, 122));
        builder.setPiece(new Mage(Alliance.P2, 123));
        builder.setPiece(new Mage(Alliance.P2, 124));
        builder.setPiece(new Enchanteur(Alliance.P2, 125));
        builder.setPiece(new Mage(Alliance.P2, 126));
        builder.setPiece(new Tireur(Alliance.P2, 127));
        //P1 commence
        builder.setMoveMaker(Alliance.P2);
        //construction du plateau
        return builder.build();
    }

    public Iterable<Move> AllLegalMoves() {
        return Iterables.unmodifiableIterable(Iterables.concat(this.p1Player.getLegalMoves(), this.p2Player.getLegalMoves()));

    }

    public static class Builder{
        Map<Integer, Piece> boardConfig;
        Alliance nextMoveMaker;
        Tank enPassantTank;
        Move transitionMove;
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

        public Builder setEnPassantTank(final Tank enPassantTank) {
            this.enPassantTank = enPassantTank;
            return this;
        }

        public Builder setMoveTransition(final Move transitionMove) {
            this.transitionMove = transitionMove;
            return this;
        }

        public Board build(){
            return new Board(this);
        }

    }
}
