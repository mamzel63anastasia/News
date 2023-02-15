package servlets;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import dao.UserDao;
import models.User;
import utils.ResponseData;
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

@WebServlet(name = "UsersServlet", value = "/users")
public class UsersServlet extends HttpServlet {
    private final Gson gson = new Gson();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!UserUtils.checkAuthUser(request)) {
            response.sendRedirect("/login");
            return;
        }

        response.setHeader("content-type", "text/html; charset=UTF-8");
        request.getRequestDispatcher("/WEB-INF/usersServlet.jsp").include(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        ResponseData responseData = new ResponseData();

        try {
            JsonReader jsonReader = new JsonReader(request.getReader());
            User userData = gson.fromJson(jsonReader, User.class);

            String fio = userData.getFio();
            String login = userData.getLogin();
            String pass = UserUtils.buildHash(userData.getPassword());

            if (login != null && pass != null && fio != null) {
                User user = new User();
                user.setFio(fio);
                user.setLogin(login);
                user.setPassword(pass);

                UserDao userDao = new UserDao();
                List<User> users = userDao.getUsers();

                if (UserUtils.findUserByLoginOrNull(users, login) == null) {
                    userDao.addUser(user);

                } else {
                    responseData.setMessage("Такой пользователь уже существует");
                }
            } else {
                responseData.setMessage("Для создания пользователя нужно логин и пароль");
            }
        } catch (NoSuchAlgorithmException e) {
            responseData.setMessage("Ошибка на стороне сервера: " + e.getMessage());
        }

        responseData.setLocation("/users");
        response.getWriter().print(responseData);
    }

    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");

        ResponseData responseData = new ResponseData();

        JsonReader jsonReader = new JsonReader(request.getReader());
        User userData = gson.fromJson(jsonReader, User.class);

        Long userDelete = userData.getId();

        if (userData.getId() != null) {
            HttpSession session = request.getSession();
            User self = (User) session.getAttribute("auth");
            if (!self.getId().equals(userDelete)) {
                UserDao userDao = new UserDao();
                userDao.deleteUser(userDelete);
                responseData.setLocation("/users");
            } else {
                responseData.setMessage("Вы не можете удалить своего пользователя");
            }
        } else {
            responseData.setMessage("Не верный набор параметров");
        }

        response.getWriter().print(responseData);
    }

    public void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        ResponseData responseData = new ResponseData();

        try {
            JsonReader jsonReader = new JsonReader(request.getReader());
            User userData = gson.fromJson(jsonReader, User.class);

            Long userId = userData.getId();
            String login = userData.getLogin();
            String pass = userData.getPassword();
            if (userData.getId() != null && login != null && pass != null) {
                pass = UserUtils.buildHash(pass);

                UserDao userDao = new UserDao();
                User user = userDao.getUser(userId);
                if (user != null) {
                    user.setPassword(pass);
                    userDao.updateUser(user);
                } else {
                    responseData.setMessage("Не удалось найти пользователя в базе");
                }
            } else {
                responseData.setMessage("Не верный набор параметров");
            }
        } catch (NoSuchAlgorithmException e) {
            responseData.setMessage("Ошибка на стороне сервера: " + e.getMessage());
        }
        responseData.setLocation("/users");

        response.getWriter().print(responseData);
    }
}
