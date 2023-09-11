package com.wd.demo.nio;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class SocketClient {
    public static void main(String args[]) throws Exception {
        // 要连接的服务端IP地址和端口
        String host = "127.0.0.1";
        int port = 55533;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            // 与服务端建立连接
            Socket socket = new Socket(host, port);

            // 建立连接后获得输出流
            OutputStream outputStream = socket.getOutputStream();
            String message = scanner.next();
            socket.getOutputStream().write(message.getBytes(StandardCharsets.UTF_8));


            // // 读一下服务端发来的东西
            // byte[] bytes = new byte[1024];
            // InputStream inputStream = socket.getInputStream();
            // int len = inputStream.read(bytes);
            // System.out.println("from server" + new String(bytes, 0, len, StandardCharsets.UTF_8));
            // inputStream.close();


            outputStream.close();
            socket.close();
        }
    }
}