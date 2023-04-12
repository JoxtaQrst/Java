package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ControlPanel extends JPanel {
    final MainFrame frame;
    JButton exitBtn = new JButton("Exit");
    JButton loadBtn = new JButton("Load");
    JButton saveBtn = new JButton("Save");
    JButton resetBtn = new JButton("Reset");
    //create all buttons (Load, Exit, etc.)...TODO
    public ControlPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }
    private void init() {
        //change the default layout manager (just for fun)
        //setLayout(new BoxLayout(this,BoxLayout.LINE_AXIS));
        setLayout(new FlowLayout());
        //setLayout(new GridLayout(2, 2));
        //add all buttons ...TODO
        add(loadBtn);
        add(saveBtn);
        add(resetBtn);
        add(exitBtn);
        //configure listeners for all buttons
        exitBtn.addActionListener(this::exitGame);
        loadBtn.addActionListener(this::loadBtn);
        saveBtn.addActionListener(this::saveBtn);
        resetBtn.addActionListener(this::resetBtn);
    }

    private void resetBtn(ActionEvent actionEvent) {
    }

    private void saveBtn(ActionEvent actionEvent) {
    }

    private void loadBtn(ActionEvent actionEvent) {
    }

    private void exitGame(ActionEvent e) {
        frame.dispose();
    }
    //...TODO

}
