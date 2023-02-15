package servlets;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import dao.ProducerDao;
import dao.UserDao;
import models.Producer;
import models.User;
import utils.ResponseData;
import utils.UserUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.UUID;

@WebServlet(name = "ProducerServlet", value = "/producers")
public class ProducerServlet  extends HttpServlet {
    private final Gson gson = new Gson();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!UserUtils.checkAuthUser(request)) {
            response.sendRedirect("/login");
            return;
        }

        response.setHeader("content-type", "text/html; charset=UTF-8");
        request.getRequestDispatcher("/WEB-INF/producerServlet.jsp").include(request, response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        ResponseData responseData = new ResponseData();

        JsonReader jsonReader = new JsonReader(request.getReader());
        Producer producerData = gson.fromJson(jsonReader, Producer.class);

        String name = producerData.getName();
        String country = producerData.getCountry();

        if (name != null && country != null) {
            Producer producer = new Producer();
            producer.setName(name);
            producer.setCountry(country);

            ProducerDao producerDao = new ProducerDao();
            producerDao.addProducer(producer);

            responseData.setLocation("/producers");
        } else {
            responseData.setMessage("Не верный набор параметров");
        }
        response.getWriter().print(responseData);
    }
    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ResponseData responseData = new ResponseData();

        JsonReader jsonReader = new JsonReader(request.getReader());
        Producer producerData = gson.fromJson(jsonReader, Producer.class);

        Long producerDelete = producerData.getId();
        if (producerDelete != null){
            ProducerDao producerDao = new ProducerDao();
            producerDao.deleteProducer(producerDelete);
            responseData.setLocation("/producers");
        }else {
            responseData.setMessage("Не верный набор параметров");
        }
        response.getWriter().print(responseData);
    }

    public void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        ResponseData responseData = new ResponseData();

        JsonReader jsonReader = new JsonReader(request.getReader());
        Producer producerData = gson.fromJson(jsonReader, Producer.class);

        String name = producerData.getName();
        String country = producerData.getCountry();
        Long producerId = producerData.getId();

        if (name != null && country != null && producerId != null) {
            ProducerDao producerDao = new ProducerDao();

            Producer producer = producerDao.getProducer(producerId);

            if (producer != null){
                producer.setName(name);
                producer.setCountry(country);
                producerDao.updateProducer(producer);

                responseData.setLocation("/producers");
            } else {
                responseData.setMessage("Не удалось найти запись");
            }
        } else {
            responseData.setMessage("Не верный набор параметров");
        }
        response.getWriter().print(responseData);
    }
}
