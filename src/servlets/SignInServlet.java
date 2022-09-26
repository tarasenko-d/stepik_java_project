package servlets;

import org.eclipse.jetty.websocket.api.Session;
import service.DBService;
import service.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class SignInServlet extends HttpServlet {
    private final DBService dbService;

    public SignInServlet(DBService dbService) {
        this.dbService = dbService;
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws IOException, ServletException {

        String login = request.getParameter("login");
        String pass = request.getParameter("pass");

        if (login == null) {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        if(dbService.CheckUser(login,pass)){
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().println("Unauthorized");
            return;
        }
        response.setContentType("text/html;charset=utf-8");

       /*   Cookie[] cookies = request.getCookies();
        String cookieName = "sos";
        if(cookies !=null) {
            for(Cookie c: cookies) {
                if(cookieName.equals(c.getName())) {
                    System.out.println(c.getValue());
                    break;
                }
            }
        }

      Cookie cookie = new Cookie("login",login);
        cookie.setMaxAge(-1);
        response.addCookie(cookie);*/

        doGet(request,response);

       /*
        // response.setStatus(HttpServletResponse.SC_OK);
        // response.getWriter().println("Authorized: " + login+"\n");
        response.getWriter().println("authorized");
        response.getWriter().println(PageGenerator.instance().getPage("signin_page.html"));
        response.getWriter().println("authorized");

        String path = "/chat.html";
        ServletContext servletContext = getServletContext();
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(path);
        requestDispatcher.forward();

        dispatcher = req.getRequestDispatcher(index).forward(request, response);
        */
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws IOException{
        response.getWriter().println(PageGenerator.instance().getPage("sign_in.html",null));
    }

    //get logged user profile
   /* public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        String sessionId = request.getSession().getId();
        System.out.println("This is GET");
        UserProfile profile = accountService.getUserBySessionId(sessionId);
        if (profile == null) {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        } else {
            Gson gson = new Gson();
            String json = gson.toJson(profile);
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println(json);
            response.setStatus(HttpServletResponse.SC_OK);
        }
    }
    //sign out
    public void doDelete(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        String sessionId = request.getSession().getId();
        response.getWriter().println("This is DELETE");
        UserProfile profile = accountService.getUserBySessionId(sessionId);
        if (profile == null) {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        } else {
            accountService.deleteSession(sessionId);
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println("Goodbye!");
            response.setStatus(HttpServletResponse.SC_OK);
        }

    }
    */

}
