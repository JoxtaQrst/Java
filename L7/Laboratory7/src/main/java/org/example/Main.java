package org.example;

public class Main {
    public static void main(String[] args) {

        var explore = new Exploration(new SharedMemory(10));
        explore.addRobot(new Robot("RD-D2", explore));
        explore.addRobot(new Robot("Ionut", explore));
        explore.addRobot(new Robot("C3PO", explore));
        explore.start();
    }
}