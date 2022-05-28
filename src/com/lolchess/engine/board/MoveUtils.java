package com.lolchess.engine.board;

import static com.lolchess.engine.board.Move.*;

public enum MoveUtils {
    INSTANCE;

    public static int Score(final Move move) {
        if(move == MoveFactory.getNullMove()) {
            return 1;
        }
        return move.isAttack() ?
                5 * Score(move.getBoard().getTransitionMove()) :
                Score(move.getBoard().getTransitionMove());
    }
}