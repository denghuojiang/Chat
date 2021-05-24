package com.hsany.bio.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {
    public static void main(String[] args) throws IOException {
        ChatServer cs = new ChatServer();
        cs.setUpServer(9000);
    }

    private void setUpServer(int port) throws IOException {
        ServerSocket server = new ServerSocket(port);
        System.out.println("服务器创建成功端口号"+port);
        while (true){
            Socket socket = server.accept();
            System.out.println("一个客户端连接");
            ServerThread serverThread = new ServerThread(socket);
            serverThread.start();
        }
    }
}
