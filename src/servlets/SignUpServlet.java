package servlets;

import service.DBServiceI;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignUpServlet extends HttpServlet {
    @SuppressWarnings({"FieldCanBeLocal", "UnusedDeclaration"})
    private final DBServiceI dbService;

    public SignUpServlet(DBServiceI dbService) {
        this.dbService = dbService;
    }

    //register
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws IOException {
        String login = request.getParameter("login");
        String pass = request.getParameter("pass");

        if (login == null || pass == null) {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        dbService.addOnlyUser(login, pass);
        System.out.println("SignUpServlet: Added user: " + login);
        response.getWriter().println("HI, " + login + "!\n\nYou have registered.\n Your login: " + login +
                "\n Your password: " + pass);

        response.setStatus(HttpServletResponse.SC_OK);
    }
}
