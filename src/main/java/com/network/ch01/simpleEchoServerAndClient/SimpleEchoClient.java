package com.network.ch01.simpleEchoServerAndClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class SimpleEchoClient {
    public static void main(String[] args) {
        System.out.println("Simple Echo Client Start");
        System.out.println("Waiting for connection.....");
        try(
            Socket clientSocket = new Socket(InetAddress.getLocalHost(), 6000);
            BufferedReader br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter pw = new PrintWriter(clientSocket.getOutputStream(), true)
        ) {
            System.out.println("Connected to server");
            Scanner scanner = new Scanner(System.in);
            while(true) {
                System.out.println("Enter text: ");
                String readLine = scanner.nextLine();
                if("quit".equals(readLine)) {
                    System.out.println("Disconnect!");
                    break;
                }
                pw.println(readLine);
                System.out.println("Server response: " + br.readLine());
            }
        } catch(IOException e) {
            System.out.println("Client Error : " + e);
        }
        System.out.println("Simple Echo Client End");
    }
}
