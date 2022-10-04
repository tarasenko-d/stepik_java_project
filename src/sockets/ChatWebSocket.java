package sockets;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import service.ChatServiceI;
import service.DBServiceI;

import java.util.Locale;

@SuppressWarnings("UnusedDeclaration")
@WebSocket
public class ChatWebSocket {

    String login;

    private ChatServiceI chatService;
    private Session session;
    private final DBServiceI dbService;

    public ChatWebSocket(ChatServiceI chatService, DBServiceI dbService) {
        this.chatService = chatService;
        this.dbService = dbService;
    }

    @OnWebSocketConnect
    public void onOpen(Session session) {
        System.out.println("On open");
        chatService.add(this);
        this.session = session;
    }

    @OnWebSocketMessage
    public void onMessage(Session session, String data) {
        String login = data.substring(0, data.indexOf(':'));
        String message = data.substring(data.indexOf(':') + 1);
        if (!dbService.isBanned(login)) {
            chatService.sendMessage(login, message);
        }
    }

    @OnWebSocketClose
    public void onClose(int statusCode, String reason) {
        chatService.remove(this);
    }

    public void sendString(String login, String message) {
        try {
            String check = message.toLowerCase(Locale.ROOT);
            if (message.matches(".*миэт.*")) {
                dbService.banUser(login);
                throw new IllegalStateException("ny eto ban");
            }
            session.getRemote().sendString(login + ":" + message);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
