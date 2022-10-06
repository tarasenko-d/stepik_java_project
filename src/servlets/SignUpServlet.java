package servlets;

import lombok.NonNull;
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

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws IOException {
        @NonNull
        String login = request.getParameter("login");
        @NonNull
        String pass = request.getParameter("pass");

        if (login.length() == 0 || pass.length() == 0) {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println("Unsupported format of login/password. Try again");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }


        if (dbService.doesntExists(login)) {
            dbService.addOnlyUser(login, pass);
            System.out.println("SignUpServlet: Added user: " + login);
            response.getWriter().println("HI, " + login + "!\n\nYou have registered.\n Your login: " + login +
                    "\n Your password: " + pass);
            response.setStatus(HttpServletResponse.SC_OK);
            return;
        }

        response.getWriter().println("User with nickname " + login + " already exists. Try another one");
        response.setStatus(HttpServletResponse.SC_OK);

    }
}
