package service;

import sockets.ChatWebSocket;


public interface ChatServiceI {

    void sendMessage(String login,String message);

    void add(ChatWebSocket webSocket);

    void remove(ChatWebSocket webSocket);
}
