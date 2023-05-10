package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

class ClientThread extends Thread {
    private Socket clientSocket;
    private ServerSocket serverSocket;

    public ClientThread(Socket clientSocket,ServerSocket serverSocket) {
        this.clientSocket = clientSocket;
        this.serverSocket = serverSocket;
    }

    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
            String request = in.readLine();
            //send the message
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream());
           String inputLine ;
           while((inputLine = in.readLine())!=null){
               out.println(inputLine);
               if(inputLine.equals("stop")){
                   out.println("Server stoppped");
                   serverSocket.close();

               }
               else if(inputLine.equals("exit")){
                   out.println("Server exit");
                   serverSocket.close();
                   break;
               }
               else{
                   out.println("Server recieved " +inputLine);
                   System.out.println("I have recieced " + inputLine);
               }

           }

        } catch (IOException e) {
            System.err.println("Communication error ... " + e);
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}