package servlets;

import dao.ProducerDao;
import dao.UserDao;
import models.Producer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@WebServlet(name = "ProducerServlet", value = "/producers")
public class ProducerServlet  extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setHeader("content-type", "text/html; charset=UTF-8");
        request.getRequestDispatcher("/WEB-INF/producerServlet.jsp").include(request, response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();

        String name = request.getParameter("name");
        String country = request.getParameter("country");

        if (name != null && country != null) {
            Producer producer = new Producer();
            producer.setName(name);
            producer.setCountry(country);

            ProducerDao producerDao = new ProducerDao();
            producerDao.addProducer(producer);
        } else {
            session.setAttribute("messageInfo", "Не верный набор параметров");
        }
        response.sendRedirect("/producers");
    }
    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String producerDelete = request.getParameter("producerDelete");
        if (producerDelete != null){
            ProducerDao producerDao = new ProducerDao();
            producerDao.deleteProducer(UUID.fromString(producerDelete));
        }
        response.sendRedirect("/producers");
    }

    public void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        String name = request.getParameter("name");
        String country = request.getParameter("country");
        String producerId = request.getParameter("producerId");

        if (name != null && country != null && producerId != null) {
            ProducerDao producerDao = new ProducerDao();

            Producer producer = producerDao.getProducer(UUID.fromString(producerId));

            if (producer != null){
                producer.setName(name);
                producer.setCountry(country);
                producerDao.updateProducer(producer);
            } else {
                session.setAttribute("messageInfo", "Не удалось найти запись");
            }
        } else {
            session.setAttribute("messageInfo", "Не верный набор параметров");
        }
        response.sendRedirect("/producers");
    }

}
