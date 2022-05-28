package com.lolchess.engine.board;

import java.util.*;
import com.lolchess.engine.board.Move.MoveFactory;

public enum BoardUtils {

    INSTANCE;
    public static final List<Boolean> FIRST_COLUMN = initColumn(0);

    public static final List<Boolean> SECOND_COLUMN = initColumn(1);

    public static final List<Boolean> SEVENTH_COLUMN = initColumn(6);
    public static final List<Boolean> EIGHTH_COLUMN = initColumn(7);

    public static final List<Boolean> FIRST_ROW = initRow(0);

    public static final List<Boolean> SECOND_ROW = initRow(8);
    public static final List<Boolean> THIRD_ROW = initRow(16);
    public static final List<Boolean> FOURTH_ROW = initRow(24);
    public static final List<Boolean> FIFTH_ROW = initRow(32);
    public static final List<Boolean> SIXTH_ROW = initRow(40);
    public static final List<Boolean> SEVENTH_ROW = initRow(48);
    public static final List<Boolean> EIGHTH_ROW = initRow(56);
    public static final List<Boolean> NINTH_ROW = initRow(64);
    public static final List<Boolean> TENTH_ROW = initRow(72);
    public static final List<Boolean> ELEVENTH_ROW = initRow(80);
    public static final List<Boolean> TWELFTH_ROW = initRow(88);
    public static final List<Boolean> THIRTEENTH_ROW = initRow(96);
    public static final List<Boolean> FOURTEENTH_ROW = initRow(104);

    public static final List<Boolean> FIFTEENTH_ROW = initRow(112);
    public static final List<Boolean> SIXTEENTH_ROW = initRow(120);

    public final List<String> ALGEBRAIC_NOTATION = initializeAlgebraicNotation();
    public final Map<String, Integer> POSITION_TO_COORDINATE = initializePositionToCoordinateMap();

    public static final int START_TILE_INDEX = 0;
    public static final int NUM_TILES = 128;
    public static final int NUM_TILES_PER_ROW = 8;

    private static List<Boolean> initColumn(int columnNumber) {
        final Boolean[] column = new Boolean[NUM_TILES];
        for(int i = 0; i < column.length; i++) {
            column[i] = false;
        }
        do {
            column[columnNumber] = true;
            columnNumber += NUM_TILES_PER_ROW;
        } while (columnNumber < NUM_TILES);
        return Collections.unmodifiableList(Arrays.asList((column)));

    }

    private static List<Boolean> initRow(int rowNumber) {
        final Boolean[] row = new Boolean[NUM_TILES];
        for(int i = 0; i < row.length; i++) {
            row[i] = false;
        }
        do {
            row[rowNumber] = true;
            rowNumber++;
        } while(rowNumber % NUM_TILES_PER_ROW != 0);
        return Collections.unmodifiableList(Arrays.asList(row));
    }

    private Map<String, Integer> initializePositionToCoordinateMap() {
        final Map<String, Integer> positionToCoordinate = new HashMap<>();
        for (int i = START_TILE_INDEX; i < NUM_TILES; i++) {
            positionToCoordinate.put(ALGEBRAIC_NOTATION.get(i), i);
        }
        return Collections.unmodifiableMap(positionToCoordinate);
    }

    //coord pour fichier PGN
    private static List<String> initializeAlgebraicNotation() {
        return Collections.unmodifiableList(Arrays.asList(
                "a16","b16","c16","d16","e16","f16","g16","h16",
                "a15","b15","c15","d15","e15","f15","g15","h15",
                "a14","b14","c14","d14","e14","f14","g14","h14",
                "a13","b13","c13","d13","e13","f13","g13","h13",
                "a12","b12","c12","d12","e12","f12","g12","h12",
                "a11","b11","c11","d11","e11","f11","g11","h11",
                "a10","b10","c10","d10","e10","f10","g10","h10",
                "a9", "b9", "c9", "d9", "e9", "f9", "g9", "h9",
                "a8", "b8", "c8", "d8", "e8", "f8", "g8", "h8",
                "a7", "b7", "c7", "d7", "e7", "f7", "g7", "h7",
                "a6", "b6", "c6", "d6", "e6", "f6", "g6", "h6",
                "a5", "b5", "c5", "d5", "e5", "f5", "g5", "h5",
                "a4", "b4", "c4", "d4", "e4", "f4", "g4", "h4",
                "a3", "b3", "c3", "d3", "e3", "f3", "g3", "h3",
                "a2", "b2", "c2", "d2", "e2", "f2", "g2", "h2",
                "a1", "b1", "c1", "d1", "e1", "f1", "g1", "h1"));
    }

    public static boolean isValidTileCoordinate(final int coordinate) {
        return coordinate >= START_TILE_INDEX && coordinate < NUM_TILES;
    }

    public int getCoordinateAtPosition(final String position) {
        return POSITION_TO_COORDINATE.get(position);
    }

    public String getPositionAtCoordinate(final int coordinate) {
        return ALGEBRAIC_NOTATION.get(coordinate);
    }




    public static List<Move> lastNMoves(final Board board, int N) {
        final List<Move> moveHistory = new ArrayList<>();
        Move currentMove = board.getTransitionMove();
        int i = 0;
        while(currentMove != MoveFactory.getNullMove() && i < N) {
            moveHistory.add(currentMove);
            currentMove = currentMove.getBoard().getTransitionMove();
            i++;
        }
        return Collections.unmodifiableList(moveHistory);
    }

}
