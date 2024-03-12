package com.network.ch01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class UseUrlConnection2 {
    public static void main(String[] args) {
        try {
            URL url = new URL("https://www.google.com");
            URLConnection urlConnection = url.openConnection();
            ReadableByteChannel readableByteChannel = Channels.newChannel(urlConnection.getInputStream());
            ByteBuffer byteBuffer = ByteBuffer.allocate(64);

            while(readableByteChannel.read(byteBuffer) > 0) {
                System.out.println(new String(byteBuffer.array()));
                byteBuffer.clear();
            }
            readableByteChannel.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
