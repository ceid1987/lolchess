package com.lolchess.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.lolchess.engine.board.Board;
import com.lolchess.engine.board.Move;
import com.lolchess.gui.Table.MoveLog;

class GameHistoryPanel extends JPanel {

    /**
     * Panel historique des mouvements
     */

    private final DataModel model;
    private final JScrollPane scrollPane;
    private static final Dimension HISTORY_PANEL_DIMENSION = new Dimension(100, 40);

    GameHistoryPanel() {
        this.setLayout(new BorderLayout());
        this.model = new DataModel();
        final JTable table = new JTable(model);
        table.setRowHeight(15);
        this.scrollPane = new JScrollPane(table);
        scrollPane.setColumnHeaderView(table.getTableHeader());
        scrollPane.setPreferredSize(HISTORY_PANEL_DIMENSION);
        this.add(scrollPane, BorderLayout.CENTER);
        this.setVisible(true);
    }

    void redo(final Board board,
              final MoveLog moveHistory) {
        int currentRow = 0;
        this.model.clear();
        for (final Move move : moveHistory.getMoves()) {
            final String moveText = move.toString();
            if (move.getMovedPiece().getPieceAlliance().isP2()) {
                this.model.setValueAt(moveText, currentRow, 0);
            }
            else if (move.getMovedPiece().getPieceAlliance().isP1()) {
                this.model.setValueAt(moveText, currentRow, 1);
                currentRow++;
            }
        }

        if(moveHistory.getMoves().size() > 0) {
            final Move lastMove = moveHistory.getMoves().get(moveHistory.size() - 1);
            final String moveText = lastMove.toString();

            if (lastMove.getMovedPiece().getPieceAlliance().isP2()) {
                this.model.setValueAt(moveText, currentRow, 0);
            }
            else if (lastMove.getMovedPiece().getPieceAlliance().isP1()) {
                this.model.setValueAt(moveText, currentRow - 1, 1);
            }
        }

        final JScrollBar vertical = scrollPane.getVerticalScrollBar();
        vertical.setValue(vertical.getMaximum());

    }



    private static class Row {

        private String p2Move;
        private String p1Move;

        Row() {
        }

        public String getP2Move() {
            return this.p2Move;
        }

        public String getP1Move() {
            return this.p1Move;
        }

        public void setP2Move(final String move) {
            this.p2Move = move;
        }

        public void setP1Move(final String move) {
            this.p1Move = move;
        }

    }

    private static class DataModel extends DefaultTableModel {

        private final List<Row> values;
        private static final String[] NAMES = {"P2", "P1"};

        DataModel() {
            this.values = new ArrayList<>();
        }

        public void clear() {
            this.values.clear();
            setRowCount(0);
        }

        @Override
        public int getRowCount() {
            if(this.values == null) {
                return 0;
            }
            return this.values.size();
        }

        @Override
        public int getColumnCount() {
            return NAMES.length;
        }

        @Override
        public Object getValueAt(final int row, final int col) {
            final Row currentRow = this.values.get(row);
            if(col == 0) {
                return currentRow.getP2Move();
            } else if (col == 1) {
                return currentRow.getP1Move();
            }
            return null;
        }

        @Override
        public void setValueAt(final Object aValue,
                               final int row,
                               final int col) {
            final Row currentRow;
            if(this.values.size() <= row) {
                currentRow = new Row();
                this.values.add(currentRow);
            } else {
                currentRow = this.values.get(row);
            }
            if(col == 0) {
                currentRow.setP2Move((String) aValue);
                fireTableRowsInserted(row, row);
            } else  if(col == 1) {
                currentRow.setP1Move((String)aValue);
                fireTableCellUpdated(row, col);
            }
        }

        @Override
        public Class<?> getColumnClass(final int col) {
            return Move.class;
        }

        @Override
        public String getColumnName(final int col) {
            return NAMES[col];
        }
    }
}