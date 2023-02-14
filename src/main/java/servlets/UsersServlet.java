package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UsersServlet", value = "/users")
public class UsersServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("content-type", "text/html; charset=UTF-8");
        request.getRequestDispatcher("/WEB-INF/usersServlet.jsp").include(request, response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) {

    }
    public void doDelete(HttpServletRequest request, HttpServletResponse response) {

    }

    public void doPut(HttpServletRequest request, HttpServletResponse response) {

    }
}
