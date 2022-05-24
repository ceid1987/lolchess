package com.lolchess.engine;

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
    },
    P1() {
        @Override
        public boolean isP1() {
            return true;
        }

        @Override
        public boolean isP2() {
            return false;
        }
    };

    public abstract boolean isP2();

    public abstract boolean isP1();
}
