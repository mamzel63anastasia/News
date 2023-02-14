package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "MedicamentServlet", value = "/medicaments")
public class MedicamentServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        ProducerDao producerDao = new ProducerDao();
//        Producer producer = new Producer();
//        producer.setName("Фарм");
//        producer.setCountry("Россия");
//        producerDao.addProducer(producer);
        response.setHeader("content-type", "text/html; charset=UTF-8");
        request.getRequestDispatcher("/WEB-INF/medicamentsServlet.jsp").include(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) {
//        ProducerDao producerDao =  new ProducerDao();
//        Producer producer = new Producer();
//        producer.setName(producer.getName());
//        producer.setCountry(producer.getName());
//        producerDao.addProducer(producer);

    }

    public void doDelete(HttpServletRequest request, HttpServletResponse response) {

    }

    public void doPut(HttpServletRequest request, HttpServletResponse response) {

    }

}
