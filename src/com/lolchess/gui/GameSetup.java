package com.lolchess.gui;

import com.lolchess.engine.Alliance;
import com.lolchess.engine.player.Player;
import com.lolchess.gui.Table.PlayerType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class GameSetup extends JDialog {

    /**
     * Fenetre Setup
     */

    private PlayerType p2PlayerType;
    private PlayerType p1PlayerType;
    private JSpinner searchDepthSpinner;

    private static final String HUMAN_TEXT = "Human";
    private static final String COMPUTER_TEXT = "Computer";

    GameSetup(final JFrame frame,
              final boolean modal) {
        super(frame, modal);
        final JPanel myPanel = new JPanel(new GridLayout(0, 1));
        final JRadioButton p2HumanButton = new JRadioButton(HUMAN_TEXT);
        final JRadioButton p2ComputerButton = new JRadioButton(COMPUTER_TEXT);
        final JRadioButton p1HumanButton = new JRadioButton(HUMAN_TEXT);
        final JRadioButton p1ComputerButton = new JRadioButton(COMPUTER_TEXT);
        p2HumanButton.setActionCommand(HUMAN_TEXT);
        final ButtonGroup p2Group = new ButtonGroup();
        p2Group.add(p2HumanButton);
        p2Group.add(p2ComputerButton);
        p2HumanButton.setSelected(true);

        final ButtonGroup p1Group = new ButtonGroup();
        p1Group.add(p1HumanButton);
        p1Group.add(p1ComputerButton);
        p1HumanButton.setSelected(true);

        getContentPane().add(myPanel);
        myPanel.add(new JLabel("White"));
        myPanel.add(p2HumanButton);
        myPanel.add(p2ComputerButton);
        myPanel.add(new JLabel("Black"));
        myPanel.add(p1HumanButton);
        myPanel.add(p1ComputerButton);

        myPanel.add(new JLabel("Search"));
        this.searchDepthSpinner = addLabeledSpinner(myPanel, "Search Depth", new SpinnerNumberModel(6, 0, Integer.MAX_VALUE, 1));

        final JButton cancelButton = new JButton("Cancel");
        final JButton okButton = new JButton("OK");

        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (p2ComputerButton.isSelected()) p2PlayerType = PlayerType.HUMAN;
                if (!p1ComputerButton.isSelected()) p1PlayerType = PlayerType.HUMAN;
                GameSetup.this.setVisible(false);
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Cancel");
                GameSetup.this.setVisible(false);
            }
        });

        myPanel.add(cancelButton);
        myPanel.add(okButton);

        setLocationRelativeTo(frame);
        pack();
        setVisible(false);
    }

    void promptUser() {
        setVisible(true);
        repaint();
    }


    PlayerType getP2PlayerType() {
        return this.p2PlayerType;
    }

    PlayerType getP1PlayerType() {
        return this.p1PlayerType;
    }

    private static JSpinner addLabeledSpinner(final Container c,
                                              final String label,
                                              final SpinnerModel model) {
        final JLabel l = new JLabel(label);
        c.add(l);
        final JSpinner spinner = new JSpinner(model);
        l.setLabelFor(spinner);
        c.add(spinner);
        return spinner;
    }

    int getSearchDepth() {
        return (Integer)this.searchDepthSpinner.getValue();
    }
}