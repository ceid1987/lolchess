package com.lolchess.engine.board;

public class BoardUtils {
    public static final boolean[] FIRST_COLUMN = initColumn(0);
    public static final boolean[] EIGHTH_COLUMN = initColumn(7) ;

    //UNCOMMENT LATER
    //public static final boolean[] FIRST_LINE = null;
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



    private BoardUtils() {
        throw new RuntimeException("Can't instantiate this!");
    }
    public static boolean isValidTileCoordinate(final int coordinate) {
        return coordinate >=0 && coordinate < 128;
    }
}
