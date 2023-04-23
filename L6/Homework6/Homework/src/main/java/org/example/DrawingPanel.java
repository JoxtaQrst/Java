package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DrawingPanel extends JPanel {
    final MainFrame frame;
    final static int W = 800, H = 600;
    private int numVertices;
    private double edgeProbability;
    private int[] x, y;
    BufferedImage image; //the offscreen image
    Graphics2D graphics; //the tools needed to draw in the image
    private ArrayList<Integer>[] connectedVertices;
    private int[] vertexColor; //color of each vertex, 0 for none, 1 for player 1, 2 for player 2
    private int currentPlayer = 1; //current player's turn, 1 for player 1, 2 for player 2

    private boolean gameDone = false;


    public DrawingPanel(MainFrame frame) {
        this.frame = frame;
        createOffscreenImage();
        initPanel();
        createBoard();
    }

    private void initPanel() {
        setPreferredSize(new Dimension(W, H));
        setBorder(BorderFactory.createEtchedBorder());
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (!gameDone) {
                    turn(e.getX(), e.getY());
                    frame.configPanel.updatePlayerLabels(currentPlayer);
                    drawVertices();
                    repaint();
                } else {
                    int choice = JOptionPane.showOptionDialog(DrawingPanel.this,
                            "Game Over! Player " + currentPlayer + " wins!",
                            "Game Over",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.INFORMATION_MESSAGE,
                            null,
                            new String[]{"New Game", "Exit"},
                            "New Game");
                    if (choice == 0) {
                        // Start a new game
                        resetGame();
                    } else {
                        // Exit the program
                        System.exit(0);
                    }
                }


            }
        });
    }

    private void createOffscreenImage() {
        image = new BufferedImage(W, H, BufferedImage.TYPE_INT_ARGB);
        graphics = image.createGraphics();
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, 800, 600);
    }

    final void createBoard() {
        numVertices = (Integer) frame.configPanel.dotsField.getValue();
        edgeProbability = (Double) frame.configPanel.linesCombo.getSelectedItem();
        frame.configPanel.updatePlayerLabels(currentPlayer);
        createOffscreenImage();
        createVertices();
        drawLines();
        drawVertices();
        repaint();
    }

    private void drawVertices() {
        for (int i = 0; i < numVertices; i++) {
            if (vertexColor[i] == 1) {
                graphics.setColor(Color.RED);
            } else if (vertexColor[i] == 2) {
                graphics.setColor(Color.GREEN);
            } else {
                graphics.setColor(Color.BLACK);
            }
            graphics.fillOval(x[i], y[i], 10, 10);
        }
    }

    private void drawLines() {
        graphics.setColor(Color.BLACK);
        connectedVertices = new ArrayList[numVertices];
        for(int i= 0; i < numVertices; i++){
            connectedVertices[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < numVertices; i++) {
            for (int j = i + 1; j < numVertices; j++) {
                if (Math.random() < edgeProbability) {
                    graphics.drawLine(x[i] + 5, y[i] + 5, x[j] + 5, y[j] + 5);
                    // add the vertices to the list
                    connectedVertices[i].add(j);
                    connectedVertices[j].add(i);
                }
            }
        }
    }

    private void createVertices() {
        int x0 = W / 2;
        int y0 = H / 2; //middle of the board
        int radius = H / 2 - 10; //board radius
        double alpha = 2 * Math.PI / numVertices; // the angle
        x = new int[numVertices];
        y = new int[numVertices];
        vertexColor = new int[numVertices];
        Arrays.fill(vertexColor, 0);
        for (int i = 0; i < numVertices; i++) {
            x[i] = x0 + (int) (radius * Math.cos(alpha * i));
            y[i] = y0 + (int) (radius * Math.sin(alpha * i));
        }
    }

    private void turn(int X, int Y) {
        for (int i = 0; i < numVertices; i++) {
            if (X >= x[i] && X <= x[i] + 10 && Y >= y[i] && Y <= y[i] + 10) {
                // Check if the vertex is uncolored
                if (vertexColor[i] == 0) {
                    // Set the color of the vertex based on the current player's turn
                    if (currentPlayer == 1) {
                        vertexColor[i] = 1; //Player 1
                    } else {
                        vertexColor[i] = 2; //Player 2
                    }
                }
                currentPlayer = 3 - currentPlayer; // 3-2 = 1, 3-1 = 2;
                if(checkTriangle(currentPlayer)) gameDone = true;
            }
        }
    }

    private boolean checkTriangle(int currentPlayer) {
        for (int i = 0; i < numVertices; i++) {
            if (vertexColor[i] == currentPlayer) {
                for (int j : connectedVertices[i]) {
                    if (vertexColor[j] == currentPlayer) {
                        for (int k : connectedVertices[j]) {
                            if (vertexColor[k] == currentPlayer) {

                                if(k!=i && vertexColor[k] == currentPlayer && connectedVertices[k].contains(i)){
                                    // Check if the three vertices form a triangle
                                    double distance1 = Math.sqrt(Math.pow(x[i] - x[j], 2) + Math.pow(y[i] - y[j], 2));
                                    double distance2 = Math.sqrt(Math.pow(x[i] - x[k], 2) + Math.pow(y[i] - y[k], 2));
                                    double distance3 = Math.sqrt(Math.pow(x[j] - x[k], 2) + Math.pow(y[j] - y[k], 2));
                                    if (distance1 + distance2 > distance3 && distance1 + distance3 > distance2 && distance2 + distance3 > distance1) {
                                        // Color the winning triangle
                                        graphics.setColor(currentPlayer == 1 ? Color.RED : Color.GREEN);
                                        graphics.fillPolygon(new int[]{x[i]+5, x[j]+5, x[k]+5}, new int[]{y[i]+5, y[j]+5, y[k]+5}, 3);
                                        return true;
                                    }
                                }

                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override
    public void update(Graphics g) {
    } //No need for update

    //Draw the offscreen image, using the original graphics
    @Override
    protected void paintComponent(Graphics graphics) {
        graphics.drawImage(image, 0, 0, this);
    }

    public BufferedImage getImage() {
        BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D game = image.createGraphics();
        paint(game);
        game.dispose();
        return image;
    }

    public void resetGame() {
        // Reset the board to its initial state
        for (int i = 0; i < numVertices; i++) {
            vertexColor[i] = 0;
        }
        currentPlayer = 1;
        connectedVertices = new ArrayList[numVertices];
        for(int i= 0; i < numVertices; i++){
            connectedVertices[i] = new ArrayList<Integer>();
        }
        gameDone = false;
        createOffscreenImage();
        createVertices();
        drawLines();
        drawVertices();
        repaint();

    }

    public void loadGame(GameState gameState){
        this.numVertices = gameState.getNumVertices();
        this.edgeProbability = gameState.getEdgeProbability();
        this.x = gameState.getX();
        this.y = gameState.getY();
        this.connectedVertices = gameState.getConnectedVertices();
        this.vertexColor = gameState.getVertexColor();
        this.currentPlayer = gameState.getCurrentPlayer();
        this.gameDone = gameState.isGameDone();
        createOffscreenImage();
        createFromLoadVertices();
        drawFromLoadLines();
        drawFromLoadVertices();
        repaint();
    }

    private void drawFromLoadVertices() {
        for (int i = 0; i < numVertices; i++) {
            if (vertexColor[i] == 1) {
                graphics.setColor(Color.RED);
            } else if (vertexColor[i] == 2) {
                graphics.setColor(Color.GREEN);
            } else {
                graphics.setColor(Color.BLACK);
            }
            graphics.fillOval(x[i], y[i], 10, 10);
        }
    }

    private void drawFromLoadLines() {
        graphics.setColor(Color.BLACK);
        for (int i = 0; i < numVertices; i++) {
                for (int j : connectedVertices[i]) {
                    graphics.drawLine(x[i] + 5, y[i] + 5, x[j] + 5, y[j] + 5);
                }

        }
    }

    private void createFromLoadVertices() {
        int x0 = W / 2;
        int y0 = H / 2; //middle of the board
        int radius = H / 2 - 10; //board radius
        double alpha = 2 * Math.PI / numVertices; // the angle
        for (int i = 0; i < numVertices; i++) {
            x[i] = x0 + (int) (radius * Math.cos(alpha * i));
            y[i] = y0 + (int) (radius * Math.sin(alpha * i));
        }
    }

    public int getNumVertices() {
        return numVertices;
    }

    public double getEdgeProbability() {
        return edgeProbability;
    }


    public int[] getXcoord() {
        return x;
    }

    public int[] getYcoord() {
        return y;
    }


    public ArrayList<Integer>[] getConnectedVertices() {
        return connectedVertices;
    }

    public int[] getVertexColor() {
        return vertexColor;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public boolean isGameDone() {
        return gameDone;
    }
}