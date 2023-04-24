package org.example;

public class Timekeeper extends Thread{
    private final long timeLimit;
    private final Exploration exploration;

    public Timekeeper(long timeLimit, Exploration exploration){
        this.timeLimit = timeLimit;
        this.exploration = exploration;
        setDaemon(true);
    }

    @Override
    public void run(){
        long startTime = System.currentTimeMillis();
        long elapsedTime;
        do{
            try{
                Thread.sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            elapsedTime = System.currentTimeMillis() -startTime;
        } while(elapsedTime < timeLimit);
        System.out.println("Exploration time limit exceeded. Stopped exploring");
        exploration.stop();
    }
}
