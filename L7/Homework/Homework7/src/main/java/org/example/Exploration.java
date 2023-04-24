package org.example;

import java.util.ArrayList;
import java.util.List;

public class Exploration {


    private final ExplorationMap map;
    private final List<Robot> robots = new ArrayList<>();

    public Exploration(int n) {
        this.map=new ExplorationMap(n);
    }

    public void addRobot(Robot robot) {
        robots.add(robot);
    }
    public void start() {
        for (Robot robot : robots) {
           robot.start();
        }
    }

    public void stop(){
        for(Robot robot:robots){
            robot.stop();
        }
    }
    public void pause(){
        for(Robot robot:robots){
            robot.pause();
        }
    }
    public void resume(){
        for(Robot robot:robots){
            robot.resume();
        }
    }

    public ExplorationMap getMap() {
        return map;
    }
}
