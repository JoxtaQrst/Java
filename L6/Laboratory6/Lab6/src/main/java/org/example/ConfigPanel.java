package org.example;

import javax.swing.*;
import java.util.Vector;

public class ConfigPanel extends JPanel {
    final MainFrame frame;
    JLabel dotsLabel,linesLabel;
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
        linesCombo = new JComboBox<>(new Double[]{1.0, 1.1, 1.2, 1.3, 1.4});
        linesLabel = new JLabel("Probability of lines");
        createButton = new JButton("New Game");

        //create the rest of the components...TODO
        add(dotsLabel); //JPanel uses FlowLayout by default...TODO
        add(dotsField);
        add(linesLabel);
        add(linesCombo);
        add(createButton);
    }

}
