package servlets;

import dao.ProducerDao;
import models.Producer;
import utils.UserUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "MainServlet", value = "/")
public class MainServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (!UserUtils.checkAuthUser(request)) {
            response.sendRedirect("/login");
            return;
        }

        response.setHeader("content-type", "text/html; charset=UTF-8");
        request.getRequestDispatcher("/WEB-INF/mainServlet.jsp").include(request, response);
    }


}
