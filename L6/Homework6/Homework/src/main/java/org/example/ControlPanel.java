package org.example;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ControlPanel extends JPanel {
    final MainFrame frame;
    JButton exitBtn = new JButton("Exit");
    JButton loadBtn = new JButton("Load");
    JButton saveBtn = new JButton("Save");
    JButton resetBtn = new JButton("Reset");
    JButton exportBtn = new JButton("Export");
    JTextField saveField = new JTextField("Save file name", 10);


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
        add(saveField);
        add(exportBtn);
        add(resetBtn);
        add(exitBtn);
        //configure listeners for all buttons
        exitBtn.addActionListener(this::exitGame);
        loadBtn.addActionListener(this::loadBtn);
        saveBtn.addActionListener(this::saveBtn);
        exportBtn.addActionListener(this::exportBtn);
        resetBtn.addActionListener(this::resetBtn);
    }

    private void saveBtn(ActionEvent actionEvent) {
        String fileName = saveField.getText();
        if(!fileName.isEmpty())
        {
            GameState gameState = new GameState(frame.canvas.getNumVertices(),frame.canvas.getEdgeProbability(),
                    frame.canvas.getXcoord(),frame.canvas.getYcoord(), frame.canvas.getConnectedVertices(),
                    frame.canvas.getVertexColor(),frame.canvas.getCurrentPlayer(),frame.canvas.isGameDone());
            gameState.saveToFile(fileName + ".dat");
        }
        else {
            JOptionPane.showMessageDialog(this,"Please enter a filee name.","Error",JOptionPane.ERROR_MESSAGE);
        }

    }

    private void resetBtn(ActionEvent actionEvent) {
        frame.canvas.resetGame();
    }

    private void exportBtn(ActionEvent actionEvent) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("."));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG Images","png");
        fileChooser.setFileFilter(filter);
        int saveDialog = fileChooser.showSaveDialog(this);
        if (saveDialog == JFileChooser.APPROVE_OPTION) {
            try {
                //get the image from the DrawingPanel
                BufferedImage image = frame.canvas.getImage();
                //get the selected file
                File file = fileChooser.getSelectedFile();
                //add the .png extension if not present
                if (!file.getAbsolutePath().endsWith(".png")) {
                    file = new File(file.getAbsolutePath() + ".png");
                }
                //save the image as a PNG file
                ImageIO.write(image, "PNG", file);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void loadBtn(ActionEvent actionEvent) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("."));
        FileNameExtensionFilter filter = new FileNameExtensionFilter(".dat file","dat");
        fileChooser.setFileFilter(filter);
        int loadDialog = fileChooser.showOpenDialog(this);
        if(loadDialog == JFileChooser.APPROVE_OPTION){
            File file = fileChooser.getSelectedFile();

            if(!file.getAbsolutePath().endsWith(".dat")){
                file= new File(file.getAbsolutePath()+".dat");
            }
            GameState gameState = GameState.loadFromFile(String.valueOf(file));
            frame.canvas.loadGame(gameState);
            System.out.println("File loaded " + String.valueOf(file));
        }

    }

    private void exitGame(ActionEvent e) {
        frame.dispose();
    }

}
