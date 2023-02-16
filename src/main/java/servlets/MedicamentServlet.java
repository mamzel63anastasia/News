package servlets;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import dao.MedicamentDao;
import models.Medicament;
import models.MedicamentData;
import utils.ResponseData;
import utils.UserUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "MedicamentServlet", value = "/medicaments")
public class MedicamentServlet extends HttpServlet {
    private final Gson gson = new Gson();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!UserUtils.checkAuthUser(request)) {
            response.sendRedirect("/medicaments");
            return;
        }

        response.setHeader("content-type", "text/html; charset=UTF-8");
        request.getRequestDispatcher("/WEB-INF/medicamentsServlet.jsp").include(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        ResponseData responseData = new ResponseData();

        JsonReader jsonReader =  new JsonReader(request.getReader());
        MedicamentData data = gson.fromJson(jsonReader, MedicamentData.class);

        Medicament medicament = new Medicament();
        medicament.setDose(data.getDose());
        medicament.setName(data.getName());
        medicament.setNumber(data.getNumber());
        medicament.setSubstance(data.getSubstance());
        medicament.setProducer(data.getProducer());

        MedicamentDao medicamentDao = new MedicamentDao();
        medicamentDao.addMedicament(medicament);

        responseData.setLocation("/medicaments");
        response.getWriter().print(responseData);
    }

    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");

        ResponseData responseData = new ResponseData();

        JsonReader jsonReader = new JsonReader(request.getReader());
        MedicamentData data = gson.fromJson(jsonReader, MedicamentData.class);

        Long id = data.getId();

        if (data.getId() != null) {
            MedicamentDao medicamentDao = new MedicamentDao();
            medicamentDao.deleteMedicament(id);
            responseData.setLocation("/medicaments");
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
        MedicamentData data = gson.fromJson(jsonReader, MedicamentData.class);

        MedicamentDao medicamentDao = new MedicamentDao();

        Medicament medicament = medicamentDao.getMedicament(data.getId());
        medicament.setNumber(data.getNumber());
        medicament.setName(data.getName());
        medicament.setDose(data.getDose());
        medicament.setProducer(data.getProducer());
        medicament.setSubstance(data.getSubstance());

        medicamentDao.updateMedicament(medicament);

        responseData.setLocation("/medicaments");

        response.getWriter().print(responseData);
    }

}
