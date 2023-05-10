package org.example;

public class Main {
    public static void main(String[] args) {
        String serverAddress = "127.0.0.1";
        int port = 8100;
        GameClient gameClient = new GameClient(serverAddress, port);
    }
}