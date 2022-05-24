package com.lolchess.engine;

import com.lolchess.engine.player.P1Player;
import com.lolchess.engine.player.P2Player;
import com.lolchess.engine.player.Player;

public enum Alliance {
    P1 {
        @Override
        public int getDirection() {
            return -1;
        }

        @Override
        public boolean isP1() {
            return true;
        }

        @Override
        public boolean isP2() {
            return false;
        }

        @Override
        public Player choosePlayer(final P1Player p1Player, final P2Player p2Player){
            return p1Player;
        }
    },
    P2 {
        @Override
        public int getDirection() {
            return 1;
        }

        @Override
        public boolean isP1() {
            return false;
        }

        @Override
        public boolean isP2() {
            return true;
        }

        @Override
        public Player choosePlayer(final P1Player p1Player, final P2Player p2Player){
            return p2Player;
        }

    };
    public abstract int getDirection();
    public abstract boolean isP1();
    public abstract boolean isP2();

    public abstract Player choosePlayer(final P1Player p1Player, final P2Player p2Player );
}
