package com.network.ch01;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class UseInetAddress {
    public static void main(String[] args) {
        try {
            InetAddress inetAddress = InetAddress.getByName("www.naver.com");
            System.out.println(inetAddress);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }
}