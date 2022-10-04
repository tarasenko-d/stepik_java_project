package main;

import controller.AccountServerController;
import controller.AccountServerControllerMBean;
import service.*;
import servlets.ChatServlet;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.SignInServlet;
import servlets.SignUpServlet;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

public class Main {
    public static void main(String[] args) throws Exception {
        DBServiceI dbService = new DBService();

        Server server = new Server(8080);
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);

        AccountServerI accountServer = new AccountServer(1);
        //Creating a MBean
        AccountServerControllerMBean serverStatistics = new AccountServerController(accountServer);
        //Asking Factory for MBeanServer
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        //Name new MBean
        ObjectName name = new ObjectName("ServerManager:type=AccountServerController");
        //Save MBean on server
        mbs.registerMBean(serverStatistics, name);

     /* dbService.addUser("admin","admin");
        dbService.addUser("test","test"); */
        dbService.addOnlyUser("admin","admin");
        dbService.addOnlyUser("test","test");

        context.addServlet(new ServletHolder(new SignUpServlet(dbService)), "/signup");
        context.addServlet(new ServletHolder(new SignInServlet(dbService,accountServer)), "/signin");
        context.addServlet(new ServletHolder(new ChatServlet(dbService)), "/chat");

        ResourceHandler resource_handler = new ResourceHandler();
        resource_handler.setDirectoriesListed(true);
        resource_handler.setResourceBase("public_html");

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{resource_handler, context});
        server.setHandler(handlers);

        server.start();
        System.out.println("Server started!");
        server.join();
    }
}
