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

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    private final Gson gson = new Gson();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("content-type", "text/html; charset=UTF-8");
        request.getRequestDispatcher("/WEB-INF/loginServlet.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        ResponseData responseData = new ResponseData();
        try {
            HttpSession session = request.getSession();

            JsonReader jsonReader = new JsonReader(request.getReader());
            User userData = gson.fromJson(jsonReader, User.class);

            String login = userData.getLogin();
            String pass = UserUtils.buildHash(userData.getPassword());

            UserDao userDao = new UserDao();
            User user = userDao.getUser(login, pass);

            if (user != null) {
                session.setAttribute("auth", user);
                responseData.setLocation("/");
            } else {
                responseData.setMessage("Не удалось найти пользователя");
            }
        } catch (NoSuchAlgorithmException e) {
            responseData.setMessage(e.getMessage());
        }
        response.getWriter().print(responseData);
    }
}
