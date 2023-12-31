package com.wd.demo.nio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * @Author: wangd
 * @Date: 2023/7/18 12:25
 */
public class TcpSocketServer {

    /**
     * 服务端程序
     */
    public void server() throws IOException {

        Scanner scanner = new Scanner(System.in);

        // 服务端监听 9528 端口
        ServerSocket serverSocket = new ServerSocket(9528);
        System.out.println("等待连接");
        Socket client = serverSocket.accept();
        System.out.println("连接成功！");
        while (true) {
            // 获取客户端输入流
            InputStream inputStream = client.getInputStream();
            byte[] bytes = new byte[1024];
            int read = inputStream.read(bytes);
            // 客户端发来的消息
            System.out.println("客户端：" + new String(bytes, 0, read, StandardCharsets.UTF_8));

            // 给客户端发端东西
            System.out.print("请输入：");
            String nextLine = scanner.next();
            if ("out".equals(nextLine)) {
                break;
            }
            client.getOutputStream().write(nextLine.getBytes(StandardCharsets.UTF_8));

        }


    }

    public static void main(String[] args) throws IOException {
        TcpSocketServer tcpSocketServer = new TcpSocketServer();
        tcpSocketServer.server();;
    }
}
