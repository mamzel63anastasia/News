package servlets;

import dao.UserDao;
import models.User;
import utils.UserUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@WebServlet(name = "RegistrationServlet", value = "/registration")
public class RegistrationServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("content-type", "text/html; charset=UTF-8");
        request.getRequestDispatcher("/WEB-INF/registrationServlet.jsp").include(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();

        try {
            String login = request.getParameter("login");
            String pass = UserUtils.buildHash(request.getParameter("pass"));

            UserDao userDao = new UserDao();
            User user = new User();
            user.setLogin(login);
            user.setPassword(pass);

            userDao.addUser(user);

            session.setAttribute("auth", user);
            response.sendRedirect("/");

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }



    }
}
