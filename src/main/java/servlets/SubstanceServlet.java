package servlets;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import dao.SubstanceDao;
import models.Substance;
import utils.ResponseData;
import utils.UserUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SubstanceServlet", value = "/substances")
public class SubstanceServlet extends HttpServlet {
    private final Gson gson = new Gson();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!UserUtils.checkAuthUser(request)) {
            response.sendRedirect("/login");
            return;
        }

        response.setHeader("content-type", "text/html; charset=UTF-8");
        request.getRequestDispatcher("/WEB-INF/substanceServlet.jsp").include(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        ResponseData responseData = new ResponseData();

        JsonReader jsonReader = new JsonReader(request.getReader());
        Substance data = gson.fromJson(jsonReader, Substance.class);

        String mnn = data.getMnn();
        String farmGroup = data.getFarmGroup();

        if (mnn != null && farmGroup != null) {
            SubstanceDao substanceDao = new SubstanceDao();
            substanceDao.addSubstance(data);
            responseData.setLocation("/substances");
        }else {
            responseData.setMessage("Неверный набор параметров");
        }
        response.getWriter().print(responseData);
    }

    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");

        ResponseData responseData = new ResponseData();

        JsonReader jsonReader = new JsonReader(request.getReader());
        Substance data = gson.fromJson(jsonReader, Substance.class);

        Long id = data.getId();

        if (data.getId() != null) {
            SubstanceDao substanceDao = new SubstanceDao();
            substanceDao.deleteSubstance(id);
            responseData.setLocation("/substances");
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
        Substance data = gson.fromJson(jsonReader, Substance.class);

        String mnn = data.getMnn();
        String farmGroup = data.getFarmGroup();
        Long id = data.getId();

        if (mnn != null && farmGroup != null && id != null) {
            SubstanceDao substanceDao = new SubstanceDao();
            substanceDao.updateSubstance(data);
            responseData.setLocation("/substances");
        } else {
            responseData.setMessage("Не верный набор параметров");
        }
        response.getWriter().print(responseData);
    }
}

