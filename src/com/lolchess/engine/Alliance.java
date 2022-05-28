package com.lolchess.engine;

import com.lolchess.engine.board.BoardUtils;
import com.lolchess.engine.player.P1Player;
import com.lolchess.engine.player.Player;
import com.lolchess.engine.player.P2Player;

public enum Alliance {

    P2() {

        @Override
        public boolean isP2() {
            return true;
        }

        @Override
        public boolean isP1() {
            return false;
        }

        @Override
        public int getDirection() {
            return UP_DIRECTION;
        }

        @Override
        public int getOppositeDirection() {
            return DOWN_DIRECTION;
        }

        @Override
        public boolean isTankPromotionSquare(final int position) {
            return BoardUtils.INSTANCE.FIRST_ROW.get(position);
        }

        @Override
        public Player choosePlayerByAlliance(final P2Player p2Player,
                                             final P1Player p1Player) {
            return p2Player;
        }

        @Override
        public String toString() {
            return "P2";
        }


    },
    P1() {

        @Override
        public boolean isP2() {
            return false;
        }

        @Override
        public boolean isP1() {
            return true;
        }

        @Override
        public int getDirection() {
            return DOWN_DIRECTION;
        }

        @Override
        public int getOppositeDirection() {
            return UP_DIRECTION;
        }

        @Override
        public boolean isTankPromotionSquare(final int position) {
            return BoardUtils.INSTANCE.EIGHTH_ROW.get(position);
        }

        @Override
        public Player choosePlayerByAlliance(final P2Player p2Player,
                                             final P1Player p1Player) {
            return p1Player;
        }

        @Override
        public String toString() {
            return "P1";
        }

    };

    public abstract int getDirection();

    public abstract int getOppositeDirection();


    public abstract boolean isP2();

    public abstract boolean isP1();

    public abstract boolean isTankPromotionSquare(int position);

    public abstract Player choosePlayerByAlliance(final P2Player p2Player, final P1Player p1Player);


    private static final int UP_DIRECTION = -1;

    private static final int DOWN_DIRECTION = 1;

}