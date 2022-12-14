package servlets;

import service.PageGenerator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChatViewServlet extends HttpServlet {
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws IOException{
        String page = PageGenerator.instance().getPage("chat.html", null);
        response.getWriter().println(page);
    }
}
