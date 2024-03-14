package com.network.ch01.java8EchoServerAndClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Java8EchoServer {
    public static void main(String[] args) {
        Socket clientSocket = null;
        try(ServerSocket serverSocket = new ServerSocket(6001)) {
            System.out.println("Java8 Echo Server");
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
                Supplier<String> socketInput = () -> {
                  try {
                      return br.readLine();
                  } catch (IOException e) {
                      return null;
                  }
                };

                Stream.generate(socketInput)
                        .map(s -> {
                            System.out.println("Server: " + s);
                            pw.println(s);
                            return s;
                        })
                        .allMatch(Objects::nonNull);
            } catch (IOException e) {
                System.out.println("error : " + e);
            }
        }
    }
}
