package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class GameClient {
    public GameClient(String serverAddress, int port) {
        try{
            Socket socket = new Socket(serverAddress, port);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));


            BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
            String inputLine;
            while((inputLine = inputReader.readLine())!=null){
                out.println(inputLine);
                if(inputLine.equals("exit")){
                    break;
                }
                System.out.println(in.readLine());
            }
            //wait for the response from server
            String response = in.readLine();
            System.out.println(response);
        }catch (IOException e){
            System.err.println("No server listening..." + e);
        }

    }
}
