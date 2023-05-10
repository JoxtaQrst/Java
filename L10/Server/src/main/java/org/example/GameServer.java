package org.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class GameServer {

    private ServerSocket serverSocket;
    public GameServer(int port){
        try{
            serverSocket = new ServerSocket(port);
            boolean isRunning = true;
            System.out.println("Server started on port " + port);
            while(isRunning){
                Socket socket = serverSocket.accept();
                System.out.println("Client connected!");
                new ClientThread(socket,serverSocket).start();

            }
        }catch (IOException e){
            System.err.println("Ooops..  " + e);
        }
    }

    public void stop() throws IOException {
        System.out.println("SERVER STOPPED");
        serverSocket.close();
        System.exit(0);
    }

}
