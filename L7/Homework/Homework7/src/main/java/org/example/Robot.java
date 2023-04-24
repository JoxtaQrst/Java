package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Robot implements Runnable {

    private final String name;
    private boolean running;
    private boolean paused;
    private int row;
    private int col;
    private Cell cell;
    private final Exploration explore;
    private final Random rand = new Random();

    private int tokensPlaced = 0;


    public Robot(String name, Exploration explore) {
        this.name = name;
        this.explore = explore;
    }

    public void setTokensPlaced (int n){
        this.tokensPlaced = this.tokensPlaced + n ;
    }

    @Override
    public void run() {
        while (running) {
            int n = explore.getMap().getSize();
            int newRow = rand.nextInt(n);
            int newCol = rand.nextInt(n);
            explore.getMap().visit(row, col, this);
            row = newRow;
            col = newCol;
            extractTokens(row, col);
            //make the robot sleep for some time
            try {
                Thread.sleep(rand.nextInt(5000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(explore.getMap().allCellsVisited()){
               explore.stop();
            }

        }
    }

    public int getTokensPlaced(){
        return this.tokensPlaced;
    }

    public void start() {
        running = true;
        new Thread(this, name).start();
        System.out.println(name + " has started exploring!");
    }

    public void stop() {
        running = false;
        System.out.println(name + " has stopped exploring! Placed " + getTokensPlaced() + " tokens " );
    }
    public void pause() {
        paused = true;
        System.out.println(name + " has been paused!");
    }

    public void resume() {
        paused = false;
        synchronized (this) {
            notifyAll();
        }
        System.out.println(name + " has been resumed!");
    }
    public String getName() {
        return name;
    }

    public int[] getPosition() {
        return new int[]{row, col};
    }

    public List<Token> extractTokens(int row, int col) {
        List<Token> tokens = new ArrayList<>();
        Cell cell1 = explore.getMap().getCell(row, col);
        if (cell != null && cell.getToken() != null) {
            tokens.add(cell.getToken());
            cell.setToken(null);
        }
        return tokens;
    }
}
