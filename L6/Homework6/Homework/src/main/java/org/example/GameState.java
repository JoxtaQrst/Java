package org.example;

import java.io.*;
import java.util.ArrayList;

public class GameState implements Serializable {
    @Serial
    private  static final long serialVersionUID = 1L;

    private int numVertices;
    private double edgeProbability;
    private int[] x, y;
    private ArrayList<Integer>[] connectedVertices;
    private int[] vertexColor;
    private int currentPlayer;
    private boolean gameDone;

    public GameState(int numVertices, double edgeProbability, int[] x, int[] y, ArrayList<Integer>[] connectedVertices, int[] vertexColor, int currentPlayer, boolean gameDone) {
        this.numVertices = numVertices;
        this.edgeProbability = edgeProbability;
        this.x = x;
        this.y = y;
        this.connectedVertices = connectedVertices;
        this.vertexColor = vertexColor;
        this.currentPlayer = currentPlayer;
        this.gameDone = gameDone;
    }

    public int getNumVertices() {
        return numVertices;
    }

    public void setNumVertices(int numVertices) {
        this.numVertices = numVertices;
    }

    public double getEdgeProbability() {
        return edgeProbability;
    }

    public void setEdgeProbability(double edgeProbability) {
        this.edgeProbability = edgeProbability;
    }

    public int[] getX() {
        return x;
    }

    public void setX(int[] x) {
        this.x = x;
    }

    public int[] getY() {
        return y;
    }

    public void setY(int[] y) {
        this.y = y;
    }

    public ArrayList<Integer>[] getConnectedVertices() {
        return connectedVertices;
    }

    public void setConnectedVertices(ArrayList<Integer>[] connectedVertices) {
        this.connectedVertices = connectedVertices;
    }

    public int[] getVertexColor() {
        return vertexColor;
    }

    public void setVertexColor(int[] vertexColor) {
        this.vertexColor = vertexColor;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(int currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public boolean isGameDone() {
        return gameDone;
    }

    public void setGameDone(boolean gameDone) {
        this.gameDone = gameDone;
    }

    // save the game state to a file using object serialization
    public void saveToFile(String filename) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(this);
            System.out.println("Game state saved to " + filename);
        } catch (IOException e) {
            System.err.println("Failed to save game state to " + filename);
            e.printStackTrace();
        }
    }

    // load the game state from a file using object deserialization
    public static GameState loadFromFile(String filename) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            GameState gameState = (GameState) in.readObject();
            System.out.println("Game state loaded from " + filename);
            return gameState;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Failed to load game state from " + filename);
            e.printStackTrace();
            return null;
        }
    }
}
