package servlets;

import lombok.NonNull;
import service.AccountServerI;
import service.DBServiceI;
import service.PageGenerator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignInServlet extends HttpServlet {
    private final DBServiceI dbService;
    private final AccountServerI accountServer;


    public SignInServlet(DBServiceI dbService, AccountServerI accountServer) {
        this.dbService = dbService;
        this.accountServer = accountServer;
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws IOException {

        String login = request.getParameter("login");
        String pass = request.getParameter("pass");


        int limit = accountServer.getUsersLimit();
        int count = accountServer.getUsersCount();


        if (limit <= count) {
            if (!accountServer.isOnline(login)) {
                System.out.println("User were rejected");
                response.getWriter().println("Server is closed for maintenance!");
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                return;
            }
        }

        if (dbService.CheckUser(login, pass)) {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().println("Unauthorized");
            return;
        }


        response.setContentType("text/html;charset=utf-8");
        if (!accountServer.isOnline(login)) {
            accountServer.addNewUser(login);
        }
        doGet(request, response);

    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws IOException {
        response.getWriter().println(PageGenerator.instance().getPage("sign_in.html", null));
    }

}

