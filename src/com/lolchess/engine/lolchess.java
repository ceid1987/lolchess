package com.lolchess.engine;

import com.lolchess.engine.board.Board;

public class lolchess {
    public static void main(String[] args) {
        Board board = Board.createStandartBoard();
        System.out.println(board);
    }
}
