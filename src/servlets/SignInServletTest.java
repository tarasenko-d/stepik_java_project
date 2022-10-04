package servlets;

import org.junit.jupiter.api.Test;
import service.AccountServerI;
import service.DBService;
import service.DBServiceI;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SignInServletTest {

    private AccountServerI getMockAccountServer(int limit) {
        AccountServerI accountMock = mock(AccountServerI.class);
        when(accountMock.getUsersLimit()).thenReturn(limit);
        when(accountMock.isOnline("admin")).thenReturn(true);
        return accountMock;
    }

    private DBServiceI getDBServiceMock() {
        DBServiceI dbServiceMock = mock(DBServiceI.class);
        when(dbServiceMock.CheckUser("admin", "admin")).thenReturn(true);
        return dbServiceMock;
    }

    private HttpServletRequest getMockRequest() {
        HttpServletRequest requestMock = mock(HttpServletRequest.class);
        when(requestMock.getParameter("login")).thenReturn("admin");
        when(requestMock.getParameter("pass")).thenReturn("admin");
        return requestMock;
    }

    private HttpServletResponse getMockResponse(StringWriter stringWriter) throws IOException {
        HttpServletResponse responseMock = mock(HttpServletResponse.class);
        final PrintWriter writer = new PrintWriter(stringWriter);
        when(responseMock.getWriter()).thenReturn(writer);
        return responseMock;
    }

    @Test
    void postTest() throws IOException, ServletException {
        StringWriter stringWriter = new StringWriter();
        AccountServerI accountServerMock = getMockAccountServer(3);
        DBServiceI dbServiceMock = getDBServiceMock();
        HttpServletResponse responseMock = getMockResponse(stringWriter);
        HttpServletRequest requestMock = getMockRequest();

        SignInServlet signInServlet = new SignInServlet(dbServiceMock,accountServerMock);

        signInServlet.doPost(requestMock,responseMock);

        assertEquals("Unauthorized", stringWriter.toString().trim());

    }

}