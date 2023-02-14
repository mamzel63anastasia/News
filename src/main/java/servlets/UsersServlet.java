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
import java.util.UUID;

@WebServlet(name = "UsersServlet", value = "/users")
public class UsersServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userDelete = request.getParameter("userDelete");
        if (userDelete != null) {
            UserDao userDao = new UserDao();
            User user = UserUtils.findUserByIdOrNull(userDao.getUsers(), userDelete);

            if (user != null) {
                UUID uuid = UUID.fromString(userDelete);
                userDao.deleteUser(uuid);
            } else {
                HttpSession session = request.getSession();
                session.setAttribute("messageInfo", "Не удалось найти такого пользователя");
            }

            response.sendRedirect("/users");
            return;
        }

        response.setHeader("content-type", "text/html; charset=UTF-8");
        request.getRequestDispatcher("/WEB-INF/usersServlet.jsp").include(request, response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        try {
            String login = request.getParameter("login");
            String pass = UserUtils.buildHash(request.getParameter("pass"));

            if (login != null && pass != null) {
                User user = new User();
                user.setLogin(login);
                user.setPassword(pass);

                UserDao userDao = new UserDao();
                List<User> users = userDao.getUsers();

                if (UserUtils.findUserByLoginOrNull(users, login) == null) {
                    userDao.addUser(user);

                }else {
                    session.setAttribute("messageInfo", "Такой пользователь уже существует");
                }
            } else {
                session.setAttribute("messageInfo", "Для создания пользователя нужно логин и пароль");
            }
        } catch (NoSuchAlgorithmException e) {
            session.setAttribute("messageInfo", "Ошибка на стороне сервера: " + e.getMessage());
        }
        response.sendRedirect("/users");
    }
    public void doDelete(HttpServletRequest request, HttpServletResponse response) {

    }

    public void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
            try {
                String userId = request.getParameter("userId");
                String login = request.getParameter("login");
                String pass = request.getParameter("pass");
                if (userId!= null && login != null && pass != null) {
                    pass = UserUtils.buildHash(pass);

                    UserDao userDao = new UserDao();
                    User user = UserUtils.findUserByIdOrNull(userDao.getUsers(), userId);
                    if (user != null) {
                        user.setPassword(pass);
                        userDao.updateUser(user);
                    } else {
                        session.setAttribute("messageInfo", "Не удалось найти пользователя в базе");
                    }
                } else {
                    session.setAttribute("messageInfo", "Не верный набор параметров");
                }
            } catch (NoSuchAlgorithmException e) {
                session.setAttribute("messageInfo", "Ошибка на стороне сервера: " + e.getMessage());
            }
        response.sendRedirect("/users");
    }
}
