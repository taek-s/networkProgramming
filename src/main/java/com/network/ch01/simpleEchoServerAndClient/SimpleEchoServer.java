package com.network.ch01.simpleEchoServerAndClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleEchoServer {
    public static void main(String[] args) {
        Socket clientSocket = null;
        try(ServerSocket serverSocket = new ServerSocket(6000)) {
            System.out.println("Simple Echo Server");
            clientSocket = serverSocket.accept();
            System.out.println("Connected to client");

        } catch(IOException e) {
            System.out.println("ServerSocket Error : " + e);
        }

        if(null != clientSocket) {
            try(
                BufferedReader br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter pw = new PrintWriter(clientSocket.getOutputStream(), true)
            ) {
                String line;
                while((line = br.readLine()) != null) {
                    System.out.println("Server: " + line);
                    pw.println(line);
                }
            } catch (IOException e) {
                System.out.println("error : " + e);
            }
        }
    }
}
