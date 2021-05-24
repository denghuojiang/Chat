package com.hsany.bio.server;

import java.io.IOException;
import java.util.ArrayList;

public class ChatTools {
    private static ArrayList<ServerThread> stList = new ArrayList<>();

    private ChatTools() {
    }

    ;

    public static void addClient(ServerThread serverThread) throws IOException {
        stList.add(serverThread);
        castMsg(serverThread.getUser(), "我上线了！，目前人数:" + stList.size());
    }

    public static void castMsg(UserInfo user, String input) throws IOException {
        input = user.getUserName() + ":" + input;
        for (ServerThread serverThread : stList) {
            serverThread.sendMsg2Me(input);
        }
    }
}
