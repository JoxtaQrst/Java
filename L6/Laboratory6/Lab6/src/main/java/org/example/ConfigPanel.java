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
        linesCombo = new JComboBox<>(new Double[]{0.1, 0.3, 0.5, 0.7, 0.9});
        linesLabel = new JLabel("Probability of lines");

        //create the rest of the components...TODO
        add(dotsLabel); //JPanel uses FlowLayout by default...TODO
        add(dotsField);
        add(linesLabel);
        add(linesCombo);
    }

}
