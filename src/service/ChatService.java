package service;

import sockets.ChatWebSocket;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ChatService implements ChatServiceI {

    private Set<ChatWebSocket> webSockets;

    public ChatService() {
        this.webSockets = Collections.newSetFromMap(new ConcurrentHashMap<>());
    }

    @Override
    public void sendMessage(String login,String message) {
        for (ChatWebSocket user : webSockets) {
            try {
                user.sendString(login,message);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public void add(ChatWebSocket webSocket) {
        System.out.println("On Add");
        webSockets.add(webSocket);
    }

    @Override
    public void remove(ChatWebSocket webSocket) {
        webSockets.remove(webSocket);
    }

}
