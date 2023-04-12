package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Robot implements Runnable{

    private final String name;
    private boolean running;
    private int row;
    private int col;
    private Cell cell;
    private Exploration explore;
    private final Random rand = new Random();

    public Robot(String name, Exploration explore){
        this.name=name;
        this.explore = explore;
    }

    @Override
    public void run() {
        while (running){
            int n = explore.getMap().getSize();
            int newRow = rand.nextInt(n);
            int newCol = rand.nextInt(n);
            explore.getMap().visit(row, col, this);
            row = newRow;
            col = newCol;
            extractTokens(row,col);
            //make the robot sleep for some time
            try{
                Thread.sleep(rand.nextInt(5000));
            }catch (InterruptedException e){
                e.printStackTrace();
            }


        }
    }

    public void start(){
        running = true;
        new Thread(this,name).start();
    }

    public void stop(){
        running=false;
        System.out.println(name + " has stopped exploring!");
    }
    public String getName() {
        return name;
    }

    public int[] getPosition(){
        return new int[]{row,col};
    }

    public List<Token> extractTokens(int row,int col) {
        List<Token> tokens = new ArrayList<>();
        //extract all tokens in the cell
        return tokens;
    }
}
