package com.lolchess.gui;

import com.lolchess.engine.board.Board;
import com.lolchess.engine.board.BoardUtils;
import com.lolchess.engine.board.Move;
import com.lolchess.engine.board.Tile;
import com.lolchess.engine.pieces.Piece;
import com.lolchess.engine.player.MoveTransition;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static javax.swing.SwingUtilities.isLeftMouseButton;
import static javax.swing.SwingUtilities.isRightMouseButton;

public class Table {

    private final JFrame gameFrame;
    private final BoardPanel boardPanel;
    private final Board chessBoard;

    private Tile sourceTile;
    private Tile destinationTile;
    private Piece humanMovedPiece;

    private final static Dimension OUTER_FRAME_DIMENSION = new Dimension(600,800);
    private final static Dimension BOARD_PANEL_DIMENSION = new Dimension(400,350);

    private final static Dimension TILE_PANEL_DIMENSION = new Dimension(10,10);
    private static final String defaultPieceImagesPath = "imgs/";
    private Color lightTileColor = Color.decode("#000000");
    private Color darkTileColor = Color.decode("#FFFFFF");
    public Table(){
        this.gameFrame = new JFrame("lolchess");
        this.gameFrame.setLayout(new BorderLayout());
        final JMenuBar tableMenuBar = createTableMenuBar();
        this.gameFrame.setJMenuBar(tableMenuBar);
        this.gameFrame.setSize(OUTER_FRAME_DIMENSION);
        this.chessBoard = Board.createStandartBoard();
        this.boardPanel = new BoardPanel();
        this.gameFrame.add(this.boardPanel, BorderLayout.CENTER);

        this.gameFrame.setVisible(true);
    }

    private JMenuBar createTableMenuBar() {
        final JMenuBar tableMenuBar = new JMenuBar();
        tableMenuBar.add(createFileMenu());
        return tableMenuBar;
    }

    private JMenu createFileMenu() {
        final JMenu fileMenu = new JMenu("File");
        final JMenuItem openPGN = new JMenuItem("Charger fichier PGN");
        openPGN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Open the pgn file");
            }
        });
        fileMenu.add(openPGN);

        final JMenuItem exitMenuItem = new JMenuItem("Quitter");
        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.exit(0);
            }
        });
        fileMenu.add(exitMenuItem);
        return fileMenu;
    }

    private class BoardPanel extends JPanel {
         final List<TilePanel> boardTiles;

        BoardPanel() {
            super(new GridLayout(16,8));
            this.boardTiles = new ArrayList<>();
            for(int i=0; i< BoardUtils.NUM_TILES; i++) {
                final TilePanel tilePanel = new TilePanel(this, i);
                this.boardTiles.add(tilePanel);
                add(tilePanel);
            }
            setPreferredSize(BOARD_PANEL_DIMENSION);
            validate();
        }
    }

    private class TilePanel extends JPanel {

        private final int tileId;
        TilePanel(final BoardPanel boardPanel,
                  final int tileId) {
            super(new GridBagLayout());
            this.tileId = tileId;
            setPreferredSize(TILE_PANEL_DIMENSION);
            assignTileColor();
            try {
                assignTilePieceIcon(chessBoard);
                addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {

                        if(isRightMouseButton(e)) {

                            sourceTile = null;
                            destinationTile = null;
                            humanMovedPiece = null;
                        } else if(isLeftMouseButton(e)) {
                            if(sourceTile==null) {
                                sourceTile = chessBoard.getTile(tileId);
                                humanMovedPiece = sourceTile.getPiece();
                                if (humanMovedPiece == null) {
                                    sourceTile = null;
                                }
                            } else {
                                destinationTile = chessBoard.getTile(tileId);
                                final Move move = null;
                            }
                        }
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {

                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {

                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {

                    }

                    @Override
                    public void mouseExited(MouseEvent e) {

                    }
                });
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            validate();
        }

        private void assignTilePieceIcon(final Board board) throws IOException {
            this.removeAll();
            if(board.getTile(this.tileId).isTileOccupied()) {

                final BufferedImage image = ImageIO.read(new File(defaultPieceImagesPath + board.getTile(this.tileId).getPiece().getPieceAlliance().toString() + "" +
                        board.getTile(this.tileId).getPiece().toString()+".png"));
                add(new JLabel(new ImageIcon(image)));
            }
        }
        private void assignTileColor() {
            if(BoardUtils.FIRST_ROW[this.tileId] ||
                BoardUtils.THIRD_ROW[this.tileId] ||
                BoardUtils.FIFTH_ROW[this.tileId] ||
                BoardUtils.SEVENTH_ROW[this.tileId] ||
                BoardUtils.NINTH_ROW[this.tileId] ||
                BoardUtils.ELEVENTH_ROW[this.tileId] ||
                BoardUtils.THIRTEENTH_ROW[this.tileId] ||
                BoardUtils.FIFTEENTH_ROW[this.tileId]) {
                setBackground(this.tileId % 2 == 0 ? lightTileColor : darkTileColor);
            } else if(BoardUtils.SECOND_ROW[this.tileId] ||
                    BoardUtils.FOURTH_ROW[this.tileId] ||
            BoardUtils.SIXTH_ROW[this.tileId] ||
            BoardUtils.EIGHTH_ROW[this.tileId] ||
            BoardUtils.TENTH_ROW[this.tileId] ||
            BoardUtils.TWELFTH_ROW[this.tileId] ||
            BoardUtils.FOURTEENTH_ROW[this.tileId] ||
            BoardUtils.SIXTEENTH_ROW[this.tileId]) {
                setBackground(this.tileId % 2 != 0 ? lightTileColor : darkTileColor);
            }
        }

    }

}
