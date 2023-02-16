package servlets;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import dao.OrderDao;
import models.Order;
import models.OrderData;
import utils.ResponseData;
import utils.UserUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "OrderServlet", value = "/orders")
public class OrderServlet extends HttpServlet {
    private final Gson gson = new Gson();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!UserUtils.checkAuthUser(request)) {
            response.sendRedirect("/orders");
            return;
        }
        response.setHeader("content-type", "text/html; charset=UTF-8");
        request.getRequestDispatcher("/WEB-INF/ordersServlet.jsp").include(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        ResponseData responseData = new ResponseData();

        JsonReader jsonReader =  new JsonReader(request.getReader());
        OrderData data = gson.fromJson(jsonReader, OrderData.class);

        if (data.getAdress() != null){
            Order order = new Order();
            order.setAdress(data.getAdress());
            order.setUser(data.getUser());
            order.setMedicament(data.getMedicament());

            OrderDao orderDao = new OrderDao();
            orderDao.addOrder(order);

            responseData.setLocation("/orders");
        } else {
            responseData.setMessage("необходимо указать все параметры");
        }

        response.getWriter().print(responseData);

    }

    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");

        ResponseData responseData = new ResponseData();

        JsonReader jsonReader = new JsonReader(request.getReader());
        OrderData data = gson.fromJson(jsonReader, OrderData.class);

        if (data.getId() != null) {
            OrderDao orderDao = new OrderDao();
            orderDao.deleteOrder(data.getId());
            responseData.setLocation("/orders");
        } else {
            responseData.setMessage("Неверный набор параметров");
        }

        response.getWriter().print(responseData);
    }

    public void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        ResponseData responseData = new ResponseData();

        JsonReader jsonReader = new JsonReader(request.getReader());
        OrderData data = gson.fromJson(jsonReader, OrderData.class);

        if (data.getId() != null) {
            OrderDao orderDao = new OrderDao();

            Order order = orderDao.getOrder(data.getId());
            order.setAdress(data.getAdress());
            order.setUser(data.getUser());
            order.setMedicament(data.getMedicament());

            orderDao.updateOrder(order);

            responseData.setLocation("/orders");
        } else {
            responseData.setMessage("Неверный набор параметров");
        }

        response.getWriter().print(responseData);
    }
}
