package com.lolchess;

import com.lolchess.engine.board.Board;
import com.lolchess.gui.Table;

public class lolchess {
    public static void main(String[] args) throws Exception {
        Board board = Board.createStandartBoard();
        System.out.println(board);
        Table table = new Table();
    }
}
