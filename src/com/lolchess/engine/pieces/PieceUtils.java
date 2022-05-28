package com.lolchess.engine.pieces;
import com.lolchess.engine.Alliance;
import com.lolchess.engine.board.BoardUtils;
import com.google.common.collect.ImmutableTable;
import com.google.common.collect.Table;

enum PieceUtils {

    /**
     * Outils pour les pieces
     */

    INSTANCE;

    private final Table<Alliance, Integer, Tireur> ALL_POSSIBLE_TIREUR = PieceUtils.createAllPossibleMovedTireur();
    private final Table<Alliance, Integer, Mage> ALL_POSSIBLE_MAGE = PieceUtils.createAllPossibleMovedMage();
    private final Table<Alliance, Integer, Combattant> ALL_POSSIBLE_COMBATTANT = PieceUtils.createAllPossibleMovedCombattant();
    private final Table<Alliance, Integer, Enchanteur> ALL_POSSIBLE_ENCHANTEUR = PieceUtils.createAllPossibleMovedEnchanteur();
    private final Table<Alliance, Integer, Tank> ALL_POSSIBLE_TANK = PieceUtils.createAllPossibleMovedTank();

    Tank getMovedTank(final Alliance alliance,
                      final int destinationCoordinate) {
        return ALL_POSSIBLE_TANK.get(alliance, destinationCoordinate);
    }

    Combattant getMovedCombattant(final Alliance alliance,
                          final int destinationCoordinate) {
        return ALL_POSSIBLE_COMBATTANT.get(alliance, destinationCoordinate);
    }

    Enchanteur getMovedEnchanteur(final Alliance alliance,
                          final int destinationCoordinate) {
        return ALL_POSSIBLE_ENCHANTEUR.get(alliance, destinationCoordinate);
    }

    Mage getMovedMage(final Alliance alliance,
                      final int destinationCoordinate) {
        return ALL_POSSIBLE_MAGE.get(alliance, destinationCoordinate);
    }

    Tireur getMovedTireur(final Alliance alliance,
                        final int destinationCoordinate) {
        return ALL_POSSIBLE_TIREUR.get(alliance, destinationCoordinate);
    }

    private static Table<Alliance, Integer, Tank> createAllPossibleMovedTank() {
        final ImmutableTable.Builder<Alliance, Integer, Tank> pieces = ImmutableTable.builder();
        for(final Alliance alliance : Alliance.values()) {
            for(int i = 0; i < BoardUtils.NUM_TILES; i++) {
                pieces.put(alliance, i, new Tank(alliance, i, false));
            }
        }
        return pieces.build();
    }

    private static Table<Alliance, Integer, Combattant> createAllPossibleMovedCombattant() {
        final ImmutableTable.Builder<Alliance, Integer, Combattant> pieces = ImmutableTable.builder();
        for(final Alliance alliance : Alliance.values()) {
            for(int i = 0; i < BoardUtils.NUM_TILES; i++) {
                pieces.put(alliance, i, new Combattant(alliance, i, false));
            }
        }
        return pieces.build();
    }

    private static Table<Alliance, Integer, Enchanteur> createAllPossibleMovedEnchanteur() {
        final ImmutableTable.Builder<Alliance, Integer, Enchanteur> pieces = ImmutableTable.builder();
        for(final Alliance alliance : Alliance.values()) {
            for(int i = 0; i < BoardUtils.NUM_TILES; i++) {
                pieces.put(alliance, i, new Enchanteur(alliance, i, false));
            }
        }
        return pieces.build();
    }

    private static Table<Alliance, Integer, Mage> createAllPossibleMovedMage() {
        final ImmutableTable.Builder<Alliance, Integer, Mage> pieces = ImmutableTable.builder();
        for(final Alliance alliance : Alliance.values()) {
            for(int i = 0; i < BoardUtils.NUM_TILES; i++) {
                pieces.put(alliance, i, new Mage(alliance, i, false));
            }
        }
        return pieces.build();
    }

    private static Table<Alliance, Integer, Tireur> createAllPossibleMovedTireur() {
        final ImmutableTable.Builder<Alliance, Integer, Tireur> pieces = ImmutableTable.builder();
        for(final Alliance alliance : Alliance.values()) {
            for(int i = 0; i < BoardUtils.NUM_TILES; i++) {
                pieces.put(alliance, i, new Tireur(alliance, i, false));
            }
        }
        return pieces.build();
    }

}