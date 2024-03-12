package com.network.ch01;

import java.io.IOException;
import java.net.InetAddress;

public class UseInetAddress {
    public static void main(String[] args) {
        try {
            InetAddress inetAddress = InetAddress.getByName("www.naver.com");
            if(inetAddress.isReachable(10000)) { // 접속 여부 확인
                System.out.println(inetAddress);
                System.out.println(inetAddress.getCanonicalHostName());
                System.out.println(inetAddress.getHostName());
            } else {
                System.out.println("connect fail");
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}