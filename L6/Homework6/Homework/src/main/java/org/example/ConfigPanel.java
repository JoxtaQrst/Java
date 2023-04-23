package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class ConfigPanel extends JPanel {
    final MainFrame frame;
    JLabel dotsLabel,linesLabel,player1Label,player2Label;
    JSpinner dotsField;
    JComboBox<Double> linesCombo;
    JButton createButton;

    public ConfigPanel(MainFrame frame){
        this.frame=frame;
        init();
    }
    private void init() {
        //create the label and the spinner
        dotsLabel = new JLabel("Number of dots:");
        dotsField = new JSpinner(new SpinnerNumberModel(6, 3, 100, 1));
        linesCombo = new JComboBox<>(new Double[]{0.1, 0.3, 0.5, 0.7, 1.0});
        linesLabel = new JLabel("Probability of lines");
        createButton = new JButton("New Game");
        player1Label = new JLabel("Player 1 : Red");
        player2Label = new JLabel("Player 2 : Green");

        //event listener for new game
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                frame.canvas.createBoard();
        }
        });

        add(dotsLabel);
        add(dotsField);
        add(linesLabel);
        add(linesCombo);
        add(createButton);
        add(player1Label);
        add(player2Label);
    }

    public void updatePlayerLabels(int currentPlayer){
        if(currentPlayer == 1){
            player1Label.setText("Player 1: Red ( Turn ) ");
            player2Label.setText("Player 2 : Green");
        } else {
            player1Label.setText("Player 1: Red  ");
            player2Label.setText("Player 2 : Green ( Turn )");
        }
    }

}
