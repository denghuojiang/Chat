package com.hsany.bio.server;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ServerThread extends Thread {

    private final Socket client;
    private OutputStream ous;
    private UserInfo user;

    public ServerThread(Socket client) throws IOException {
        this.client = client;
        this.ous = client.getOutputStream();
    }

    public UserInfo getUser() {
        return user;
    }

    @Override
    public void run() {
        try {
            processSocket();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendMsg2Me(String msg) throws IOException {
        msg += "\r\n";
        ous.write(msg.getBytes(StandardCharsets.UTF_8));
        ous.flush();

    }

    private void processSocket() throws IOException {
        InputStream ins = client.getInputStream();
        BufferedReader brd = new BufferedReader(new InputStreamReader(ins));
        sendMsg2Me("欢迎来到聊天室，请输入用户名");
        String userName = brd.readLine();
        sendMsg2Me("请输入密码");
        String password = brd.readLine();
        user = new UserInfo(userName);
        user.setPassword(password);
        boolean loginState  = DaoTools.checkLogin(user);
        if(!loginState){
            this.closeMe();
                return;
        }
        ChatTools.addClient(this);
        String input = brd.readLine();
        while (!input.equals("bye")){
            System.out.println("服务器读到数据");
            ChatTools.castMsg(this.user,input);
            input=brd.readLine();
        }
        ChatTools.castMsg(this.user,"bye");
        this.closeMe();

    }

    private void closeMe() throws IOException {
        client.close();;
    }
}
