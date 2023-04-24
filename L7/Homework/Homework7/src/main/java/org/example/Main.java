package org.example;
import javax.sound.midi.Soundbank;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

        int n = 3;
        var explore = new Exploration(n);
        explore.addRobot(new Robot("RD-D2", explore));
        explore.addRobot(new Robot("Ionut", explore));
        explore.addRobot(new Robot("C3PO", explore));
        Timekeeper zilean = new Timekeeper(60000,explore);
        Thread timekeeperThread = new Thread(zilean);
        timekeeperThread.setDaemon(true); // set the timekeeper thread as daemon

        Scanner scanner = new Scanner(System.in);
        boolean exploring = true;

        while(exploring){
            System.out.println("Enter a command -start/stop/pause/resume-");
            String command = scanner.nextLine();

            switch (command.toLowerCase()) {
                case "start" -> {
                    zilean.start();
                    explore.start();
                }
                case "stop" -> explore.stop();
                case "pause" -> explore.pause();
                case "resume" -> explore.resume();
                case "quit" -> {
                    explore.stop();
                    exploring = false;
                    timekeeperThread.interrupt();
                }
                default -> System.out.println("Invalid command! Please try again.");
            }
        }
        scanner.close();

    }
}