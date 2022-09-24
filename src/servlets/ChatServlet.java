package servlets;

import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;
import service.ChatService;
import service.PageGenerator;
import sockets.ChatWebSocket;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "WebSocketChatServlet", urlPatterns = {"/chat"})
public class ChatServlet extends WebSocketServlet {
    private final static int LOGOUT_TIME = 10 * 60 * 1000;
    private final ChatService chatService;

    public ChatServlet() {
        this.chatService = new ChatService();
    }

    @Override
    public void configure(WebSocketServletFactory factory) {
        factory.getPolicy().setIdleTimeout(LOGOUT_TIME);
        factory.setCreator((req, resp) -> new ChatWebSocket(chatService));
    }

public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        response.getWriter().println(PageGenerator.instance().getPage("chat.html",null));
    }
}
   /* Cookie[] cookies = request.getCookies();
    String cookieName = "user_login";
        System.out.println("here");
                if(cookies !=null) {
                for(Cookie c: cookies) {
                if(cookieName.equals(c.getName())) {
                System.out.println("yes");
                break;
                }
                }
                }*/