package com.lolchess.engine.board;

public class BoardUtils {
    public static final boolean[] FIRST_COLUMN = initColumn(0);

    public static final boolean[] SECOND_COLUMN = initColumn(1);

    public static final boolean[] SEVENTH_COLUMN = initColumn(6);
    public static final boolean[] EIGHTH_COLUMN = initColumn(7) ;

    public static final boolean[] FIRST_ROW = initRow(0);

    public static final boolean[] SECOND_ROW = initRow(8);
    public static final boolean[] THIRD_ROW = initRow(16);
    public static final boolean[] FOURTH_ROW = initRow(24);
    public static final boolean[] FIFTH_ROW = initRow(32);
    public static final boolean[] SIXTH_ROW = initRow(40);
    public static final boolean[] SEVENTH_ROW = initRow(48);
    public static final boolean[] EIGHTH_ROW = initRow(56);
    public static final boolean[] NINTH_ROW = initRow(64);
    public static final boolean[] TENTH_ROW = initRow(72);
    public static final boolean[] ELEVENTH_ROW = initRow(80);
    public static final boolean[] TWELFTH_ROW = initRow(88);
    public static final boolean[] THIRTEENTH_ROW = initRow(96);
    public static final boolean[] FOURTEENTH_ROW = initRow(104);

    public static final boolean[] FIFTEENTH_ROW = initRow(112);
    public static final boolean[] SIXTEENTH_ROW = initRow(120);
    public static final int NUM_TILES = 128;
    public static final int NUM_TILES_PER_ROW = 8;

    private static boolean[] initColumn(int columnNumber) {
        final boolean[] column = new boolean[NUM_TILES];
        do {
            column[columnNumber] = true;
            columnNumber += NUM_TILES_PER_ROW;
        } while(columnNumber < NUM_TILES);
        return column;
    }

    private static boolean[] initRow(int rowNumber) {
        final boolean[] row = new boolean[NUM_TILES];
        for(int i = 0; i < row.length; i++) {
            row[i] = false;
        }
        do {
            row[rowNumber] = true;
            rowNumber++;
        } while(rowNumber % NUM_TILES_PER_ROW != 0);
        return row;
    }




    private BoardUtils() {
        throw new RuntimeException("Can't instantiate this!");
    }
    public static boolean isValidTileCoordinate(final int coordinate) {
        return coordinate >=0 && coordinate < 128;
    }
}
