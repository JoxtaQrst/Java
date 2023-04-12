package org.example;

import java.util.ArrayList;
import java.util.List;

public class Exploration {

    private final SharedMemory mem;
    private final ExplorationMap map = new ExplorationMap();
    private final List<Robot> robots = new ArrayList<>();

    public Exploration(SharedMemory mem) {
        this.mem=mem;
    }

    public void addRobot(Robot robot) {
        robots.add(robot);
    }
    public void start() {
        for (Robot robot : robots) {
           robot.start();
        }
    }

    public ExplorationMap getMap() {
        return map;
    }
}
