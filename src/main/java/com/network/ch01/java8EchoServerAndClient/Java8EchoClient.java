package com.network.ch01.java8EchoServerAndClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Java8EchoClient {
    public static void main(String[] args) {
        System.out.println("Java8 Echo Client Start");
        System.out.println("Waiting for connection.....");
        try(
            Socket clientSocket = new Socket(InetAddress.getLocalHost(), 6001);
            BufferedReader br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter pw = new PrintWriter(clientSocket.getOutputStream(), true)
        ) {
            System.out.println("Connected to server");
            Scanner scanner = new Scanner(System.in);
            Supplier<String> scannerInput = scanner::next;
            System.out.println("Enter text: ");
            Stream.generate(scannerInput)
                    .map(s -> {
                        pw.println(s);
                        try {
                            System.out.println("Server response: " + br.readLine());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        if(!"quit".equals(s)) {
                            System.out.println("Enter text: ");
                        }
                        return s;
                    })
                    .noneMatch("quit"::equalsIgnoreCase);

        } catch(IOException e) {
            System.out.println("Client Error : " + e);
        }
        System.out.println("Java8 Echo Client End");
    }
}
