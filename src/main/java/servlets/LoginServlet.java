package servlets;

import dao.UserDao;
import models.User;
import utils.UserUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("content-type", "text/html; charset=UTF-8");
        request.getRequestDispatcher("/WEB-INF/loginServlet.jsp").include(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            HttpSession session = request.getSession();

            String login = request.getParameter("login");
            String pass = UserUtils.buildHash(request.getParameter("pass"));

            UserDao userDao = new UserDao();

            List<User> users = userDao.getUsers(login, pass);
            if (!users.isEmpty()) {

                session.setAttribute("auth", users.get(0));
                response.sendRedirect("/");
            } else {
                session.setAttribute("messageInfo", "Не удалось найти пользователя");

                response.setHeader("content-type", "text/html; charset=UTF-8");
                request.getRequestDispatcher("/WEB-INF/loginServlet.jsp").include(request, response);
            }
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

    }
}
